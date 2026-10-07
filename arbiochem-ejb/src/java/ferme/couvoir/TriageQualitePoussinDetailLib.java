package ferme.couvoir;

public class TriageQualitePoussinDetailLib extends TriageQualitePoussinDetail{
    String idBatimentLib, idParquetLib, idQualiteLib;

    public TriageQualitePoussinDetailLib() throws Exception {
        this.setNomTable("triageQualitePoussinDetail_lib");
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

    public String getIdQualiteLib() {
        return idQualiteLib;
    }

    public void setIdQualiteLib(String idQualiteLib) {
        this.idQualiteLib = idQualiteLib;
    }
}
