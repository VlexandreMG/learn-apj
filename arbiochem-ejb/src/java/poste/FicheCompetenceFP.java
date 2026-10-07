package poste;

import bean.ClassMAPTable;

import java.sql.Connection;

public class FicheCompetenceFP extends ClassMAPTable {
    private String id;
    private String idficheposte;
    private String idtypecompetencesfp;
    private String description;
    private int niveau;

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

    public String getIdtypecompetencesfp() {
        return idtypecompetencesfp;
    }

    public void setIdtypecompetencesfp(String idtypecompetencesfp) {
        this.idtypecompetencesfp = idtypecompetencesfp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }



    public FicheCompetenceFP() throws Exception {
        this.setNomTable("FICHE_POSTE_COMPETENCES");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FCPT","getSEQ_FICHE_POSTE_COMP");
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

