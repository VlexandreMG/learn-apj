package ferme.batiment;

public class BatimentVideSanitaireLib extends BatimentVideSanitaire{
    private String idBatimentLib, idFerme, idFermeLib, idResponsableLib, etatLib;

    public BatimentVideSanitaireLib() throws Exception {
        this.setNomTable("BATIMENTVIDESANITAIRE_LIB");
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdBatimentLib() {
        return idBatimentLib;
    }

    public void setIdBatimentLib(String idBatimentLib) {
        this.idBatimentLib = idBatimentLib;
    }

    public String getIdFerme() {
        return idFerme;
    }

    public void setIdFerme(String idFerme) {
        this.idFerme = idFerme;
    }

    public String getIdFermeLib() {
        return idFermeLib;
    }

    public void setIdFermeLib(String idFermeLib) {
        this.idFermeLib = idFermeLib;
    }

    public String getIdResponsableLib() {
        return idResponsableLib;
    }

    public void setIdResponsableLib(String idResponsableLib) {
        this.idResponsableLib = idResponsableLib;
    }
}
