package paie.competence;

import bean.ClassMAPTable;

public class CompetenceTypeDashboard extends ClassMAPTable {
    private String idtypecompetencefplib;
    private double notemoyenne;

    public String getIdtypecompetencefplib() {
        return idtypecompetencefplib;
    }

    public void setIdtypecompetencefplib(String idtypecompetencefplib) {
        this.idtypecompetencefplib = idtypecompetencefplib;
    }

    public double getNotemoyenne() {
        return notemoyenne;
    }

    public void setNotemoyenne(double notemoyenne) {
        this.notemoyenne = notemoyenne;
    }



    public CompetenceTypeDashboard() throws Exception {
        this.setNomTable("V_EVALUATION_CPT_MOYENNE");
    }



    @Override
    public String getTuppleID() {
        return idtypecompetencefplib;
    }

    @Override
    public String getAttributIDName() {
        return "idtypecompetencefplib";
    }
}

