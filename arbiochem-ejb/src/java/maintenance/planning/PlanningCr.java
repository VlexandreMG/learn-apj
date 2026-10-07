package maintenance.planning;

import bean.CGenUtil;
import bean.ClassMAPTable;
import utilitaire.Utilitaire;

import java.sql.Date;

public class PlanningCr extends ClassMAPTable {
    int nombreDemande;
    int nombreRealise;
    double pourcentage;

    public PlanningCr() {
        this.setNomTable("PLANNING_CR");
    }

    @Override
    public String getTuppleID() {
        return "";
    }

    @Override
    public String getAttributIDName() {
        return "";
    }

    public int getNombreDemande() {
        return nombreDemande;
    }

    public void setNombreDemande(int nombreDemande) {
        this.nombreDemande = nombreDemande;
    }

    public int getNombreRealise() {
        return nombreRealise;
    }

    public void setNombreRealise(int nombreRealise) {
        this.nombreRealise = nombreRealise;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public PlanningCr[] rapprochementCr(String daty) throws Exception{
        String[] colInt = {"dateDebut"};
        String[] valInt = {daty, Utilitaire.ajoutJourDateString(Utilitaire.dateDuJourSql(),7)};
        PlanningCpl t = new PlanningCpl();
        PlanningCpl[] pldata =(PlanningCpl[]) CGenUtil.rechercher(new PlanningCpl(),colInt,valInt,null,"");
        int[] dataResume = t.getDataRssume(pldata);
        this.setNombreDemande(dataResume[0]);
        this.setNombreRealise(dataResume[1]);
        double pourcentage = this.getNombreDemande() == 0 ? 0 : ( (double) this.getNombreRealise() / this.getNombreDemande() ) * 100;
        this.setPourcentage(pourcentage);
        return new PlanningCr[]{this};
    }
}
