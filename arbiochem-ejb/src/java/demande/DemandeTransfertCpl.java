package demande;
public class DemandeTransfertCpl extends DemandeTransfert {
    private String idMagasinDepartLib,idMagasinArriveLib,etatlib,categorieIngredientLib;
    private double montantQuantite;

    public double getMontantQuantite() {
        return montantQuantite;
    }

    public void setMontantQuantite(double montantQuantite) {
        this.montantQuantite = montantQuantite;
    }

    public DemandeTransfertCpl() throws Exception{
        this.setNomTable("demandetransfertcpl");
    }

    public String getCategorieIngredientLib() {
        return categorieIngredientLib;
    }

    public void setCategorieIngredientLib(String categorieIngredientLib) {
        this.categorieIngredientLib = categorieIngredientLib;
    }

    public String getIdMagasinDepartLib() {
        return idMagasinDepartLib;
    }

    public void setIdMagasinDepartLib(String idMagasinDepartLib) {
        this.idMagasinDepartLib = idMagasinDepartLib;
    }

    public String getIdMagasinArriveLib() {
        return idMagasinArriveLib;
    }

    public void setIdMagasinArriveLib(String idMagasinArriveLib) {
        this.idMagasinArriveLib = idMagasinArriveLib;
    }

    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
}