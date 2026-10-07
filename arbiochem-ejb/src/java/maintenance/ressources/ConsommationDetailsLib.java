package maintenance.ressources;

public class ConsommationDetailsLib extends ConsommationDetails{
    private String idUniteLib;
    private String idIngredientsLib;

    public ConsommationDetailsLib() throws Exception {
        this.setNomTable("CONSOMMATIONDETAILSLIB");
    }

    public String getIdUniteLib() {
        return idUniteLib;
    }

    public void setIdUniteLib(String idUniteLib) {
        this.idUniteLib = idUniteLib;
    }

    public String getIdIngredientsLib() {
        return idIngredientsLib;
    }

    public void setIdIngredientsLib(String idIngredientsLib) {
        this.idIngredientsLib = idIngredientsLib;
    }
}
