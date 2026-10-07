package paie.evaluation;

import bean.ClassFille;

import java.sql.Connection;

public class ObjectifAnnuelDetail extends ClassFille {
    private String id;
    private String idObjectifannuel;
    private String objectif;
    private String indicateurAtteinte;
    private String moyenAtteinte;
    private int pourcentageAtteinte;
    private String commentaire;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdObjectifannuel() {
        return idObjectifannuel;
    }

    public void setIdObjectifannuel(String idObjectifannuel) {
        this.idObjectifannuel = idObjectifannuel;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getIndicateurAtteinte() {
        return indicateurAtteinte;
    }

    public void setIndicateurAtteinte(String indicateurAtteinte) {
        this.indicateurAtteinte = indicateurAtteinte;
    }

    public String getMoyenAtteinte() {
        return moyenAtteinte;
    }

    public void setMoyenAtteinte(String moyenAtteinte) {
        this.moyenAtteinte = moyenAtteinte;
    }

    public int getPourcentageAtteinte() {
        return pourcentageAtteinte;
    }

    public void setPourcentageAtteinte(int pourcentageAtteinte) {
        this.pourcentageAtteinte = pourcentageAtteinte;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.evaluation.ObjectifAnnuel";
    }

    @Override
    public String getLiaisonMere() {
        return "idObjectifannuel";
    }

    public ObjectifAnnuelDetail() throws Exception {
        this.setNomTable("OBJECTIF_ANNUEL_DETAIL");
        this.setNomClasseMere("paie.evaluation.ObjectifAnnuel");
        this.setLiaisonMere("idObjectifannuel");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OBJD","GET_SEQ_OBJ_ANN_DET");
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

