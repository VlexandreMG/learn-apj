package ferme.production;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class Mirage extends ClassMere {
    private String id;
    private Date daty;
    private double nombreoeufsinitial;
    private String idmachine;

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

    public double getNombreoeufsinitial() {
        return nombreoeufsinitial;
    }

    public void setNombreoeufsinitial(double nombreoeufsinitial) {
        this.nombreoeufsinitial = nombreoeufsinitial;
    }

    public String getIdmachine() {
        return idmachine;
    }

    public void setIdmachine(String idmachine) {
        this.idmachine = idmachine;
    }



    public Mirage() throws Exception {
        this.setNomTable("MIRAGE");
        this.setNomClasseFille("ferme.production.MirageDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MRG","getseq_mirage");
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

