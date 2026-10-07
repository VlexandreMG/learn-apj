package paie.formation.suivi;

public class SuiviPresenceMereLib extends SuiviPresenceMere{
    private String actionFormationLib, sessionFormationLib;
    public SuiviPresenceMereLib() throws Exception {
        this.setNomTable("SUIVI_PRESENCE_FILLE");
    }

    public String getActionFormationLib() {
        return actionFormationLib;
    }

    public void setActionFormationLib(String actionFormationLib) {
        this.actionFormationLib = actionFormationLib;
    }

    public String getSessionFormationLib() {
        return sessionFormationLib;
    }

    public void setSessionFormationLib(String sessionFormationLib) {
        this.sessionFormationLib = sessionFormationLib;
    }
}
