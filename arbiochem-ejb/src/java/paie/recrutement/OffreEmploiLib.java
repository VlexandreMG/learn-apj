package paie.recrutement;

public class OffreEmploiLib extends OffreEmploi{
    String idtypecontratlib;
    String idfichepostelib;
    String etatlib;

    
    public OffreEmploiLib() throws Exception{
        this.setNomTable("offre_emploilib");
    }
    public String getIdtypecontratlib() {
        return idtypecontratlib;
    }
    public void setIdtypecontratlib(String idtypecontratlib) {
        this.idtypecontratlib = idtypecontratlib;
    }
    public String getIdfichepostelib() {
        return idfichepostelib;
    }
    public void setIdfichepostelib(String idfichepostelib) {
        this.idfichepostelib = idfichepostelib;
    }
    public String getEtatlib() {
        return etatlib;
    }
    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
    

    
    
}
