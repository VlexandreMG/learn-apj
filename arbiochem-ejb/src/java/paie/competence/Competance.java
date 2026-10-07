package paie.competence;

import bean.TypeObjet;

import java.sql.Connection;

public class Competance extends TypeObjet {
    public Competance() throws Exception {
        this.setNomTable("COMPETENCE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CPT","getSeqCompetence");
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
        String[] motCles={"id","val", "desce"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"id","val", "desce"};
        return valMotCles;
    }
}

