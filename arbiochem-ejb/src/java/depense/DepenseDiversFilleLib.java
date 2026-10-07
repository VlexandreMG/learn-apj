package depense;

public class DepenseDiversFilleLib extends DepenseDiversFille{
    private String idProduitLib;
    private double montant;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public DepenseDiversFilleLib() throws Exception {
        this.setNomTable("DEPENSEDIVERSFILLELIB");
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }
    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }
}
