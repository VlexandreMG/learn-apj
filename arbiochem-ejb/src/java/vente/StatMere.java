package vente;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ResultatEtSomme;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;

public class StatMere extends ClassMAPTable {
    String id, famille;
    double qte, montant;
    String idclient, idprovince;
    Date daty;

    public String getIdprovince() {
        return idprovince;
    }

    public void setIdprovince(String idprovince) {
        this.idprovince = idprovince;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String getTuppleID() {
        return this.getId();
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public StatMere() {
        this.setNomTable("StatMere");
    }

    public ResultatEtSomme rechercherPage(String[] colInt, String[]valInt, int numPage, String apresWhere, String[]nomColSomme, Connection c, int npp) throws Exception {

        String daty1 = Utilitaire.dateDuJour();
        String daty2 = Utilitaire.dateDuJour();

        if(valInt != null && valInt.length > 0) {
            daty1 = valInt[0].toString();
        }
        if(valInt != null && valInt.length > 1) {
            daty2 = valInt[1].toString();
        }

        String selectCols = "IDFAMILLE as id, famille, sum(qte) as qte, sum(montant) as montant";
        String groupByCols = "IDFAMILLE, famille";
        if(this.getIdclient()!=null){
            selectCols = selectCols+ ", IDCLIENT";
            groupByCols = groupByCols+ ", IDCLIENT";
        }

        if(this.getIdprovince()!=null){
            selectCols = selectCols+ ", IDPROVINCE";
            groupByCols = groupByCols+ ", IDPROVINCE";
        }

        String req = "SELECT " + selectCols +
                " FROM balance_client evd " +
                "WHERE evd.daty BETWEEN '" + daty1 + "' AND '" + daty2 + "' " +
                "GROUP BY " + groupByCols +" order by famille";

        ResultatEtSomme rs = CGenUtil.rechercherPage(this, req, numPage, nomColSomme, apresWhere, c, npp);

        System.err.println(req);
        return rs;
    }
}
