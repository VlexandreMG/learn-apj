package entretien;

import bean.ClassMere;

import java.sql.Connection;

public class EntretienRH extends ClassMere {
    private String id;
    private int typeEntretient;
    private String nomPrenomCandidat;
    private String idPersonnel;
    private String idFonction;
    private String idDirection;
    private String idEvaluateur;
    private String motivationCandidat;
    private String ambitionProfessionel;
    private double pretentionSalariale;
    private double remunerationActuel;
    private String appreciationCandidat;
    private String commentaires;
    private int experienceCandidat;
    private String atout;
    private String faiblesses;
    private String pointVigilance;
    private String conclustion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTypeEntretient() {
        return typeEntretient;
    }

    public void setTypeEntretient(int typeEntretient) {
        this.typeEntretient = typeEntretient;
    }

    public String getNomPrenomCandidat() {
        return nomPrenomCandidat;
    }

    public void setNomPrenomCandidat(String nomPrenomCandidat) {
        this.nomPrenomCandidat = nomPrenomCandidat;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(String idFonction) {
        this.idFonction = idFonction;
    }

    public String getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(String idDirection) {
        this.idDirection = idDirection;
    }

    public String getIdEvaluateur() {
        return idEvaluateur;
    }

    public void setIdEvaluateur(String idEvaluateur) {
        this.idEvaluateur = idEvaluateur;
    }

    public String getMotivationCandidat() {
        return motivationCandidat;
    }

    public void setMotivationCandidat(String motivationCandidat) {
        this.motivationCandidat = motivationCandidat;
    }

    public String getAmbitionProfessionel() {
        return ambitionProfessionel;
    }

    public void setAmbitionProfessionel(String ambitionProfessionel) {
        this.ambitionProfessionel = ambitionProfessionel;
    }

    public double getPretentionSalariale() {
        return pretentionSalariale;
    }

    public void setPretentionSalariale(double pretentionSalariale) {
        this.pretentionSalariale = pretentionSalariale;
    }

    public double getRemunerationActuel() {
        return remunerationActuel;
    }

    public void setRemunerationActuel(double remunerationActuel) {
        this.remunerationActuel = remunerationActuel;
    }

    public String getAppreciationCandidat() {
        return appreciationCandidat;
    }

    public void setAppreciationCandidat(String appreciationCandidat) {
        this.appreciationCandidat = appreciationCandidat;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public int getExperienceCandidat() {
        return experienceCandidat;
    }

    public void setExperienceCandidat(int experienceCandidat) {
        this.experienceCandidat = experienceCandidat;
    }

    public String getAtout() {
        return atout;
    }

    public void setAtout(String atout) {
        this.atout = atout;
    }

    public String getFaiblesses() {
        return faiblesses;
    }

    public void setFaiblesses(String faiblesses) {
        this.faiblesses = faiblesses;
    }

    public String getPointVigilance() {
        return pointVigilance;
    }

    public void setPointVigilance(String pointVigilance) {
        this.pointVigilance = pointVigilance;
    }

    public String getConclustion() {
        return conclustion;
    }

    public void setConclustion(String conclustion) {
        this.conclustion = conclustion;
    }



    public EntretienRH() throws Exception {
        this.setNomTable("ENTRETIENRH");
        this.setNomClasseFille("entretien.EntretienRHDetails");
        this.setLiaisonFille("identRetientRh");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ERH","getseq_EntretienRH");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}

