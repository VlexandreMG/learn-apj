package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationBilanGlobal extends ClassMAPTable {
    private String statut;
    private int nombre;

    public FormationBilanGlobal() throws Exception {
        this.setNomTable("v_formation_bilan_global");
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getTuppleID() {
        return statut;
    }

    @Override
    public String getAttributIDName() {
        return "statut";
    }
}