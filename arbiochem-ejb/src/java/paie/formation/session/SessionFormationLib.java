package paie.formation.session;

public class SessionFormationLib extends SessionFormation {
    String idactionformationlib;
    String etatlib;
    public SessionFormationLib() throws Exception {
        super.setNomTable("SESSION_FORMATION_LIB");
    }
    public String getIdactionformationlib() {
        return idactionformationlib;
    }
    public void setIdactionformationlib(String idactionformationlib) {
        this.idactionformationlib = idactionformationlib;
    }
    public String getEtatlib() {
        return etatlib;
    }
    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
    

    
    
}
