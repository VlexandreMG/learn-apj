package ferme.production;

public class ImportOAC extends ReceptionOACLib{
    String idProvenance, idProvenanceLib;

    public ImportOAC() throws Exception {
        this.setNomTable("IMPORTOAC");
    }

    public String getIdProvenance() {
        return idProvenance;
    }

    public void setIdProvenance(String idProvenance) {
        this.idProvenance = idProvenance;
    }

    public String getIdProvenanceLib() {
        return idProvenanceLib;
    }

    public void setIdProvenanceLib(String idProvenanceLib) {
        this.idProvenanceLib = idProvenanceLib;
    }
}
