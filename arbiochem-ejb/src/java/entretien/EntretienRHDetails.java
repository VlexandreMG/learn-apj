package entretien;

import bean.ClassFille;

import java.sql.Connection;

public class EntretienRHDetails extends ClassFille {
    private String id;
    private String identRetientRh;
    private String idCategorieEvaluation;
    private String critere;
    private int note;
    private String observation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentRetientRh() {
        return identRetientRh;
    }

    public void setIdentRetientRh(String identRetientRh) {
        this.identRetientRh = identRetientRh;
    }

    public String getIdCategorieEvaluation() {
        return idCategorieEvaluation;
    }

    public void setIdCategorieEvaluation(String idCategorieEvaluation) {
        this.idCategorieEvaluation = idCategorieEvaluation;
    }

    public String getCritere() {
        return critere;
    }

    public void setCritere(String critere) {
        this.critere = critere;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }


    @Override
    public String getNomClasseMere() {
        return "entretien.EntretienRH";
    }

    @Override
    public String getLiaisonMere() {
        return "idEntretientRH";
    }

    public EntretienRHDetails() throws Exception {
        this.setNomTable("ENTRETIENRH_DETAIL");
        this.setNomClasseMere("entretien.EntretienRH");
        this.setLiaisonMere("identRetientRh");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ERHD","getseq_EntretienRH_Detail");
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

