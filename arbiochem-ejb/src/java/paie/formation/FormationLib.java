package paie.formation;

public class FormationLib extends Formation{
    String typeLib, planLib;
    public FormationLib() throws Exception {
        this.setNomTable("V_FORMATION");
    }

    public String getTypeLib() {
        return typeLib;
    }

    public void setTypeLib(String typeLib) {
        this.typeLib = typeLib;
    }

    public String getPlanLib() {
        return planLib;
    }

    public void setPlanLib(String planLib) {
        this.planLib = planLib;
    }
}
