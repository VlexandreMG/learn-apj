/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faturefournisseur;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;
import java.util.Vector;

import stock.MvtStock;
import stock.MvtStockFille;
import utilitaire.Utilitaire;
import utils.ConstanteEtatStation;
import utils.ConstanteSocobis;
import utils.ConstanteStation;
import vente.As_BondeLivraisonClient;
import utilitaire.UtilDB;   
import constante.ConstanteEtat;
/**
 *
 * @author Mirado
 */
public class As_BonDeLivraison extends ClassMere{
    
    String id,remarque,idbc;
    Date daty;
    int etat;
    String magasin;
    String idFournisseur;
    String idFactureFournisseur;
    String idDepenseDivers;

    public As_BonDeLivraison() throws Exception {
        this.setNomTable("AS_BONDELIVRAISON");
        this.setLiaisonFille("numbl");
	    // this.setNomClasseFille("faturefournisseur.As_BonDeLivraison_Fille");
       setNomClasseFille("faturefournisseur.As_BonDeLivraison_Fille");
    }
    
    public void construirePK(Connection c) throws Exception{
        this.preparePk("BL", "GETSEQBONDELIVRAISON");
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
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDepenseDivers() {
        return idDepenseDivers;
    }

    public void setIdDepenseDivers(String idDepenseDivers) {
        this.idDepenseDivers = idDepenseDivers;
    }

    public void setIdFactureFournisseur(String idFactureFournisseur) {
        this.idFactureFournisseur = idFactureFournisseur;
    }

    public String getIdFactureFournisseur() {
        return idFactureFournisseur;
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

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }
    @Override
    public String getLiaisonFille() {
        return "numbl";
    }
    @Override
    public String getNomClasseFille() {
        return "faturefournisseur.As_BonDeLivraison_Fille";
    }

    public As_BonDeLivraison_Fille[] getBonDeLivraisonFille(Connection c) throws Exception {
        As_BonDeLivraison_Fille abfCrt=new As_BonDeLivraison_Fille();
        abfCrt.setNumbl(this.getId());
        As_BonDeLivraison_Fille[] abf=(As_BonDeLivraison_Fille[]) CGenUtil.rechercher(abfCrt , null, null, c, " ");
        return abf;
    }
    public MvtStock createMvtStock() throws Exception{
        MvtStock ms=new MvtStock();
        ms.setDesignation("livraison "+this.getRemarque());
        ms.setIdTypeMvStock(ConstanteStation.TYPEMVTSTOCKENTREE);
        ms.setIdMagasin(this.getMagasin());
        ms.setDaty(Utilitaire.dateDuJourSql());
        ms.setIdTransfert(this.getId());
        return ms;
    }

    public MvtStockFille[] createMvtStockFille(Connection c,String idMere)throws Exception{
        boolean estOuvert = false;
        try {
            if(c==null){
                c = new UtilDB().GetConn();
                estOuvert = true;
            }
            As_BonDeLivraison_Fille[] fille = getBonDeLivraisonFille(c);
            MvtStockFille[] mvtStockFilles = new MvtStockFille[fille.length];
            int index=0;
            for (As_BonDeLivraison_Fille bLivraison_Fille : fille) {
                MvtStockFille f = new MvtStockFille();
                f.setIdProduit(bLivraison_Fille.getProduit());
                f.setEntree(bLivraison_Fille.getQuantite());
                f.setSortie(0);
                f.setIdTransfertDetail(bLivraison_Fille.getId());
                f.setIdMvtStock(idMere);
                mvtStockFilles[index]= f;
                index++;
            }
            return mvtStockFilles;
        } catch (Exception e) {
            throw e;
        }finally{
            if(estOuvert) c.close();
        }
    }

    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) throws Exception {
        if( this.getMode().equals("modif") && idFournisseur!=null && idFournisseur.isEmpty() ){
            throw new Exception("Le Fournisseur ne doit pas etre null");
        }
        this.idFournisseur = idFournisseur;
    }
    
