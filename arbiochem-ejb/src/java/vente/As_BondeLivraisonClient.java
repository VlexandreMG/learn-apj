package vente;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ClassMere;
import chatbot.AiColDesc;
import chatbot.AiTabDesc;
import chatbot.ClassIA;
import constante.ConstanteEtat;

import java.sql.Connection;
import java.sql.Date;
import stock.MvtStock;
import stock.MvtStockFille;
import utilitaire.Utilitaire;
import utils.ConstanteEtatStation;
import utils.ConstanteStation;
import utilitaire.UtilDB;   

import java.sql.Date;
import utils.ConstanteSocobis;

@AiTabDesc("La table AS_BONDELIVRAISON_CLIENT représente les bons de livraison clients, contenant les informations d'entête telles que le client, la date, l'état, le mode de paiement, la devise, le magasin, les frais et modalités de livraison.")
public class As_BondeLivraisonClient extends ClassMere implements ClassIA {
    String id , remarque;
    @AiColDesc("id de la bon de commande d'où vient cette livraison")
    String idbc;
    String magasin,idvente,idorigine, idclient;
    @AiColDesc("date de livraison de la commande")
    Date daty;
    @AiColDesc("l'état de la livraison : quand l'état est égale à 1 il est créer donc en cours et si il est égale à 11 alors il a été validé ou finalisé")
    int etat;
    String idClient,vehicule,chauffeur,description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    @Override
    public String getNomTableIA() {
        return "AS_BONDELIVRAISON_CLIENT_CPL";
    }

    @Override
    public String getUrlSaisie() {
        return "/socobis/pages/module.jsp?but=vente/bondelivraison/bondelivraison-saisie.jsp&currentMenu=MNDN000000001071";
    }
    @Override
    public ClassIA getClassSaisie() {
        return this;
    }
    @Override
    public String getUrlListe() {
        return "/socobis/pages/module.jsp?but=bondelivraison/bondelivraison-liste.jsp&currentMenu=MNDN000000007";
    }
    @Override
    public String getUrlAnalyse() {
        return "/socobis/pages/module.jsp?but=bondelivraison/bondelivraison-analyse.jsp&currentMenu=MNDN0000000111";
    }
    @Override
    public ClassIA getClassListe() {
        return this;
    }
    @Override
    public ClassIA getClassAnalyse() {
        return this;
    }

    public As_BondeLivraisonClient() throws Exception{
        this.setNomTable("AS_BONDELIVRAISON_CLIENT");
	 setNomClasseFille("vente.As_BondeLivraisonClientFille");
	 setLiaisonFille("numbl");
    }

