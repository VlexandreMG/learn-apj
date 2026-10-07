package poste;

import bean.ClassFille;

import java.sql.Connection;

public class FichePosteFille extends ClassFille {
    private String id;
    private String idficheposte;
    private String titreactivites;
    private String descriptionactivites;

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

    public String getTitreactivites() {
        return titreactivites;
    }

    public void setTitreactivites(String titreactivites) {
        this.titreactivites = titreactivites;
    }

    public String getDescriptionactivites() {
        return descriptionactivites;
    }

    public void setDescriptionactivites(String descriptionactivites) {
        this.descriptionactivites = descriptionactivites;
    }


    @Override
    public String getNomClasseMere() {
        return "poste.FichePoste";
    }

    @Override
    public String getLiaisonMere() {
        return "idficheposte";
    }

    public FichePosteFille() throws Exception {
        this.setNomTable("FICHE_POSTE_FILLE");
        this.setNomClasseMere("poste.FichePoste");
        this.setLiaisonMere("idficheposte");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FPFF","getSEQ_FICHE_POSTE_FILLE");
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

