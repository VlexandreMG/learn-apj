package ferme.production;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class ReceptionOAC extends ClassMere {
    private String id;
    private Date datereception;
    private Date dateponte;
    private Date datecollecte;
    private String idnumerocollecte;
    private String heuredepartferme;
    private String heurearriveecouvoir;
    private String idresponsable;
    private String idchambrefroide;
    private String idchauffeur;
    private String idvehicule;
    private double temperatureminvehicule;
    private double temperaturemaxvehicule;
    private String idProvenance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatereception() {
        return datereception;
    }

    public void setDatereception(Date datereception) {
        this.datereception = datereception;
    }

    public Date getDateponte() {
        return dateponte;
    }

    public void setDateponte(Date dateponte) {
        this.dateponte = dateponte;
    }

    public Date getDatecollecte() {
        return datecollecte;
    }

    public void setDatecollecte(Date datecollecte) {
        this.datecollecte = datecollecte;
    }

    public String getIdnumerocollecte() {
        return idnumerocollecte;
    }

    public void setIdnumerocollecte(String idnumerocollecte) {
        this.idnumerocollecte = idnumerocollecte;
    }

    public String getHeuredepartferme() {
        return heuredepartferme;
    }

    public void setHeuredepartferme(String heuredepartferme) {
        this.heuredepartferme = heuredepartferme;
    }

    public String getHeurearriveecouvoir() {
        return heurearriveecouvoir;
    }

    public void setHeurearriveecouvoir(String heurearriveecouvoir) {
        this.heurearriveecouvoir = heurearriveecouvoir;
    }

    public String getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(String idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getIdchambrefroide() {
        return idchambrefroide;
    }

    public void setIdchambrefroide(String idchambrefroide) {
        this.idchambrefroide = idchambrefroide;
    }

    public String getIdchauffeur() {
        return idchauffeur;
    }

    public void setIdchauffeur(String idchauffeur) {
        this.idchauffeur = idchauffeur;
    }

    public String getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(String idvehicule) {
        this.idvehicule = idvehicule;
    }

    public double getTemperatureminvehicule() {
        return temperatureminvehicule;
    }

    public void setTemperatureminvehicule(double temperatureminvehicule) {
        this.temperatureminvehicule = temperatureminvehicule;
    }

    public double getTemperaturemaxvehicule() {
        return temperaturemaxvehicule;
    }

    public void setTemperaturemaxvehicule(double temperaturemaxvehicule) {
        this.temperaturemaxvehicule = temperaturemaxvehicule;
    }

    public String getIdProvenance() {
        return idProvenance;
    }

    public void setIdProvenance(String idProvenance) {
        this.idProvenance = idProvenance;
    }

    public ReceptionOAC() throws Exception {
        this.setNomTable("RECEPTIONOAC");
        this.setNomClasseFille("ferme.production.ReceptionOACDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RPO","getseq_receptionoac");
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
}