    public MvtStock genereMvtStock( Connection c ) throws Exception{
        boolean connectionWasOpenedInside = false;
        try{
            if( c == null ){
                c = new UtilDB().GetConn();
                connectionWasOpenedInside = true;
            }

            As_BonDeLivraison_Fille_Cpl[] filles = (As_BonDeLivraison_Fille_Cpl[]) this.getBonDeLivraisonFilleCpl("RAPPROCHEMENT_BF_LIVREE", c);
            boolean hasResteAEntrer = false;

            for (As_BonDeLivraison_Fille_Cpl fille : filles) {
                if (fille.getResteAEntrer() != 0) {
                    hasResteAEntrer = true;
                    break;
                }
            }

            if (!hasResteAEntrer) {
                throw new Exception("Ce bon de livraison est d\u00e9j\u00e0 entr\u00e9 en stock");
            }
            MvtStock mouvement = new MvtStock();
            mouvement.setIdobjet(this.getId());
            mouvement.setDaty(this.getDaty());
            mouvement.setDesignation("Mouvement de Stock pour " + this.getId());
            mouvement.setIdPoint(this.getMagasin());
            mouvement.setIdTypeMvStock(ConstanteSocobis.TYPE_MVT_ENTREE);
            mouvement.setIdTransfert(this.getId());
            
            // Maintenant micreer mouvement fille hoan 'ireo'

            MvtStockFille[] mouvements = new MvtStockFille[filles.length];
            for( int i = 0; i < filles.length ; i++ ){
                mouvements[i] = filles[i].createMvtStockFille();
                mouvements[i].setIdMvtStock(mouvement.getId());
                mouvements[i].setPu(filles[i].getPuar());
                mouvements[i].setEntree(filles[i].getResteAEntrer());
            }
            mouvement.setFille(mouvements);
            return mouvement;

        }catch(Exception e){
            throw e;
        }finally{
            if( connectionWasOpenedInside ){
                c.close();
            }
        }
    }

    public void genererMvtStockPersist( String refUser ) throws Exception{
        Connection connection = null;
        boolean isOpened = false;
        try{
            if( connection == null ){
                connection = new UtilDB().GetConn();
                isOpened = true;
            }
            As_BonDeLivraison blc=(As_BonDeLivraison) this.getById(this.getId(),null,connection);
            blc.setNomClasseFille("faturefournisseur.As_BonDeLivraison_Fille");
	        MvtStock mvt = blc.genereMvtStock(connection);

        }catch(Exception e){
            throw e;
        } finally {
            if (isOpened)
                connection.close();
        }
    }

    public static As_BonDeLivraison[] getAll(String[] ids, Connection co) throws Exception {
        As_BonDeLivraison bl = new As_BonDeLivraison();
        As_BonDeLivraison[] bls = (As_BonDeLivraison[]) CGenUtil.rechercher(bl, null, null, co,
                " and id in (" + Utilitaire.tabToString(ids, "'", ",")+ ")");
        return bls;
    }

    public static void controlerFournisseur(As_BonDeLivraison[] bls) throws Exception {
        String idFournisseur = "";
        for (As_BonDeLivraison item : bls) {
            if (idFournisseur.equals("") == false) {
                if (idFournisseur.equals(item.getIdFournisseur()) == false) {
                    throw new Exception("Tiers different");
                }
            }
            idFournisseur = item.getIdFournisseur();
        }
    }

