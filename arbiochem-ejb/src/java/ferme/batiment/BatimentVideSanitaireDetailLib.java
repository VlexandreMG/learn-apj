package ferme.batiment;

public class BatimentVideSanitaireDetailLib extends BatimentVideSanitaireDetail{
    private String idProduitLib, idUnite, idUniteLib;
    private double pu;

    public BatimentVideSanitaireDetailLib() throws Exception {
        this.setNomTable("BATIMENTVIDESANITAIREDETAILLIB");
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public String getIdUniteLib() {
        return idUniteLib;
    }

    public void setIdUniteLib(String idUniteLib) {
        this.idUniteLib = idUniteLib;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }
}
