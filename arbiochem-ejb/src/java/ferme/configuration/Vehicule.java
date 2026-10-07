package ferme.configuration;

import bean.TypeObjet;
import java.sql.Connection;

public class Vehicule extends TypeObjet {
    public Vehicule() throws Exception {
        this.setNomTable("VEHICULE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("VHC","getseq_vehicule");
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

