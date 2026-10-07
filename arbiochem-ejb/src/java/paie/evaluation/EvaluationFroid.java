package paie.evaluation;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class EvaluationFroid extends ClassMAPTable {
    private String id;
    private String idformationsuivi;
    private String idpersonnel;
    private double note;
    private String commentaire;
    private Date dateevaluation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdformationsuivi() {
        return idformationsuivi;
    }

    public void setIdformationsuivi(String idformationsuivi) {
        this.idformationsuivi = idformationsuivi;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note)throws Exception {
        if(this.getMode().equals("modif")) {
            if (note < 0 || note > 4) {
                throw new Exception("La note doit \\u00EAtre comprise entre 0 et 4.");
            }
        }
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateevaluation() {
        return dateevaluation;
    }

    public void setDateevaluation(Date dateevaluation) {
        this.dateevaluation = dateevaluation;
    }



    public EvaluationFroid() throws Exception {
        this.setNomTable("EVALUATION_FROID");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EVF","GET_SEQ_EVALUATION_FROID");
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
}

