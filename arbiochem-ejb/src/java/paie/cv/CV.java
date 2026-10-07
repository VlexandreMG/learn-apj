package paie.cv;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class CV extends ClassMere {
    private String id;
    private String nomcandidat;
    private String prenomcandidat;
    private String titrecv;
    private String resumeprofil;
    private Date daty;
    private String idFichePoste;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomcandidat() {
        return nomcandidat;
    }

    public void setNomcandidat(String nomcandidat) {
        this.nomcandidat = nomcandidat;
    }

    public String getPrenomcandidat() {
        return prenomcandidat;
    }

    public void setPrenomcandidat(String prenomcandidat) {
        this.prenomcandidat = prenomcandidat;
    }

    public String getTitrecv() {
        return titrecv;
    }

    public void setTitrecv(String titrecv) {
        this.titrecv = titrecv;
    }

    public String getResumeprofil() {
        return resumeprofil;
    }

    public void setResumeprofil(String resumeprofil) {
        this.resumeprofil = resumeprofil;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdFichePoste() {
        return idFichePoste;
    }

    public void setIdFichePoste(String idFichePoste) {
        this.idFichePoste = idFichePoste;
    }

    public CV() throws Exception {
        this.setNomTable("CV");
        this.setNomClasseFille("paie.cv.CVExperience");
        this.setLiaisonFille("idcv");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CV","getSeqCv");
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

