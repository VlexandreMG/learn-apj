package ferme.suiviJournalier;

public class SuiviJournalierDetailLib extends SuiviJournalierDetail{
    String idBatimentLib, idParquetLib;
    public SuiviJournalierDetailLib() throws Exception {
        this.setNomTable("SUIVIJOURNALIERDETAIL_LIB");
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
