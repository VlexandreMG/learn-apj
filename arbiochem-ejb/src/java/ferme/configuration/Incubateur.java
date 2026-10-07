package ferme.configuration;

import bean.TypeObjet;
import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class Incubateur extends TypeObjet {
    private double capacite;

    public Incubateur() {
        super.setNomTable("INCUBATEUR");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        super.setNomTable("INCUBATEUR");
        this.preparePk("INC", "GETSEQINCUBATEUR");
        this.setId(this.makePK(c));
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }
}