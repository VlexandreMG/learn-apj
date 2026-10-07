package previsionFerme;

import bean.ClassEtat;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrevisionFerme extends ClassEtat {
    private String id;
    private String designation;
    private String idMagasin;
    private String idMvtStockFille;
    private double pu;
    private String idProduit;
    private double entree;
    private double sortie;
    private Date daty;
    private String idOrigine;
    private String idTiers;

    private double qteInitial;
    private double qteFinal;
    private Date datyDebut;
    private Date datyFin;
    private String grouperPar;

    public String getGrouperPar() {
        return grouperPar;
    }

    public void setGrouperPar(String grouperPar) {
        this.grouperPar = grouperPar;
    }

    public Date getDatyDebut() {
        return datyDebut;
    }

    public void setDatyDebut(Date datyDebut) {
        this.datyDebut = datyDebut;
    }

    public Date getDatyFin() {
        return datyFin;
    }

    public void setDatyFin(Date datyFin) {
        this.datyFin = datyFin;
    }

    public double getQteInitial() {
        return qteInitial;
    }

    public void setQteInitial(double qteInitial) {
        this.qteInitial = qteInitial;
    }

    public double getQteFinal() {
        return qteFinal;
    }

    public void setQteFinal(double qteFinal) {
        this.qteFinal = qteFinal;
    }

    public boolean isSortie() {
        return this.getSortie() > 0 && this.getEntree() <= 0;
    }

    public boolean isEntree() {
        return this.getEntree() > 0 && this.getSortie() <= 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getIdMvtStockFille() {
        return idMvtStockFille;
    }

    public void setIdMvtStockFille(String idMvtStockFille) {
        this.idMvtStockFille = idMvtStockFille;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) throws Exception {
        if(this.getMode().equals("modif") && Utilitaire.champNull(idProduit).isEmpty()){
            throw new Exception("Champ produit obligatoire");
        }
        this.idProduit = idProduit;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
    
    public String getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(String idOrigine) {
        this.idOrigine = idOrigine;
    }

    public String getIdTiers() {
        return idTiers;
    }

    public void setIdTiers(String idTiers) {
        this.idTiers = idTiers;
    }

    public PrevisionFerme() {
        this.setNomTable("PREVISIONFERME");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PRF","getSeqPrevisionFerme");
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

    public double calculerQteFinale(){
        double qte = this.getQteInitial() + this.getEntree() - this.getSortie();
        this.setQteFinal(qte);
        return this.getQteFinal();
    }
    
    public String getDatyG(){
        return getDaty().toString();
    }

    public String getDatyGsemaine(){
        // Use Calendar to get the week information
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDaty());
        // Set the calendar to the start of the week (optional: change to Monday or Sunday based on locale)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Set to the first day of the week (Monday)
        // Get the first day of the week
        java.util.Date weekStartDate = calendar.getTime();
        // Use SimpleDateFormat to format the week string
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy"); // Format as "6 April"
        String weekStartString = dateFormat.format(weekStartDate);
        return "Semaine du " + weekStartString;
    }

    public String getDatyGmois(){
        // Use SimpleDateFormat to format the month string
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy");  // "MMMM" gives full month name (e.g., "April")
        // Format the date to get the month
        return monthFormat.format(getDaty());
    }
}

