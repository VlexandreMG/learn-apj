package ferme.pesee;

import bean.ClassFille;
import java.sql.Connection;

public class PeseePoussinDetail extends ClassFille {
    private String id;
    private String idmere;
    private double poids;
    private double nombreoiseaux;

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

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getNombreoiseaux() {
        return nombreoiseaux;
    }

    public void setNombreoiseaux(double nombreoiseaux) {
        this.nombreoiseaux = nombreoiseaux;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.pesee.PeseePoussin";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public PeseePoussinDetail() throws Exception {
        this.setNomTable("PESEEPOUSSINDETAIL");
        this.setNomClasseMere("ferme.pesee.PeseePoussin");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PPD","getseq_peseepoussindetail");
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

