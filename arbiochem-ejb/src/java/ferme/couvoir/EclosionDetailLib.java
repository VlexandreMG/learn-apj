package ferme.couvoir;

public class EclosionDetailLib extends EclosionDetail{
    String idQualiteEclosionLib;

    public EclosionDetailLib() throws Exception {
        this.setNomTable("ECLOSIONDETAIL_LIB");
    }

    public String getIdQualiteEclosionLib() {
        return idQualiteEclosionLib;
    }

    public void setIdQualiteEclosionLib(String idQualiteEclosionLib) {
        this.idQualiteEclosionLib = idQualiteEclosionLib;
    }
}
