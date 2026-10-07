package paie.onBoarding;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class ProgrammeIntegration extends ClassMere {
    private String id;
    private String idpersonnel;
    private Date daty;
    private String remarque;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }



    public ProgrammeIntegration() throws Exception {
        this.setNomTable("PROGRAMME_INTEGRATION");
        this.setNomClasseFille("paie.onBoarding.ProgrammeIntegrationDetail");
        this.setLiaisonFille("idprogramedetail");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PGI","GET_SEQ_PROG_INT");
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

