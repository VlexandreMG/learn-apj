package maintenance.capacitejournalier;

public class CapaciteJournalierLib extends CapaciteJournalier{

    private String idLigneLib;

    public String getIdLigneLib() {
        return idLigneLib;
    }

    public void setIdLigneLib(String idLigneLib) {
        this.idLigneLib = idLigneLib;
    }

    public CapaciteJournalierLib(){
        setNomTable("CAPACITEJOURNALIERLIB");
    }
}
