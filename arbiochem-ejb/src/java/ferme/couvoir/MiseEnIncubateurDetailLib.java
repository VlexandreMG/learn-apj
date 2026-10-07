package ferme.couvoir;

public class MiseEnIncubateurDetailLib extends MiseEnIncubateurDetail {
    String idLotLib, idBatimentLib, idParquetLib;

    public MiseEnIncubateurDetailLib() throws Exception {
        this.setNomTable("MISEENINCUBATEURDETAIL_LIB");
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
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
}
