package poste;

public class FicheCompetenceFPLib extends FicheCompetenceFP {
    private String idtypecompetencesfplib;
    private String niveaulib;

    

    public String getIdtypecompetencesfplib() {
        return idtypecompetencesfplib;
    }

    public void setIdtypecompetencesfplib(String idtypecompetencesfplib) {
        this.idtypecompetencesfplib = idtypecompetencesfplib;
    }
    

    public FicheCompetenceFPLib() throws Exception {
        this.setNomTable("FICHE_POSTE_COMPETENCES_LIB");
    }

    public String getNiveaulib() {
        return niveaulib;
    }

    public void setNiveaulib(String niveaulib) {
        this.niveaulib = niveaulib;
    }

   
}

