package paie.formation.suivi;

public class SuiviPresenceFilleLib extends SuiviPresenceFille{
    private String personnelLib, matricule, categorieEmploye, actionFormationLib;
    public SuiviPresenceFilleLib() throws Exception {
        this.setNomTable("SUIVI_PRESENCE_FILLE_LIB");
    }

    public String getPersonnelLib() {
        return personnelLib;
    }

    public void setPersonnelLib(String personnelLib) {
        this.personnelLib = personnelLib;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCategorieEmploye() {
        return categorieEmploye;
    }

    public void setCategorieEmploye(String categorieEmploye) {
        this.categorieEmploye = categorieEmploye;
    }

    public String getActionFormationLib() {
        return actionFormationLib;
    }

    public void setActionFormationLib(String actionFormationLib) {
        this.actionFormationLib = actionFormationLib;
    }
}
