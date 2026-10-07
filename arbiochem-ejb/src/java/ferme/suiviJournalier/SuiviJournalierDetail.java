package ferme.suiviJournalier;

import bean.ClassFille;
import java.sql.Connection;

public class SuiviJournalierDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idbatiment;
    private String idparquet;
    private double agejour;
    private double entree;
    private double tri;
    private double mortalite;
    private double culls;
    private double consignesuraliment;
    private double eau;
    private double temperaturemin;
    private double temperaturemax;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public String getIdbatiment() {
        return idbatiment;
    }

    public void setIdbatiment(String idbatiment) {
        this.idbatiment = idbatiment;
    }

    public String getIdparquet() {
        return idparquet;
    }

    public void setIdparquet(String idparquet) {
        this.idparquet = idparquet;
    }

    public double getAgejour() {
        return agejour;
    }

    public void setAgejour(double agejour) {
        this.agejour = agejour;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getTri() {
        return tri;
    }

    public void setTri(double tri) {
        this.tri = tri;
    }

    public double getMortalite() {
        return mortalite;
    }

    public void setMortalite(double mortalite) {
        this.mortalite = mortalite;
    }

    public double getCulls() {
        return culls;
    }

    public void setCulls(double culls) {
        this.culls = culls;
    }

    public double getConsignesuraliment() {
        return consignesuraliment;
    }

    public void setConsignesuraliment(double consignesuraliment) {
        this.consignesuraliment = consignesuraliment;
    }

    public double getEau() {
        return eau;
    }

    public void setEau(double eau) {
        this.eau = eau;
    }

    public double getTemperaturemin() {
        return temperaturemin;
    }

    public void setTemperaturemin(double temperaturemin) {
        this.temperaturemin = temperaturemin;
    }

    public double getTemperaturemax() {
        return temperaturemax;
    }

    public void setTemperaturemax(double temperaturemax) {
        this.temperaturemax = temperaturemax;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.suiviJournalier.SuiviJournalier";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public SuiviJournalierDetail() throws Exception {
        this.setNomTable("SUIVIJOURNALIERDETAIL");
        this.setNomClasseMere("ferme.suiviJournalier.SuiviJournalier");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SJD","getseq_suivijournalierdetail");
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

