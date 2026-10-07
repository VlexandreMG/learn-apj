package mg.cnaps.compta;

import java.sql.Connection;
import java.util.LinkedHashMap;

import bean.AdminGen;
import bean.CGenUtil;
import bean.ClassMAPTable;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

public class ComptaEtatGrandLivreGenerator extends ClassMAPTable {
    String id ;


    int exercice;
    String typeCompte;
    int moisDebut;
    int moisFin;
    String compteDebut;
    String compteFin;
    String compteDebutOriginal;
    String compteFinOriginal;
    int etat;
    String dateDebut ;
    String dateFin ;
    LinkedHashMap<String,ComptaCompte> comptes = new LinkedHashMap<String,ComptaCompte>();
    ComptaCompte[] compteArray;
    

    public String getId() {
        return id;
    }

    public String getDateDebut() {
        return "01/01/" + this.getExercice();
    }

    public String getDateFin() {
        return Utilitaire.formatterDaty(Utilitaire.getLastDayOfDate("01/" + Utilitaire.completerInt(2, this.getMoisFin()) + "/" + this.getExercice()));
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getExercice() {
        return exercice;
    }

    public void setExercice(int exercice) {
        this.exercice = exercice;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public int getMoisDebut() {
        return moisDebut;
    }

    public void setMoisDebut(int moisDebut) {
        this.moisDebut = moisDebut;
    }

    public int getMoisFin() {
        return moisFin;
    }

    public void setMoisFin(int moisFin) {
        this.moisFin = moisFin;
    }

    public String getCompteDebut() {
        return compteDebut;
    }

    public void setCompteDebut(String compteDebut) {
        if (compteDebut!=null&&!compteDebut.isEmpty()){
            this.compteDebutOriginal = compteDebut;
            this.compteDebut = Utilitaire.completerIntFin(ConstanteCompta.constante_compte,compteDebut, 0);
            return;
        }
        this.compteDebut = compteDebut;
        this.compteDebutOriginal = compteDebut;
    }

    public String getCompteFin() {
        return compteFin;
    }

    public void setCompteFin(String compteFin) {
        if (compteFin!=null&&!compteFin.isEmpty()){
            this.compteFinOriginal = compteFin;
            this.compteFin = Utilitaire.completerIntFin(ConstanteCompta.constante_compte,compteFin,0);
            return;
        }
        this.compteFin = compteFin;
        this.compteFinOriginal = compteFin;
    }

    /**
     * Normalise les comptes : si debut == fin, utilise la valeur originale non formatée
     */
    public void normalizeComptes() {
        if (this.compteDebutOriginal != null && this.compteFinOriginal != null) {
            if (this.compteDebutOriginal.equals(this.compteFinOriginal)) {
                // Si les deux comptes originaux sont identiques, utiliser la valeur originale
                this.compteDebut = this.compteDebutOriginal;
                this.compteFin = this.compteFinOriginal;
            }
        }
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public LinkedHashMap<String, ComptaCompte> getComptes() {
        return comptes;
    }

    public void setComptes(LinkedHashMap<String, ComptaCompte> comptes) {
        this.comptes = comptes;
    }

    public ComptaCompte[] getCompteArray() {
        return compteArray;
    }

    public void setCompteArray(ComptaCompte[] compteArray) {
        this.compteArray = compteArray;
    }

    public ReportSolde[] getReports(Connection c)throws Exception{
        try{
            ReportSolde report = new ReportSolde();
            report.setNomTableSelonTypeCompte(Integer.valueOf(typeCompte));
            String[] colInt = {"mois","annee","compte"};
            String[] valInt = {String.valueOf(moisDebut-1),String.valueOf(moisDebut-1),String.valueOf(exercice),String.valueOf(exercice),compteDebut,compteFin};
            String ordre = " ORDER BY compte ASC";
            if (this.getMoisDebut() == 1) {
                report.setNomTable("REPORTSOLDE_JANVIER");
                colInt = new String[] { "annee", "typecompte","compte"};
                valInt = new String[] { String.valueOf(exercice), String.valueOf(exercice), typeCompte, typeCompte,compteDebut,compteFin };
            }
            return (ReportSolde[])CGenUtil.rechercher(report,colInt,valInt,c,ordre);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public ComptaSousEcriture[] getMouvements(Connection c)throws Exception{
        try{
            ComptaSousEcritureLib ecriture = new ComptaSousEcritureLib();
            ecriture.setNomTableSelonEtatEtTypeCompte(etat, Integer.valueOf(typeCompte));
           // String dateMin = Utilitaire.moisEtAnneeToDate2(moisDebut-1,exercice,"min");
           // String dateMax = Utilitaire.moisEtAnneeToDate2(moisFin-1,exercice,"max");
           
            String[] colInt = {"compteInt"};
            String[] valInt = {compteDebut,compteFin};
            String ordre =  " AND COMPTE BETWEEN '" + compteDebutOriginal + "' AND '" + compteFinOriginal + "' and  daty>= TO_DATE('"+dateDebut+"', 'YYYY-MM-DD') and daty<= TO_DATE('"+dateFin+"', 'YYYY-MM-DD') ORDER BY compte ASC";
            ComptaSousEcriture[] val = (ComptaSousEcriture[])CGenUtil.rechercher(ecriture,null,null,c,ordre);          
            return val;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public LinkedHashMap<String,ComptaCompte> getComptes(Connection c)throws Exception{
        try{
            ComptaCompteCpl compte = new ComptaCompteCpl();
            compte.setNomTable("v_compta_compte");
            String[] colInt = {"compteInt"};
            String[] valInt = {compteDebut,compteFin};
            String ordre =  " AND COMPTE BETWEEN '" + compteDebutOriginal + "' AND '" + compteFinOriginal + "' ORDER BY compte ASC";

            setCompteArray((ComptaCompte[])CGenUtil.rechercher(compte,null,null,c,ordre));


            for(int i=0;i<compteArray.length;i++){
                this.comptes.put(((ComptaCompteCpl)compteArray[i]).getCompte(),compteArray[i]);
            }

            return this.comptes;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void fillComptesWithMouvementAndReport(Connection c)throws Exception{
        int verif=0;
        try{
            if(c==null){
                c = new UtilDB().GetConn();
                verif = 1;
            }
            getComptes(c);
            ReportSolde[] report = getReports(c);
            ComptaSousEcriture[] mouvements = getMouvements(c);
            for(int i=0;i<report.length;i++){
                ComptaCompte comptacompte = this.comptes.get(report[i].getCompte());
                comptacompte.addReport(report[i]);
            }
            for(int i=0;i<mouvements.length;i++){
                ComptaCompte comptacompte = this.comptes.get(mouvements[i].getCompte());
                comptacompte.addMouvement(mouvements[i]);
            }

        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            if(c!=null && verif==1)c.close();
        }
    }

    public double getTotalDebit(){
        return AdminGen.calculSommeDouble(compteArray,"mouvementDebit");
    }

    public double getTotalCredit(){
        return AdminGen.calculSommeDouble(compteArray,"mouvementCredit");
    }

    public double getSoldeCredit(){
        return Math.max(getTotalCredit()-getTotalDebit(),0);
    }

    public double getSoldeDebit(){
        return Math.max(getTotalDebit()-getTotalCredit(),0);
    }

    @Override
    public String getTuppleID() {
        // TODO Auto-generated method stub
        return getId();
    }

    @Override
    public String getAttributIDName() {
        // TODO Auto-generated method stub
        return "id";
    }
}
