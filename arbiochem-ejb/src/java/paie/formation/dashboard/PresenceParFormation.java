package paie.formation.dashboard;

import bean.ClassMAPTable;

public class PresenceParFormation extends ClassMAPTable {
    private String intituleFormation;
    private double tauxPresence;

    public PresenceParFormation() throws Exception { this.setNomTable("v_presence_par_formation"); }
    public String getIntituleFormation() { return intituleFormation; }
    public void setIntituleFormation(String intituleFormation) { this.intituleFormation = intituleFormation; }
    public double getTauxPresence() { return tauxPresence; }
    public void setTauxPresence(double tauxPresence) { this.tauxPresence = tauxPresence; }
    @Override public String getTuppleID() { return intituleFormation; }
    @Override public String getAttributIDName() { return "intituleFormation"; }
}