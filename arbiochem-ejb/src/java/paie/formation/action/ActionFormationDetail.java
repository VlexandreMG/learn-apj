package paie.formation.action;

import bean.ClassFille;

import java.sql.Connection;

public class ActionFormationDetail extends ClassFille {
    private String id;
    private String idactionformation;
    private String idtypecout;
    private double cout;
    private String iddevise;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdactionformation() {
        return idactionformation;
    }

    public void setIdactionformation(String idactionformation) {
        this.idactionformation = idactionformation;
    }

    public String getIdtypecout() {
        return idtypecout;
    }

    public void setIdtypecout(String idtypecout) {
        this.idtypecout = idtypecout;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public String getIddevise() {
        return iddevise;
    }

    public void setIddevise(String iddevise) {
        this.iddevise = iddevise;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.formation.action.ActionFormation";
    }

    @Override
    public String getLiaisonMere() {
        return "idactionformation";
    }

    public ActionFormationDetail() throws Exception {
        this.setNomTable("ACTION_FORMATION_DETAIL");
        this.setNomClasseMere("paie.formation.actionActionFormation");
        this.setLiaisonMere("idactionformation");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ACTD","GET_SEQ_ACTION_FORM_DET");
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

