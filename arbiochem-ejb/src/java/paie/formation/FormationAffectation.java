package paie.formation;

import bean.ClassEtat;

import java.sql.Connection;

public class FormationAffectation extends ClassEtat {
    private String id;
    private String idformation;
    private String idpersonnel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdformation() {
        return idformation;
    }

    public void setIdformation(String idformation) {
        this.idformation = idformation;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }



    public FormationAffectation() throws Exception {
        this.setNomTable("FORMATION_AFFECTATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FRAFF","getSeqFormationAffectation");
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

