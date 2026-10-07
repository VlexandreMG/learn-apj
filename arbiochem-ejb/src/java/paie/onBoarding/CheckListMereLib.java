package paie.onBoarding;

public class CheckListMereLib extends CheckListMere {

    private String typeCheckListeLib;
    private String idPersonnelLib;
    private String matricule;
    private String idFonction;
    private String idFonctionLib;
    private String idService;
    private String idServiceLib;
    private String idDirection;
    private String idDirectionLib;

    public CheckListMereLib() throws Exception {
        this.setNomTable("CHECKLIST_MERE_LIB");
    }

    public CheckListMereLib(String nomtable) throws Exception {
        this.setNomTable(nomtable);
    }

    public String getTypeCheckListeLib() {
        return typeCheckListeLib;
    }

    public void setTypeCheckListeLib(String typeCheckListeLib) {
        this.typeCheckListeLib = typeCheckListeLib;
    }

    public String getIdPersonnelLib() {
        return idPersonnelLib;
    }

    public void setIdPersonnelLib(String idPersonnelLib) {
        this.idPersonnelLib = idPersonnelLib;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdServiceLib() {
        return idServiceLib;
    }

    public void setIdServiceLib(String idServiceLib) {
        this.idServiceLib = idServiceLib;
    }

    public String getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(String idDirection) {
        this.idDirection = idDirection;
    }

    public String getIdDirectionLib() {
        return idDirectionLib;
    }

    public void setIdDirectionLib(String idDirectionLib) {
        this.idDirectionLib = idDirectionLib;
    }
}