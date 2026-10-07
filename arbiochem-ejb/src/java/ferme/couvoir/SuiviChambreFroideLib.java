package ferme.couvoir;

public class SuiviChambreFroideLib extends SuiviChambreFroide{
    String idSalleStockageOeufLib, etatLib;

    public SuiviChambreFroideLib() throws Exception {
        this.setNomTable("suiviChambreFroide_lib");
    }

    public String getIdSalleStockageOeufLib() {
        return idSalleStockageOeufLib;
    }

    public void setIdSalleStockageOeufLib(String idSalleStockageOeufLib) {
        this.idSalleStockageOeufLib = idSalleStockageOeufLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
