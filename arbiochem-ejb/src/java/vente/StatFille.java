package vente;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ResultatEtSomme;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;

public class StatFille extends ClassMAPTable {

    String id, libelle;
    double qte, montant;
    String idclient, idprovince, idfamille, famille;
    Date daty;

    double remise;

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getIdfamille() {
        return idfamille;
    }

    public void setIdfamille(String idfamille) {
        this.idfamille = idfamille;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public String getIdprovince() {
        return idprovince;
    }

    public void setIdprovince(String idprovince) {
        this.idprovince = idprovince;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public StatFille() {
        this.setNomTable("StatFille");
    }

    @Override
    public String getTuppleID() {
        return this.getId();
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }


    public ResultatEtSomme rechercherPage(String[] colInt, String[]valInt, int numPage, String apresWhere, String[]nomColSomme, Connection c, int npp) throws Exception {

        String daty1 = Utilitaire.dateDuJour();
        String daty2 = Utilitaire.dateDuJour();

        if(valInt != null && valInt.length > 0 && valInt[0].toString().compareToIgnoreCase("null")!=0  && !valInt[0].toString().trim().isEmpty()) {
            daty1 = valInt[0].toString();
        }
        if(valInt != null && valInt.length > 1 && valInt[1].toString().compareToIgnoreCase("null")!=0 && !valInt[1].toString().trim().isEmpty()) {
            daty2 = valInt[1].toString();
        }

        for (int i = 0; i < valInt.length; i++) {
            System.err.println("========================"+valInt[i]+"===============");
        }

        String selectCols = "FAMILLE,IDFAMILLE as IDFAMILLE,IDPRODUIT as id, libelle, sum(qte) as qte, sum(montant) as montant, sum(remise) as remise";
        String groupByCols = "IDPRODUIT, libelle, FAMILLE,IDFAMILLE";

        if(this.getIdclient()!=null && !this.getIdclient().trim().isEmpty()){
            selectCols+=",idclient";
            groupByCols+=",idclient";
        }
        if(this.getIdprovince()!=null && !this.getIdprovince().trim().isEmpty()){
            selectCols+=",idprovince";
            groupByCols+=",idprovince";
        }

        String req = "SELECT " + selectCols +
                " FROM balance_client evd " +
                "WHERE evd.daty BETWEEN '" + daty1 + "' AND '" + daty2 + "' " +
                "GROUP BY " + groupByCols +" order by FAMILLE,libelle";
        System.err.println(req);
        ResultatEtSomme rs = CGenUtil.rechercherPage(this, req, numPage, nomColSomme, apresWhere, c, npp);


        return rs;
    }
}
