package paie.competence;

public class PersonnelCompetenceLib extends PersonnelCompetence{
    String competenceLib, niveauLib, personnelLib;
    public PersonnelCompetenceLib() throws Exception {
        this.setNomTable("V_PERSONNEL_COMPETENCE");
    }

    public String getCompetenceLib() {
        return competenceLib;
    }

    public void setCompetenceLib(String competenceLib) {
        this.competenceLib = competenceLib;
    }

    public String getNiveauLib() {
        return niveauLib;
    }

    public void setNiveauLib(String niveauLib) {
        this.niveauLib = niveauLib;
    }

    public String getPersonnelLib() {
        return personnelLib;
    }

    public void setPersonnelLib(String personnelLib) {
        this.personnelLib = personnelLib;
    }
}
