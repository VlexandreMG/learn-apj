package ferme.configuration;

import bean.TypeObjet;
import java.sql.Connection;

public class OrigineLot extends TypeObjet {

    public OrigineLot() throws Exception {
        this.setNomTable("ORIGINELOT");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OGL","getseq_originelot");
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
        String[] motCles={"id","val", "desce"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"id","val", "desce"};
        return valMotCles;
    }
}

