package maintenance.ressources;

public class ConsommableMachineLib extends ConsommableMachine{
    private String idTypeMaintenanceLib;
    private String idMachineLib;
    private String idUniteLib;
    private String frequenceLib;
    private String idConsommableLib;

    public ConsommableMachineLib() throws Exception {
        this.setNomTable("CONSOMMABLEMACHINELIB");
    }

    public String getIdUniteLib() {
        return idUniteLib;
    }

    public void setIdUniteLib(String idUniteLib) {
        this.idUniteLib = idUniteLib;
    }

    public String getFrequenceLib() {
        return frequenceLib;
    }

    public String getIdConsommableLib() {
        return idConsommableLib;
    }

    public void setIdConsommableLib(String idConsommableLib) {
        this.idConsommableLib = idConsommableLib;
    }

    public void setFrequenceLib(String frequenceLib) {
        this.frequenceLib = frequenceLib;
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
