package ferme.receptionaeroport;

import java.sql.Connection;

/**
 *
 * @author Safidy
 */
public class ReceptionPoussinAeroportDetailLib extends ReceptionPoussinAeroportDetail {
    private String idQualitePoussinLib,idSexeLib;
    public ReceptionPoussinAeroportDetailLib()throws Exception{
        super.setNomTable("receptionPoussinAEDetail_lib");
    }

    public String getIdQualitePoussinLib() {
        return idQualitePoussinLib;
    }

    public void setIdQualitePoussinLib(String idQualitePoussinLib) {
        this.idQualitePoussinLib = idQualitePoussinLib;
    }

    public String getIdSexeLib() {
        return idSexeLib;
    }

    public void setIdSexeLib(String idSexeLib) {
        this.idSexeLib = idSexeLib;
    }
}
