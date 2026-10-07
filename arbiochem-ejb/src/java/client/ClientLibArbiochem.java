package client;

public class ClientLibArbiochem extends ClientArbiochem{

    private String idTypeClientLib;
    private String provinceLib;

    public ClientLibArbiochem(){
         this.setNomTable("CLIENTLIB");
    }

    public String getProvinceLib() {
        return provinceLib;
    }

    public void setProvinceLib(String provinceLib) {
        this.provinceLib = provinceLib;
    }

    public String getIdTypeClientLib() {
        return idTypeClientLib;
    }
    public void setIdTypeClientLib(String idTypeClientLib) {
        this.idTypeClientLib = idTypeClientLib;
    }
}
