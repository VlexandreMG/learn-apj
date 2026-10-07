package ferme.suiviJournalier;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class SuiviJournalier extends ClassMere {
    private String id;
    private Date daty;
    private String idferme;
    private String idlot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdferme() {
        return idferme;
    }

    public void setIdferme(String idferme) {
        this.idferme = idferme;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }



    public SuiviJournalier() throws Exception {
        this.setNomTable("SUIVIJOURNALIER");
        this.setNomClasseFille("ferme.suiviJournalier.SuiviJournalierDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SJ","getseq_suivijournalier");
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

