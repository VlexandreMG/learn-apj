package caisse;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class OuvertureCaisse extends ClassMere {

    private String id;
    private Date daty;
    private String heure;
    private String idPoint;
    private String remarque;

    public OuvertureCaisse() throws Exception {
        this.setNomTable("OUVERTURECAISSE");
        this.setLiaisonFille("idOuverture");
        this.setNomClasseFille("caisse.OuvertureCaisseFille");
    }

    public  String getNomClasseFille()
    {
        return "caisse.OuvertureCaisseFille";
    }

    public String getLiaisonFille() {
        return "idOuverture";
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OVC", "getseq_ouvcaisse");
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(String idPoint) {
        this.idPoint = idPoint;
    }


    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
