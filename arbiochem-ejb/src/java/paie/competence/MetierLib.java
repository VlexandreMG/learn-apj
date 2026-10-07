package paie.competence;

public class MetierLib extends Metier{
    private String idCodeRomeLib,idFamilleProLib,idSousFamilleProLib,idFonctionLib;

    public MetierLib() throws Exception {
        super.setNomTable("metierlib");
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

    public String getIdFonctionLib() {
        return idFonctionLib;
    }

    public void setIdFonctionLib(String idFonctionLib) {
        this.idFonctionLib = idFonctionLib;
    }

}
