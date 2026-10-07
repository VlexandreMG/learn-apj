package paie.onBoarding;

public class ProgrammeIntegrationDetailLib extends ProgrammeIntegrationDetail {
    private String idintervenantlib;
    private String idintervenantfonction;
    private String idintervenantfonctionlib;
    private String idintervenantservice;
    private String idintervenantservicelib;
    private double duree_effective;

    public ProgrammeIntegrationDetailLib() throws Exception {
        super();
        this.setNomTable("V_PROGRAMME_INTEGRATION_DETAIL_LIB");
    }

    // Getters et Setters
    public String getIdintervenantlib() { return idintervenantlib; }
    public void setIdintervenantlib(String idintervenantlib) { this.idintervenantlib = idintervenantlib; }

    public String getIdintervenantfonction() { return idintervenantfonction; }
    public void setIdintervenantfonction(String idintervenantfonction) { this.idintervenantfonction = idintervenantfonction; }

    public String getIdintervenantfonctionlib() { return idintervenantfonctionlib; }
    public void setIdintervenantfonctionlib(String idintervenantfonctionlib) { this.idintervenantfonctionlib = idintervenantfonctionlib; }

    public String getIdintervenantservice() { return idintervenantservice; }
    public void setIdintervenantservice(String idintervenantservice) { this.idintervenantservice = idintervenantservice; }

    public String getIdintervenantservicelib() { return idintervenantservicelib; }
    public void setIdintervenantservicelib(String idintervenantservicelib) { this.idintervenantservicelib = idintervenantservicelib; }

    public double getDuree_effective() { return duree_effective; }
    public void setDuree_effective(double duree_effective) { this.duree_effective = duree_effective; }
}