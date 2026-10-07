package paie.formation.suivi;

import bean.ClassFille;

import java.sql.Connection;
import java.sql.Date;

public class SuiviPresenceFille extends ClassFille {
    private String id;
    private String idsuivipresencemere;
    private String idparticipationformation;
    private String idpersonnel;
    private String idactionformation;
    private Date dateseance;
    private String presence;
    private double nbheureeffectue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdsuivipresencemere() {
        return idsuivipresencemere;
    }

    public void setIdsuivipresencemere(String idsuivipresencemere) {
        this.idsuivipresencemere = idsuivipresencemere;
    }

    public String getIdparticipationformation() {
        return idparticipationformation;
    }

    public void setIdparticipationformation(String idparticipationformation) {
        this.idparticipationformation = idparticipationformation;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getIdactionformation() {
        return idactionformation;
    }

    public void setIdactionformation(String idactionformation) {
        this.idactionformation = idactionformation;
    }

    public Date getDateseance() {
        return dateseance;
    }

    public void setDateseance(Date dateseance) {
        this.dateseance = dateseance;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public double getNbheureeffectue() {
        return nbheureeffectue;
    }

    public void setNbheureeffectue(double nbheureeffectue) {
        this.nbheureeffectue = nbheureeffectue;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.formation.suivi.SuiviPresenceMere";
    }

    @Override
    public String getLiaisonMere() {
        return "idsuivipresencemere";
    }

    public SuiviPresenceFille() throws Exception {
        this.setNomTable("SUIVI_PRESENCE_FILLE");
        this.setNomClasseMere("paie.formation.suivi.SuiviPresenceMere");
        this.setLiaisonMere("idsuivipresencemere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SPF","GET_SEQ_SUIVI_PRES_FILLE");
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

