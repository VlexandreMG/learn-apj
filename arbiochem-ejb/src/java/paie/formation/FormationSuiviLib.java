package paie.formation;

public class FormationSuiviLib extends FormationSuivi{
    String idpersonnellib;
    String idformationlib;
    String etatlib;
    String persMatricule;
    public FormationSuiviLib() throws Exception{
        this.setNomTable("FORMATION_SUIVILIB");
    }
    public String getIdpersonnellib() {
        return idpersonnellib;
    }
    public void setIdpersonnellib(String idpersonnellib) {
        this.idpersonnellib = idpersonnellib;
    }
    public String getIdformationlib() {
        return idformationlib;
    }
    public void setIdformationlib(String idformationlib) {
        this.idformationlib = idformationlib;
    }
    public String getEtatlib() {
        return etatlib;
    }
    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }

    public String getPersMatricule() {
        return persMatricule;
    }

    public void setPersMatricule(String persMatricule) {
        this.persMatricule = persMatricule;
    }
}
