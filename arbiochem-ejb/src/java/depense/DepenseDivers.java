package depense;

import bean.CGenUtil;
import bean.ClassMere;
import caisse.MvtCaisse;
import faturefournisseur.As_BonDeLivraison;
import faturefournisseur.As_BonDeLivraison_Fille;
import faturefournisseur.FactureFournisseur;
import faturefournisseur.FactureFournisseurDetails;
import historique.MapUtilisateur;
import utilitaire.ConstanteEtat;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import utils.ConstanteSocobis;

import java.sql.Connection;
import java.sql.Date;

public class DepenseDivers extends ClassMere {
    private String id;
    private Date daty;
    private String fournisseur;
    private String remarque;
    private String idModepaiement;
    private String compteCheque;
    private String acheteur;
    private String idMagasin;
    private String idCategorie;
    private String idSection;

    public String getIdSection() {
        return idSection;
    }

    public void setIdSection(String idSection) {
        this.idSection = idSection;
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

    public void setDaty(Date daty) {
        this.daty = daty;
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

    public String getIdModepaiement() {
        return idModepaiement;
    }

    public void setIdModepaiement(String idModepaiement) {
        this.idModepaiement = idModepaiement;
    }

    public String getCompteCheque() {
        return compteCheque;
    }

    public void setCompteCheque(String compteCheque) {
        this.compteCheque = compteCheque;
    }

    public String getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
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

    public DepenseDivers() throws Exception {
        this.setNomTable("DEPENSEDIVERS");
        this.setNomClasseFille("depense.DepenseDiversFille");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("DD","getseqdepenseDivers");
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
        return "depense.DepenseDiversFille";
    }

    @Override
    public String getLiaisonFille() {
        return "idMere";
    }

    @Override
    public Object validerObject(MapUtilisateur u, Connection c) throws Exception {
        String role = u.getIdrole();
        switch (role) {
            case "chef_dep_bisc":
            case "chef_dep_conf":
                this.controlerUpdate(c);
                this.updateEtat(3,this.getId(),c);
                break;
            case "comptable":
                this.controlerUpdate(c);
                this.updateEtat(4,this.getId(),c);
                break;
            case "dg":
                this.controlerUpdate(c);
                this.updateEtat(ConstanteEtat.getEtatValider(),this.getId(),c);
                break;
            case "respachat":
                this.controlerUpdate(c);
                this.updateEtat(5,this.getId(),c);
                break;
        }
        return this;
    }

    public As_BonDeLivraison_Fille[] genererBonDeLivraisonFille(Connection c)throws Exception{
        DepenseDiversFille depenseDiversFille = new DepenseDiversFille();
        depenseDiversFille.setIdMere(this.getId());
        DepenseDiversFille[] depenseDiversFilles = (DepenseDiversFille[]) CGenUtil.rechercher(depenseDiversFille, null, null, c, "");
        As_BonDeLivraison_Fille[] as_BonDeLivraison_Filles = new As_BonDeLivraison_Fille[depenseDiversFilles.length];
        for (int i = 0; i < depenseDiversFilles.length; i++) {
            as_BonDeLivraison_Filles[i] = new As_BonDeLivraison_Fille();
            as_BonDeLivraison_Filles[i].setProduit(ConstanteSocobis.ID_PRODUIT_DIVERS);
            as_BonDeLivraison_Filles[i].setDesignation(depenseDiversFilles[i].getDesignation());
            as_BonDeLivraison_Filles[i].setQuantite(depenseDiversFilles[i].getQuantite());
            as_BonDeLivraison_Filles[i].setPu(depenseDiversFilles[i].getPu());
            as_BonDeLivraison_Filles[i].setTauxdechange(1);
        }
        return as_BonDeLivraison_Filles;
    }

    public As_BonDeLivraison genererBonDeLivraison() throws Exception {
        As_BonDeLivraison bonDeLivraison = new As_BonDeLivraison();
        bonDeLivraison.setDaty(Utilitaire.dateDuJourSql());
        bonDeLivraison.setIdFournisseur(this.getFournisseur());
        bonDeLivraison.setMagasin(this.getIdMagasin());
        bonDeLivraison.setIdDepenseDivers(this.getId());
        bonDeLivraison.setRemarque("Bon de livraison d\u00E9pense divers : "+this.getId());
        return bonDeLivraison;
    }

    public As_BonDeLivraison_Fille[] genererBonDeLivraisonFille() throws Exception {
        DepenseDiversFille[] depenseDiversFilles = (DepenseDiversFille[]) this.getFille();
        As_BonDeLivraison_Fille[] as_BonDeLivraison_Filles = new As_BonDeLivraison_Fille[depenseDiversFilles.length];
        for (int i = 0; i < depenseDiversFilles.length; i++) {
            as_BonDeLivraison_Filles[i] = new As_BonDeLivraison_Fille();
            as_BonDeLivraison_Filles[i].setProduit(ConstanteSocobis.ID_PRODUIT_DIVERS);
            as_BonDeLivraison_Filles[i].setDesignation(depenseDiversFilles[i].getDesignation());
            as_BonDeLivraison_Filles[i].setQuantite(depenseDiversFilles[i].getQuantite());
            as_BonDeLivraison_Filles[i].setPu(depenseDiversFilles[i].getPu());
            as_BonDeLivraison_Filles[i].setTauxdechange(1);
        }
        return as_BonDeLivraison_Filles;
    }

    public FactureFournisseur genererFactureFournisseur() {
        FactureFournisseur facture = new FactureFournisseur();
        facture.setIdFournisseur(this.getFournisseur());
        facture.setIdModePaiement(this.getIdModepaiement());
        facture.setDaty(Utilitaire.dateDuJourSql());
        facture.setIdDevise("AR");
        facture.setIdMagasin(this.getIdMagasin());
        facture.setTypeFactureFournisseur(ConstanteSocobis.typeFactureFournisseurFAR);
        facture.setDesignation("Facture fournisseur d\u00E9pense divers : "+this.getId());
        return facture;
    }

    public FactureFournisseurDetails[] genererFactureFournisseurFille() throws Exception {
        DepenseDiversFille[] depenseDiversFilles = (DepenseDiversFille[]) this.getFille();
        FactureFournisseurDetails[] details = new FactureFournisseurDetails[depenseDiversFilles.length];
        for (int i = 0; i < details.length; i++) {
            details[i] = new FactureFournisseurDetails();
            details[i].setIdProduit(ConstanteSocobis.ID_PRODUIT_DIVERS);
            details[i].setQte(depenseDiversFilles[i].getQuantite());
            details[i].setPu(depenseDiversFilles[i].getPu());
            details[i].setDesignation(depenseDiversFilles[i].getDesignation());
            details[i].setCompte(ConstanteSocobis.COMPTE_PRODUIT_DIVERS);
            details[i].setIdDevise("AR");
            details[i].setTauxDeChange(1);
        }
        return details;
    }

    public void genererFactureDefinitive(String u, Connection c) throws Exception {
        boolean canClose = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                canClose = true;
            }
            DepenseDivers depense = (DepenseDivers) this.getMereFille(null,"DEPENSEDIVERSFILLE",c);
            FactureFournisseur fact = depense.genererFactureFournisseur();
            fact.setFille(depense.genererFactureFournisseurFille());
            fact.createObject(u,c);

            As_BonDeLivraison bl = depense.genererBonDeLivraison();
            bl.setFille(depense.genererBonDeLivraisonFille());
            bl.setIdFactureFournisseur(fact.getId());
            bl.createObject(u,c);
            bl.validerObject(u,c);

            fact.validerObject(u,c);
            MvtCaisse mvt = new MvtCaisse();
            mvt.setIdOrigine(this.getId());
            MvtCaisse[] caisses = (MvtCaisse[]) CGenUtil.rechercher(mvt,null,null,c,"");
            for (MvtCaisse m : caisses) {
                m.setIdOrigine(fact.getId());
                m.updateToTableWithHisto(u,c);
            }
        } catch (Exception e) {
            if (canClose) c.rollback();
            throw e;
        } finally {
            if (canClose) c.close();
        }
    }

}

