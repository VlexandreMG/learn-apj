package onBoarding;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class EmployeOnBoardingSession extends ClassMere {
    private String id;
    private String idPersonnel;
    private String idOnboarding;
    private Date dateDebut;
    private Date dateFin;
    private int etat;
    private double progression;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getIdOnboarding() {
        return idOnboarding;
    }

    public void setIdOnboarding(String idOnboarding) {
        this.idOnboarding = idOnboarding;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public double getProgression() {
        return progression;
    }

    public void setProgression(double progression) {
        this.progression = progression;
    }


    public EmployeOnBoardingSession() throws Exception {
        this.setNomTable("EMPLOYEE_ONBOARDING_SESSIONS");
        this.setNomClasseFille("onBoarding.EmployeOnBoardingChecklist");
        this.setLiaisonFille("idSessionOnboarding");
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","idPersonel"};
        return motCles;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EOBS","get_SEQ_EMP_ONBOARDINGSESSIONS");
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

