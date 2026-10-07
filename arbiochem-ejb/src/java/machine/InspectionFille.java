package machine;

import bean.ClassFille;
import java.sql.Connection;

public class InspectionFille extends ClassFille {
    private String id;
    private String idMere;
    private String idElement;
    private String idEtatInspection;
    private String remarque;

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

    public String getIdEtatInspection() {
        return idEtatInspection;
    }

    public void setIdEtatInspection(String idEtatInspection) {
        this.idEtatInspection = idEtatInspection;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    @Override
    public String getNomClasseMere() {
        return "machine.InspectionMere";
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

    public InspectionFille() throws Exception {
        this.setNomTable("INSPECTIONFILLE");
        this.setNomClasseMere("machine.InspectionMere");
        this.setLiaisonMere("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ISPF","getSeqInspectionFille");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}

