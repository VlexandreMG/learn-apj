/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vente;

import bean.ClassMAPTable;
import rapprochement.ReleverDetailCpl;
import bean.*;
import java.sql.Connection;
import utilitaire.*;
/**
 *
 * @author Sahy
 */
public class InsertionVente extends Vente{
    String idDevise;

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public InsertionVente() {
        this.setNomTable("INSERTION_VENTE");
    }
    
    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        super.setNomTable("VENTE");
        return super.createObject(u, c);
    }
    public InsertionVente getByIdsRelever(String[] ids, Connection c) throws Exception {
        boolean canClose=false;
        try {
            if(c==null) {
                c=new UtilDB().GetConn();
                canClose=true;
            }
            InsertionVente vente = new InsertionVente();
            ReleverDetailCpl releverDetail = new ReleverDetailCpl();
            releverDetail.setNomTable("ReleverDetailNR");
            String awhereIdRelever = " and id in (" + Utilitaire.tabToString(ids, "'", ",") + ")";
            ReleverDetailCpl[] releverDetailCpls=(ReleverDetailCpl[]) CGenUtil.rechercher(releverDetail,null,null,c,awhereIdRelever);
            if(releverDetailCpls.length>0){
                TypeObjet tpc = new TypeObjet();
                tpc.setNomTable("BANQUE_COMPTA");
                tpc.setDesce(releverDetailCpls[0].getCompte());
                TypeObjet[] banque_compta=(TypeObjet[]) CGenUtil.rechercher(tpc,null,null,c,"");
                if(banque_compta.length>0){
                        vente.setReferencefact(banque_compta[0].getId());
                }
                
            }
            
           return vente;
        } finally {
            if(canClose) c.close();
        }
    }
}
