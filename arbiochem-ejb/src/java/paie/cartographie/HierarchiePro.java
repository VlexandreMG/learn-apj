package paie.cartographie;

import bean.ClassMAPTable;

public class HierarchiePro extends ClassMAPTable {
    private String famillepro;
    private String idfamillepro;
    private String sousfamille;
    private String coderome;
    private String metier;
    private String emploie;
    private String fonction;

    public String getFamillepro() {
        return famillepro;
    }

    public void setFamillepro(String famillepro) {
        this.famillepro = famillepro;
    }

    public String getIdfamillepro() {
        return idfamillepro;
    }

    public void setIdfamillepro(String idfamillepro) {
        this.idfamillepro = idfamillepro;
    }

    public String getSousfamille() {
        return sousfamille;
    }

    public void setSousfamille(String sousfamille) {
        this.sousfamille = sousfamille;
    }

    public String getCoderome() {
        return coderome;
    }

    public void setCoderome(String coderome) {
        this.coderome = coderome;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getEmploie() {
        return emploie;
    }

    public void setEmploie(String emploie) {
        this.emploie = emploie;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }



    public HierarchiePro() throws Exception {
        this.setNomTable("V_HIERARCHIE_PROFESSIONNELLE");
    }



    @Override
    public String getTuppleID() {
        return idfamillepro;
    }

    @Override
    public String getAttributIDName() {
        return "idfamillepro";
    }
}

