package entretien;

public class EntretienRHDetailsLib extends EntretienRHDetails{
    private String idCategorieEvaluationLib;
    public EntretienRHDetailsLib() throws Exception {
        this.setNomTable("EntretienRH_Detail_lib");
    }

    public String getIdCategorieEvaluationLib() {
        return idCategorieEvaluationLib;
    }

    public void setIdCategorieEvaluationLib(String idCategorieEvaluationLib) {
        this.idCategorieEvaluationLib = idCategorieEvaluationLib;
    }
}
