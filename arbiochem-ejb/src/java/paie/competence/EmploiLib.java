package paie.competence;

public class EmploiLib extends Emploi{
    private String idMetierLib,idCodeRomeLib,idFamilleProLib,idSousFamilleProLib;
    public EmploiLib(){
        super.setNomTable("EMPLOI_LIB");
    }
    public String getIdMetierLib() {
        return idMetierLib;
    }

    public void setIdMetierLib(String idMetierLib) {
        this.idMetierLib = idMetierLib;
    }

    public String getIdCodeRomeLib() {
        return idCodeRomeLib;
    }

    public void setIdCodeRomeLib(String idCodeRomeLib) {
        this.idCodeRomeLib = idCodeRomeLib;
    }

    public String getIdFamilleProLib() {
        return idFamilleProLib;
    }

    public void setIdFamilleProLib(String idFamilleProLib) {
        this.idFamilleProLib = idFamilleProLib;
    }

    public String getIdSousFamilleProLib() {
        return idSousFamilleProLib;
    }

    public void setIdSousFamilleProLib(String idSousFamilleProLib) {
        this.idSousFamilleProLib = idSousFamilleProLib;
    }
}
