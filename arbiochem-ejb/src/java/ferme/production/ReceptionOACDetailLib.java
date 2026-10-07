package ferme.production;

public class ReceptionOACDetailLib extends ReceptionOACDetail{
    String idLotLib, idBatimentLib, idParquetLib, idQualiteTriageOeufLib;

    public ReceptionOACDetailLib() throws Exception {
        this.setNomTable("RECEPTIONOACDETAIL_LIB");
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

    public String getIdQualiteTriageOeufLib() {
        return idQualiteTriageOeufLib;
    }

    public void setIdQualiteTriageOeufLib(String idQualiteTriageOeufLib) {
        this.idQualiteTriageOeufLib = idQualiteTriageOeufLib;
    }
}
