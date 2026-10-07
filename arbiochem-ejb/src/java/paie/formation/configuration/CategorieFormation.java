package paie.formation.configuration;

import bean.ClassMAPTable;

import java.sql.Connection;

public class CategorieFormation extends ClassMAPTable {
    private String id;
    private String val;
    private String desce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }



    public CategorieFormation() throws Exception {
        this.setNomTable("CATEGORIE_FORMATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CTF","GET_SEQ_CATEGORIE_FORMATION");
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
    @Override
    public String[] getMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }
}

