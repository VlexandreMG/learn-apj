package ferme.configuration;

import bean.TypeObjet;

public class ParquetBatiment extends TypeObjet {
    String idBatiment;
    public ParquetBatiment(){
        this.setNomTable("PARQUET_BATIMENT_LIB");
    }

    public String getIdBatiment() {
        return idBatiment;
    }

    public void setIdBatiment(String idBatiment) {
        this.idBatiment = idBatiment;
    }
}
