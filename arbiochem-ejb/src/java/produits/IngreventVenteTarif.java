package produits;

public class IngreventVenteTarif extends  IngredientVente {
    private String tarifMagasin;

    public String getTarifMagasin() {
        return tarifMagasin;
    }

    public void setTarifMagasin(String tarifMagasin) {
        this.tarifMagasin = tarifMagasin;
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","libelle","unite2"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"libelle","unite2"};
        return motCles;
    }
}
