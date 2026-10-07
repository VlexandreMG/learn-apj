package maintenance.tempOuverture;

import bean.ClassMAPTable;
import java.sql.Connection;

public class TempsOuverture extends ClassMAPTable {
    private String id;
    private String idMachine;
    private int temps;
    private int capacite;
    private String idUnite;

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }



    public TempsOuverture() throws Exception {
        this.setNomTable("TEMPSOUVERTURE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TO","get_SEQ_TEMPS_OUVERTURE");
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

