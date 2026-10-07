package paie.formation;

import bean.TypeObjet;

import java.sql.Connection;

public class TypeFormation extends TypeObjet {
    public TypeFormation() throws Exception {
        this.setNomTable("TYPE_FORMATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TFR","GET_SEQ_TYPE_FORMATION");
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
    @Override
    public String[] getMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }
}

