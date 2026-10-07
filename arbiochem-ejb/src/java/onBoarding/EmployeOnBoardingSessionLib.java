package onBoarding;

public class EmployeOnBoardingSessionLib extends EmployeOnBoardingSession{
    String onboardinglib;
    String personnelLib;
    String etatLib;
    String idFonction;
    String idFonctionLib;

    public String getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(String idFonction) {
        this.idFonction = idFonction;
    }

    public String getIdFonctionLib() {
        return idFonctionLib;
    }

    public void setIdFonctionLib(String idFonctionLib) {
        this.idFonctionLib = idFonctionLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getOnboardinglib() {
        return onboardinglib;
    }

    public void setOnboardinglib(String onboardinglib) {
        this.onboardinglib = onboardinglib;
    }

    public String getPersonnelLib() {
        return personnelLib;
    }

    public void setPersonnelLib(String personnelLib) {
        this.personnelLib = personnelLib;
    }

    public EmployeOnBoardingSessionLib() throws Exception {
        this.setNomTable("employeeOnboardingSessionsCpl");
    }


}
