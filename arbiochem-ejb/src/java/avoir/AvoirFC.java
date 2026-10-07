/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avoir;

import bean.AdminGen;
import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ClassMere;
import client.Client;
import faturefournisseur.As_BonDeLivraison;
import faturefournisseur.As_BonDeLivraison_Fille;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mg.cnaps.compta.ComptaEcriture;
import mg.cnaps.compta.ComptaLettrage;
import mg.cnaps.compta.ComptaSousEcriture;
import prevision.Prevision;
import prevision.PrevisionComplet;
import rapport.Utilitaire;
import ristourne.Ristourne;
import ristourne.RistourneDetails;
import stock.MvtStock;
import stock.MvtStockFille;
import utilitaire.UtilDB;
import utils.ConstanteSocobis;
import utils.ConstanteStation;
import vente.*;

/**
 *
 * @author randr
 */
public class AvoirFC extends ClassMere{

    String id;
    String designation;
    String idMagasin;
    Date daty;
    String remarque;
    int etat;
    String idOrigine;
    String idClient;
    String idVente;
    String idMotif;
    String idCategorie;
    String compte,compteauxiliaire;
    String idtypeavoir;
    String numavoir, idristourne;

    ComptaEcriture ecritureAvoir;

    public ComptaEcriture getEcritureAvoir() {
        return ecritureAvoir;
    }

    public void setEcritureAvoir(ComptaEcriture ecritureAvoir) {
        this.ecritureAvoir = ecritureAvoir;
    }

    public String getIdristourne() {
        return idristourne;
    }

    public void setIdristourne(String idristourne) {
        this.idristourne = idristourne;
    }

    public double getPuFacture(String idProduit) throws Exception{
        try {
            if(this.getIdristourne()!=null){
                String awhere = " AND IDRISTOURNE='"+this.getIdristourne()+"' AND IDPRODUIT='"+idProduit+"'";
                RistourneDetails [] ristournes = (RistourneDetails[]) CGenUtil.rechercher(new RistourneDetails(), null,null,null,awhere);
                if(ristournes.length>0){
                   return ristournes[0].getPu();
                }
            }
            return 1;
        } catch (Exception e) {
            throw e;
        }
    }

    public String getNumavoir() {
        return numavoir;
    }

    public void setNumavoir(String numavoir) {
        this.numavoir = numavoir;
    }

    public String getCompteauxiliaire() {
        return compteauxiliaire;
    }

    public void setCompteauxiliaire(String compteauxiliaire) {
        this.compteauxiliaire = compteauxiliaire;
    }

    AvoirFCFille [] avoirDetails;
    
    public AvoirFC(){
        this.setNomTable("AvoirFC");
    }
    
    public AvoirFC(String nomtable){
        this.setNomTable(nomtable);
    }
    
