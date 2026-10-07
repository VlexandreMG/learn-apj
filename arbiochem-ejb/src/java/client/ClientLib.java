package client;

public class ClientLib extends Client{

    private String idTypeClientLib;
    private String provinceLib;

    public ClientLib(){
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
