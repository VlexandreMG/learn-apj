package ferme.utils;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ResultatEtSomme;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConfigPoids extends ClassMAPTable {
    private String id;
    private double poidsmin;
    private double poidsmax;
    private double pas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPoidsmin() {
        return poidsmin;
    }

    public void setPoidsmin(double poidsmin) {
        this.poidsmin = poidsmin;
    }

    public double getPoidsmax() {
        return poidsmax;
    }

    public void setPoidsmax(double poidsmax) {
        this.poidsmax = poidsmax;
    }

    public double getPas() {
        return pas;
    }

    public void setPas(double pas) {
        this.pas = pas;
    }

    public ConfigPoids() throws Exception {
        this.setNomTable("CONFIGPOIDS_LIB");
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public ConfigPoids[] genererPlagePoids(double poidsMin, double poidsMax, double pas) throws Exception {
        if (pas <= 0) {
            throw new Exception("Le pas doit \u00EAtre sup\u00E9rieur \u00E0 0");
        }
        if (poidsMax < poidsMin) {
            throw new Exception("Le poids max doit \u00EAtre sup\u00E9rieur ou \u00E9gal au poids min");
        }

        List<ConfigPoids> liste = new ArrayList<>();
        int nb = (int) Math.floor((poidsMax - poidsMin) / pas) + 1;

        for (int i = 0; i < nb; i++) {
            ConfigPoids cp = new ConfigPoids();
            double valeur = poidsMin + (i * pas);
            cp.setId(String.valueOf(i + 1));
            cp.setPoidsmin(valeur);
            cp.setPoidsmax(poidsMax);
            cp.setPas(pas);
            liste.add(cp);
        }

        return liste.toArray(new ConfigPoids[0]);
    }
}

