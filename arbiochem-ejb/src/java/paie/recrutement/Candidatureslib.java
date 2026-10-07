package paie.recrutement;

public class Candidatureslib extends Candidatures {
    String idcandidatlib;
    String idoffreemploielib;
    String etatLib;

    public Candidatureslib() throws Exception {
        this.setNomTable("candidatureslib");
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getIdcandidatlib() {
        return idcandidatlib;
    }

    public void setIdcandidatlib(String idcandidatlib) {
        this.idcandidatlib = idcandidatlib;
    }

    public String getIdoffreemploielib() {
        return idoffreemploielib;
    }

    public void setIdoffreemploielib(String idoffreemploielib) {
        this.idoffreemploielib = idoffreemploielib;
    }
    
    
}
