package maintenance.configuration;


import bean.*;
import charge.Charge;
import fabrication.Fabrication;
import fabrication.FabricationFille;
import fabrication.FabricationFilleCpl;
import fabrication.OfFille;
import historique.MapUtilisateur;
import maintenance.ressources.OfRattache;
import maintenance.ressources.RattachementFabrication;
import maintenance.tranche.Tranche;
import maintenance.utils.ConstanteMaintenance;
import produits.Ingredients;
import stock.MvtStock;
import stock.MvtStockEntreeAvecReste;
import stock.MvtStockFille;
import stock.MvtStockFilleLib;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import utils.ConstanteSocobis;


import java.sql.Connection;
import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class CompteurMaintenance extends ClassEtat {
    private String id, idLigne, idCategorie;
    private double valeur, ecart;
    private Date daty;
    private String idCategorieLib, idLigneLib, idMagasin, idMagasinLib, etatLib;

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdCategorieLib() {
        return this.idCategorieLib;
    }

    public void setIdCategorieLib(String idCategorieLib) {
        this.idCategorieLib = idCategorieLib;
    }

    public String getIdLigneLib() {
        return this.idLigneLib;
    }

    public void setIdLigneLib(String idLigneLib) {
        this.idLigneLib = idLigneLib;
    }

    private String heure;
    private String remarque;
    private double ancien;

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public double getAncien() {
        return ancien;
    }

    public void setAncien(double ancien) {
        this.ancien = ancien;
    }

    public CompteurMaintenance() {
        super.setNomTable("COMPTEURMAINTENANCE");
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
        return new String[]{"maintenancier", "cheffab"};
    }

    @Override
    public Object validerObject(MapUtilisateur u, Connection c) throws Exception {
        String role = u.getIdrole();
        switch (role) {
            case "maintenancier":
                checkEtat(3);
                this.updateEtat(3,this.getId(),c);
                this.genererMvtStock(u.getTuppleID(), c);
                break;
            case "cheffab":
                checkEtat(11);
                insererChargeDeFabrication(u.getTuppleID(), c);
                this.updateEtat(11,this.getId(),c);
                break;
        }
        return this;
    }

    public void checkEtat(int etat) throws Exception {
        if(this.getEtat()==etat){
                     throw new Exception("Ce relevé est déjà validé.");
        }
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("COMPTM", "getSeqCompteurMaintenance");
        this.setId(makePK(c));
    }

    @Override
    public void controler(Connection c) throws Exception {
        CompteurMaintenance lastCompteur = this.getLastCompteur(c, this.getIdLigne(), this.getIdCategorie());
        boolean estInit=false;
        String remarque = this.getRemarque();

        if (remarque != null)
        {
            if(this.getRemarque().equalsIgnoreCase("init"))estInit=true;
            if (!estInit&&(lastCompteur != null && lastCompteur.getValeur() != this.getAncien())) {
                throw new Exception("Il est impossible de modifier l'ancienne valeur !");
            }
        }

//        String idTranche = this.getIdTranche();
//        if (ConstanteMaintenance.CATEGORIE_ELECTRICITE.equalsIgnoreCase(this.getIdCategorie()) &&  idTranche!=null && !idTranche.isEmpty()) {
//            Tranche tranche = (Tranche) new Tranche().getById(idTranche,null,c);
//            String debut = tranche.getHeuredebut();
//            String fin = tranche.getHeurefin();
//            String heure = this.getHeure();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//            LocalTime hDebut = LocalTime.parse(debut, formatter);
//            LocalTime hFin   = LocalTime.parse(fin, formatter);
//            LocalTime hSaisie = LocalTime.parse(heure, formatter);
//            if (hSaisie.isBefore(hDebut) || hSaisie.isAfter(hFin)) {
//                throw new Exception("L'heure saisie n'est pas comprise dans la tranche horaire !");
//            }
//        }
    }

    public void checkLiaisonFab(Connection c) throws Exception {
        ReleveFab fab = new ReleveFab();
        fab.setIdReleve(this.getId());
        ReleveFab[] fabs = (ReleveFab[]) CGenUtil.rechercher(fab, null, null, c, "");
        if (fabs.length == 0) {
            throw new Exception("Le relev\u00E9 doit avoir une fabrication rattach\u00E9e");
        }

    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
//        CompteurMaintenance lastCompteur = this.getLastCompteur(c,this.getIdLigne(),this.getIdCategorie());
        this.setEcart(Math.abs(this.getValeur() - this.getAncien()));
//        if(lastCompteur!=null){
//            this.setEcart(this.getValeur()-lastCompteur.getValeur());
//        }
//        else{
//            this.setEcart(0);
//        }

        String remarque = this.getRemarque();

        if (remarque != null) {

            if(remarque.equalsIgnoreCase("init")){
                this.setEcart(0);
            }
        }

        ClassMAPTable classMAPTable = super.createObject(u, c);

        if (remarque != null)
        {
            if(this.getRemarque().equalsIgnoreCase("init")){
                super.validerObject(u,c);
            }
        }

        return classMAPTable;
    }

    public CompteurMaintenance getLastCompteur() throws Exception {
        try (Connection c = new UtilDB().GetConn()) {
            return getLastCompteur(c, this.getIdLigne(), this.getIdCategorie());
        }
    }

    public CompteurMaintenance getLastCompteur(Connection c, String idLigne, String idCategorie) throws Exception {
        CompteurMaintenance compteur = new CompteurMaintenance();
        compteur.setIdLigne(idLigne);
        compteur.setIdCategorie(idCategorie);
        compteur.setEtat(3);
        CompteurMaintenance[] list = (CompteurMaintenance[]) CGenUtil.rechercher(compteur, null, null, c, "  ORDER BY DATY DESC");
        if (list != null && list.length > 0) {
            compteur = list[0];
            return compteur;
        }
        return null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) throws Exception {
        if (this.getMode().equals("modif")) {
            CompteurMaintenance cmp = getLastCompteur(null, this.getIdLigne(), this.getIdCategorie());
            if (cmp != null && this.getValeur() > cmp.getValeur()) {
                throw new Exception("La valeur doit \u00EAtre sup\u00E9rieur \u00E0 la valeur initiale");
            }
        }
        this.valeur = valeur;
    }

    public double getEcart() {
        return ecart;
    }

    public void setEcart(double ecart) {
        this.ecart = ecart;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public void insererChargeDeFabrication(String utilisateur, Connection connexion) throws Exception {
        boolean connexionLocale = false;

        try {
            if (connexion == null) {
                connexion = new UtilDB().GetConn();
                connexionLocale = true;
            }

            checkLiaisonFab(connexion);

            Ingredients ingredient = findIngredientByTypeConso(connexion);
            if (ingredient == null) {
                throw new Exception(
                        "Aucun produit trouvé pour la catégorie : " + this.getIdCategorie()
                );
            }

            FabricationFille[] fabricationsRattachees = rechercherFabricationsRattachees(connexion);

            if (fabricationsRattachees.length == 0) {
                throw new Exception(
                        "Aucune fabrication rattachée trouvée pour le relevé : " + this.getId()
                );
            }

            double prixUnitaire = rechercherPrixUnitaireMouvement(
                    ingredient,
                    connexion
            );

            for (FabricationFille fabrication : fabricationsRattachees) {
                Charge charge = creerCharge(
                        fabrication,
                        ingredient,
                        prixUnitaire
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
            double prixUnitaire
    ) {
        Charge charge = new Charge();
        double qte = fabrication.getPourcentage() * this.getEcart() / 100;

        charge.setPu(prixUnitaire);
        charge.setQte(qte);
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

    public double calculConsommationParPetri(Connection c) throws Exception {
        boolean verif = false;
        try {
            if (!verif) {
                c = new UtilDB().GetConn();
                verif = true;
            }
            double consommation = 0;

            OfRattache[] ofRattaches = (OfRattache[]) CGenUtil.rechercher(new OfRattache(), null, null, c, " AND idReleve='" + this.getId() + "'");
            for (OfRattache ofRattache : ofRattaches) {
                consommation += ofRattache.getQtePetri();
            }
//            System.out.println(consommation);
            CompteurMaintenance cMaintenance = (CompteurMaintenance) this.getById(this.getId(), null, c);
            double rep = 0;
            double ecart = 0;
            if (cMaintenance != null) {
                ecart = cMaintenance.getEcart();
            } else {
                ecart = this.getEcart();
            }
//            System.out.println(ecart);
            if (ecart != 0) {
                rep = ecart / consommation;
            }
//            System.out.println(rep);
            return rep;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && verif)
                c.close();
        }
    }

    public Ingredients findIngredientByTypeConso(Connection c) throws Exception {
        Ingredients[] ingredients = (Ingredients[]) CGenUtil.rechercher(new Ingredients(), null, null, c, " AND id ='" + this.getIdCategorie() + "'");
        if (ingredients.length > 0) {
            return ingredients[0];
        }
        return null;
    }

    public void insertChargeFab(String u, Connection c) throws Exception {
        boolean verif = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = true;
            }

            ReleveFab refFab = new ReleveFab();
            refFab.setIdReleve(this.getId());
            ReleveFab[] releves = (ReleveFab[]) CGenUtil.rechercher(refFab, null, null, c, "");

            for (ReleveFab rfab : releves) {
                OfRattache[] rattaches = (OfRattache[]) CGenUtil.rechercher(
                        new OfRattache(), null, null, c, " AND idOffille='" + rfab.getIdFab() + "'"
                );
                if (rattaches == null || rattaches.length == 0) {
                    throw new Exception("Aucun rattachement trouvé pour le relevé : " + rfab.getIdReleve());
                }

                OfRattache ofFille = rattaches[0];

                FabricationFilleCpl fabricationFilleCpl = new FabricationFilleCpl();
                fabricationFilleCpl.setIsPetri(1);
                fabricationFilleCpl.setIdOffille(rfab.getIdFab());
                FabricationFilleCpl[] fabFilles = (FabricationFilleCpl[]) CGenUtil.rechercher(fabricationFilleCpl, null, null, c, " and isPetri = 1");

                Ingredients ingredient = findIngredientByTypeConso(c);
                if (ingredient == null) {
                    throw new Exception("Aucun ingrédient trouvé pour la catégorie : " + this.getIdCategorie());
                }

                if (fabFilles != null && fabFilles.length > 0) {
                    double qte = (ofFille.getPourcentage() * this.getEcart()) / 100;
                    Charge charge = new Charge();
                    double pu = ingredient.getPu();
                    MvtStockFilleLib stfille = new MvtStockFilleLib();
                    stfille.setNomTable("MVTSTOCKFILLELIB");
                    MvtStockFilleLib[] filleLibs = (MvtStockFilleLib[]) CGenUtil.rechercher(stfille, null, null, c, " and IDOBJET = '"+ this.getId()+"' AND idProduit='" + ingredient.getId() + "'");
                    if (filleLibs.length > 0){
                        pu = filleLibs[0].getPu();
                    }
                    charge.setPu(pu);
                    charge.setQte(qte);
                    charge.setIdfabrication(fabFilles[0].getIdMere());
                    charge.setIdingredients(ingredient.getId());
                    charge.setDaty(this.getDaty());
                    charge.setLibelle("Consommable " + ingredient.getLibelle() + " pour l'OF  " + ofFille.getIdOffille()+ " du releve "+this.getId());
                    System.out.println(charge.toString());
                    charge.createObject(u, c);
                } else {
                    throw new Exception("Aucune fabrication de petri trouvée pour l'OFF : " + ofFille.getIdOffille());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (c != null && verif) {
                c.rollback();
            }
            throw e;
        } finally {
            if (c != null && verif) {
                c.close();
            }
        }
    }

    @Override
    public int updateObject(String u, Connection c) throws Exception {
        if(getEtat() <= 1){
        this.setEcart(this.getAncien() - this.getValeur());
        }
        return super.updateObject(u, c);
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public boolean checkMvtStock() throws Exception {
        MvtStock mvtStock = new MvtStock();
        mvtStock.setIdobjet(this.getId());
        MvtStock[] mvtStocks = (MvtStock[]) CGenUtil.rechercher(mvtStock, null, null, " ");
        if (mvtStocks.length > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void checkAlreadyGenerated() throws Exception {
        if (checkMvtStock()) {
            throw new Exception("Ce compteur a déjà un mouvement de stock !");
        }
    }

    private MvtStock createMvtStockHeader() throws Exception {
        MvtStock mvtStock = new MvtStock();
        mvtStock.setIdMagasin(this.getIdMagasin());
        mvtStock.setIdobjet(this.getId());
        mvtStock.setIdCategorieStock(ConstanteSocobis.ID_CATEGORIESTOCK_NORMAL);
        mvtStock.setIdTypeMvStock(ConstanteSocobis.TYPE_MVT_SORTIE);
        mvtStock.setDaty(this.getDaty());
        return mvtStock;
    }

    private double calculateQuantiteSortie() {
        if ("IG000363".equalsIgnoreCase(this.getIdCategorie())) {
            return this.getEcart() * 4000 / 100 * 0.577;
        }
        return this.getEcart();
    }

    private List<MvtStockFille> generateMvtDetails(Connection conn, double quantiteSortie) throws Exception {

        List<MvtStockFille> details = new ArrayList<>();

        MvtStockEntreeAvecReste[] stocks = getStocksDisponibles(conn);

        List<MvtStockEntreeAvecReste> stockList = new ArrayList<>(Arrays.asList(stocks));

        for (MvtStockEntreeAvecReste stock : stockList) {
            if (quantiteSortie <= 0) break;

            if (stock.getReste() <= 0) continue;

            double qtePrise = Math.min(stock.getReste(), quantiteSortie);

            MvtStockFille detail = createMvtDetail(stock, qtePrise);

            details.add(detail);

            stock.setReste(stock.getReste() - qtePrise);
            quantiteSortie -= qtePrise;
        }

        if (quantiteSortie > 0) {
            throw new Exception("Stock insuffisant !");
        }

        return details;
    }

    private MvtStockEntreeAvecReste[] getStocksDisponibles(Connection conn) throws Exception {
        MvtStockEntreeAvecReste filtre = new MvtStockEntreeAvecReste();
        filtre.setNomTable("V_ETATSTOCK_ENTREE_STANDARD");
        filtre.setIdMagasin(getIdMagasin());
        filtre.setIdProduit(this.getIdCategorie());

        return (MvtStockEntreeAvecReste[]) CGenUtil.rechercher(
                filtre, null, null, conn, " order by daty asc"
        );
    }

    private MvtStockFille createMvtDetail(MvtStockEntreeAvecReste stock, double quantite) throws Exception {
        MvtStockFille detail = new MvtStockFille();

        detail.setIdProduit(stock.getIdProduit());
        detail.setSortie(quantite);
        detail.setMvtSrc(stock.getId());
        detail.setPu(stock.getPu());

        return detail;
    }

    private void persistMvtStock(MvtStock mvtStock, String user, Connection conn) throws Exception {
        mvtStock = (MvtStock) mvtStock.createObject(user, conn);
        mvtStock.validerObject(user, conn);
    }

    public MvtStock genererMvtStock(String user, Connection conn) throws Exception {
        boolean shouldCloseConnection = false;

        try {
            conn = openConnectionIfNeeded(conn);
            checkAlreadyGenerated();

            MvtStock mvtStock = createMvtStockHeader();

            double quantiteSortie = calculateQuantiteSortie();

            List<MvtStockFille> details = generateMvtDetails(conn, quantiteSortie);

            mvtStock.setFille(details.toArray(new MvtStockFille[0]));
            persistMvtStock(mvtStock, user, conn);

            return mvtStock;

        } finally {
            closeConnection(conn, shouldCloseConnection);
        }
    }

    private Connection openConnectionIfNeeded(Connection conn) throws Exception {
        if (conn == null) {
            return new UtilDB().GetConn();
        }
        return conn;
    }

    private void closeConnection(Connection conn, boolean shouldClose) throws Exception {
        if (conn != null && shouldClose) {
            conn.close();
        }
    }

    public CompteurMaintenance getById(String id) throws Exception {
        CompteurMaintenance compteurMaintenance = new CompteurMaintenance();
        compteurMaintenance.setId(id);
        CompteurMaintenance[] compteurMaintenances = (CompteurMaintenance[]) CGenUtil.rechercher(compteurMaintenance, null, null, " ");
        if (compteurMaintenances.length > 0) {
            return compteurMaintenances[0];
        }
        return null;
    }
}