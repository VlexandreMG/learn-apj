package ferme.production;

import bean.ClassFille;
import java.sql.Connection;

public class MirageDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idbatiment;
    private String idparquet;
    private String idqualitemirage;
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

    public String getIdqualitemirage() {
        return idqualitemirage;
    }

    public void setIdqualitemirage(String idqualitemirage) {
        this.idqualitemirage = idqualitemirage;
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
        return "ferme.production.Mirage";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public MirageDetail() throws Exception {
        this.setNomTable("MIRAGEDETAIL");
        this.setNomClasseMere("ferme.production.Mirage");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MRGD","getseq_miragedetail");
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

