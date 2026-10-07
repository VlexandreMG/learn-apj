package fabrication;

public class FabricationCpl extends Fabrication {
    String lanceparLib, cibleLib,etatLib;
    public FabricationCpl() throws Exception {
        super.setNomTable("FABRICATIONCPL");
    }

    public String getLanceparLib(){
        return this.lanceparLib;
    }

    public void setLanceparLib(String lanceparLib){
        this.lanceparLib = lanceparLib;
    }

    public String getCibleLib(){
        return this.cibleLib;
    }

    public void setCibleLib(String cibleLib){
        this.cibleLib = cibleLib;
    }

    public String getEtatLib(){
        return this.etatLib;
    }

    public void setEtatLib(String etatLib){
        this.etatLib = etatLib;
    }

    public String[] getValMotCles() {
        String[] motCles={"id","libelle", "idoffille", "daty"};
        return motCles;
    }
}
