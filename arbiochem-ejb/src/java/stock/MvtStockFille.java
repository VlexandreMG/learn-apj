/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import bean.AdminGen;
import bean.ClassFille;
import bean.CGenUtil;
import bean.ClassMAPTable;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import chatbot.AiTabDesc;
import chatbot.ClassIA;
import produits.Ingredients;
import java.sql.Statement;

import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import utils.ConstanteSocobis;
import utils.ConstanteStation;
import vente.As_BondeLivraisonClientFille;
import vente.As_BondeLivraisonClientFille_Cpl;
import chatbot.AiColDesc;

@AiTabDesc("La table MvtStockFille représente les mouvements de stock pour les stocks, fabrications et vente")
public class MvtStockFille extends ClassFille implements ClassIA {
    @AiColDesc("id du ordre de fabrication fille, commence par OFF")
    private String id;
    private String idMvtStock, idVenteDetail, idTransfertDetail,designation,idFab,idOf,categorieIngredient,idOff,autocompl, idUniteLib;
    @AiColDesc("le produits qui a un mouvement de stock soit en entrée soit en sortie")
    private String idProduit;
    @AiColDesc("la quantité entrées du produits pour chaque mouvement de stock")
    private double entree;
    @AiColDesc("la quantité sortie du produits pour chaque mouvement de stock")
    private double sortie;
    private double pu,montant;
    private String mvtSrc;
    private double reste;
    private String idinventairefille;
    java.sql.Date dateInventaire;
    double qteInventaire;
    String compte_stock;
    String compte_sortie_stock;
    EtatStock dernierEtatStock;
    Ingredients ingredients;

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public EtatStock getDernierEtatStock() {
        return dernierEtatStock;
    }

    public void setDernierEtatStock(EtatStock dernierEtatStock) {
        this.dernierEtatStock = dernierEtatStock;
    }

    public double getMontantCalc()
    {
        return this.getPu()*(this.getEntree()+this.getSortie());
    }
    public String getCompte_sortie_stock() {
        return compte_sortie_stock;
    }

    public void setCompte_sortie_stock(String compte_sortie_stock) {
        this.compte_sortie_stock = compte_sortie_stock;
    }

    public String getCompte_stock() {
        return compte_stock;
    }

    public void setCompte_stock(String compte_stock) {
        this.compte_stock = compte_stock;
    }


    public Date getDateInventaire() {
        return dateInventaire;
    }

    public void setDateInventaire(Date dateInventaire) {
        this.dateInventaire = dateInventaire;
    }

    public double getQteInventaire() {
        return qteInventaire;
    }

    public void setQteInventaire(double qteInventaire) throws Exception{
        if ((this.getMode().compareToIgnoreCase("modif")==0||this.getMode().compareToIgnoreCase("insert")==0) && qteInventaire < 0) {
            throw new Exception("Quantit\u00E9 ne peut pas &ecirc;tre inf\u00E9rieur &agrave; 0");
        }
        this.qteInventaire = qteInventaire;
    }

    @Override
    public String getNomTableIA() {
        return "mvtstockfillelib";
    }

    @Override
    public String getUrlSaisie() {
        return "/socobis/pages/module.jsp?but=stock/mvtstock-saisie.jsp&currentMenu=MNDN000000001071";
    }
    @Override
    public ClassIA getClassSaisie() {
        return this;
    }
    @Override
    public String getUrlListe() {
        return "/socobis/pages/module.jsp?but=stock/mvtstockfille-liste.jsp&currentMenu=MNDN000000007";
    }
    @Override
    public String getUrlAnalyse() {
        return "/socobis/pages/module.jsp?but=stock/mvtstockfille-liste.jsp&currentMenu=MNDN0000000111";
    }
    @Override
    public ClassIA getClassListe() {
        return this;
    }
    @Override
    public ClassIA getClassAnalyse() {
        return this;
    }


    public String getIdinventairefille() {
        return idinventairefille;
    }

    public String getIdUniteLib() {
        return idUniteLib;
    }

    public void setIdUniteLib(String idUniteLib) {
        this.idUniteLib = idUniteLib;
    }

    public String getAutocompl() {
        return autocompl;
    }

    public void setAutocompl(String autocompl) {
        this.autocompl = autocompl;
    }

    public void setIdinventairefille(String idinventairefille) {
        this.idinventairefille = idinventairefille;
    }

    public String getIdFab() {
        return idFab;
    }

    public String getIdOff() {
        return idOff;
    }

