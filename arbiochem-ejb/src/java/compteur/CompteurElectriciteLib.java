package compteur;

public class CompteurElectriciteLib extends  CompteurElectricite{

    private String idmachinelib;

    private String IdCategorieLib;

    private double montant, pu;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }
    public String getIdCategorieLib() {
            return IdCategorieLib;
        }

    public void setIdCategorieLib(String idCategorieLib) {
        IdCategorieLib = idCategorieLib;
    }

    public String getIdmachinelib() {
        return idmachinelib;
    }

    public void setIdmachinelib(String idmachinelib) {
        this.idmachinelib = idmachinelib;
    }

    public CompteurElectriciteLib() throws Exception {
        this.setNomTable("COMPTEURELECTRICITELIB");
    }
}
