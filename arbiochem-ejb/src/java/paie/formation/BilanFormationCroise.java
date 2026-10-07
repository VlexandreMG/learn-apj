package paie.formation;

import bean.ClassMAPTable;

public class BilanFormationCroise extends ClassMAPTable {
    private int annee;
    private int nbrealiseinterne;
    private int nbrealiseexterne;

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNbrealiseinterne() {
        return nbrealiseinterne;
    }

    public void setNbrealiseinterne(int nbrealiseinterne) {
        this.nbrealiseinterne = nbrealiseinterne;
    }

    public int getNbrealiseexterne() {
        return nbrealiseexterne;
    }

    public void setNbrealiseexterne(int nbrealiseexterne) {
        this.nbrealiseexterne = nbrealiseexterne;
    }



    public BilanFormationCroise() throws Exception {
        this.setNomTable("V_FORMATION_CROISE_TYPE_STATUT");
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

