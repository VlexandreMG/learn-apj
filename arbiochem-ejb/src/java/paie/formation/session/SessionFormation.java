package paie.formation.session;

import bean.CGenUtil;
import bean.ClassEtat;

import java.sql.Connection;
import java.sql.Date;

public class SessionFormation extends ClassEtat {
    private String id;
    private String idactionformation;
    private Date datedebut;
    private Date datefin;
    private double nbheureprevue;
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

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public double getNbheureprevue() {
        return nbheureprevue;
    }

    public void setNbheureprevue(double nbheureprevue) {
        this.nbheureprevue = nbheureprevue;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }



    public SessionFormation() throws Exception {
        this.setNomTable("SESSION_FORMATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SFR","GET_SEQ_SESSION_FORMATION");
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
        String[] motCles={"id","nbheureprevue", "remarque"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","nbheureprevue", "remarque"};
        return motCles;
    }

    public SessionFormation[] getAllSessionFormation() throws Exception {
        SessionFormation[] sessionFormations = (SessionFormation[]) CGenUtil.rechercher(new SessionFormation(), null, null, "");
        return sessionFormations;
    }

    public SessionFormation getSessionFormationById(String idSessionFormation) throws Exception {
        SessionFormation sessionFormation = new SessionFormation();
        sessionFormation.setId(idSessionFormation);
        SessionFormation[] sessionFormations = (SessionFormation[]) CGenUtil.rechercher(sessionFormation, null, null,"");
        if (sessionFormations.length > 0) {
            return sessionFormations[0];
        } else {
            return null;
        }
    }

}

