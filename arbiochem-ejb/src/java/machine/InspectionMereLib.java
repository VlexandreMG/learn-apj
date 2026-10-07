package machine;

public class InspectionMereLib extends InspectionMere{
    private String idInspecteurLib;

    public String getIdInspecteurLib() {
        return idInspecteurLib;
    }

    public void setIdInspecteurLib(String idInspecteurLib) {
        this.idInspecteurLib = idInspecteurLib;
    }

    public InspectionMereLib() throws  Exception{
        setNomTable("INSPECTIONMERELIB");
    }
}
