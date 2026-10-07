package paie.categorie;

public class SalaireCategorieLib extends SalaireCategorie{

    public SalaireCategorieLib() throws Exception
    {
        this.setNomTable("CATEGORIE_QUALIFICATION_VW");
    }

    private String categorieLib;
    private String qualificationLib;
    private String etatLib;

    public String getCategorieLib() {
        return categorieLib;
    }

    public void setCategorieLib(String categorieLib) {
        this.categorieLib = categorieLib;
    }

    public String getQualificationLib() {
        return qualificationLib;
    }

    public void setQualificationLib(String qualificationLib) {
        this.qualificationLib = qualificationLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
