package faturefournisseur;

import bean.ClassMAPTable;
import java.sql.Connection;

public class ComparaisonPrixBCFact extends ClassMAPTable {
    private String id;
    private String idProduit;
    private String idProduitLib;
    private String idFactureFournisseur;
    private double pufact;
    private double pubc;
    private int ecart;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }

    public String getIdFactureFournisseur() {
        return idFactureFournisseur;
    }

    public void setIdFactureFournisseur(String idFactureFournisseur) {
        this.idFactureFournisseur = idFactureFournisseur;
    }

    public double getPufact() {
        return pufact;
    }

    public void setPufact(double pufact) {
        this.pufact = pufact;
    }

    public double getPubc() {
        return pubc;
    }

    public void setPubc(double pubc) {
        this.pubc = pubc;
    }

    public int getEcart() {
        return ecart;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }



    public ComparaisonPrixBCFact() throws Exception {
        this.setNomTable("COMPARAISONPRIXFACTBC");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CPFBC","getSeqComparaisonPrixBCFact");
    }

    @Override
    public String getTuppleID() {
        return this.id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}

