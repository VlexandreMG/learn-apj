package ferme.couvoir;

public class TriageQualitePoussinLib extends TriageQualitePoussin{
    String idLotLib, Lib, idIncubateurLib, idEclosoirLib, etatLib;

    public TriageQualitePoussinLib() throws Exception {
        this.setNomTable("triageQualitePoussin_lib");
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
    }

    public String getLib() {
        return Lib;
    }

    public void setLib(String lib) {
        Lib = lib;
    }

    public String getIdIncubateurLib() {
        return idIncubateurLib;
    }

    public void setIdIncubateurLib(String idIncubateurLib) {
        this.idIncubateurLib = idIncubateurLib;
    }

    public String getIdEclosoirLib() {
        return idEclosoirLib;
    }

    public void setIdEclosoirLib(String idEclosoirLib) {
        this.idEclosoirLib = idEclosoirLib;
    }
}
