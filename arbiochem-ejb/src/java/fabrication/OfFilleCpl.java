package fabrication;

import bean.AdminGen;
import produits.Ingredients;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import chatbot.AiColDesc;
import chatbot.AiTabDesc;
import chatbot.ClassIA;

import java.sql.Connection;
import java.sql.Date;
@AiTabDesc("La structure de ma table d'ordre de fabrication " +
        "(il n'y a aucune saisie relie par l'OF mais utilise Fabrication)" +
        " ou on peut voir les restes des fabrications et les qte a fabriquer d'un OF." +
        " Si l'utilisateur demande des reste a fabriquer ou qte a fabriquer, c'est la table a utiliser," +
        " Dans ma table de ordre de fabrication ou OF la colonne nombrepargroupe n'existe pas, " +
        "ne l'utilise jamais, utilise idMere pas id: " +
        "La colonne idIngredients correspond à l'identifiant du produit , la colonne idProduit n'existe pas")
public class OfFilleCpl extends OfFille implements ClassIA {
    String iduniteLib,libelleMere,idbc;
    double montantentree,montantsortie,pourc;
    @AiColDesc("Qte fabrique")
    double qteFabrique;
    @AiColDesc("Qte reste a fabriquer")
    double qteReste;
    java.sql.Date daty;
    String libelleexacte;
    double pv;
    double tauxRevient;

    @Override
    public String getNomTableIA() {
        return "OFFILLELIBSTOCKIA";
    }
    @Override
    public String getUrlListe() {
        return "/socobis/pages/module.jsp?but=fabrication/ordre-fabrication-liste.jsp&currentMenu=MENUDYN0304005";
    }
    @Override
    public String getUrlAnalyse() {
        return "/socobis/pages/module.jsp?but=fabrication/ordre-fabrication-liste.jsp&currentMenu=MENUDYN0304005";
    }
    @Override
    public String getUrlSaisie() {
        return "/socobis/pages/module.jsp?but=fabrication/ordre-fabrication-liste.jsp&currentMenu=MENUDYN0304005";
    }
    @Override
    public ClassIA getClassListe() {
        return this;
    }
    @Override
    public ClassIA getClassAnalyse() {
        return this;
    }

    @Override
    public ClassIA getClassSaisie() {
        return this;
    }

    public OfFilleCpl() throws Exception {
        super.setNomTable("OfFilleResteLib");
    }

    public String getIduniteLib() {
        return iduniteLib;
    }

    public void setIduniteLib(String iduniteLib) {
        this.iduniteLib = iduniteLib;
    }

     public String getIdbc() {
        return idbc;
    }

    public void setIdbc(String idbc) {
        this.idbc = idbc;
    }

    public double getQteFabrique() {
        return qteFabrique;
    }

    public void setQteFabrique(double qteFabrique) {
        this.qteFabrique = qteFabrique;
    }

    public double getQteReste() {
        return qteReste;
    }

    public void setQteReste(double qteReste) {
        this.qteReste = qteReste;
    }

    public String getLibelleMere() {
        return libelleMere;
    }

    public void setLibelleMere(String libelleMere) {
        this.libelleMere = libelleMere;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getLibelleexacte() {
        System.out.println("libexacte =="+libelleexacte); 
        return this.libelleexacte;
    }

    public void setLibelleexacte(String libelleexacte) {
        System.out.println("libexacte !!!=="+libelleexacte); 
        this.libelleexacte =libelleexacte;
    }

    public double getMontantentree() {
        return montantentree;
    }

    public void setMontantentree(double montantentree) {
        this.montantentree = montantentree;
    }

    public double getMontantsortie() {
        return montantsortie;
    }

    public void setMontantsortie(double montantsortie) {
        this.montantsortie = montantsortie;
    }

    public double getPourc() {
        return pourc;
    }

    public void setPourc(double pourc) {
        this.pourc = pourc;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public double getTauxRevient() {
        return this.getMontantentree()==0 ? 0: Utilitaire.arrondir(this.getMontantsortie() / this.getMontantentree() * 100,2);
    }
    public void setTauxRevient(double tauxRevient) {
        this.tauxRevient = tauxRevient;
    }
    public double getPurevient() {
        return this.getQteFabrique()==0 ? 0 : Utilitaire.arrondir(this.getMontantsortie() / this.getQteFabrique(),2);
    }

    public double getPuRevient(double montant){
        return this.getQteFabrique()==0 ? 0 : Utilitaire.arrondir(montant / this.getQteFabrique(),2);
    }
     public double getTauxRevientTotal(double montant , Connection c) throws Exception {
         boolean estOuvert = false;
         try {
             if (c == null) {
                 estOuvert = true;
                 c = new UtilDB().GetConn();
             }
             double puRevient = getPuRevient(montant);
             String idIngredients = getIdIngredients();

             if (idIngredients == null) {
                 return 0;
             }

             Ingredients ingredients = (Ingredients) new Ingredients().getById(idIngredients, "AS_INGREDIENTS", c);
             if (ingredients == null) {
                 return 0;
             }

             double puVente = ingredients.getPv();
             if (puVente == 0 || this.getMontantentree() == 0) {
                 return 0;
             }

             return Utilitaire.arrondir(puRevient / puVente * 100, 2);
         } catch (Exception e) {
             throw new Exception("Erreur lors du calcul du taux de revient total", e);
         }finally {
             if (c != null && estOuvert == true) {
                 c.close();
             }
         }
     }
    /* public String getLIBELLEEXTACTE(){

       return this.LIBELLEEXTACTE;
    }

    public void setLIBELLEEXTACTE(String LIBELLEEXTACTE){
        this.LIBELLEEXTACTE = LIBELLEEXTACTE;
    } 
*/

    @Override
    public String[] getValMotCles() {
        return new String[]{"id", "qte","idunite", "libelleexacte"};
    }

    @Override
    public String[] getMotCles() {
        return new String[]{"id", "qte", "idunite", "libelleexacte"};
    }
}
