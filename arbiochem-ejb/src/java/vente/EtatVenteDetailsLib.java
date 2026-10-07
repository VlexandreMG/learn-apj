package vente;

import bean.CGenUtil;
import bean.ResultatEtSomme;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;
public class EtatVenteDetailsLib extends VenteDetailsLib{
    String idClient;
    String idProvince;

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }

    public EtatVenteDetailsLib() {
        this.setNomTable("etat_VENTE_DETAILS_POIDS");
    }

//    public ResultatEtSomme rechercherPage(String[] colInt, String[]valInt, int numPage, String apresWhere, String[]nomColSomme, Connection c, int npp) throws Exception {
//        String daty1 = Utilitaire.dateDuJour();
//        String daty2 = Utilitaire.dateDuJour();
//        if(valInt != null && valInt.length > 0) {
//            daty1 = valInt[0].toString();
//        }
//        if(valInt != null && valInt.length > 1) {
//            daty2 = valInt[1].toString();
//        }
//
//        Date datesql = Utilitaire.stringDate(daty2);
//        String selectCols = "evd.IDPRODUIT, evd.IDPRODUITLIB, SUM(evd.qte) AS qte, SUM(evd.montant) AS montant, '" + datesql + "' AS daty , evd.IDCLIENT";
//        String groupByCols = "evd.IDPRODUIT, evd.IDPRODUITLIB, evd.IDCLIENT";
//
//
//        String req = "SELECT " + selectCols +
//                     " FROM etat_VENTE_DETAILS_POIDS evd " +
//                     "WHERE evd.daty BETWEEN '" + daty1 + "' AND '" + daty2 + "' " +
//                     "GROUP BY " + groupByCols;
//        ResultatEtSomme rs = CGenUtil.rechercherPage(this, req, numPage, nomColSomme, apresWhere, c, npp);
//        return rs;
//    }
}
