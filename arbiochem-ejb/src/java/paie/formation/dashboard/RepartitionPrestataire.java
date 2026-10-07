package paie.formation.dashboard;

import bean.ClassMAPTable;

public class RepartitionPrestataire extends ClassMAPTable {
    private String typePrestataire;
    private int nombreFormation;

    public RepartitionPrestataire() throws Exception { this.setNomTable("v_repartition_prestataire"); }
    public String getTypePrestataire() { return typePrestataire; }
    public void setTypePrestataire(String typePrestataire) { this.typePrestataire = typePrestataire; }
    public int getNombreFormation() { return nombreFormation; }
    public void setNombreFormation(int nombreFormation) { this.nombreFormation = nombreFormation; }
    @Override public String getTuppleID() { return typePrestataire; }
    @Override public String getAttributIDName() { return "typePrestataire"; }
}