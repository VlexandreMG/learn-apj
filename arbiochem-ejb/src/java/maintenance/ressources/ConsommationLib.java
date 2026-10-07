package maintenance.ressources;

public class ConsommationLib extends Consommation{
    private String idTypeMaintenanceLib;
    private String idMachineLib;
    private String etatLib;

    public ConsommationLib() throws Exception {
        this.setNomTable("CONSOMMATIONLIB");
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdTypeMaintenanceLib() {
        return idTypeMaintenanceLib;
    }

    public void setIdTypeMaintenanceLib(String idTypeMaintenanceLib) {
        this.idTypeMaintenanceLib = idTypeMaintenanceLib;
    }

    public String getIdMachineLib() {
        return idMachineLib;
    }

    public void setIdMachineLib(String idMachineLib) {
        this.idMachineLib = idMachineLib;
    }
}
