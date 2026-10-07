package paie.formation;

import bean.CGenUtil;
import bean.ClassEtat;

import java.sql.Connection;
import java.sql.Date;

public class ParticipationFormation extends ClassEtat {
    private String id;
    private String idactionformation;
    private String idpersonnel;
    private Date dateinscription;
    private String remarque;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdactionformation() {
        return idactionformation;
    }

    public void setIdactionformation(String idactionformation) {
        this.idactionformation = idactionformation;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public Date getDateinscription() {
        return dateinscription;
    }

    public void setDateinscription(Date dateinscription) {
        this.dateinscription = dateinscription;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }



    public ParticipationFormation() throws Exception {
        this.setNomTable("PARTICIPATION_FORMATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PFR","GET_SEQ_PARTICIPATIONFORMATION");
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

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","intitule", "reference"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","intitule", "reference"};
        return motCles;
    }

    public ParticipationFormation[] getParticipationFormationByIdActionFormation(String idactionformation) throws Exception {
        ParticipationFormation participationFormation = new ParticipationFormation();
        ParticipationFormation[] participationFormations = (ParticipationFormation[]) CGenUtil.rechercher(participationFormation, null, null, "");
        if (participationFormations.length > 0){
            return participationFormations;
        } else {
            return null;
        }
    }
}

