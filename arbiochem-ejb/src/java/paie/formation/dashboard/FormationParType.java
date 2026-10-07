package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationParType extends ClassMAPTable {
    private String typeFormation;
    private int nombreFormation;

    public FormationParType() throws Exception {
        this.setNomTable("v_formation_par_type");
    }

    public String getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(String typeFormation) {
        this.typeFormation = typeFormation;
    }

    public int getNombreFormation() {
        return nombreFormation;
    }

    public void setNombreFormation(int nombreFormation) {
        this.nombreFormation = nombreFormation;
    }

    @Override
    public String getTuppleID() {
        return typeFormation;
    }

    @Override
    public String getAttributIDName() {
        return "typeFormation";
    }
}