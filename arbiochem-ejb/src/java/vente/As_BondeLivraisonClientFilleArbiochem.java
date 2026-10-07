package vente;

import bean.ClassFille;

import java.sql.Connection;

public class As_BondeLivraisonClientFilleArbiochem extends As_BondeLivraisonClientFille {
    public As_BondeLivraisonClientFilleArbiochem() throws Exception{
        this.setNomTable("AS_BONDELIVRAISON_CLIENT_FILLE");
        this.setNomClasseMere("vente.As_BondeLivraisonClient");
        setLiaisonMere("numbl");
    }

    public VenteDetailsLibArbiochem genererVenteDetailsLib2(Connection c) throws Exception{
        BonDeCommandeFIlleCplArbiochem detail = (BonDeCommandeFIlleCplArbiochem) new BonDeCommandeFIlleCplArbiochem().getById(this.getIdbc_fille(),"", c);
        VenteDetailsLibArbiochem vnt = detail.createVenteFilleLib2();
        vnt.setQte(this.getQuantite());
        return vnt;
    }

}
