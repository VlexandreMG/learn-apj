/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avoir;

/**
 *
 * @author bruel
 */
public class AvoirFCFilleLib extends AvoirFCFille {
    private String idproduitlib , contenuelib;
    private double calorie;
    private double poids;
    private double ptHt;
    private double montantTVA;

    public String getContenuelib() {
        return contenuelib;
    }

    public void setContenuelib(String contenuelib) {
        this.contenuelib = contenuelib;
    }

    public double getMontantTVA() {
        return montantTVA;
    }

    public void setMontantTVA(double montantTVA) {
        this.montantTVA = montantTVA;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getPtHt() {
        return ptHt;
    }

    public void setPtHt(double ptHt) {
        this.ptHt = ptHt;
    }

    public AvoirFCFilleLib() throws Exception {
        this.setNomTable("AVOIRFCFILLELIB");
    }
    
    public AvoirFCFilleLib(String nomtable) throws Exception {
        
        this.setNomTable(nomtable);
    }

    public String getIdproduitlib() {
        return idproduitlib;
    }

    public void setIdproduitlib(String idproduitlib) {
        this.idproduitlib = idproduitlib;
    }
}
