package paie.poste;

import bean.ClassMAPTable;

import java.sql.Connection;

public class ObjectifIndividuel extends ClassMAPTable {
    private String id;
    private String idFichePoste;
    private String objectif;
    private String kpi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFichePoste() {
        return idFichePoste;
    }

    public void setIdFichePoste(String idFichePoste) {
        this.idFichePoste = idFichePoste;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }



    public ObjectifIndividuel() throws Exception {
        this.setNomTable("FP_OBJECTIF_INDIVIDUEL");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OBJ","GET_SEQ_OBJECTIFINDV");
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

