package paie.formation.dashboard;

import bean.ClassMAPTable;

public class CoutFormationType extends ClassMAPTable {
    private String typeCout;
    private double montantTotal;

    public CoutFormationType() throws Exception { this.setNomTable("v_cout_formation_par_type_cout"); }
    public String getTypeCout() { return typeCout; }
    public void setTypeCout(String typeCout) { this.typeCout = typeCout; }
    public double getMontantTotal() { return montantTotal; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }
    @Override public String getTuppleID() { return typeCout; }
    @Override public String getAttributIDName() { return "typeCout"; }
}
