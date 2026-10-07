package paie.hs;

import java.sql.Date;

public class HsMoisCpl extends HsMois {

    private String idCategorieLib;
    private String idDepartementLib;
    private String etatLib;
    private String moisLib,matricule,nomPersonnel,semaine;
    private double IF,JF,MN,HD,HS;
    private Date dateDebutSemaine,dateFinSemaine;

    public String getMoisLib() {
        return this.moisLib;
    }

    public void setMoisLib(String moisLib) {
        this.moisLib = moisLib;
    }

    public HsMoisCpl() {
        this.setNomTable("HSMOIS_CPL");
    }

    public String getIdCategorieLib() {
        return idCategorieLib;
    }

    public void setIdCategorieLib(String idCategorieLib) {
        this.idCategorieLib = idCategorieLib;
    }

    public String getIdDepartementLib() {
        return idDepartementLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public void setIdDepartementLib(String idDepartementLib) {
        this.idDepartementLib = idDepartementLib;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public double getIF() {
        return IF;
    }

    public void setIF(double IF) {
        this.IF = IF;
    }

    public double getJF() {
        return JF;
    }

    public void setJF(double JF) {
        this.JF = JF;
    }

    public double getMN() {
        return MN;
    }

    public void setMN(double MN) {
        this.MN = MN;
    }

    public double getHD() {
        return HD;
    }

    public void setHD(double HD) {
        this.HD = HD;
    }

    public Date getDateDebutSemaine() {
        return dateDebutSemaine;
    }

    public void setDateDebutSemaine(Date dateDebutSemaine) {
        this.dateDebutSemaine = dateDebutSemaine;
    }

    public Date getDateFinSemaine() {
        return dateFinSemaine;
    }

    public void setDateFinSemaine(Date dateFinSemaine) {
        this.dateFinSemaine = dateFinSemaine;
    }

    public double getHS() {
        return HS;
    }

    public void setHS(double HS) {
        this.HS = HS;
    }

    public String getSemaine() {
        return semaine;
    }

    public void setSemaine(String semaine) {
        this.semaine = semaine;
    }
}
