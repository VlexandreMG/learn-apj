package ferme.production;

public class triageOeufLib extends TriageOeuf{
    String idOperateurLib, idNumeroCollecteLib, idBatimentLib, idParquetLib, etatLib;
    double ecart;


    public triageOeufLib() throws Exception {
        this.setNomTable("TRIAGEOEUF_LIB");
    }

    public double getEcart() {
        return ecart;
    }

    public void setEcart(double ecart) {
        this.ecart = ecart;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdOperateurLib() {
        return idOperateurLib;
    }

    public void setIdOperateurLib(String idOperateurLib) {
        this.idOperateurLib = idOperateurLib;
    }

    public String getIdNumeroCollecteLib() {
        return idNumeroCollecteLib;
    }

    public void setIdNumeroCollecteLib(String idNumeroCollecteLib) {
        this.idNumeroCollecteLib = idNumeroCollecteLib;
    }

    public String getIdBatimentLib() {
        return idBatimentLib;
    }

    public void setIdBatimentLib(String idBatimentLib) {
        this.idBatimentLib = idBatimentLib;
    }

    public String getIdParquetLib() {
        return idParquetLib;
    }

    public void setIdParquetLib(String idParquetLib) {
        this.idParquetLib = idParquetLib;
    }
}
