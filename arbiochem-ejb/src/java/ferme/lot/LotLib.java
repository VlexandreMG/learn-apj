package ferme.lot;

public class LotLib extends Lot{
    String idFermeLib, idArticleLib, idOrigineLib, idProgrammeLib, etatLib,idSoucheLib,idCategorieLotLib;
    int ageJour, ageSemaine;

    public LotLib() throws Exception {
        this.setNomTable("LOT_LIB");
    }

    public int getAgeJour() {
        return ageJour;
    }

    public void setAgeJour(int ageJour) {
        this.ageJour = ageJour;
    }

    public int getAgeSemaine() {
        return ageSemaine;
    }

    public void setAgeSemaine(int ageSemaine) {
        this.ageSemaine = ageSemaine;
    }

    public String getIdSoucheLib() {
        return idSoucheLib;
    }

    public void setIdSoucheLib(String idSoucheLib) {
        this.idSoucheLib = idSoucheLib;
    }

    public String getIdFermeLib() {
        return idFermeLib;
    }

    public void setIdFermeLib(String idFermeLib) {
        this.idFermeLib = idFermeLib;
    }

    public String getIdArticleLib() {
        return idArticleLib;
    }

    public void setIdArticleLib(String idArticleLib) {
        this.idArticleLib = idArticleLib;
    }

    public String getIdOrigineLib() {
        return idOrigineLib;
    }

    public void setIdOrigineLib(String idOrigineLib) {
        this.idOrigineLib = idOrigineLib;
    }

    public String getIdProgrammeLib() {
        return idProgrammeLib;
    }

    public void setIdProgrammeLib(String idProgrammeLib) {
        this.idProgrammeLib = idProgrammeLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdCategorieLotLib() {
        return idCategorieLotLib;
    }

    public void setIdCategorieLotLib(String idCategorieLotLib) {
        this.idCategorieLotLib = idCategorieLotLib;
    }
}
