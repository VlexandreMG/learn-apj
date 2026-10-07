/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;


import inventaire.Inventaire;
import inventaire.InventaireFille;
import java.sql.Date;

public class MvtStockFilleLib extends MvtStockFille{
    private String idProduitlib, idVenteDetaillib, idTransfertDetaillib , libelleexacte, idMagasinLib, idMagasin, mvtsrc;
    private String libelle;
    private Date daty;
    private Date dateSql;
    private String idObjet;
    private  String idOfFille;

    String etatlib;

    


    public MvtStockFilleLib() throws Exception{
        setNomTable("mvtstockfillelib");
    }

    public String getIdOfFille() {
        return idOfFille;
    }

    public void setIdOfFille(String idOfFille) {
        this.idOfFille = idOfFille;
    }

    public String getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(String idObjet) {
        this.idObjet = idObjet;
    }

    public Date getDateSql() {
        return dateSql;
    }

    public void setDateSql(Date dateSql) {
        this.dateSql = dateSql;
    }

    public String getMvtsrc() {
        return mvtsrc;
    }

    public void setMvtsrc(String mvtsrc) {
        this.mvtsrc = mvtsrc;
    }

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

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdProduitlib() {
        return idProduitlib;
    }

    public void setIdProduitlib(String idProduitlib) {
        this.idProduitlib = idProduitlib;
    }

    public String getIdVenteDetaillib() {
        return idVenteDetaillib;
    }

    public void setIdVenteDetaillib(String idVenteDetaillib) {
        this.idVenteDetaillib = idVenteDetaillib;
    }

    public String getIdTransfertDetaillib() {
        return idTransfertDetaillib;
    }

    public void setIdTransfertDetaillib(String idTransfertDetaillib) {
        this.idTransfertDetaillib = idTransfertDetaillib;
    }

    public String getLibelleexacte() {
        return libelleexacte;
    }

    public void setLibelleexacte(String libelleexacte) {
        this.libelleexacte = libelleexacte;
    }


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","libelleexacte","pu","daty"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"id","libelleexacte","daty"};
        return valMotCles;
    }
    public Inventaire genererInventaire() throws Exception {
            Inventaire inv =new Inventaire();
            inv.setIdMagasin(this.getIdMagasin());
            InventaireFille[] fille = new InventaireFille[1];
            fille[0]=new InventaireFille();
            fille[0].setIdProduit(this.getIdProduit());
            fille[0].setMvtsrc(this.getMvtsrc());
            fille[0].setQuantite(0);
            inv.setFille(fille);
            return inv;
    }

    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
}
