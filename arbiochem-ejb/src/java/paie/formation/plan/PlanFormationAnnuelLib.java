package paie.formation.plan;

public class PlanFormationAnnuelLib extends PlanFormationAnnuel {
    String etatlib;

    
    public PlanFormationAnnuelLib() throws Exception {
        super.setNomTable("PLANFORMATIONANNUELLELIB");
    }

    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
    
    
}
