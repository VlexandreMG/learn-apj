package paie.competence;

public class CodeRomeLib extends CodeRome{
    private String idSousFamilleLib;
    public CodeRomeLib(){
        super.setNomTable("code_rome_lib");
    }

    public String getIdSousFamilleLib() {
        return idSousFamilleLib;
    }

    public void setIdSousFamilleLib(String idSousFamilleLib) {
        this.idSousFamilleLib = idSousFamilleLib;
    }
}
