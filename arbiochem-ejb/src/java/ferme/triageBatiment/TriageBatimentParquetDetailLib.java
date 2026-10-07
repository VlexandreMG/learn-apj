package ferme.triageBatiment;

public class TriageBatimentParquetDetailLib extends TriageBatimentParquetDetail{
    String idBatimentLib, idParquetLib, idQualiteLib, idSexeLib,idLot;
    public TriageBatimentParquetDetailLib() throws Exception {
        this.setNomTable("TRIAGEBATPARQUETDETAIL_LIB");
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

    public String getIdSexeLib() {
        return idSexeLib;
    }

    public void setIdSexeLib(String idSexeLib) {
        this.idSexeLib = idSexeLib;
    }

    public String getIdLot() {
        return idLot;
    }

    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }
}
