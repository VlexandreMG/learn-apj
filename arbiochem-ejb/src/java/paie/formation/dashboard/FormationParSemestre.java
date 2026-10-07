package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationParSemestre extends ClassMAPTable {
    private String semestre;
    private int nombreFormation;

    public FormationParSemestre() throws Exception {
        this.setNomTable("v_formation_par_semestre");
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getNombreFormation() {
        return nombreFormation;
    }

    public void setNombreFormation(int nombreFormation) {
        this.nombreFormation = nombreFormation;
    }

    @Override
    public String getTuppleID() {
        return semestre;
    }

    @Override
    public String getAttributIDName() {
        return "semestre";
    }
}