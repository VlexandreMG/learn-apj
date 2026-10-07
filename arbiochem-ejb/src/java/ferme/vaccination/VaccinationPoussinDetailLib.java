package ferme.vaccination;

import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class VaccinationPoussinDetailLib extends VaccinationPoussinDetail {

    private String idBatimentLib, idParquetLib, idTypeVaccinationLib, idMaladiePoussinLib, idVaccinLib, idModeAdministrationLib;

    public VaccinationPoussinDetailLib() throws Exception {
        super.setNomTable("vaccinationpoussindetail_lib");
    }

    public String getIdBatimentLib() {
        return idBatimentLib;
    }

    public void setIdBatimentLib(String idBatimentLib) {
        this.idBatimentLib = idBatimentLib;
    }

    public String getIdParquetLib() {
        return idParquetLib;
    }

    public void setIdParquetLib(String idParquetLib) {
        this.idParquetLib = idParquetLib;
    }

    public String getIdTypeVaccinationLib() {
        return idTypeVaccinationLib;
    }

    public void setIdTypeVaccinationLib(String idTypeVaccinationLib) {
        this.idTypeVaccinationLib = idTypeVaccinationLib;
    }

    public String getIdMaladiePoussinLib() {
        return idMaladiePoussinLib;
    }

    public void setIdMaladiePoussinLib(String idMaladiePoussinLib) {
        this.idMaladiePoussinLib = idMaladiePoussinLib;
    }

    public String getIdVaccinLib() {
        return idVaccinLib;
    }

    public void setIdVaccinLib(String idVaccinLib) {
        this.idVaccinLib = idVaccinLib;
    }

    public String getIdModeAdministrationLib() {
        return idModeAdministrationLib;
    }

    public void setIdModeAdministrationLib(String idModeAdministrationLib) {
        this.idModeAdministrationLib = idModeAdministrationLib;
    }
}