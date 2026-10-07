package ferme.configuration;

import bean.TypeObjet;
import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class QualitePoussin extends TypeObjet {
    public QualitePoussin() {
        super.setNomTable("QUALITEPOUSSIN");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        super.setNomTable("QUALITEPOUSSIN");
        this.preparePk("QP", "GETSEQQUALITEPOUSSIN");
        this.setId(this.makePK(c));
    }
}