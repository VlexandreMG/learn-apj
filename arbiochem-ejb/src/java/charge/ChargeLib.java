package charge;

public class ChargeLib extends Charge{
    private String idOf;
    private String idOffille;
    private String typechargelib;
    private String idingredientslib;
    double montant;

    public ChargeLib() {
        this.setNomTable("charge_lib");
    }
    public String getIdOffille() {
        return idOffille;
    }
    public void setIdOffille(String idOffille) {
        this.idOffille = idOffille;
    }
    public String getIdOf() {
        return idOf;
    }
    public void setIdOf(String idOf) {
        this.idOf = idOf;
    }
    public String getTypechargelib() {
        return typechargelib;
    }
    public void setTypechargelib(String typechargelib) {
        this.typechargelib = typechargelib;
    }
    public String getIdingredientslib() {
        return idingredientslib;
    }
    public void setIdingredientslib(String idingredientslib) {
        this.idingredientslib = idingredientslib;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    

}
