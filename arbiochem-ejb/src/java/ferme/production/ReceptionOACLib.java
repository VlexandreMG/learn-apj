package ferme.production;

public class ReceptionOACLib extends ReceptionOAC{
    String idNumeroCollecteLib, idResponsableLib, idChambreFroidLib,idChauffeurLib, idVehiculeLib, etatLib;

    public ReceptionOACLib() throws Exception {
        this.setNomTable("RECEPTIONOAC_LIB");
    }

    public String getIdNumeroCollecteLib() {
        return idNumeroCollecteLib;
    }

    public void setIdNumeroCollecteLib(String idNumeroCollecteLib) {
        this.idNumeroCollecteLib = idNumeroCollecteLib;
    }

    public String getIdResponsableLib() {
        return idResponsableLib;
    }

    public void setIdResponsableLib(String idResponsableLib) {
        this.idResponsableLib = idResponsableLib;
    }

    public String getIdChambreFroidLib() {
        return idChambreFroidLib;
    }

    public void setIdChambreFroidLib(String idChambreFroidLib) {
        this.idChambreFroidLib = idChambreFroidLib;
    }

    public String getIdChauffeurLib() {
        return idChauffeurLib;
    }

    public void setIdChauffeurLib(String idChauffeurLib) {
        this.idChauffeurLib = idChauffeurLib;
    }

    public String getIdVehiculeLib() {
        return idVehiculeLib;
    }

    public void setIdVehiculeLib(String idVehiculeLib) {
        this.idVehiculeLib = idVehiculeLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