    public void setIdOff(String idOff) {
        this.idOff = idOff;
    }

    public String getIdOf() {
        return idOf;
    }

    public void setIdOf(String idOf) {
        this.idOf = idOf;
    }

    public String getCategorieIngredient() {
        return categorieIngredient;
    }

    public void setCategorieIngredient(String categorieIngredient) {
        this.categorieIngredient = categorieIngredient;
    }

    public void setIdFab(String idFab) {
        this.idFab = idFab;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) throws Exception {
        if ((this.getMode().compareToIgnoreCase("modif")==0||this.getMode().compareToIgnoreCase("insert")==0) && pu < 0) {
            throw new Exception("Prix unitaire ne peut pas &ecirc;tre inf\u00E9rieur &agrave; 0");
        }
        this.pu = pu;
    }

    @Override
    public boolean isSynchro(){
        return true;
    }
    
    public MvtStockFille() throws Exception{
        setNomTable("MvtStockFille");
        this.setLiaisonMere("idMvtStock");
        this.setNomClasseMere("stock.MvtStock");
    }

    public String getNomClasseMere(){
      return "stock.MvtStock";
    }

    public String getLiaisonMere(){
      return "idMvtStock";
    }

    public MvtStockFille contrer()throws Exception{
        MvtStockFille stockFille = (MvtStockFille)this.dupliquerSansBase();
        if(this.getEntree()>0)
        {
            stockFille.setSortie(this.getEntree());
            stockFille.setEntree(0);
        }
        else if(this.getSortie()>0){
            stockFille.setEntree(this.getSortie());
            stockFille.setSortie(0);
        }
        return stockFille;
    }

