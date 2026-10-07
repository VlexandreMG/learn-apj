package maintenance.ressources;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class Consommation extends ClassMere {
    private String id;
    private Date daty;
    private String idTypeMaintenance;
    private String idMachine;
    private String desce;

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

    public String getIdTypeMaintenance() {
        return idTypeMaintenance;
    }

    public void setIdTypeMaintenance(String idTypeMaintenance) {
        this.idTypeMaintenance = idTypeMaintenance;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }



    public Consommation() throws Exception {
        this.setNomTable("CONSOMMATION");
        this.setNomClasseFille("maintenance.ressources.ConsommationDetails");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CONS","GETSEQCONSOMMATION");
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