    public void construirePK(Connection c)throws Exception{
        this.preparePk("BLC","get_seqASBONDELIVRAISONCLIENT");
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
    public String getNomClasseFille() {
        return ("vente.As_BondeLivraisonClientFille");
    }

    @Override
    public String getLiaisonFille() {
        return "numbl"  ;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRemarque() {
        return remarque;
    }
    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
    public String getIdbc() {
        return idbc;
    }
    public void setIdbc(String idbc) {
        this.idbc = idbc;
    }
    public String getMagasin() {
        return magasin;
    }
    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }
    public String getIdvente() {
        return idvente;
    }
    public void setIdvente(String idvente) {
        this.idvente = idvente;
    }
    public Date getDaty() {
        return daty;
    }
    public void setDaty(Date daty) {
        this.daty = daty;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIdorigine() {
        return idorigine;
    }

    public void setIdorigine(String idorigine) {
        this.idorigine = idorigine;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public MvtStock genererMvtStock(Connection c)throws Exception
    {
        boolean estOuvert=false;
        try
        {
            if(c==null)
            {
                c=new UtilDB().GetConn();
                estOuvert=true;
            }
            
            
            As_BondeLivraisonClient bl = (As_BondeLivraisonClient) this.getById(this.getId(),"AS_BONDELIVRAISON_CLIENT",c);           
            
            
            MvtStock mv=new MvtStock();
            mv.setIdobjet(bl.getId());
            mv.setDaty(bl.getDaty());
            mv.setDesignation("Mouvement de stock relatif "+this.getId());
            mv.setIdPoint(bl.getMagasin());
            mv.setIdTypeMvStock(ConstanteSocobis.TYPE_MVT_SORTIE);
            mv.setIdTransfert(bl.getId());
            mv.setIdVente(bl.getIdvente());
            
            mv.setIdMagasin(bl.getMagasin());
            As_BondeLivraisonClientFille_Cpl[] listeF=(As_BondeLivraisonClientFille_Cpl[]) this.getListeFilleCpl("rapprochement_bf_client",c);
            MvtStockFille[]lf=new MvtStockFille[listeF.length];
            for(int i=0;i<listeF.length;i++) {
                lf[i]=listeF[i].genererMvtStockFille();
		        lf[i].setIdMvtStock(mv.getId());
            }
            mv.setFille(lf);
            return mv;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(c!=null&&estOuvert==true)c.close();
        }
    }
    public VenteDetails[] getListeVenteDetails(String nTBLFille, Connection c)throws Exception{

        As_BondeLivraisonClientFille crt = new As_BondeLivraisonClientFille();
        crt.setNumbl(this.getId());
        if(nTBLFille!=null && nTBLFille.compareTo("")!=0){
            crt.setNomTable(nTBLFille);
        }
        As_BondeLivraisonClientFille[] blf = (As_BondeLivraisonClientFille[])CGenUtil.rechercher(crt, null,null,c,"");
        VenteDetails[] v = new VenteDetails[blf.length];
        for(int i =0; i<blf.length;i++){
            v[i]=blf[i].genererVenteDetails();
        }
        return v;
    }
    public void genererMvtStockPersist(String user)throws Exception
    {
	 boolean estOuvert=false;
	 Connection c=null;
        try
        {
            if(c==null){
                c=new UtilDB().GetConn();
                estOuvert=true;
            }
	     As_BondeLivraisonClient blc=(As_BondeLivraisonClient)this.getById(this.getId(),null,c);
	     MvtStock mvt=blc.genererMvtStock(c);
        //  mvt.setEtat(ConstanteEtat.getEtatValider());
        mvt.createObjectMultiple(user,c);
        mvt.validerObject(user, c);
	     /*MvtStockFille[] filles=(MvtStockFille[])mvt.getFille();
	     for( MvtStockFille lf:filles)
	     {
		  lf.setIdMvtStock(mvt.getId());
		  lf.createObject(user, c);
	     }*/
	 }
	 catch(Exception e){
            throw e;
        }
        finally
        {
            if(c!=null&&estOuvert==true)c.close();
        }
    }
    
    public static void controlerClient(As_BondeLivraisonClient[] bls)throws Exception{
        String idClient = "";
        for(As_BondeLivraisonClient item : bls){
            if(idClient.equals("") == false){
                if(idClient.equals(item.getIdClient()) == false){
                    throw new Exception("Tiers different");
                }
            }
            idClient = item.getIdClient();
        }
    }
    
    public static As_BondeLivraisonClient[] getAll(String[] ids, Connection co)throws Exception{
        As_BondeLivraisonClient bl = new As_BondeLivraisonClient();
        As_BondeLivraisonClient[] bls = (As_BondeLivraisonClient[]) CGenUtil.rechercher(bl, null, null,co, " and id in "+Utilitaire.tabToString(ids, "'", ","));
        return bls;
    }

    public As_BondeLivraisonClientFille_Cpl[] getListeFilleCpl(String nTBLFille, Connection c)throws Exception{
        As_BondeLivraisonClientFille_Cpl crt = new As_BondeLivraisonClientFille_Cpl();
        crt.setNumbl(this.getId());
        if(nTBLFille!=null && nTBLFille.compareTo("")!=0){
            crt.setNomTable(nTBLFille);
        }
        As_BondeLivraisonClientFille_Cpl[] blf = (As_BondeLivraisonClientFille_Cpl[])CGenUtil.rechercher(crt, null,null,c,"");
        return blf;
    }

    public void controleLivraison(Connection c) throws Exception{
        As_BondeLivraisonClientFille [] filles = (As_BondeLivraisonClientFille []) this.getFille();
        VenteDetailsLib vLib = new VenteDetailsLib();
        vLib.setNomTable("VENTE_DETAILS_RESTE");
        VenteDetailsLib[] details = (VenteDetailsLib[]) CGenUtil.rechercher(vLib,null,null,c," AND idVente='"+this.getIdvente()+"' AND reste > 0");
        for (int i = 0; i < details.length; i++) {
            for (int j = 0; j < filles.length; j++) {
                System.err.println(details[i].getIdProduit().compareToIgnoreCase(filles[j].getProduit())+"==============>"+filles[j].getQuantite()+">"+details[i].getReste());
                if(details[i].getIdUnite()!=null && filles[j].getUnite()!=null){
                    if(details[i].getIdProduit().compareToIgnoreCase(filles[j].getProduit())==0 && details[i].getIdUnite().compareToIgnoreCase(filles[j].getUnite())==0 && filles[j].getQuantite()>details[i].getReste()){
                        throw new Exception("Quantit\u00E9 \u00E0 livrer inf\u00E9rieur \u00E0 la quantit\u00E9 livr\u00E9e");
                    }
                }else {
                    throw new Exception("V&eacute;rifier les unit&eacute;s des produits");
                }
            }
        }
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        controleLivraison(c);
        return super.createObject(u, c);
    }

    public ClassMAPTable createObjectSimple(String u, Connection c) throws Exception {
        return super.createObject(u, c);
    }

    public VenteDetailsLib[] getListeVenteDetailsLib(String nTBLFille, Connection c)throws Exception{

        As_BondeLivraisonClientFille crt = new As_BondeLivraisonClientFille();
        crt.setNumbl(this.getId());
        if(nTBLFille!=null && nTBLFille.compareTo("")!=0){
            crt.setNomTable(nTBLFille);
        }
        As_BondeLivraisonClientFille[] blf = (As_BondeLivraisonClientFille[])CGenUtil.rechercher(crt, null,null,c,"");
        VenteDetailsLib[] v = new VenteDetailsLib[blf.length];
        for(int i =0; i<blf.length;i++){
            v[i]=blf[i].genererVenteDetailsLib(c);
        }
        return v;
    }
}
