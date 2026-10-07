/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faturefournisseur;


public class As_BonDeCommandeCpl extends As_BonDeCommande{
    double montantTVA;
    double montantHT;
    double montantTTC;
    double montantTTCAriary;
    String idDeviselib , idTraite , Traite;
    String etatLib;
    private String idServiceLib;
    private int nb;
    private double sommeAcompte;

    public double getSommeAcompte() {
        return sommeAcompte;
    }

    public void setSommeAcompte(double sommeAcompte) {
        this.sommeAcompte = sommeAcompte;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public As_BonDeCommandeCpl(){
        this.setNomTable("As_BonDeCommande_MERECPL");
    }

    public String getIdTraite() {
        return idTraite;
    }

    public void setIdTraite(String idTraite) {
        this.idTraite = idTraite;
    }

    public String getTraite() {
        return Traite;
    }

    public void setTraite(String Traite) {
        this.Traite = Traite;
    }

    public String getIdServiceLib() {
        return idServiceLib;
    }

    public void setIdServiceLib(String idServiceLib) {
        this.idServiceLib = idServiceLib;
    }

    public double getMontantTVA() {
        return montantTVA;
    }

    public void setMontantTVA(double montantTVA) {
        this.montantTVA = montantTVA;
    }

    public double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(double montantHT) {
        this.montantHT = montantHT;
    }

    public double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public double getMontantTTCAriary() {
        return montantTTCAriary;
    }

    public void setMontantTTCAriary(double montantTTCAriary) {
        this.montantTTCAriary = montantTTCAriary;
    }

    public String getIdDeviselib() {
        return idDeviselib;
    }

    public void setIdDeviselib(String idDeviselib) {
        this.idDeviselib = idDeviselib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
