package previsionFerme;

public class PrevisionFermeLib extends PrevisionFerme{
    private String idMagasinLib;
    private String idTiersLib;
    private String idProduitLib;
    private double sortieEffective;
    private double entreeEffective;
    private double ecartEntree;
    private double ecartSortie;

    public double getSortieEffective() {
        return sortieEffective;
    }

    public void setSortieEffective(double sortieEffective) {
        this.sortieEffective = sortieEffective;
    }

    public double getEntreeEffective() {
        return entreeEffective;
    }

    public void setEntreeEffective(double entreeEffective) {
        this.entreeEffective = entreeEffective;
    }

    public double getEcartEntree() {
        return ecartEntree;
    }

    public void setEcartEntree(double ecartEntree) {
        this.ecartEntree = ecartEntree;
    }

    public double getEcartSortie() {
        return ecartSortie;
    }

    public void setEcartSortie(double ecartSortie) {
        this.ecartSortie = ecartSortie;
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public String getIdTiersLib() {
        return idTiersLib;
    }

    public void setIdTiersLib(String idTiersLib) {
        this.idTiersLib = idTiersLib;
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }

    public PrevisionFermeLib() {
        this.setNomTable("PREVISIONFERMELIB");
    }
}
