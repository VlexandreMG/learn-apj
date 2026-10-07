/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vente;

import affichage.PageInsertMultiple;
import annexe.Produit;
import avoir.AvoirFC;
import avoir.AvoirFCFille;
import avoir.AvoirFCLib;
import bean.AdminGen;
import bean.CGenUtil;
import bean.ClassMere;
import bean.ResultatEtSomme;
import bean.ClassMAPTable;
import bean.LibelleAffichage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import caisse.Caisse;
import caisse.MvtCaisse;
import client.Client;
import constante.ConstanteEtat;
import encaissement.Encaissement;
import encaissement.EncaissementDetails;
import equivalence.EquivalenceIngredient;
import faturefournisseur.FactureFournisseur;
import faturefournisseur.FactureFournisseurCpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import faturefournisseur.FactureFournisseurDetails;
import magasin.Magasin;
import mg.cnaps.compta.ComptaEcriture;
import mg.cnaps.compta.ComptaSousEcriture;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import paiement.LiaisonPaiement;
import prevision.Prevision;
import prevision.PrevisionComplet;
import produits.Ingredients;
import remise.RemiseFille;
import ristourne.Ristourne;
import ristourne.RistourneDetails;
import rapprochement.RapprochementDBMere;
import stock.*;
import user.UserEJB;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import utils.ConstanteAsync;
import utils.ConstanteEtatCustom;
import utils.ConstanteSocobis;
import utils.ConstanteStation;
import utilitaire.ConstanteComptable;

import javax.imageio.ImageIO;
import produits.RecetteLib;
/**
 *
 * @author Angela
 */
public class Vente extends FactureCF {
    @LibelleAffichage("D&eacute;signation")
    protected String designation;
    @LibelleAffichage("Magasin")
    protected String idMagasin;
    @LibelleAffichage("Remarque")
    protected String remarque;
    @LibelleAffichage("Origine")
    protected String idOrigine;
    @LibelleAffichage("Client")
    protected String idClient;
    @LibelleAffichage("Libell&eacute; du client")
    protected String clientlib;
    @LibelleAffichage("Compte")
    protected String compte;
    @LibelleAffichage("Taux de change")
    protected double  tauxdechange;
    @LibelleAffichage("D&eacute;tails de la vente")
    protected VenteDetails[] venteDetails;
    @LibelleAffichage("Est pr&eacute;vu")
    int estPrevu;
    @LibelleAffichage("Montant de revient")
    double montantRevient;
    @LibelleAffichage("Marge brute")
    double margeBrute;
    @LibelleAffichage("&Eacute;ch&eacute;ance de la facture")
    double echeancefacture;
    @LibelleAffichage("Mode de paiement")
    String modepaiement;
    @LibelleAffichage("Frais de livraison")
    double fraislivraison;
    @LibelleAffichage("Mode de livraison")
    int modelivraison;
    @LibelleAffichage("R&eacute;f&eacute;rence de la facture")
    String referencefact;
    @LibelleAffichage("Num&eacute;ro de facture")
    String numerofacture;
    @LibelleAffichage("Lieu de livraison")
    private String lieuLivraison;
    @LibelleAffichage("Date de livraison")
    private Date dateLivraison;
    @LibelleAffichage("Pr&eacute;vision")
    private String idPrevision;
    @LibelleAffichage("Montant donn&eacute;")
    private double montantDonne;
    @LibelleAffichage("Montant retourn&eacute;")
    private double montantRetourner;
    @LibelleAffichage("Mode de paiement")
    private String idModePaiementLib;
    @LibelleAffichage("Num&eacute;ro du mode de paiement")
    private String idModePaiement;
    @LibelleAffichage("Responsable")
    private String idResponsable;
    @LibelleAffichage("Liste des ingr&eacute;dients")
    Ingredients[] listeIng;
    @LibelleAffichage("Client")
    Client client;
    String estAppoint="non";

    public String getEstAppoint() {
        return estAppoint;
    }

