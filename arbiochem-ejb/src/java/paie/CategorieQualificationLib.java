package paie;

public class CategorieQualificationLib extends CategorieQualification{
    String qualificationLib;
    String categorieLib;
    String nomPersonnel;
    String matricule;

    public String getQualificationLib() {
        return qualificationLib;
    }

    public void setQualificationLib(String qualificationLib) {
        this.qualificationLib = qualificationLib;
    }

    public String getCategorieLib() {
        return categorieLib;
    }

    public void setCategorieLib(String categorieLib) {
        this.categorieLib = categorieLib;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    @Override
    public String getMatricule() {
        return matricule;
    }

    @Override
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public CategorieQualificationLib() throws Exception{
        this.setNomTable("CATEGORIEQUALIFICATION_LIB");
    }
}
