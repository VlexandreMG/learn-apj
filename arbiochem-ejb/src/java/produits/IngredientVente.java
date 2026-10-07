package produits;

public class IngredientVente extends IngredientsLib{
    private String idTypeClient;
    private String idTypeClientLib;
    private String idUniteLib;
    private double poids;
    private double qte;
    private double prixUnitaire;

    public IngredientVente() {
        setNomTable("AS_INGREDIENT_VENTE_LIB");
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","libelle"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"libelle"};
        return motCles;
    }

    public String getIdTypeClient() {
        return idTypeClient;
    }

    public void setIdTypeClient(String idTypeClient) {
        this.idTypeClient = idTypeClient;
    }

    public String getIdTypeClientLib() {
        return idTypeClientLib;
    }

    public void setIdTypeClientLib(String idTypeClientLib) {
        this.idTypeClientLib = idTypeClientLib;
    }

    public String getIdUniteLib() {
        return idUniteLib;
    }

    public void setIdUniteLib(String idUniteLib) {
        this.idUniteLib = idUniteLib;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