    public void setEstAppoint(String estAppoint) {
        this.estAppoint = estAppoint;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Ingredients[] getListeIng() {
        return listeIng;
    }

    public void setListeIng(Ingredients[] listeIng) {
        this.listeIng = listeIng;
    }

    public String getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(String idPrevision) {
        this.idPrevision = idPrevision;
    }

    public String getLieuLivraison() {
        return lieuLivraison;
    }

    public void setLieuLivraison(String lieuLivraison) {
        this.lieuLivraison = lieuLivraison;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getNumerofacture() {
        return numerofacture;
    }

    public void setNumerofacture(String numerofacture) {
        this.numerofacture = numerofacture;
    }

    public int getModelivraison() {
        return modelivraison;
    }

    public void setModelivraison(int modelivraison) {
        this.modelivraison = modelivraison;
    }

    public double getFraislivraison() {
        return fraislivraison;
    }

    public void setFraislivraison(double fraislivraison) {
        this.fraislivraison = fraislivraison;
    }

    public String getModepaiement() {
        return modepaiement;
    }

    public void setModepaiement(String modepaiement) {
        this.modepaiement = modepaiement;
    }

    public void setMargeBrute(double margeBrute) {
        this.margeBrute = margeBrute;
    }

    public double getMontantRevient() {
        return montantRevient;
    }

    public void setMontantRevient(double montantRevient) {
        this.montantRevient = montantRevient;
    }
    public String getReferencefact() {
        return referencefact;
    }

    public void setReferencefact(String referencefact) {
        this.referencefact = referencefact;
    }

    public double getMargeBrute()
    {
        return this.getMontantttc()-this.getMontantRevient();
    }

    @Override
    public boolean isSynchro(){
        return true;
    }
    
    public void Vente(){
    }

    @Override
    public boolean getEstIndexable() {
        return true;
    }

    @Override
    public String getTiers(){
        return this.getIdClient();
    }
    @Override
    public String getSensPrev(){
        return "credit";
    }

    public Prevision[] genererPrevision(String u, Connection c) throws Exception{
//        Prevision mere = new Prevision();
//        Vente venteComplet = this.getVenteWithMontant(c);
//        mere.setDaty(datyPrevu);
//        mere.setCredit(venteComplet.getMontantttcAr());
//        mere.setIdFacture(this.id);
//        mere.setIdCaisse(ConstanteStation.idCaisse);
//        mere.setIdDevise("AR");
//        mere.setDesignation("Prevision rattachée au vente N : "+this.getId());
//        mere.setIdTiers(this.getIdClient());
//        return ( Prevision ) mere.createObject(u, c);

        VenteDetails[] fille = (VenteDetails[]) this.getFille();
        List<Prevision> previsions = new ArrayList<>();


        for(VenteDetails f : fille)
        {
            Prevision prev = f.genererPrevision();
            prev.setDaty(this.getDatyPrevu());
            prev.setIdTiers(this.getIdClient());
            Prevision newPrev = (Prevision) prev.createObject(u, c);
            previsions.add(newPrev);
            System.out.println("Prevision created: " + newPrev.getId());

        }

        return previsions.toArray(new Prevision[previsions.size()]);

    }
    
    public Vente getVenteWithMontant(Connection c) throws Exception{
        return (Vente)new Vente().getById(this.getId(), "VENTE_CPL", c);
    }
    
    public String genererBonLivraison(String u) throws Exception{
        Connection c = null;
        try{
            c = new UtilDB().GetConn();
            c.setAutoCommit(false);
            Vente enBase = (Vente)this.getById(this.getId(), this.getNomTable(), c);
            VenteDetailsLib vLib = new VenteDetailsLib();
            vLib.setNomTable("VENTE_DETAILS_RESTE");
            VenteDetailsLib[] details = (VenteDetailsLib[]) CGenUtil.rechercher(vLib,null,null,c," AND idVente='"+this.getId()+"' AND reste > 0");
            if(details.length > 0){
                As_BondeLivraisonClient client = new As_BondeLivraisonClient();
                client.setMode("modif");
                client.setIdvente(this.getId());
                client.setEtat(1);
                client.setIdclient(enBase.getIdClient());
                client.setRemarque("Livraison de la facture numero "+this.getId());
                client.setDaty(Utilitaire.dateDuJourSql());
                client.createObject(u, c);
                for(VenteDetailsLib detail:details){
                    As_BondeLivraisonClientFille clientFille = new As_BondeLivraisonClientFille();
                    clientFille.setMode("modif");
                    clientFille.setProduit(detail.getIdProduit());
                    clientFille.setUnite(detail.getIdUnite());
                    clientFille.setQuantite(detail.getReste());
                    clientFille.setIdventedetail(detail.getId());
                    clientFille.setNumbl(client.getId());
                    clientFille.createObject(u, c);
                }
                c.commit();
                return client.getId();
            }
            throw new Exception("Plus aucun article à livrer");
        }
        catch(Exception e){
            if(c!=null) c.rollback();
            throw e;
        }
        finally{
            if(c!=null) c.close();
        }
    }

    public As_BondeLivraisonClient genererBonLivraison() throws Exception{
        Connection c = null;
        try{
            c = new UtilDB().GetConn();
            Vente enBase = (Vente)this.getById(this.getId(), this.getNomTable(), c);
            VenteDetailsLib vLib = new VenteDetailsLib();
            vLib.setNomTable("VENTE_DETAILS_RESTE");
            VenteDetailsLib[] details = (VenteDetailsLib[]) CGenUtil.rechercher(vLib,null,null,c," AND idVente='"+this.getId()+"' AND reste > 0");
            if(details.length > 0){
                As_BondeLivraisonClient client = new As_BondeLivraisonClient();
                client.setMode("modif");
                client.setIdbc(enBase.getIdOrigine());
                client.setIdvente(this.getId());
                client.setMagasin(enBase.getIdMagasin());
                client.setEtat(1);
                client.setIdclient(enBase.getIdClient());
                client.setRemarque("Livraison de la facture numero "+this.getId());
                client.setDaty(Utilitaire.dateDuJourSql());
                client.setIdorigine(this.getId());
                As_BondeLivraisonClientFille [] filles = new As_BondeLivraisonClientFille[details.length];
                int i = 0;
                List<As_BondeLivraisonClientFille_Cpl> listeFilles = new ArrayList<>();
                for(VenteDetailsLib detail:details){
                    As_BondeLivraisonClientFille_Cpl clientFille = new As_BondeLivraisonClientFille_Cpl();
                    clientFille.setMode("modif");
                    clientFille.setProduit(detail.getIdProduit());
                    Ingredients ing = (Ingredients) new Ingredients().getById(detail.getIdProduit(),"AS_INGREDIENTS",c);
                    if (ing.getCategorieIngredient() != null &&
                            ing.getCategorieIngredient().compareToIgnoreCase(ConstanteStation.idProduitService) == 0) {
                            continue; 
                    }
                    String inglibelle = ing.getLibelleVente() != null && !ing.getLibelleVente().isEmpty()
                            ? ing.getLibelleVente()
                            : ing.getLibelle();
                    clientFille.setProduitlib(inglibelle);
                    clientFille.setUnite(detail.getIdUnite());
                    clientFille.setUnitelib(detail.getUnitelib());
                    clientFille.setQuantite(detail.getReste());
                    clientFille.setIdventedetail(detail.getId());
                    clientFille.setNumbl(client.getId());
                    filles[i] = clientFille;
                    i++;
                    listeFilles.add(clientFille);
                }
                As_BondeLivraisonClientFille[] lsfilles = listeFilles.toArray(new As_BondeLivraisonClientFille[listeFilles.size()]);
                client.setFille(lsfilles);
                return client;
            }
            throw new Exception("Plus aucun article à livrer");
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(c!=null) c.close();
        }
    }

    public String getCompte() {
        return compte;
    }

    
    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) throws Exception {
        if(this.getMode().compareTo("modif")==0)
        {
            if(idClient==null||idClient.compareToIgnoreCase("")==0)
                throw new Exception("Client obligatoire");
        }
        this.idClient = idClient;
    }

    public Vente() {
        this.setNomTable("VENTE");
    }

    public Vente(String nomtable) {
        setNomTable(nomtable);
    }

    public int getEstPrevu() {
	 return estPrevu;
    }

    public void setEstPrevu(int estPrevu) {
	 this.estPrevu = estPrevu;
    }

    
    
    
    


    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    

    public boolean isPaye(){
        if(this.getEtat()== ConstanteEtatCustom.PAYE_LIVRE  || this.getEtat()==ConstanteEtatCustom.PAYE_NON_LIVRE){
            return true;
        }
        return false;
    }

    public boolean isLivre(){
        if(this.getEtat()== ConstanteEtatCustom.LIVRE_NON_PAYE || this.getEtat()==ConstanteEtatCustom.PAYE_LIVRE){
            return true;
        }
        return false;
    }

    

    public String getClientlib() {
        return clientlib;
    }

    public void setClientlib(String clientlib) {
        this.clientlib = clientlib;
    }

    public double getTauxdechange() {
        return tauxdechange;
    }

    public void setTauxdechange(double tauxdechange) {
        this.tauxdechange = tauxdechange;
    }
    
    public void payer(String u,Connection c) throws Exception{
        if(this.getEtat() < ConstanteEtat.getEtatValider()){
            throw new Exception("Impossible d encaisser une vente non validée");
        }
        if(isLivre()){
            this.updateEtat(ConstanteEtatCustom.PAYE_LIVRE, this.getId(), c);
        }
        else{
            this.updateEtat(ConstanteEtatCustom.PAYE_NON_LIVRE, this.getId(), c);
        }
        genererEcritureEncaissement(u, c);
    }

    public void livrer(String u,Connection c) throws Exception{
        if(this.getEtat() < ConstanteEtat.getEtatValider()){
            throw new Exception("Impossible de livrer une vente non validée");
        }
        if(isPaye()){
            this.updateEtat(ConstanteEtatCustom.PAYE_LIVRE, this.getId(), c);
        }
        else{
            this.updateEtat(ConstanteEtatCustom.LIVRE_NON_PAYE, this.getId(), c);
        }
    }

    public void lierLivraisons(String u, String [] idLivraison) throws Exception{
        Connection c = null;
        try {
            c = new UtilDB().GetConn();
            VenteDetails[] venteDetails = getVenteDetails(c);
            As_BondeLivraisonClient [] blcs = As_BondeLivraisonClient.getAll(idLivraison,c);
            As_BondeLivraisonClient.controlerClient(blcs);
            for (As_BondeLivraisonClient blcTemp : blcs) {
                blcTemp.setIdvente(this.getId());
                blcTemp.updateToTableWithHisto(u, c);
                As_BondeLivraisonClientFille [] blcfs = (As_BondeLivraisonClientFille[]) CGenUtil.rechercher(new As_BondeLivraisonClientFille(), null, null, c, " and NUMBL = '"+ blcTemp.getId() +"'");
                    for (int i = 0; i < venteDetails.length; i++) {
                        for (As_BondeLivraisonClientFille as_BondeLivraisonClientFilleTemp: blcfs) {
                            if(venteDetails[i].getIdProduit().equals(as_BondeLivraisonClientFilleTemp.getProduit())){
                                as_BondeLivraisonClientFilleTemp.setIdventedetail(venteDetails[i].getId());
                                as_BondeLivraisonClientFilleTemp.updateToTableWithHisto(u, c);
                            }
                        }
                    }
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
    
    @Override
    public void changeState(String acte, String u,Connection con)throws Exception{
        if(acte.equals("livrer")){
            this.livrer(u, con);
        }
        else if(acte.equals("payer")){
            this.payer(u, con);
        }
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    @Override
    public String getTuppleID() {
        return this.getId();
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public String getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(String idOrigine) {
        this.idOrigine = idOrigine;
    }


    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("VNT", "getSeqVente");
        this.setId(makePK(c));
    }

    public Caisse getCaisse(Connection c) throws Exception {
        Caisse caisse = new Caisse();
        Caisse[] caisses = (Caisse[]) CGenUtil.rechercher(caisse, null, null, c, " and idMagasin = '"+this.getIdMagasin()+"'");
        if (caisses.length > 0) {
            return caisses[0];
        }
        return null;
    }

    public Magasin getMagasin(Connection c) throws Exception {
        Magasin magasin = new Magasin();
        magasin.setId(this.getIdMagasin());
        Magasin[] magasins = (Magasin[]) CGenUtil.rechercher(magasin, null, null, c, " ");
        if (magasins.length > 0) {
            return magasins[0];
        }
        return null;
    }

    public VenteDetails[] getVenteDetails() {
        return venteDetails;
    }

    public void setVenteDetails(VenteDetails[] venteDetails) {
        this.venteDetails = venteDetails;
    }

    @Override
    public void controler(Connection c) throws Exception {
        super.controler(c);
    }

    @Override
    public void controlerUpdate(Connection c) throws Exception {
        super.controlerUpdate(c);

    }

    public void createMvtCaisses(String u, Connection c) throws Exception {
        VenteDetailsCpl vdc = new VenteDetailsCpl();
        vdc.setIdVente(this.getId());
        VenteDetailsCpl[] vdcs = (VenteDetailsCpl[]) CGenUtil.rechercher(vdc, null, null, c, " ");
        for (int i = 0; i < vdcs.length; i++) {
            MvtCaisse mc = vdcs[i].createMvtCaisse();
            mc.createObject(u, c);
        }
    }

    public VenteDetailsLib[] getVenteDetails(Connection c) throws Exception {
            VenteDetailsLib obj = new VenteDetailsLib();
            obj.setNomTable("VENTE_DETAILS_CPL");
            obj.setIdVente(this.getId());
            VenteDetailsLib[] objs = (VenteDetailsLib[]) CGenUtil.rechercher(obj, null, null, c, " ");
            if (objs.length > 0) {
                return objs;
            }
            return null;
    }
protected EncaissementDetails [] generateDetailsEncaissements (Connection c ) throws Exception{
       VenteDetailsLib[] vd= this.getVenteDetails(c);
        EncaissementDetails[] ed=new EncaissementDetails [vd.length];
        for(int i=0;i<ed.length;i++){
            ed[i]=vd[i].generateEncaissementDetails();
        }
        return ed;
 }

    @Override
    public Object payerObject(String u,Connection con) throws Exception{
        super.payerObject(u, con);
        Encaissement enc=this.genererEncaissement();
        enc=(Encaissement) enc.createObject(u, con);
        EncaissementDetails [] ed=generateDetailsEncaissements ( con );
        for(int i=0;i<ed.length;i++){
             ed[i].setIdEncaissement(enc.getId());
             ed[i].createObject(u,con);
        }
        return enc;
    } 


 
    protected MvtStockFille[] createMvtStockFilles(Connection c) throws Exception {
        VenteDetails[] tsd = this.getVenteDetails(c);
        MvtStockFille[] mvtf = new MvtStockFille[tsd.length];
        for (int i = 0; i < tsd.length; i++) {
            mvtf[i] = tsd[i].createMvtStockFille();
        }
        return mvtf;
    }

    protected MvtStock createMvtStock() throws Exception {
        MvtStock md = new MvtStock();
        md.setDaty(this.getDaty());
        md.setDesignation("Vente lubrifiant : " + this.getDesignation());
        md.setIdTransfert(this.getId());
        md.setIdTypeMvStock(ConstanteStation.TYPEMVTSTOCKSORTIE);
        md.setIdMagasin(this.getIdMagasin());
        return md;
    }

    protected MvtStock createMvtStockSortie(String u, Connection c) throws Exception {
        MvtStock ms = this.createMvtStock();
        ms.setFille(this.createMvtStockFilles(c));
        ms.createObject(u, c);
        ms.saveMvtStockFille(u, c);
        ms.validerObject(u, c);
        return ms;
    }

    
    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        NumeroFacture[] numFact = (NumeroFacture[]) CGenUtil.rechercher(new NumeroFacture(), null, null, c, "");
        this.setNumerofacture(numFact[0].getProchain_num_format());
        VenteDetails[] listeFille = (VenteDetails[]) this.getFille();
        RemiseFille[] listeRemise=this.getRemise(null,c);
        Client cl=null;
        Ingredients[] listeIng=null;
        if(listeRemise!=null)
        {
            cl=this.getClient(c);
            String apresW=" and id in ("+Utilitaire.tabToString(listeFille,"idProduit","'",",")+")";
            //listeIng=(Ingredients[])CGenUtil.rechercher(new Ingredients(),null,null,c,apresW);
            listeIng=this.getListeIngredients(null,c);
        }
        Set<String> seen = new HashSet<>();

        if (this.getFille() == null)
        {
            throw new Exception("Vente Detail is null");
        }

        for (VenteDetails vd : listeFille) {
            String key = vd.getIdProduit();
            String designation = vd.getDesignation();
            vd.setCatClient(cl.getIdTypeClient());
            RemiseFille rf=vd.estDansRemise(listeRemise,listeIng);
            String idDevise = null;
            if (vd.getIdDevise() != null || !vd.getIdDevise().trim().isEmpty())
            {
                idDevise = vd.getIdDevise().trim().toUpperCase();
                vd.setIdDevise(idDevise);
            }

            if(rf!=null)
            {
                vd.setRemise(rf.getRemise());
                vd.setIdRemise(rf.getIdremise());
            }
            if (key == null || key.trim().isEmpty()) continue;

            if (!seen.add(key)) {
                throw new Exception("Doublon pour l'article : " + designation);
            }
        }
//        CheckEtatStockVenteDetailsALaCreation(c);


        Vente createVente = (Vente) super.createObject(u, c);
        String codebarre = this.getId();

        this.genererCodeBarreWithName(createVente.getId(), ConstanteAsync.buildChemin(codebarre), codebarre, u);

        return createVente;
    }

    public RemiseFille[] getRemise(String nt, Connection c) throws Exception {
        RemiseFille crt = new RemiseFille();
        crt.setNomTable("remiseFilleMereVise");
        if(nt!=null&&nt.compareToIgnoreCase("")!=0)crt.setNomTable(nt);
        String apresWherePoint=" and (idpoint='"+this.getIdMagasin()+"' or idpoint is null) ";
        String apresWhereDaty=" and '"+Utilitaire.formatterDaty(this.getDaty())+"'>=dateDebut and '"+Utilitaire.formatterDaty(this.getDaty())+ "'<=dateFin order by daty desc";
        RemiseFille[] ret= (RemiseFille[]) CGenUtil.rechercher(crt,null,null, c,apresWherePoint+ apresWhereDaty);
        return ret;
    }
    public Ingredients[] getListeIngredients(String nt,Connection c) throws Exception {
        Ingredients crt=new Ingredients();
        VenteDetails[]fille=(VenteDetails[]) this.getFille();
        if(nt!=null&&nt.compareToIgnoreCase("")!=0)crt.setNomTable(nt);
        String apresW=" and id in ("+Utilitaire.tabToString(fille,"idProduit","'",",") +")";
        return (Ingredients[]) CGenUtil.rechercher(crt,null,null, c,apresW);
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                estOuvert = true;
                c.setAutoCommit(false);
                c = new UtilDB().GetConn();
            }
//            CheckEtatStockVenteDetails(c);
            VenteDetails[] listeFille=(VenteDetails[]) this.getFille();
            Ingredients[] listeIng=this.getListeIngredients(null,c);
            this.setListeIng(listeIng);
            for(int i=0;i<listeFille.length;i++){
                Ingredients ingredients = null;
                if(listeFille[i].getIdProduit()!=null&&!listeFille[i].getIdProduit().trim().isEmpty())
                {
                    String []col={"id"};
                    String []val={listeFille[i].getIdProduit()};
                    ingredients=(Ingredients)AdminGen.find(listeIng,col,val)[0];
                    listeFille[i].setIngFille(ingredients);
                    listeFille[i].setPoids(ingredients.getCalorie());
                    listeFille[i].calculerRevient(c);
                    listeFille[i].updateToTableWithHisto(u,c);
                }
            }
            super.validerObject(u, c);
            setFille(listeFille);
            genererEcriture(u, c);
            MvtStock stockEngage = genererStockEngage(listeFille, c);
            if (stockEngage != null) {
                stockEngage.createObject(u, c);
            }

            Ristourne ristourne = genererRistourne();
            if (ristourne != null) {
                ristourne.createObject(u, c);
                ristourne.validerObject(u, c);
            }

//            createMvtStockSortie(u, c);
            if(this.getEstPrevu() == 0){
                genererPrevision(u, c);
            }
            else if (this.getEstPrevu() == 1){
                genererPrevisionPrevu(u, c);
            }
            boolean estAppro = false;
            String[] clientsAppro = ConstanteSocobis.CLIENT_APPRO_VENTE;
            for (String idClientListe : clientsAppro) {
                if (this.getIdClient().equals(idClientListe)) {
                    estAppro = true;
                    break;
                }
            }
            if(estAppro == true){
                //genererApprovisionnement(listeFille, u, c);
            }

            return this;

        } catch (Exception e) {
            if (c != null) {
                c.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && estOuvert == true) {
                c.close();
            }
        }
    }

    public TransfertStock genererApprovisionnement(VenteDetails[] listeFille, String u, Connection c) throws Exception {
        TransfertStock st = new TransfertStock();
        st.setDaty(this.getDaty());
        st.setDesignation("Transfert de stock pour vente "+this.getId());
        st.setIdMagasinArrive(ConstanteSocobis.MAGASIN_APPRO);
        st.setIdMagasinDepart(this.getIdMagasin());
        st.setIsvente(1);
        st.setFille(new TransfertStockDetails[0]);
        st.createObject(u,c);
        TransfertStockDetails[] transfertFille =  genererApprovisionnementFilleParPiece(listeFille, st, u, c);
        st.validerObject(u, c);
        return st;
    }

    public TransfertStock genererTransfertStock(Connection c) throws Exception {
        VenteDetails[] listeFille=(VenteDetails[]) this.getFille(null,c,"");
        TransfertStock st = new TransfertStock();
        st.setDaty(this.getDaty());
        st.setDesignation("Transfert de stock pour vente "+this.getId());
        st.setIdMagasinArrive(ConstanteSocobis.MAGASIN_APPRO);
        st.setIdMagasinDepart(this.getIdMagasin());
        st.setIsvente(1);
        st.setFille(new TransfertStockDetails[0]);
        //st.createObject(u,c);
        TransfertStockDetails[] transfertFille =  genererTransfertFilleParPiece(listeFille, st, c);
        st.setFille(transfertFille);
        //st.validerObject(u, c);
        return st;
    }

    public TransfertStockDetails[] genererApprovisionnementFilleParPiece(VenteDetails[] listeFille, TransfertStock mere, String u, Connection c) throws Exception {
        if (listeFille == null || listeFille.length == 0) {
            listeFille = (VenteDetails[]) this.getFille(null,c,"");
        }
        TransfertStockDetailsAppro[] fille = new TransfertStockDetailsAppro[listeFille.length];
        for (int i = 0; i < listeFille.length; i++) {

            double qtePiece = listeFille[i].getQte();
            double puPiece =  listeFille[i].getPu();
            String idproduit1 = "";
            /*EquivalenceVente equivalence = Ingredients.getEquivalenceVente(listeFille[i].getIdProduit(), ConstanteSocobis.UNITE_PIECE, c);
            if(equivalence != null){
                qtePiece = listeFille[i].getQte() * equivalence.getQuantite();
                puPiece = listeFille[i].getPu() / equivalence.getQuantite();
                idproduit1 = equivalence.getIdproduit1();
            }*/
            fille[i] = new TransfertStockDetailsAppro();
            fille[i].setQuantitesource(listeFille[i].getQte());
            fille[i].setPusource(listeFille[i].getPu());
            fille[i].setIdProduit(listeFille[i].getIdProduit());
            fille[i].setDaty(listeFille[i].getDatesaisie());
            fille[i].setPu(listeFille[i].getPu());
            fille[i].setQuantite(listeFille[i].getQte());
            fille[i].setIdTransfertStock(mere.getId());
            fille[i].setEstapprodv(1);
            fille[i].createObject(u,c);
        }
        mere.setFille(fille);
        return  fille;
    }

    public TransfertStockDetails[] genererTransfertFilleParPiece(VenteDetails[] listeFille, TransfertStock mere, Connection c) throws Exception {
        if (listeFille == null || listeFille.length == 0) {
            listeFille = (VenteDetails[]) this.getFille(null,c,"");
        }
        TransfertStockDetailsAppro[] fille = new TransfertStockDetailsAppro[listeFille.length];
        for (int i = 0; i < listeFille.length; i++) {

            double qtePiece = listeFille[i].getQte();
            double puPiece =  listeFille[i].getPu();
            String idproduit1 = "";
            /*EquivalenceVente equivalence = Ingredients.getEquivalenceVente(listeFille[i].getIdProduit(), ConstanteSocobis.UNITE_PIECE, c);
            if(equivalence != null){
                qtePiece = listeFille[i].getQte() * equivalence.getQuantite();
                puPiece = listeFille[i].getPu() / equivalence.getQuantite();
                idproduit1 = equivalence.getIdproduit1();
            }*/
            fille[i] = new TransfertStockDetailsAppro();
            fille[i].setQuantitesource(listeFille[i].getQte());
            fille[i].setPusource(listeFille[i].getPu());
            fille[i].setIdProduit(listeFille[i].getIdProduit());
            fille[i].setDaty(listeFille[i].getDatesaisie());
            fille[i].setPu(listeFille[i].getPu());
            fille[i].setQuantite(listeFille[i].getQte());
            fille[i].setIdTransfertStock(mere.getId());
            fille[i].setEstapprodv(1);
            //fille[i].createObject(u,c);
        }
        mere.setFille(fille);
        return  fille;
    }

    public Ristourne genererRistourne() throws Exception {
        Ristourne ristourne = new Ristourne();
        ristourne.setEstLiaisonPaiement(1);
        ristourne.setDesignation("Ristourne de la facture "+this.getId());
        ristourne.setIdClient(getIdClient());
        ristourne.setIdOrigine(this.getId());
        ristourne.setDaty(Utilitaire.dateDuJourSql());
        ristourne.setMois(Utilitaire.getMois(getDaty()));
        ristourne.setAnnee(Utilitaire.getAnnee(getDaty()));
        ristourne.setIdVente(getId());
        ristourne.setDateDebutRistourne(null);
        ristourne.setDateFinRistourne(null);

        List<RistourneDetails> ristourneDetails = getRistourneDetails();

        if (ristourneDetails.isEmpty()) {
            return null;
        }

        ristourne.setFille(ristourneDetails.toArray(new RistourneDetails[]{}));
        return ristourne;
    }

    private List<RistourneDetails> getRistourneDetails() throws Exception {
        VenteDetails[] listeFille= (VenteDetails[]) this.getFille();
        List<RistourneDetails> ristourneDetails = new ArrayList<>();
        for (VenteDetails venteFille : listeFille) {
            if (venteFille.getRistourne() != 0) {
                RistourneDetails ristourneFille = new RistourneDetails();
                ristourneFille.setIdProduit(venteFille.getIdProduit());
                ristourneFille.setIdOrigine(venteFille.getIdOrigine());
                ristourneFille.setTaux1(venteFille.getRistourne());
                ristourneFille.setPu(venteFille.getPu()*(1+(venteFille.getTva()/100))*venteFille.getQte());
                ristourneFille.setQte((int)venteFille.getQte());
                ristourneDetails.add(ristourneFille);
            }
        }
        return ristourneDetails;
    }

    public MvtStock genererStockEngage(VenteDetails[] listeFille, Connection c) throws Exception {
        MvtStock mvtff = new MvtStock();
        mvtff.setNomTable("stock_engage");
        mvtff.setDesignation("Engagement du vente " + this.getId());
        mvtff.setIdMagasin(this.getIdMagasin());
        mvtff.setIdVente(this.getId());
        mvtff.setIdTypeMvStock(ConstanteStation.TYPEMVTSTOCKSORTIE);
        mvtff.setIdobjet(this.getId());
        mvtff.setDaty(this.getDaty());
        mvtff.setEtat(ConstanteEtat.getEtatValider());
        List<MvtStockFille> mvtStockFilles = new ArrayList<>();

        for (VenteDetails vd : listeFille) {
            if(vd.getIdProduit()!=null&&vd.getIdProduit().compareToIgnoreCase("")!=0){
                Ingredients [] ings = {vd.getIngFille()};
                if(ings[0].getCategorieIngredient().compareToIgnoreCase(ConstanteStation.idProduitAutre)!=0 && ings[0].getCategorieIngredient().compareToIgnoreCase(ConstanteStation.idProduitService)!=0){
                    MvtStockFille mvtFille = new MvtStockFille();
                    mvtFille.setNomTable("stock_engage_fille");

                    //Produit produit = vd.getProduitAs(c);

                    mvtFille.setIdProduit(ings[0].getId());
                    mvtFille.setIdVenteDetail(vd.getId());
                    mvtFille.setDesignation("Engagement detail : " + vd.getId());
                    mvtFille.setCategorieIngredient(ings[0].getIdCategorie());
                    mvtFille.setEntree(0);
                    mvtFille.setSortie(vd.getQte());
                    mvtFille.setPu(vd.getPu());
                    mvtFille.setMontant(vd.getMontant());
                    mvtFille.setEtat(ConstanteEtat.getEtatValider());

                    mvtStockFilles.add(mvtFille);
                }
            }
        }
        if(mvtStockFilles.size()>0){
            mvtff.setFille(mvtStockFilles.toArray(new MvtStockFille[]{}));
            return mvtff;
        }
        return null;
    }

    public void CheckEtatStockVenteDetails(Connection c) throws Exception {
        VenteDetailsLib[] vds = getVenteDetails(c);
        if (vds != null) {
            for (VenteDetailsLib v : vds) {
                Ingredients [] ings = (Ingredients[]) CGenUtil.rechercher(new Ingredients(), null, null, c, " and id = '"+v.getIdProduit()+"'");
                if(ings[0].getCategorieIngredient().compareToIgnoreCase(ConstanteStation.idProduitAutre)!=0 &&  ings[0].getCategorieIngredient().compareToIgnoreCase(ConstanteStation.idProduitService)!=0){
                    v.CheckEtatStock(c);
                }
            }
        }
    }

    public void CheckEtatStockVenteDetailsALaCreation(Connection c) throws Exception {
        VenteDetails[] vds = (VenteDetails[])this.getFille();
        if (vds != null) {
            for (VenteDetails v : vds) {
                v.CheckEtatStockALaCreation(c, this.getIdMagasin());
            }
        }
    }
    
    public void genererEcritureEncaissement(String u, Connection c) throws Exception{
        ComptaEcriture mere = new ComptaEcriture();
        Date dateDuJour = utilitaire.Utilitaire.dateDuJourSql();
        int exercice = utilitaire.Utilitaire.getAnnee(daty);
        mere.setDaty(dateDuJour);
        mere.setDesignation(this.getDesignation());
        mere.setExercice(""+exercice);
        mere.setDateComptable(this.getDaty());
        mere.setJournal(ConstanteStation.JOURNALVENTE);
        mere.setOrigine(this.getId());
        mere.setIdobjet(this.getId());
        mere.createObject(u, c);
        ComptaSousEcriture[] filles = this.genererSousEcritureEncaissement(u,c);
        for(int i=0; i<filles.length; i++){
            filles[i].setIdMere(mere.getId());
            filles[i].setExercice(exercice);
            filles[i].setDaty(this.getDaty());
            filles[i].setJournal(ConstanteStation.JOURNALVENTE);
            
            if(filles[i].getDebit()>0 || filles[i].getCredit()>0) filles[i].createObject(u, c);
        }
    }
    
    public ComptaSousEcriture[] genererSousEcritureEncaissement(String refUser,Connection c) throws Exception{
        ComptaSousEcriture[] compta={};
        boolean canClose=false;
        try{
            if(c==null){
                c=new UtilDB().GetConn();
                canClose=true;
            }
            Vente[] ventes = (Vente[]) CGenUtil.rechercher(new Vente("VENTE_MERE_MONTANT"), null, null, c, " and id = '"+this.getId()+"'");
            if(ventes.length<1) throw new Exception("Facture mere Introuvable");
            this.setCompte(getClient(c).getCompte());

            compta = new ComptaSousEcriture[2];
            
            compta[0]=new ComptaSousEcriture();
            compta[0].setLibellePiece(this.getDesignation());
            compta[0].setRemarque(this.getDesignation());
            compta[0].setCompte(getCaisse(c).getCompte());
            compta[0].setDebit(ventes[0].getMontantttc());
	     MvtCaisse mvt= new MvtCaisse();
	     mvt.setCredit(ventes[0].getMontantttc());
         mvt.setIdCaisse(getCaisse(c).getId());
	     mvt.setDaty(utilitaire.Utilitaire.dateDuJourSql());
	     mvt.setDesignation( "mvt pour"+ this.getDesignation());
	     mvt.setIdOrigine(this.getId());
	     mvt.createObject(refUser, c);
	     mvt.validerObject(refUser, c);
            
            compta[1]=new ComptaSousEcriture();
            compta[1].setLibellePiece("Encaissement Client "+ventes[0].getClientlib());
            compta[1].setRemarque("Encaissement Client "+ventes[0].getClientlib());
            compta[1].setCompte(this.getCompte());
//            compta[i].setDebit((montantHT-retenue) * ((this.getTva()/100)));
            compta[1].setCredit(ventes[0].getMontantttc());
            
        } catch(Exception e){
            throw e;
        } finally {
            if(canClose){
                c.close();
            }
        }
        return compta;
    }
    
    public void genererEcriture(String u, Connection c) throws Exception{
        ComptaEcriture mere = new ComptaEcriture();
        Date dateDuJour = utilitaire.Utilitaire.dateDuJourSql();
        int exercice = utilitaire.Utilitaire.getAnnee(daty);
        mere.setDaty(dateDuJour);
        mere.setDesignation(this.getDesignation());
        mere.setExercice(""+exercice);
        mere.setDateComptable(this.getDaty());
        mere.setJournal(ConstanteStation.JOURNALVENTE);
        mere.setOrigine(this.getId());
        mere.setIdobjet(this.getId());

        ComptaSousEcriture[] filles = this.genererSousEcriture(c);
        for(int i=0; i<filles.length; i++){
            filles[i].setIdMere(mere.getId());
            filles[i].setExercice(exercice);
            filles[i].setDaty(this.getDaty());
            filles[i].setJournal(ConstanteStation.JOURNALVENTE);
            //if(filles[i].getDebit()>0 || filles[i].getCredit()>0) filles[i].createObject(u, c);
        }
        mere.setFille(filles);
        mere.createObject(u, c);
    }

    public Client getClient(Connection c) throws Exception{
        Client client = new Client();
        Client[] clients = (Client[]) CGenUtil.rechercher(client,null,null,c, " and id = '"+this.getIdClient()+"'");
        if(clients.length > 0){
            return clients[0];
        }
        throw new Exception("Le client n'existe pas");
    }

    public double getPoidsTotal(Connection c) throws Exception{
        double poidsTotal =0;
        VenteDetails[] details = (VenteDetails[]) this.getFille(); //this.getDetailsLib(c);
        for (VenteDetails detail : details) {
            poidsTotal += detail.getPoids() * detail.getQte();
        }
        return poidsTotal;
    }

    public ComptaSousEcriture[] genererSousEcriture(Connection c) throws Exception{
        ComptaSousEcriture[] compta={};
        boolean canClose=false;
        try{
            if(c==null){
                c=new UtilDB().GetConn();
                canClose=true;
            }
            String magasin = this.getIdMagasin();
            //Vente[] ventes = (Vente[]) CGenUtil.rechercher(new Vente("VENTE_MERE_MONTANT"), null, null, c, " and id = '"+this.getId()+"'");
            //if(ventes.length<1) throw new Exception("Facture mere Introuvable");
            Client client = getClient(c);
            this.setClient(client);
            VenteDetails [] details = this.getDetails(c);
            double montantHT = AdminGen.calculSommeDouble(details,"montantHT")*details[0].getTauxDeChange();
            double montantTva = AdminGen.calculSommeDouble(details,"montantTva")*details[0].getTauxDeChange();
            double montantTTC = AdminGen.calculSommeDouble(details,"montantTTC")*details[0].getTauxDeChange();
            int taille = details.length;
            if( montantTva > 0 ){
                compta = new ComptaSousEcriture[taille+2];
            }else  {
                compta = new ComptaSousEcriture[taille+1];
            }
            int i=0;
            double poidsTotal = getPoidsTotal(c);
            for(i=i;i<taille;i++){
               // ing = (Ingredients)new Ingredients().getById(details[i].getIdProduit(),"AS_INGREDIENTS",c);
                compta[i]=new ComptaSousEcriture();
                compta[i].setLibellePiece(this.getDesignation());
                compta[i].setRemarque(details[i].getLibelle());
                if(magasin.compareToIgnoreCase(ConstanteComptable.magasinVD) == 0){
                    compta[i].setCompte(ConstanteComptable.CompteMagasinVD);
                }else {
                    compta[i].setCompte(details[i].getCompte());
                }
                double fraisdetails = poidsTotal * this.getFraislivraison();
                compta[i].setCredit((details[i].getMontantHT() * details[i].getTauxDeChange()) - fraisdetails);
            }
            if(montantTva>0) {
                compta[i] = new ComptaSousEcriture();
                compta[i].setLibellePiece("TVA Collectee");
                compta[i].setRemarque("TVA Collectee");
                //if(ing.getCompte_tva()!=null){
                //compta[i].setCompte(ing.getCompte_tva());
                //}else{
                compta[i].setCompte(ConstanteStation.compteTVACollecte);
                //}
//            compta[i].setDebit((montantHT-retenue) * ((this.getTva()/100)));
                compta[i].setCredit(montantTva);
                i++;
            }

            compta[i]=new ComptaSousEcriture();
            compta[i].setLibellePiece("Vente Client "+this.getClient().getNom()+ " facture num : "+this.getId());
            compta[i].setRemarque("Vente Client "+this.getClient().getNom()+ " facture num : "+this.getId());
            compta[i].setCompte(this.getClient().getCompte());
            compta[i].setCompte_aux(this.getIdClient());
            compta[i].setDebit(montantTTC- (fraislivraison* poidsTotal)) ;
        } catch(Exception e){
            throw e;
        } finally {
            if(canClose){
                c.close();
            }
        }
        return compta;
    }



    public VenteDetails[] getDetails(Connection c) throws Exception{           
        VenteDetails[] venteDetails = null;        
        try{
            String awhere = " and IDVENTE = '"+this.getId()+"'";
            venteDetails = (VenteDetails[]) CGenUtil.rechercher(new VenteDetails("VENTE_COMPTA_GEN"), null, null, c, awhere);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return venteDetails;
    }

    public VenteDetails[] getDetailsLib(Connection c) throws Exception{
        VenteDetails[] venteDetails = null;
        try{
            String awhere = " and IDVENTE = '"+this.getId()+"'";
            venteDetails = (VenteDetails[]) CGenUtil.rechercher(new VenteDetails("Vente_Details"), null, null, c, awhere);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return venteDetails;
    }
    
    
    public Encaissement genererEncaissement(){
        Encaissement enc = new Encaissement();
        enc.setIdOrigine(this.getId());
        enc.setDaty(utilitaire.Utilitaire.dateDuJourSql());
        enc.setDesignation("Encaissement vente du "+ this.getDaty()+ " de la facture numéro "+this.getId());
        enc.setIdTypeEncaissement(ConstanteStation.TYPE_ENCAISSEMENT_ENTREE);
        return enc;
    }
    
    public void genererAPartirLivraison(String[]ids, String u, Connection c)throws Exception{
        boolean canClose=false;
        try{
            if(c==null){
                c=new UtilDB().GetConn();
                canClose=true;
            }
        As_BondeLivraisonClient[] bls = As_BondeLivraisonClient.getAll(ids, c);
        As_BondeLivraisonClient.controlerClient(bls);
      
        this.setDesignation("Facturation de Bon de Livraison");
        this.setIdClient(bls[0].getIdclient());
        this.setIdMagasin(bls[0].getMagasin());
        this.setDaty(Utilitaire.dateDuJourSql());
        this.createObject(u, c);
        for(As_BondeLivraisonClient bl : bls){
            bl.setIdvente(this.getId());
            bl.updateToTableWithHisto(u, c);
        }
        As_BondeLivraisonClientFille blf = new As_BondeLivraisonClientFille();
        String[] somGr = {"quantite"};
        String[] gr = {"produit"};
        String[] tabvide = {};
        ResultatEtSomme rs =  CGenUtil.rechercherGroupe(blf, gr, somGr, null, null, " and numbl in "+Utilitaire.tabToString(ids, "'", ","), tabvide, "", c);
        As_BondeLivraisonClientFille[] blfs = (As_BondeLivraisonClientFille[]) rs.getResultat();
        for(As_BondeLivraisonClientFille item : blfs){
            VenteDetails vd = item.toVenteDetails();
            vd.setIdVente(this.getId());
            vd.setIdDevise("AR");
            vd.createObject(u, c);
        }
        } catch(Exception e){
            throw e;
        } finally {
            if(canClose){
                c.close();
            }
        }        
    }

    public static void annulerFacture(String u, Connection c, String idVente) throws SQLException, Exception{
        AvoirFC avoirFC = genererAvoir2(u, c, idVente);

        avoirFC.validerObject(u, c);
        System.out.println("avoir généré");
    }
    
    public static AvoirFC genererAvoir(String u, Connection c, String idVente) throws SQLException, Exception{
        AvoirFC avoirFC = null;
        boolean estOuvert = false;
        if(c==null){
            c = new UtilDB().GetConn();
            estOuvert = true;
            c.setAutoCommit(false);
        }
        try {
            
            Vente vente = Vente.getById(c, idVente);
            vente.getVenteDetailsNonGrp(c);
            
            int tailleFilles = vente.venteDetails.length;
            avoirFC = Vente.transformerFactureToAvoir(vente);
            avoirFC.createObject(u, c);
            AvoirFCFille[] avoirFCFilles = new AvoirFCFille[tailleFilles];
            for(int i = 0; i < tailleFilles; i++){
                avoirFCFilles[i] = Vente.transformerFactureToAvoirFille(vente.venteDetails[i]);
                avoirFCFilles[i].setIdAvoirFC(avoirFC.getId());
                avoirFCFilles[i].createObject(u, c);
            }
            avoirFC.setAvoirDetails(avoirFCFilles);
            c.commit();
        } catch (Exception e) {
            if(estOuvert) c.rollback();
            throw e ;
        } finally {
            if(estOuvert) c.close();
        }
        
        return avoirFC;
    }
    
    public static AvoirFC genererAvoir2(String u, Connection c, String idVente) throws SQLException, Exception{
        AvoirFC avoirFC = null;
        boolean estOuvert = false;
        if(c==null){
            c = new UtilDB().GetConn();
            estOuvert = true;
            c.setAutoCommit(false);
        }
        try {
            Vente vente = Vente.getById(c, idVente);
            vente.getVenteDetailsNonGrp(c);
            AvoirFC[] avoirs = (AvoirFC[]) CGenUtil.rechercher(new AvoirFC(), null, null, c, " and idVente = '"+idVente+"'");
            int tailleFilles = vente.venteDetails.length;
            avoirFC = Vente.transformerFactureToAvoir(vente);
            avoirFC.setIdtypeavoir(ConstanteSocobis.ID_TYPE_AVOIR);
            avoirFC.createObject(u, c);
            AvoirFCFille[] avoirFCFilles = new AvoirFCFille[tailleFilles];
            for(int i = 0; i < tailleFilles; i++){
                avoirFCFilles[i] = vente.transformerFactureToAvoirFille2(vente.venteDetails[i], c, avoirs);
                avoirFCFilles[i].setIdAvoirFC(avoirFC.getId());
                avoirFCFilles[i].createObject(u, c);
            }
            avoirFC.setAvoirDetails(avoirFCFilles);
            c.commit();
        } catch (Exception e) {
            if(estOuvert) c.rollback();
            throw e ;
        } finally {
            if(estOuvert) c.close();
        }
        
        return avoirFC;
    }

    public static AvoirFC transformerFactureToAvoir(Vente vente){
        AvoirFC valeur = new AvoirFC();
        valeur.setDesignation(vente.getDesignation());
        valeur.setIdMagasin(vente.getIdMagasin());
        valeur.setRemarque(vente.getRemarque());
        valeur.setIdOrigine(vente.getIdOrigine());
        valeur.setIdClient(vente.getIdClient());
        valeur.setIdVente(vente.getId());
        valeur.setCompte(vente.getCompte());
	 valeur.setDaty(vente.getDaty());
        valeur.setEtat(1);
        return valeur;
    }
    
    public static AvoirFCFille transformerFactureToAvoirFille(VenteDetails venteDetails) throws Exception{
        AvoirFCFille valeur = new AvoirFCFille();
//        valeur.setIdAvoirFC(venteDetails.get());
        valeur.setIdProduit(venteDetails.getIdProduit());
        //valeur.setIdOrigine(venteDetails.getIdOrigine());
        valeur.setQte(1);
        valeur.setPu(0);
        valeur.setTva(venteDetails.getTva());
        valeur.setPuAchat(0);
        valeur.setPuVente(0);
        valeur.setIdDevise(venteDetails.getIdDevise());
        valeur.setTauxDeChange(1);
        valeur.setDesignation(venteDetails.getIdProduit());
        //valeur.setIdVenteDetails(venteDetails.getId());
        valeur.setEtat(11);
        return valeur;
    }
        public  AvoirFCFille transformerFactureToAvoirFille2(VenteDetails venteDetails, Connection c, AvoirFC[] avoirs) throws Exception{
        AvoirFCFille valeur = new AvoirFCFille();   
        valeur.setIdProduit(venteDetails.getIdProduit());
        double  montantFraisLivraison = this.getFraislivraison() * venteDetails.getPoids() * venteDetails.getQte();
        double montantHT = venteDetails.getPu() * venteDetails.getQte();
        double  montantRemise = montantHT * venteDetails.getRemise() / 100;
        double  htApresRemise = montantHT - montantRemise;
        double  montantRistourne = htApresRemise * venteDetails.getRistourne() / 100;
        double  htFinal = htApresRemise - montantRistourne - montantFraisLivraison ;
        double  puNet = htFinal / venteDetails.getQte();
        valeur.setQte(venteDetails.getQte() );
        valeur.setPu(puNet);
        valeur.setRemise(venteDetails.getRemise());
        valeur.setTva(venteDetails.getTva());
        valeur.setPuAchat(0);
        valeur.setPuVente(0);
        valeur.setIdDevise(venteDetails.getIdDevise());
        valeur.setTauxDeChange(1);
        valeur.setDesignation(venteDetails.getIdProduit());
        //valeur.setIdVenteDetails(venteDetails.getId());
        valeur.setEtat(11);
        return valeur;
    }
    
    public static Vente getById(Connection c, String id) throws Exception{
        Vente vtn = new Vente();        
           try{
            vtn.setId(id);
            vtn = ((Vente[]) CGenUtil.rechercher(vtn, null, null, c, "")).length > 0 ? (Vente)((Vente[]) CGenUtil.rechercher(vtn, null, null, c, ""))[0] : null;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return vtn;
    }
    
    public VenteDetails [] getVenteDetailsNonGrp(Connection c) throws Exception{
        VenteDetails[] venteDetails = null;        
           try{
            String awhere = " and IDVENTE = '"+this.getId()+"'";
            venteDetails = (VenteDetails[]) CGenUtil.rechercher(new VenteDetails(), null, null, c, awhere);
            this.setVenteDetails(venteDetails);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return venteDetails;
    }
    
     public Prevision[] getPrevisions(Connection c) throws Exception{
        Boolean estOuvert = false;
        try{
            if(c==null){
                c = new UtilDB().GetConn();
                estOuvert=true;
            }
            Prevision prevision = new Prevision();
            prevision.setIdFacture(this.getId());
            Prevision[] prev = (Prevision[]) CGenUtil.rechercher(prevision, null, null, c, " ");
            return prev;
        } catch (Exception e) {
            throw e;
        } finally {
            if(estOuvert)c.close();
        }     
    }
    public Vente[] controllerVentePaiementMultiple(String[] ids)throws Exception{
        String aWhere=Utilitaire.getAWhereIn(ids,"id");
        Vente[] ventes = (Vente[]) CGenUtil.rechercher(new Vente(), null, null, null, aWhere);
        String firstClientId = ventes[0].getIdClient();
        for (int i = 0; i < ventes.length; i++) {
            if (!ventes[i].getIdClient().equalsIgnoreCase(firstClientId))throw new Exception("Clients differents!");
            if (ventes[i].getEtat() < 11)throw new Exception("Presence de facture non visee!");
        }
        return ventes;
    }

     public String getDesignation() {
         return designation;
     }

     public void setDesignation(String designation) {
         this.designation = designation;
     }


    public static VenteLib genererVenteClient(String [] ids, Connection c) throws Exception{
        boolean estOuvert = false;
        try{
            if(c==null){
                c = new UtilDB().GetConn();
                estOuvert=true;
            }
            VenteLib[] liste =(VenteLib[])CGenUtil.rechercher(new VenteLib(), null, null,c," AND ID IN ("+ Utilitaire.tabToString(ids, "'", ",")+")");
            if(liste.length<=0){
                return null;
            }
            String idClient = liste[0].getIdClient();
            double montant = liste[0].getMontantreste();
            for (int i = 1; i < liste.length; i++) {
                if(liste[i].getIdClient().compareToIgnoreCase(idClient)!=0){
                    throw new Exception("Client different pour ces ventes");
                }
                montant += liste[i].getMontantreste();
            }
            liste[0].setMontantreste(montant);
            return liste[0];
        } catch (Exception e) {
            throw e;
        } finally {
            if(estOuvert)c.close();
        }
    }
    public static LiaisonPaiement genererPaiementFactureParAvoir(String u, Connection c, String idVente) throws SQLException, Exception{
        LiaisonPaiement pFacture = null;
        boolean estOuvert = false;
        if(c==null){
            c = new UtilDB().GetConn();
            estOuvert = true;
            c.setAutoCommit(false);
        }
        try {
            
            Vente vente = Vente.getById(c, idVente);
            AvoirFCLib t = new AvoirFCLib();
            t.setNomTable("AVOIRFCLIB_CPL");
            AvoirFCLib[] details = (AvoirFCLib[]) CGenUtil.rechercher(t,null,null,c," AND idClient='"+vente.getIdClient()+"' order by id asc");
            StringBuilder sb = new StringBuilder();
            if(details.length>0){
                for(int i=0;i<details.length;i++){
                    if (i > 0) sb.append(";"); 
                    sb.append(details[i].getId());
                }
            }
            double montantTotalReste = AdminGen.calculSommeDouble(details,"resteapayerar");
            pFacture = new LiaisonPaiement();
            pFacture.setId2(idVente);
            //pFacture.setId1(sb.toString());
            //pFacture.setDaty(vente.getDaty());
            pFacture.setMontant(montantTotalReste);
            c.commit();
        } catch (Exception e) {
            if(estOuvert) c.rollback();
            throw e ;
        } finally {
            if(estOuvert) c.close();
        }
        
        return pFacture;
    }

    public double getEcheancefacture() {
        return echeancefacture;
    }

    public void setEcheancefacture(double echeancefacture) {
        this.echeancefacture = echeancefacture;
    }
    public static MvtCaisse genererPaiementFacture(String[] ids,Connection c) throws Exception{
        System.out.println("ids = "+ids.length);
        String requete = null;
        boolean ifconnnull = false;
        MvtCaisse mvt = new MvtCaisse();
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                ifconnnull = true;
                VenteLib ff = new VenteLib();
                ff.setNomTable("VENTE_CPL");
                requete = "SELECT * FROM " + ff.getNomTable() + " WHERE id IN (";
                requete += Utilitaire.tabToString(ids, "'", ",") + ")";
                System.out.println(requete);
                VenteLib[] liste = (VenteLib[]) CGenUtil.rechercher(ff, requete, c);
                Set<String> clients = new HashSet<>();
                for (VenteLib v : liste) {
                    clients.add(v.getIdClient());
                }
                if (clients.size() > 1) {
                    throw new Exception("Client different pour ces ventes !");
                }

                double montantTotal = AdminGen.calculSommeDouble(liste,"montantreste");
                mvt.setIdOrigine(String.join(";;", ids));
                mvt.setCredit(montantTotal);
                mvt.setIdDevise("Ar");
                mvt.setIdTiers(liste[0].getIdClient());
                mvt.setDesignation("Paiement facture  par "+liste[0].getIdClientLib());
            }
        } catch (Exception x) {
            x.printStackTrace();
            throw x;
        } finally {
            if (ifconnnull && c != null) {
                c.close();
            }
        }
        return mvt;
    }

    public Vente reglerReleverCredit(String u, Connection c, String[] ids) throws Exception{
        boolean canClose=false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                canClose = true;
                c.setAutoCommit(false);
            }
            Vente facture = (Vente) super.createObject(u,c);
            VenteDetails[] filles = (VenteDetails[]) facture.getFille();
            double montant = 0;
            for (VenteDetails fille : filles ) {
                //fille.createObject(u,c);
                fille.validerObject(u,c);
                montant += fille.getPu() * fille.getQte();
            }
            facture.validerObject(u,c);

            MvtCaisse mvt = new MvtCaisse();
            mvt.setIdCaisse(facture.getReferencefact());
            mvt.setDesignation("Paiement de la facture de règlement : "+facture.getId());
            mvt.setIdTiers(facture.getIdClient());
            mvt.setDaty(facture.getDaty());
            mvt.setIdOrigine(facture.getId());
            mvt.setCredit(montant);
            mvt.setDebit(0);
            MvtCaisse val = (MvtCaisse) mvt.createObject(u,c);
            mvt.validerObject(u,c);

            ComptaEcriture ce = new ComptaEcriture();
            ce.setIdobjet(val.getId());
            ComptaEcriture[] ecriture = (ComptaEcriture[]) CGenUtil.rechercher(ce,null,null,c,"");
            System.err.println("==============================================IDS"+ids.length);
            System.err.println("==============================================ecriture"+ecriture.length);
            if (ecriture.length > 0) {
                ecriture[0].validerObject(u,c);
                ComptaSousEcriture sousEcriture = new ComptaSousEcriture();
                ComptaSousEcriture[] sousEcritures = (ComptaSousEcriture[]) CGenUtil.rechercher(sousEcriture,null,null,c," AND IDMERE='"+ecriture[0].getId()+"' AND COMPTE like '5%'");
                String[] idEcritures = new String[1];
                //for (int i = 0; i < sousEcritures.length; i++) {
                idEcritures[0] = sousEcritures[0].getId();
                sousEcritures[0].validerObject(u,c);
                //}
                RapprochementDBMere rp = new RapprochementDBMere().rapprochement(u,idEcritures,ids,c);
                rp.validerObject(u,c);
            }
            c.commit();
            return facture;
        }catch (Exception e){
            if(canClose) c.rollback();
            throw e;
        } finally {
            if(canClose) c.close();
        }
    }
    public Prevision[] getListePrevision(String nt,Connection c)throws Exception
    {
        String apresW=Utilitaire.tabToString(this.getFille(),"id","'",",");
        Prevision crt=new Prevision();
        if(nt!=null&&nt.compareToIgnoreCase("")!=0)crt.setNomTable(nt);
        apresW=" and IDVENTEDETAIL in ("+apresW+")";
        return (Prevision[]) CGenUtil.rechercher(crt,null,null,c,apresW);
    }
    public Prevision[] genererPrevisionPrevu(String u, Connection c) throws Exception {
        VenteDetails[] details = (VenteDetails[]) this.getFille();
        Prevision[] previsions = new Prevision[details.length];

        for (int i = 0; i < details.length; i++) {
            previsions[i] = details[i].genererPrevisionPrevu(u, c);
        }
        return  previsions;
    }

    public VenteDetails creerAPpoint(double montant) throws Exception {
        VenteDetails venteDetails = new VenteDetails();
        venteDetails.setIdProduit("APP");
        venteDetails.setQte(1);
        venteDetails.setPu(montant);
        venteDetails.setTva(0);
        venteDetails.setIdVente(this.getId());
        return venteDetails;
    }

    protected MvtStock createMvtStockVenteDirecte() throws Exception {
        MvtStock md = new MvtStock();
        md.setDaty(this.getDaty());
        md.setDesignation("Vente directe Num : " + this.getId() );
        md.setIdTypeMvStock(ConstanteStation.TYPEMVTSTOCKSORTIE);
        md.setIdMagasin(this.getIdMagasin());
        md.setIdVente(this.getId());
        md.setIdobjet(this.getId());
        return md;
    }

    public static String enregistrerViserLivrer(UserEJB u, PageInsertMultiple p, String nomtable, String colonneMere, String modePaiement, String reference)throws Exception{
        Connection c = null;
        try {

            c = new UtilDB().GetConn();
            c.setAutoCommit(false);
            ClassMAPTable cmere = p.getObjectAvecValeur();
            VenteDetails[] cfille = (VenteDetails[]) p.getObjectFilleAvecValeur();
//            System.out.println("cfilleee " + cfille.length);
            for (int i = 0; i < cfille.length; i++) {
                cfille[i].setNomTable(nomtable);
//                cfille[i].setCompte("710011");
//                System.out.println("====> Compte POS" + cfille[i].getCompte());
            }
            Vente mere = ((Vente)cmere);
            if(mere.getIdMagasin()==null || mere.getIdMagasin().compareToIgnoreCase("")==0) {
                mere.setIdMagasin(ConstanteAsync.MAGAGIN);
            }
            mere.setDatyPrevu(Utilitaire.dateDuJourSql());
            mere.setDaty(Utilitaire.dateDuJourSql());

            if (modePaiement==null || modePaiement.compareToIgnoreCase("")==0 || modePaiement.compareToIgnoreCase("especes") == 0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_ESPECE);
            } else if(modePaiement.compareToIgnoreCase("virement")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_CARTE);
            } else if(modePaiement.compareToIgnoreCase("mvola")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_MOBILEMONEY);
            } else if(modePaiement.compareToIgnoreCase("orange")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_MOBILEMONEY);
            } else if(modePaiement.compareToIgnoreCase("airtel")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_MOBILEMONEY);
            } else if(modePaiement.compareToIgnoreCase("cheque")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_CHEQUE);
            }
