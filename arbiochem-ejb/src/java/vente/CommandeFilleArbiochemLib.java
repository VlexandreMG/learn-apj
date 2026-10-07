package vente;

public class CommandeFilleArbiochemLib extends CommandeFille{
    double montantRemise, montantRistourne;

    public double getMontantRemise() {
        return montantRemise;
    }

    public void setMontantRemise(double montantRemise) {
        this.montantRemise = montantRemise;
    }

    public double getMontantRistourne() {
        return montantRistourne;
    }

    public void setMontantRistourne(double montantRistourne) {
        this.montantRistourne = montantRistourne;
    }

    public CommandeFilleArbiochemLib() throws Exception {
        super.setNomTable("COMMANDEFILLE_arbiochem");
    }
}
