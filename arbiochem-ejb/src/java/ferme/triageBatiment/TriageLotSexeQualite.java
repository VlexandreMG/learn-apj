package ferme.triageBatiment;

import bean.ClassMAPTable;

/**
 *
 * @author Safidy
 */
public class TriageLotSexeQualite extends TriageBatimentParquetDetail {
    private String idLot;
    public TriageLotSexeQualite() throws Exception {
        this.setNomTable("v_qte_dispo_lot_sexe_qualite");
    }

    public String getIdLot() {
        return idLot;
    }

    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }
}