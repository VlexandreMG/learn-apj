package ferme.suiviJournalier;

public class SuiviJournalieLib extends SuiviJournalier{
    String idFermeLib, idLotLib,idSoucheLib, etatLib;
    public SuiviJournalieLib() throws Exception {
        this.setNomTable("SUIVIJOURNALIER_LIB");
    }

    public String getIdFermeLib() {
        return idFermeLib;
    }

    public void setIdFermeLib(String idFermeLib) {
        this.idFermeLib = idFermeLib;
    }

    public String getIdLotLib() {
        return idLotLib;
    }

    public void setIdLotLib(String idLotLib) {
        this.idLotLib = idLotLib;
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