    public String getDesignation() {
        if(designation==null&&this.getMode().compareToIgnoreCase("select")==0) return "";
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception{
        this.id = id;
    }

    public String getIdMvtStock() {
        return idMvtStock;
    }

    public void setIdMvtStock(String idMvtStock) {
        this.idMvtStock = idMvtStock;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) throws Exception{
        if((this.getMode().compareToIgnoreCase("modif")==0||this.getMode().compareToIgnoreCase("insert")==0) && (idProduit==null || idProduit.isEmpty()==true))throw new Exception("Veuillez entrer un produit");
        this.idProduit = idProduit;
    }

    public String getIdVenteDetail() {
        return idVenteDetail;
    }

    public void setIdVenteDetail(String idVenteDetail) {
        this.idVenteDetail = idVenteDetail;
    }

    public String getIdTransfertDetail() {
        return idTransfertDetail;
    }

    public void setIdTransfertDetail(String idTransfertDetail) {
        this.idTransfertDetail = idTransfertDetail;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) throws Exception {
        if((this.getMode().compareToIgnoreCase("modif")==0||this.getMode().compareToIgnoreCase("insert")==0) && entree<0)throw new Exception("Valeur de l'entree invalide");
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) throws Exception {
        if((this.getMode().compareToIgnoreCase("modif")==0||this.getMode().compareToIgnoreCase("insert")==0) && sortie<0)throw new Exception("Valeur de la sortie invalide");
        this.sortie = sortie;
    }
    public MvtStockFille[] getMvtFromSrc(MvtStockFille mvtF)throws Exception{
        Connection c=null;
        boolean canClose=false;
        try {
            c=new UtilDB().GetConn();
            canClose=true;
            MvtStockFille mvtfille = new MvtStockFille();
            mvtfille.setMvtSrc(mvtF.getId());
            MvtStockFille[] lmvt=(MvtStockFille[]) CGenUtil.rechercher(mvtfille, null, null, c, "");
            return lmvt;
        } catch (Exception e) {
            throw e;
        }
        finally {
            if(canClose){
                c.close();
            }
        }
    }
    public void updatePrice(MvtStockFille[] lmvtFille, MvtStockFille mvtFille, double price)throws Exception{
        Connection c=null;
        boolean canClose=false;
        try {
            c=new UtilDB().GetConn();
            canClose=true;
            lmvtFille = getMvtFromSrc(mvtFille);
            for (int i = 0; i < lmvtFille.length; i++) {
                lmvtFille[i].setPu(price);
                lmvtFille[i].updateToTable(c);
            }
            mvtFille.setPu(price);
            mvtFille.updateToTable(c);
        } catch (Exception e) {
            throw e;
        }
        finally {
            if(canClose){
                c.close();
            }
        }
    }


    
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MVTSFI", "GETSEQMVTSTOCKFILLE");
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
    
    @Override
    public void setLiaisonMere(String liaisonMere) {
        super.setLiaisonMere("idMvtStock");
    }

    @Override
    public void controlerUpdate(Connection c) throws Exception {
        super.setNomClasseMere("stock.MvtStock");
        this.controlerSaisie();
        this.controllerMvtSrc(c);
        super.controlerUpdate(c);
    }

    @Override
    public void controler(Connection c) throws Exception{
        this.controlerSaisie();
        this.controllerMvtSrc(c);
        /**if( this.getSortie() > 0 && this.getEntree() == 0 ){
            EtatStock[] etats = (EtatStock[])CGenUtil.rechercher( new EtatStock(), null,null, c, " and id='" + this.getIdProduit() + "' and idMagasin='"+((MvtStock)this.getMere()).getIdMagasin()+"'");
            EtatStock etat = etats[0];t
            if( etat.getReste() <= 0 || etat.getReste() < this.getSortie() ){
                throw new Exception("Veuillez rentrez le produit " + this.getIdProduit() + " : stock insuffisant");
            }
        }*/
        Ingredients ing=this.getIngredient(c);
        if (ing.getTypeStock()==null || ing.getTypeStock()=="") {
            throw new Exception(String.format("Ce produit %s n pas de type de stock",ing.getId()));
        }
        if(this.getSortie()>0 && this.getEntree() == 0)
        {   
            /*EtatStock e = new EtatStock();
            e.setNomTable("V_ETATSTOCK_ING");
            e.setId(this.getIdProduit());
            MvtStock mere = this.getMereMvtStock(c);
            e.setIdMagasin(mere.getIdMagasin());
            EtatStock[] etats=null;// = (EtatStock[])CGenUtil.rechercher(e, null,null, c,"");
            EtatStock etat=null;
            if(etats!=null) etat= etats[0];*/
            /*if( etat.getReste() <= 0 || etat.getReste() < this.getSortie() ){
                throw new Exception("Stock de "+etat.getIdProduitLib()+" insuffisant: "+etat.getReste());
            }*/
            
        }
        //controllerQteLivraison(c);
    }
    public As_BondeLivraisonClientFille_Cpl getBondeLivraisonClientFille(Connection c) throws Exception{
        As_BondeLivraisonClientFille_Cpl blf= new As_BondeLivraisonClientFille_Cpl();
        blf.setIdventedetail(this.getIdVenteDetail());
        As_BondeLivraisonClientFille_Cpl[] As_BondeLivraisonClientFille= (As_BondeLivraisonClientFille_Cpl[]) CGenUtil.rechercher(blf,null,null,c, "");
        if(As_BondeLivraisonClientFille.length>0 || blf!=null){
            return As_BondeLivraisonClientFille[0];
        }
        return null;
    }
    public void controllerQteLivraison(Connection c) throws Exception{
        As_BondeLivraisonClientFille_Cpl blf=getBondeLivraisonClientFille(c);
        if(this.sortie>blf.getQteResteALivrer()){
            throw new Exception( blf.getIdproduitlib()+ " : quantité supérieure au reste à livrer");
            }
    }
    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        return super.createObject(u, c);
    }



    public void controlerSaisie() throws Exception{
        if(this.getIdProduit() == null || this.getIdProduit().isEmpty()){
            throw new Exception("Veuillez entrer un produit");
        }
        if(this.getEntree() <= 0 && this.getSortie() <=0){
            throw new Exception("Verifier la quantite sur entree et sortie!");
        }
    }

     public MvtStock getMereMvtStock(Connection c) throws Exception {
        /*if (c == null) {
            throw new Exception("Connection non etablie");
        }*/
        MvtStock m = new MvtStock();
        m.setId(this.getIdMvtStock());
        MvtStock[] listes = (MvtStock[]) CGenUtil.rechercher(m, null, null, c, "");
        if (listes.length > 0) {
            return listes[0];
        }
        return null;
    }

    public Ingredients getIngredient(Connection c) throws Exception {
        if(this.getIngredients()!=null)return this.getIngredients();
        Ingredients search = new Ingredients();
        search.setId(this.getIdProduit());
        Ingredients[] result = (Ingredients[]) CGenUtil.rechercher(search,null,null,c,"");
        if (result.length == 0) {
            throw new Exception("Ingredients avec id : " + this.getIdProduit() + " introuvable");
        }
        this.setIngredients(result[0]);
        return result[0];
    }

