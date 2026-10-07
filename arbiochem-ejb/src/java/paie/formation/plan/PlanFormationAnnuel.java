package paie.formation.plan;

import bean.ClassEtat;
import paie.formation.action.ActionFormation;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;

public class PlanFormationAnnuel extends ClassEtat {
    private String id;
    private int annee;
    private String libelle;
    private double budgettotalprevisionel;
    private Date daty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getBudgettotalprevisionel() {
        return budgettotalprevisionel;
    }

    public void setBudgettotalprevisionel(double budgettotalprevisionel) {
        this.budgettotalprevisionel = budgettotalprevisionel;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }



    public PlanFormationAnnuel() throws Exception {
        this.setNomTable("PLAN_FORMATION_ANNUEL");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PFA","GET_SEQ_PLAN_FORMATION_ANNUEL");
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
        String[] motCles={"id","libelle"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","libelle"};
        return motCles;
    }
    public ActionFormation genererActionFormation(Connection c)throws Exception
    {
        boolean estOuvert=false;
        try
        {
            if(c==null)
            {
                c=new UtilDB().GetConn();
                estOuvert=true;
            }
            PlanFormationAnnuel bl = (PlanFormationAnnuel) this.getById(this.getId(),"PLAN_FORMATION_ANNUEL",c);           
            ActionFormation mv=new ActionFormation();
            mv.setIdplanformation(bl.getId());
            mv.setIntitule("Action de formation pour le plan "+bl.getLibelle());
            mv.setReference("REF-"+bl.getAnnee());
            mv.setNbcadreprevue(0);
            mv.setObjectif("Objectif de l'action de formation pour le plan "+bl.getLibelle());
            return mv;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(c!=null&&estOuvert==true)c.close();
        }
    }
}

