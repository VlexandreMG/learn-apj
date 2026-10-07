package ferme.aliment;

/**
 *
 * @author Safidy
 */
public class DistributionAlimentLib extends DistributionAliment {
    private String idFermeLib,idLotLib, idSoucheLib, etatLib;

    public DistributionAlimentLib()throws Exception {
        super.setNomTable("distributionAliment_lib");
    }

    public String getIdFermeLib() {
        return idFermeLib;
    }

    public void setIdFermeLib(String idFermeLib) {
        this.idFermeLib = idFermeLib;
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
    }

    public String getIdSoucheLib() {
        return idSoucheLib;
    }

    public void setIdSoucheLib(String idSoucheLib) {
        this.idSoucheLib = idSoucheLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}