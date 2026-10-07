package paie.formation;

public class FormationPlanLib extends FormationPlan{
    String etatLib;
    public FormationPlanLib() throws Exception {
        this.setNomTable("V_FORMATION_PLAN");
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
