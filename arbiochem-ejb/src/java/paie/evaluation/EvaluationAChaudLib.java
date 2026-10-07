package paie.evaluation;

public class EvaluationAChaudLib extends EvaluationAChaud{
    private String idFormationLib,idPersonnelLib,matriculePersonnel,idFonction,idFonctionLib,idIntervenantLib,idFonctionIntervenant,idFonctionIntervenantLib;
    public EvaluationAChaudLib()throws Exception{
        super.setNomTable("EVALUATION_A_CHAUD_LIB");
    }

    public String getIdFormationLib() {
        return idFormationLib;
    }

    public void setIdFormationLib(String idFormationLib) {
        this.idFormationLib = idFormationLib;
    }

    public String getIdPersonnelLib() {
        return idPersonnelLib;
    }

    public void setIdPersonnelLib(String idPersonnelLib) {
        this.idPersonnelLib = idPersonnelLib;
    }

    public String getMatriculePersonnel() {
        return matriculePersonnel;
    }

    public void setMatriculePersonnel(String matriculePersonnel) {
        this.matriculePersonnel = matriculePersonnel;
    }

    public String getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(String idFonction) {
        this.idFonction = idFonction;
    }

    public String getIdFonctionLib() {
        return idFonctionLib;
    }

    public void setIdFonctionLib(String idFonctionLib) {
        this.idFonctionLib = idFonctionLib;
    }

    public String getIdIntervenantLib() {
        return idIntervenantLib;
    }

    public void setIdIntervenantLib(String idIntervenantLib) {
        this.idIntervenantLib = idIntervenantLib;
    }

    public String getIdFonctionIntervenant() {
        return idFonctionIntervenant;
    }

    public void setIdFonctionIntervenant(String idFonctionIntervenant) {
        this.idFonctionIntervenant = idFonctionIntervenant;
    }

    public String getIdFonctionIntervenantLib() {
        return idFonctionIntervenantLib;
    }

    public void setIdFonctionIntervenantLib(String idFonctionIntervenantLib) {
        this.idFonctionIntervenantLib = idFonctionIntervenantLib;
    }
}
