package maintenance.tempOuverture;

public class TempsOuvertureLib extends TempsOuverture{

    public String idMachinelib;
    public String idUnitelib;

    public String getIdMachinelib() {
        return idMachinelib;
    }

    public void setIdMachinelib(String idMachinelib) {
        this.idMachinelib = idMachinelib;
    }

    public String getIdUnitelib() {
        return idUnitelib;
    }

    public void setIdUnitelib(String idUnitelib) {
        this.idUnitelib = idUnitelib;
    }

    public TempsOuvertureLib() throws Exception {
        this.setNomTable("tempsouverturelib");
    }
}
