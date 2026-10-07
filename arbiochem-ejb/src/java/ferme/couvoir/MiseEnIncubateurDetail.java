package ferme.couvoir;

import bean.ClassFille;
import java.sql.Connection;
import java.sql.Date;

public class MiseEnIncubateurDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idlot;
    private String idbatiment;
    private String idparquet;
    private String heuredemarrage;
    private String heurefin;
    private Date dateponte;
    private double qte;
    private double poidsmoyen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }

    public String getIdbatiment() {
        return idbatiment;
    }

    public void setIdbatiment(String idbatiment) {
        this.idbatiment = idbatiment;
    }

    public String getIdparquet() {
        return idparquet;
    }

    public void setIdparquet(String idparquet) {
        this.idparquet = idparquet;
    }

    public String getHeuredemarrage() {
        return heuredemarrage;
    }

    public void setHeuredemarrage(String heuredemarrage) {
        this.heuredemarrage = heuredemarrage;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heurefin;
    }

    public Date getDateponte() {
        return dateponte;
    }

    public void setDateponte(Date dateponte) {
        this.dateponte = dateponte;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPoidsmoyen() {
        return poidsmoyen;
    }

    public void setPoidsmoyen(double poidsmoyen) {
        this.poidsmoyen = poidsmoyen;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.couvoir.MiseEnIncubateur";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public MiseEnIncubateurDetail() throws Exception {
        this.setNomTable("MISEENINCUBATEURDETAIL");
        this.setNomClasseMere("ferme.couvoir.MiseEnIncubateur");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MEID","getseq_miseenincubateurdetail");
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
}

