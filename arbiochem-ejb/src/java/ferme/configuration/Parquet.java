package ferme.configuration;

import bean.TypeObjet;
import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class Parquet extends TypeObjet {
    public Parquet() {
        super.setNomTable("PARQUET");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        super.setNomTable("PARQUET");
        this.preparePk("PRQ", "GETSEQPARQUET");
        this.setId(this.makePK(c));
    }
}