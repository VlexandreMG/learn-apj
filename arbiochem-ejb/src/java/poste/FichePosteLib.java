package poste;

public class FichePosteLib extends FichePoste{
    String idDepartementLib;
    String idTypeContratLib;
    String etatLib;
    String idNiveauFormationLib;
    String idPermisLib;
    String idFonctionlib;
    private String idBuLib;
    private String idCategoriePaieLib;
    private String idQualificationPaieLib;
    String idFamilleProfessionelLib , idMetierLib , idEmploiLib ,idCodeRomeLib;
    public FichePosteLib() throws Exception {
        this.setNomTable("FICHE_POSTE_LIB");
    }

    public String getIdFamilleProfessionelLib() {
        return idFamilleProfessionelLib;
    }

    public void setIdFamilleProfessionelLib(String idFamilleProfessionelLib) {
        this.idFamilleProfessionelLib = idFamilleProfessionelLib;
    }

    public String getIdMetierLib() {
        return idMetierLib;
    }

    public void setIdMetierLib(String idMetierLib) {
        this.idMetierLib = idMetierLib;
    }

    public String getIdEmploiLib() {
        return idEmploiLib;
    }

    public void setIdEmploiLib(String idEmploiLib) {
        this.idEmploiLib = idEmploiLib;
    }

    public String getIdCodeRomeLib() {
        return idCodeRomeLib;
    }

    public void setIdCodeRomeLib(String idCodeRomeLib) {
        this.idCodeRomeLib = idCodeRomeLib;
    }

    public String getIdDepartementLib() {
        return idDepartementLib;
    }

    public void setIdDepartementLib(String idDepartementLib) {
        this.idDepartementLib = idDepartementLib;
    }

    public String getIdTypeContratLib() {
        return idTypeContratLib;
    }

    public void setIdTypeContratLib(String idTypeContratLib) {
        this.idTypeContratLib = idTypeContratLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdNiveauFormationLib() {
        return idNiveauFormationLib;
    }

    public void setIdNiveauFormationLib(String idNiveauFormationLib) {
        this.idNiveauFormationLib = idNiveauFormationLib;
    }

    public String getIdPermisLib() {
        return idPermisLib;
    }

    public void setIdPermisLib(String idPermisLib) {
        this.idPermisLib = idPermisLib;
    }

    public String getIdFonctionlib() {
        return idFonctionlib;
    }

    public void setIdFonctionlib(String idFonctionlib) {
        this.idFonctionlib = idFonctionlib;
    }

    public String getIdBuLib() {
        return idBuLib;
    }

    public void setIdBuLib(String idBuLib) {
        this.idBuLib = idBuLib;
    }

    public String getIdCategoriePaieLib() {
        return idCategoriePaieLib;
    }

    public void setIdCategoriePaieLib(String idCategoriePaieLib) {
        this.idCategoriePaieLib = idCategoriePaieLib;
    }

    public String getIdQualificationPaieLib() {
        return idQualificationPaieLib;
    }

    public void setIdQualificationPaieLib(String idQualificationPaieLib) {
        this.idQualificationPaieLib = idQualificationPaieLib;
    }
}
