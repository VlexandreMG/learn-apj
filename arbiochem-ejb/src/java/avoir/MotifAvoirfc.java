package avoir;

import bean.TypeObjet;
import java.sql.Connection;

public class MotifAvoirfc extends TypeObjet {



    public MotifAvoirfc() throws Exception {
        this.setNomTable("MOTIFAVOIRFC");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MTAV","GETseqmotifavoirfc");
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

