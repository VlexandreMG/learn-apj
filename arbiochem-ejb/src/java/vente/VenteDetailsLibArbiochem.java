/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vente;

import bean.LibelleAffichage;
import chatbot.AiColDesc;
import chatbot.AiTabDesc;
import chatbot.ClassIA;
import encaissement.EncaissementDetails;

import java.sql.Date;

/**
 *
 * @author Angela
 */
@AiTabDesc("La structure de ma table de vente est comme ceci: ")
public class VenteDetailsLibArbiochem extends VenteDetailsArbiochem implements ClassIA {

    @LibelleAffichage("Libell&eacute; du produit")
    @AiColDesc("libellé du produit")
    String idProduitLib;
    @LibelleAffichage("Libell&eacute; du point")
    @AiColDesc("libellé du point")
    String idPointLib;
    @LibelleAffichage("Montant")
    @AiColDesc("montant")
    double montant;

    @LibelleAffichage("Montant remis&eacute;")
    double montantremiser;
    @LibelleAffichage("Unit&eacute; 2")
    String unite2;

    public String getUnite2() {
        return unite2;
    }

    public void setUnite2(String unite2) {
        this.unite2 = unite2;
    }

    @LibelleAffichage("Prix unitaire de l'ingr&eacute;dient")
    private double puingredient;
    @LibelleAffichage("Contenance")
    private double contenue;
    @LibelleAffichage("Libell&eacute; de la contenance")
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

    @LibelleAffichage("Num&eacute;ro de la cat&eacute;gorie")
    protected String idCategorie;
    @LibelleAffichage("Libell&eacute; de la cat&eacute;gorie")
    protected String idCategorieLib;
    @LibelleAffichage("Prix de revient")
    protected double puRevient;
    @LibelleAffichage("Prix total")
    protected  double puTotal;
    @LibelleAffichage("Reste")
    protected double reste;
    @LibelleAffichage("Date")
    protected Date daty;
    @LibelleAffichage("Magasin")
    protected String idMagasin;
    @LibelleAffichage("Libell&eacute; du magasin")
    protected String idMagasinLib;
    @LibelleAffichage("Point de vente")
    protected String idPoint;
    @LibelleAffichage("Unit&eacute;")
    protected String idUnite;
    @LibelleAffichage("Libell&eacute; de la devise")
    protected String idDeviseLib;
    @LibelleAffichage("Libell&eacute; de l'unit&eacute;")
    protected String unitelib;
    @LibelleAffichage("Cat&eacute;gorie du produit")
    private String categorieproduitlib;
    @LibelleAffichage("Libell&eacute; du client")
    private String idclientlib;
    @LibelleAffichage("Date pr&eacute;vue")
    protected Date datyprevu;
    @LibelleAffichage("Prix unitaire net")
    private double punet;
    @LibelleAffichage("Montant HT")
    private double montantht;
    @LibelleAffichage("Montant de la remise")
    private double montantremise;
    @LibelleAffichage("Poids")
    private double poids;
    @LibelleAffichage("Frais")
    private double frais;
    @LibelleAffichage("Prix unitaire remis&eacute;")
    private double puRemiseLib;

    @LibelleAffichage("Type de produit")
    private String typeProduit;
    @LibelleAffichage("Libell&eacute; du type de produit")
    private String typeProduitLib;

    @LibelleAffichage("Province")
    private String idprovince;
    @LibelleAffichage("Libell&eacute; de la province")
    private String idprovincelib;

    public String getIdprovince() {
        return idprovince;
    }

    public void setIdprovince(String idprovince) {
        this.idprovince = idprovince;
    }

    public String getIdprovincelib() {
        return idprovincelib;
    }

