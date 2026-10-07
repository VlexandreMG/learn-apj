package vente;

import bean.CGenUtil;

import java.sql.Connection;

public class As_BondeLivraisonClientArbiochem extends As_BondeLivraisonClient{
    public As_BondeLivraisonClientArbiochem() throws Exception{
        this.setNomTable("AS_BONDELIVRAISON_CLIENT");
        setNomClasseFille("vente.As_BondeLivraisonClientFille");
        setLiaisonFille("numbl");
    }

    public VenteDetailsLibArbiochem[] getListeVenteDetailsLib2(String nTBLFille, Connection c)throws Exception{
        As_BondeLivraisonClientFilleArbiochem crt = new As_BondeLivraisonClientFilleArbiochem();
        crt.setNumbl(this.getId());
        if(nTBLFille!=null && nTBLFille.compareTo("")!=0){
            crt.setNomTable(nTBLFille);
        }
        As_BondeLivraisonClientFilleArbiochem[] blf = (As_BondeLivraisonClientFilleArbiochem[]) CGenUtil.rechercher(crt, null,null,c,"");
        VenteDetailsLibArbiochem[] v = new VenteDetailsLibArbiochem[blf.length];
        for(int i =0; i<blf.length;i++){
            v[i]=blf[i].genererVenteDetailsLib2(c);
        }
        return v;
    }
}
