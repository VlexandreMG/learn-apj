package paie.absence;

public class TypeAbsenceLib extends TypeAbsence {
    private String etatLib,frequenceLib;
    public TypeAbsenceLib() {
        super.setNomTable("TYPEABSENCE_CPL");
    }
    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getFrequenceLib() {
        return frequenceLib;
    }

    public void setFrequenceLib(String frequenceLib) {
        this.frequenceLib = frequenceLib;
    }
}
