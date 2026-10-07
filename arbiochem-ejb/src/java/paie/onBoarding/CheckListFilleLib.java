package paie.onBoarding;

public class CheckListFilleLib extends CheckListFille {

    private String idTypeDocumentLib;
    private String idResponsableLib;
    private String est_cocheLib;

    public CheckListFilleLib() throws Exception {
        this.setNomTable("CHECKLIST_FILLE_LIB");
    }

    public CheckListFilleLib(String nomtable) throws Exception {
        this.setNomTable(nomtable);
    }

    public String getIdTypeDocumentLib() {
        return idTypeDocumentLib;
    }

    public void setIdTypeDocumentLib(String idTypeDocumentLib) {
        this.idTypeDocumentLib = idTypeDocumentLib;
    }

    public String getIdResponsableLib() {
        return idResponsableLib;
    }

    public void setIdResponsableLib(String idResponsableLib) {
        this.idResponsableLib = idResponsableLib;
    }

    public String getEst_cocheLib() {
        return est_cocheLib;
    }

    public void setEst_cocheLib(String est_cocheLib) {
        this.est_cocheLib = est_cocheLib;
    }
}