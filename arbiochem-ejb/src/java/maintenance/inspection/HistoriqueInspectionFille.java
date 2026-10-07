package maintenance.inspection;
import bean.ClassFille;

import java.sql.Connection;

public class HistoriqueInspectionFille extends  ClassFille{
    private String id,idMere,idElement,etatInspection,remarque;
    public HistoriqueInspectionFille() throws Exception{
        this.setNomClasseMere("maintenance.inspection.HistoriqueInspectionMere");
        this.setNomTable("HistoriqueInspectionFille");
        this.setLiaisonMere("idMere");
    }
    public void construirePK(Connection c) throws Exception {
        this.preparePk("HIF", "getSeqHistoriqueInspectionF");
        this.setId(makePK(c));
    }

    @Override
    public String getNomClasseMere() {
        return "maintenance.inspection.HistoriqueInspectionMere";
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMere() {
        return idMere;
    }

    public void setIdMere(String idMere) {
        this.idMere = idMere;
    }

    public String getIdElement() {
        return idElement;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }

    public String getEtatInspection() {
        return etatInspection;
    }

    public void setEtatInspection(String etatInspection) {
        this.etatInspection = etatInspection;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
