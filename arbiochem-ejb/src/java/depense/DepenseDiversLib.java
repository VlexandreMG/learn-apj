package depense;

public class DepenseDiversLib extends DepenseDivers{
    private String fournisseurLib;
    private String idModePaiementLib;
    private String idCategorieLib;
    private String etatLib;
    private String idMagasinLib;
    private double montantTotal;
    private String serviceLib;
    private String idSectionLib;
    private double surplusTotal;
    private double renduTotal;

    public double getSurplusTotal() {
        return surplusTotal;
    }

    public void setSurplusTotal(double surplusTotal) {
        this.surplusTotal = surplusTotal;
    }

    public double getRenduTotal() {
        return renduTotal;
    }

    public void setRenduTotal(double renduTotal) {
        this.renduTotal = renduTotal;
    }

    public String getServiceLib() {
        return serviceLib;
    }

    public void setServiceLib(String serviceLib) {
        this.serviceLib = serviceLib;
    }

    public String getIdSectionLib() {
        return idSectionLib;
    }

    public void setIdSectionLib(String idSectionLib) {
        this.idSectionLib = idSectionLib;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public DepenseDiversLib() throws Exception {
        this.setNomTable("DEPENSEDIVERSLIB");
    }

    public String getFournisseurLib() {
        return fournisseurLib;
    }

    public void setFournisseurLib(String fournisseurLib) {
        this.fournisseurLib = fournisseurLib;
    }

    public String getIdModePaiementLib() {
        return idModePaiementLib;
    }

    public void setIdModePaiementLib(String idModePaiementLib) {
        this.idModePaiementLib = idModePaiementLib;
    }

    public String getIdCategorieLib() {
        return idCategorieLib;
    }

    public void setIdCategorieLib(String idCategorieLib) {
        this.idCategorieLib = idCategorieLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
}
