package paie.formation.dashboard;

import bean.ClassMAPTable;

public class FormationRealisationIntExt extends ClassMAPTable {
    private String typeprestataire;
    private int nbrealisee;
    private int nbnonrealisee;

    public String getTypeprestataire() {
        return typeprestataire;
    }

    public void setTypeprestataire(String typeprestataire) {
        this.typeprestataire = typeprestataire;
    }

    public int getNbrealisee() {
        return nbrealisee;
    }

    public void setNbrealisee(int nbrealisee) {
        this.nbrealisee = nbrealisee;
    }

    public int getNbnonrealisee() {
        return nbnonrealisee;
    }

    public void setNbnonrealisee(int nbnonrealisee) {
        this.nbnonrealisee = nbnonrealisee;
    }



    public FormationRealisationIntExt() throws Exception {
        this.setNomTable("V_FORMATION_INT_EXT_RLSATION");
    }

  

    @Override
    public String getTuppleID() {
        return "typeprestataire";
    }

    @Override
    public String getAttributIDName() {
        return "typeprestataire";
    }
}

