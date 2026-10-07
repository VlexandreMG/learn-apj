package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationStagiaireAnnee extends ClassMAPTable {
    private String annee;
    private int nombreStagiaire;
    private String mois;

    public FormationStagiaireAnnee() throws Exception {
        this.setNomTable("v_formation_stagiaire_par_an");
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public int getNombreStagiaire() {
        return nombreStagiaire;
    }

    public void setNombreStagiaire(int nombreStagiaire) {
        this.nombreStagiaire = nombreStagiaire;
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