package faturefournisseur;
import bean.LibelleAffichage;

import java.sql.Connection;

public class FFDetailsPerteGain extends FactureFournisseurDetails{
    @LibelleAffichage("Libelle du produit")
    private String libelleProduit;
    public FFDetailsPerteGain() throws Exception{
        super();
    }

    @Override
    public void controlerUpdate(Connection c){
    }
    public int updateObject(String u,Connection c)throws Exception
    {
        RepartirionPG rp=new RepartirionPG();
        rp.setIdFactureFille(this.getId());
        rp.setMontant(this.getMontantPerteGain());
        rp.setIdPerte(this.getIdbcDetail());
        rp.createObject(u, c);
        rp.creerUsage(u,c);
        return 1;
    }
    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }
}
