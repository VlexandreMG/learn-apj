package paie.formation;

import bean.ClassMAPTable;

public class BilanFormationParStatut extends ClassMAPTable {
    private int annee;
    private int nbrealise;
    private int nbnonrealisee;

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNbrealise() {
        return nbrealise;
    }

    public void setNbrealise(int nbrealise) {
        this.nbrealise = nbrealise;
    }

    public int getNbnonrealisee() {
        return nbnonrealisee;
    }

    public void setNbnonrealisee(int nbnonrealisee) {
        this.nbnonrealisee = nbnonrealisee;
    }



    public BilanFormationParStatut() throws Exception {
        this.setNomTable("V_FORMATION_BILAN_PAR_STATUT");
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

