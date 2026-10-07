package maintenance.inspection;

public class HistoriqueInspectionLib extends HistoriqueInspectionMere{
    private String idInspecteurLib;
    private String idElementLib;

    public HistoriqueInspectionLib() throws Exception {
        this.setNomTable("HistoriqueInspectionLib");
    }

    public String getIdInspecteurLib() {
        return idInspecteurLib;
    }

    public void setIdInspecteurLib(String idInspecteurLib) {
        this.idInspecteurLib = idInspecteurLib;
    }

    public String getIdElementLib() {
        return idElementLib;
    }

    public void setIdElementLib(String idElementLib) {
        this.idElementLib = idElementLib;
    }
}
