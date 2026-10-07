/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vente;

import bean.LibelleAffichage;
import chatbot.AiColDesc;
import chatbot.AiTabDesc;
import chatbot.ClassIA;

/**
 *
 * @author Angela
 */
@AiTabDesc("La structure de ma table de vente mère est comme ceci: ")
public class VenteLib extends Vente implements ClassIA {
    @LibelleAffichage("Magasin")
    private String idMagasinLib;
    private String idOrigine;
    private String etatLib;
    private double montanttotal;
    @LibelleAffichage("Devise")
    private String idDevise;
    private String idEcriture;
    private String adresse;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @AiColDesc("nom du client")
    @LibelleAffichage("Client")
    private String idClientLib;

    public String getIdEcriture() {
        return idEcriture;
    }

    public void setIdEcriture(String idEcriture) {
        this.idEcriture = idEcriture;
    }

    @LibelleAffichage("Montant pay&eacute;")
    private double montantpaye;
    @LibelleAffichage("Reste &agrave; payer")
    private double montantreste;
    @AiColDesc("Montant total de la vente")
    @LibelleAffichage("Montant TTC")
    private double montantttc;
    @LibelleAffichage("Montant TTC (Ar)")
    double montantTtcAr;
    protected double avoir, montantremise,montant,poids;
    private String referencefacture,modepaiementlib;
    @LibelleAffichage("Mois")
    private int mois;
    @LibelleAffichage("Ann&eacute;e")
    private int annee;
    private String modelivraisonlib;
    @LibelleAffichage("ID Province")
    private String idprovince;
    @LibelleAffichage("Province")
    private String provincelib;
    private String livraison;
    private double frais;
    private double colis, montantimpute;
    private String estprevuLib;

    public String getEstprevuLib() {
        return estprevuLib;
    }

    public void setEstprevuLib(String estprevuLib) {
        this.estprevuLib = estprevuLib;
    }

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    public String getModelivraisonlib() {
        return modelivraisonlib;
    }

    public void setModelivraisonlib(String modelivraisonlib) {
        this.modelivraisonlib = modelivraisonlib;
    }

    public String getModepaiementlib() {
        return modepaiementlib;
    }

    public void setModepaiementlib(String modepaiementlib) {
        this.modepaiementlib = modepaiementlib;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getMontantremise() {
        return montantremise;
    }

    public void setMontantremise(double montantremise) {
        this.montantremise = montantremise;
    }

    public String getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(String idOrigine) {
        this.idOrigine = idOrigine;
    }
    

    public double getAvoir() {
        return avoir;
    }

    public void setAvoir(double avoir) {
        this.avoir = avoir;
    }

    public String getDesignation() {
        return designation;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public String getIdprovince() {
        return idprovince;
    }

    public void setIdprovince(String idprovince) {
        this.idprovince = idprovince;
    }

    public String getProvincelib() {
        return provincelib;
    }

    public void setProvincelib(String provincelib) {
        this.provincelib = provincelib;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getMontantTtcAr() {
        return montantTtcAr;
    }

    public void setMontantTtcAr(double montantTtcAr) {
        this.montantTtcAr = montantTtcAr;
    }

    public double getMontantttc() {
        return montantttc;
    }

    public void setMontantttc(double montantttc) {
        this.montantttc = montantttc;
    }
        

    public void setMontantpaye(double montantpaye) {
        this.montantpaye = montantpaye;
    }

    public double getMontantpaye() {
        return montantpaye;
    }

    public void setMontantreste(double montantreste) {
        this.montantreste = montantreste;
    }

    public double getMontantreste() {
        return montantreste;
    }

    public String getIdClientLib() {
        return idClientLib;
    }

    public void setIdClientLib(String idClientLib) {
        this.idClientLib = idClientLib;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public double getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(double montanttotal) {
        this.montanttotal = montanttotal;
    }

    public VenteLib() {
        this.setNomTable("VENTE_CPL");
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public String getChaineEtat(){
        return chaineEtat(this.getEtat());
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getReferencefacture() {
        return referencefacture;
    }

    public double getMontantimpute() {
        return montantimpute;
    }

    public void setMontantimpute(double montantimpute) {
        this.montantimpute = montantimpute;
    }

    public void setReferencefacture(String referencefacture) {
        this.referencefacture = referencefacture;
    }
    public double getColis() {
        return colis;
    }

    public void setColis(double colis) {
        this.colis = colis;
    }
}
