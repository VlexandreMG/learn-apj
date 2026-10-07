package faturefournisseur;

import java.sql.Date;

public class As_BonDeCommande_Fille_CPL extends As_BonDeCommande_Fille {
    protected String produitlib ;
    protected String unitelib;
    protected String modepaiementlib;
    protected String fournisseurlib;
    private Date daty;
    protected double qtelivrer;
    private String fournisseur;
     protected double qtefacturer;
     double montantTtcAr;
    private double remiseMontant;

    public double getRemiseMontant() {
        return remiseMontant;
    }

    public void setRemiseMontant(double remiseMontant) {
        this.remiseMontant = remiseMontant;
    }
    
    public As_BonDeCommande_Fille_CPL() throws Exception {
        super.setNomTable("AS_BONDECOMMANDE_CPL");
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getProduitlib() {
        return produitlib;
    }
    public void setProduitlib(String produitlib) {
        this.produitlib = produitlib;
    }
    public String getUnitelib() {
        return unitelib;
    }
    public void setUnitelib(String unitelib) {
        this.unitelib = unitelib;
    }
    public String getModepaiementlib() {
        return modepaiementlib;
    }
    public void setModepaiementlib(String modepaiementlib) {
        this.modepaiementlib = modepaiementlib;
    }
    public String getFournisseurlib() {
        return fournisseurlib;
    }
    public void setFournisseurlib(String fournisseurlib) {
        this.fournisseurlib = fournisseurlib;
    }
    public double getQtelivrer() {
        return qtelivrer;
    }
    public void setQtelivrer(double qtelivrer) {
        this.qtelivrer = qtelivrer;
    }
    public double getQtefacturer() {
        return qtefacturer;
    }
    public void setQtefacturer(double qtefacturer) {
        this.qtefacturer = qtefacturer;
    }


    public double getResteLivre() {
        return getQuantite() - getQtelivrer();
    }

    public double getResteFacture() {
        return getQuantite() - getQtefacturer();
    }


    public double getMontantTtcAr() {
        return montantTtcAr;
    }

    public void setMontantTtcAr(double montantTtcAr) {
        this.montantTtcAr = montantTtcAr;
    }
}
