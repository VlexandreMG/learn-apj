package paie.cv;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class CVFormation extends ClassMAPTable {
    private String id;
    private String idcv;
    private String etablissement;
    private String iddiplome;
    private String domaine;
    private Date datedebut;
    private Date datefin;

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

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getIddiplome() {
        return iddiplome;
    }

    public void setIddiplome(String iddiplome) {
        this.iddiplome = iddiplome;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }



    public CVFormation() throws Exception {
        this.setNomTable("CV_FORMATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CVF","getSeqCvFormation");
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

