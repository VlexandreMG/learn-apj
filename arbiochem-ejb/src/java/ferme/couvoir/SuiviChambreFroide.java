package ferme.couvoir;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class SuiviChambreFroide extends ClassMere {
    private String id;
    private Date daty;
    private String idsallestockageoeuf;

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

    public String getIdsallestockageoeuf() {
        return idsallestockageoeuf;
    }

    public void setIdsallestockageoeuf(String idsallestockageoeuf) {
        this.idsallestockageoeuf = idsallestockageoeuf;
    }



    public SuiviChambreFroide() throws Exception {
        this.setNomTable("SUIVICHAMBREFROIDE");
        this.setNomClasseFille("ferme.couvoir.SuiviChambreFroideDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SCF","getseqsuiviChambreFroide");
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

