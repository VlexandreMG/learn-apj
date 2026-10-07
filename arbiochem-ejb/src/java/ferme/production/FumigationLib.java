package ferme.production;

public class FumigationLib extends Fumigation{
    String idNumeroVagueLib, idOperateurLib, idLotLib, etatLib, idProduitLib, idBatimentLib, idParquetLib;

    public FumigationLib() throws Exception {
        this.setNomTable("FUMIGATION_LIB");
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

    public String getIdNumeroVagueLib() {
        return idNumeroVagueLib;
    }

    public void setIdNumeroVagueLib(String idNumeroVagueLib) {
        this.idNumeroVagueLib = idNumeroVagueLib;
    }

    public String getIdOperateurLib() {
        return idOperateurLib;
    }

    public void setIdOperateurLib(String idOperateurLib) {
        this.idOperateurLib = idOperateurLib;
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

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }
}
