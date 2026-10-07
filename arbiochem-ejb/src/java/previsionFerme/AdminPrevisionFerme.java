package previsionFerme;

import bean.CGenUtil;
import stock.EtatStock;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import java.sql.Connection;
import java.sql.Date;

public class AdminPrevisionFerme {
    PrevisionFerme[] listePrev;
    PrevisionFerme minimum;
    PrevisionFerme previsionInfo;

    public AdminPrevisionFerme() {}

    public AdminPrevisionFerme(PrevisionFerme prevision) {
        this.previsionInfo = prevision;
    }

    public PrevisionFerme getPrevisionInfo() {
        return previsionInfo;
    }

    public void setPrevisionInfo(PrevisionFerme previsionInfo) {
        this.previsionInfo = previsionInfo;
    }

    public void setPrevision(PrevisionFerme previsionInfo) {
        this.previsionInfo = previsionInfo;
    }

    public PrevisionFerme[] getListePrev() {
        return listePrev;
    }

    public void setListePrev(PrevisionFerme[] listePrev) {
        this.listePrev = listePrev;
    }

    public PrevisionFerme getMinimum() {
        return minimum;
    }

    public void setMinimum(PrevisionFerme minimum) {
        this.minimum = minimum;
    }


    public double getResteStock(Connection c) throws Exception {
        String daty = Utilitaire.datetostring(this.getPrevisionInfo().getDatyDebut());
        Date date = Utilitaire.ajoutJourDate(Utilitaire.string_date("dd/MM/yyyy", daty), -1);
        String req = EtatStock.getReqEtatStock(Utilitaire.datetostring(date));
        String aWhere = " where idProduit = '"+this.getPrevisionInfo().getIdProduit()+"'";
        if (!Utilitaire.champNull(this.getPrevisionInfo().getIdMagasin()).isEmpty()) {
            aWhere = aWhere + " AND idMagasin = '"+this.getPrevisionInfo().getIdMagasin()+"'";
        }
        req = "select * from ("+req+") "+aWhere;
        EtatStock[] etatStock = (EtatStock[]) CGenUtil.rechercher(new EtatStock(), req ,c);
        if (etatStock.length > 0) {
            return etatStock[0].getReste();
        }
        return 0;
    }

public String getRequete() {
    String grouper = this.getPrevisionInfo().getGrouperPar();
    String debut = Utilitaire.datetostring(this.getPrevisionInfo().getDatyDebut());
    String daty = Utilitaire.datetostring(this.getPrevisionInfo().getDaty());
    String fin = Utilitaire.datetostring(this.getPrevisionInfo().getDatyFin());

    String idProduit = this.getPrevisionInfo().getIdProduit();
    String idMagasin = this.getPrevisionInfo().getIdMagasin();
    boolean filtreMagasin = !Utilitaire.champNull(idMagasin).isEmpty();

    String jointureMf = " AND mf.idProduit = '" + idProduit + "'";
    String jointureP = " AND p.idProduit = '" + idProduit + "'";
    String selIdMagasin = "NULL AS idmagasin";
    if (filtreMagasin) {
        jointureMf = jointureMf + " AND mf.idMagasin = '" + idMagasin + "'";
        jointureP = jointureP + " AND p.idMagasin = '" + idMagasin + "'";
        selIdMagasin = "'" + idMagasin + "' AS idmagasin";
    }

    String req = "SELECT\n" +
        "    '" + idProduit + "' AS idproduit,\n" +
        "    " + selIdMagasin + ",\n" +
        "    t.daty,\n" +
        "    NVL(SUM(mf.entree),0) AS entree,\n" +
        "    NVL(SUM(mf.sortie),0) AS sortie,\n" +
        "    CAST(TO_CHAR(t.daty,'IW') AS INTEGER) AS semaine,\n" +
        "    EXTRACT(MONTH FROM t.daty) AS mois,\n" +
        "    EXTRACT(YEAR FROM t.daty) AS annee\n" +
        "FROM TOUSLESDATE t\n" +
        "LEFT JOIN MvtStockFilleParDate mf\n" +
        "    ON mf.daty = t.daty\n" +
        "   " + jointureMf + "\n" +
        "WHERE t.daty >= TO_DATE('" + debut + "','DD/MM/YYYY')\n" +
        "  AND t.daty < TO_DATE('" + daty + "','DD/MM/YYYY')\n" +
        "GROUP BY t.daty, CAST(TO_CHAR(t.daty,'IW') AS INTEGER), EXTRACT(MONTH FROM t.daty), EXTRACT(YEAR FROM t.daty)\n" +
        "UNION ALL (\n" +
        "    SELECT\n" +
        "        '" + idProduit + "' AS idproduit,\n" +
        "        " + selIdMagasin + ",\n" +
        "        t.daty,\n" +
        "        NVL(SUM(p.entree),0) AS entree,\n" +
        "        NVL(SUM(p.sortie),0) AS sortie,\n" +
        "        CAST(TO_CHAR(t.daty,'IW') AS INTEGER) AS semaine,\n" +
        "        EXTRACT(MONTH FROM t.daty) AS mois,\n" +
        "        EXTRACT(YEAR FROM t.daty) AS annee\n" +
        "    FROM TOUSLESDATE t\n" +
        "    LEFT JOIN PREVISIONFERME p\n" +
        "        ON p.daty = t.daty\n" +
        "       " + jointureP + "\n" +
        "    WHERE t.daty >= TO_DATE('" + daty + "','DD/MM/YYYY')\n" +
        "      AND t.daty <= TO_DATE('" + fin + "','DD/MM/YYYY')\n" +
        "    GROUP BY t.daty, CAST(TO_CHAR(t.daty,'IW') AS INTEGER), EXTRACT(MONTH FROM t.daty), EXTRACT(YEAR FROM t.daty)\n" +
        ")";

    if (grouper != null && grouper.equalsIgnoreCase("semaine")) {
        req = "SELECT MIN(daty) AS daty, SUM(entree) AS entree, SUM(sortie) AS sortie FROM (" + req + ") GROUP BY semaine";
    }
    if (grouper != null && grouper.equalsIgnoreCase("mois")) {
        req = "SELECT MIN(daty) AS daty, SUM(entree) AS entree, SUM(sortie) AS sortie FROM (" + req + ") GROUP BY mois, annee";
    }
    return req + " ORDER BY DATY ";
}

    public void getPrevision(Connection c) throws Exception {
        boolean canClose = false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                canClose = true;
            }
            PrevisionFerme[] previsions = (PrevisionFerme[]) CGenUtil.rechercher(new PrevisionFerme(), this.getRequete());
            this.setListePrev(previsions);
            this.setMinimum(previsions[0]);
            previsions[0].setQteInitial(this.getResteStock(c));
            previsions[0].calculerQteFinale();
            for (int i = 1; i < this.getListePrev().length; i++) {
                previsions[i].setQteInitial(previsions[i-1].getQteFinal());
                previsions[i].calculerQteFinale();
                if(this.getMinimum().getQteFinal() > previsions[i].getQteFinal()) this.setMinimum(previsions[i]);
            }
        } finally {
            if (canClose) c.close();
        }
    }
}
