package ferme.production;

import bean.ClassFille;
import java.sql.Connection;

public class TriageOeufDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idtypecollecte;
    private String idqualitetriageoeuf;
    private double qte;
    private String iddestination;

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

    public String getIdtypecollecte() {
        return idtypecollecte;
    }

    public void setIdtypecollecte(String idtypecollecte) {
        this.idtypecollecte = idtypecollecte;
    }

    public String getIdqualitetriageoeuf() {
        return idqualitetriageoeuf;
    }

    public void setIdqualitetriageoeuf(String idqualitetriageoeuf) {
        this.idqualitetriageoeuf = idqualitetriageoeuf;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String getIddestination() {
        return iddestination;
    }

    public void setIddestination(String iddestination) {
        this.iddestination = iddestination;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.production.TriageOeuf";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public TriageOeufDetail() throws Exception {
        this.setNomTable("TRIAGEOEUFDETAIL");
        this.setNomClasseMere("ferme.production.TriageOeuf");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TROD","getseq_triageoeufdetail");
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

