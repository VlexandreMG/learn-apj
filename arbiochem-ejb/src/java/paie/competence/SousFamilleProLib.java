package paie.competence;

public class SousFamilleProLib extends SousFamillePro{
    private String idFamilleLib;
    public SousFamilleProLib() throws Exception {
        super.setNomTable("sous_famille_pro_lib");
    }

    public String getIdFamilleLib() {
        return idFamilleLib;
    }

    public void setIdFamilleLib(String idFamilleLib) {
        this.idFamilleLib = idFamilleLib;
    }
}
