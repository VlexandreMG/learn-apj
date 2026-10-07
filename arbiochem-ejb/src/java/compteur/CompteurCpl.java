package compteur;

import maintenance.configuration.CompteurMaintenance;

public class CompteurCpl extends CompteurMaintenance {
    public String idLigneLib;
    public String idCategorieLib;
    public String idTrancheLib;


    public String getIdLigneLib() {
        return idLigneLib;
    }

    public void setIdLigneLib(String idLigneLib) {
        this.idLigneLib = idLigneLib;
    }

    public String getIdCategorieLib() {
        return idCategorieLib;
    }

    public void setIdCategorieLib(String idCategorieLib) {
        this.idCategorieLib = idCategorieLib;
    }

    public String getIdTrancheLib() {
        return idTrancheLib;
    }

    public void setIdTrancheLib(String idTrancheLib) {
        this.idTrancheLib = idTrancheLib;
    }

    public CompteurCpl(){
        this.setNomTable("compteurcpl");
    }
}
