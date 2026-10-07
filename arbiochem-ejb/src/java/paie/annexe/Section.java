package paie.annexe;

import bean.TypeObjet;

import java.sql.Connection;

public class Section extends TypeObjet {

    public Section() throws Exception {
        this.setNomTable("SECTION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SECT","GETSEQ_SECTION");
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

