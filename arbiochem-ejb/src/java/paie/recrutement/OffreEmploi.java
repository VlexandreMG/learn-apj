package paie.recrutement;

import bean.CGenUtil;
import bean.ClassEtat;
import poste.FichePoste;

import java.sql.Connection;
import java.sql.Date;

public class OffreEmploi extends ClassEtat {
    private String id;
    private String idficheposte;
    private String titre;
    private String description;
    private String mission;
    private String exigenceposte;
    private String idtypecontrat;
    private double salairemin;
    private double salairemax;
    private Date datepublication;
    private Date datefermeture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdficheposte() {
        return idficheposte;
    }

    public void setIdficheposte(String idficheposte) {
        this.idficheposte = idficheposte;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getExigenceposte() {
        return exigenceposte;
    }

    public void setExigenceposte(String exigenceposte) {
        this.exigenceposte = exigenceposte;
    }

    public String getIdtypecontrat() {
        return idtypecontrat;
    }

    public void setIdtypecontrat(String idtypecontrat) {
        this.idtypecontrat = idtypecontrat;
    }

    public double getSalairemin() {
        return salairemin;
    }

    public void setSalairemin(double salairemin) {
        this.salairemin = salairemin;
    }

    public double getSalairemax() {
        return salairemax;
    }

    public void setSalairemax(double salairemax) {
        this.salairemax = salairemax;
    }

   

    public Date getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(Date datepublication) {
        this.datepublication = datepublication;
    }

    public Date getDatefermeture() {
        return datefermeture;
    }

    public void setDatefermeture(Date datefermeture) {
        this.datefermeture = datefermeture;
    }



    public OffreEmploi() throws Exception {
        this.setNomTable("OFFRE_EMPLOI");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OFE","getseq_offre_emploi");
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

    public FichePoste getFromeFichePoste() throws Exception {
        FichePoste fichePoste = new FichePoste();
        fichePoste.setId(this.getIdficheposte());
        FichePoste[] fichePostes = (FichePoste[]) CGenUtil.rechercher(fichePoste, null, null, "");
        if(fichePostes.length > 0) {
            return fichePostes[0];
        }
        return null;
    }
       @Override
    public String[] getMotCles() {
        String[] motCles={"id","titre"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
	 String[] valMotCles={"id","titre"};
        return valMotCles;
    }
}

