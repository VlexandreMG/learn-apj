package compteur;

public class CompteurElectriciteRecentCpl extends CompteurElectriciteCpl{

    int rn;
    String lignelib;
    String machinelib;

    public int getRn() {
        return rn;
    }

    public String getMachinelib() {
        return machinelib;
    }

    public void setMachinelib(String machinelib) {
        this.machinelib = machinelib;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getLignelib() {
        return lignelib;
    }

    public void setLignelib(String lignelib) {
        this.lignelib = lignelib;
    }

    public CompteurElectriciteRecentCpl() throws Exception {
        this.setNomTable("COMPTEURCPLRECENTELECTRICITE");
    }


}