    public void setIdprovincelib(String idprovincelib) {
        this.idprovincelib = idprovincelib;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public String getTypeProduitLib() {
        return typeProduitLib;
    }

    public void setTypeProduitLib(String typeProduitLib) {
        this.typeProduitLib = typeProduitLib;
    }

    public String getIdclientlib() {
        return idclientlib;
    }

    public void setIdclientlib(String idclientlib) {
        this.idclientlib = idclientlib;
    }
    public String getCategorieproduitlib() {
        return categorieproduitlib;
    }

    public void setCategorieproduitlib(String categorieproduitlib) {
        this.categorieproduitlib = categorieproduitlib;
    }
     public Date getDatyprevu() {
        return datyprevu;
    }
    public void setDatyprevu(Date datyprevu) {
        this.datyprevu = datyprevu;
    }
    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getMontantremise() {
        return montantremise;
    }

    public void setMontantremise(double montantremise) {
        this.montantremise = montantremise;
    }

    public String getUnitelib() {
        return unitelib;
    }

    public void setUnitelib(String unitelib) {
        this.unitelib = unitelib;
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

    @Override
    public String getNomTableIA() {
        return "VENTE_DETAILS_CPL_2_VISEE";
    }
    @Override
    public String getUrlListe() {
        return "/socobis/pages/module.jsp?but=vente/vente-liste.jsp&currentMenu=MNDN000000007";
    }
    @Override
    public String getUrlAnalyse() {
        return "/socobis/pages/module.jsp?but=vente/vente-analyse.jsp&currentMenu=MNDN0000000111";
    }
    @Override
    public String getUrlSaisie() {
        return "/socobis/pages/module.jsp?but=vente/vente-saisie.jsp&currentMenu=MNDN000000006";
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
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public String getIdDeviseLib() {
        return idDeviseLib;
    }

    public void setIdDeviseLib(String idDeviseLib) {
        this.idDeviseLib = idDeviseLib;
    }

    public String getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(String idPoint) {
        this.idPoint = idPoint;
    }



    public String getIdPointLib() {
        return idPointLib;
    }



    public void setIdPointLib(String idPointLib) {
        this.idPointLib = idPointLib;
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



    public Date getDaty() {
        return daty;
    }



    public void setDaty(Date daty) {
        this.daty = daty;
    }



    public String getIdCategorie() {
        return idCategorie;
    }



    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }



    public String getIdCategorieLib() {
        return idCategorieLib;
    }



    public void setIdCategorieLib(String idCategorieLib) {
        this.idCategorieLib = idCategorieLib;
    }

    public double getPuRevient() {
        return puRevient;
    }

    public void setPuRevient(double puRevient) {
        this.puRevient = puRevient;
    }

    public VenteDetailsLibArbiochem() {
        this.setNomTable("VENTE_DETAILS_LIB");
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

    
        public EncaissementDetails generateEncaissementDetails()throws Exception{
        EncaissementDetails encaissementDetails = new EncaissementDetails();
        encaissementDetails.setMontant(montant);
        encaissementDetails.setIdOrigine(this.getId());
        encaissementDetails.setIdDevise(this.getIdDevise());
        encaissementDetails.setRemarque("Encaissement Vente "+this.getIdProduitLib());
        
        return encaissementDetails;
    }

    public double getMargeBrute() {
        return margeBrute;
    }

    public double getPuRemiseLib() {
        return this.getPu() * (100 - this.getRemise())/100;
    }

    public void setPuRemiseLib(double puRemiseLib) {
        this.puRemiseLib = puRemiseLib;
    }

    public String getRequetePourcentage(Date datyMin, Date datyMax) {
        StringBuilder req = new StringBuilder();

        req.append("SELECT\n");
        req.append("    DATY,\n");
        req.append("    IDPRODUITLIB,\n");
        req.append("    SUM(qte) AS qte,\n");
        req.append("    SUM(PUTOTAL) AS puTotal,\n");
        req.append("    ROUND(\n");
        req.append("        SUM(PUTOTAL)\n");
        req.append("        / NULLIF(SUM(SUM(PUTOTAL)) OVER (PARTITION BY DATY), 0),\n");
        req.append("        3\n");
        req.append("    ) * 100 AS puRevient\n");
        req.append("FROM VENTE_DETAILS_CPL_2_VISEE\n");

        boolean hasDatyMin = datyMin != null && !datyMin.toString().trim().isEmpty();
        boolean hasDatyMax = datyMax != null && !datyMax.toString().trim().isEmpty();

        if (hasDatyMin || hasDatyMax) {
            req.append("WHERE ");

            if (hasDatyMin) {
                req.append("DATY >= DATE '").append(datyMin).append("'");
            }

            if (hasDatyMin && hasDatyMax) {
                req.append(" AND ");
            }

            if (hasDatyMax) {
                req.append("DATY <= DATE '").append(datyMax).append("'");
            }

            req.append("\n");
        }

        req.append("GROUP BY\n");
        req.append("    DATY,\n");
        req.append("    IDPRODUITLIB\n");
        req.append("ORDER BY puRevient DESC");

        System.out.println(req.toString());

        return req.toString();
    }

    public String getRequetePourcentageGroupe(Date datyMin, Date datyMax) {
        StringBuilder req = new StringBuilder();

        req.append("SELECT\n");
        req.append("    IDPRODUITLIB,\n");
        req.append("    SUM(qte) AS qte,\n");
        req.append("    SUM(PUTOTAL) AS puTotal,\n");
        req.append("    ROUND(\n");
        req.append("        SUM(PUTOTAL)\n");
        req.append("        / NULLIF(SUM(SUM(PUTOTAL)) OVER (), 0),\n");
        req.append("        3\n");
        req.append("    ) * 100 AS puRevient\n");
        req.append("FROM VENTE_DETAILS_CPL_2_VISEE\n");

        boolean hasDatyMin = datyMin != null;
        boolean hasDatyMax = datyMax != null;

        if (hasDatyMin || hasDatyMax) {
            req.append("WHERE ");

            if (hasDatyMin) {
                req.append("DATY >= DATE '")
                        .append(datyMin)
                        .append("'");
            }

            if (hasDatyMin && hasDatyMax) {
                req.append(" AND ");
            }

            if (hasDatyMax) {
                req.append("DATY < DATE '")
                        .append(datyMax)
                        .append("' + 1");
            }

            req.append("\n");
        }

        req.append("GROUP BY\n");
        req.append("    IDPRODUITLIB\n");
        req.append("ORDER BY puRevient DESC");

        System.out.println(req.toString());

        return req.toString();
    }

}