    public void updateQteIngredient(String u, Connection c)throws Exception{
        Ingredients ing = this.getIngredient(c);
        ing.setReste(ing.getReste()+this.getEntree());
        ing.setReste(ing.getReste()-this.getSortie());
        ing.updateToTableWithHisto(u, c);
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public String getMvtSrc() {
        return mvtSrc;
    }

    public void setMvtSrc(String mvtSrc) {
        this.mvtSrc = mvtSrc;
    }

    public double getQuantite(Connection c, MvtStock mere) throws Exception {
        EtatStockParEntree search = new EtatStockParEntree();
        search.setId(this.getIdProduit());
        search.setIdMagasin(mere.getIdMagasin());
        if(mere.getIdTypeMvStock().compareTo(ConstanteStation.TYPEMVTSTOCKINVENTAIRE)==0){
            search.setNomTable("V_ETATSTOCK_ING_ALL");
        }else if(this.getMvtSrc() != null && !this.getMvtSrc().isEmpty()){
            search.setNomTable("V_ETATSTOCK_ENTREE_STANDARD");
            search.setId(this.getMvtSrc());
            search.setIdProduit(this.getIdProduit());
        }
        else
        {
            EtatStock crt=new EtatStock();
            crt.setIdProduit(this.getIdProduit());
            crt.setIdMagasin(mere.getIdMagasin());
            EtatStock[] etat=(EtatStock[]) CGenUtil.rechercher(crt,null,null,c,"");
            if(etat.length>0&&etat[0]!=null){
                double retour= etat[0].getReste();
                return retour;
            }
            return 0;
        }

        EtatStockParEntree[] retour = (EtatStockParEntree[]) CGenUtil.rechercher(search,null,null, c,"");
        if (retour.length == 0) {
            throw new Exception("Mouvement Stock Entree avec id produit : " + this.getIdProduit() + " introuvable");
        }
        return retour[0].getReste();
    }

    public EtatStock getMonEtatStock(Connection c) throws Exception
    {
        MvtStock mer=(MvtStock) this.getMere();
        EtatStock[] etatStock=mer.findEtatStockGlobale(c);
        String[] col={"idProduit"};
        String[] val={this.getIdProduit()};
        EtatStock[] monEtatStock=(EtatStock[]) AdminGen.find(etatStock,col,val);
        if(monEtatStock!=null&&monEtatStock.length>0)return monEtatStock[0];
        return null;
    }
    public EtatStockParEntree getMonEtatStockParEntree(Connection c) throws Exception
    {
        MvtStock mer=(MvtStock) this.getMere();
        EtatStockParEntree[] etatStock=mer.findEtatStockEntree(c);
        String[] col={"id"};
        String[] val={this.getMvtSrc()};
        EtatStockParEntree[] monEtatStock=(EtatStockParEntree[]) AdminGen.find(etatStock,col,val);
        if(monEtatStock!=null&&monEtatStock.length>0)return monEtatStock[0];
        return null;
    }
    public double getReste(Connection c,MvtStock mere) throws Exception {
        if(this.getMvtSrc()==null||this.getMvtSrc().compareToIgnoreCase("")==0) {
            /*EtatStockParEntree search = new EtatStockParEntree();
            search.setNomTable("ETATSTOCKSIMPLE");
            search.setIdProduit(this.getIdProduit());
            search.setIdMagasin(mere.getIdMagasin());
            EtatStockParEntree[] retour = (EtatStockParEntree[]) CGenUtil.rechercher(search, null, null, c, "");
            if (retour == null || retour.length == 0) return 0;
            return retour[0].getReste();*/
            EtatStock es=this.getMonEtatStock(c);
            if(es!=null) return es.getReste();
        }
        else
        {
            EtatStockParEntree esp=this.getMonEtatStockParEntree(c);
            if(esp!=null)return esp.getReste();
        }
        return 0;
    }

    public boolean estSuffisant(Connection c, MvtStock mere) throws Exception {
        if (this.getSortie()>this.getQuantite(c, mere)) {
            return false;
        }
        return true;
    }

    public MvtStockFille[] genererNouveauMvt(MvtStockEntreeAvecReste[] stockReste) throws Exception {
        List<MvtStockFille> retourList = new ArrayList<>();
        double quantite = this.getSortie();
            for (int i = 0; i < stockReste.length && quantite>0; i++) {
                double reste = stockReste[i].getQuantite() - quantite;
                if (reste < 0) {
                    reste = 0;
                }
                double sortie= stockReste[i].getQuantite() - reste;
                MvtStockFille mvtFille = new MvtStockFille();
                mvtFille.setIdMvtStock(this.getIdMvtStock());
                mvtFille.setIdProduit(this.getIdProduit());
                mvtFille.setSortie(sortie);
                mvtFille.setMvtSrc(stockReste[i].getId());
                mvtFille.setPu(stockReste[i].getPu());
                quantite = quantite - mvtFille.getSortie();
                retourList.add(mvtFille);
            }
        return retourList.toArray(new MvtStockFille[0]);
    }

    public void updateQteIngredientBatch(String u, Statement st)throws Exception{
        Ingredients ing = this.getIngredient(st.getConnection());
        ing.setReste(ing.getReste()+this.getEntree());
        ing.setReste(ing.getReste()-this.getSortie());
        ing.updateToTableWithHistoBatch(u, st);
    }

    public void controllerMvtSrc(Connection c)throws Exception{
        boolean estOuvert = false;
        try {
            if (c == null) {
                estOuvert = true;
                c = new UtilDB().GetConn();
            }
            Ingredients ing = this.getIngredient(c);
            if (ing.getTypeStock().compareToIgnoreCase("LOT")==0 && this.getMvtSrc() == null) {
                throw new Exception("Mouvement source obligatoire pour le produit g\u00E9r\u00E9 par Lot");
            }
            if(this.getMvtSrc() != null && !this.getMvtSrc().isEmpty()){
                if(this.getMvtSrc().startsWith("MVTSF")==false)throw new Exception("Mouvement source non vide mais non valide");
                EtatStockParEntreeStandard es=new EtatStockParEntreeStandard();
                EtatStockParEntreeStandard rep=(EtatStockParEntreeStandard)es.getById(this.getMvtSrc(),null,c);
                if(rep!=null&&this.getIdProduit().compareToIgnoreCase(rep.getIdProduit())!=0)
                    throw new Exception("Le produit dans le mouvement source "+ rep.getId()+" ne correspondant pas au produit dans " + this.getMvtSrc());
                if(rep==null) throw new Exception("Mouvement source non trouve ou n est plus en stock");
                if(rep!=null&&rep.reste<this.getSortie())throw new Exception("Quantite superieur au reste du mouvement source");
                /*MvtStockEntreeAvecReste map = new MvtStockEntreeAvecReste();

                MvtStockEntreeAvecReste rep = (MvtStockEntreeAvecReste) map.getById(this.getMvtSrc(), "V_ETATSTOCK_ENTREE", c);
                if(rep != null) {
                    if(this.getIdProduit().compareToIgnoreCase(rep.getIdProduit()) != 0){
                        throw new Exception("Le produit dans le mouvement source "+ rep.getId()+" ne correspondant pas au produit dans " + this.getMvtSrc());
                    }
                }*/
            }/*else{
                MvtStock mere = this.getMereMvtStock(c);
                if(mere!=null &&  mere.getIdTypeMvStock() != null && mere.getIdTypeMvStock().compareTo(ConstanteSocobis.TYPE_MVT_SORTIE) == 0){
                    TransfertStock trans = null;
                    if (mere.getIdTransfert() != null) {
                        trans = (TransfertStock) new TransfertStock().getById(mere.getIdTransfert(), "transfertstock", c);
                    }

                    Ingredients ingredients = this.getIngredient(c);
                    if ((trans == null || trans.getIsvente() != 1)
                        && ingredients != null
                        && ingredients.getCategorieIngredient() != null
                        && !ingredients.getCategorieIngredient().equalsIgnoreCase(ConstanteSocobis.CATEGORIE_CONSOMMABLE)
                        && !ingredients.getCategorieIngredient().equalsIgnoreCase(ConstanteSocobis.CATEGORIE_PRODUIT_FINI)
                        && !ingredients.getCategorieIngredient().equalsIgnoreCase(ConstanteSocobis.CATEGORIE_MAINTENANCE)
                        && ingredients.getTypeStock().compareToIgnoreCase("CUMP")!=0)
                    {
                        throw new Exception("Le mouvement source ne peut pas être vide");
                    }
                }
            }*/
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null && estOuvert == true) {
                c.close();
            }
        }
    }


