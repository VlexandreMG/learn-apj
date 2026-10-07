package paie.sanction.configuration;

import bean.TypeObjet;

import java.sql.Connection;

public class TypeAccident extends TypeObjet {

    public TypeAccident() throws Exception {
        this.setNomTable("TYPE_ACCIDENT");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TPA","getseq_typeAccident");
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
