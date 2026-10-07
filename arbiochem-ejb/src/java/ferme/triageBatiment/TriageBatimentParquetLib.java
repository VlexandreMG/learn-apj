package ferme.triageBatiment;

public class TriageBatimentParquetLib extends TriageBatimentParquet{
    String idLotLib,idSoucheLib,etatLib, ecartFemelle, ecartMale;
    public TriageBatimentParquetLib() throws Exception {
        this.setNomTable("TRIAGEBATIMENTPARQUET_LIB");
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

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public void setIdSoucheLib(String idSoucheLib) {
        this.idSoucheLib = idSoucheLib;
    }

    public String getEcartFemelle() {
        return ecartFemelle;
    }

    public void setEcartFemelle(String ecartFemelle) {
        this.ecartFemelle = ecartFemelle;
    }

    public String getEcartMale() {
        return ecartMale;
    }

    public void setEcartMale(String ecartMale) {
        this.ecartMale = ecartMale;
    }
}
