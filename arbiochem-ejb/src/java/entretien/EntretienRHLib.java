package entretien;

public class EntretienRHLib extends EntretienRH{
    private String typeEntretientLib, idFonctionLib, idDirectionLib,idEvaluateurLib;
    public EntretienRHLib() throws Exception {
        this.setNomTable("EntretienRH_lib");
    }

    public String getTypeEntretientLib() {
        return typeEntretientLib;
    }

    public void setTypeEntretientLib(String typeEntretientLib) {
        this.typeEntretientLib = typeEntretientLib;
    }

    public String getIdFonctionLib() {
        return idFonctionLib;
    }

    public void setIdFonctionLib(String idFonctionLib) {
        this.idFonctionLib = idFonctionLib;
    }

    public String getIdDirectionLib() {
        return idDirectionLib;
    }

    public void setIdDirectionLib(String idDirectionLib) {
        this.idDirectionLib = idDirectionLib;
    }

    public String getIdEvaluateurLib() {
        return idEvaluateurLib;
    }

    public void setIdEvaluateurLib(String idEvaluateurLib) {
        this.idEvaluateurLib = idEvaluateurLib;
    }
}
