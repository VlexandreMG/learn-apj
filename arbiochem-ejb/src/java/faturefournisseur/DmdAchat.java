package faturefournisseur;

import bean.*;
import faturefournisseur.historique.HistoriqueValidationDmdAchat;
import historique.MapUtilisateur;
import maintenance.travaux.TravauxCpl;
import produits.Ingredients;
import utilitaire.ConstanteEtat;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import vente.Vente;

import java.sql.Connection;
import java.sql.Date;

public class DmdAchat extends ClassMere {
    private String id;
    private Date daty;
    private String fournisseur;
    private String remarque;

    private Date dateLimite;

    private String idMagasin;

    private String idCategorie;

    private String idObjet;
    private String service;
    private String idProvenance;

    public DmdAchat() throws Exception {
        this.setNomTable("DMDACHAT");
        this.setLiaisonFille("idmere");
        this.setNomClasseFille("faturefournisseur.DmdAchatFille");
    }

    public String getService() {
        return service;
    }

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

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date date) {
        this.daty = date;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(String idObjet) {
        this.idObjet = idObjet;
    }

    public FactureFournisseurDetails[] getFactureFournisseurDetails(Connection c) throws Exception {
        int indice = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                indice = 1;
            }
            DmdAchatFille[] dmdAchatFille = (DmdAchatFille[])this.getFille("DMDACHATFILLE",c,"");
            FactureFournisseurDetails[] factureFoDetails = new  FactureFournisseurDetails[dmdAchatFille.length];

            for (int i = 0; i < dmdAchatFille.length; i++) {
                Ingredients ing = (Ingredients)new Ingredients().getById(dmdAchatFille[i].getIdproduit(),"AS_INGREDIENTS",c);
                factureFoDetails[i] = new FactureFournisseurDetails();
                factureFoDetails[i].setIdProduit(dmdAchatFille[i].getIdproduit());
                factureFoDetails[i].setPu(dmdAchatFille[i].getPu());
                factureFoDetails[i].setQte(dmdAchatFille[i].getQuantite());
                factureFoDetails[i].setTva(dmdAchatFille[i].getTva());
                factureFoDetails[i].setIdDevise("AR");
                factureFoDetails[i].setCompte(ing.getCompte_achat());
            }
            return factureFoDetails;
        }catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }finally{
            if(indice == 1 && c!=null)c.close();
        }
    }

    public As_BonDeCommande_Fille[] getBonDeCommandeDetails(Connection c) throws Exception {
        int indice = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                indice = 1;
            }
            DmdAchatFille[] dmdAchatFille = (DmdAchatFille[])this.getFille("DMDACHATFILLE",c,"");
            As_BonDeCommande_Fille[] factureFoDetails = new  As_BonDeCommande_Fille[dmdAchatFille.length];

            for (int i = 0; i < dmdAchatFille.length; i++) {
//                Ingredients ing = (Ingredients)new Ingredients().getById(dmdAchatFille[i].getIdproduit(),"AS_INGREDIENTS",c);
                factureFoDetails[i] = new As_BonDeCommande_Fille();
                factureFoDetails[i].setProduit(dmdAchatFille[i].getIdproduit());
                factureFoDetails[i].setPu(dmdAchatFille[i].getPu());
                factureFoDetails[i].setQuantite(dmdAchatFille[i].getQuantite());
                factureFoDetails[i].setTva(dmdAchatFille[i].getTva());
                factureFoDetails[i].setIdDevise("AR");
                factureFoDetails[i].setTauxDeChange(1);
                factureFoDetails[i].setProduitLib(dmdAchatFille[i].getDesignation());
            }
            return factureFoDetails;
        }catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }finally{
            if(indice == 1 && c!=null)c.close();
        }
    }

    public DmdAchatFille[] getDmdAchatFille()throws Exception{
        DmdAchatFille dmdAchatFille = new DmdAchatFille();
        dmdAchatFille.setIdmere(this.getId());
        DmdAchatFille[] ds = (DmdAchatFille[]) CGenUtil.rechercher(dmdAchatFille, null, null, "");
        return ds;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("DMDA", "GETSEQDMDACHAT");
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
    public String[] getRoleValidation() {
        return new String[]{"cheffab", "dg", "respachat"};
    }

    @Override
    public Object validerObject(MapUtilisateur u, Connection c) throws Exception {
        String role = u.getIdrole();
        switch (role) {
            case "cheffab":
                this.setEtat(3);
                break;
            case "dg":
                this.setEtat(4);
                break;
            case "respachat":
                this.setEtat(11);
                break;
        }
        HistoriqueValidationDmdAchat h = this.genererHistoriqueValidation(u);
        h.createObject(u, c);
        return super.validerObject(u, c);
    }
    public HistoriqueValidationDmdAchat genererHistoriqueValidation(MapUtilisateur u)throws Exception{
        HistoriqueValidationDmdAchat h = new HistoriqueValidationDmdAchat();
        h.setNomTable("HISTORIQUEVALIDATIONDMDACHAT");
        h.setIdDmdAchat(this.getId());
        h.setRefuser(""+u.getRefuser());
        h.setNomuser(u.getNomuser());
        h.setDaty(Utilitaire.dateDuJourSql());
        h.setHeure(Utilitaire.heureCourante());
        return h;
    }


    public TravauxCpl findTravaux(Connection c ) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                estOuvert = true;
            }

            TravauxCpl[] tvcpl = (TravauxCpl[]) CGenUtil.rechercher(new TravauxCpl(), null, null, c,"and id = '" + this.getIdObjet() + "'");
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

    public String getIdProvenance() {
        return idProvenance;
    }

    public void setIdProvenance(String idProvenance) {
        this.idProvenance = idProvenance;
    }

    public DmdAchat fusionners(String[] ids, Connection c) throws Exception {
        DmdAchat dmdAchat = null;
        try {
            if (c == null){
                c = new UtilDB().GetConn();
                c.setAutoCommit(false);
            }
            DmdAchat[] dmdAchatsIds = getDmdAchat(ids, c);
            String[] clients = AdminGen.getDistinct(dmdAchatsIds, "fournisseur");
            if (clients.length > 1){
                throw new Exception("Impossible de fusionner des demandes d'achat de fournisseurs diff\\u00e9rents");
            }
            As_BonDeCommande[] asBonDeCommandes = (As_BonDeCommande[]) CGenUtil.rechercher(new As_BonDeCommande(), null, null, " and IDDMDACHAT in("+ Utilitaire.tabToString(ids,"'",",")+") and etat = 11");
            if (asBonDeCommandes.length > 0){
                throw new Exception("Il y a deja une bon de commande valider lier a ces demandes d'achat");
            }
            DmdAchat[] dmdAchats = (DmdAchat[]) CGenUtil.rechercher(new DmdAchat(), null, null, c, " and id in ("+ Utilitaire.tabToString(ids,"'",",")+")");
            dmdAchat = (DmdAchat) dmdAchats[0].fusionner(ids, c);
            return dmdAchat;
        } catch (Exception e) {
            c.rollback();
            throw new Exception(e);
        } finally {
            if (c != null){
                c.commit();
                c.close();
            }
        }
    }

    public DmdAchat[] getDmdAchat(String[] ids, Connection c) throws Exception {
        DmdAchat[] dmdAchats = null;
        dmdAchats = (DmdAchat[]) CGenUtil.rechercher(new DmdAchat(), null, null, c, " and id in ("+ Utilitaire.tabToString(ids,"'",",")+")");
        return dmdAchats;
    }
}
