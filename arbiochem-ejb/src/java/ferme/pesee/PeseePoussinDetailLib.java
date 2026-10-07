package ferme.pesee;

public class PeseePoussinDetailLib extends PeseePoussinDetail{

    private double poidsnombre;
    private String dansplageuniformite;
    private double pourcentageoiseaux;

    public PeseePoussinDetailLib() throws Exception {
        this.setNomTable("PESEEPOUSSINDETAIL_LIB");
    }

    public double getPoidsnombre() {
        return poidsnombre;
    }

    public void setPoidsnombre(double poidsnombre) {
        this.poidsnombre = poidsnombre;
    }

    public String getDansplageuniformite() {
        return dansplageuniformite;
    }

    public void setDansplageuniformite(String dansplageuniformite) {
        this.dansplageuniformite = dansplageuniformite;
    }

    public double getPourcentageoiseaux() {
        return pourcentageoiseaux;
    }

    public void setPourcentageoiseaux(double pourcentageoiseaux) {
        this.pourcentageoiseaux = pourcentageoiseaux;
    }
}