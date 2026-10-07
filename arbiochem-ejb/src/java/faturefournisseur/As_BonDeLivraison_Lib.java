/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faturefournisseur;

/**
 *
 * @author 26134
 */
public class As_BonDeLivraison_Lib extends As_BonDeLivraison{
    String magasinlib;
    String etatLib;
    String idFournisseurLib , idTraite , traite;

    public As_BonDeLivraison_Lib() throws Exception{
        this.setNomTable("AS_BONDELIVRAISON_lib");
    }

    public String getIdTraite() {
        return idTraite;
    }

    public void setIdTraite(String idTraite) {
        this.idTraite = idTraite;
    }

    public String getTraite() {
        return traite;
    }

    public void setTraite(String traite) {
        this.traite = traite;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public String getMagasinlib() {
        return magasinlib;
    }

    public void setMagasinlib(String magasinlib) {
        this.magasinlib = magasinlib;
    }

    public String getIdFournisseurLib() {
        return idFournisseurLib;
    }

    public void setIdFournisseurLib(String idFournisseurLib) {
        this.idFournisseurLib = idFournisseurLib;
    }
     
}
