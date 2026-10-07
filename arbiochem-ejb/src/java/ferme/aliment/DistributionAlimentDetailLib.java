package ferme.aliment;

import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class DistributionAlimentDetailLib extends DistributionAlimentDetail{
    private String idBatimentLib,idParquetLib,idAlimentLib;

    public DistributionAlimentDetailLib()throws Exception {
        super.setNomTable("distributionAlimentDetail_lib");
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

    public String getIdAlimentLib() {
        return idAlimentLib;
    }

    public void setIdAlimentLib(String idAlimentLib) {
        this.idAlimentLib = idAlimentLib;
    }
}
