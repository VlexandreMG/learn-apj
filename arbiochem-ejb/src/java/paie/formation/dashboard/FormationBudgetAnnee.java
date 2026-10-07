package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationBudgetAnnee extends ClassMAPTable {
    private int annee;
    private double budgetTotalPrevisionnel;

    public FormationBudgetAnnee() throws Exception { this.setNomTable("v_budget_formation_par_annee"); }
    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }
    public double getBudgetTotalPrevisionnel() { return budgetTotalPrevisionnel; }
    public void setBudgetTotalPrevisionnel(double budgetTotalPrevisionnel) { this.budgetTotalPrevisionnel = budgetTotalPrevisionnel; }
    @Override public String getTuppleID() { return String.valueOf(annee); }
    @Override public String getAttributIDName() { return "annee"; }
}
