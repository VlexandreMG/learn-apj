package ferme.couvoir;

public class EclosionLib extends Eclosion{
    String idIncubateurLib, idBatimentLib, idParquetLib, etatLib, idEclosoirLib;

    public EclosionLib() throws Exception {
        this.setNomTable("ECLOSION_LIB");
    }

    public String getIdIncubateurLib() {
        return idIncubateurLib;
    }

    public void setIdIncubateurLib(String idIncubateurLib) {
        this.idIncubateurLib = idIncubateurLib;
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

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdEclosoirLib() {
        return idEclosoirLib;
    }

    public void setIdEclosoirLib(String idEclosoirLib) {
        this.idEclosoirLib = idEclosoirLib;
    }
}
