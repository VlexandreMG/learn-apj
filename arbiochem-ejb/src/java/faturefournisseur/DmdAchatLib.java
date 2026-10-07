package faturefournisseur;

public class DmdAchatLib extends DmdAchat {

    String fournisseurLib , traite ,idbc;
    String etatlib;
    private String idMagasinLib;
    private String idCategorieLib;
    private String idServiceLib;
    private String idProvenanceLib;
    private String refproforma;
    private String idTraite;

    public String getIdTraite() {
        return idTraite;
    }

    public void setIdTraite(String idTraite) {
        this.idTraite = idTraite;
    }

    public String getIdbc() {
        return idbc;
    }

    public void setIdbc(String idbc) {
        this.idbc = idbc;
    }

    public String getTraite() {
        return traite;
    }

    public void setTraite(String traite) {
        this.traite = traite;
    }

    public String getIdServiceLib() {
        return idServiceLib;
    }

    public void setIdServiceLib(String idServiceLib) {
        this.idServiceLib = idServiceLib;
    }

    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }

    public String getFournisseurLib() {
        return fournisseurLib;
    }

    public void setFournisseurLib(String fournisseurLib) {
        this.fournisseurLib = fournisseurLib;
    }

    public DmdAchatLib() throws Exception {
        this.setNomTable("DMDACHATLIB");
    }

    public String getIdMagasinLib() {
        return idMagasinLib;
    }

    public void setIdMagasinLib(String idMagasinLib) {
        this.idMagasinLib = idMagasinLib;
    }

    public String getIdCategorieLib() {
        return idCategorieLib;
    }

    public void setIdCategorieLib(String idCategorieLib) {
        this.idCategorieLib = idCategorieLib;
    }

    public String getIdProvenanceLib() {
        return idProvenanceLib;
    }

    public void setIdProvenanceLib(String idProvenanceLib) {
        this.idProvenanceLib = idProvenanceLib;
    }

    public String getRefproforma() {
        return refproforma;
    }

    public void setRefproforma(String refproforma) {
        this.refproforma = refproforma;
    }
    
    
}
