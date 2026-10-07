package ferme.vaccination;

import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class VaccinationPoussinLib extends VaccinationPoussin {

    private String idLotLib, etatLib;

    public VaccinationPoussinLib() throws Exception {
        super.setNomTable("vaccinationpoussin_lib");
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}