package vente;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import chatbot.*;
import fabrication.Fabrication;
import fabrication.FabricationFille;
import fabrication.Of;
import fabrication.OfFille;
import produits.Ingredients;
import produits.IngredientsLib;
import produits.Recette;
import proforma.Proforma;
import proforma.ProformaDetailsLib;
import client.Client;
import magasin.Magasin;
import user.UserEJB;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import bean.*;
import faturefournisseur.As_BonDeCommande;
import vente.Carton;
import vente.CartonFille;

@AiTabDesc("La table BONDECOMMANDE_CLIENT représente les bons de commande clients, contenant les informations d'entête telles que le client, la date, l'état, le mode de paiement, la devise, le magasin, les frais et modalités de livraison, ainsi que la référence à une éventuelle proforma.")
public class BonDeCommande extends ClassMere implements ClassIA {
    private String id;
    private String idClient;
    private Date daty, dateBesoin;
    @AiColDesc("l'état de la commande : quand l'état est égale à 1 il est créer donc en cours et si il est égale à 11 alors il a été validé ou finalisé")
    private int etat;
    private String remarque;
    @AiColDesc("mode de paiement de la commande")
    private String modepaiement;
    private String reference;
    @AiColDesc("designation de livraison de la commande")
    private String designation;
    private String idDevise;
    private String idMagasin;
    private String idProforma;
    @AiColDesc("mode de livraison de la commande")
    private int modelivraison;
    private String lieuLivraison;
    @AiColDesc("date de livraison de la commande")
    private Date dateLivraison;
    private double fraislivraison;
    private String numeroBc;

    double montantTTC,montantTTCAR;

    public double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public double getMontantTTCAR() {
        return montantTTCAR;
    }

    public void setMontantTTCAR(double montantTTCAR) {
        this.montantTTCAR = montantTTCAR;
    }

    public String getNumeroBc() {
        return numeroBc;
    }

