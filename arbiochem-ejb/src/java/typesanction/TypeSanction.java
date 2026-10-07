package typesanction;

import bean.TypeObjet;
import java.sql.Connection;

public class TypeSanction extends TypeObjet {

    public TypeSanction() throws Exception {
        this.setNomTable("TYPESANCTION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TSANC","GETseq_TYPESANCTION");
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

