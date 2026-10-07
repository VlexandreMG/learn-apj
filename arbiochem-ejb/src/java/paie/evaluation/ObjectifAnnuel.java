package paie.evaluation;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class ObjectifAnnuel extends ClassMere {
    private String id;
    private String idPersonnel;
    private String titre;
    private Date daty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }



    public ObjectifAnnuel() throws Exception {
        this.setNomTable("OBJECTIF_ANNUEL");
        this.setNomClasseFille("paie.evaluation.ObjectifAnnuelDetail");
        this.setLiaisonFille("idObjectifannuel");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OBJ","GET_SEQ_OBJECTIF_ANNUEL");
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

