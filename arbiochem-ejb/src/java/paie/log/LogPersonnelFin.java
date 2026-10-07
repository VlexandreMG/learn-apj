/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paie.log;

/**
 *
 * @author mariano
 */
public class LogPersonnelFin extends LogPersonnelValide{
    private String idcategorie, ctg, indicegrade, indice_fonctionnel, statut,categorieval,ctgval, numero_cnaps, numero_ostie;
    private String temporaire,idpersonnel;
    private double soldeConge;
    private double droit,prie,resteconge;
    private String departementLib;
    private String idDepartement;
    private String etatLib;
    private String idQualification;
    private String qualificationLib;

    public String getDepartementLib() {
        return this.departementLib;
    }

    public void setDepartementLib(String departementLib) {
        this.departementLib = departementLib;
    }

    public double getDroit() {
        return droit;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public void setDroit(double droit) {
        this.droit = droit;
    }

    public double getPrie() {
        return prie;
    }

    public void setPrie(double prie) {
        this.prie = prie;
    }

    public double getResteconge() {
        return resteconge;
    }

    public void setResteconge(double resteconge) {
        this.resteconge = resteconge;
    }

    
    public String getCtgval() {
        return ctgval;
    }

    public void setCtgval(String ctgval) {
        this.ctgval = ctgval;
    }

    public String getCategorieval() {
        return categorieval;
    }

    public void setCategorieval(String categorieval) {
        this.categorieval = categorieval;
    }

    public LogPersonnelFin() {
        this.setMode("select");
        this.setNomTable("LOG_PERSONNEL_FIN");
    }

    public String getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(String idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getCtg() {
        return ctg;
    }

    public void setCtg(String ctg) {
        this.ctg = ctg;
    }

    public String getIndicegrade() {
        return indicegrade;
    }

    public void setIndicegrade(String indicegrade) {
        this.indicegrade = indicegrade;
    }

    public String getIndice_fonctionnel() {
        return indice_fonctionnel;
    }

    public void setIndice_fonctionnel(String indice_fonctionnel) {
        this.indice_fonctionnel = indice_fonctionnel;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNumero_cnaps() {
        return numero_cnaps;
    }

    public void setNumero_cnaps(String numero_cnaps) {
        this.numero_cnaps = numero_cnaps;
    }

    public String getNumero_ostie() {
        return numero_ostie;
    }

    public void setNumero_ostie(String numero_ostie) {
        this.numero_ostie = numero_ostie;
    }

    public String getTemporaire() {
        return temporaire;
    }

    public void setTemporaire(String temporaire) {
        this.temporaire = temporaire;
    }

    public double getSoldeConge() {
        return soldeConge;
    }

    public void setSoldeConge(double soldeConge) {
        this.soldeConge = soldeConge;
    }

    public String getQualificationLib() {
        return qualificationLib;
    }

    public void setQualificationLib(String qualificationLib) {
        this.qualificationLib = qualificationLib;
    }

    public String getIdQualification() {
        return idQualification;
    }

    public void setIdQualification(String idQualification) {
        this.idQualification = idQualification;
    }

    public String getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(String idDepartement) {
        this.idDepartement = idDepartement;
    }
}
