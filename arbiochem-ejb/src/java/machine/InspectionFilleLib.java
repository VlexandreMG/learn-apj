package machine;

public class InspectionFilleLib extends InspectionFille{
    private String idElementLib;
    private String idEtatInspectionLib;
    private String idMachine;
    private String idMachineLib;
    private String idLigne;
    private String idLigneLib;

    public InspectionFilleLib() throws Exception {
        this.setNomTable("InspectionFilleLib");
    }

    public String getIdElementLib() {
        return idElementLib;
    }

    public void setIdElementLib(String idElementLib) {
        this.idElementLib = idElementLib;
    }

    public String getIdEtatInspectionLib() {
        return idEtatInspectionLib;
    }

    public void setIdEtatInspectionLib(String idEtatInspectionLib) {
        this.idEtatInspectionLib = idEtatInspectionLib;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public String getIdMachineLib() {
        return idMachineLib;
    }

    public void setIdMachineLib(String idMachineLib) {
        this.idMachineLib = idMachineLib;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    public String getIdLigneLib() {
        return idLigneLib;
    }

    public void setIdLigneLib(String idLigneLib) {
        this.idLigneLib = idLigneLib;
    }
}
