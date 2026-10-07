package ferme.receptionaeroport;
import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class ReceptionPoussinAeroportLib extends ReceptionPoussinAeroport {
    private String idLotLib,etatLib;
    private int nbrCartonTotal;
    public ReceptionPoussinAeroportLib()throws Exception{
        super.setNomTable("receptionPoussinAeroport_lib");
    }

    public int getNbrCartonTotal() {
        return nbrCartonTotal;
    }

    public void setNbrCartonTotal(int nbrCartonTotal) {
        this.nbrCartonTotal = nbrCartonTotal;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
    }
}
