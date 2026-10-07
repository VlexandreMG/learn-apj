package onBoarding;

public class EmployeOnboardingChecklistLib extends EmployeOnBoardingChecklist{
    String etatLib;
    String idOnboardingItemlib;
    String estTerminerLib;


    public String getEstTerminerLib() {
        return estTerminerLib;
    }

    public void setEstTerminerLib(String estTerminerLib) {
        this.estTerminerLib = estTerminerLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdOnboardingItemlib() {
        return idOnboardingItemlib;
    }

    public void setIdOnboardingItemlib(String idOnboardingItemlib) {
        this.idOnboardingItemlib = idOnboardingItemlib;
    }

    public EmployeOnboardingChecklistLib() throws Exception {
        this.setNomTable("employeeOnboardingChecklistCPL");
    }
}
