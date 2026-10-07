package paie.formation.dashboard;

import bean.ClassMAPTable;

public class CoutFormationParAction extends ClassMAPTable {
    private String intituleFormation; // Doit être identique au AS dans le SQL
    private double coutTotal;

    public CoutFormationParAction() throws Exception { this.setNomTable("v_cout_formation_par_action"); }

    public String getIntituleFormation() { return intituleFormation; }
    public void setIntituleFormation(String intituleFormation) { this.intituleFormation = intituleFormation; }

    public double getCoutTotal() { return coutTotal; }
    public void setCoutTotal(double coutTotal) { this.coutTotal = coutTotal; }

    @Override public String getTuppleID() { return intituleFormation; }
    @Override public String getAttributIDName() { return "intituleFormation"; }
}