package paie.competence;

import bean.ClassMAPTable;

import java.sql.Connection;

public class CompetenceVal extends ClassMAPTable {
    private String id;
    private int val;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public CompetenceVal() throws Exception {
        this.setNomTable("COMPETENCEVAL");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("","");
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

