package paie.cv;

import bean.ClassMAPTable;

public class CVScoreGlobale extends ClassMAPTable {
    private String idcv;
    private String idficheposte;
    private int nbcompetences;
    private int nbcompetencesvalides;
    private int nbcompetencesnonvalides;
    private int score;

    public String getIdcv() {
        return idcv;
    }

    public void setIdcv(String idcv) {
        this.idcv = idcv;
    }

    public String getIdficheposte() {
        return idficheposte;
    }

    public void setIdficheposte(String idficheposte) {
        this.idficheposte = idficheposte;
    }

    public int getNbcompetences() {
        return nbcompetences;
    }

    public void setNbcompetences(int nbcompetences) {
        this.nbcompetences = nbcompetences;
    }

    public int getNbcompetencesvalides() {
        return nbcompetencesvalides;
    }

    public void setNbcompetencesvalides(int nbcompetencesvalides) {
        this.nbcompetencesvalides = nbcompetencesvalides;
    }

    public int getNbcompetencesnonvalides() {
        return nbcompetencesnonvalides;
    }

    public void setNbcompetencesnonvalides(int nbcompetencesnonvalides) {
        this.nbcompetencesnonvalides = nbcompetencesnonvalides;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    public CVScoreGlobale() throws Exception {
        this.setNomTable("V_CV_SCORE_GLOBAL");
    }


    @Override
    public String getTuppleID() {
        return idcv;
    }

    @Override
    public String getAttributIDName() {
        return "idcv";
    }
}

