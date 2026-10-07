package paie.competence;

import bean.ClassMAPTable;

public class EcartEvaluationDashboard extends ClassMAPTable {
    private String competence;
    private double moyenneautoevaluation;
    private double moyenneevaluationsuperieur;

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public double getMoyenneautoevaluation() {
        return moyenneautoevaluation;
    }

    public void setMoyenneautoevaluation(double moyenneautoevaluation) {
        this.moyenneautoevaluation = moyenneautoevaluation;
    }

    public double getMoyenneevaluationsuperieur() {
        return moyenneevaluationsuperieur;
    }

    public void setMoyenneevaluationsuperieur(double moyenneevaluationsuperieur) {
        this.moyenneevaluationsuperieur = moyenneevaluationsuperieur;
    }



    public EcartEvaluationDashboard() throws Exception {
        this.setNomTable("V_COMPARAISON_EVALUATION");
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

