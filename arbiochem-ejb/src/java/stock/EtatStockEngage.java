package stock;

import bean.CGenUtil;
import bean.ResultatEtSomme;

import java.sql.Connection;

public class EtatStockEngage extends EtatStock {
    private String libellevente;
    private double physique;
    private double engage;
    private double afacturer;

    public String getLibellevente() {
        return libellevente;
    }

    public void setLibellevente(String libellevente) {
        this.libellevente = libellevente;
    }

    @Override
    public ResultatEtSomme rechercherPage(String[] colInt, String[] valInt, int numPage, String apresWhere, String[] nomColSomme, Connection c, int npp) throws Exception {
        ResultatEtSomme rs = CGenUtil.rechercherPage(this,colInt, valInt,numPage,apresWhere,nomColSomme,c,npp);
        return rs;
    }

    public double getPhysique() {
        return physique;
    }

    public void setPhysique(double physique) {
        this.physique = physique;
    }

    public double getEngage() {
        return engage;
    }

    public void setEngage(double engage) {
        this.engage = engage;
    }

    public double getAfacturer() {
        return afacturer;
    }

    public void setAfacturer(double afacturer) {
        this.afacturer = afacturer;
    }
}