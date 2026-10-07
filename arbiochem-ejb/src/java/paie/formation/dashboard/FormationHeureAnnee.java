package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationHeureAnnee extends ClassMAPTable {
    private String annee;
    private String mois;
    private double totalHeureFormation;

    public FormationHeureAnnee() throws Exception {
        this.setNomTable("v_formation_heure_par_annee");
    }

    public String getAnnee() { return annee; }
    public void setAnnee(String annee) { this.annee = annee; }

    public double getTotalHeureFormation() { return totalHeureFormation; }
    public void setTotalHeureFormation(double totalHeureFormation) { this.totalHeureFormation = totalHeureFormation; }

    @Override public String getTuppleID() { return annee; }
    @Override public String getAttributIDName() { return "annee"; }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

}