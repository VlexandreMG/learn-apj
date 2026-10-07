package faturefournisseur;

public class FournisseurCpl extends Fournisseur {
    private int nb;

    public FournisseurCpl () {
        this.setNomTable("FournisseurCpl");
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
