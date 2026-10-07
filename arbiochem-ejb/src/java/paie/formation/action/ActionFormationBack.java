package paie.formation.action;

import bean.ClassEtat;
import utilitaire.UtilDB;

import java.sql.Connection;

public class ActionFormationBack extends ClassEtat {
    private String id;
    private String idplanformation;
    private String idtypeformation;
    private String idcategorieformation;
    private String formateur;
    private String reference;
    private String intitule;
    private String typeprestataire;
    private String idsemestre;
    private int nbsessionprevus;
    private double dureestagiaireheure;
    private int nbouvriersprevue;
    private int nbcadreprevue;
    private String objectif;
    private int estrealisee;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdplanformation() {
        return idplanformation;
    }

    public void setIdplanformation(String idplanformation) {
        this.idplanformation = idplanformation;
    }

    public String getIdtypeformation() {
        return idtypeformation;
    }

    public void setIdtypeformation(String idtypeformation) {
        this.idtypeformation = idtypeformation;
    }

    public String getIdcategorieformation() {
        return idcategorieformation;
    }

    public void setIdcategorieformation(String idcategorieformation) {
        this.idcategorieformation = idcategorieformation;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getTypeprestataire() {
        return typeprestataire;
    }

    public void setTypeprestataire(String typeprestataire) {
        this.typeprestataire = typeprestataire;
    }

    public String getIdsemestre() {
        return idsemestre;
    }

    public void setIdsemestre(String idsemestre) {
        this.idsemestre = idsemestre;
    }

    public int getNbsessionprevus() {
        return nbsessionprevus;
    }

    public void setNbsessionprevus(int nbsessionprevus) {
        this.nbsessionprevus = nbsessionprevus;
    }

    public double getDureestagiaireheure() {
        return dureestagiaireheure;
    }

    public void setDureestagiaireheure(double dureestagiaireheure) {
        this.dureestagiaireheure = dureestagiaireheure;
    }

    public int getNbouvriersprevue() {
        return nbouvriersprevue;
    }

    public void setNbouvriersprevue(int nbouvriersprevue) {
        this.nbouvriersprevue = nbouvriersprevue;
    }

    public int getNbcadreprevue() {
        return nbcadreprevue;
    }

    public void setNbcadreprevue(int nbcadreprevue) {
        this.nbcadreprevue = nbcadreprevue;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }


    

    public ActionFormationBack() throws Exception {
        this.setNomTable("ACTION_FORMATION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ACF","GET_SEQ_ACTION_FORMATION");
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
        String[] motCles={"id","intitule", "reference"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","intitule", "reference"};
        return motCles;
    }

    public int getEstrealisee() {
        return estrealisee;
    }

    public void setEstrealisee(int estrealisee) {
        this.estrealisee = estrealisee;
    }
    public void marquerOui(String u) throws Exception {
        Connection c = null;
        try {
            if (c == null){
                UtilDB util = new UtilDB();
                c = util.GetConn();
                c.setAutoCommit(false);
            }
            ActionFormationBack actionFormation = (ActionFormationBack) this.getById(this.getId(), this.getNomTable(), c);
            if(actionFormation.getEstrealisee() == 1){
               throw new Exception("Cette action de formation est d\\u00e9j\\u00E0 marqu\\u00e9e comme r\\u00e9alis\\u00e9e");
            }
            actionFormation.setEstrealisee(1);
            actionFormation.updateToTableWithHisto(u, c);
           
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
}   

