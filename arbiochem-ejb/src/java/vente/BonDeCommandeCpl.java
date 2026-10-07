package vente;

public class BonDeCommandeCpl extends BonDeCommande{
    private String idclientlib ,modepaiementlib, etatlib ,idMagasinLib, modelivraisonlib;
    int nbfacture;
    String facturelib;


    public String getFacturelib() {
        return facturelib;
    }

    public void setFacturelib(String facturelib) {
        this.facturelib = facturelib;
    }

    public String getModelivraisonlib() {
        return modelivraisonlib;
    }

    public void setModelivraisonlib(String modelivraisonlib) {
        this.modelivraisonlib = modelivraisonlib;
    }

    public int getNbfacture() {
        return nbfacture;
    }

    public void setNbfacture(int nbfacture) {
        this.nbfacture = nbfacture;
    }

    public BonDeCommandeCpl() {
        this.setNomTable("BONDECOMMANDE_CLIENT_CPL");
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public String getIdclientlib() {
        return idclientlib;
    }

    public void setIdclientlib(String idclientlib) {
        this.idclientlib = idclientlib;
    }

    public String getModepaiementlib() {
        return modepaiementlib;
    }

    public void setModepaiementlib(String modepaiementlib) {
        this.modepaiementlib = modepaiementlib;
    }
    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
}
