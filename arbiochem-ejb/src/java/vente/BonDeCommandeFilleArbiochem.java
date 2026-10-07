package vente;

public class BonDeCommandeFilleArbiochem extends BonDeCommandeFille{
    private double remiseArbiochem, ristourneArbiochem;

    public double getRemiseArbiochem() {
        return remiseArbiochem;
    }

    public void setRemiseArbiochem(double remiseArbiochem) {
        this.remiseArbiochem = remiseArbiochem;
    }

    public double getRistourneArbiochem() {
        return ristourneArbiochem;
    }

    public void setRistourneArbiochem(double ristourneArbiochem) {
        this.ristourneArbiochem = ristourneArbiochem;
    }

    public BonDeCommandeFilleArbiochem() throws Exception {
        super.setNomTable("BONDECOMMANDE_CLIENT_FILLE");
        this.setNomClasseMere("vente.BonDeCommande");
        this.setLiaisonMere("idbc");
    }
}
