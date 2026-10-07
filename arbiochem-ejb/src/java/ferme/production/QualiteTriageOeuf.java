package ferme.production;

import bean.TypeObjet;
import java.sql.Connection;

public class QualiteTriageOeuf extends TypeObjet {



    public QualiteTriageOeuf() throws Exception {
        this.setNomTable("QUALITETRIAGEOEUF");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("QTO","GETSEQ_QUALITETRIAGEOEUF");
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

