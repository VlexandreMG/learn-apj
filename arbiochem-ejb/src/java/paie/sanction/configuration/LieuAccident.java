package paie.sanction.configuration;

import bean.TypeObjet;

import java.sql.Connection;

public class LieuAccident extends TypeObjet {
    public LieuAccident() throws Exception {
        this.setNomTable("LIEU_ACCIDENT_TRAVAIL");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("LA","getseq_lieuaccident");
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
