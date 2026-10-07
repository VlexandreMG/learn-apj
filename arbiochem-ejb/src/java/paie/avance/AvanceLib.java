package paie.avance;

public class AvanceLib extends Avance{

    private String nomPersonnel;
    private String matricule;

    public AvanceLib() throws Exception
    {
        super.setNomTable("AVANCELIB_VIDE");
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    @Override
    public String getMatricule() {
        return matricule;
    }

    @Override
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
