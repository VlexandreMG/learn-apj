package ferme.programme;

public class ProgrammeZootechniqueDetailsLib extends ProgrammeZootechniqueDetails{
    String idSexeLib;
    public ProgrammeZootechniqueDetailsLib() throws Exception {
        this.setNomTable("PROGRAMMEZOOTECHNIQUEDT_LIB");
    }

    public String getIdSexeLib() {
        return idSexeLib;
    }

    public void setIdSexeLib(String idSexeLib) {
        this.idSexeLib = idSexeLib;
    }
}
