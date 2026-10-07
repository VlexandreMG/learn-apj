package vente;

public class CommandeCpl extends Commande{

    String idMagasinLib;
    String idClientLib;
    String etatLib;
    String modelivraisonLib;
    String modePaiementLib;
    double montantttc;

    public double getMontantttc() {
        return montantttc;
    }

    public void setMontantttc(double montantttc) {
        this.montantttc = montantttc;
    }

    public void setModelivraisonLib(String modelivraisonLib) {
        this.modelivraisonLib = modelivraisonLib;
    }

    public String getModePaiementLib() {
        return modePaiementLib;
    }

    public void setModePaiementLib(String modePaiementLib) {
        this.modePaiementLib = modePaiementLib;
    }

    public String getModelivraisonLib() {
        return modelivraisonLib;
    }

    public void setModelivraisonlib(String modelivraisonLib) {
        this.modelivraisonLib = modelivraisonLib;
    }

    public CommandeCpl() throws Exception {
        this.setNomTable("CommandeCpl");
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public String getIdClientLib() {
        return idClientLib;
    }

    public void setIdClientLib(String idClientLib) {
        this.idClientLib = idClientLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
