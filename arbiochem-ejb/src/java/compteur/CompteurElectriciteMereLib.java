package compteur;

public class CompteurElectriciteMereLib extends CompteurElectriciteMere{
    private  String lignelib;
    private  String etatlib;
    private  double consommation;
    private  double montant;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public String getLignelib() {
            return lignelib;
        }

    public void setLignelib(String lignelib) {
        this.lignelib = lignelib;
    }

    public  CompteurElectriciteMereLib() throws Exception {
        this.setNomTable("COMPTEURELECTRICITEMERELIB");
    }

}
