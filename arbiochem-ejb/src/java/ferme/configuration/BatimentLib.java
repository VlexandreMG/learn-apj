package ferme.configuration;
import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class BatimentLib extends Batiment {
    private String idFermeLib;
    public BatimentLib()throws Exception{
        super.setNomTable("batiment_lib");
    }

    public String getIdFermeLib() {
        return idFermeLib;
    }

    public void setIdFermeLib(String idFermeLib) {
        this.idFermeLib = idFermeLib;
    }
}
