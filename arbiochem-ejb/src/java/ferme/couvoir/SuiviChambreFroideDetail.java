package ferme.couvoir;

import bean.ClassFille;
import java.sql.Connection;

public class SuiviChambreFroideDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idresponsable;
    private String heure;
    private int temperature;
    private int humidite;
    private String remarque;

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

    public String getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(String idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidite() {
        return humidite;
    }

    public void setHumidite(int humidite) {
        this.humidite = humidite;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.couvoir.SuiviChambreFroide";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public SuiviChambreFroideDetail() throws Exception {
        this.setNomTable("SUIVICHAMBREFROIDEDETAIL");
        this.setNomClasseMere("ferme.couvoir.SuiviChambreFroide");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SCFD","getSEQsuiviChambreFroideDetail");
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