    public FactureFournisseurDetails[] getDetailsFacturePrest(String idBL, Connection c) throws Exception {
        Vector<FactureFournisseurDetails> allDetails = new Vector<>();
        boolean connectionWasOpenedInside = false;

        try {
            if (c == null) {
                c = new utilitaire.UtilDB().GetConn();
                connectionWasOpenedInside = true;
            }

            this.setId(idBL);
            As_BonDeLivraison[] searchBL = (As_BonDeLivraison[]) CGenUtil.rechercher(this, null, null, c,"");
            if (searchBL == null || searchBL.length == 0) return new FactureFournisseurDetails[0];

            As_BonDeLivraison bl = searchBL[0];
            As_BonDeLivraison_Fille[] blFilles = bl.getBonDeLivraisonFille(c);

            for (As_BonDeLivraison_Fille blFille : blFilles) {
                FactureFournisseurDetails[] detailsFille = blFille.toFactureFournisseurPrestataire(c);
                if (detailsFille != null) {
                    for (FactureFournisseurDetails d : detailsFille) {
                        allDetails.add(d);
                    }
                }
            }

            FactureFournisseurDetails[] result = new FactureFournisseurDetails[allDetails.size()];
            allDetails.copyInto(result);
            return result;

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
    }

    public FactureFournisseurDetails[] getDetailsFacture(String idBL, Connection c) throws Exception {
        boolean connectionWasOpenedInside = false;

        try {
            if (c == null) {
                c = new utilitaire.UtilDB().GetConn();
                connectionWasOpenedInside = true;
            }

            this.setId(idBL);
            As_BonDeLivraison[] searchBL = (As_BonDeLivraison[]) CGenUtil.rechercher(this, null, null,c, "");
            if (searchBL == null || searchBL.length == 0) return new FactureFournisseurDetails[0];

            As_BonDeLivraison bl = searchBL[0];
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(bl.getIdFournisseur());
            As_BonDeCommande bc = new As_BonDeCommande();
            bc.setId(bl.getIdbc());
            As_BonDeCommande[] bcl = (As_BonDeCommande[]) CGenUtil.rechercher(bc, null, null,c, "");
            if(bcl.length > 0){
                bc = bcl[0];
            }else{
                bc = null;
            }

            Fournisseur[] searchFournisseur = (Fournisseur[]) CGenUtil.rechercher(fournisseur, null, null, c,"");
            if (searchFournisseur == null || searchFournisseur.length == 0) return new FactureFournisseurDetails[0];

            fournisseur = searchFournisseur[0];

            if ("TPF002".equalsIgnoreCase(fournisseur.getIdTypeFournisseur())) {
                return getDetailsFacturePrest(idBL, c);
            } else {
                As_BonDeLivraison_Fille[] blFilles = bl.getBonDeLivraisonFille(c);
                FactureFournisseurDetails[] detailsFille = new FactureFournisseurDetails[blFilles.length];
                for (int i = 0; i < blFilles.length; i++) {
                    detailsFille[i] = blFilles[i].toFactureFournisseurDetails(bc);
                }
                return detailsFille;
            }

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

    }
    public As_BonDeLivraison_Fille_Cpl[] getBonDeLivraisonFilleCpl(String nTBLFille, Connection c) throws Exception {
        As_BonDeLivraison_Fille_Cpl abfCrt = new As_BonDeLivraison_Fille_Cpl();
        abfCrt.setNumbl(this.getId());
        if(nTBLFille!=null && nTBLFille.compareTo("")!=0){
            abfCrt.setNomTable(nTBLFille);
        }
        As_BonDeLivraison_Fille_Cpl[] abf = (As_BonDeLivraison_Fille_Cpl[]) CGenUtil.rechercher(abfCrt, null, null, c, " ");
        return abf;
    }
    public MvtStock genererMvtStockPersist1( String refUser ) throws Exception{
        Connection connection = null;
        boolean isOpened = false;
        try{
            if( connection == null ){
                connection = new UtilDB().GetConn();
                isOpened = true;
            }
            As_BonDeLivraison blc=(As_BonDeLivraison) this.getById(this.getId(),null,connection);
            blc.setNomClasseFille("faturefournisseur.As_BonDeLivraison_Fille");
	        MvtStock mvt = blc.genereMvtStock(connection);
            mvt.setEtat(ConstanteEtat.getEtatCreer());
	        return mvt;

        }catch(Exception e){
            throw e;
        } finally {
            if (isOpened)
                connection.close();
        }
    }
    
    public FactureFournisseur genererFacture() throws Exception {
        As_BonDeCommande f = (As_BonDeCommande) new As_BonDeCommande().getById(this.getIdbc(),"AS_BONDECOMMANDE",null);
        FactureFournisseur fact = new FactureFournisseur();
        fact.setDesignation("Facturation de la commande num "+this.getId());
        fact.setIdFournisseur(this.getIdFournisseur());
        fact.setDaty(this.getDaty());
        fact.setIdMagasin(this.getMagasin());
        fact.setIdObjet(this.getId());
        if (this.getIdbc() != null) {
            fact.setIdDevise(f.getIdDevise());
            fact.setIdModePaiement(f.getModepaiement());
            fact.setService(f.getService());
        } else {
            fact.setIdDevise("AR");
        }
        fact.setIdRef(this.getIdDepenseDivers());
        return fact;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        As_BonDeCommande_Fille_CPL_Livre vLib = new As_BonDeCommande_Fille_CPL_Livre();
        vLib.setNomTable("as_bondecommande_livre");
        vLib.setIdbc(this.getIdbc());
        As_BonDeCommande_Fille_CPL_Livre[] details = (As_BonDeCommande_Fille_CPL_Livre[]) CGenUtil.rechercher(vLib,null,null,c,"");
        if (this.getIdbc() != null && this.getIdbc().startsWith("BC")){
            As_BonDeLivraison_Fille[] bonDeLivraisonFille = (As_BonDeLivraison_Fille[]) this.getFille();
            if (details.length > 0){
                for (int i = 0; i < details.length; i++) {
                    if (bonDeLivraisonFille[i].getQuantite() <= 0){
                        throw new Exception("La quantit\u00E9 livrer ne peut pas \u00EAtre 0");
                    }
                    else{
                        if (bonDeLivraisonFille[i].getQuantite() > Double.valueOf(details[i].getQte_reste())){
//                            throw new Exception("La quantit\u00E9 livrer ne peux pas \u00EAtre superieur a la quantit\u00E9 restante");
                        }
                    }
                }
            }
        }
        return super.createObject(u, c);
    }
    public String getMagasin(String id) throws Exception {
        As_BonDeLivraison crt = new As_BonDeLivraison();
        crt.setId(id);
       As_BonDeLivraison[] blf = (As_BonDeLivraison[]) CGenUtil.rechercher(crt, null, null, null, "");
       if (blf != null && blf.length > 0) {
       return blf[0].getMagasin();
       }
        return "";
   }

}
