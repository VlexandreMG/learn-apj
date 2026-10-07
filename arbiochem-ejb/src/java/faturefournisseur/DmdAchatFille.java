package faturefournisseur;

import bean.ClassFille;

import java.sql.Connection;

public class DmdAchatFille extends ClassFille {
    private String id;
    private String idmere;
    private String idproduit;
    private String designation;
    private double quantite;
    private double pu;
    private double tva;
    private double qtestock;

    public DmdAchatFille() throws Exception {
        this.setNomTable("DMDACHATFILLE");
        this.setNomClasseMere("faturefournisseur.DmdAchat");
        this.setLiaisonMere("idmere");
    }

    @Override
    public String getNomClasseMere()
    {
        return "faturefournisseur.DmdAchat";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getQtestock() {
        return qtestock;
    }

    public void setQtestock(double qtestock) {
        this.qtestock = qtestock;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("DMDAF", "GETSEQDMDACHATFILLE");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}
