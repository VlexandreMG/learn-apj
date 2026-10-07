package ferme.configuration;

import bean.TypeObjet;
import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class Eclosoir extends TypeObjet {
    private double capacite;

    public Eclosoir() {
        super.setNomTable("ECLOSOIR");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        super.setNomTable("ECLOSOIR");
        this.preparePk("ECL", "GETSEQECLOSOIR");
        this.setId(this.makePK(c));
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }
}