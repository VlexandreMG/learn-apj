package vente;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VenteDetailsArbiochem extends VenteDetails{
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

    public VenteDetailsArbiochem() {
        super.setNomTable("Vente_Details");
        try {
            this.setNomClasseMere("vente.Vente");
            this.setLiaisonMere("idVente");
        } catch (Exception ex) {
            Logger.getLogger(VenteDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
