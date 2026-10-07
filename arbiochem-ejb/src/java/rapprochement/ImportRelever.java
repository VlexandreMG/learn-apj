package rapprochement;

import bean.CGenUtil;
import caisse.Caisse;
import mg.cnaps.compta.ConstanteCompta;
import mg.cnaps.compta.ConstanteComptabilite;
import utilitaire.Utilitaire;
import utils.csv.CsvReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ImportRelever {
    Map<String,String[]> titreCsv;
    Map<String,Integer> debutDeLigne;
    Map<String,String> colonneRequired;
    Map<String,String> delimiter;

    private String idCaisse;
    private String separateur;

    public ImportRelever (String idCaisse) throws Exception {
        this.setIdCaisse(idCaisse);
        this.setSeparateur(";");
    }


    public Relever creerRelever(InputStream inputStream , Date date , Connection con) throws Exception {

        Relever relever = new Relever();
        List<ReleverDetail> releverDetails = new ArrayList<>();
        relever = new Relever();

        Date daty = date;
        String idCaisse = this.getIdCaisse();

        Caisse c = new Caisse();
        Caisse caisse = (Caisse) c.getById(idCaisse, c.getNomTable(),con);
        String idTypeBanque = caisse.getIdTypeBanque();
        System.out.println("===>>>>>>>>>> ID TYPE BANQUE = " + idTypeBanque  );
        boolean valide = "TB1".equals(idTypeBanque)
                || "TB2".equals(idTypeBanque)
                || "TB3".equals(idTypeBanque);

        if (!valide) {
            throw new Exception("Caisse : " + caisse.getVal() + " n'a pas de type banque ou type banque invalide");
        }

        Date dateDebut = null;
        Date dateFin = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (idTypeBanque.equals("TB1")) {
            System.out.println("BMOI sy BNI");
            boolean dateDejaLue = false;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String ligneBrute;
                boolean inTable = false;
                while ((ligneBrute = reader.readLine()) != null) {

                    StringBuilder sb = new StringBuilder(ligneBrute);
                    while (countQuotes(sb.toString()) % 2 != 0) {
                        String suite = reader.readLine();
                        if (suite == null) break;
                        sb.append("\n").append(suite);
                    }

                    String ligne = sb.toString();
                    String clean = normalize(ligne);

                    if (clean.startsWith("date") && !dateDejaLue) {
                        String[] tab = ligne.split(this.getSeparateur());
                        System.out.println(clean);
                        if (tab.length >= 2) {
                            daty = Date.valueOf(LocalDate.parse(tab[1].trim(), formatter));
                            dateDejaLue = true;
                            continue;
                        }
                    }

                    if (clean.startsWith("periode du")) {
                        String[] tab = ligne.split(this.getSeparateur());
                        if (tab.length >= 4) {
                            dateDebut = Date.valueOf(LocalDate.parse(tab[1].trim(), formatter));
                            dateFin = Date.valueOf(LocalDate.parse(tab[3].trim(), formatter));
                            continue;
                        }
                    }

                    if (clean.startsWith("date;valeur;libelle")) {
//                        System.out.println("tafiditra ato");
                        inTable = true;
                        continue;
                    }

                    if (!inTable) continue;

                    if (clean.contains("total") || clean.contains("solde (mga)")) {
                        inTable = false;
                        continue;
                    }

                    if (inTable) {
                        String[] tab = splitCsvLine(ligne);
                        if (tab.length >= 6) {
                            ReleverDetail detail = new ReleverDetail();
                            String dateBrute = tab[0].trim();
                            String dateValeurBrute = tab[1].trim();
                            detail.setDaty(Date.valueOf(LocalDate.parse(dateBrute, formatter)));
                            detail.setDatyvaleur(Date.valueOf(LocalDate.parse(dateValeurBrute, formatter)));
                            detail.setDesignation(tab[2].trim().replace("\"", ""));
                            detail.setDebit(castToDouble(tab[3]));
                            detail.setCredit(castToDouble(tab[4]));
                            detail.setSolde(castToDouble(tab[5]));
                            releverDetails.add(detail);
                        }
                    }
                }
            }
        } if (idTypeBanque.equals("TB3")) {
            System.out.println("BRED");

            DateTimeFormatter formatterBred = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    if (ligne.trim().isEmpty()) {
                        continue;
                    }

                    String[] tab = splitCsvLine(ligne);

                    // index attendu (0-based), pas d'entete dans ce format :
                    // 3  = date de l'arrete / statement
                    // 7  = date operation
                    // 8  = date valeur
                    // 9  = montant signe (debit negatif / credit positif)
                    // 13 = designation / libelle
                    if (tab.length >= 14) {
                        ReleverDetail detail = new ReleverDetail();

                        String dateStatementBrute = tab[3].trim();
                        String dateOperationBrute = tab[7].trim();
                        String dateValeurBrute = tab[8].trim();
                        String montantBrut = tab[9].trim();
                        String designation = tab[13].trim().replace("\"", "");

                        Date dateStatement = Date.valueOf(LocalDate.parse(dateStatementBrute, formatterBred));
                        if (dateDebut == null || dateStatement.before(dateDebut)) {
                            dateDebut = dateStatement;
                        }
                        if (dateFin == null || dateStatement.after(dateFin)) {
                            dateFin = dateStatement;
                        }

                        detail.setDaty(Date.valueOf(LocalDate.parse(dateOperationBrute, formatterBred)));
                        detail.setDatyvaleur(Date.valueOf(LocalDate.parse(dateValeurBrute, formatterBred)));
                        detail.setDesignation(designation);
                        detail.setSolde(0d);

                        double montant = castToDouble(montantBrut);
                        if (montant < 0) {
                            detail.setDebit(Math.abs(montant));
                            detail.setCredit(0d);
                        } else {
                            detail.setCredit(montant);
                            detail.setDebit(0d);
                        }

                        releverDetails.add(detail);
                    }
                }
            }

            if (dateFin != null) {
                daty = dateFin;
            }
        } if (idTypeBanque.equals("TB2")) {
            System.out.println("Banque MCB");

            DateTimeFormatter formatterPeriode = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatterLigne = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    String ligneTrim = ligne.trim();
                    if (ligneTrim.isEmpty()) {
                        continue;
                    }

                    String clean = normalize(ligneTrim);

                    if (clean.startsWith("periode specifiee") || clean.startsWith("periode du")) {
                        int debutParenthese = ligneTrim.indexOf('(');
                        int finParenthese = ligneTrim.indexOf(')');
                        if (debutParenthese >= 0 && finParenthese > debutParenthese) {
                            String contenu = ligneTrim.substring(debutParenthese + 1, finParenthese);
                            String[] bornes = contenu.split(" - ");
                            if (bornes.length == 2) {
                                dateDebut = Date.valueOf(LocalDate.parse(bornes[0].trim(), formatterPeriode));
                                dateFin = Date.valueOf(LocalDate.parse(bornes[1].trim(), formatterPeriode));
                            }
                        }
                        continue;
                    }

                    if (!ligneTrim.startsWith("\"")) {
                        continue;
                    }

                    String contenuLigne = ligneTrim;
                    if (contenuLigne.length() >= 2 && contenuLigne.startsWith("\"") && contenuLigne.endsWith("\"")) {
                        contenuLigne = contenuLigne.substring(1, contenuLigne.length() - 1);
                    }
                    contenuLigne = contenuLigne.replace("\"\"", "\"");

                    String[] tab = splitCsvLineComma(contenuLigne);

                    // 0 = date de la transaction
                    // 1 = date de valeur
                    // 2 = numero de reference de l'operation
                    // 3 = nature de compte (designation)
                    // 4 = debit
                    // 5 = credit
                    // 6 = solde
                    if (tab.length >= 7) {
                        ReleverDetail detail = new ReleverDetail();

                        String dateOperationBrute = tab[0].trim();
                        String dateValeurBrute = tab[1].trim();
                        String designation = tab[3].trim();
                        String debitBrut = tab[4].trim();
                        String creditBrut = tab[5].trim();
                        String soldeBrut = tab[6].trim();

                        detail.setDaty(Date.valueOf(LocalDate.parse(dateOperationBrute, formatterLigne)));
                        detail.setDatyvaleur(Date.valueOf(LocalDate.parse(dateValeurBrute, formatterLigne)));
                        detail.setDesignation(designation);
                        detail.setDebit(castToDouble(debitBrut));
                        detail.setCredit(castToDouble(creditBrut));
                        detail.setSolde(castToDouble(soldeBrut));

                        releverDetails.add(detail);
                    }
                }
            }

            if (dateFin != null) {
                daty = dateFin;
            }
        }

        relever.setDaty(daty);
        relever.setDatyDebut(dateDebut);
        relever.setDatyFin(dateFin);
        relever.setIdCaisse(idCaisse);

        ReleverDetail[] tableau = releverDetails.toArray(new ReleverDetail[0]);
        relever.setFille(tableau);

        return relever;
    }

    private int countQuotes(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '"') count++;
        }
        return count;
    }


    private String[] splitCsvLine(String ligne) {
        List<String> champs = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < ligne.length(); i++) {
            char c = ligne.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ';' && !inQuotes) {
                champs.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        champs.add(current.toString());
        return champs.toArray(new String[0]);
    }

    private String[] splitCsvLineComma(String ligne) {
        List<String> champs = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < ligne.length(); i++) {
            char c = ligne.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                champs.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        champs.add(current.toString());
        return champs.toArray(new String[0]);
    }

    private String normalize(String s) {
        return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")   // enlève accents
                .replace("\uFEFF", "")
                .replace("\u00A0", " ")
                .trim()
                .toLowerCase();
    }

    public ImportRelever() {
        titreCsv = new HashMap<>();
        debutDeLigne = new HashMap<>();
        colonneRequired = new HashMap<>();
        delimiter = new HashMap<>();

        // Banque BNI
        titreCsv.put("CAI000237",new String[]{"titre1","titre2","titre3","titre4","titre5","titre6"});
        debutDeLigne.put("CAI000237",14);
        colonneRequired.put("CAI000237","titre1");
        delimiter.put("CAI000237",";");

        // Banque BMOI
        titreCsv.put("CAI000233",new String[]{"titre1","titre2","titre3","titre4","titre5","titre6"});
        debutDeLigne.put("CAI000233",14);
        colonneRequired.put("CAI000233","titre1");
        delimiter.put("CAI000233",";");
        // Banque BRED
        titreCsv.put("CAI000229",new String[]{"titre1","titre2","titre3","titre4","titre5","titre6","titre7","titre8","titre9","titre10","titre11","titre12","titre13","titre14"});
        debutDeLigne.put("CAI000229",1);
        colonneRequired.put("CAI000229","titre4");
        delimiter.put("CAI000229",";");
        // Banque MCB
        titreCsv.put("CAI000234",new String[]{"titre1","titre2","titre3","titre4","titre5","titre6","titre7"});
        debutDeLigne.put("CAI000234",26);
        colonneRequired.put("CAI000234","titre1");
        delimiter.put("CAI000234",",");
        // Banque BNI 2
        titreCsv.put("CAISS00001",new String[]{"titre1","titre2","titre3","titre4","titre5","titre6"});
        debutDeLigne.put("CAISS00001",14);
        colonneRequired.put("CAISS00001","titre1");
        delimiter.put("CAISS00001",";");
    }

    public boolean contains(String[] data, String id) {
        for (String s : data) {
            if (s.equalsIgnoreCase(id)) {
                return true;
            }
        }

        return false;
    }


    public Relever genererRelever(InputStream inputStream, String delimiter, String idCaisse, Date daty) throws Exception {
        Integer startLine = this.getDebutDeLigne().get(idCaisse);
        Relever relever = null;
        Caisse caisseRef = new Caisse();
        if (startLine != null) {
            relever = new Relever();
            relever.setDaty(daty);
            relever.setIdCaisse(idCaisse);
            Date dateDebut = null;
            Date dateFin = null;

            String[] idCaisseBI = caisseRef.getIdCaisseByTypeBanque(ConstanteCompta.caisseBI, null);
            String[] idCaisseMC = caisseRef.getIdCaisseByTypeBanque(ConstanteCompta.caisseMC, null);
            String[] idCaisseBR = caisseRef.getIdCaisseByTypeBanque(ConstanteCompta.caisseBR, null);

            if (contains(idCaisseBI, idCaisse)){
                List<Map<String,String>> dataDate = CsvReader.readCSV(inputStream,this.getDelimiter().get(idCaisse),2,"titre1",this.getTitreCsv().get(idCaisse));
                if (!dataDate.isEmpty()){
                    Map<String,String> dataMap = dataDate.get(0);
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dateDebut = Date.valueOf(LocalDate.parse(dataMap.get("titre2"),dateTimeFormatter));
                    dateFin = Date.valueOf(LocalDate.parse(dataMap.get("titre4"),dateTimeFormatter));
                }
            }

            if (contains(idCaisseMC, idCaisse)){
                List<Map<String,String>> dataDate = CsvReader.readCSV(inputStream,this.getDelimiter().get(idCaisse),22,"titre1",this.getTitreCsv().get(idCaisse));
                if (!dataDate.isEmpty()){
                    Map<String,String> dataMap = dataDate.get(0);
                    String [] tab = dataMap.get("titre1").split(" ");
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    dateDebut = Date.valueOf(LocalDate.parse(tab[2].replace("(",""),dateTimeFormatter));
                    dateFin = Date.valueOf(LocalDate.parse(tab[4].replace(")",""),dateTimeFormatter));
                }
            }
            List<Map<String,String>> data = CsvReader.readCSV(inputStream,this.getDelimiter().get(idCaisse),startLine,this.getColonneRequired().get(idCaisse),this.getTitreCsv().get(idCaisse));

            relever.setDatyDebut(dateDebut);
            relever.setDatyFin(dateFin);
            List<ReleverDetail> releverDetails = new ArrayList<>();
            for (Map<String,String> row : data) {
                ReleverDetail releverDetail = new ReleverDetail();
                if (contains(idCaisseBI, idCaisse)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dateBrute = row.get("titre1").replace(" ","");
                    String dateValeurBrute = row.get("titre2").replace(" ","");
                    releverDetail.setDaty(Date.valueOf(LocalDate.parse(dateBrute,formatter)));
                    releverDetail.setDatyvaleur(Date.valueOf(LocalDate.parse(dateValeurBrute,formatter)));
                    releverDetail.setDesignation(row.get("titre3"));
                    releverDetail.setDebit(castToDouble(row.get("titre4")));
                    releverDetail.setCredit(castToDouble(row.get("titre5")));
                    releverDetail.setSolde(castToDouble(row.get("titre6")));
                }
                if (contains(idCaisseBR, idCaisse)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dateBrute = row.get("titre4").replace(" ","").replace(".","/");
                    releverDetail.setDaty(Date.valueOf(LocalDate.parse(dateBrute,formatter)));
                    releverDetail.setDatyvaleur(Date.valueOf(LocalDate.parse(dateBrute,formatter)));
                    releverDetail.setDesignation(row.get("titre14"));
                    releverDetail.setDebit(castToDouble(row.get("titre6")));
                    releverDetail.setCredit(castToDouble(row.get("titre7")));
                    releverDetail.setSolde(castToDouble(row.get("titre10")));
                }
                if (contains(idCaisseMC, idCaisse)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);;
                    String dateBrute = row.get("titre1");
                    String dateValeurBrute = row.get("titre2");
                    releverDetail.setDaty(Date.valueOf(LocalDate.parse(dateBrute,formatter)));
                    releverDetail.setDatyvaleur(Date.valueOf(LocalDate.parse(dateValeurBrute,formatter)));
                    releverDetail.setDesignation(row.get("titre4"));
                    releverDetail.setDebit(castToDouble(row.get("titre5")));
                    releverDetail.setCredit(castToDouble(row.get("titre6")));
                    releverDetail.setSolde(castToDouble(row.get("titre7")));
                }
                releverDetails.add(releverDetail);
            }
            relever.setFille(releverDetails.toArray(new ReleverDetail[]{}));
        }
        else {
            throw new Exception("La caisse n'est pas prise en compte");
        }
        return relever;
    }

    public double castToDouble (String val){
        String result = val != null && val.replace(" ","").isEmpty()==false ? val.replace(" ","").replace(".","") : "0";
        return Double.parseDouble(result.replace(",","."));
    }
    public Map<String, String[]> getTitreCsv() {
        return titreCsv;
    }

    public void setTitreCsv(Map<String, String[]> titreCsv) {
        this.titreCsv = titreCsv;
    }

    public Map<String, Integer> getDebutDeLigne() {
        return debutDeLigne;
    }

    public void setDebutDeLigne(Map<String, Integer> debutDeLigne) {
        this.debutDeLigne = debutDeLigne;
    }

    public Map<String, String> getColonneRequired() {
        return colonneRequired;
    }

    public void setColonneRequired(Map<String, String> colonneRequired) {
        this.colonneRequired = colonneRequired;
    }

    public Map<String, String> getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Map<String, String> delimiter) {
        this.delimiter = delimiter;
    }

    public String getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(String idCaisse) throws Exception {
        this.idCaisse = idCaisse;
    }

    public String getSeparateur() {
        return separateur;
    }

    public void setSeparateur(String separateur) {
        this.separateur = separateur;
    }

}