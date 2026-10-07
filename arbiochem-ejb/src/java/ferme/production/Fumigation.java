package ferme.production;

import bean.ClassEtat;
import java.sql.Connection;
import java.sql.Date;

public class Fumigation extends ClassEtat {
    private String id;
    private Date daty;
    private String idnumerovague;
    private String idoperateur;
    private String idlot;
    private double nombreoac;
    private String heuredebutfumigation;
    private String heurefinfumigation;
    private String heuredebutextraction;
    private String heurefinextraction;
    private String idproduit;
    private double qte;
    private double temperaturemin;
    private double temperaturemax;
    private String idBatiment, idParquet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdnumerovague() {
        return idnumerovague;
    }

    public void setIdnumerovague(String idnumerovague) {
        this.idnumerovague = idnumerovague;
    }

    public String getIdoperateur() {
        return idoperateur;
    }

    public void setIdoperateur(String idoperateur) {
        this.idoperateur = idoperateur;
    }

    public String getIdBatiment() {
        return idBatiment;
    }

    public void setIdBatiment(String idBatiment) {
        this.idBatiment = idBatiment;
    }

    public String getIdParquet() {
        return idParquet;
    }

    public void setIdParquet(String idParquet) {
        this.idParquet = idParquet;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }

    public double getNombreoac() {
        return nombreoac;
    }

    public void setNombreoac(double nombreoac) {
        this.nombreoac = nombreoac;
    }

    public String getHeuredebutfumigation() {
        return heuredebutfumigation;
    }

    public void setHeuredebutfumigation(String heuredebutfumigation) {
        this.heuredebutfumigation = heuredebutfumigation;
    }

    public String getHeurefinfumigation() {
        return heurefinfumigation;
    }

    public void setHeurefinfumigation(String heurefinfumigation) {
        this.heurefinfumigation = heurefinfumigation;
    }

    public String getHeuredebutextraction() {
        return heuredebutextraction;
    }

    public void setHeuredebutextraction(String heuredebutextraction) {
        this.heuredebutextraction = heuredebutextraction;
    }

    public String getHeurefinextraction() {
        return heurefinextraction;
    }

    public void setHeurefinextraction(String heurefinextraction) {
        this.heurefinextraction = heurefinextraction;
    }

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
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



    public Fumigation() throws Exception {
        this.setNomTable("FUMIGATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FMG","getseq_fumigation");
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

