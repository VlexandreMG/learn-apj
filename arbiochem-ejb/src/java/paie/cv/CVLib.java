package paie.cv;

public class CVLib extends CV {
    String idFichePosteLib;

    public CVLib()throws Exception{
        this.setNomTable("CV_LIB");
    }

    public String getIdFichePosteLib() {
        return idFichePosteLib;
    }

    public void setIdFichePosteLib(String idFichePosteLib) {
        this.idFichePosteLib = idFichePosteLib;
    }
}
