package paie.pointage;

import bean.ClassMAPTable;
import java.sql.Connection;
import java.sql.Date;

public class Calendrier extends ClassMAPTable {
    private String id;
    private Date daty;
    private String evenement;

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

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }



    public Calendrier() throws Exception {
        this.setNomTable("CALENDRIER");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CDR","getseqcalendrier");
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

