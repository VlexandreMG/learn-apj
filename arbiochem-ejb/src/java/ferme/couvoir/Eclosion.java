package ferme.couvoir;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class Eclosion extends ClassMere {
    private String id;
    private String idlot;
    private Date dateeclosion;
    private double nombreoeufinitial;
    private String ideclosoir;
    private String idincubateur;
    private String idbatiment;
    private String idparquet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }

    public Date getDateeclosion() {
        return dateeclosion;
    }

    public void setDateeclosion(Date dateeclosion) {
        this.dateeclosion = dateeclosion;
    }

    public double getNombreoeufinitial() {
        return nombreoeufinitial;
    }

    public void setNombreoeufinitial(double nombreoeufinitial) {
        this.nombreoeufinitial = nombreoeufinitial;
    }

    public String getIdeclosoir() {
        return ideclosoir;
    }

    public void setIdeclosoir(String ideclosoir) {
        this.ideclosoir = ideclosoir;
    }

    public String getIdincubateur() {
        return idincubateur;
    }

    public void setIdincubateur(String idincubateur) {
        this.idincubateur = idincubateur;
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



    public Eclosion() throws Exception {
        this.setNomTable("ECLOSION");
        this.setNomClasseFille("ferme.couvoir.EclosionDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ECL","getseq_eclosion");
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

