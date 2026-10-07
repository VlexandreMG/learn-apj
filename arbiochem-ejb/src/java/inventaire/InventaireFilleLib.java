/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire;

import java.sql.Date;


public class InventaireFilleLib extends InventaireFille{
    private String idproduitlib,libelleexacte;
    private double ecart , montantTheorique , montantReelle ,ecartMontant ;
    private Date dateInv;
    private String uniteLib;
    private String idMagasin, idMagasinLib;

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public String getUniteLib() {
        return uniteLib;
    }

    public void setUniteLib(String uniteLib) {
        this.uniteLib = uniteLib;
    }

    public double getMontantTheorique() {
        return montantTheorique;
    }

    public void setMontantTheorique(double montantTheorique) {
        this.montantTheorique = montantTheorique;
    }

    public double getMontantReelle() {
        return montantReelle;
    }

    public void setMontantReelle(double montantReelle) {
        this.montantReelle = montantReelle;
    }

    public double getEcartMontant() {
        return ecartMontant;
    }

    public void setEcartMontant(double ecartMontant) {
        this.ecartMontant = ecartMontant;
    }

    public Date getDateInv() {
        return dateInv;
    }

    public void setDateInv(Date dateInv) {
        this.dateInv = dateInv;
    }

    
    public double getEcart() {
        return ecart;
    }

    public void setEcart(double ecart) {
        this.ecart = ecart;
    }

    public InventaireFilleLib() throws Exception{
        this.setNomTable("InventaireFilleLib");
    }

    public String getIdproduitlib() {
        return idproduitlib;
    }

    public void setIdproduitlib(String idproduitlib) {
        this.idproduitlib = idproduitlib;
    }

    public String getLibelleexacte() {
        return libelleexacte;
    }

    public void setLibelleexacte(String libelleexacte) {
        this.libelleexacte = libelleexacte;
    }

    
}
