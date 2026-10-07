package ferme.configuration;

import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class BatimentDetailLib extends BatimentDetail {
    private String idParquetLib,idLotLib,idSexeLib;
    public BatimentDetailLib()throws Exception{
        super.setNomTable("batimentdetail_lib");
    }

    public String getIdParquetLib() {
        return idParquetLib;
    }

    public void setIdParquetLib(String idParquetLib) {
        this.idParquetLib = idParquetLib;
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
    }

    public String getIdSexeLib() {
        return idSexeLib;
    }

    public void setIdSexeLib(String idSexeLib) {
        this.idSexeLib = idSexeLib;
    }
}
