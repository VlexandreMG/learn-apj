package ferme.production;

import bean.ClassFille;
import java.sql.Connection;

public class ReceptionOACDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idlot;
    private String idbatiment;
    private String idparquet;
    private String idqualitetriageoeuf;
    private double qterecus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }

    public String getIdbatiment() {
        return idbatiment;
    }

    public void setIdbatiment(String idbatiment) {
        this.idbatiment = idbatiment;
    }

    public String getIdparquet() {
        return idparquet;
    }

    public void setIdparquet(String idparquet) {
        this.idparquet = idparquet;
    }

    public String getIdqualitetriageoeuf() {
        return idqualitetriageoeuf;
    }

    public void setIdqualitetriageoeuf(String idqualitetriageoeuf) {
        this.idqualitetriageoeuf = idqualitetriageoeuf;
    }

    public double getQterecus() {
        return qterecus;
    }

    public void setQterecus(double qterecus) {
        this.qterecus = qterecus;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.production.ReceptionOAC";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public ReceptionOACDetail() throws Exception {
        this.setNomTable("RECEPTIONOACDETAIL");
        this.setNomClasseMere("ferme.production.ReceptionOAC");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RPOD","getseq_receptionoacdetail");
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

