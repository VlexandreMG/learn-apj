package maintenance.ressources;

import bean.ClassFille;

import java.sql.Connection;

public class ConsommationDetails extends ClassFille {
    private String id;
    private String idMere;
    private String idIngredients;
    private double qte;
    private String idUnite;

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

    public String getIdIngredients() {
        return idIngredients;
    }

    public void setIdIngredients(String idIngredients) {
        this.idIngredients = idIngredients;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    @Override
    public String getNomClasseMere() {
        return "maintenance.ressources.Consommation";
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

    public ConsommationDetails() throws Exception {
        this.setNomTable("CONSOMMATIONDETAILS");
        this.setNomClasseMere("maintenance.ressources.Consommation");
        this.setLiaisonMere("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CONSF","GETSEQCONSOMMATIONDETAILS");
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

