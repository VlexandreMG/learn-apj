package ferme.couvoir;

import bean.ClassFille;
import java.sql.Connection;

public class TriageQualitePoussinDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idbatiment;
    private String idparquet;
    private String idqualite;
    private int qte;

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

    public String getIdqualite() {
        return idqualite;
    }

    public void setIdqualite(String idqualite) {
        this.idqualite = idqualite;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.couvoir.TriageQualitePoussin";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public TriageQualitePoussinDetail() throws Exception {
        this.setNomTable("TRIAGEQUALITEPOUSSINDETAIL");
        this.setNomClasseMere("ferme.couvoir.TriageQualitePoussin");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TRPD","getseqtriageQualPoussinDetail");
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

