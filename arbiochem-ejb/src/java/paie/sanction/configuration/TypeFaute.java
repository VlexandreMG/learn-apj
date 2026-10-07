package paie.sanction.configuration;

import bean.TypeObjet;

import java.sql.Connection;

public class TypeFaute extends TypeObjet {

    public TypeFaute() throws Exception {
        this.setNomTable("TYPEFAUTE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TPF","getseq_typefaute");
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
