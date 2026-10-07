package vente;

import bean.CGenUtil;

public class BonDeCommandeArbiochem extends BonDeCommande{

    public BonDeCommandeArbiochem() {
        setNomTable("BONDECOMMANDE_CLIENT");
        this.setLiaisonFille("idbc");
    }

    public BonDeCommandeFIlleCplArbiochem[] getFilleBCLib2()throws Exception{
        BonDeCommandeFIlleCplArbiochem profD = new BonDeCommandeFIlleCplArbiochem();
        profD.setIdbc(this.getId());
        BonDeCommandeFIlleCplArbiochem[] val= (BonDeCommandeFIlleCplArbiochem[]) CGenUtil.rechercher(profD, null, null, "");
        return val;
    }
}
