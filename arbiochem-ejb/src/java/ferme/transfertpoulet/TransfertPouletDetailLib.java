package ferme.transfertpoulet;

import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class TransfertPouletDetailLib extends TransfertPouletDetail {
    private String idSexeLib,idFermeDepartLib,idBatimentDepartLib,idParquetDepartLib,idFermeArriveLib,idBatimentArriveLib,idParquetArriveLib,idControleurLib,idChauffeurLib,idVehiculeLib;
    public TransfertPouletDetailLib()throws Exception{
        super.setNomTable("transfertPouletDetail_lib");
    }

    public String getIdSexeLib() {
        return idSexeLib;
    }

    public void setIdSexeLib(String idSexeLib) {
        this.idSexeLib = idSexeLib;
    }

    public String getIdFermeDepartLib() {
        return idFermeDepartLib;
    }

    public void setIdFermeDepartLib(String idFermeDepartLib) {
        this.idFermeDepartLib = idFermeDepartLib;
    }

    public String getIdBatimentDepartLib() {
        return idBatimentDepartLib;
    }

    public void setIdBatimentDepartLib(String idBatimentDepartLib) {
        this.idBatimentDepartLib = idBatimentDepartLib;
    }

    public String getIdParquetDepartLib() {
        return idParquetDepartLib;
    }

    public void setIdParquetDepartLib(String idParquetDepartLib) {
        this.idParquetDepartLib = idParquetDepartLib;
    }

    public String getIdFermeArriveLib() {
        return idFermeArriveLib;
    }

    public void setIdFermeArriveLib(String idFermeArriveLib) {
        this.idFermeArriveLib = idFermeArriveLib;
    }

    public String getIdBatimentArriveLib() {
        return idBatimentArriveLib;
    }

    public void setIdBatimentArriveLib(String idBatimentArriveLib) {
        this.idBatimentArriveLib = idBatimentArriveLib;
    }

    public String getIdParquetArriveLib() {
        return idParquetArriveLib;
    }

    public void setIdParquetArriveLib(String idParquetArriveLib) {
        this.idParquetArriveLib = idParquetArriveLib;
    }

    public String getIdControleurLib() {
        return idControleurLib;
    }

    public void setIdControleurLib(String idControleurLib) {
        this.idControleurLib = idControleurLib;
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
}
