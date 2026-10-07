package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationParCategorie extends ClassMAPTable {
    private String categorieFormation;
    private int nombreFormation;

    public FormationParCategorie() throws Exception {
        this.setNomTable("v_formation_par_categorie");
    }

    public String getCategorieFormation() {
        return categorieFormation;
    }

    public void setCategorieFormation(String categorieFormation) {
        this.categorieFormation = categorieFormation;
    }

    public int getNombreFormation() {
        return nombreFormation;
    }

    public void setNombreFormation(int nombreFormation) {
        this.nombreFormation = nombreFormation;
    }

    @Override
    public String getTuppleID() {
        return categorieFormation;
    }

    @Override
    public String getAttributIDName() {
        return "categorieFormation";
    }
}