package paie.competence;

import bean.ClassMAPTable;

public class FaibleCompetenceDashboard extends ClassMAPTable {
    private String competence;
    private double notemoyenne;

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public double getNotemoyenne() {
        return notemoyenne;
    }

    public void setNotemoyenne(double notemoyenne) {
        this.notemoyenne = notemoyenne;
    }



    public FaibleCompetenceDashboard() throws Exception {
        this.setNomTable("V_COMPETENCE_NOTE_FAIBLE");
    }



    @Override
    public String getTuppleID() {
        return competence;
    }

    @Override
    public String getAttributIDName() {
        return "competence";
    }
}

