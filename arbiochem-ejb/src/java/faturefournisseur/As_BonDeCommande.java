/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package faturefournisseur;

import bean.*;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import maintenance.travaux.TravauxCpl;
import produits.IngredientsLib;
import utilitaire.ConstanteEtat;
import utilitaire.UtilDB;
import utils.ConstanteSocobis;
import vente.*;
import utilitaire.Utilitaire;

/**
 *
 * @author micha
 */
public class As_BonDeCommande extends ClassMere {
    
    private String id;
    private Date daty;
    private int etat;
    private String remarque;
    private String designation;
    private String fournisseur;
    private String modepaiement;
    private String reference;
    private String modepaiementlib;
    private String fournisseurlib;
    private String idDevise;
    private String idMagasin;
    @LibelleAffichage("Remise en (%)")
    private String remise;

    private Date dateLimite;

    private String service;

    private String iddmdachat;
    private String refproforma;

    public String getIddmdachat() {
        return iddmdachat;
    }

    public void setIddmdachat(String iddmdachat) {
        this.iddmdachat = iddmdachat;
    }

    public String getRemise() {
        return remise;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    @Override
    public void annulerVisa(String u, Connection c) throws Exception {
        if (etat == ConstanteEtat.getEtatValider()) {
            throw  new Exception("Cette Bon de commande est deja valide");
        }
        super.annulerVisa(u, c);
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public void setService(String service) {
        this.service = service;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    // private ArrayList<As_BonDeCommande_Fille> bcFilles;

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

    public As_BonDeCommande() {
        this.setNomTable("AS_BONDECOMMANDE");
        this.setLiaisonFille("idbc");

    }

    public void construirePK(Connection c) throws Exception {
        this.preparePk("BC", "GETSEQBONCOMMANDE");
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

    public Date getDaty() {
        return this.daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public int getEtat() {
        return this.etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getRemarque() {
        return this.remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getDesignation() {
        return Utilitaire.champNull(designation);
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFournisseur() {
        return this.fournisseur;
    }

    public void setFournisseur(String fournisseur) throws Exception{
        if(fournisseur.compareToIgnoreCase("") == 0 || fournisseur == null)throw new Exception("Fournisseur manquant");
        this.fournisseur = fournisseur;
    }

    public String getModepaiement() {
        return Utilitaire.champNull(modepaiement);
    }

    public void setModepaiement(String modepaiement) {
        this.modepaiement = modepaiement;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    // public ArrayList<As_BonDeCommande_Fille> getBcFilles() {
    //     return bcFilles;
    // }

    // public void setBcFilles(ArrayList<As_BonDeCommande_Fille> bcFilles) {
    //     this.bcFilles = bcFilles;
    // }
     @Override
    public String getLiaisonFille() {
        return "idbc";
    }
    @Override
    public String getNomClasseFille() {
        return "faturefournisseur.As_BonDeCommande_Fille";
    }
    @Override
    public void controler(Connection c) throws Exception
    {
        As_BonDeCommande_Fille[] bcfille = (As_BonDeCommande_Fille[]) CGenUtil.rechercher(new As_BonDeCommande_Fille(), null, null, c, "and idbc  ='" + this.getId() + "' and idDevise!='"+this.getIdDevise()+"'");
         if(bcfille.length>0){
             throw new Exception("Devise diff\\u00E9rent non accept\\u00E9");
         }
    }
            
    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }
    
    public String genererFacture(String u) throws Exception{
        Connection c = null;
        try{
            c = new UtilDB().GetConn();
            c.setAutoCommit(false);
            String idFacture =  genererFacture(u,c);
            c.commit();
            return idFacture;
        }
        catch(Exception e){
            if(c!=null) c.rollback();
            throw e;
        }
        finally{
            if(c!=null) c.close();
        }
    }

    public String genererFacture(String u, Connection c) throws Exception{
        As_BonDeCommande enBase = (As_BonDeCommande)this.getById(this.getId(), this.getNomTable(), c);
        As_BonDeCommande_Fille vLib = new As_BonDeCommande_Fille();
        vLib.setNomTable("AS_BONDECOMMANDE_FILLE");
        As_BonDeCommande_Fille[] details = (As_BonDeCommande_Fille[]) CGenUtil.rechercher(vLib,null,null,c," AND idBc='"+this.getId()+"'");
        if(details.length > 0){
            Vente vente = new Vente();
            vente.setIdClient(enBase.getFournisseur());
            vente.setDesignation("Facture de la BC "+this.getId());
            vente.setDaty(Utilitaire.dateDuJourSql());
            vente.setIdOrigine(enBase.getId());
            // vente.setIdMagasin(enBase.getIdMagasin());
            vente.setEtat(1);
            vente.createObject(u, c);
            for(As_BonDeCommande_Fille detail:details){
                VenteDetails detailVente= new VenteDetails();
                detailVente.setIdOrigine(detail.getId());
                detailVente.setIdProduit(detail.getProduit());
                detailVente.setQte(detail.getQuantite());
                detailVente.setPu(detail.getPu());
                detailVente.setTva(detail.getTva());
                detailVente.setIdVente(vente.getId());
                detailVente.setIdDevise(detail.getIdDevise());
                detailVente.setTauxDeChange(detail.getTauxDeChange());
                detailVente.createObject(u, c);
            }
            return vente.getId();
        }
        throw new Exception("Plus aucun article à facturer");
    }    


    public As_BonDeLivraison createBL() throws Exception {
        As_BonDeLivraison bl = new As_BonDeLivraison();
        bl.setDaty(Utilitaire.dateDuJourSql());
        bl.setIdbc(this.getId());
        bl.setIdFournisseur(fournisseur);
        return bl;
    }

    public As_BonDeCommande_Fille[] getBCFilleResteALivrer(Connection c, String nomtable) {
        As_BonDeCommande_Fille[] bcfilles = null;
        try {
            if (c == null) {
                throw new Exception("Connexion non établie");
            }
            if (nomtable == null) {
                nomtable = "BCFILLERESTEALIVRERLIB";
            }
            As_BonDeCommande_Fille temp = new As_BonDeCommande_Fille();
            temp.setNomTable(nomtable);
            bcfilles = (As_BonDeCommande_Fille[]) CGenUtil.rechercher(temp, null, null, c, " and idbc = '" + this.getId() + "'");
            for (int i = 0; i < bcfilles.length; i++) {
                As_BonDeCommande_Fille bcfille = bcfilles[i];
                bcfille.calculerTva();
                bcfille.calculerHT();
                bcfille.calculerTTC();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bcfilles;
    }

    public As_BonDeLivraison[] getBondelivraisons(Connection c, String nomtable) throws Exception {
        As_BonDeLivraison crt = new As_BonDeLivraison();
        if(nomtable!=null){
            crt.setNomTable(nomtable);
        }
        crt.setIdbc(this.getId());
        return (As_BonDeLivraison[]) CGenUtil.rechercher(crt, null, null, c, "");
    }

    private void generateBLFille(As_BonDeLivraison bl, Connection c, String u) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
                c.setAutoCommit(false);
            }
            As_BonDeCommande_Fille[] details = getBCFilleResteALivrer(c, "BCFILLERESTEALIVRERLIB");
            for (As_BonDeCommande_Fille d : details) {
                As_BonDeLivraison_Fille blf = d.createBLFille(bl.getId());
                blf.createObject(u, c);
            }
            
            if(estOuvert){
                c.commit();
            }
            
        } catch (Exception e) {
            if(estOuvert){
                c.rollback();
            }
            throw e;
        } finally {
            if (estOuvert) {
                c.close();
            }
        }
    }

    private As_BonDeLivraison genererBL(String u, Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
                c.setAutoCommit(false);
            }
            As_BonDeLivraison bl = createBL();
            bl.setEtat(1);
            bl = (As_BonDeLivraison) bl.createObject(u, c);
            generateBLFille(bl, c, u);
            
            if(estOuvert){
                c.commit();
            }
            
            return bl;
        } catch (Exception e) {
            if(estOuvert){
                c.rollback();
            }
            throw e;
        } finally {
            if (estOuvert) {
                c.close();
            }
        }
    }

    public As_BonDeLivraison genererLivraisonPourPrerempli(String id, Connection c) throws Exception {
        boolean estOuvert = false;
        As_BonDeLivraison bl = new As_BonDeLivraison();
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
                c.setAutoCommit(false);
            }
            As_BonDeCommande f = new As_BonDeCommande();
            f = (As_BonDeCommande)f.getById(id,"AS_BONDECOMMANDE", c);
            bl = f.createBL();
            As_BonDeCommande_Fille_CPL_Livre vLib = new As_BonDeCommande_Fille_CPL_Livre();
            vLib.setNomTable("as_bondecommande_livre");
            vLib.setIdbc(id);
            As_BonDeCommande_Fille_CPL_Livre[] details = (As_BonDeCommande_Fille_CPL_Livre[]) CGenUtil.rechercher(vLib,null,null,c,"");
            Vector<As_BonDeLivraison_Fille> listeBLF = new Vector<>();
            for(As_BonDeCommande_Fille_CPL_Livre detail: details){
                As_BonDeLivraison_Fille blf = new As_BonDeLivraison_Fille();
                blf.setIdbc_fille(detail.getId());
                blf.setProduit(detail.getProduit());
                blf.setPu(detail.getPu());
                blf.setTauxdechange(detail.getTauxDeChange());
                blf.setUnite(detail.getUnite());
                blf.setDesignation(detail.getProduitlib());
                double qteReste = 0.0;
                try{
                    qteReste = Double.parseDouble(detail.getQte_reste());
                }
                catch(Exception e){
                    qteReste = 0.0;
                }
                blf.setQuantite(qteReste);
                listeBLF.add(blf);
            }
            bl.setFille(listeBLF.toArray(new As_BonDeLivraison_Fille[0]));


            return bl;
        } catch (Exception e) {
            if(estOuvert){
                c.rollback();
            }
            throw e;
        } finally {
            if (estOuvert && c != null) {
                c.close();
            }
        }
    }

    public As_BonDeCommande getAS_BonDommance_By_ID(String id, Connection c) throws Exception {
        boolean estOuvert = false;
        As_BonDeCommande f = new As_BonDeCommande();
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
                c.setAutoCommit(false);
            }
            f = (As_BonDeCommande)f.getById(id,"AS_BONDECOMMANDE", c);
            return f;
        } catch (Exception e) {
            if(estOuvert){
                c.rollback();
            }
            throw e;
        } finally {
            if (estOuvert && c != null) {
                c.close();
            }
        }
    }


    public String genererLivraison(String u, Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
                c.setAutoCommit(false);
            }
            As_BonDeCommande f = new As_BonDeCommande();
            f.setId(this.getId());
            As_BonDeCommande[] ff = (As_BonDeCommande[]) CGenUtil.rechercher(f, null, null, c, "");
            String idLivraison = ff[0].genererBL(u, c).getId();
            if(estOuvert){
                c.commit();
            }
            return idLivraison;
        } catch (Exception e) {
            if(estOuvert){
                c.rollback();
            }
            throw e;
        } finally {
            if (estOuvert) {
                c.close();
            }
        }
    }

    public As_BonDeCommande_Fille[] getBonDeCommandeFille(Connection c) throws Exception {
        As_BonDeCommande_Fille abfCrt=new As_BonDeCommande_Fille();
        abfCrt.setIdbc(this.getId());
        As_BonDeCommande_Fille[] abf=(As_BonDeCommande_Fille[]) CGenUtil.rechercher(abfCrt , null, null, c, " ");
        return abf;
    }

    public FactureFournisseurDetails[] getDetailsFacture(String idBL, Connection c) throws Exception {
        boolean connectionWasOpenedInside = false;

        FactureFournisseurDetails[] var5;
        try {
            if (c == null) {
                c = (new UtilDB()).GetConn();
                connectionWasOpenedInside = true;
            }
            As_BonDeCommande bc = new As_BonDeCommande();
            bc.setId(idBL);
            As_BonDeCommande[] searchBL = (As_BonDeCommande[])CGenUtil.rechercher(bc, (String[])null, (String[])null, c, "");
            if (searchBL != null && searchBL.length != 0) {
                As_BonDeCommande bl = searchBL[0];
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setId(bl.getFournisseur());
                Fournisseur[] searchFournisseur = (Fournisseur[])CGenUtil.rechercher(fournisseur, (String[])null, (String[])null, c, "");
                if (searchFournisseur != null && searchFournisseur.length != 0) {
                    fournisseur = searchFournisseur[0];

                    As_BonDeCommande_Fille[] blFilles = bl.getBonDeCommandeFille(c);
                    FactureFournisseurDetails[] detailsFille = new FactureFournisseurDetails[blFilles.length];

                    for(int i = 0; i < blFilles.length; ++i) {
                        detailsFille[i] = blFilles[i].toFactureFournisseurDetails();
                        detailsFille[i].setMois(Utilitaire.getMoisEnCoursReel());
                        detailsFille[i].setAnnee(Utilitaire.getAnnee(Utilitaire.dateDuJourSql()));

                        As_BonDeCommande_Fille_CPL bfc = new As_BonDeCommande_Fille_CPL();
                        bfc.setNomTable("AS_BONDECOMMANDE_CPL_RECEPT");

                        bfc.setIdbc(idBL);
                        bfc.setProduit(blFilles[i].getProduit());


                        As_BonDeCommande_Fille_CPL[] sr = (As_BonDeCommande_Fille_CPL[]) CGenUtil.rechercher(bfc, null, null, c, "");
                        
                        if (sr.length != 0) {
                            detailsFille[i].setQte(sr[0].getResteFacture());

                            // validation reste a livre
                            if (sr[0].getResteLivre() > 0) {
                                throw new Exception(
                                        "Le produit " + sr[0].getProduitlib() +
                                                " doit \u00EAtre livr\u00E9 en totalit\u00E9 pour g\u00E9n\u00E9rer une facture."
                                );
                            }
                        }
                    }

                    FactureFournisseurDetails[] var28 = detailsFille;
                    return var28;
                }

                FactureFournisseurDetails[] var8 = new FactureFournisseurDetails[0];
                return var8;
            }

            var5 = new FactureFournisseurDetails[0];
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connectionWasOpenedInside && c != null) {
                try {
                    c.close();
                } catch (Exception ce) {
                    ce.printStackTrace();
                }
            }

        }

        return var5;
    }

    public DmdAchat findDmdAchat(Connection c ) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
            }

            DmdAchat[] tvcpl = (DmdAchat[]) CGenUtil.rechercher(new DmdAchat(), null, null, c," and id = '" + this.getIddmdachat() + "'");
            if(tvcpl.length != 0 ) {
                return tvcpl[0];
            } else {
                return null;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (estOuvert && c != null) {
                c.close();
            }
        }
    }

    public TravauxCpl findTravaux(Connection c ) throws Exception {
            DmdAchat dmdAchat = this.findDmdAchat(null);
            TravauxCpl tvcpl = null;
            if(dmdAchat == null) {
                return null;
            } else {
                tvcpl = dmdAchat.findTravaux(null);
                return tvcpl;
            }
    }

    public FactureFournisseur genererFacture() throws Exception {
        FactureFournisseur fact = new FactureFournisseur();
        fact.setIdFournisseur(this.getFournisseur());
        fact.setIdModePaiement(this.getModepaiement());
        fact.setDaty(this.getDaty());
        fact.setIdDevise(this.getIdDevise());
        fact.setIdMagasin(this.getIdMagasin());
        fact.setIdBc(this.getId());
        fact.setIdtypefacture(ConstanteSocobis.TYPE_FACTURE);
        if(this.findTravaux(null)!=null) {
            fact.setIdDmdAchat(this.getIddmdachat());
        }
        fact.setService(this.getService());
        return fact;
    }

    public String getRefproforma() {
        return refproforma;
    }

    public void setRefproforma(String refproforma) {
        this.refproforma = refproforma;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {

        As_BonDeCommande_Fille[] filles = (As_BonDeCommande_Fille[]) this.getFille();

        //for (As_BonDeCommande_Fille f : filles)
        //{
           // f.controle(c, this.getIddmdachat());
        //}

        return super.createObject(u, c);
    }

    private FactureFournisseurDetails[] regrouperDetails(FactureFournisseurDetails[] details) throws Exception {
        Map<String, FactureFournisseurDetails> map = new LinkedHashMap<>();
        for (FactureFournisseurDetails detail : details) {
            String key = detail.getIdProduit() + "_" + detail.getPu();
            FactureFournisseurDetails existant = map.get(key);
            if (existant == null) {
                map.put(key, detail);
            } else {
                existant.setQte(existant.getQte() + detail.getQte());
                String ids = existant.getIdbcDetail() + ";" + detail.getIdbcDetail();
                existant.setIdbcDetail(ids);
            }
        }
        return map.values().toArray(new FactureFournisseurDetails[0]);
    }

    public FactureFournisseurDetails[] getDetailsFactureByIdsBC(String[] idsBCF, Connection c) throws Exception {
        boolean canClose = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                canClose = true;
            }
            String aWhere = " and id in ("+Utilitaire.tabToString(idsBCF, "'", ",")+" )";
            As_BonDeCommande_Fille_CPL abf = new As_BonDeCommande_Fille_CPL();
            abf.setNomTable("AS_BC_RESTE_A_FACTURER");
            As_BonDeCommande_Fille_CPL[] blf = (As_BonDeCommande_Fille_CPL[]) CGenUtil.rechercher(abf,null,null,c,aWhere);
            FactureFournisseurDetails[] details = new FactureFournisseurDetails[blf.length];
            String[] idProduits = new String[details.length];
            for (int i = 0; i < details.length ; i++) {
                details[i] = new FactureFournisseurDetails();
                details[i].setIdProduit(blf[i].getProduit());
                details[i].setPu(blf[i].getPu());
                details[i].setQte(blf[i].getQuantite());
                details[i].setIdbcDetail(blf[i].getId());
                details[i].setIdFournisseur(blf[i].getFournisseur());
                idProduits[i] = blf[i].getProduit();
            }
            aWhere = " and id in ("+Utilitaire.tabToString(idProduits, "'", ",")+" )";
            IngredientsLib i = new IngredientsLib();
            i.setNomTable("ST_INGREDIENTSAUTOACHAT_CPL");
            IngredientsLib[] ing = (IngredientsLib[]) CGenUtil.rechercher(i,null,null,c,aWhere);
            String fournisseur = null;
            for (int j = 0; j < details.length; j++) {
                IngredientsLib produit = (IngredientsLib)AdminGen.findUnique(ing,new String[]{"id"},new String[]{details[j].getIdProduit()});
                if (fournisseur != null && !fournisseur.equals(blf[j].getFournisseurlib())) {
                    throw new Exception("Impossible de g\u00E9n\u00E9rer une facture avec diff\u00E9rents fournisseurs.");
                }
                fournisseur = blf[j].getFournisseurlib();
                if (produit != null) {
                    details[j].setCompte(produit.getCompte());
                    details[j].setDesignation(produit.getLibelle());
                }
            }
            return regrouperDetails(details);
        } finally {
            if (canClose) c.rollback();
        }
    }
}
