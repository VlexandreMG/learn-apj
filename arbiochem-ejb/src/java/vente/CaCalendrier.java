package vente;

import bean.CGenUtil;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.Locale;

public class CaCalendrier {
    String[] listeMois;
    HashMap<String, Map<String, List<CaJourDetailPourCalendrier>>> caMensuels;
    String[] listeDate;
    HashMap<String, Vector> reservations;
    int [] jours;
    HashMap<String,Double[]> total = new HashMap<>();
    public CaCalendrier(String dtMin,String dtMax) throws Exception {
        Connection c=null;
        try {
            c = new UtilDB().GetConn();
            this.jours = new int[31];
            for (int i = 0; i < 31; i++) {
                jours[i] = i+1;
            }
            this.setListeDate(dtMin,dtMax);
            this.setReservations(dtMin,dtMax,c);
            this.setCaMensuels();
            this.setTotal();
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if(c!=null)c.close();
        }
    }

    public int[] getJours() {
        return jours;
    }

    public void setJours(int[] jours) {
        this.jours = jours;
    }

    public String[] getListeDate() {
        return listeDate;
    }

    public void setListeDate(String dtMin,String dtMax) {
//        if(Utilitaire.diffJourDaty(dtMax,dtMin)<0)throw new Exception("Date sup inferieur a date Inf");
        int day = Utilitaire.diffJourDaty(dtMax, dtMin);
        String liste[]=new String[day];
        for (int i = 0; i < day; i++) {
            liste[i]=Utilitaire.formatterDaty(Utilitaire.ajoutJourDate(dtMin,i))  ;
        }
        this.listeDate = liste;
    }

    public HashMap<String, Vector> getReservations() {
        return reservations;
    }

    public void setReservations(String dMin,String dMax,Connection c) throws Exception {
        CaJourDetailPourCalendrier res = new CaJourDetailPourCalendrier();
        if(dMin==null||dMin.compareToIgnoreCase("")==0)dMin=Utilitaire.formatterDaty(Utilitaire.getDebutSemaine(Utilitaire.dateDuJourSql())) ;
        String[] colInt={"daty"};
        String[] valInt={dMin,dMax};
        this.reservations= CGenUtil.rechercher2D(res,colInt,valInt,"daty",c,"");
    }

    public HashMap<String, Map<String, List<CaJourDetailPourCalendrier>>> getCaMensuels() {
        return caMensuels;
    }

    public void setCaMensuels(HashMap<String, Map<String, List<CaJourDetailPourCalendrier>>> caMensuels) {
        this.caMensuels = caMensuels;
    }

    public void setCaMensuels() {
        this.caMensuels = fusionParMois(this.getReservations());
        // Générer la liste complète de tous les mois entre min et max, même sans données
        this.listeMois = genererTousLesMois();
        
        // Initialiser les mois vides pour éviter les NullPointerException
        for (String mois : listeMois) {
            if (!caMensuels.containsKey(mois)) {
                caMensuels.put(mois, new HashMap<>());
            }
        }
    }

    private String[] genererTousLesMois() {
        if (listeDate == null || listeDate.length == 0) {
            return new String[]{};
        }
        
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
        LinkedHashMap<String, Boolean> moisMap = new LinkedHashMap<>();
        
        // Extraire la première et dernière date pour parcourir tous les mois
        LocalDate debut = LocalDate.parse(listeDate[0], inputFormat);
        LocalDate fin = LocalDate.parse(listeDate[listeDate.length - 1], inputFormat);
        
        // Parcourir chaque jour et ajouter le mois correspondant
        LocalDate courant = debut;
        while (!courant.isAfter(fin)) {
            String cleMois = courant.getMonth().getDisplayName(TextStyle.FULL, Locale.FRANCE)
                    + " " + courant.getYear();
            moisMap.putIfAbsent(cleMois, true);
            courant = courant.plusMonths(1).withDayOfMonth(1);
        }
        
        return moisMap.keySet().toArray(new String[]{});
    }

    public HashMap<String, Map<String,List<CaJourDetailPourCalendrier>>> fusionParMois(HashMap<String, Vector> caJour) {
        HashMap<String, Map<String,List<CaJourDetailPourCalendrier>>> resultat = new LinkedHashMap<>();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
        for (String dateStr:listeDate) {

            Vector valeursJour = caJour.get(dateStr);
            if (valeursJour!=null){
                LocalDate date = LocalDate.parse(dateStr, inputFormat);

                String cleMois = date.getMonth().getDisplayName(TextStyle.FULL, Locale.FRANCE)
                        + " " + date.getYear();
                String cleJour = String.valueOf(date.getDayOfMonth());
                resultat.putIfAbsent(cleMois, new HashMap<>());
                resultat.get(cleMois).putIfAbsent(cleJour, new ArrayList<>());
                resultat.get(cleMois).get(cleJour).addAll(valeursJour);
            }
        }
        return resultat;
    }

    public void setTotal() {
        // total[0] - Montant Total
        // total[1] - Moyenne Total
        this.total = new HashMap<>();
        for (String dt : listeMois) {
            double montantTotal = 0;
            double moyenne = 0;
            int count = 0;
            Map<String, List<CaJourDetailPourCalendrier>> caJours = this.caMensuels.get(dt);
            for (Map.Entry<String, List<CaJourDetailPourCalendrier>> entry : caJours.entrySet()) {
                String cle = entry.getKey();
                List<CaJourDetailPourCalendrier> v = entry.getValue();
                if (v != null) {
                    for (Object o:v){
                        CaJourDetailPourCalendrier res = (CaJourDetailPourCalendrier) o;
                        montantTotal += res.getMontant();
                    }
                    count += v.size();
                }
            }
            moyenne += montantTotal / count;
            total.put(dt,new Double[]{montantTotal,moyenne});
        }
    }

    public HashMap<String, Double[]> getTotal() {
        return total;
    }

    public String[] getListeMois() {
        return listeMois;
    }

    public void setListeMois(String[] listeMois) {
        this.listeMois = listeMois;
    }

    public CaJourDetailPourCalendrier [] getReservationByTime(int jour, String mois) throws Exception {
        System.out.println(mois+"  "+jour);
        List<CaJourDetailPourCalendrier> res = this.caMensuels.get(mois).get(String.valueOf(jour));
        if (res == null) {
            return null;
        }
        System.out.println(res.size());
        return res.toArray(new CaJourDetailPourCalendrier[]{});
    }
}