package maintenance.configuration;


public class ReleveFabCpl extends ReleveFab {
    private String idCategorie;

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public ReleveFabCpl() {
        super.setNomTable("RELEVERFABCPL");
    }

}
