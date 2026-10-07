package paie.formation;

import bean.ClassFille;

import java.sql.Connection;
import java.sql.Date;

public class Formation extends ClassFille {
    private String id;
    private String idplan;
    private String titre;
    private String description;
    private Date datedebut;
    private Date datefin;
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdplan() {
        return idplan;
    }

    public void setIdplan(String idplan) {
        this.idplan = idplan;
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

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.formation.FormationPlan";
    }

    @Override
    public String getLiaisonMere() {
        return "idPlan";
    }

    public Formation() throws Exception {
        this.setNomTable("FORMATION");
        this.setNomClasseMere("paie.formation.FormationPlan");
        this.setLiaisonMere("idplan");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FRPF","getSeqFormation");
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

