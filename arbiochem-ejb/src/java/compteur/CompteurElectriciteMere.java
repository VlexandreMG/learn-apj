package compteur;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ClassMere;
import charge.Charge;
import fabrication.FabricationFille;
import historique.MapUtilisateur;
import maintenance.configuration.ReleveFab;
import produits.Ingredients;
import stock.MvtStockFilleLib;
import utilitaire.UtilDB;
import utils.ConstanteSocobis;

import java.sql.Connection;
import java.sql.Date;

public class CompteurElectriciteMere extends ClassMere {
    private String id;
    private Date daty;
    private String ligne;
    private String remarque;
    private double consommation;
    private double montant;

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
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

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    public CompteurElectriciteMere() throws Exception {
        this.setNomTable("COMPTEURELECTRICITEMERE");
        this.setNomClasseFille("compteur.CompteurElectricite");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CME", "GETSEQ_CompteurElectriciteMere");
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
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        CompteurElectriciteMere filtre = new CompteurElectriciteMere();
        setLigne(this.ligne);
        setDaty(this.daty);
        CompteurElectriciteMere[] existeMere = (CompteurElectriciteMere[]) CGenUtil.rechercher(filtre, null, null, c, "");
        CompteurElectricite[] filles = (CompteurElectricite[]) getFille();
        if(filles.length>0){
            filles[0].setEcart(Math.abs(filles[0].getValeur() - filles[0].getAncien()));
            filles[0].setIdligne(this.ligne);
            CompteurElectricite filtreFille = new CompteurElectricite();
            filtreFille.setIdcategorie(filles[0].getIdcategorie());
            filtreFille.setIdmachine(filles[0].getIdmachine());
            CompteurElectricite[] existeFille = (CompteurElectricite[]) CGenUtil.rechercher(filtreFille, null, null, c, "");
            if(existeFille.length>0 && existeMere.length>0){
                throw new Exception("Ce compteur existe d\\u00E9j\\u00E0 pour cette ligne, ce categorie, cette machine et  cette date");
            }
        }
        ClassMAPTable compteurelectricite = super.createObject(u, c);
        if(this.getRemarque().equalsIgnoreCase("init")){
            super.validerObject(u,c);
        }
        return compteurelectricite;
    }

    @Override
    public String[] getRoleValidation() {
        return new String[]{"maintenancier", "cheffab"};
    }

    @Override
    public Object validerObject(MapUtilisateur u, Connection c) throws Exception {
        String role = u.getIdrole();
        switch (role) {
            case "maintenancier":
                System.out.println("maintenancier");
                checkEtat(3);
                this.updateEtat(3,this.getId(),c);
                break;
            case "cheffab":
                insererChargeDeFabrication(u.getTuppleID(), c);
                checkEtat(11);
                this.updateEtat(11,this.getId(),c);
                break;
        }
        return this;
    }

    public void checkEtat(int etat) throws Exception {
        if(this.getEtat()==etat){
            throw new Exception("Ce relev\\u00E9 est d\\u00E9j\\u00E0 valid\\u00E9.");
        }
    }

    public void checkEtatNonValide(int etat) throws Exception {
        if(this.getEtat()==etat){
            throw new Exception("Ce relev\\u00E9 est doit valider par un maintenancier d'abord");
        }
    }

    public Ingredients findIngredientElectricite(Connection c) throws Exception {
        Ingredients[] ingredients = (Ingredients[]) CGenUtil.rechercher(new Ingredients(), null, null, c, " AND id ='" + ConstanteSocobis.ID_PRODUIT_ELECTRICITE + "'");
        if (ingredients.length > 0) {
            return ingredients[0];
        }
        return null;
    }

    public void checkLiaisonFab(Connection c) throws Exception {
        ReleveFab fab = new ReleveFab();
        fab.setIdReleve(this.getId());
        ReleveFab[] fabs = (ReleveFab[]) CGenUtil.rechercher(fab, null, null, c, "");
        if (fabs.length == 0) {
            throw new Exception("Le relev\u00E9 doit avoir une fabrication rattach\u00E9e");
        }

    }

