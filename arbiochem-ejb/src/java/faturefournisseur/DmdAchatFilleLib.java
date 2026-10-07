/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package faturefournisseur;

/**
 *
 * @author dell
 */
public class DmdAchatFilleLib extends DmdAchatFille {
    String unite;

    public DmdAchatFilleLib() throws Exception {
        super.setNomTable("DMDACHATFILLE_LIB");
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
    
    
    
}