    public static Map<String, Double> getDataChartCheese() throws Exception {
        Map<String, Double> dataChart = new HashMap<String, Double>();
        String req = "select sum(ENTREE) as entree, sum(SORTIE) as sortie from MVTSTOCKFILLELIB where IDPRODUIT = 'ING000T0108'";
        MvtStockFille[] etat = (MvtStockFille[]) CGenUtil.rechercher(new MvtStockFille(), req);
        dataChart.put("Entree", (double) etat[0].getEntree());
        dataChart.put("Sortie", (double) etat[0].getSortie());
        return dataChart;
    }

    public static Map<String, Double> getDataChartBar() throws Exception {
        Map<String, Double> dataChart = new LinkedHashMap<>();
        String req = "SELECT *\n" +
                "FROM (\n" +
                "    SELECT IDPRODUIT, IDPRODUITLIB, SUM(SORTIE) AS sortie\n" +
                "    FROM MVTSTOCKFILLELIB\n" +
                "    GROUP BY IDPRODUIT, IDPRODUITLIB\n" +
                ")\n" +
                "WHERE ROWNUM <= 50";
        MvtStockFilleLib[] etat = (MvtStockFilleLib[]) CGenUtil.rechercher(new MvtStockFilleLib(), req);
        for (MvtStockFilleLib charge : etat) {
            dataChart.put(charge.getIdProduitlib(), charge.getSortie());
        }

        return dataChart;
    }

