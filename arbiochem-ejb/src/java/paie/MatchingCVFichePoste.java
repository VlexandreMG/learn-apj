package paie;

import bean.ClassMAPTable;

public class MatchingCVFichePoste extends ClassMAPTable {
    private String idcv;
    private String idficheposte;
    private String idfichepostecompetence;
    private String idtypecompetence;
    private String typecompetencelib;
    private String competence;
    private int niveaurequis;
    private int niveaucandidat;
    private int ecart;
    private int estvalide;
    private String resultat;

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

    public String getIdfichepostecompetence() {
        return idfichepostecompetence;
    }

    public void setIdfichepostecompetence(String idfichepostecompetence) {
        this.idfichepostecompetence = idfichepostecompetence;
    }

    public String getIdtypecompetence() {
        return idtypecompetence;
    }

    public void setIdtypecompetence(String idtypecompetence) {
        this.idtypecompetence = idtypecompetence;
    }

    public String getTypecompetencelib() {
        return typecompetencelib;
    }

    public void setTypecompetencelib(String typecompetencelib) {
        this.typecompetencelib = typecompetencelib;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public int getNiveaurequis() {
        return niveaurequis;
    }

    public void setNiveaurequis(int niveaurequis) {
        this.niveaurequis = niveaurequis;
    }

    public int getNiveaucandidat() {
        return niveaucandidat;
    }

    public void setNiveaucandidat(int niveaucandidat) {
        this.niveaucandidat = niveaucandidat;
    }

    public int getEcart() {
        return ecart;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    public int getEstvalide() {
        return estvalide;
    }

    public void setEstvalide(int estvalide) {
        this.estvalide = estvalide;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }



    public MatchingCVFichePoste() throws Exception {
        this.setNomTable("V_MATCHING_CV_FP");
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

