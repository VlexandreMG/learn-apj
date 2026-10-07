package paie.onBoarding;

import bean.ClassFille;

import java.sql.Connection;

public class ProgrammeIntegrationDetail extends ClassFille {
    private String id;
    private String idprogramedetail;
    private String jourdelasemaine;
    private String heure;
    private double duree;
    private String actiontheme;
    private String contenue;
    private String idintervenant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdprogramedetail() {
        return idprogramedetail;
    }

    public void setIdprogramedetail(String idprogramedetail) {
        this.idprogramedetail = idprogramedetail;
    }

    public String getJourdelasemaine() {
        return jourdelasemaine;
    }

    public void setJourdelasemaine(String jourdelasemaine) {
        this.jourdelasemaine = jourdelasemaine;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public String getActiontheme() {
        return actiontheme;
    }

    public void setActiontheme(String actiontheme) {
        this.actiontheme = actiontheme;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public String getIdintervenant() {
        return idintervenant;
    }

    public void setIdintervenant(String idintervenant) {
        this.idintervenant = idintervenant;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.onBoarding.ProgrammeIntegration";
    }

    @Override
    public String getLiaisonMere() {
        return "idprogramedetail";
    }

    public ProgrammeIntegrationDetail() throws Exception {
        this.setNomTable("PROGRAMME_INTEGRATION_DETAIL");
        this.setNomClasseMere("paie.onBoarding.ProgrammeIntegration");
        this.setLiaisonMere("idprogramedetail");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PGID","GET_SEQ_PROG_INT_DET");
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

