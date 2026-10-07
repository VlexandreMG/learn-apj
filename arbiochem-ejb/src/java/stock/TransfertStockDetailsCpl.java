package stock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 26134
 */
public class TransfertStockDetailsCpl extends TransfertStockDetails{
    String idTransfertStockLib,idProduitLib,unite;
    private String designation;

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public TransfertStockDetailsCpl() {
        this.setNomTable("TransfertStockDetailsCpl");
    }

    public String getIdTransfertStockLib() {
        return idTransfertStockLib;
    }

    public void setIdTransfertStockLib(String idTransfertStockLib) {
        this.idTransfertStockLib = idTransfertStockLib;
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }
    
}
