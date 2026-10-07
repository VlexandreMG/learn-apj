package onBoarding;

import bean.ClassMere;

import java.sql.Connection;

public class OnboardingMere extends ClassMere {
    private String id;
    private String nom;
    private String description;
    private String idFonction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(String idFonction) {
        this.idFonction = idFonction;
    }

    public OnboardingMere() throws Exception {
        this.setNomTable("ONBOARDING_MERE");
        this.setNomClasseFille("onBoarding.OnboardingFille");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OBM","get_SEQ_ONBOARDING_MERE");
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
        String[] motCles={"id","nom"};
        return motCles;
    }
}