    public MvtStockEntreeAvecReste[] getMvtSelonType(Connection c,String apres) throws Exception {
        MvtStockEntreeAvecReste search = new MvtStockEntreeAvecReste();
        MvtStock mere=(MvtStock)this.getMere();
        search.setIdMagasin(mere.getIdMagasin());
        search.setIdProduit(this.getIdProduit());
        return  (MvtStockEntreeAvecReste[]) CGenUtil.rechercher(search,null, null, apres);
    }


    public void validerObjectSortieFIFOLIFO(String u, Connection c, String ordre) throws Exception
    {
        List<MvtStockFille> mvtstockfillenew= new ArrayList<MvtStockFille>();
        if(ordre==null||ordre.compareToIgnoreCase("")==0) ordre=" order by daty asc,id asc ";
        MvtStockEntreeAvecReste[] listeMvt=getMvtSelonType(c,ordre);
        double sommeReste= AdminGen.calculSommeDouble(listeMvt,"reste");
        if(this.getSortie()>sommeReste) throw new Exception("Le stock est suffisant pour le magasin choisi");
        double reste=this.getSortie();
        for(int i=0;reste>0;i++)
        {
            MvtStockFille temp=(MvtStockFille) this.dupliquerSansBase();
            temp.setSortie(Math.min(listeMvt[i].getReste(),reste));
            temp.construirePK(c);
            temp.setMvtSrc(listeMvt[i].getId());
            temp.setPu(listeMvt[i].getPu());
            temp.createObject(u,c);
            reste=reste-temp.getSortie() ;
            mvtstockfillenew.add(temp);
        }
        this.deleteToTableWithHisto(u,c);
        MvtStockFille[]lf=new MvtStockFille[mvtstockfillenew.size()];
        mvtstockfillenew.toArray(lf);
        this.getMere().setFille(lf);
    }

    public Object validerObject(String u, Connection c) throws Exception
    {
        if((this.getIngredient(c).getTypeStock().compareToIgnoreCase("FIFO")!=0&&this.getIngredient(c).getTypeStock().compareToIgnoreCase("LIFO")!=0
            &&this.getIngredient(c).getTypeStock().compareToIgnoreCase("LOT")!=0)
            ||(this.getMvtSrc()!=null&&this.getMvtSrc().compareToIgnoreCase("")!=0&&this.getMvtSrc().compareToIgnoreCase("CUMP")!=0)) return null;
        if(this.getEntree()>0&&this.getSortie()==0)
            return validerObjectEntree(u,c);
        return validerObjectSortie(u,c);
    }
    public Object validerObjectEntree(String u, Connection c) throws Exception
    {
        this.setReste(this.getEntree());
        return this.getMere();
    }
    public Object validerObjectSortie(String u, Connection c) throws Exception
    {
        if(this.getMvtSrc()!=null&&this.getMvtSrc().compareToIgnoreCase("")!=0)
        {
            return this.getMere();
        }
        Ingredients ing=this.getIngredient(c);
        String apres=" order by daty asc,id asc ";
        MvtStock mere=(MvtStock) this.getMere();
        if(ing.getTypeStock().compareToIgnoreCase("LIFO")==0) apres=" order by daty desc,id desc ";;
        validerObjectSortieFIFOLIFO(u,c,apres);
        return mere;
    }
}
