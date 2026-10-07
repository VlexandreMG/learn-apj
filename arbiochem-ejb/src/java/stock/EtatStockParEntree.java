package stock;


import bean.CGenUtil;
import java.sql.Connection;
import java.util.Objects;
import java.util.Vector;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

public class EtatStockParEntree extends EtatStockParEntreeStandard {

    public EtatStockParEntree(){
        this.setNomTable("V_ETATSTOCK_ENTREE_STANDARD");
    }

    public TransfertStockDetails toTransfertStock()throws Exception{
        TransfertStockDetails td=new TransfertStockDetails();
        td.setIdProduit(this.getIdProduit());
        td.setIdSource(this.getId());
        td.setQuantite(this.getReste());
        td.setPu(this.getPu());
        return td;
    }
    public static TransfertStockDetails[] toTransfertStock(Connection c,String[] ids) throws Exception {
        boolean canClose=false;
        try{
            if(c==null){
                c=new UtilDB().GetConn();
                canClose=true;
            }
            EtatStockParEntree [] entree = (EtatStockParEntree[]) CGenUtil.rechercher(new EtatStockParEntree(),null,null,c,"  AND id in ("+Utilitaire.tabToString(ids, "'",",")+ ")");
            String idMagasin=null;
            if(entree.length<=0)return null;
            else idMagasin=entree[0].getIdMagasin();
            Vector<TransfertStockDetails> td = new Vector<>(entree.length);
            for(int i=0;i<entree.length;i++){
                if(!Objects.equals(idMagasin, entree[i].getIdMagasin()))throw new Exception("Le magasin de d\u00E9part doit \u00EAtre pareil");
                td.add(entree[i].toTransfertStock());
            }
            return td.toArray(new TransfertStockDetails[0]);
        } catch(Exception e){
            throw e;
        } finally {
            if(canClose){
                c.close();
            }
        }

    }
}
