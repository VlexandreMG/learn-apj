package paie.formateur;

public class ModulePrestataireLib extends ModulePrestataire{
    private String idFormateurLib;
    public ModulePrestataireLib() throws Exception {
        this.setNomTable("V_MODULE_PRESTATAIRE_LIB");
    }

    public String getIdFormateurLib() {
        return idFormateurLib;
    }

    public void setIdFormateurLib(String idFormateurLib) {
        this.idFormateurLib = idFormateurLib;
    }
}
