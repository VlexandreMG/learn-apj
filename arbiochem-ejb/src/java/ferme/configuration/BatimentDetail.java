package ferme.configuration;

import java.sql.Connection;
import bean.ClassFille;

/**
 *
 * @author Safidy
 */
public class BatimentDetail extends ClassFille {
    private String id;
    private String idMere;
    private String idParquet;
    private double surface;
    private double capacite;
    private String idLot;
    private String idSexe;
    private double effectif;
    private double densite;

    public BatimentDetail()throws Exception {
        super.setNomTable("BATIMENTDETAIL");
        this.setNomClasseMere("ferme.configuration.Batiment");
        this.setLiaisonMere("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BATF", "GETSEQBATIMENTDETAIL");
        this.setId(this.makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public String getNomClasseMere() {
        return "ferme.configuration.Batiment";
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMere() {
        return idMere;
    }

    public void setIdMere(String idMere) {
        this.idMere = idMere;
    }

    public String getIdParquet() {
        return idParquet;
    }

    public void setIdParquet(String idParquet) {
        this.idParquet = idParquet;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    public String getIdLot() {
        return idLot;
    }

    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }

    public String getIdSexe() {
        return idSexe;
    }

    public void setIdSexe(String idSexe) {
        this.idSexe = idSexe;
    }

    public double getEffectif() {
        return effectif;
    }

    public void setEffectif(double effectif) {
        this.effectif = effectif;
    }

    public double getDensite() {
        return densite;
    }

    public void setDensite(double densite) {
        this.densite = densite;
    }
}
