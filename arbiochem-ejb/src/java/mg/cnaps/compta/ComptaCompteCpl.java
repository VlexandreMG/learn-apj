package mg.cnaps.compta;

public class ComptaCompteCpl extends ComptaCompte{
    private String compteInt;

    public String getCompteInt() {
        return compteInt;
    }

    public void setCompteInt(String compteInt) {
        this.compteInt = compteInt;
    }


    public ComptaCompteCpl() {
        this.setNomTable("v_compta_compte");
    }
}
