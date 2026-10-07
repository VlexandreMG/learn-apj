package paie.cv;

public class CVCompetenceLib extends CVCompetence{
    private String idtypecompetencesfplib;
    public CVCompetenceLib() throws Exception {
        this.setNomTable("CV_COMPETENCE_LIB");
    }

    public String getIdtypecompetencesfplib() {
        return idtypecompetencesfplib;
    }

    public void setIdtypecompetencesfplib(String idtypecompetencesfplib) {
        this.idtypecompetencesfplib = idtypecompetencesfplib;
    }
}
