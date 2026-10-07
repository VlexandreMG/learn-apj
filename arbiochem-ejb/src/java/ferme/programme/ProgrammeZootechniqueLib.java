package ferme.programme;

public class ProgrammeZootechniqueLib extends ProgrammeZootechnique{
    String idSoucheLib,etatLib;
    public ProgrammeZootechniqueLib() throws Exception {
        this.setNomTable("PROGRAMMEZOOTECHNIQUE_LIB");
    }

    public String getIdSoucheLib() {
        return idSoucheLib;
    }

    public void setIdSoucheLib(String idSoucheLib) {
        this.idSoucheLib = idSoucheLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
