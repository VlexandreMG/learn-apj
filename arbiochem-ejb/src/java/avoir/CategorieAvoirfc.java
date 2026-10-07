package avoir;

import bean.TypeObjet;
import java.sql.Connection;

public class CategorieAvoirfc extends TypeObjet {



    public CategorieAvoirfc() throws Exception {
        this.setNomTable("CATEGORIEAVOIRFC");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CAT","GETseqcategorieavoirfc");
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

