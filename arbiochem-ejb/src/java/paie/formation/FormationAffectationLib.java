package paie.formation;

public class FormationAffectationLib extends FormationAffectation{
    String etatLib, formationlib, personnellib;
    public FormationAffectationLib() throws Exception {
        this.setNomTable("V_FORMATION_AFFECTATION");
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getFormationlib() {
        return formationlib;
    }

    public void setFormationlib(String formationlib) {
        this.formationlib = formationlib;
    }

    public String getPersonnellib() {
        return personnellib;
    }

    public void setPersonnellib(String personnellib) {
        this.personnellib = personnellib;
    }
}
