package paie.cv;

import bean.ClassMAPTable;

import java.sql.Connection;

public class CVLangue extends ClassMAPTable {
    private String id;
    private String idcv;
    private String langue;
    private int niveaulecture;
    private int niveauecrit;
    private int niveauoral;

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

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public int getNiveaulecture() {
        return niveaulecture;
    }

    public void setNiveaulecture(int niveaulecture) {
        this.niveaulecture = niveaulecture;
    }

    public int getNiveauecrit() {
        return niveauecrit;
    }

    public void setNiveauecrit(int niveauecrit) {
        this.niveauecrit = niveauecrit;
    }

    public int getNiveauoral() {
        return niveauoral;
    }

    public void setNiveauoral(int niveauoral) {
        this.niveauoral = niveauoral;
    }



    public CVLangue() throws Exception {
        this.setNomTable("CV_LANGUE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CVL","getSeqCvLangue");
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