//            mere.createObject(""+u.getUser().getRefuser(),c);
//            u.createObjectMultiple(cfille,colonneMere,mere.getTuppleID(),c);
            mere.setFille(cfille);
            mere.createObject(""+u.getUser().getRefuser(),c);
            mere.validerObject(""+u.getUser().getRefuser(), c);
            String idBL = mere.genererBonLivraisonAvecMvtStock(""+u.getUser().getRefuser(),c);

            VenteDetails[] listeFille=(VenteDetails[]) mere.getFille(null,c,"");
            MvtStock mvtStockMere = mere.createMvtStockVenteDirecte();
            List<MvtStockFille> mvtStockFilles = new ArrayList<>();

            for(int i=0;i<listeFille.length;i++){
                Ingredients ing = listeFille[i].getIngredientById();
                if (ing.getCompose() == 1) {
                    RecetteLib[] listeComposition = ing.getRecette(null, c);
                    for (RecetteLib recetteLib : listeComposition) {
                        MvtStockFille mvtStockFille = new MvtStockFille();
                        mvtStockFille.setIdProduit(recetteLib.getIdingredients());
                        double qteIngredient = recetteLib.getQuantite() * listeFille[i].getQte();
                        mvtStockFille.setSortie(qteIngredient);
                        mvtStockFille.setIdVenteDetail(listeFille[i].getId());
                        mvtStockFille.setPu(recetteLib.getPu());
                        mvtStockFille.setMontant(mvtStockFille.getSortie() * recetteLib.getPu());
                        mvtStockFille.setDesignation("Sortie par vente directe de " + ing.getLibelle() + " - Composition de " + recetteLib.getLibelleingredient());
                        mvtStockFilles.add(mvtStockFille);
                    }

                }

                listeFille[i].calculerRevient(c);
                listeFille[i].updateToTableWithHisto(""+u.getUser().getRefuser(),c);
            }
            mvtStockMere.setFille(mvtStockFilles.toArray(new MvtStockFille[]{}));

            mvtStockMere.setEtat(ConstanteEtat.getEtatValider());
            mvtStockMere.createObject(String.valueOf(u.getUser().getRefuser()), c);

            // mere.genererEncaissementVente(modePaiement, reference, u.getUser().getTuppleID(), c);
            c.commit();
            try {
                ExportPDFDirect.ficheVente(mere.getId());
            } catch (Exception printEx) {
                printEx.printStackTrace();
            }
            return mere.getId();
        } catch (Exception e) {
            e.printStackTrace();
            c.rollback();
            throw e;
        }finally {
            if (c != null) {
                c.close();
            }
        }
    }


    public static synchronized String enregistrerViserLivrerEncaisser(UserEJB u, PageInsertMultiple p, String nomtable, String colonneMere, String modePaiement, String reference, String[] estEnGrosListe, String aretourner, double montantPaye)throws Exception{
        Connection c = null;
        try {

            c = new UtilDB().GetConn();
            c.setAutoCommit(false);
            ClassMAPTable cmere = p.getObjectAvecValeur();
            VenteDetails[] cfille = (VenteDetails[]) p.getObjectFilleAvecValeur();
            for (int i = 0; i < cfille.length; i++) {
//                System.out.println("cfilleee " + i);
                cfille[i].setNomTable(nomtable);
//                cfille[i].setCompte("710011");
//                System.out.println("====> Compte POS" + cfille[i].getCompte());
            }
            Vente mere = ((Vente)cmere);

            if(mere.getIdMagasin()==null || mere.getIdMagasin().compareToIgnoreCase("")==0) {
                mere.setIdMagasin(u.getPointEncours().getId());
            }
            mere.setDatyPrevu(Utilitaire.dateDuJourSql());
            mere.setDaty(Utilitaire.dateDuJourSql());
            mere.setMontantDonne(montantPaye);

            if (modePaiement==null || modePaiement.compareToIgnoreCase("")==0 || modePaiement.compareToIgnoreCase("especes") == 0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_ESPECE);
            } else if(modePaiement.compareToIgnoreCase("virement")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_CARTE);
            } else if(modePaiement.compareToIgnoreCase("mvola")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_MOBILEMONEY);
            } else if(modePaiement.compareToIgnoreCase("orange")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_MOBILEMONEY);
            } else if(modePaiement.compareToIgnoreCase("airtel")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_MOBILEMONEY);
            } else if(modePaiement.compareToIgnoreCase("cheque")==0) {
                mere.setIdModePaiement(ConstanteAsync.PAIEMENT_CHEQUE);
            }

