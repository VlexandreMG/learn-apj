package paie.formation.dashboard;

import bean.ClassMAPTable;

public class MoyenneHeureStagiaire extends ClassMAPTable {
    private String annee;
    private double moyenneHeureParStagiaire;
    private String mois;

    public MoyenneHeureStagiaire() throws Exception {
        this.setNomTable("v_format_moyen_h_stagiaire");
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public double getMoyenneHeureParStagiaire() {
        return moyenneHeureParStagiaire;
    }

    public void setMoyenneHeureParStagiaire(double moyenneHeureParStagiaire) {
        this.moyenneHeureParStagiaire = moyenneHeureParStagiaire;
    }

    @Override
    public String getTuppleID() {
        return annee;
    }

    @Override
    public String getAttributIDName() {
        return "annee";
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }
    
}