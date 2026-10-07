package ferme.configuration;

import java.sql.Connection;
import bean.ClassMere;
import bean.ClassMAPTable;

/**
 *
 * @author Safidy
 */
public class Batiment extends ClassMere {
    private String id;
    private String idFerme;
    private String nomBatiment;
    private double surface;
    private double capacite;
    private double densite;
    private double longueur;
    private double largeur;

    public Batiment()throws Exception {
        super.setNomTable("BATIMENT");
        this.setNomClasseFille("ferme.configuration.BatimentDetail");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BAT", "GETSEQBATIMENT");
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
    public String getNomClasseFille() {
        return "ferme.configuration.BatimentDetail";
    }

    @Override
    public String getLiaisonFille() {
        return "idMere";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFerme() {
        return idFerme;
    }

    public void setIdFerme(String idFerme) {
        this.idFerme = idFerme;
    }

    public String getNomBatiment() {
        return nomBatiment;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getCapacite() {
        return capacite;
    }

    public void setCapacite(Double capacite) {
        this.capacite = capacite;
    }

    public Double getDensite() {
        return densite;
    }

    public void setDensite(Double densite) {
        this.densite = densite;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","nomBatiment"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"id","nomBatiment"};
        return valMotCles;
    }
    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        this.checkValeurEntierFille();
        return super.createObject(u, c);
    }

    public void checkValeurEntierFille()throws Exception{
        BatimentDetail [] fille = (BatimentDetail[]) this.getFille();
        double sommeCapaciteFille = 0;
        double sommeDensiteFille = 0;
        double sommeSurfaceFille = 0;
        for(BatimentDetail f : fille){
            sommeCapaciteFille+=f.getCapacite();
            sommeDensiteFille+=f.getDensite();
            sommeSurfaceFille+=f.getSurface();
        }
        if (this.getCapacite() < sommeCapaciteFille)throw new Exception("La capacit\u00e9 totale   (" + sommeCapaciteFille+ ") d\u00e9passe la capacit\u00e9 du batiment (" + this.getCapacite() + ")");
        if (this.getDensite() < sommeDensiteFille) throw new Exception("La densit\u00e9 totale  (" + sommeDensiteFille+ ") d\u00e9passe la densit\u00e9 du batiment (" + this.getDensite() + ")");
        if (this.getSurface() < sommeSurfaceFille) throw new Exception("La surface totale  (" + sommeSurfaceFille+ ") d\u00e9passe la surface du batiment (" + this.getSurface() + ")");
    }
}