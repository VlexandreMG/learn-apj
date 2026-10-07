package ferme.configuration;

import bean.TypeObjet;
import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class Souche extends TypeObjet {
    public Souche() {
        super.setNomTable("souche");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        super.setNomTable("souche");
        this.preparePk("SOU", "GETSEQSOUCHE");
        this.setId(this.makePK(c));
    }
}