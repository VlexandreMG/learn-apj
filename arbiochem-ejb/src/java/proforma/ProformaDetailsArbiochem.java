package proforma;

public class ProformaDetailsArbiochem extends ProformaDetails{
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

    public ProformaDetailsArbiochem() throws Exception {
        this.setNomTable("PROFORMA_DETAILS");
        this.setLiaisonMere("idProforma");
        this.setNomClasseMere("proforma.Proforma");
    }
}
