package paie.formation;

public class ParticipationFormationLib extends ParticipationFormation {
    String matricule, categorieEmploye, idActionFormationLib, etatLib;
    String personnel;
    public ParticipationFormationLib() throws Exception {
        this.setNomTable("PARTICIPATION_FORMATION_LIB");
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCategorieEmploye() {
        return categorieEmploye;
    }

    public void setCategorieEmploye(String categorieEmploye) {
        this.categorieEmploye = categorieEmploye;
    }

    public String getIdActionFormationLib() {
        return idActionFormationLib;
    }

    public void setIdActionFormationLib(String idActionFormationLib) {
        this.idActionFormationLib = idActionFormationLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }
    @Override
    public String[] getMotCles() {
        String[] motCles={"id","matricule", "idActionFormationLib"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","matricule", "idActionFormationLib"};
        return motCles;
    }

}
