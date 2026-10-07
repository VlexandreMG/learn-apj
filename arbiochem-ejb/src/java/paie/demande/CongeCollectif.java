package paie.demande;

public class CongeCollectif extends DemandeJustifications {
    private String idDepartement;
    private String departementLib;

    public CongeCollectif() {
        this.setNomTable("DEMANDE");
    }

    public String getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(String idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getDepartementLib() {
        return departementLib;
    }

    public void setDepartementLib(String departementLib) {
        this.departementLib = departementLib;
    }
}
