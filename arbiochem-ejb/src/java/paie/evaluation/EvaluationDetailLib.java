package paie.evaluation;

public class EvaluationDetailLib extends EvaluationDetail {
    String idcriterelib, idTypeCompetenceFPLib;

    public EvaluationDetailLib() throws Exception{
        this.setNomTable("EVALUATION_DETAIL_LIB");
    }

    public String getIdcriterelib() {
        return idcriterelib;
    }

    public void setIdcriterelib(String idcriterelib) {
        this.idcriterelib = idcriterelib;
    }

    public String getIdTypeCompetenceFPLib() {
        return idTypeCompetenceFPLib;
    }

    public void setIdTypeCompetenceFPLib(String idTypeCompetenceFPLib) {
        this.idTypeCompetenceFPLib = idTypeCompetenceFPLib;
    }
}
