package paie.formation;

import bean.ClassMere;

import java.sql.Connection;

public class FormationPlan extends ClassMere {
    private String id;
    private int annee;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public FormationPlan() throws Exception {
        this.setNomTable("FORMATION_PLAN");
        this.setNomClasseFille("paie.formation.Formation");
        this.setLiaisonFille("idplan");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FRP","getSeqFormationPlan");
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
        String[] motCles={"id","description"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"id","description"};
        return valMotCles;
    }
}

