package stat;

import bean.CGenUtil;
import bean.ResultatEtSomme;

import java.sql.Connection;

public class EtatStockProduitFiniCatPourcentage extends EtatStockProduitFini {
    private double pourcentage;

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public EtatStockProduitFiniCatPourcentage() {
        this.setNomTable("VESTOCKPRODFINI_CAT_PCENTAGE");
    }

    @Override
    public ResultatEtSomme rechercherPage(String[] colInt, String[]valInt, int numPage, String apresWhere, String[]nomColSomme, Connection c, int npp) throws Exception {
        return CGenUtil.rechercherPage(this, colInt, valInt, numPage, apresWhere, nomColSomme, c, npp);
    }
}
