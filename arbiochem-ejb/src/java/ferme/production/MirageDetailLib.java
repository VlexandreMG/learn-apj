package ferme.production;

public class MirageDetailLib extends MirageDetail{
    String idBatimentLib, idParquetLib, idQualiteMirageLib;

    public MirageDetailLib() throws Exception {
        this.setNomTable("MIRAGEDETAIL_LIB");
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

    public String getIdQualiteMirageLib() {
        return idQualiteMirageLib;
    }

    public void setIdQualiteMirageLib(String idQualiteMirageLib) {
        this.idQualiteMirageLib = idQualiteMirageLib;
    }
}
