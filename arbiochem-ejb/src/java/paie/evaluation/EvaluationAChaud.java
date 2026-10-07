package paie.evaluation;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class EvaluationAChaud extends ClassMere {
    private String id,idFormationSuivie,idPersonnel,idIntervenant,Remarque;
    private Date dateFormation,daty;
    public EvaluationAChaud()throws Exception{
        super.setNomTable("EVALUATION_A_CHAUD");
        setLiaisonFille("idEvaluationChaud");
        setNomClasseFille("paie.evaluation.EvaluationAChaudFille");
    }
    public  String getNomClasseFille()
    {
        return "paie.evaluation.EvaluationAChaudFille";
    }
    public String getLiaisonFille() {
        return "idEvaluationChaud";
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EAC","getseq_evaluation_a_chaud");
        this.setId(makePK(c));
    }
    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFormationSuivie() {
        return idFormationSuivie;
    }

    public void setIdFormationSuivie(String idFormationSuivie) {
        this.idFormationSuivie = idFormationSuivie;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getIdIntervenant() {
        return idIntervenant;
    }

    public void setIdIntervenant(String idIntervenant) {
        this.idIntervenant = idIntervenant;
    }

    public String getRemarque() {
        return Remarque;
    }

    public void setRemarque(String remarque) {
        Remarque = remarque;
    }

    public Date getDateFormation() {
        return dateFormation;
    }

    public void setDateFormation(Date dateFormation) {
        this.dateFormation = dateFormation;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
}
