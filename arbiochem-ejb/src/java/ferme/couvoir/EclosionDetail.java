package ferme.couvoir;

import bean.ClassFille;
import java.sql.Connection;

public class EclosionDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idqualiteeclosion;
    private double qte;
    private String remarque;

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

    public String getIdqualiteeclosion() {
        return idqualiteeclosion;
    }

    public void setIdqualiteeclosion(String idqualiteeclosion) {
        this.idqualiteeclosion = idqualiteeclosion;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.couvoir.Eclosion";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public EclosionDetail() throws Exception {
        this.setNomTable("ECLOSIONDETAIL");
        this.setNomClasseMere("ferme.couvoir.Eclosion");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ECLD","getseq_eclosiondetail");
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