//            mere.createObject(""+u.getUser().getRefuser(),c);

            for (int j = 0; j < cfille.length; j++) {
                Ingredients ing = cfille[j].getIngredientById();
                cfille[j].setPu(ing.getPv());
                if ((estEnGrosListe[j]!=null || estEnGrosListe[j]==null) && ing.getUnite().compareToIgnoreCase("UNT00004") == 0 && !estEnGrosListe[j].equals("1")) {
                    double qteModifie = cfille[j].getQte() / 1000;
                    cfille[j].setQte(qteModifie);
                    cfille[j].setPu(ing.getPv());
                } else if (estEnGrosListe[j]!=null && ing.getUnite().compareToIgnoreCase("UNT00004") == 0 && estEnGrosListe[j].equals("1")) {
                    double qteModifie = cfille[j].getQte() / 1000;
                    cfille[j].setQte(qteModifie);
                    cfille[j].setPu(ing.getPvgros());

                }
            }




            double montantTTC = AdminGen.calculSommeDouble(cfille, "montantTTCCalc");
//            System.out.println(" montantTTC " + montantTTC);
            double montantTTCPAr1000 = montantTTC / 1000;
            montantTTCPAr1000 = Utilitaire.arrondir(montantTTCPAr1000, 1);
//            System.out.println(" montantTTC " + montantTTC + " montantTTCPAr1000 1 " + montantTTCPAr1000);
            montantTTCPAr1000 = montantTTCPAr1000 * 1000;
            double appoint = montantTTCPAr1000 - montantTTC;
