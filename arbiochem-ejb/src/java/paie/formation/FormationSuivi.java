package paie.formation;

import bean.ClassEtat;

import java.sql.Connection;
import java.sql.Date;

public class FormationSuivi extends ClassEtat {
    private String id;
    private String idformation;
    private String idpersonnel;
    private Date datedebut;
    private Date datefin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdformation() {
        return idformation;
    }

    public void setIdformation(String idformation) {
        this.idformation = idformation;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
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



    public FormationSuivi() throws Exception {
        this.setNomTable("FORMATION_SUIVI");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FRM","GET_SEQ_FORMATION_SUIVI");
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

