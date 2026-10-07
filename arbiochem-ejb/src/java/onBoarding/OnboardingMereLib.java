package onBoarding;

public class OnboardingMereLib extends OnboardingMere{
    private String idFonctionLib;

    public String getIdFonctionLib() {
        return idFonctionLib;
    }

    public void setIdFonctionLib(String idFonctionLib) {
        this.idFonctionLib = idFonctionLib;
    }

    public OnboardingMereLib() throws Exception {
        this.setNomTable("ONBOARDING_MERE_LIB");
    }
}
