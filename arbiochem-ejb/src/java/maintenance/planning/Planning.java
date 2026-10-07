package maintenance.planning;

import annexe.Unite;
import bean.CGenUtil;
import bean.ClassEtat;
import bean.ClassMAPTable;
import maintenance.configuration.UniteMaintenance;
import maintenance.utils.CalendarUtil;
import maintenance.utils.ConstanteMaintenance;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Planning extends ClassEtat {
    String id;
    String refObjet;
    int estPeriodique;
    String idTypeMaintenance;
    String idMachine;
    double frequence;
    String unite;
    Date datedebut;
    Date datefin;
    String duree;
    String heure;
    String idSource;
    UniteMaintenance uniteMaintenance;
    ArrayList<String> joursOuvrablesEntreDebutFin;
    String idLigne, idSituation;

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    public String getIdSituation() {
        return idSituation;
    }

    public void setIdSituation(String idSituation) {
        this.idSituation = idSituation;
    }

    public ArrayList<String> getJoursOuvrablesEntreDebutFin() {
        return joursOuvrablesEntreDebutFin;
    }

    public void setJoursOuvrablesEntreDebutFin(ArrayList<String> joursOuvrablesEntreDebutFin) {
        this.joursOuvrablesEntreDebutFin = joursOuvrablesEntreDebutFin;
    }

    public UniteMaintenance getUniteMaintenance() {
        return uniteMaintenance;
    }

    public void setUniteMaintenance(UniteMaintenance uniteMaintenance) {
        this.uniteMaintenance = uniteMaintenance;
    }

    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public Planning() {
        this.setNomTable("Planning");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PLN", "getSeqPlanning");
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefObjet() {
        return refObjet;
    }

    public void setRefObjet(String refObjet) {
        this.refObjet = refObjet;
    }

    public int getEstPeriodique() {
        return estPeriodique;
    }

    public void setEstPeriodique(int estPeriodique) {
        this.estPeriodique = estPeriodique;
    }

    public String getIdTypeMaintenance() {
        return idTypeMaintenance;
    }

    public void setIdTypeMaintenance(String idTypeMaintenance) {
        this.idTypeMaintenance = idTypeMaintenance;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) throws Exception {
        if(this.getMode().equals("modif") && (idMachine == null || idMachine.isEmpty())){
            throw new Exception("L'\u00E9l\u00E9ment ne doit pas \u00EAtre vide");
        }
        this.idMachine = idMachine;
    }

    public double getFrequence() {
        return frequence;
    }

    public void setFrequence(double frequence) {
        this.frequence = frequence;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
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

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) throws Exception {
        if (!CalendarUtil.isValidTime(duree)) {
            this.duree = duree;
        }
        else {
            this.duree = String.valueOf(CalendarUtil.HMSToSecond(duree));
        }
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) throws Exception {
        if (!CalendarUtil.isValidTime(heure)) {
            throw new Exception("L' heure doit etre de format HH:MM:SS");
        }
        this.heure = heure;
    }

    public UniteMaintenance findEchelle(Connection c) throws Exception {
        UniteMaintenance u = (UniteMaintenance) new UniteMaintenance().getById(this.getUnite(),"",c);
        return u;
    }

    private void controlePlanningJournalier() throws Exception {
        LocalTime heureDebut = LocalTime.parse(this.getHeure());
        LocalTime finJournee = LocalTime.of(17, 0);
        long secondes = Long.parseLong(this.getDuree());
        int frequence = (int) this.getFrequence();
        Duration duree = Duration.ofSeconds(secondes);
        if (frequence <= 0) {
            throw new Exception("La fr\u00E9quence doit \u00EAtre au moins 1.");
        }
        if (secondes <= 0) {
            throw new Exception("La dur\u00E9e d'une s\u00E9ance doit \u00EAtre sup\u00E9rieure \u00E0 0 minute.");
        }
        if (!heureDebut.isBefore(finJournee)) {
            throw new Exception("L'heure de d\u00E9but doit \u00EAtre avant 17:00.");
        }
        // ---- calcul temps ----
        Duration tempsRestant = Duration.between(heureDebut, finJournee);
        Duration tempsOccupe = duree.multipliedBy(frequence);
        // ---- pas assez de temps ----
        if (tempsOccupe.compareTo(tempsRestant) > 0) {
            long minutesDisponibles = tempsRestant.toMinutes();
            long minutesSeance = duree.toMinutes();
            long maxSeances = minutesDisponibles / minutesSeance;
            String erreur = "Impossible de planifier " + frequence + " s\u00E9ance(s) de "
                    + minutesSeance + " minute(s). "
                    + "Entre " + heureDebut + " et 17:00 vous pouvez placer au maximum "
                    + maxSeances + " s\u00E9ance(s).";
            throw new Exception(erreur);
        }
    }

    public void controleAvantCalcul() throws Exception {
        if (this.getJoursOuvrablesEntreDebutFin().isEmpty()) {
            throw new Exception("La p\u00E9riode choisie ne contient aucun jour ouvrable.");
        }
        int frequence = (int) this.getFrequence();
        if (frequence <= 0) {
            throw new Exception("La fr\u00E9quence doit \u00EAtre au moins 1.");
        }
    }

    public void controleMensuelOuPlus(Connection c) throws Exception {
        int frequence = (int) this.getFrequence();
        int joursOuvrablesUnite = this.getUniteMaintenance().getOuvrable();
        if (this.getJoursOuvrablesEntreDebutFin().size() < joursOuvrablesUnite) {
            throw new Exception(
                "L'intervalle de jours ne correspond pas à l'unit\u00E9 choisie ("
                    + this.getUniteMaintenance().getVal() + ")."
            );
        }
        // Vérifier que la période peut accueillir la fréquence demandée
        if (this.getJoursOuvrablesEntreDebutFin().size() < frequence) {
            throw new Exception(
                "Impossible de planifier " + frequence + " s\u00E9ance(s) entre "
                    + this.getDatedebut() + " et " + this.getDatefin() +"."
            );
        }
    }

    public void controleHebdomadaire(Connection c) throws Exception {

        // Skip weekly pattern validation
        if (this.getUnite().equalsIgnoreCase(ConstanteMaintenance.UNITE_JOUR)) {
            return;
        }

        int frequence = (int) this.getFrequence();

        System.out.println("Frequence " + frequence);
        System.out.println("joursOuvrables size " + this.getJoursOuvrablesEntreDebutFin().size());

        if (frequence <= 0) {
            throw new Exception("La fréquence doit être au moins 1.");
        }

        int size = this.getJoursOuvrablesEntreDebutFin().size();

        System.out.println(this.getDatedebut());
        System.out.println(this.getDatefin());

//        if (size < frequence) {
//            throw new Exception(
//                    "CMP Impossible de planifier " + frequence + " séance(s) entre "
//                            + this.getDatedebut() + " et " + this.getDatefin() + "."
//            );
//        }
    }

    public ArrayList<String> genererDebutCreneaux() {
        String heureDebut = this.getHeure();
        long secondes = Long.parseLong(this.getDuree());
        Duration duree = Duration.ofSeconds(secondes);

        int frequence = (int) this.getFrequence();
        ArrayList<String> sceances = new ArrayList<>();
        LocalTime finJournee = LocalTime.of(17,0);
        LocalTime courant = LocalTime.parse(heureDebut);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

        // calcul temps restant
        Duration tempsRestant = Duration.between(courant, finJournee);
        Duration tempsOccupe = duree.multipliedBy(frequence);
        Duration tempsLibre = tempsRestant.minus(tempsOccupe);

        Duration espacement = Duration.ZERO;
        if(frequence > 1)
            espacement = tempsLibre.dividedBy(frequence - 1);

        // génération
        for(int i=0; i<frequence; i++){
            sceances.add(courant.format(format));
            courant = courant.plus(duree).plus(espacement);
        }
        return sceances;
    }

    public Planning genererPlanningByDateHeure(Date date, String heure) throws Exception {
        Planning p = new Planning();
        p.setIdSource(this.getId());
        p.setDatedebut(date);
        p.setDatefin(date);
        p.setHeure(heure);
        p.setDuree(this.getDuree());
        p.setEstPeriodique(0);
        p.setUnite(this.getUnite());
        p.setFrequence(this.getFrequence());
        p.setIdTypeMaintenance(this.getIdTypeMaintenance());
        p.setIdMachine(this.getIdMachine());
        p.setRefObjet(this.getRefObjet());
        return p;
    }

    public ArrayList<String> findJoursOuvrablesEntreDebutFin() throws Exception {
        String dateDebut = Utilitaire.datetostring(this.getDatedebut());
        String dateFin = Utilitaire.datetostring(this.getDatefin());
        return Utilitaire.genererDatesEntre(dateDebut, dateFin);
    }

    public Planning createPlanningJournalier(String u, Connection c) throws Exception {
        this.controlePlanningJournalier();
        Planning firstPlanning = null;
        ArrayList<String> joursOuvrables = this.getJoursOuvrablesEntreDebutFin();
        for (String date : joursOuvrables) {
            ArrayList<String> sceances = this.genererDebutCreneaux();
            for (int i = 0; i < sceances.size(); i++) {
                Planning p = this.genererPlanningByDateHeure(Utilitaire.stringDate(date), sceances.get(i));
                p.createObject(u,c);
                if (firstPlanning == null){
                    firstPlanning = p;
                }
            }
        }
        return firstPlanning;
    }

    public int calculerIntervalle(Connection c) throws Exception {
        int frequence = (int)this.getFrequence();
        int joursOuvrables = this.getUniteMaintenance().getOuvrable();
        if (frequence > joursOuvrables) {
            throw new Exception("Impossible de planifier " + frequence + " s\u00E9ances dans " + joursOuvrables + " jours ouvrables.");
        }
        int intervalle = joursOuvrables / frequence;
        return Math.max(intervalle, 1);
    }

    public Planning createPlanningMensuelOuPlus(String u, Connection c) throws Exception {
        Planning firstPlanning = null;
        ArrayList<String> joursOuvrables = this.getJoursOuvrablesEntreDebutFin();
        int intervalle = this.calculerIntervalle(c);
        for (int i = 0; i < joursOuvrables.size(); i+=intervalle) {
            String date = joursOuvrables.get(i);
            Planning p = this.genererPlanningByDateHeure(Utilitaire.stringDate(date), this.getHeure());
            p.createObject(u,c);
            if (firstPlanning == null){
                firstPlanning = p;
            }
        }
        return firstPlanning;
    }

    public Planning createPlanningByUnite(String u, Connection c) throws Exception {
        Planning firstPlanning = null;
        this.setUniteMaintenance(this.findEchelle(c));
        this.setJoursOuvrablesEntreDebutFin(this.findJoursOuvrablesEntreDebutFin());
        this.controleAvantCalcul();
        if (this.getUnite().equalsIgnoreCase(ConstanteMaintenance.UNITE_JOUR)) {
            firstPlanning = this.createPlanningJournalier(u,c);
        } else if (this.getUnite().equalsIgnoreCase(ConstanteMaintenance.UNITE_HEBDOMADAIRE))
        {
            this.controleHebdomadaire(c);
            firstPlanning = this.createPlanningMensuelOuPlus(u,c);
        }
        else {
            this.controleMensuelOuPlus(c);
            firstPlanning = this.createPlanningMensuelOuPlus(u,c);
        }
        return firstPlanning;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        if (this.getEstPeriodique() == 1 ) {
            return this.createPlanningByUnite(u,c);
        }
        Planning p = (Planning) super.createObject(u,c);
        if(this.getIdTypeMaintenance()!=null && this.getIdTypeMaintenance().equalsIgnoreCase(ConstanteMaintenance.TypeMaintenanceConsommables)){
            DemandeTravaux dmt= genererTravauxMaintenanceConsommable(p, u, c);
            p.setIdSource(dmt.getId());
            p.validerObject(u,c);
        }
        return p;
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if (c == null) {
                estOuvert = true;
                c = new UtilDB().GetConn();
                c.setAutoCommit(false);
            }
            if (this.getIdSource()==null){
                Planning p = new Planning();
                p.setIdSource(this.getId());
                Planning [] plannings = (Planning[]) CGenUtil.rechercher(p,null,null,c,"");
                for (Planning plan : plannings){
                    plan.validerObject(u,c);
                }
            }
            super.validerObject(u, c);
        } catch (Exception e) {
            if (c != null) {
                c.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && estOuvert == true) {
                c.close();
            }
        }
        return this;
    }
    public DemandeTravauxCpl getDemandeTravaux() throws Exception {
        DemandeTravauxCpl d = (DemandeTravauxCpl) new DemandeTravauxCpl().getById(this.getIdSource(),"",null);
        return d;
    }

    public Planning[] genererPlanningSemaine(String[] jours, String u) throws Exception{
        Connection c = null;
        try{
            c = new UtilDB().GetConn();
            return genererPlanningSemaine(jours, u, c);
        }finally {
            c.close();
        }
    }
    public Planning[] genererPlanningSemaine(String[] jours, String u, Connection c) throws Exception{
        Planning[] plannings = new Planning[0];
        List<Date> dateJours = utils.CalendarUtil.genererDatesParFrequence(getDatedebut(), getDatefin(), jours);
        for(Date date : dateJours){
            Planning copy = (Planning) dupliquerSansBase();
            copy.setDatedebut(date);
            copy.setDatefin(date);
            copy.createObject(u, c);
        }
        return plannings;
    }
    public DemandeTravaux genererDemandeTravaux(Planning planning) throws Exception {
        DemandeTravaux dt = new DemandeTravaux();
        dt.setIdPlanning(planning.getId());
        dt.setIdMachine(planning.getIdMachine());
        dt.setDaty(planning.getDatedebut());
        dt.setDateBesoin(planning.getDatedebut());
        dt.setIdTypeMaintenance(planning.getIdTypeMaintenance());
        dt.setIdSituation(ConstanteMaintenance.situationAutres);
        dt.setIdEntite(ConstanteMaintenance.ENTITE_OUTILS);
        dt.setPriorite(ConstanteMaintenance.prioriteMaintenanceConsommables);
        dt.setDescription("demande de travaux suivant le planning "+planning.getId());
        return dt;
    }
    public DemandeTravaux genererTravauxMaintenanceConsommable(Planning planning,String u,Connection c) throws Exception {
        DemandeTravaux val = new  DemandeTravaux();
        if(this.getIdTypeMaintenance().equalsIgnoreCase(ConstanteMaintenance.TypeMaintenanceConsommables)){
            DemandeTravaux dt = this.genererDemandeTravaux(planning);
            val = (DemandeTravaux) dt.createObject(u,c);
            dt.validerObject(u,c);
        }
        return val;
    }
}
