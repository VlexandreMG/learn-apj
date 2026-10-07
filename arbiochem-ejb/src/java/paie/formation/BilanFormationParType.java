package paie.formation;

import bean.ClassMAPTable;

public class BilanFormationParType extends ClassMAPTable {
    private int annee;
    private int nbinterne;
    private int nbexterne;

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNbinterne() {
        return nbinterne;
    }

    public void setNbinterne(int nbinterne) {
        this.nbinterne = nbinterne;
    }

    public int getNbexterne() {
        return nbexterne;
    }

    public void setNbexterne(int nbexterne) {
        this.nbexterne = nbexterne;
    }



    public BilanFormationParType() throws Exception {
        this.setNomTable("V_FORMATION_BILAN_PAR_TYPE");
    }

    @Override
    public String getTuppleID() {
        return String.valueOf(annee);
    }

    @Override
    public String getAttributIDName() {
        return "annee";
    }
}

