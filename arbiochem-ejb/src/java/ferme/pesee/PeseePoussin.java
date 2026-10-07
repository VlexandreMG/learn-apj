package ferme.pesee;

import bean.ClassMere;
import ferme.utils.ConfigPoids;

import java.sql.Connection;
import java.sql.Date;

public class PeseePoussin extends ClassMere {
    private String id;
    private Date datepesee;
    private String idtypepesee;
    private String idresponsable;
    private String idferme;
    private String idbatiment;
    private String idlot;
    private String idparquet;
    private String idsexe;
    private String remarque;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatepesee() {
        return datepesee;
    }

    public void setDatepesee(Date datepesee) {
        this.datepesee = datepesee;
    }

    public String getIdtypepesee() {
        return idtypepesee;
    }

    public void setIdtypepesee(String idtypepesee) {
        this.idtypepesee = idtypepesee;
    }

    public String getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(String idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getIdferme() {
        return idferme;
    }

    public void setIdferme(String idferme) {
        this.idferme = idferme;
    }

    public String getIdbatiment() {
        return idbatiment;
    }

    public void setIdbatiment(String idbatiment) {
        this.idbatiment = idbatiment;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }

    public String getIdparquet() {
        return idparquet;
    }

    public void setIdparquet(String idparquet) {
        this.idparquet = idparquet;
    }

    public String getIdsexe() {
        return idsexe;
    }

    public void setIdsexe(String idsexe) {
        this.idsexe = idsexe;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }



    public PeseePoussin() throws Exception {
        this.setNomTable("PESEEPOUSSIN");
        this.setNomClasseFille("ferme.pesee.PeseePoussinDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PP","getseq_peseepoussin");
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

    public PeseePoussinDetail[] transformerToDetail(ConfigPoids[] configPoids) throws Exception {
        PeseePoussinDetail[] peseePoussinDetails = new PeseePoussinDetail[configPoids.length];
        for (int i = 0; i < configPoids.length; i++) {
            PeseePoussinDetail peseePoussinDetail = new PeseePoussinDetail();
            peseePoussinDetail.setPoids(configPoids[i].getPoidsmin());
            peseePoussinDetails[i] = peseePoussinDetail;
        }
        return peseePoussinDetails;
    }
}

