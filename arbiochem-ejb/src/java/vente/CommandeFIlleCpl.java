package vente;

import chatbot.ClassIA;
import produits.Ingredients;
import proforma.ProformaDetailsLib;

public class CommandeFIlleCpl extends  CommandeFille{
    String produitLib;
    double qteOfRestante;
    double qteFabRestante;
    double qteNonLivre;
    String compte, uniteLib;
    double qtereste,montanttotal,montantremise,montanttva, montantdelaremise, montantristourne, fraistransport, poidstotal;

    private double punet, montantht,montantttc;
    double calorie;



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


    public CommandeFIlleCpl() throws Exception {
        super();
        setNomTable("COMMANDEFILLE_CPL_LIB");
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
    public BonDeCommandeFIlleCpl createBonDeCommandeFilleLib() throws Exception {
        try {
            BonDeCommandeFIlleCpl ligne = new BonDeCommandeFIlleCpl();

            ligne.setProduit(this.getProduit());
            Ingredients ing =(Ingredients) new Ingredients().getById(this.getProduit(), null, null);
            ligne.setCalorie(ing.getCalorie());
            ligne.setQuantite(this.getQuantite());
            ligne.setPu(this.getPu());
            ligne.setTva(this.getTva());
            ligne.setIdDevise(this.getIdDevise());
            ligne.setUnite(this.getUnite());
            ligne.setUniteLib(this.getUnite());
            ligne.setDesignation(this.getProduitLib());
            ligne.setRemise(this.getRemise());
            ligne.setMontantht((this.getPu() - (this.getPu() * this.getRemise() / 100)) * this.getQuantite());
            ligne.setPunet(this.getPu() - (this.getPu() * this.getRemise() / 100) + ((this.getPu() * this.getTva() / 100)));
            ligne.setMontantttc(this.getMontant());
            ligne.setRistourne(getRistourne());
            return ligne;
        }catch(Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public BonDeCommandeFIlleCpl createBonDeCommandeFilleCpl() throws Exception {
        try {
            BonDeCommandeFIlleCpl ligne = new BonDeCommandeFIlleCpl();

            ligne.setProduit(this.getProduit());
            ligne.setCalorie(this.getCalorie());
            ligne.setQuantite(this.getQuantite());
            ligne.setPu(this.getPu());
            ligne.setTva(this.getTva());
            ligne.setIdDevise(this.getIdDevise());
            ligne.setUnite(this.getUnite());
            ligne.setUniteLib(this.getUnite());
            ligne.setDesignation(this.getProduitLib());
            ligne.setRemise(this.getRemise());
            ligne.setMontantht((this.getPu() - (this.getPu() * this.getRemise() / 100)) * this.getQuantite());
            ligne.setPunet(this.getPu() - (this.getPu() * this.getRemise() / 100) + ((this.getPu() * this.getTva() / 100)));
            ligne.setMontantttc(this.getMontant());
            ligne.setRistourne(getRistourne());
            return ligne;
        }catch(Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public ProformaDetailsLib createProformaDetailsLib() throws Exception {
        try {
            ProformaDetailsLib ligne = new ProformaDetailsLib();

            ligne.setIdProduit(this.getProduit());
            ligne.setQte((int)this.getQuantite());
            ligne.setPu(this.getPu());
            ligne.setTva(this.getTva());
            ligne.setIdDevise(this.getIdDevise());
            ligne.setUnite(this.getUnite());
            ligne.setUniteLib(this.getUniteLib());
            ligne.setDesignation(this.getProduitLib());
            ligne.setRemise(this.getRemise());
            ligne.setMontantht((this.getPu() - (this.getPu() * this.getRemise() / 100)) * this.getQuantite());
            ligne.setPunet(this.getPu() - (this.getPu() * this.getRemise() / 100) + ((this.getPu() * this.getTva() / 100)));
            ligne.setMontantttc(ligne.getMontantht() + (ligne.getMontantht() * this.getTva() / 100));
            ligne.setRistourne(this.getRistourne());
            ligne.setCalorie(this.getCalorie());
            return ligne;
        }catch(Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