    public String getId() {
        return id;
    }
    @Override
    public String getLiaisonFille() {
        return "idAvoirFC";
    } 
    @Override
    public  String getNomClasseFille() {
        return "avoir.AvoirFCFille";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(String idOrigine) {
        this.idOrigine = idOrigine;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdVente() {
        return idVente;
    }

    public void setIdVente(String idVente) {
        this.idVente = idVente;
    }

    public String getIdMotif() {
        return idMotif;
    }

    public void setIdMotif(String idMotif) {
        this.idMotif = idMotif;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public AvoirFCFille[] getAvoirDetails() {
        return avoirDetails;
    }

    public void setAvoirDetails(AvoirFCFille[] avoirDetails) {
        this.avoirDetails = avoirDetails;
    }
    

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
    public AvoirFCLib getAvoirComplet(Connection c)throws Exception
    {
        return (AvoirFCLib)new AvoirFCLib().getById(this.getId(), "AVOIRFCLIB_CPL_VISEE", c);
    }
    public PrevisionComplet[] getPrevisionCompletFactureAttache(Connection c)throws Exception
    {
        PrevisionComplet crt=new PrevisionComplet();
        crt.setIdFacture(this.getIdVente());
        return (PrevisionComplet[])CGenUtil.rechercher(crt, null,null, c, " order by daty asc");
    }
    public Prevision genererPrevision(String u, Connection c) throws Exception{
        PrevisionComplet prevFactRattache[]=this.getPrevisionCompletFactureAttache(c);
        if(prevFactRattache==null||prevFactRattache.length==0)return null;
        Prevision mere = new Prevision();
        AvoirFCLib avoirComplet = this.getAvoirComplet(c);
        mere.setDaty(prevFactRattache[prevFactRattache.length-1].getDaty());//Prendre la dernière echeance de la prevision
        mere.setDebit(avoirComplet.getMontantTTCAr());
        mere.setIdFacture(this.id);
        mere.setIdCaisse(ConstanteStation.idCaisse);
        mere.setIdDevise("AR");
        mere.setTaux(1);
        mere.setDesignation("Pr&eacute;vision rattach&eacute;e au Facture d avoir N"+this.getId());
        return ( Prevision ) mere.createObject(u, c);
    }
    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                estOuvert = true;
                c = new UtilDB().GetConn();
                c.setAutoCommit(false);
            }
            AvoirFCFille[] avoirFCFille = (AvoirFCFille[]) this.getFille();
            Prevision[] planPaiement = null;
            for (int i = 0; i < avoirFCFille.length; i++) {
                planPaiement = avoirFCFille[i].getPlanPaiementByIDFacture(u,c);
                if(planPaiement != null && planPaiement.length>0){
                    planPaiement[0].setCredit(planPaiement[0].getCredit() - avoirFCFille[i].getMontantttcar());
                    planPaiement[0].updateToTableWithHisto(u,c);
                }
            }
            //CheckEtatStockVenteDetails(c);
            super.validerObject(u, c);
            System.out.println("Type Avoir : " + this.getIdtypeavoir());
            if (this.getIdtypeavoir()!=null && this.getIdtypeavoir().equals(ConstanteSocobis.typeavoiravecstock)) {
                genererBR(u, c);
            }else if (this.getIdtypeavoir()!=null && this.getIdtypeavoir().equals(ConstanteSocobis.typeavoirengager)) {
                System.out.println("Desengagement du stock pour l'avoir avec stock engagé");
                desengagerStock(u, c);
            }
            //Jerena miaraka am Fitia ilay ID caisse
            //this.genererPrevision(u, c);
            genererEcriture(u, c);
            this.genererLettrage(this.getIdOrigine(),u,c);
            //createMvtStockSortie(u, c);
            if(estOuvert==true)c.commit();
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
    public ComptaLettrage genererLettrage(String idOrigine, String u, Connection c) throws Exception
    {
        if(idOrigine==null||idOrigine.equalsIgnoreCase(""))return null;
        ComptaEcriture gauche=FactureCF.getEcritureAvecFille(idOrigine,null,null,c);
        if(gauche==null)return null;
        List<ComptaSousEcriture> resultats = Arrays.stream((ComptaSousEcriture[])gauche.getFille())
                .filter(se -> se.getCompte_aux () != null&&se.getCompte_aux().equalsIgnoreCase("")==false)
                .collect(Collectors.toList());
        if(resultats==null || resultats.size()==0)return null;
        ComptaSousEcriture seGauche= resultats.get(0);
        List<ComptaSousEcriture> resultatsDroite = Arrays.stream((ComptaSousEcriture[])this.getEcritureAvoir().getFille())
                .filter(se -> se.getCompte_aux () != null&&se.getCompte_aux().equalsIgnoreCase("")==false)
                .collect(Collectors.toList());
        if(resultatsDroite==null || resultatsDroite.size()==0)return null;
        ComptaSousEcriture seDroite=resultatsDroite.get(0);
        return new ComptaLettrage().createLettrageCSEMF(new String[]{seGauche.getId(),seDroite.getId()},null,u,c);

    }

    public void sortieStock(String u, Connection c) throws Exception {
        Connection cx = c;
        try {
            if (c == null) {
                cx = new UtilDB().GetConn();
                cx.setAutoCommit(false);
            }
            As_BonDeLivraison br = new As_BonDeLivraison();
            br.setIdbc(this.getId());
            As_BonDeLivraison[] brs = (As_BonDeLivraison[]) CGenUtil.rechercher(br, null, null,cx, " ");
            if (brs.length > 0) {
                throw new Exception("Sortie de stock d\\u00E9j\\u00E0 effectu\\u00E9e pour l'avoir " + this.getId());
            } else {
                genererBR(u, cx);
                cx.commit();
            }
        } catch (Exception e) {
            cx.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (cx != null) {
                cx.close();
            }
        }
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
            
            if(filles[i].getDebit()>0 || filles[i].getCredit()>0) mere.ajouterFille(filles[i]);
        }
        mere.createObject(u, c);
        this.setEcritureAvoir(mere);
    }
    
    public ComptaSousEcriture[] genererSousEcriture(Connection c) throws Exception{
        ComptaSousEcriture[] compta={};
        boolean canClose=false;
        try{
            if(c==null){
                c=new UtilDB().GetConn();
                canClose=true;
            }
            AvoirFCLib[] avoirs = (AvoirFCLib[]) CGenUtil.rechercher(new AvoirFCLib("AVOIRFC_MERE_MONTANT"), null, null, c, " and id = '"+this.getId()+"'");
            if(avoirs.length<1) throw new Exception("Avoir Introuvable");
            Client cli = getClient(c);
            this.setCompte(cli.getCompte());
            AvoirFCFille [] details = this.getDetails(c);
            double montantTva = AdminGen.calculSommeDouble(details,"montantTVAAr");
            double montantTTC = AdminGen.calculSommeDouble(details,"montantTTCAr");
            int taille = details.length;
            compta = new ComptaSousEcriture[taille+2];
            int i=0;
            for(i=i;i<taille;i++){ 
                compta[i]=new ComptaSousEcriture();
                compta[i].setLibellePiece(this.getDesignation());
                compta[i].setRemarque(details[i].getDesignation());
                compta[i].setCompte(details[i].getCompte());
                if(this.getIdtypeavoir().compareToIgnoreCase(ConstanteSocobis.ID_AVOIR_RISTOURNE)==0){
                    compta[i].setCompte(details[i].getCompteRistourne(c));
                }
                compta[i].setDebit(details[i].getMontanthtar());
            }
            
            compta[i]=new ComptaSousEcriture();
            compta[i].setLibellePiece("TVA Collectee");
            compta[i].setRemarque("TVA Collectee");
            compta[i].setCompte(ConstanteStation.compteTVACollecte);
//            compta[i].setDebit((montantHT-retenue) * ((this.getTva()/100)));
            compta[i].setDebit(montantTva);
            i++;
            
            compta[i]=new ComptaSousEcriture();
            compta[i].setLibellePiece("Avoir Client "+avoirs[0].getClientlib());
            compta[i].setRemarque("Avoir Client "+avoirs[0].getClientlib());
            compta[i].setCompte(this.getCompte());
            compta[i].setCredit(montantTTC);
            compta[i].setCompte_aux(this.getIdClient());
        } catch(Exception e){
            throw e;
        } finally {
            if(canClose){
                c.close();
            }
        }
        return compta;
    }
    
    public AvoirFCFille[] getDetails(Connection c) throws Exception{           
        AvoirFCFille[] avoirfcFille = null;        
           try{
            String awhere = " and IDAVOIRFC = '"+this.getId()+"'";
            avoirfcFille = (AvoirFCFille[]) CGenUtil.rechercher(new AvoirFCFille("AVOIRFC_GRP_VISEE"), null, null, c, awhere);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return avoirfcFille;
    }
    
    public Client getClient(Connection c) throws Exception{
        Client client = new Client();
        Client[] clients = (Client[]) CGenUtil.rechercher(client,null,null,c, " and id = '"+this.getIdClient()+"'");
        if(clients.length > 0){
            return clients[0];
        }
        throw new Exception("Le client n'existe pas");
    }
    
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("AVRFC", "GETSEQAVOIRFC");
        this.setId(makePK(c));
    }
    
    @Override
    public void controlerUpdate(Connection c) throws Exception {
        if(this.etat<1) this.setEtat(1);
    }

    public String getIdtypeavoir() {
        return idtypeavoir;
    }

    public void setIdtypeavoir(String idtypeavoir) {
        this.idtypeavoir = idtypeavoir;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        NumeroFacture numFactF = new NumeroFacture();
        numFactF.setNomTable("V_NUMEROAVOIR_EN_COURS");
        NumeroFacture[] numFact = (NumeroFacture[]) CGenUtil.rechercher(numFactF, null, null, c, "");
        this.setNumavoir(numFact[0].getProchain_num_format());
        if(this.getIdtypeavoir().compareToIgnoreCase(ConstanteSocobis.ID_AVOIR_RISTOURNE)==0){
            return super.createObject(u, c);
        }
        AvoirFCFille [] filles = (AvoirFCFille[]) this.getFille();
        VenteDetails v = new VenteDetails();
        v.setIdVente(this.getIdVente());
        VenteDetails [] venteDetails = (VenteDetails[]) CGenUtil.rechercher(v,null,null,c,"");
        if (venteDetails.length > 0) {
            Map<String, Double> qteParProduit = Arrays.stream(venteDetails)
                    .collect(Collectors.toMap(
                            vd -> vd.getIdProduit().toLowerCase(),
                            VenteDetails::getQte,
                            Double::sum
                    ));
            if (filles != null) {
                for (AvoirFCFille fille : filles) {
                    Double qteVente = qteParProduit.get(
                            fille.getIdProduit().toLowerCase()
                    );
                    if (qteVente != null) {
                        fille.setQte(fille.getQte());
                        //fille.setQte(qteVente - fille.getQte());
                    }
                }
            }
        }
        return super.createObject(u, c);
    }

    public void genererBR(String u, Connection c) throws Exception {
        AvoirFCFille[] details = (AvoirFCFille[]) this.getFille("AvoirFCFille", c, "");
        As_BonDeLivraison   br = new As_BonDeLivraison();
        br.setIdFournisseur(ConstanteSocobis.fournisseurSocobis);
        br.setMagasin(this.getIdMagasin());
        br.setIdbc(this.getId());
        br.setDaty(utilitaire.Utilitaire.dateDuJourSql());
        br.setEtat(1);
        br.setRemarque("Remarque par avoir n° " + this.getId() + "");
        System.out.println("Creation du bon de livraison pour l'avoir n° " + this.getId() + " avec " + details.length + " détails");
        br = (As_BonDeLivraison) br.createObject(u, c);
        As_BonDeLivraison_Fille[] brFilles = new As_BonDeLivraison_Fille[details.length];
        for (int i = 0; i < details.length; i++) {
            brFilles[i] = new As_BonDeLivraison_Fille();
            brFilles[i].setProduit(details[i].getIdProduit());
            brFilles[i].setQuantite(details[i].getQte());
            brFilles[i].setPu(details[i].getPu());
            brFilles[i].setNumbl(br.getId());
            brFilles[i].setIdbc_fille(details[i].getId());
            brFilles[i].setDesignation(details[i].getDesignation());
            brFilles[i].setTauxdechange(details[i].getTauxDeChange());;
            brFilles[i] = (As_BonDeLivraison_Fille) brFilles[i].createObject(u, c);
            System.out.println("Creation du détail du bon de livraison pour le produit " + details[i].getIdProduit() + " avec la quantité " + details[i].getQte());
        }
    }

    public void desengagerStock(String u, Connection c) throws Exception {
        String idVente = this.getIdVente();

        MvtStock mvtSearch = new MvtStock();
        mvtSearch.setIdVente(idVente);
        mvtSearch.setNomTable("stock_engage");

        MvtStock[] resultatRecherche = (MvtStock[]) CGenUtil.rechercher(mvtSearch, null, null, c, "");
        if (resultatRecherche.length == 0) {
            throw new Exception("Stock avec engagement introuvable pour le vente : " + idVente + " associé a la blc : " + this.getId());
        }

        MvtStock stockEngage = resultatRecherche[0];
        MvtStockFille[] stockEngageFilles = (MvtStockFille[]) stockEngage.getFille("STOCK_ENGAGE_FILLE", c, "");

        AvoirFCFille[] stockFille = (AvoirFCFille[]) CGenUtil.rechercher(new AvoirFCFille(), null, null, c, " AND IDAVOIRFC = '" + this.getId() + "'");
        for (AvoirFCFille fille : stockFille) {
            boolean finded = false;
            for (MvtStockFille filleEngage : stockEngageFilles) {
                filleEngage.setNomTable("STOCK_ENGAGE_FILLE");
                if (fille.getIdProduit().equals(filleEngage.getIdProduit())) {
                    filleEngage.setSortie(filleEngage.getSortie() - fille.getQte());
                    if (filleEngage.getSortie() < 0) {
                        throw new Exception("Quantité superieure à quantité engagé");
                    }
                    filleEngage.updateToTable(c);
                    finded = true;
                    break;
                }
            }

            if (!finded) {
                throw new Exception("Stock avec engagement introuvable pour le produit : " + fille.getIdProduit());
            }
        }   
    }
}
