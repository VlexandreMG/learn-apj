package charge;

import annexe.Poste;
import bean.CGenUtil;
import bean.ClassMAPTable;
import fabrication.HeureSupFabrication;
import fabrication.RessourceParFabrication;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import paie.edition.PaieEditionmoisannee;
import paie.employe.PaieInfoPersonnel;
import rh.QualificationPaie;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class ChargePersonnelTemp extends ClassMAPTable {
    private String id;
    private String idPersonnel;
    private String idPoste;
    private String idClasse;
    private String idFab;
    private double heureNormal;
    private double hs;
    private double ferie;
    private double dim;
    private double nuit;
    private double iff;
    private double taux;
    private Sheet sheet;
    private String matriculeCol;
    private Map<String, Integer> colIndex;
    private int headerRow;
    private Date daty;

    private static final String[] HEURE_NORMAL_ALIASES = {
            "HEURE NORMAL",
            "HEURE NORMALE",
            "HEURE HN",
            "HN"
    };

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public ChargePersonnelTemp() {
        this.setNomTable("CHARGEPERSONNELTEMP");
    }

    public Sheet getSheet() { return sheet; }
    public void setSheet(Sheet sheet) { this.sheet = sheet; }
    public String getMatriculeCol() { return matriculeCol; }
    public void setMatriculeCol(String matriculeCol) { this.matriculeCol = matriculeCol; }
    public Map<String, Integer> getColIndex() { return colIndex; }
    public void setColIndex(Map<String, Integer> colIndex) { this.colIndex = colIndex; }
    public int getHeaderRow() { return headerRow; }
    public void setHeaderRow(int headerRow) { this.headerRow = headerRow; }

    public double getHeureNormal() { return heureNormal; }
    public void setHeureNormal(double heureNormal) { this.heureNormal = heureNormal; }
    public double getHs() { return hs; }
    public void setHs(double hs) { this.hs = hs; }
    public double getFerie() { return ferie; }
    public void setFerie(double ferie) { this.ferie = ferie; }
    public double getDim() { return dim; }
    public void setDim(double dim) { this.dim = dim; }
    public double getNuit() { return nuit; }
    public void setNuit(double nuit) { this.nuit = nuit; }
    public double getIff() { return iff; }
    public void setIff(double iff) { this.iff = iff; }
    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }
    public String getIdPersonnel() { return idPersonnel; }
    public void setIdPersonnel(String idPersonnel) { this.idPersonnel = idPersonnel; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getIdPoste() { return idPoste; }
    public void setIdPoste(String idPoste) { this.idPoste = idPoste; }
    public String getIdClasse() { return idClasse; }
    public void setIdClasse(String idClasse) { this.idClasse = idClasse; }
    public String getIdFab() { return idFab; }
    public void setIdFab(String idFab) { this.idFab = idFab; }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CH-", "getseq_chargepersonneltemp");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() { return id; }
    @Override
    public String getAttributIDName() { return "id"; }

    public void createRessourceParFab(String u, Connection c) throws SQLException {
        boolean canClose = false;
        try {
            if (c == null){
                c = new UtilDB().GetConn();
                canClose = true;
            }

            RessourceParFabrication ressourceParFabrication = new RessourceParFabrication();
            ressourceParFabrication.setIdFabrication(this.getIdFab());
            ressourceParFabrication.setIdRessource(this.getIdPersonnel());
            String aWhere = " AND TRUNC(DATY) = DATE '" + this.getDaty() + "' ";
            RessourceParFabrication[] ressourceParFabrications = (RessourceParFabrication[]) CGenUtil.rechercher(ressourceParFabrication,null,null,c,aWhere);

//            System.out.println("FABRICATION: " + this.getIdFab());
//            System.out.println("PERSONNELE: " + this.getIdPersonnel());
//            System.out.println("DATY: " + this.getDaty());
//            System.out.println("LENGTH: " + ressourceParFabrications.length);

            // --- CORRECTION : ignorer le doublon au lieu de crasher ---
            if (ressourceParFabrications != null && ressourceParFabrications.length > 0) {
//                System.out.println("DEBUG: Doublon ignoré pour matricule " + this.getIdPersonnel()
//                        + " (déjà importé pour cette fabrication et cette date)");
                return; // <-- on sort proprement, sans exception
            }
//            RessourceParFabrication ressource = new RessourceParFabrication();
//            ressource.setIdRessource(this.getIdPersonnel());
//            ressource.setIdFabrication(this.getIdFab());
//            RessourceParFabrication[] rs = (RessourceParFabrication[])CGenUtil.rechercher(ressource,null,null,c,"");
//
//            String idRessourceParFab;
//
//            if (rs.length > 0){
//                System.out.println("DEBUG: Resource already exists for " + this.getIdPersonnel());
//                idRessourceParFab = rs[0].getId();
//            } else {
//                ressource.setIdPoste(this.getIdPoste());
//                ressource.setIdQualification(this.getIdClasse());
//                ressource.setDaty(this.getDaty());
//                ressource.createObject(u,c);
//                idRessourceParFab = ressource.getId();
//            }

            String idRessourceParFab;

            RessourceParFabrication ressource = new RessourceParFabrication();
            ressource.setIdRessource(this.getIdPersonnel());
            ressource.setIdFabrication(this.getIdFab());
            ressource.setIdPoste(this.getIdPoste());
            ressource.setIdQualification(this.getIdClasse());
            ressource.setDaty(this.getDaty());
            ressource.createObject(u,c);
            idRessourceParFab = ressource.getId();

            // Create Heure Sup
            HeureSupFabrication heureSup = new HeureSupFabrication();
            heureSup.setIdRessParFab(idRessourceParFab);
            heureSup.setHS(this.getHs());
            heureSup.setMN(this.getNuit());
            heureSup.setHD(this.getDim());
            heureSup.setJF(this.getFerie());
            heureSup.setTemporaire(0);
            heureSup.setIdFabrication(this.getIdFab());
            heureSup.setIF(this.getIff());
            heureSup.setMontant(0);
            heureSup.setDaty(this.getDaty());
            heureSup.setDateImport(Utilitaire.dateDuJourSql());

            // Assuming setter exists on HeureSupFabrication
            heureSup.setHeurenormale(this.getHeureNormal());

//            System.out.println("DEBUG: Creating HeureSup - HN: " + this.getHeureNormal() + ", HS: " + this.getHs());
            heureSup.createObject(u,c);

        } catch (Exception e){
            e.printStackTrace(); // Log error
            if (canClose) c.rollback();
            throw new SQLException(e);
        } finally{
            if (canClose) c.close();
        }
    }

    private String sanitizeMatricule(String matricule) {
        if (matricule == null) return null;

        // 1. Trim spaces
        String clean = matricule.trim();

        // 2. Remove all non-alphanumeric characters (keep letters and numbers)
        clean = clean.replaceAll("[^A-Za-z0-9]", "");

        // 3. Optional: uppercase everything
        clean = clean.toUpperCase();

        return clean;
    }

    private Date extractAndValidateDate(ArrayList<String> erreurs) {
        String datyStr = null;
        boolean foundDateOfLabel = false;
        int searchLimit = Math.min(this.getHeaderRow(), 15);
        for (int rNum = 0; rNum <= searchLimit; rNum++) {
            Row r = this.getSheet().getRow(rNum);
            if (r == null) continue;
            for (int cNum = 0; cNum < r.getLastCellNum(); cNum++) {
                Cell cell = r.getCell(cNum);
                String val = safe(cell);
                if (val != null && val.toLowerCase().contains("date of")) {
                    Cell dateCell = r.getCell(cNum + 1);
                    datyStr = safe(dateCell);
                    foundDateOfLabel = true;
                    break;
                }
            }
            if (foundDateOfLabel) break;
        }

        if (!foundDateOfLabel) {
            Row firstRow = this.getSheet().getRow(0);
            datyStr = firstRow != null ? safe(firstRow.getCell(1)) : null;
        }

        if (datyStr == null || datyStr.trim().isEmpty()) {
            erreurs.add("La date de fabrication (champ 'Date of') est obligatoire et introuvable ou vide.");
            return null;
        }

        datyStr = datyStr.trim();
        if (!datyStr.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            erreurs.add("Le format de la date de fabrication '" + datyStr + "' est incorrect. Le format attendu est JJ/MM/AAAA (ex: 29/04/2026).");
            return null;
        }

        return Utilitaire.stringDate(datyStr);
    }

    public ArrayList<String> importerChargePersonnel(String u, Connection c) throws Exception {
        ArrayList<String> erreurs = new ArrayList<>();
        Date datyImport = Utilitaire.dateDuJourSql();
        Date daty = extractAndValidateDate(erreurs);
        if (daty == null) {
            return erreurs;
        }

        boolean canClose = false;
        java.util.Set<String> matriculesTraites = new java.util.HashSet<>();
        CallableStatement deleteRessourceParFab = null;
        CallableStatement deleteRessourceHasFab = null;

        try {
            if (c == null){
                c = new UtilDB().GetConn();
                canClose = true;
            }

//            String procedureDeleteRessourceParFab = "{call DELETERESSOURCEFABRICATION(?)}";
//            String procedureDeleteRessourceHsFab = "{call DELETEHSFABRICATION(?)}";
//
//            RessourceParFabrication ressourceParFabrication = new RessourceParFabrication();
//            ressourceParFabrication.setIdFabrication(this.getIdFab());
//            RessourceParFabrication[] ressourceParFabrications = (RessourceParFabrication[])CGenUtil.rechercher(ressourceParFabrication,null,null,c,"");
//            if (ressourceParFabrications.length > 0) {
//                deleteRessourceParFab = c.prepareCall(procedureDeleteRessourceParFab);
//                deleteRessourceParFab.setString(1, this.getIdFab());
//                deleteRessourceParFab.executeUpdate();
//
//                deleteRessourceHasFab = c.prepareCall(procedureDeleteRessourceHsFab);
//                deleteRessourceHasFab.setString(1, this.getIdFab());
//                deleteRessourceHasFab.executeUpdate();
//            }


            boolean foundHeureNormal = false;
            for (String alias : HEURE_NORMAL_ALIASES) {
                if (this.getColIndex().get(alias) != null) {
                    foundHeureNormal = true;
                    break;
                }
            }
            if (!foundHeureNormal) {
                erreurs.add("La colonne HEURE NORMAL (ou l'un de ses alias comme HEURE HN, HN) est introuvable");
//                System.out.println("ERROR: 'HEURE NORMAL' column or alias not found in mapping!");
            }

            for (int i = this.getHeaderRow() + 1; i <= this.getSheet().getLastRowNum(); i++) {
                Map<String,Integer> index = this.getColIndex();
                Row row = this.getSheet().getRow(i);
                if (row == null) continue;

                String matricule = normalizeMatricule(safe(row.getCell(index.get(this.getMatriculeCol()))));
//                System.out.println("DEBUG IMPORT: Row " + i + " - Raw matricule from cell: " + safe(row.getCell(index.get(this.getMatriculeCol()))));
//                System.out.println("DEBUG IMPORT: Row " + i + " - Normalized matricule: " + matricule);

                if (matricule == null || matricule.isEmpty()) {
//                    System.out.println("DEBUG IMPORT: Row " + i + " - Matricule is empty, skipping");
                    continue;
                }

                matricule = sanitizeMatricule(matricule);
//                System.out.println("DEBUG IMPORT: Row " + i + " - Sanitized matricule: " + matricule);

                // --- CORRECTION : vérifier le doublon avant la DB ---
                if (matriculesTraites.contains(matricule)) {
//                    System.out.println("DEBUG IMPORT: Row " + i + " - Doublon détecté en mémoire pour " + matricule + ", ignoré");
                    erreurs.add("Doublon ignore pour le matricule " + matricule + " (ligne " + i + ")");
                    continue;
                }

                String idPersonnel = getIdPersonnel(matricule, c);
                if (idPersonnel == null) {
                    erreurs.add("La personne avec la matricule " + matricule + " est introuvable");
//                    System.out.println("DEBUG IMPORT: Personnel not found for matricule " + matricule);
                    continue;
                }
//                System.out.println("DEBUG IMPORT: Found idPersonnel=" + idPersonnel + " for matricule=" + matricule);

                String idPoste  = safe(getCell(row, index, "POSTE"));
                String idClasse = safe(getCell(row, index, "CLASS"));

                if (idClasse != null && !idClasse.contains("-")) {
                    String originalClasse = idClasse;
                    idClasse = idClasse + "-mini";
//                    System.out.println("DEBUG: Transformed class '" + originalClasse + "' to '" + idClasse + "' for matricule " + matricule);
                }

                double hNormal = 0;
                String rawHN = null;

                for (String col : HEURE_NORMAL_ALIASES) {
                    if (index.get(col) == null) continue;

                    String val = getValue(row, index, col);
                    if (val != null && !val.trim().isEmpty()) {
                        rawHN = val;
                        hNormal = parseDouble(val);
                        if (hNormal != 0) break;
                    }
                }


                // Only print if there is data to avoid spamming 0s
                if (hNormal > 0 || (rawHN != null && !rawHN.isEmpty())) {
//                    System.out.println("DEBUG: Row " + i + " Mat " + matricule + " -> Raw HN: [" + rawHN + "] Parsed: " + hNormal);
                }

                double hs       = parseDouble(getValue(row, index, "HEURE HS"));
                double ferie    = parseDouble(getValue(row, index, "HEURE FERIE"));
                double dim      = parseDouble(getValue(row, index, "HEURE DIM"));
                double nuit     = parseDouble(getValue(row, index, "MN"));
                double iff      = parseDouble(getValue(row, index, "IF"));
                double taux     = parseDouble(getValue(row, index, "TAUX"));

                if (hNormal == 0 &&
                        hs == 0 &&
                        ferie == 0 &&
                        dim == 0 &&
                        nuit == 0 &&
                        iff == 0) {
                    erreurs.add("Ligne ignoree " + i +
                            " (aucune donnee d'heures) pour le matricule " + matricule);
//                    System.out.println("DEBUG: Ligne ignorée " + i +
//                            " (aucune donnée d’heures) pour le matricule " + matricule);
                    continue;
                }
                matriculesTraites.add(matricule);


                ChargePersonnelTemp charge = new ChargePersonnelTemp();
                charge.setIdPersonnel(idPersonnel);
                charge.setIdPoste(idPoste);
                charge.setIdClasse(idClasse);
                charge.setHeureNormal(hNormal);
                charge.setHs(hs);
                charge.setFerie(ferie);
                charge.setDim(dim);
                charge.setNuit(nuit);
                charge.setIff(iff);
                charge.setTaux(taux);
                charge.setIdFab(this.getIdFab());
                charge.setDaty(daty);

                // Logic for Classe and Poste mapping (unchanged)
                QualificationPaie qualificationPaie = new QualificationPaie();
                qualificationPaie.setVal(idClasse);
                QualificationPaie[] qualifications = (QualificationPaie[]) CGenUtil.rechercher(qualificationPaie,null,null,c,"");
                charge.setIdClasse(qualifications.length > 0 ? qualifications[0].getId() : null);

                Poste poste = new Poste();
                poste.setVal(idPoste);
                Poste[] postes = (Poste[])CGenUtil.rechercher(poste,null,null,c,"");
                charge.setIdPoste(postes.length > 0 ? postes[0].getId() : null);

                // Insert into Temp Table
//                System.out.println("DEBUG IMPORT: About to create object for matricule " + matricule);
                charge.createObject(u,c);
//                System.out.println("DEBUG IMPORT: Successfully created CHARGEPERSONNELTEMP for matricule " + matricule);

                // Insert into Real Production Tables
//                System.out.println("DEBUG IMPORT: About to create RessourceParFab for matricule " + matricule);
                charge.createRessourceParFab(u,c);
//                System.out.println("DEBUG IMPORT: Successfully created RessourceParFab for matricule " + matricule);
            }
        } catch (Exception e){
            if (canClose) c.rollback();
            if (deleteRessourceParFab != null) deleteRessourceParFab.close();
            if (deleteRessourceHasFab != null) deleteRessourceHasFab.close();
            throw e;
        } finally{
            if (canClose) c.close();
            if (deleteRessourceParFab != null) deleteRessourceParFab.close();
            if (deleteRessourceHasFab != null) deleteRessourceHasFab.close();
        }
        return erreurs;
    }

    private String getIdPersonnel(String matricule, Connection con) throws Exception {
        PaieInfoPersonnel crit = new PaieInfoPersonnel();
        crit.setMatricule(matricule);
        PaieInfoPersonnel[] res = (PaieInfoPersonnel[]) CGenUtil.rechercher(
                crit, null, null, con, "AND matricule='"+matricule+"'"
        );
        if (res == null || res.length == 0) return null;
        return res[0].getId();
    }

    private String safe(Cell c) {
        if (c == null) return null;
        int type = c.getCellType();
        if (type == Cell.CELL_TYPE_STRING) return c.getStringCellValue().trim();
        if (type == Cell.CELL_TYPE_NUMERIC) {
            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(c)) {  // ← ICI
                java.util.Date date = c.getDateCellValue();
                return new java.text.SimpleDateFormat("dd/MM/yyyy").format(date);
            }
            double n = c.getNumericCellValue();
            if (n == (long) n) return String.valueOf((long) n);
            return String.valueOf(n);
        }
        if (type == Cell.CELL_TYPE_FORMULA) {
            try { return c.getStringCellValue().trim(); }
            catch (Exception e) { return String.valueOf(c.getNumericCellValue()); }
        }
        if (type == Cell.CELL_TYPE_BOOLEAN) return String.valueOf(c.getBooleanCellValue());
        return null;
    }

    private Cell getCell(Row r, Map<String,Integer> col, String key) {
        Integer idx = col.get(key);
        if (idx == null) return null;
        return r.getCell(idx);
    }

    private String getValue(Row r, Map<String,Integer> col, String key) {
        return safe(getCell(r, col, key));
    }

    private String normalizeMatricule(String m) {
        if (m == null) return null;
        // No need to normalize here - the servlet already cleaned it
        // Just trim and return
        return m.trim();
    }

    private double parseDouble(String s) {
        if (s == null) return 0d;
        s = s.trim();
        if (s.isEmpty()) return 0d;
        s = s.replace("\u00A0", ""); // remove non-breaking space
        try {
            return Double.parseDouble(s);
        } catch (Exception ignore) {}

        // Handle commas/dots
        String digitsOnly = s.replaceAll("[^0-9\\.,\\-]", "");
        int lastDot = digitsOnly.lastIndexOf('.');
        int lastComma = digitsOnly.lastIndexOf(',');
        int sep = Math.max(lastDot, lastComma);

        if (sep == -1) {
            if (digitsOnly.isEmpty() || "-".equals(digitsOnly)) return 0d;
            try { return Double.parseDouble(digitsOnly); } catch(Exception e) { return 0d; }
        }

        String intPart = digitsOnly.substring(0, sep).replaceAll("[^0-9\\-]", "");
        String fracPart = digitsOnly.substring(sep + 1).replaceAll("[^0-9]", "");
        if (intPart.isEmpty() || "-".equals(intPart)) intPart = "0";
        if (fracPart.isEmpty()) return Double.parseDouble(intPart);
        try { return Double.parseDouble(intPart + "." + fracPart); } catch(Exception e) { return 0d; }
    }
}