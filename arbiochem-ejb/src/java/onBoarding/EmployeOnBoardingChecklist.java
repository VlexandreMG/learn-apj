package onBoarding;

import bean.CGenUtil;
import bean.ClassFille;
import constante.ConstanteEtat;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;

public class EmployeOnBoardingChecklist extends ClassFille {
    private String id;
    private String idSessionOnboarding;
    private String idOnboardingItem;
    private int estTerminer;
    private Date dateFin;
    private String remarque;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSessionOnboarding() {
        return idSessionOnboarding;
    }

    public void setIdSessionOnboarding(String idSessionOnboarding) {
        this.idSessionOnboarding = idSessionOnboarding;
    }

    public String getIdOnboardingItem() {
        return idOnboardingItem;
    }

    public void setIdOnboardingItem(String idOnboardingItem) {
        this.idOnboardingItem = idOnboardingItem;
    }

    public int getEstTerminer() {
        return estTerminer;
    }

    public void setEstTerminer(int estTerminer) {
        this.estTerminer = estTerminer;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public EmployeOnBoardingChecklist() throws Exception {
        this.setNomTable("EMPLOYEE_ONBOARDING_CHECKLIST");
    }

    @Override
    public String getNomClasseMere() {
        return "onBoarding.EmployeOnBoardingSession";
    }

    @Override
    public String getLiaisonMere() {
        return "idSessionOnboarding";
    }


    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EOBC","get_SEQ_EMPONBOARDINGCHECKLIST");
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

    public EmployeOnBoardingSession validerOnBoardingMultiple(String[] ids, String u, Connection c) throws Exception {
        EmployeOnBoardingSession empOnb = null;
        boolean estOuvert = false;

        try {
            if (c == null) {
                estOuvert = true;
                c = new UtilDB().GetConn();
            }

            EmployeOnBoardingChecklist[] bls = (EmployeOnBoardingChecklist[]) CGenUtil.rechercher(new EmployeOnBoardingChecklist(), null, null, c, " and id in (" + Utilitaire.tabToString(ids, "'", ",") + ") ");

            if (bls.length > 0) {
                for (int i = 0; i < bls.length; i++) {
                    bls[i].setEstTerminer(1);
                    bls[i].setDateFin(Utilitaire.dateDuJourSql());
                    bls[i].setEtat(ConstanteEtat.getEtatValider());
                    bls[i].updateToTableWithHisto(u, c);
                }
                empOnb = (EmployeOnBoardingSession) new EmployeOnBoardingSession().getById(bls[0].getIdSessionOnboarding(), null, c);
                EmployeOnBoardingChecklist[] filles = (EmployeOnBoardingChecklist[]) CGenUtil.rechercher(new EmployeOnBoardingChecklist(), null, null, c, " and IDSESSIONONBOARDING = '" + empOnb.getId() + "'");

                int total = filles.length;
                int completed = 0;

                for (EmployeOnBoardingChecklist f : filles) {
                    if (f.getEstTerminer() == 1) {
                        completed++;
                    }
                }
                if (total > 0) {
                    int progression = (int) ((100.0 * completed) / total);
                    empOnb.setProgression(progression);
                } else {
                    empOnb.setProgression(0);
                }

                if (empOnb.getProgression() >= 100) {
                    empOnb.setEtat(ConstanteEtat.getEtatValider());
                }
                empOnb.updateToTableWithHisto(u, c);
            }

            return empOnb;

        } catch (Exception e) {
            if (c != null) {
                c.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            if (c != null && estOuvert) {
                c.close();
            }
        }
    }
}

