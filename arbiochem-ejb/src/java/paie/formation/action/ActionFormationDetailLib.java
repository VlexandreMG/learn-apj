package paie.formation.action;

public class ActionFormationDetailLib extends ActionFormationDetail{
    String idTypeCoutLib, idDeviseLib;
    public ActionFormationDetailLib() throws Exception {
        this.setNomTable("ACTION_FORMATION_DETAIL_LIB");
    }

    public String getIdTypeCoutLib() {
        return idTypeCoutLib;
    }

    public void setIdTypeCoutLib(String idTypeCoutLib) {
        this.idTypeCoutLib = idTypeCoutLib;
    }

    public String getIdDeviseLib() {
        return idDeviseLib;
    }

    public void setIdDeviseLib(String idDeviseLib) {
        this.idDeviseLib = idDeviseLib;
    }
}
