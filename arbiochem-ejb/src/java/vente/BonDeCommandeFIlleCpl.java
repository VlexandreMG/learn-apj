package vente;

import chatbot.AiTabDesc;
import chatbot.ClassIA;
import produits.Ingredients;

import java.io.Serializable;
import java.text.DecimalFormat;

@AiTabDesc("La structure de ma table de bon de commande, Dans ma table de bon de commande ou BC, on parle de OF (ordre de fabrication), FAB (fabrication) et LIVRE (livraison) de produits, la colonne nombrepargroupe n'existe pas, ne l'utilise jamais: ")
public class BonDeCommandeFIlleCpl extends BonDeCommandeFille implements ClassIA {
    String produitLib;
    double qteOfRestante;
    double qteFabRestante;
    double qteNonLivre;
    String compte, uniteLib;
    double qtereste,montanttotal,montantremise,montanttva, montantdelaremise, montantristourne, fraistransport, poidstotal;

    private double punet, montantht,montantttc;
    double calorie;
    private double puRemiseLib;

    double montantremiser;
    String unite2;

    public double getMontantTTCAr() throws Exception
    {
        return this.getMontantttc()*this.getTaux();
    }
    public String getUnite2() {
        return unite2;
    }

    public void setUnite2(String unite2) {
        this.unite2 = unite2;
    }

    private double puingredient, contenue;
    private String contenuelib;

    public double getMontantremiser() {
        return montantremiser;
    }

    public void setMontantremiser(double montantremiser) {
        this.montantremiser = montantremiser;
    }

    public double getPuingredient() {
        return puingredient;
    }

    public void setPuingredient(double puingredient) {
        this.puingredient = puingredient;
    }

    public double getContenue() {
        return contenue;
    }

    public void setContenue(double contenue) {
        this.contenue = contenue;
    }

    public String getContenuelib() {
        return (int)getContenue()+"x"+(double)getPuingredient();
    }

    

    public double getMontantdelaremise() {
        return montantdelaremise;
    }

    public void setMontantdelaremise(double montantdelaremise) {
        this.montantdelaremise = montantdelaremise;
    }

    public double getFraistransport() {
        return fraistransport;
    }

    public void setFraistransport(double fraistransport) {
        this.fraistransport = fraistransport;
    }

    public double getPoidstotal() {
        return poidstotal;
    }

    public void setPoidstotal(double poidstotal) {
        this.poidstotal = poidstotal;
    }

    public double getPunet() {
        return punet;
    }

    public void setPunet(double punet) {
        this.punet = punet;
    }

    public double getMontantht() {
        return montantht;
    }

    public void setMontantht(double montantht) {
        this.montantht = montantht;
    }

    public double getMontantttc() {
        return montantttc;
    }

    public void setMontantttc(double montantttc) {
        this.montantttc = montantttc;
    }

    public String getUniteLib() {
        return uniteLib;
    }

    public void setUniteLib(String uniteLib) {
        this.uniteLib = uniteLib;
    }

    public double getMontantremise() {
        return montantremise;
    }

    public void setMontantremise(double montantremise) {
        this.montantremise = montantremise;
    }

    public double getMontanttva() {
        return montanttva;
    }

    public void setMontanttva(double montanttva) {
        this.montanttva = montanttva;
    }

    public double getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(double montanttotal) {
        this.montanttotal = montanttotal;
    }

    @Override
    public String getNomTableIA() {
        return "BC_CLIENT_FILLE_CPL_LIB_VISEE";
    }
    @Override
    public String getUrlListe() {
        return "/socobis/pages/module.jsp?but=vente/bondecommande-liste.jsp&currentMenu=MNDN000000001072";
    }
    @Override
    public String getUrlAnalyse() {
        return "/socobis/pages/module.jsp?but=vente/bondecommande-liste.jsp&currentMenu=MNDN000000001072";
    }
    @Override
    public String getUrlSaisie() {
        return "/socobis/pages/module.jsp?but=vente/bondecommande/bondecommande-saisie.jsp&currentMenu=MNDN000000001071";
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
        return new BonDeCommande();
    }

    public BonDeCommandeFIlleCpl() throws Exception {
        super();
        setNomTable("BC_CLIENT_FILLE_CPL_LIB");
    }

    public String getProduitLib() {
        return produitLib;
    }

    public void setProduitLib(String produitLib) {
        this.produitLib = produitLib;
    }

    public double getQteOfRestante() {
        return qteOfRestante;
    }

    public void setQteOfRestante(double qteOfRestante) {
        this.qteOfRestante = qteOfRestante;
    }

    public double getQteFabRestante() {
        return qteFabRestante;
    }

    public void setQteFabRestante(double qteFabRestante) {
        this.qteFabRestante = qteFabRestante;
    }

    public double getQteNonLivre() {
        return qteNonLivre;
    }

    public void setQteNonLivre(double qteNonLivre) {
        this.qteNonLivre = qteNonLivre;
    }

    public double getMontant() {
        return this.getPu() * this.getQuantite();
    }
    public String getCompte() {
        return compte;
    }
    public void setCompte(String compte) {
        this.compte = compte;
    }

    public double getMontantristourne() {
        return montantristourne;
    }

    public void setMontantristourne(double montantristourne) {
        this.montantristourne = montantristourne;
    }

    public double getQtereste() {
        return qtereste;
    }
    public void setQtereste(double qtereste) {
        this.qtereste = qtereste;
    }
    public VenteDetailsLib createVenteFilleLib() throws Exception {
        try {
            VenteDetailsLib ligne = new VenteDetailsLib();
            ligne.setIdProduit(this.getProduit());
            Ingredients i = (Ingredients) new Ingredients().getById(this.getProduit(), null, null);
            ligne.setCalorie(i.getCalorie());
            ligne.setQte(this.getQtereste());
            ligne.setPu(this.getPu());
            ligne.setTva(this.getTva());
            ligne.setIdDevise(this.getIdDevise());
            ligne.setDesignation(this.getProduitLib());
            ligne.setCompte(this.getCompte());
            ligne.setIdbcfille(this.getId());
            ligne.setRemise(this.getRemise());
            ligne.setUnitelib(this.getUniteLib());
            ligne.setUnite(this.getUnite());
            ligne.setMontantht((this.getPu() - (this.getPu() * this.getRemise() / 100)) * this.getQtereste());
            ligne.setPunet(this.getPu() - (this.getPu() * this.getRemise() / 100) + ((this.getPu() * this.getTva() / 100)));
            ligne.setRistourne(getRistourne());

            double montantttc = ligne.getMontantht() + (ligne.getMontantht() * (this.getTva() / 100));
            ligne.setMontantTTC(montantttc);
//            ligne.setMontantttc((this.getPu() - (this.getPu() * this.getRemise() / 100) + ((this.getPu() * this.getTva() / 100)))* this.getQtereste());
            return ligne;
        }catch(Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }
    public double getPuRemiseLib() {
        return this.getPu() * (100 - this.getRemise())/100;
    }

    public void setPuRemiseLib(double puRemiseLib) {
        this.puRemiseLib = puRemiseLib;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }
}