//            u.createObjectMultiple(cfille,colonneMere,mere.getTuppleID(),c);

            mere.setFille(cfille);
            mere.createObject(""+u.getUser().getRefuser(),c);

            if (appoint != 0) mere.creerAPpoint(appoint).createObject(""+u.getUser().getRefuser(),c);

            mere.setEtat(11);
            mere.updateToTableWithHisto(""+u.getUser().getRefuser(),c);


            VenteDetails[] listeFille=(VenteDetails[]) mere.getFille(null,c,"");
            MvtStock mvtStockMere = mere.createMvtStockVenteDirecte();
            List<MvtStockFille> mvtStockFilles = new ArrayList<>();

            for(int i=0;i<listeFille.length;i++){
                Ingredients ing = listeFille[i].getIngredientById();
                if (ing.getCompose() == 1) {
                    RecetteLib[] listeComposition = ing.getRecette(null, c);
                    for (RecetteLib recetteLib : listeComposition) {
                        MvtStockFille mvtStockFille = new MvtStockFille();
                        mvtStockFille.setIdProduit(recetteLib.getIdingredients());
                        double qteIngredient = recetteLib.getQuantite() * listeFille[i].getQte();
                        mvtStockFille.setSortie(qteIngredient);
                        mvtStockFille.setIdVenteDetail(listeFille[i].getId());
                        mvtStockFille.setPu(recetteLib.getPu());
                        mvtStockFille.setMontant(mvtStockFille.getSortie() * recetteLib.getPu());
                        mvtStockFille.setDesignation("Sortie par vente directe de " + ing.getLibelle() + " - Composition de " + recetteLib.getLibelleingredient());
                        mvtStockFilles.add(mvtStockFille);
                    }

                } else {
                    MvtStockFille mvtStockFille = new MvtStockFille();
                    mvtStockFille.setIdProduit(listeFille[i].getIdProduit());
                    double qteIngredient = listeFille[i].getQte();
                    mvtStockFille.setSortie(qteIngredient);
                    mvtStockFille.setIdVenteDetail(listeFille[i].getId());
                    mvtStockFille.setPu(ing.getPv());
                    mvtStockFille.setMontant(mvtStockFille.getSortie() * ing.getPv());
                    mvtStockFille.setDesignation("Sortie par vente directe de " + ing.getLibelle() + " - Composition de " + ing.getLibelle());
                    mvtStockFilles.add(mvtStockFille);
                }

                listeFille[i].calculerRevient(c);
                listeFille[i].updateToTableWithHisto(""+u.getUser().getRefuser(),c);
            }
            mvtStockMere.setFille(mvtStockFilles.toArray(new MvtStockFille[]{}));

            mvtStockMere.setEtat(ConstanteEtat.getEtatValider());
            mvtStockMere.createObject(String.valueOf(u.getUser().getRefuser()), c);

            mere.genererEcriture(""+u.getUser().getRefuser(), c);
