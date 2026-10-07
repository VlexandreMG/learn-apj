/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package faturefournisseur;

import bean.*;
import bean.ClassMAPTable;
import produits.*;
import bean.CGenUtil;
import magasin.Magasin;
import java.sql.Connection;
import annexe.Produit;
import utilitaire.Utilitaire;

/**
 *
 * @author micha
 */
public class As_BonDeCommande_Fille extends ClassFille{
        
    private String id;
    private String produit,idproduit;
    private String idbc;
    private double quantite;
    private double pu;
    private double montant;
    private double tva;
    private String unite,idunite;
    private double remise;
    private String idDevise;
    private double tauxDeChange; 
    private double montantTva,montantHT,montantTTC;
    private String produitLib;

    public As_BonDeCommande_Fille() throws Exception {
        super.setNomTable("AS_BONDECOMMANDE_FILLE");
        this.setLiaisonMere("idbc");
        this.setNomClasseMere("faturefournisseur.As_BonDeCommande");
    }

//    @Override
//    public String getLiaisonMere() {
//        return "idbc";
//    }
//
//    @Override
//    public String getNomClasseMere() {
//        return "faturefournisseur.As_BonDeCommande";
//    }

    public FactureFournisseurDetails toFactureFournisseurDetails() throws Exception{
        FactureFournisseurDetails v = new FactureFournisseurDetails();
        v.setIdProduit(this.getProduit());
        v.setQte(this.getQuantite());
        v.setIdDevise(this.getIdDevise());
        v.setPu(this.getPu());
        IngredientsLib ing = (IngredientsLib)new IngredientsLib().getById(this.getProduit(),"ST_INGREDIENTSAUTOACHAT_CPL",null);
        v.setCompte(ing.getCompte_achat());
        v.setDesignation(ing.getLibelle());
        v.setTva(this.getTva());
        v.setIdbcDetail(this.getId());
        return v;
    }

    public As_BonDeCommande_Fille(String id, String produit, String idbc, double quantite, double pu, double montant, double tva, String unite, double remise) throws Exception{
        super.setNomTable("AS_BONDECOMMANDE_FILLE");
        this.setLiaisonMere("idbc");
        this.setNomClasseMere("faturefournisseur.As_BonDeCommande");
        this.setProduit(produit);
        this.setIdbc(idbc);
        this.setQuantite(quantite);
        this.setPu(pu);
        this.setMontant(montant);
        this.setTva(tva);
        this.setUnite(unite);
        this.setRemise(remise);
    }

    public void construirePK(Connection c) throws Exception {
        this.preparePk("BCF", "GETSEQBONDECOMMANDEFILLE");
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduit() {
        return this.produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getIdbc() {
        return this.idbc;
    }

    public void setIdbc(String idbc) throws Exception{
       // if(idbc.compareToIgnoreCase("") == 0 || idbc == null) throw new Exception("ID Parent introuvable");
        this.idbc = idbc;
    }

    public double getQuantite()  {
        // if(this.quantite <=0 && this.getMode().equals("modif")){
        //     throw new Exception("Quantite negatif ou 0");
        // }
        return this.quantite;
    }

    public void setQuantite(double quantite) throws Exception {
        if (this.getMode().equals("modif") && quantite <= 0) {
            throw new Exception("La quantit\u00e9 ne peut pas \u00eatre inf\u00e9rieure ou \u00e9gale \u00e0 0");
        }
        this.quantite = quantite;
    }

    public double getPu() {
        return this.pu;
    }

    public void setPu(double pu) throws Exception {
        if (this.getMode().equals("modif") && pu < 0) {
            throw new Exception("PU ne peut pas \u00eatre inf\u00e9rieur \u00e0 0");
        }
        this.pu = pu;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTva() {
        return this.tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public String getUnite() {
        return this.unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public double getRemise() {
        return this.remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public double getTauxDeChange() {
        return tauxDeChange;
    }

    public void setTauxDeChange(double tauxDeChange) throws Exception {
        if(getMode().compareTo("modif")==0 && tauxDeChange<=0)throw new Exception("Taux de change invalide");
        this.tauxDeChange = tauxDeChange;
    }

    public String getIdunite() {
        return idunite;
    }

    public void setIdunite(String idunite) {
        this.idunite = idunite;
    }

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
    }

    public double getMontantTva() {
        return montantTva;
    }

    public void setMontantTva(double montantTva) {
        this.montantTva = montantTva;
    }

    public double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(double montantHT) {
        this.montantHT = montantHT;
    }

    public double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public String getProduitLib() {
        return produitLib;
    }

    public void setProduitLib(String produitLib) {
        this.produitLib = produitLib;
    }

    @Override
    public String getNomClasseMere() {
        return "faturefournisseur.As_BonDeCommande";
    }
    @Override
    public String getLiaisonMere() {
        return "idbc";
    }

    @Override
    public void controler(Connection c) throws Exception {
        Ingredients p = new Ingredients();
        p.setId(this.produit);
        Ingredients[] produit = (Ingredients[])CGenUtil.rechercher(p,null, null, c, "");
        this.setUnite(produit[0].getUnite());
        super.controler(c);
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        
        Ingredients p = new Ingredients();
        p.setId(this.produit);
        Ingredients[] produit = (Ingredients[])CGenUtil.rechercher(p,null, null, c, "");
        this.setUnite(produit[0].getUnite());
        return super.createObject(u, c);
    }
    
    public As_BonDeLivraison_Fille createBLFille(String idMere) throws Exception{
            As_BonDeLivraison_Fille resultat = new As_BonDeLivraison_Fille();
            resultat.setIdbc_fille(this.getId());
            resultat.setProduit(this.getIdproduit());
            resultat.setNumbl(idMere);
            resultat.setQuantite(this.getQuantite());
            resultat.setUnite(this.getIdunite());

            return resultat;
    }
    
    public double calculerTva(){
        double montantTva = 0;
        try {
            montantTva = (this.getPu() * this.getQuantite()* this.getTva()) / 100;
            this.setMontantTva(montantTva);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return montantTva;
    }
    
    public double calculerHT(){
        double montantHT = 0;
        try {
            montantHT = this.getPu() * this.getQuantite();
            this.setMontantHT(montantHT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return montantHT;
    }
    
    public double calculerTTC(){
        double montantTTC = this.getMontantHT() + this.getMontantTva();
        this.setMontantTTC(montantTTC);
        return montantTTC;
    }

    public void controle(Connection c, String idDmdAchat) throws Exception
    {

        DmdAchatSuiviCommande dasc = new DmdAchatSuiviCommande();
        dasc.setIdDmdAchat(idDmdAchat);
        dasc.setProduit(this.getProduit());

        DmdAchatSuiviCommande[] dmdAchatSuiviCommandes = (DmdAchatSuiviCommande[]) CGenUtil.rechercher(dasc, null, null, c, "");

        if (dmdAchatSuiviCommandes == null || dmdAchatSuiviCommandes.length == 0)
        {
            throw new Exception("Aucune ligne de suivi de commande trouv\u00e9e pour cette demande d'achat et ce produit.");
        }

        if (this.getQuantite() > dmdAchatSuiviCommandes[0].getResteACommander())
        {
            throw new Exception("La quantit\u00e9 saisie est inf\u00e9rieure au reste \u00e0 commander pour ce produit.");
        }

    }

}
