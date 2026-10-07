package ferme.production;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class TriageOeuf extends ClassMere {
    private String id;
    private Date daty;
    private String heuredepart;
    private String idoperateur;
    private String idnumerocollecte;
    private String idbatiment;
    private String idparquet;
    private double oeuftotal;
    private double poidsmoyenoeufs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(String heuredepart) {
        this.heuredepart = heuredepart;
    }

    public String getIdoperateur() {
        return idoperateur;
    }

    public void setIdoperateur(String idoperateur) {
        this.idoperateur = idoperateur;
    }

    public String getIdnumerocollecte() {
        return idnumerocollecte;
    }

    public void setIdnumerocollecte(String idnumerocollecte) {
        this.idnumerocollecte = idnumerocollecte;
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

    public double getOeuftotal() {
        return oeuftotal;
    }

    public void setOeuftotal(double oeuftotal) {
        this.oeuftotal = oeuftotal;
    }

    public double getPoidsmoyenoeufs() {
        return poidsmoyenoeufs;
    }

    public void setPoidsmoyenoeufs(double poidsmoyenoeufs) {
        this.poidsmoyenoeufs = poidsmoyenoeufs;
    }



    public TriageOeuf() throws Exception {
        this.setNomTable("TRIAGEOEUF");
        this.setNomClasseFille("ferme.production.TriageOeufDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TRO","getseq_triageoeuf");
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

