package maintenance.inspection;

public class HistoriqueInspectionFilleLib extends HistoriqueInspectionFille{
    private String idInspecteurLib;
    private String idElementLib;
    private String etatInspectionLib;

    public HistoriqueInspectionFilleLib() throws Exception {
        this.setNomTable("HistoriqueInspectionFilleLib");
    }

    public String getEtatInspectionLib() {
        return etatInspectionLib;
    }

    public void setEtatInspectionLib(String etatInspectionLib) {
        this.etatInspectionLib = etatInspectionLib;
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
