package proforma;

import bean.CGenUtil;

public class ProformaArbichem extends Proforma{
    public ProformaArbichem()throws Exception{
        this.setNomTable("PROFORMA");
        this.setLiaisonFille("idProforma");
        this.setNomClasseFille("proforma.ProformaDetails");
    }

    public ProformaDetailsLibArbiochem[] getFilleProformaLib2()throws Exception{
        ProformaDetailsLibArbiochem profD = new ProformaDetailsLibArbiochem();
        profD.setIdProforma(this.getId());
        ProformaDetailsLibArbiochem[] val= (ProformaDetailsLibArbiochem[]) CGenUtil.rechercher(profD, null, null, "");
        return val;
    }
}
