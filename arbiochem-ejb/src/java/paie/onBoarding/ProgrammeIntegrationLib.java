package paie.onBoarding;

public class ProgrammeIntegrationLib extends ProgrammeIntegration {
    private String idpersonnellib;
    private String matricule;
    private String idfonction;
    private String idfonctionlib;
    private String idservice;
    private String idservicelib;

    public ProgrammeIntegrationLib() throws Exception {
        this.setNomTable("V_PROGRAMME_INTEGRATION_LIB");
    }

    // Getters et Setters
    public String getIdpersonnellib() { return idpersonnellib; }
    public void setIdpersonnellib(String idpersonnellib) { this.idpersonnellib = idpersonnellib; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getIdfonction() { return idfonction; }
    public void setIdfonction(String idfonction) { this.idfonction = idfonction; }

    public String getIdfonctionlib() { return idfonctionlib; }
    public void setIdfonctionlib(String idfonctionlib) { this.idfonctionlib = idfonctionlib; }

    public String getIdservice() { return idservice; }
    public void setIdservice(String idservice) { this.idservice = idservice; }

    public String getIdservicelib() { return idservicelib; }
    public void setIdservicelib(String idservicelib) { this.idservicelib = idservicelib; }
}