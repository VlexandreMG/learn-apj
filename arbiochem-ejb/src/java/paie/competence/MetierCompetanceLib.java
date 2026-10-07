package paie.competence;

public class MetierCompetanceLib extends MetierCompetence{
    String niveauLib, metierLib, competenceLib;
    public MetierCompetanceLib() throws Exception {
        this.setNomTable("V_METIER_COMPETENCE");
    }

    public String getNiveauLib() {
        return niveauLib;
    }

    public void setNiveauLib(String niveauLib) {
        this.niveauLib = niveauLib;
    }

    public String getMetierLib() {
        return metierLib;
    }

    public void setMetierLib(String metierLib) {
        this.metierLib = metierLib;
    }

    public String getCompetenceLib() {
        return competenceLib;
    }

    public void setCompetenceLib(String competenceLib) {
        this.competenceLib = competenceLib;
    }
}