//            mere.validerObject(""+u.getUser().getRefuser(),c);
//            String idBL = mere.genererBonLivraisonAvecMvtStock(""+u.getUser().getRefuser(),c);

            // mere.genererEncaissementVente(modePaiement, reference, u, c, estEnGrosListe, aretourner);
            double aRetournerCalcule = 0;
            if (mere.getIdModePaiement().equals(ConstanteAsync.PAIEMENT_ESPECE) && montantPaye > montantTTCPAr1000) {
                aRetournerCalcule = montantPaye - montantTTCPAr1000;
            }
            mere.genererEncaissementVente(modePaiement, reference, u, c, estEnGrosListe, String.valueOf(aRetournerCalcule));
            c.commit();
//            try {
//                ExportPDFDirect.ficheVente(mere.getId());
//            } catch (Exception printEx) {
//                printEx.printStackTrace();
//            }
            return mere.getId();
        } catch (Exception e) {
            e.printStackTrace();
            c.rollback();
            throw e;
        }finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public void genererEncaissementVente(String modePaiement,String reference, UserEJB u, Connection c, String[] estEnGrosListe, String aretourner) throws Exception {
        try {
            if(c == null) {
                c = new UtilDB().GetConn();
            }
            MvtCaisse mvt = new MvtCaisse();
            VenteDetails venteDetail = new VenteDetails();
            venteDetail.setIdVente(this.getId());
            VenteDetails[] res = (VenteDetails[]) CGenUtil.rechercher(venteDetail, null, null, c, "");
            double montantTotal = 0;
//            if (res != null && res.length > 0) {
//                for (int i = 0; i < res.length; i++) {
//                    Ingredients ing = new Ingredients();
//                    ing.setId(res[i].getIdProduit());
//                    Ingredients[] ings = (Ingredients[]) CGenUtil.rechercher(ing, null, null, c, "");
//                    System.out.println(" ing " + ings.length + ings[0].getId() + " i = " + i );
//                    double prixUnitaire = 0;
//                    prixUnitaire = ings[0].getPv();;
//                    System.out.println("inggg " + ings[0] + estEnGrosListe[i]);
//                    if ( ings[0].getId().compareToIgnoreCase("APP")!=0&& ings[0].getUnite()!= null && ings[0].getUnite().compareToIgnoreCase("UNT00004") == 0 && (estEnGrosListe == null || i >= estEnGrosListe.length )) {
//                        prixUnitaire = ings[0].getPv();
//                    } else if( ings[0].getId().compareToIgnoreCase("APP")!=0&&  ings[0].getUnite()!= null && ings[0].getUnite().compareToIgnoreCase("UNT00004")==0 && (estEnGrosListe != null || estEnGrosListe.length>0 || estEnGrosListe[i].equals("1"))){
//                        prixUnitaire = ings[0].getPvgros();
//                    }
//                    prixUnitaire = Math.round(prixUnitaire / 10.0) * 10.0;
//
//                    System.out.println(" pv " + ings[0].getPv() + " pvgros " + ings[0].getPvgros());
//                    double montant = (prixUnitaire * res[i].getQte()) - res[i].getRemiseMontant();
//                    montantTotal += montant;
//                }
//            }
            montantTotal = AdminGen.calculSommeDouble(res, "montantTTCCalc");
            String idCaisse = "";
            Caisse caisse =  new Caisse();
            Caisse crt=new Caisse();
            crt.setIdPoint(u.getPointEncours().getId());
            crt.setIdTypeCaisse(ConstanteAsync.caisseTypeEspece);
            Caisse[] listeCaisse=(Caisse[]) CGenUtil.rechercher(crt, null, null, c, " ");

            if(listeCaisse!=null&&listeCaisse.length>0){
                u.setCaisseEspeceDefaut(listeCaisse[0]);
            }
            else{
                Caisse vao=new Caisse();
                vao.setId(ConstanteAsync.CAISSE_ESPECE);
                u.setCaisseEspeceDefaut(vao);
            }

            String idPoint = u.getPointEncours().getId();
            if (modePaiement==null || modePaiement.compareToIgnoreCase("")==0 || modePaiement.compareToIgnoreCase("especes") == 0) {
                idCaisse = u.getCaisseEspeceDefaut().getId();
            } else if(modePaiement.compareToIgnoreCase("virement")==0 || modePaiement.compareToIgnoreCase("visa")==0) {
                idCaisse = getCaisseTpe(c, idPoint);
            } else if(modePaiement.compareToIgnoreCase("mvola")==0) {
                idCaisse = getCaisseMvola(c, idPoint);
            } else if(modePaiement.compareToIgnoreCase("orange")==0) {
                idCaisse = getCaisseOrange(c, idPoint);
            } else if(modePaiement.compareToIgnoreCase("airtel")==0) {
                idCaisse = getCaisseAirtel(c, idPoint);
            } else if(modePaiement.compareToIgnoreCase("cheque")==0) {
                idCaisse = getCaisseCheque(c, idPoint);
            }

            if(reference!=null && reference.compareToIgnoreCase("")!=0) {
                mvt.setReference(reference);
            }
            mvt.setIdCaisse(idCaisse);
            mvt.setDaty(this.getDaty());
            mvt.setCredit(montantTotal);
            mvt.setIdOrigine(this.getId());
            mvt.setDesignation("Encaissement de la facture Num : " + this.getId());
            mvt.setTaux(1);
            mvt.setIdDevise("AR");
            mvt.setIdTiers(this.getIdClient());
            mvt.setMontantRetourner(Double.parseDouble(aretourner));
            mvt.setCompte(ConstanteAsync.COMPTE_REGROUPEMENT);
            mvt.setIdCaissier(u.getUser().getTuppleID());
            mvt.createObject(u.getUser().getTuppleID(), c);
            mvt.validerObject(u.getUser().getTuppleID(), c);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    public static String getCaisseMvola(Connection c, String idPoint)throws Exception {
        Caisse crt=new Caisse();
        crt.setIdPoint(idPoint);
        crt.setIdCategorieCaisse(ConstanteAsync.caisseCategMvola);
        Caisse[] listeCaisse=(Caisse[]) CGenUtil.rechercher(crt, null, null, c, " ");

        if(listeCaisse!=null&&listeCaisse.length>0){
            return listeCaisse[0].getId();
        }
        else{
            return ConstanteAsync.CAISSE_MVOLA;
        }
    }

    public static String getCaisseOrange(Connection c, String idPoint)throws Exception {
        Caisse crt=new Caisse();
        crt.setIdPoint(idPoint);
        crt.setIdCategorieCaisse(ConstanteAsync.caisseCategOrange);
        Caisse[] listeCaisse=(Caisse[]) CGenUtil.rechercher(crt, null, null, c, " ");

        if(listeCaisse!=null&&listeCaisse.length>0){
            return listeCaisse[0].getId();
        }
        else{
            return ConstanteAsync.CAISSE_ORANGE;
        }
    }

    public static String getCaisseAirtel(Connection c, String idPoint)throws Exception {
        Caisse crt=new Caisse();
        crt.setIdPoint(idPoint);
        crt.setIdCategorieCaisse(ConstanteAsync.caisseCategAirtel);
        Caisse[] listeCaisse=(Caisse[]) CGenUtil.rechercher(crt, null, null, c, " ");

        if(listeCaisse!=null&&listeCaisse.length>0){
            return listeCaisse[0].getId();
        }
        else{
            return ConstanteAsync.CAISSE_AIRTEL;
        }
    }

    public static String getCaisseTpe(Connection c, String idPoint)throws Exception {
        Caisse crt=new Caisse();
        crt.setIdPoint(idPoint);
        crt.setIdCategorieCaisse(ConstanteAsync.caisseCategTpe);
        Caisse[] listeCaisse=(Caisse[]) CGenUtil.rechercher(crt, null, null, c, " ");

        if(listeCaisse!=null&&listeCaisse.length>0){
            return listeCaisse[0].getId();
        }
        else{
            return ConstanteAsync.CAISSE_TPE;
        }
    }

    public static String getCaisseCheque(Connection c, String idPoint)throws Exception {
        Caisse crt=new Caisse();
        crt.setIdPoint(idPoint);
        crt.setIdCategorieCaisse(ConstanteAsync.caisseCategCheque);
        Caisse[] listeCaisse=(Caisse[]) CGenUtil.rechercher(crt, null, null, c, " ");

        if(listeCaisse!=null&&listeCaisse.length>0){
            return listeCaisse[0].getId();
        }
        else{
            return ConstanteAsync.CAISSE_CHEQUE;
        }
    }

    public static double arrondir(double nombre) {
        return Math.round(nombre / 100.0) * 100.0;
    }

    public String genererBonLivraisonAvecMvtStock(String u,Connection c) throws Exception{
        try{
            VenteDetailsLib vLib = new VenteDetailsLib();
            vLib.setNomTable("VENTE_DETAILS_RESTE");
            VenteDetailsLib[] details = (VenteDetailsLib[]) CGenUtil.rechercher(vLib,null,null,c," AND idVente='"+this.getId()+"' AND reste > 0");

            if(details.length > 0){
                As_BondeLivraisonClient client = new As_BondeLivraisonClient();
                client.setMode("modif");
                client.setIdvente(this.getId());
                client.setMagasin(this.getIdMagasin());
                client.setEtat(1);
                client.setIdclient(this.getIdClient());
                client.setRemarque("POS Livraison de la facture numero "+this.getId());
                client.setDaty(Utilitaire.dateDuJourSql());
//                client.createObjectSimple(u, c);

                As_BondeLivraisonClient createdClient = (As_BondeLivraisonClient) client.createObjectSimple(u, c);
                createdClient.setEtat(11);
                createdClient.updateToTableWithHisto(u,c);
                for(VenteDetailsLib detail:details){
                    As_BondeLivraisonClientFille clientFille = new As_BondeLivraisonClientFille();
                    clientFille.setMode("modif");
                    clientFille.setProduit(detail.getIdProduit());
                    clientFille.setUnite(detail.getIdUnite());
                    clientFille.setQuantite(detail.getReste());
                    clientFille.setIdventedetail(detail.getId());
                    clientFille.setNumbl(createdClient.getId());
                    clientFille.createObject(u, c);
                }
                MvtStock mvt = createdClient.genererMvtStock(c);
                mvt = (MvtStock) mvt.createObject(u,c);
                mvt.validerObject(u,c);
                return createdClient.getId();
            }
            throw new Exception("Plus aucun article à livrer");
        }
        catch(Exception e){
            throw e;
        }
    }

    public void genererEncaissementMultiple(String idCaisse, String reference, String u, Connection c, double montant, String aretourner) throws Exception {
        try {
            if(c == null) {
                c = new UtilDB().GetConn();
            }

            MvtCaisse mvt = new MvtCaisse();
            mvt.setReference(reference);
            mvt.setIdCaisse(idCaisse);
            mvt.setDaty(this.getDaty());
            mvt.setCredit(montant);
            mvt.setIdOrigine(this.getId());
            mvt.setDesignation("Encaissement multiple de la facture Num : " + this.getId());
            mvt.setTaux(1);
            mvt.setIdDevise("AR");
            mvt.setIdTiers(this.getIdClient());
            mvt.setCompte(ConstanteAsync.COMPTE_REGROUPEMENT);
            mvt.setIdCaissier(u);
            mvt.setMontantRetourner(Double.parseDouble(aretourner));
            mvt.createObject(u, c);
            mvt.validerObject(u, c);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static String enregistrerViserLivrerEncaisserMultiple(UserEJB u, PageInsertMultiple p, String nomtable, String colonneMere, Map<String, PaiementInfo> paiements, String[] estEnGrosListe, String aretourner, double montantPaye)throws Exception{
        Connection c = null;
        try {

            c = new UtilDB().GetConn();
            c.setAutoCommit(false);
            ClassMAPTable cmere = p.getObjectAvecValeur();
            VenteDetails[] cfille = (VenteDetails[]) p.getObjectFilleAvecValeur();
            for (int i = 0; i < cfille.length; i++) {
                cfille[i].setNomTable(nomtable);
//                cfille[i].setCompte("710011");
//                System.out.println("====> Compte POS" + cfille[i].getCompte());
            }
            Vente mere = ((Vente)cmere);
            if(mere.getIdMagasin()==null || mere.getIdMagasin().compareToIgnoreCase("")==0) {
                mere.setIdMagasin(u.getPointEncours().getId());
            }
            mere.setDatyPrevu(Utilitaire.dateDuJourSql());
            mere.setDaty(Utilitaire.dateDuJourSql());
            String user = u.getUser().getTuppleID();
            mere.setIdModePaiement(ConstanteAsync.PAIEMENT_MULTIPLE);
            mere.setMontantDonne(montantPaye);

            for (int j = 0; j < cfille.length; j++) {
                Ingredients ing = cfille[j].getIngredientById();
                cfille[j].setPu(ing.getPv());
                if (ing.getUnite().compareToIgnoreCase("UNT00004") == 0 && !estEnGrosListe[j].equals("1")) {
                    double qteModifie = cfille[j].getQte() / 1000;
                    cfille[j].setQte(qteModifie);
                    cfille[j].setPu(ing.getPv());
                } else if (ing.getUnite().compareToIgnoreCase("UNT00004") == 0 && estEnGrosListe[j].equals("1")) {
                    double qteModifie = cfille[j].getQte() / 1000;
                    cfille[j].setQte(qteModifie);
                    cfille[j].setPu(ing.getPvgros());

                }
            }
            PaiementInfo[] listePayement = paiements.values().toArray(new PaiementInfo[0]);
            double somme = 0;
            double sommeApayer = 0;

            for (PaiementInfo info : listePayement) {
                somme += info.getMontant();
            }

            for (VenteDetails detail : cfille) {
                double aPayer = arrondir(detail.getPu() * detail.getQte());
                sommeApayer += (aPayer);
            }

            System.out.println("SOMME ====>" + somme);
            System.out.println("SOMME A PAYER ====>" + sommeApayer);
            // I removed this because it doesn't allow the remise to be paid, we must fix the top
//            if (somme < sommeApayer) throw new Exception("Montant payer insuffisant par rapport au montant total !");
            String idPoint = u.getPointEncours().getId();

//            mere.createObject(""+u.getUser().getRefuser(),c);

            double montantTTC = AdminGen.calculSommeDouble(cfille, "montantTTCCalc");
//            System.out.println(" montantTTC " + montantTTC);
            double montantTTCPAr1000 = montantTTC / 1000;
            montantTTCPAr1000 = Utilitaire.arrondir(montantTTCPAr1000, 1);
//            System.out.println(" montantTTC " + montantTTC + " montantTTCPAr1000 1 " + montantTTCPAr1000);
            montantTTCPAr1000 = montantTTCPAr1000 * 1000;
            double appoint = montantTTCPAr1000 - montantTTC;
//            u.createObjectMultiple(cfille,colonneMere,mere.getTuppleID(),c);


            mere.setFille(cfille);
            mere.createObject(""+u.getUser().getRefuser(),c);

            if (appoint != 0) mere.creerAPpoint(appoint).createObject(""+u.getUser().getRefuser(),c);

            mere.setEtat(11);
            mere.updateToTableWithHisto(""+u.getUser().getRefuser(),c);
            VenteDetails[] listeFille=(VenteDetails[]) mere.getFille(null,c,"");
            MvtStock mvtStockMere = mere.createMvtStockVenteDirecte();
            List<MvtStockFille> mvtStockFilles = new ArrayList<>();
            for(int i=0;i<listeFille.length;i++){
                Ingredients ing = listeFille[i].getIngredientById();
                if (ing.getCompose() == 1) {
                    RecetteLib[] listeComposition = ing.getRecette(null, c);
                    for (RecetteLib recetteLib : listeComposition) {
                        MvtStockFille mvtStockFille = new MvtStockFille();
                        mvtStockFille.setIdProduit(recetteLib.getIdingredients());
                        double qteIngredient = recetteLib.getQuantite() * listeFille[i].getQte();
                        mvtStockFille.setSortie(qteIngredient);
                        mvtStockFille.setIdVenteDetail(listeFille[i].getId());
                        mvtStockFille.setPu(recetteLib.getPu());
                        mvtStockFille.setMontant(mvtStockFille.getSortie() * recetteLib.getPu());
                        mvtStockFille.setDesignation("Sortie par vente directe de " + ing.getLibelle() + " - Composition de " + recetteLib.getLibelleingredient());
                        mvtStockFilles.add(mvtStockFille);
                    }

                }

                listeFille[i].calculerRevient(c);
                listeFille[i].updateToTableWithHisto(""+u.getUser().getRefuser(),c);
            }
            mvtStockMere.setFille(mvtStockFilles.toArray(new MvtStockFille[]{}));

            mvtStockMere.setEtat(ConstanteEtat.getEtatValider());
            mvtStockMere.createObject(String.valueOf(u.getUser().getRefuser()), c);

            mere.genererEcriture(""+u.getUser().getRefuser(), c);
//            mere.validerObject(""+u.getUser().getRefuser(),c);
            String idBL = mere.genererBonLivraisonAvecMvtStock(""+u.getUser().getRefuser(),c);
//            mere.genererEncaissementVente(modePaiement, reference, u.getUser().getTuppleID(), c, estEnGrosListe);
            double aRetournerCalcule = 0;
            if (somme > montantTTCPAr1000) {
                aRetournerCalcule = somme - montantTTCPAr1000;
            }
            for(Map.Entry<String, PaiementInfo> entry : paiements.entrySet()) {
                String modePaiement = entry.getKey();
                PaiementInfo paiement = entry.getValue();
                String monnaie = (modePaiement.equalsIgnoreCase("especes")) ? String.valueOf(aRetournerCalcule) : "0";
                if (modePaiement==null || modePaiement.compareToIgnoreCase("")==0 || modePaiement.compareToIgnoreCase("especes") == 0) {
                    mere.genererEncaissementMultiple(u.getCaisseEspeceDefaut().getId(), paiement.getReference(), user, c, paiement.getMontant(), monnaie);
                } else if(modePaiement.compareToIgnoreCase("virement")==0) {
                    mere.genererEncaissementMultiple(getCaisseTpe(c, idPoint), paiement.getReference(), user, c, paiement.getMontant(), monnaie);
                } else if(modePaiement.compareToIgnoreCase("mvola")==0) {
                    mere.genererEncaissementMultiple(getCaisseMvola(c, idPoint), paiement.getReference(), user, c, paiement.getMontant(), monnaie);
                } else if(modePaiement.compareToIgnoreCase("orange")==0) {
                    mere.genererEncaissementMultiple(getCaisseOrange(c, idPoint), paiement.getReference(), user, c, paiement.getMontant(), monnaie);
                } else if(modePaiement.compareToIgnoreCase("airtel")==0) {
                    mere.genererEncaissementMultiple(getCaisseAirtel(c, idPoint), paiement.getReference(), user, c, paiement.getMontant(), monnaie);
                } else if(modePaiement.compareToIgnoreCase("cheque")==0) {
                    mere.genererEncaissementMultiple(getCaisseCheque(c, idPoint), paiement.getReference(), user, c, paiement.getMontant(), monnaie);
                }
            }
            c.commit();
//            try {
//                ExportPDFDirect.ficheVente(mere.getId());
//            } catch (Exception printEx) {
//                printEx.printStackTrace();
//            }
            return mere.getId();
        } catch (Exception e) {
            e.printStackTrace();
            c.rollback();
            throw e;
        }finally {
            if (c != null) {
                c.close();
            }
        }
    }

    private File genererCodeBarreWithName(String id, String chemin, String nomPersonne, String u) throws Exception {
        File fichier = new File(chemin);

        File parentDir = fichier.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
            parentDir = new File("/tmp/vente");
            parentDir.mkdirs();
            fichier = new File(parentDir, fichier.getName());
            System.out.println("➡ Fichier redirigé vers : " + fichier.getAbsolutePath());
        }

        if (fichier.exists()) {
            System.out.println("ℹ Code-barre déjà généré : " + fichier.getAbsolutePath());
            return fichier;
        }

        ConstanteAsync.CODE128_BEAN.setFontSize(0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                baos, "image/png", ConstanteAsync.DPI, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        ConstanteAsync.CODE128_BEAN.generateBarcode(canvas, id);
        canvas.finish();

        BufferedImage barcodeImage = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));

        int extraHeight = 30;
        BufferedImage combined = new BufferedImage(
                barcodeImage.getWidth(), barcodeImage.getHeight() + extraHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = combined.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, combined.getWidth(), combined.getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(nomPersonne);
        int x = (combined.getWidth() - textWidth) / 2;
        int y = fm.getAscent() + 5;
        g.drawString(nomPersonne, x, y);

        g.drawImage(barcodeImage, 0, extraHeight, null);

        g.dispose();
//        System.out.println("avant updateeeee ");
//
        ImageIO.write(combined, "png", fichier);
//        System.out.println("avant updateeeee 2222 ");
//        Ingredients ing = (Ingredients) new Ingredients().getById(this.getId(), "AS_INGREDIENTS", null);
//        System.out.println(" andeha hanao updateeeee ");
//        ing.setCodeBarre(nomPersonne);
//
//        ing.updateToTableWithHisto(u);

        return fichier;
    }


    @Override
    public String getLiaisonFille() {
        return "idVente";
    }

    @Override
    public String getNomClasseFille() {
        return "vente.VenteDetails";
    }

    public double getMontantDonne() {
        return montantDonne;
    }

    public void setMontantDonne(double montantDonne) {
        this.montantDonne = montantDonne;
    }

    public double getMontantRetourner() {
        return montantRetourner;
    }

    public void setMontantRetourner(double montantRetourner) {
        this.montantRetourner = montantRetourner;
    }

    public String getIdModePaiementLib() {
        return idModePaiementLib;
    }

    public void setIdModePaiementLib(String idModePaiementLib) {
        this.idModePaiementLib = idModePaiementLib;
    }

    public String getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(String idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    public String getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(String idResponsable) {
        this.idResponsable = idResponsable;
    }
}
