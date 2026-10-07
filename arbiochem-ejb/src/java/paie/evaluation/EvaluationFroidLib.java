package paie.evaluation;

public class EvaluationFroidLib extends EvaluationFroid{
    private String personnel,matricule;
    public EvaluationFroidLib()throws Exception{
        super.setNomTable("EVALUATION_FROID_LIB");
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
