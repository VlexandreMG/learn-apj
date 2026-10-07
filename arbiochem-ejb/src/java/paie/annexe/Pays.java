package paie.annexe;

import bean.TypeObjet;

import java.sql.Connection;

public class Pays extends TypeObjet {



    public Pays() throws Exception {
        this.setNomTable("PAYS");
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

    @Override
    public String[] getMotCles() {
        String[] motCles={"id", "val","desce"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id", "val","desce"};
        return motCles;
    }
}

