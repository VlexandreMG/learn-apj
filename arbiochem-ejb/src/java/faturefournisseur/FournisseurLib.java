package faturefournisseur;

public class FournisseurLib extends Fournisseur {

    private String idTypeFournisseurLib;

    public FournisseurLib() {
        this.setNomTable("V_FOURNISSEUR_LIB");
    }

    public String getIdTypeFournisseurLib() {
        return idTypeFournisseurLib;
    }

    public void setIdTypeFournisseurLib(String idTypeFournisseurLib) {
        this.idTypeFournisseurLib = idTypeFournisseurLib;
    }
}
