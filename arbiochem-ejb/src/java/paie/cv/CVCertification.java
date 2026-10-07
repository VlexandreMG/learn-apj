package paie.cv;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class CVCertification extends ClassMAPTable {
    private String id;
    private String idcv;
    private String nomcertification;
    private String organisme;
    private Date dateobtention;

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

    public String getNomcertification() {
        return nomcertification;
    }

    public void setNomcertification(String nomcertification) {
        this.nomcertification = nomcertification;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public Date getDateobtention() {
        return dateobtention;
    }

    public void setDateobtention(Date dateobtention) {
        this.dateobtention = dateobtention;
    }



    public CVCertification() throws Exception {
        this.setNomTable("CV_CERTIFICATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CVCR","getSeqCvCertification");
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

