package paie.evaluation;

public class EvaluationAChaudFilleLib extends EvaluationAChaudFille{
    private String idEvaluationCritereLib;
    public EvaluationAChaudFilleLib()throws Exception{
        super.setNomTable("EVALUATION_A_CHAUD_FILLE_LIB");
    }
    public String getIdEvaluationCritereLib() {
        return idEvaluationCritereLib;
    }

    public void setIdEvaluationCritereLib(String idEvaluationCritereLib) {
        this.idEvaluationCritereLib = idEvaluationCritereLib;
    }
}
