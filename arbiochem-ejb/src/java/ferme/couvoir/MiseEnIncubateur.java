package ferme.couvoir;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class MiseEnIncubateur extends ClassMere {
    private String id;
    private Date datemiseenmachine;
    private Date dateeclosionprevue;
    private String idincubateur;
    private String idresponsable;
    private double temperateurcible;
    private double humiditecible;
    private String heureprechauffage;
    private String heuredebutincubation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatemiseenmachine() {
        return datemiseenmachine;
    }

    public void setDatemiseenmachine(Date datemiseenmachine) {
        this.datemiseenmachine = datemiseenmachine;
    }

    public Date getDateeclosionprevue() {
        return dateeclosionprevue;
    }

    public void setDateeclosionprevue(Date dateeclosionprevue) {
        this.dateeclosionprevue = dateeclosionprevue;
    }

    public String getIdincubateur() {
        return idincubateur;
    }

    public void setIdincubateur(String idincubateur) {
        this.idincubateur = idincubateur;
    }

    public String getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(String idresponsable) {
        this.idresponsable = idresponsable;
    }

    public double getTemperateurcible() {
        return temperateurcible;
    }

    public void setTemperateurcible(double temperateurcible) {
        this.temperateurcible = temperateurcible;
    }

    public double getHumiditecible() {
        return humiditecible;
    }

    public void setHumiditecible(double humiditecible) {
        this.humiditecible = humiditecible;
    }

    public String getHeureprechauffage() {
        return heureprechauffage;
    }

    public void setHeureprechauffage(String heureprechauffage) {
        this.heureprechauffage = heureprechauffage;
    }

    public String getHeuredebutincubation() {
        return heuredebutincubation;
    }

    public void setHeuredebutincubation(String heuredebutincubation) {
        this.heuredebutincubation = heuredebutincubation;
    }



    public MiseEnIncubateur() throws Exception {
        this.setNomTable("MISEENINCUBATEUR");
        this.setNomClasseFille("ferme.couvoir.MiseEnIncubateurDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MEI","getseq_miseenincubateur");
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

