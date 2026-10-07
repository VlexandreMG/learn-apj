package vente;

public class PaiementInfo {
    private String reference;
    private double montant;

    public PaiementInfo(String reference, double montant) {
        this.reference = reference;
        this.montant = montant;
    }

    public String getReference() { return reference; }
    public double getMontant() { return montant; }
}