    public void insererChargeDeFabrication(String utilisateur, Connection connexion) throws Exception {
        boolean connexionLocale = false;

        try {
            if (connexion == null) {
                connexion = new UtilDB().GetConn();
                connexionLocale = true;
            }

            checkLiaisonFab(connexion);

            Ingredients ingredient = findIngredientElectricite(connexion);
            if (ingredient == null) {
                throw new Exception(
                        "Aucun produit trouvé pour la catégorie : electricite "
                );
            }
            CompteurElectriciteMereLib compteur = (CompteurElectriciteMereLib) new CompteurElectriciteMereLib().getById(this.getId(),"COMPTEURELECTRICITEMERELIB",connexion);
            double montant = compteur.getMontant();

            FabricationFille[] fabricationsRattachees = rechercherFabricationsRattachees(connexion);

            if (fabricationsRattachees.length == 0) {
                throw new Exception(
                        "Aucune fabrication rattachée trouvée pour le relevé : " + this.getId()
                );
            }


            for (FabricationFille fabrication : fabricationsRattachees) {
                Charge charge = creerCharge(
                        fabrication,
                        ingredient,
                        montant
                );

                charge.createObject(utilisateur, connexion);
                charge.validerObject(utilisateur, connexion);
            }


            if (connexionLocale) {
                connexion.commit();
            }

        } catch (Exception e) {
            if (connexionLocale && connexion != null) {
                try {
                    connexion.rollback();
                } catch (Exception rollbackException) {
                    e.addSuppressed(rollbackException);
                }
            }

            throw e;

        } finally {
            if (connexionLocale && connexion != null) {
                try {
                    connexion.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    private FabricationFille[] rechercherFabricationsRattachees(
            Connection connexion
    ) throws Exception {
        FabricationFille critere = new FabricationFille();
        critere.setNomTable("fabricationrattache");
        critere.setIdReleve(this.getId());

        FabricationFille[] resultats = (FabricationFille[]) CGenUtil.rechercher(
                critere,
                null,
                null,
                connexion,
                ""
        );

        return resultats != null
                ? resultats
                : new FabricationFille[0];
    }

    private double rechercherPrixUnitaireMouvement(
            Ingredients ingredient,
            Connection connexion
    ) throws Exception {
        double prixUnitaire = ingredient.getPu();

        MvtStockFilleLib critere = new MvtStockFilleLib();
        critere.setNomTable("MVTSTOCKFILLELIB");

        String condition =
                " AND IDOBJET = '" + echapperValeurSql(this.getId()) + "'" +
                        " AND IDPRODUIT = '" + echapperValeurSql(ingredient.getId()) + "'";

        MvtStockFilleLib[] mouvements = (MvtStockFilleLib[]) CGenUtil.rechercher(
                critere,
                null,
                null,
                connexion,
                condition
        );

        if (mouvements != null && mouvements.length > 0) {
            prixUnitaire = mouvements[0].getPu();
        }

        return prixUnitaire;
    }

    private Charge creerCharge(
            FabricationFille fabrication,
            Ingredients ingredient,
            double montant
    ) {
        Charge charge = new Charge();
        double pu = fabrication.getPourcentage() * montant / 100;
        charge.setPu(pu);
        charge.setQte(1);
        charge.setIdfabrication(fabrication.getIdMere());
        charge.setIdingredients(ingredient.getId());
        charge.setDaty(this.getDaty());

        charge.setLibelle(
                "Consommable " + ingredient.getLibelle()
                        + " pour la fabrication " + fabrication.getIdMere()
                        + " du relevé " + this.getId()
        );
        System.out.println("Charge creer = "+ charge.toString());

        return charge;
    }

    private String echapperValeurSql(String valeur) {
        return valeur == null
                ? ""
                : valeur.replace("'", "''");
    }

}

