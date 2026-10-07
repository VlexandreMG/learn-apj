package paie.formation.action;

public class ActionFormationLib extends ActionFormation {
    String idplanformationlib;
    String idtypeformationlib;
    String idcategorieformationlib;
    String idsemestrelib;
    String estrealiseelib;
    String formateurlib;
    
    public ActionFormationLib() throws Exception {
        super.setNomTable("ACTION_FORMATION_LIB");
    }
    public String getIdplanformationlib() {
        return idplanformationlib;
    }
    public void setIdplanformationlib(String idplanformationlib) {
        this.idplanformationlib = idplanformationlib;
    }
    public String getIdtypeformationlib() {
        return idtypeformationlib;
    }
    public void setIdtypeformationlib(String idtypeformationlib) {
        this.idtypeformationlib = idtypeformationlib;
    }
    public String getIdcategorieformationlib() {
        return idcategorieformationlib;
    }
    public void setIdcategorieformationlib(String idcategorieformationlib) {
        this.idcategorieformationlib = idcategorieformationlib;
    }
    public String getIdsemestrelib() {
        return idsemestrelib;
    }
    public void setIdsemestrelib(String idsemestrelib) {
        this.idsemestrelib = idsemestrelib;
    }
    public String getEstrealiseelib() {
        return estrealiseelib;
    }
    public void setEstrealiseelib(String estrealiseelib) {
        this.estrealiseelib = estrealiseelib;
    }

    public String getFormateurlib() {
        return formateurlib;
    }
    public void setFormateurlib(String formateurlib) {
        this.formateurlib = formateurlib;
    }
    
}
