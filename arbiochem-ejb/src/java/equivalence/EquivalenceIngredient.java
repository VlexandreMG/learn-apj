package equivalence ;

import bean.ClassMAPTable;
import java.sql.Connection;

public class EquivalenceIngredient extends ClassMAPTable {
    private String id;
    private String idproduit;
    private String idunite;
    private double qte;
    private double poids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
    }

    public String getIdunite() {
        return idunite;
    }

    public void setIdunite(String idunite) {
        this.idunite = idunite;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }



    public EquivalenceIngredient() throws Exception {
        this.setNomTable("EQUIVALENCE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EUI","get_seq_equivalence");
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

