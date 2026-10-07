package caisse;

public class MouvementCaisseFilleCpl extends MouvementCaisseFille{
    String idCaisseLib;

    public String getIdCaisseLib() {
        return idCaisseLib;
    }

    public void setIdCaisseLib(String idCaisseLib) {
        this.idCaisseLib = idCaisseLib;
    }

    public MouvementCaisseFilleCpl() throws Exception {
        this.setNomTable("MouvementCaisseFilleCpl");
    }
}
