package paie.cv;

import bean.ClassFille;

import java.sql.Connection;

public class CVExperience extends ClassFille {
    private String id;
    private String idcv;
    private String entreprise;
    private String posteoccupe;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcv() {
        return idcv;
    }

    public void setIdcv(String idcv) {
        this.idcv = idcv;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getPosteoccupe() {
        return posteoccupe;
    }

    public void setPosteoccupe(String posteoccupe) {
        this.posteoccupe = posteoccupe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.cv.CV";
    }

    @Override
    public String getLiaisonMere() {
        return "idcv";
    }

    public CVExperience() throws Exception {
        this.setNomTable("CV_EXPERIENCE");
        this.setNomClasseMere("paie.cv.CV");
        this.setLiaisonMere("idcv");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CVE","getSeqCvExperience");
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

