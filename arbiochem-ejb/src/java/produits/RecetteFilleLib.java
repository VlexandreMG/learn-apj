package produits;

public class RecetteFilleLib extends RecetteFille{

    private String idIngredientLib;
    private String idUniteLib;

    public String getIdIngredientLib() {
        return idIngredientLib;
    }

    public void setIdIngredientLib(String idIngredientLib) {
        this.idIngredientLib = idIngredientLib;
    }

    public String getIdUniteLib() {
        return idUniteLib;
    }

    public void setIdUniteLib(String idUniteLib) {
        this.idUniteLib = idUniteLib;
    }

    public RecetteFilleLib() throws Exception {
        this.setNomTable("RECETTE_FILLE_LIB");
    }
}
