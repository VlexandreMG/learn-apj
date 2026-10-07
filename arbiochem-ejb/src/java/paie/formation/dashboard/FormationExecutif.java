package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationExecutif extends ClassMAPTable {
    private String typeprestataire;
    private int nbformationtotal;
    private int nbformationrealisee;
    private int nbstagiaireforme;
    private int totalheureformation;
    private int budgetformation;
    private int tauxpresence;
    private int tauxcouverture;

    public String getTypeprestataire() {
        return typeprestataire;
    }

    public void setTypeprestataire(String typeprestataire) {
        this.typeprestataire = typeprestataire;
    }

    public int getNbformationtotal() {
        return nbformationtotal;
    }

    public void setNbformationtotal(int nbformationtotal) {
        this.nbformationtotal = nbformationtotal;
    }

    public int getNbformationrealisee() {
        return nbformationrealisee;
    }

    public void setNbformationrealisee(int nbformationrealisee) {
        this.nbformationrealisee = nbformationrealisee;
    }

    public int getNbstagiaireforme() {
        return nbstagiaireforme;
    }

    public void setNbstagiaireforme(int nbstagiaireforme) {
        this.nbstagiaireforme = nbstagiaireforme;
    }

    public int getTotalheureformation() {
        return totalheureformation;
    }

    public void setTotalheureformation(int totalheureformation) {
        this.totalheureformation = totalheureformation;
    }

    public int getBudgetformation() {
        return budgetformation;
    }

    public void setBudgetformation(int budgetformation) {
        this.budgetformation = budgetformation;
    }

    public int getTauxpresence() {
        return tauxpresence;
    }

    public void setTauxpresence(int tauxpresence) {
        this.tauxpresence = tauxpresence;
    }

    public int getTauxcouverture() {
        return tauxcouverture;
    }

    public void setTauxcouverture(int tauxcouverture) {
        this.tauxcouverture = tauxcouverture;
    }



    public FormationExecutif() throws Exception {
        this.setNomTable("V_DASHBOARD_FORMATION_EXECUTIF");
    }



    @Override
    public String getTuppleID() {
        return typeprestataire;
    }

    @Override
    public String getAttributIDName() {
        return "typeprestataire";
    }
}

