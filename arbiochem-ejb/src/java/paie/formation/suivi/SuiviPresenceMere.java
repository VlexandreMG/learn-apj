package paie.formation.suivi;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class SuiviPresenceMere extends ClassMere {
    private String id;
    private String idsessionformation;
    private Date daty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdsessionformation() {
        return idsessionformation;
    }

    public void setIdsessionformation(String idsessionformation) {
        this.idsessionformation = idsessionformation;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }



    public SuiviPresenceMere() throws Exception {
        this.setNomTable("SUIVI_PRESENCE_MERE");
        this.setNomClasseFille("paie.formation.suivi.SuiviPresenceFille");
        this.setLiaisonFille("idsuivipresencemere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SPM","GET_SEQ_SUIVI_PRES_MERE");
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

