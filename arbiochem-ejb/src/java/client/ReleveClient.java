package client;

import bean.CGenUtil;
import bean.ClassMAPTable;
import utilitaire.Utilitaire;

import java.sql.Date;

public class ReleveClient extends ClassMAPTable {
    String id, journal, reference, libelle, idclient, clientlib, compte, lettre;
    double debit, credit, solde;
    Date daty;
    int annee;

    public ReleveClient() {
        setNomTable("releveclient");
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    public String getClientlib() {
        return clientlib;
    }

    public void setClientlib(String clientlib) {
        this.clientlib = clientlib;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

//    public String generateQueryCore(Date dateMin, Date dateMax, String idClient) {
//        String safeIdClient = idClient != null ? idClient.replace("'", "''") : "";
//        String dateMinStr = Utilitaire.datetostring(dateMin);
//        String dateMaxStr = Utilitaire.datetostring(dateMax);
//
//        return "SELECT " +
//                "    NULL AS daty, " +
//                "    NULL AS reference, " +
//                "    CASE " +
//                "        WHEN SUM(r.solde) IS NULL THEN 'N/A' " +
//                "        WHEN SUM(r.solde) < 0 THEN 'SOLDE CREDITEUR DU ' || '" + dateMinStr + "' " +
//                "        WHEN SUM(r.solde) > 0 THEN 'SOLDE DEBITEUR DU ' || '" + dateMinStr + "' " +
//                "        ELSE 'SOLDE NUL' " +
//                "    END AS libelle, " +
//                "    CASE " +
//                "        WHEN SUM(r.solde) IS NULL THEN 0 " +
//                "        WHEN SUM(r.solde) < 0 THEN 0 " +
//                "        WHEN SUM(r.solde) > 0 THEN SUM(r.solde) " +
//                "        ELSE 0 " +
//                "    END AS debit, " +
//                "    CASE " +
//                "        WHEN SUM(r.solde) IS NULL THEN 0 " +
//                "        WHEN SUM(r.solde) < 0 THEN ABS(SUM(r.solde)) " +
//                "        WHEN SUM(r.solde) > 0 THEN 0 " +
//                "        ELSE 0 " +
//                "    END AS credit, " +
//                "    '" + safeIdClient + "' as IDCLIENT, " +
//                "    MAX(r.clientlib) as clientlib " +
//                "FROM RELEVECLIENT r " +
//                "WHERE r.DATY <= '" + dateMinStr + "' " +
//                "AND r.IDCLIENT = '" + safeIdClient + "' " +
//                "AND r.DATY IS NOT NULL " +
//                "UNION ALL " +
//                "SELECT " +
//                "    r.daty, " +
//                "    r.reference, " +
//                "    r.libelle, " +
//                "    r.debit, " +
//                "    r.credit, " +
//                "    r.IDCLIENT, " +
//                "    r.clientlib " +
//                "FROM RELEVECLIENT r " +
//                "WHERE r.DATY >= '" + dateMinStr + "' " +
//                "AND r.DATY <= '" + dateMaxStr + "' " +
//                "AND r.IDCLIENT = '" + safeIdClient + "' " +
//                "ORDER BY daty NULLS FIRST, reference";
//    }


    public String generateQueryCore(Date dateMin, Date dateMax, String idClient) {
        String safeIdClient = idClient != null ? idClient.replace("'", "''") : "";
        String dateMinStr = Utilitaire.datetostring(dateMin);
        String dateMaxStr = Utilitaire.datetostring(dateMax);

        String dateMinOra = "TO_DATE('" + dateMinStr + "', 'DD/MM/YYYY')";
        String dateMaxOra = "TO_DATE('" + dateMaxStr + "', 'DD/MM/YYYY')";

        return "WITH base AS ( " +
                "    SELECT /*+ MATERIALIZE */ " +
                "        r.daty, r.reference, r.libelle, r.debit, r.credit, " +
                "        r.IDCLIENT, r.clientlib, r.solde " +
                "    FROM RELEVECLIENT r " +
                "    WHERE r.IDCLIENT = '" + safeIdClient + "' " +
                "    AND r.DATY <= " + dateMaxOra + " " +
                ") " +
                "SELECT " +
                "    NULL AS daty, " +
                "    NULL AS reference, " +
                "    CASE " +
                "        WHEN SUM(solde) IS NULL THEN 'N/A' " +
                "        WHEN SUM(solde) < 0 THEN 'SOLDE CREDITEUR DU ' || '" + dateMinStr + "' " +
                "        WHEN SUM(solde) > 0 THEN 'SOLDE DEBITEUR DU ' || '" + dateMinStr + "' " +
                "        ELSE 'SOLDE NUL' " +
                "    END AS libelle, " +
                "    NVL(GREATEST(SUM(solde), 0), 0) AS debit, " +
                "    NVL(GREATEST(-SUM(solde), 0), 0) AS credit, " +
                "    '" + safeIdClient + "' AS IDCLIENT, " +
                "    MAX(clientlib) AS clientlib " +
                "FROM base " +
                "WHERE daty <= " + dateMinOra + " " +
                "UNION ALL " +
                "SELECT " +
                "    daty, reference, libelle, debit, credit, IDCLIENT, clientlib " +
                "FROM base " +
                "WHERE daty >= " + dateMinOra + " AND daty <= " + dateMaxOra + " " +
                "ORDER BY daty NULLS FIRST, reference";
    }

    public ReleveClient[] relever(Date dateMin, Date dateMax, String idClient)throws Exception{
        String query = generateQueryCore(dateMin, dateMax, idClient);
        System.out.println(query);
        return (ReleveClient[]) CGenUtil.rechercher(this, query);
    }
}
