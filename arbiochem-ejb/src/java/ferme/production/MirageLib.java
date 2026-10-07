package ferme.production;

public class MirageLib extends Mirage{
    String idMachineLib;

    public MirageLib() throws Exception {
        this.setNomTable("MIRAGE_LIB");
    }

    public String getIdMachineLib() {
        return idMachineLib;
    }

    public void setIdMachineLib(String idMachineLib) {
        this.idMachineLib = idMachineLib;
    }
}
