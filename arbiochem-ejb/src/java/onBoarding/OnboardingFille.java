package onBoarding;

import bean.CGenUtil;
import bean.ClassFille;

import java.sql.Connection;

public class OnboardingFille extends ClassFille {
    private String id;
    private String idMere;
    private String titre;
    private String description;
    private int rang;
    private String objectif;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMere() {
        return idMere;
    }

    public void setIdMere(String idMere) {
        this.idMere = idMere;
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

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public OnboardingFille() throws Exception {
        this.setNomTable("ONBOARDING_FILLE");
    }

    @Override
    public String getNomClasseMere() {
        return "onBoarding.OnboardingMere";
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("OBF","get_SEQ_ONBOARDING_FILLE");
        this.setId(makePK(c));
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","titre"};
        return motCles;
    }


    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public OnboardingFille[] getByIdMere() throws Exception {
        OnboardingFille[] onboardingFilles = (OnboardingFille[]) CGenUtil.rechercher(this, null, null, "");
        if (onboardingFilles.length <= 0){
            throw new Exception("Element du onboarding non trouver");

        }
        return onboardingFilles;
    }
}

