package paie.formation;

import bean.ClassMAPTable;

import java.sql.Connection;

public class CoutFormation extends ClassMAPTable {
    private String id;
    private String idactionformation;
    private double coutpedagogiquehoraire;
    private double coutpedagogiquetotal;
    private double coutdeplacement;
    private double couthebergement;
    private double coutrestauration;
    private double autrecout;
    private double coutparstagiaire;
    private double totalheurestagiaire;
    private String iddevise;
    private double coutindirecte;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdactionformation() {
        return idactionformation;
    }

    public void setIdactionformation(String idactionformation) {
        this.idactionformation = idactionformation;
    }

    public double getCoutpedagogiquehoraire() {
        return coutpedagogiquehoraire;
    }

    public void setCoutpedagogiquehoraire(double coutpedagogiquehoraire) {
        this.coutpedagogiquehoraire = coutpedagogiquehoraire;
    }

    public double getCoutpedagogiquetotal() {
        return coutpedagogiquetotal;
    }

    public void setCoutpedagogiquetotal(double coutpedagogiquetotal) {
        this.coutpedagogiquetotal = coutpedagogiquetotal;
    }

    public double getCoutdeplacement() {
        return coutdeplacement;
    }

    public void setCoutdeplacement(double coutdeplacement) {
        this.coutdeplacement = coutdeplacement;
    }

    public double getCouthebergement() {
        return couthebergement;
    }

    public void setCouthebergement(double couthebergement) {
        this.couthebergement = couthebergement;
    }

    public double getCoutrestauration() {
        return coutrestauration;
    }

    public void setCoutrestauration(double coutrestauration) {
        this.coutrestauration = coutrestauration;
    }

    public double getAutrecout() {
        return autrecout;
    }

    public void setAutrecout(double autrecout) {
        this.autrecout = autrecout;
    }

    public double getCoutparstagiaire() {
        return coutparstagiaire;
    }

    public void setCoutparstagiaire(double coutparstagiaire) {
        this.coutparstagiaire = coutparstagiaire;
    }

    public double getTotalheurestagiaire() {
        return totalheurestagiaire;
    }

    public void setTotalheurestagiaire(double totalheurestagiaire) {
        this.totalheurestagiaire = totalheurestagiaire;
    }

    public String getIddevise() {
        return iddevise;
    }

    public void setIddevise(String iddevise) {
        this.iddevise = iddevise;
    }



    public CoutFormation() throws Exception {
        this.setNomTable("COUT_FORMATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CFR","GET_SEQ_COUT_FORMATION");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public double getCoutindirecte() {
        return coutindirecte;
    }

    public void setCoutindirecte(double coutindirecte) {
        this.coutindirecte = coutindirecte;
    }
    
}

