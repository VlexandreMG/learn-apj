package proforma;

import produits.Ingredients;
import vente.BonDeCommandeFIlleCplArbiochem;

public class ProformaDetailsLibArbiochem extends ProformaDetailsArbiochem{
    private String idProformaLib,idProduitLib;
    private double puTotal,remisemontant,montanttotal, montanttva, montantttc, montantristourne, fraislivraison, fraistransport, montantremise;
    private String uniteLib,reference;
    private double punet, montantht;
    private double calorie, poidsparkilo, poidstotal;
    private double puRemiseLib;
    private double puingredient, contenue;
    private String contenuelib;
    private String unite2;
    private double restebc;

    public double getRestebc() {
        return restebc;
    }

    public void setRestebc(double restebc) {
        this.restebc = restebc;
    }

    public double getMontantremise() {
        return montantremise;
    }

    public void setMontantremise(double montantremise) {
        this.montantremise = montantremise;
    }

    public String getUnite2() {
        return unite2;
    }

    public void setUnite2(String unite2) {
        this.unite2 = unite2;
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

    public double getPoidsparkilo() {
        return poidsparkilo;
    }

    public void setPoidsparkilo(double poidsparkilo) {
        this.poidsparkilo = poidsparkilo;
    }

    public double getPoidstotal() {
        return poidstotal;
    }

    public void setPoidstotal(double poidstotal) {
        this.poidstotal = poidstotal;
    }

    private String idDevis, image;
    public ProformaDetailsLibArbiochem()throws Exception {
        this.setNomTable("PROFORMADETAILS_CPL");
    }

    public double getMontantttc() {
        return montantttc;
    }

    public double getFraistransport() {
        return fraistransport;
    }

    public void setFraistransport(double fraistransport) {
        this.fraistransport = fraistransport;
    }

    public void setMontantttc(double montantttc) {
        this.montantttc = montantttc;
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

    public double getRemisemontant() {
        return remisemontant;
    }

    public void setRemisemontant(double remisemontant) {
        this.remisemontant = remisemontant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUniteLib() {
        return uniteLib;
    }

    public void setUniteLib(String uniteLib) {
        this.uniteLib = uniteLib;
    }

    public String getIdProformaLib() {
        return idProformaLib;
    }

    public void setIdProformaLib(String idProformaLib) {
        this.idProformaLib = idProformaLib;
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }

    public double getPuTotal() {
        return puTotal;
    }

    public void setPuTotal(double puTotal) {
        this.puTotal = puTotal;
    }

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getMontantristourne() {
        return montantristourne;
    }

    public void setMontantristourne(double montantristourne) {
        this.montantristourne = montantristourne;
    }

    public double getFraislivraison() {
        return fraislivraison;
    }

    public void setFraislivraison(double fraislivraison) {
        this.fraislivraison = fraislivraison;
    }

    public BonDeCommandeFIlleCplArbiochem createBonDeCommandeFilleLib() throws Exception {
        try {
            BonDeCommandeFIlleCplArbiochem ligne = new BonDeCommandeFIlleCplArbiochem();

            ligne.setProduit(this.getIdProduit());
            Ingredients ing =(Ingredients) new Ingredients().getById(this.getIdProduit(), null, null);
            ligne.setCalorie(ing.getCalorie());
            ligne.setQuantite(this.getRestebc());
            ligne.setPu(this.getPu());
            ligne.setTva(this.getTva());
            ligne.setIdDevise(this.getIdDevise());
            ligne.setUnite(this.getUnite());
            ligne.setUniteLib(this.getUniteLib());
            ligne.setDesignation(this.getDesignation());
            ligne.setRemise(this.getRemise());
            ligne.setMontantht((this.getPu() - ((this.getPu() * this.getRemise() / 100)) +this.getRemiseArbiochem() ) * this.getQte());
            ligne.setPunet(this.getPu() - (this.getPu() * this.getRemise() / 100) + ((this.getPu() * this.getTva() / 100)) + this.getRemiseArbiochem());
            ligne.setMontantttc(this.getMontantttc());
            ligne.setRistourne(getRistourne());
            ligne.setRemiseArbiochem(this.getRemiseArbiochem());
            ligne.setRistourneArbiochem(this.getRistourneArbiochem());
            return ligne;
        }catch(Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public double getPuRemiseLib() {
        if(this.getRemise()>0){
            return this.getPu() * (100 - this.getRemise())/100;
        }
        return this.getPu();
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
