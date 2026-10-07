package maintenance.travaux;

public class ResultatMaintenanceLib extends ResultatMaintenance {
    private String idpersonnellib;
    private String etatmachinelib;

    public ResultatMaintenanceLib() throws Exception {
        this.setNomTable("ResultatMaintenanceLib");
    }

    public String getIdpersonnellib() {
        return idpersonnellib;
    }

    public void setIdpersonnellib(String idpersonnellib) {
        this.idpersonnellib = idpersonnellib;
    }

    public String getEtatmachinelib() {
        return etatmachinelib;
    }

    public void setEtatmachinelib(String etatmachinelib) {
        this.etatmachinelib = etatmachinelib;
    }
}

