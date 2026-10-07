package faturefournisseur;

import bean.ClassMAPTable;
import java.sql.Connection;
import java.sql.Date;

public class PlanCommande extends ClassMAPTable {
    private String id;
    private String iddmdachat;
    private String idProduit;
    private String designation;
    private double quantite;
    private Date daty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIddmdachat() {
        return iddmdachat;
    }

    public void setIddmdachat(String iddmdachat) {
        this.iddmdachat = iddmdachat;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public PlanCommande() throws Exception {
        this.setNomTable("PLANCOMMANDE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PCM","GETSEQ_PLANCOMMANDE");
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

