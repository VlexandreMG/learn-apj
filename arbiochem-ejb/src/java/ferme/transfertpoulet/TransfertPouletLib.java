package ferme.transfertpoulet;

import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class TransfertPouletLib extends TransfertPoulet {
    private String idLotLib, idsouchelib, etatLib;
    public TransfertPouletLib()throws Exception{
        super.setNomTable("transfertPoulet_lib");
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
    }

    public String getIdsouchelib() {
        return idsouchelib;
    }

    public void setIdsouchelib(String idsouchelib) {
        this.idsouchelib = idsouchelib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
