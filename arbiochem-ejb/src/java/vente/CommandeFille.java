package vente;

import bean.ClassFille;
import proforma.ProformaDetailsLib;
import produits.Ingredients;
import bean.CGenUtil;
import java.sql.Connection;

public class CommandeFille extends ClassFille {
    private String id;
    private String produit;
    private String idc;
    private String unite;
    private double quantite;
    private double qteOf;
    private double qteFab;
    private double qteLivre;
    private double pu;
    private double montant;
    private double tva;
    private double remise;
    private double reste;
    private String idDevise;
    private double taux;
    private double puRevient;
    String libelleProduit;
    private String designation;
    private double ristourne;
    private double calorie;

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) throws Exception {
        if(quantite<=0){
            throw new Exception("La quantit\u00e9 doit \u00eatre sup\u00e9rieure \u00e0 0.");
        }
        this.quantite = quantite;
    }

    public double getQteOf() {
        return qteOf;
    }

    public void setQteOf(double qteOf) {
        this.qteOf = qteOf;
    }

    public double getQteFab() {
        return qteFab;
    }

    public void setQteFab(double qteFab) {
        this.qteFab = qteFab;
    }

    public double getQteLivre() {
        return qteLivre;
    }

    public void setQteLivre(double qteLivre) {
        this.qteLivre = qteLivre;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getPuRevient() {
        return puRevient;
    }

    public void setPuRevient(double puRevient) {
        this.puRevient = puRevient;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getRistourne() {
        return ristourne;
    }

    public void setRistourne(double ristourne) {
        this.ristourne = ristourne;
    }

    @Override
    public String getNomClasseMere() {
        return "vente.Commande";
    }

    @Override
    public String getLiaisonMere() {
        return "idc";
    }

    public CommandeFille() throws Exception {
        this.setNomTable("COMMANDEFILLE");
        this.setNomClasseMere("vente.Commande");
        this.setLiaisonMere("idc");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CMDF","GETseqCOMMANDEbesoinFille");
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

    public ProformaDetailsLib createProformaDetailsLib() throws Exception {
        try {
            ProformaDetailsLib ligne = new ProformaDetailsLib();

            ligne.setIdProduit(this.getProduit());
            Ingredients ing = (Ingredients) new Ingredients().getById(this.getProduit(), null, null);
            ligne.setQte((int)this.getQuantite());
            ligne.setPu(this.getPu());
            ligne.setTva(this.getTva());
            ligne.setIdDevise(this.getIdDevise());
            ligne.setUnite(this.getUnite());
            ligne.setDesignation(this.getDesignation());
            ligne.setRemise(this.getRemise());
            ligne.setMontantht((this.getPu() - (this.getPu() * this.getRemise() / 100)) * this.getQuantite());
            ligne.setPunet(this.getPu() - (this.getPu() * this.getRemise() / 100) + ((this.getPu() * this.getTva() / 100)));
            ligne.setMontantttc(ligne.getMontantht() + (ligne.getMontantht() * this.getTva() / 100));
            ligne.setRistourne(this.getRistourne());
            ligne.setCalorie(ing.getCalorie());
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
}

