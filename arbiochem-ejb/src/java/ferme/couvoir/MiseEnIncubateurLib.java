package ferme.couvoir;

public class MiseEnIncubateurLib extends MiseEnIncubateur{
    String idIncubateurLib, idResponsableLib;

    public MiseEnIncubateurLib() throws Exception {
        this.setNomTable("MISEENINCUBATEUR_LIB");
    }

    public String getIdIncubateurLib() {
        return idIncubateurLib;
    }

    public void setIdIncubateurLib(String idIncubateurLib) {
        this.idIncubateurLib = idIncubateurLib;
    }

    public String getIdResponsableLib() {
        return idResponsableLib;
    }

    public void setIdResponsableLib(String idResponsableLib) {
        this.idResponsableLib = idResponsableLib;
    }
}
