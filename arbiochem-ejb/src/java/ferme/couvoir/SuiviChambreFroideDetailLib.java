package ferme.couvoir;

public class SuiviChambreFroideDetailLib extends SuiviChambreFroideDetail{
    String idResponsableLib;

    public SuiviChambreFroideDetailLib() throws Exception {
        this.setNomTable("suiviChambreFroideDetail_lib");
    }

    public String getIdResponsableLib() {
        return idResponsableLib;
    }

    public void setIdResponsableLib(String idResponsableLib) {
        this.idResponsableLib = idResponsableLib;
    }
}
