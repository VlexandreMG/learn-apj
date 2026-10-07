package paie.cantine;

import bean.ClassEtat;
import paie.elementpaie.PaiePersonnelElementpaie;
import paie.employe.ConstantePaie;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;

public class PointageCantine extends ClassEtat {
    private String id;
    private String idPersonnel;
    private String idDepartement;
    private int mois;
    private int annee;
    private int nombre;
    private Date daty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(String idDepartement) {
        this.idDepartement = idDepartement;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }



    public PointageCantine() throws Exception {
        this.setNomTable("POINTAGECANTINE");
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        PaiePersonnelElementpaie elementpaie = new PaiePersonnelElementpaie();
//        elementpaie.setRubrique("Retenue cantine");
        elementpaie.setGain(ConstantePaie.retenueCantine*this.getNombre());
        elementpaie.setIdpersonnel(this.getIdPersonnel());
        elementpaie.setRemarque("");
        // elementpaie.setId_objet();
        elementpaie.setAnneeregularisation(this.getAnnee()+"");
        elementpaie.setMoisregularisation(this.getMois()+"");
        elementpaie.setCode_rubrique(ConstantePaie.cantine);
//        elementpaie.setRetenue(ConstantePaie.retenueCantine*this.getNombre());
        elementpaie.setDate_debut(Utilitaire.stringDate("01/" + this.getMois() + "/" + this.getAnnee()));
        String dateFin = Utilitaire.getNombreJourMois(String.valueOf(this.getMois()), String.valueOf(this.getAnnee())) + "/" + this.getMois() + "/" + this.getAnnee();
        elementpaie.setDate_fin(Utilitaire.stringDate(dateFin));

        elementpaie.createObject(u,c);
        elementpaie.validerObject(u,c);

        return super.validerObject(u, c);
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PCANT","GET_SEQ_POINTAGECANTINE");
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