    public void setNumeroBc(String numeroBc) {
        this.numeroBc = numeroBc;
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

    public int getModelivraison() {
        return modelivraison;
    }

    public void setModelivraison(int modelivraison) {
        this.modelivraison = modelivraison;
    }

    public String getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(String idProforma) {
        this.idProforma = idProforma;
    }

    public double getFraislivraison() {
        return fraislivraison;
    }

    public void setFraislivraison(double fraislivraison) {
        this.fraislivraison = fraislivraison;
    }

    @Override
    public String getNomTableIA() {
        return "BONDECOMMANDE_CLIENT_FILLE_CPL";
    }

    @Override
    public String getUrlSaisie() {
        return "/socobis/pages/module.jsp?but=vente/bondecommande/bondecommande-saisie.jsp&currentMenu=MNDN000000001071";
    }
    @Override
    public ClassIA getClassSaisie() {
        return this;
    }
    @Override
    public String getUrlListe() {
        return "/socobis/pages/module.jsp?but=vente/vente-liste.jsp&currentMenu=MNDN000000007";
    }
    @Override
    public String getUrlAnalyse() {
        return "/socobis/pages/module.jsp?but=vente/vente-analyse.jsp&currentMenu=MNDN0000000111";
    }
    @Override
    public ClassIA getClassListe() {
        return this;
    }
    @Override
    public ClassIA getClassAnalyse() {
        return this;
    }

    public static BonDeCommande fromOcr(MereOcr mere) throws Exception {
        BonDeCommande f = new BonDeCommande();
        f.setDesignation(mere.getDesignation());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(mere.getDaty(), formatter);
        Date sqlDate = Date.valueOf(localDate);
        f.setDaty(sqlDate);

        Client client = new Client();
        Client[] clients = (Client[]) CGenUtil.rechercher(client, null, null,
                " and upper(nom) like '%" + mere.getIdOrigine().toUpperCase() + "%'");
        if (clients != null && clients.length > 0) {
            f.setIdClient(clients[0].getId());
        }

        Magasin magasin = new Magasin();
        Magasin[] magasins = (Magasin[]) CGenUtil.rechercher(magasin, null, null,
                " and upper(val) like '%" + mere.getIdMagasin().toUpperCase() + "%'");
        if (magasins != null && magasins.length > 0) {
            f.setIdMagasin(magasins[0].getId());
        }

        BonDeCommandeFille[] filles = fillesFromOcr(mere.getDetails().toArray(new FilleOcr[0]));
        f.setFille(filles);

        return f;
    }

    public static BonDeCommandeFille[] fillesFromOcr(FilleOcr[] filles) throws Exception {
        BonDeCommandeFille[] f = new BonDeCommandeFille[filles.length];
        for (int i = 0; i < filles.length; i++) {
            f[i] = BonDeCommandeFille.fromOcr(filles[i]);
        }
        return f;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public String genererFacture(String u) throws Exception {
        Connection c = null;
        try {
            c = new UtilDB().GetConn();
            c.setAutoCommit(false);
            String idFacture = genererFacture(u, c);
            c.commit();
            return idFacture;
        } catch (Exception e) {
            if (c != null)
                c.rollback();
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

    public void controlerFacturer(Connection c) throws Exception {
        Vente[] ventesAnterieur = (Vente[]) CGenUtil.rechercher(new Vente(), null, null, c,
                " AND idOrigine='" + this.getId() + "' and etat >0");
        if (ventesAnterieur.length > 0) {
            throw new Exception("Facture existante");
        }

    }

    public String genererFacture(String u, Connection c) throws Exception {
        BonDeCommande enBase = (BonDeCommande) this.getById(this.getId(), this.getNomTable(), c);
        BonDeCommandeFille vLib = new BonDeCommandeFille();
        vLib.setNomTable("BONDECOMMANDE_CLIENTF_taux");
        BonDeCommandeFille[] details = (BonDeCommandeFille[]) CGenUtil.rechercher(vLib, null, null, c,
                " AND idBc='" + this.getId() + "'");
        if (details.length > 0) {
            Vente vente = new Vente();
            vente.setIdClient(enBase.getIdClient());
            vente.setDesignation("Facture de la BC " + this.getId());
            vente.setDaty(Utilitaire.dateDuJourSql());
            vente.setIdOrigine(enBase.getId());
            vente.setIdMagasin(enBase.getIdMagasin());
            vente.setEtat(1);
            vente.createObject(u, c);
            for (BonDeCommandeFille detail : details) {
                VenteDetails detailVente = new VenteDetails();
                detailVente.setIdOrigine(detail.getId());
                detailVente.setIdProduit(detail.getProduit());
                detailVente.setQte(detail.getQuantite());
                detailVente.setPu(detail.getPu());
                detailVente.setTva(detail.getTva());
                detailVente.setIdVente(vente.getId());
                detailVente.setIdDevise(detail.getIdDevise());
                detailVente.setTauxDeChange(detail.getTaux());
                detailVente.createObject(u, c);
            }
            return vente.getId();
        }
        throw new Exception("Plus aucun article à facturer");
    }
//    verifier s'il y a encore des articles a facturer
    public boolean estFacture(Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
            }
            Vente[] details= (Vente[]) CGenUtil.rechercher(new Vente(), null, null, c,
                    " AND idOrigine='" + this.getId() + "' and etat >0");
            if (details.length > 0) return true;
            return false;
        }catch (Exception e) {
            throw e;
        }
        finally {
            if(estOuvert==true&&c!=null)c.close();
        }
    }

    public Recette[] decomposer(Connection c) throws Exception {
        return decomposer("AS_RECETTEBC", c);
    }

    public Recette[] decomposer(String nT, Connection c) throws Exception {
        boolean estOuvert = false;
        Ingredients ing = new Ingredients();
        ing.setId(this.getId());
        return ing.decomposerBase(nT, c);
    }

    public String genererBonLivraison(String u) throws Exception {
        Connection c = null;
        try {
            c = new UtilDB().GetConn();
            c.setAutoCommit(false);
            BonDeCommande enbase = (BonDeCommande) this.getById(this.getId(), this.getNomTable(), c);
            BonDeCommandeFille vLib = new BonDeCommandeFille();
            vLib.setNomTable("BC_DETAILS_RESTE");
            BonDeCommandeFille[] details = (BonDeCommandeFille[]) CGenUtil.rechercher(vLib, null, null, c,
                    " AND idBc='" + this.getId() + "' AND reste > 0");
            if (details.length > 0) {
                As_BondeLivraisonClient client = new As_BondeLivraisonClient();
                client.setMode("modif");
                client.setIdbc(this.getId());
                client.setEtat(1);
                client.setIdclient(enbase.getIdClient());
                client.setRemarque("Livraison du bon de commande numero " + this.getId());
                client.setDaty(Utilitaire.dateDuJourSql());
                client.createObject(u, c);
                for (BonDeCommandeFille detail : details) {
                    As_BondeLivraisonClientFille clientFille = new As_BondeLivraisonClientFille();
                    clientFille.setMode("modif");
                    clientFille.setProduit(detail.getProduit());
                    clientFille.setUnite(detail.getUnite());
                    clientFille.setQuantite(detail.getReste());
                    clientFille.setIdbc_fille(detail.getId());
                    clientFille.setNumbl(client.getId());
                    clientFille.createObject(u, c);
                }
                c.commit();
                return client.getId();
            }
            throw new Exception("Plus aucun article à livrer");
        } catch (Exception e) {
            if (c != null)
                c.rollback();
            throw e;
        } finally {
            if (c != null)
                c.close();
        }
    }

    public As_BondeLivraisonClient genererLivraison(String id) throws Exception {
        BonDeCommande bc = new BonDeCommande();
        bc.setId(id);
        return bc.genererLivraison();
    }

    public As_BondeLivraisonClient genererLivraison() throws Exception {
        Connection c = null;
        String id = this.getId();
        try {
            c = new UtilDB().GetConn();
            return genererLivraison(c);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public As_BondeLivraisonClient genererLivraison(Connection c) throws Exception {
        String id = this.getId();
        System.out.println("tafiditra ato");
        
        BonDeCommande enbase = (BonDeCommande) this.getById(id, this.getNomTable(), c);

        BonDeCommandeFille vLib = new BonDeCommandeFille();
        vLib.setNomTable("BC_DETAILS_RESTE");
        BonDeCommandeFille[] details = (BonDeCommandeFille[]) CGenUtil.rechercher(
                vLib, null, null, c, " AND idBc='" + id + "'");
         System.out.println("id = "+id);
          System.out.println("details.length = "+details.length);
        if (details.length > 0) {
            As_BondeLivraisonClient client = new As_BondeLivraisonClient();
            client.setMode("modif");
            client.setIdbc(id);
            client.setEtat(enbase.getEtat());
            client.setIdclient(enbase.getIdClient());
            client.setRemarque("Livraison du bon de commande numero " + id);
//            client.setDaty(enbase.getDaty());

            As_BondeLivraisonClientFilleInsertion[] clientFilles = new As_BondeLivraisonClientFilleInsertion[details.length];
            for (int i = 0; i < details.length; i++) {
                As_BondeLivraisonClientFilleInsertion a = new As_BondeLivraisonClientFilleInsertion();
                
                System.out.println("Details[i].getProduit()====>"+details[i].getProduit());
                
                IngredientsLib ing = (IngredientsLib) new IngredientsLib().getById(details[i].getProduit(), "AS_INGREDIENTS_LIB", c);

                a.setUnite(ing.getIdunite());
                a.setUniteLib(ing.getUnite());
                a.setProduit(details[i].getProduit());
                a.setProduitLib(details[i].getProduit().concat(" - ").concat(ing.getLibelle()));
                a.setQuantite(details[i].getReste());
                a.setUnite(details[i].getUnite());
                a.setIdbc_fille(details[i].getId());
                clientFilles[i] = a;
            }
            client.setFille(clientFilles);

            return client;
        } else {
            throw new Exception("Plus aucun article à livrer");
        }
    }
    public void calculerRevient(Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
            }
            BonDeCommandeFille[] liste=(BonDeCommandeFille[]) this.getFille();
            if(liste==null) liste = (BonDeCommandeFille[]) this.getFille(null, c, "");
            for (BonDeCommandeFille f : liste) {
                f.calculerRevient(c);
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if(estOuvert==true&&c!=null)c.close();
        }
    }

    public Of genererOf() throws Exception {
        Connection c = null;
        String id = this.getId();
        try {
            c = new UtilDB().GetConn();
            BonDeCommande enbase = (BonDeCommande) this.getById(id, this.getNomTable(), c);

            BonDeCommandeFille vLib = new BonDeCommandeFille();
            vLib.setNomTable("");
            BonDeCommandeFille[] details = (BonDeCommandeFille[]) CGenUtil.rechercher(
                    vLib, null, null, c, " AND idBc='" + id + "' AND reste > 0");

            if (details.length > 0) {
                Of client = new Of();
                client.setRemarque("OF du bon de commande numero : " + id);
                client.setDaty(enbase.getDaty());
                OfFille[] clientFilles = new OfFille[details.length];
                for (int i = 0; i < details.length; i++) {
                    clientFilles[i] = details[i].genererOfFille();
                }
                client.setOfFilles(clientFilles);

                return client;
            } else {
                throw new Exception("Plus aucun article à livrer");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public OfFille[] getBondeCommandeFille() throws Exception {
        Connection c = null;
        try {
            c = new UtilDB().GetConn();
            BonDeCommandeFille vLib = new BonDeCommandeFille();
            vLib.setNomTable("BONDECOMMANDE_CLIENT_FILLE_CPL");
            vLib.setIdbc(this.getId());
            BonDeCommandeFille[] bcf = (BonDeCommandeFille[]) CGenUtil.rechercher(
                    vLib, null, null, c, "");

            OfFille[] rep = new OfFille[bcf.length];

            if (bcf == null || bcf.length == 0) {
                throw new Exception("Aucun bon de Commande trouvé!");
            }

            for (int i = 0; i < bcf.length; i++) {
                rep[i] = bcf[i].genererOfFille();
            }
            return rep;
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public CartonFille[] getCartonFille()throws Exception{                    
        Connection c = null;
        try {
            
            c = new UtilDB().GetConn();
            BonDeCommandeFille vLib = new BonDeCommandeFille();
            vLib.setNomTable("BONDECOMMANDE_CLIENT_FILLE_CPL");
            vLib.setIdbc(this.getId());
            BonDeCommandeFille[] bcf = (BonDeCommandeFille[]) CGenUtil.rechercher(
                    vLib, null, null, c, "");

            CartonFille[] rep = new CartonFille[bcf.length];

            if (bcf == null || bcf.length == 0) {
                throw new Exception("Aucun bon de Commande trouvé!");
            }

            for (int i = 0; i < bcf.length; i++) {
                rep[i] = bcf[i].genererCartonFille();
            }
            return rep;
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

        public CartonFille[] getCartonFille(String[]idbcf)throws Exception{                    
        Connection c = null;
        try {            
            c = new UtilDB().GetConn();            
            CartonFille[] cretour= new CartonFille[idbcf.length];
            
                for (int i = 0; i < idbcf.length; i++) {            
                    BonCommandeDetailsCarton_Cpl bcff = new BonCommandeDetailsCarton_Cpl();
                    bcff.setId(idbcf[i]);                    
                    BonCommandeDetailsCarton_Cpl[] bcf = (BonCommandeDetailsCarton_Cpl[]) CGenUtil.rechercher(bcff, null, null, c, "");                    
                    if(bcf.length>0){
                        if(bcf[0].getRestecf()>0){
                            CartonFille cf = new CartonFille();
                            System.out.println(bcf[0].getRestecf()+" ii = "+i +" ; produit = "+bcf[0].getProduit()+" ="+bcf[0].getId());
                            
                            cf.setQuantite(bcf[0].getRestecf());
                            cf.setIdIngredient(bcf[0].getProduit());
                            cf.setIdBcFille(bcf[0].getId());                            
                            cretour[i] = cf;                        
                        }
                        else{
                            throw new Exception("Un ou plusieurs produits dont le reste à mettre dans le carton est 0, Veuillez le deselectionner.");
                        }
                    }
                    else{
                        throw new Exception("Le bon de commande fille "+idbcf[i]+" n'existe pas.");
                    }
                }
            return cretour;
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    
    public FabricationFille[] getFabFille() throws Exception {
        Connection c = null;
        try {
            c = new UtilDB().GetConn();
            BonDeCommandeFille vLib = new BonDeCommandeFille();
            vLib.setNomTable("BONDECOMMANDE_CLIENT_FILLE_CPL");
            vLib.setIdbc(this.getId());
            BonDeCommandeFille[] bcf = (BonDeCommandeFille[]) CGenUtil.rechercher(
                    vLib, null, null, c, "");

            FabricationFille[] rep = new FabricationFille[bcf.length];

            if (bcf == null || bcf.length == 0) {
                throw new Exception("Aucun bon de Commande trouvé!");
            }

            for (int i = 0; i < bcf.length; i++) {
                rep[i] = bcf[i].genererFabFille();
            }
            return rep;
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public Fabrication genererFabrication(String id) throws Exception {
        Connection c = null;
        try {
            c = new UtilDB().GetConn();
            Fabrication fabrication = (Fabrication) this.getById(id, "Fabrication", c);
            if (fabrication == null) {
                throw new Exception("Aucune fabrication trouvée avec l'ID spécifié");
            }

            FabricationFille vLib = new FabricationFille();
            vLib.setNomTable("FabricationFille");
            FabricationFille[] fabricationFilles = (FabricationFille[]) CGenUtil.rechercher(
                    vLib, null, null, c, " AND idMere='" + id + "'");

            if (fabricationFilles == null || fabricationFilles.length == 0) {
                throw new Exception("Aucun détail de fabrication trouvé pour l'ID spécifié");
            }

            fabrication.setFille(fabricationFilles);

            return fabrication;
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public BonDeCommande() {
        setNomTable("BONDECOMMANDE_CLIENT");
        this.setLiaisonFille("idbc");
    }

    public void construirePK(Connection c) throws Exception {
        this.preparePk("FCBC", "getseqBONDECOMMANDE_CLIENT");
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

    public String getIdClient() {
        return idClient;
    }

    public Date getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Date dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public void setIdClient(String idClient) throws Exception {
        if (this.getMode().equals("modif")) {
            if (idClient == null || idClient.isEmpty()) {
                throw new Exception("Le client est obligatoire");
            }
        }
        this.idClient = idClient;
    }

    protected void lierDeviseFille() {
        BonDeCommandeFille[] filles = (BonDeCommandeFille[]) this.getFille();
        for (BonDeCommandeFille bcf : filles) {
            bcf.setIdDevise(this.getIdDevise());
        }
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        NumeroFacture numFactF = new NumeroFacture();
        numFactF.setNomTable("V_NUMEROBC_EN_COURS");
        NumeroFacture[] numFact = (NumeroFacture[]) CGenUtil.rechercher(numFactF, null, null, c, "");
        this.setNumeroBc(numFact[0].getProchain_num_format());

        lierDeviseFille();
        return super.createObject(u, c);
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id", "designation","dateBesoin"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"designation","dateBesoin"};
        return motCles;
    }
    public InsertionVente createVente()throws Exception{
        try {
            BonDeCommande bc = new BonDeCommande();
            bc.setId(this.getId());
            BonDeCommande[] resultats = (BonDeCommande[]) CGenUtil.rechercher(bc, null, null, "");
            if(resultats.length > 0) {
                bc = resultats[0];
                InsertionVente bd = new InsertionVente();
                Client cli = (Client) new Client().getById(bc.getIdClient(),"CLIENT",null);
                bd.setEcheancefacture(cli.getEcheance());
                if (bc.getIdClient() != null) {
                    bd.setIdClient(bc.getIdClient());
                }
                if (bc.getIdMagasin() != null) {
                    bd.setIdMagasin(bc.getIdMagasin());
                }
                if (bc.getIdDevise() != null) {
                    bd.setIdDevise(bc.getIdDevise());
                }
                bd.setModepaiement(bc.getModepaiement());
                bd.setModelivraison(bc.getModelivraison());
                bd.setDesignation("Facturation du/de la "+bc.getDesignation());
                bd.setRemarque(bc.getRemarque());
                bd.setDateLivraison(bc.getDateLivraison());
                bd.setLieuLivraison(bc.getLieuLivraison());
                bd.setDatyPrevu(Utilitaire.ajoutJourDate(bd.getDatyPrevu(), (int)cli.getEcheance()));
                bd.setIdOrigine(bc.getId());
                bd.setFraislivraison(bc.getFraislivraison());
                return bd;
            }else {
                throw new Exception("La proforma n'existe pas");
            }
        }catch(Exception e){
            throw e;
        }
    }
     public BonDeCommandeFIlleCpl[] getFilleBCLib()throws Exception{
        BonDeCommandeFIlleCpl profD = new BonDeCommandeFIlleCpl();
        profD.setIdbc(this.getId());
        BonDeCommandeFIlleCpl[] val= (BonDeCommandeFIlleCpl[])CGenUtil.rechercher(profD, null, null, "");
        return val;
    }
     @Override
    public String getNomClasseFille() {
        return ("vente.BonDeCommandeFille");
    }

    @Override
    public String getLiaisonFille() {
        return "idbc"  ;
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        if (this.getEtat() == 11){
            this.setEtat(12);
        }
        return super.validerObject(u, c);
    }
}
