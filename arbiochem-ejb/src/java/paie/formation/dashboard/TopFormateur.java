package paie.formation.dashboard;

import bean.ClassMAPTable;

public class TopFormateur extends ClassMAPTable {
    private String nomFormateur;
    private int nombreFormation;

    public TopFormateur() throws Exception { this.setNomTable("v_top_formateur"); }

    public String getNomFormateur() { return nomFormateur; }
    public void setNomFormateur(String nomFormateur) { this.nomFormateur = nomFormateur; }

    public int getNombreFormation() { return nombreFormation; }
    public void setNombreFormation(int nombreFormation) { this.nombreFormation = nombreFormation; }

    @Override public String getTuppleID() { return nomFormateur; }
    @Override public String getAttributIDName() { return "nomFormateur"; }
}