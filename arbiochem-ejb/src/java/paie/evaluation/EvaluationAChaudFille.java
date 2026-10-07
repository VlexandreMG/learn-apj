package paie.evaluation;

import bean.CGenUtil;
import bean.ClassFille;

public class EvaluationAChaudFille extends ClassFille {
    private String id,idEvaluationChaud,idEvaluationCritere;
    private int note;
    public EvaluationAChaudFille()throws Exception {
        super.setNomTable("EVALUATION_A_CHAUD_FILLE");
        setLiaisonMere("idEvaluationChaud");
        setNomClasseMere("paie.evaluation.EvaluationAChaud");
    }
    @Override
    public String getAttributIDName() {
        return "id";
    }
    @Override
    public String getTuppleID() {
        return id;
    }
    @Override
    public void construirePK(java.sql.Connection c) throws Exception {
        this.preparePk("EACF", "getseq_evaluation_a_chaud_f");
        this.setId(makePK(c));
    }
    @Override
    public String getNomClasseMere()
    {
        return "paie.evaluation.EvaluationAChaud";
    }
    @Override
    public String getLiaisonMere() {
        return "idEvaluationChaud";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEvaluationChaud() {
        return idEvaluationChaud;
    }

    public void setIdEvaluationChaud(String idEvaluationChaud) {
        this.idEvaluationChaud = idEvaluationChaud;
    }

    public String getIdEvaluationCritere() {
        return idEvaluationCritere;
    }

    public void setIdEvaluationCritere(String idEvaluationCritere) {
        this.idEvaluationCritere = idEvaluationCritere;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public EvaluationAChaudFille[] getEvaluationCritere(){
        try {
            CritereEvalAChaud[] critereEvalAChauds = (CritereEvalAChaud[]) CGenUtil.rechercher(new CritereEvalAChaud(), null, null, " ");
            if (critereEvalAChauds.length > 0){
                EvaluationAChaudFille[] evaluationAChaudFilles = new EvaluationAChaudFille[critereEvalAChauds.length];
                for (int i = 0; i < critereEvalAChauds.length; i++) {
                    evaluationAChaudFilles[i] = new EvaluationAChaudFille();
                    evaluationAChaudFilles[i].setIdEvaluationCritere(critereEvalAChauds[i].getId());
                    evaluationAChaudFilles[i].setIdEvaluationChaud(this.getIdEvaluationChaud());
                }
                return evaluationAChaudFilles;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
