package produits;

public class IngredientsLib extends produits.Ingredients
{
    String idcategorieingredient;
    String idcategorie;
    String compte;
    String uniteLib;
    double taux;
    String  refpostLib;
    String  idligne;
    String refqualificationLib;
    String composelib,idFamilleLib, idFournisseurLib,idDepartementLib;
    String libellevente;
    double nbpiece;
    String idunite;
    private String typeProduitLib;

    public String getTypeProduitLib() {
        return typeProduitLib;
    }

    public void setTypeProduitLib(String typeProduitLib) {
        this.typeProduitLib = typeProduitLib;
    }

    public String getIdunite() {
        return idunite;
    }

    public void setIdunite(String idunite) {
        this.idunite = idunite;
    }

    public double getNbpiece() {
        return nbpiece;
    }

    public void setNbpiece(double nbpiece) {
        this.nbpiece = nbpiece;
    }

    public String getLibellevente() {
        return libellevente;
    }

    public void setLibellevente(String libellevente) {
        this.libellevente = libellevente;
    }

    public String getIdDepartementLib() {
        return idDepartementLib;
    }

    public void setIdDepartementLib(String idDepartementLib) {
        this.idDepartementLib = idDepartementLib;
    }

    public String getIdFournisseurLib() {
        return idFournisseurLib;
    }

    public void setIdFournisseurLib(String idFournisseurLib) {
        this.idFournisseurLib = idFournisseurLib;
    }

    public String getComposelib() {
        return composelib;
    }

    public void setComposelib(String composelib) {
        this.composelib = composelib;
    }
    public String getIdligne() {
		return this.idligne;
	}

	public void setIdligne(String idligne) {
		this.idligne = idligne;
	}
    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public String getUniteLib() {
        return uniteLib;
    }

    public void setUniteLib(String uniteLib) {
        this.uniteLib = uniteLib;
    }

    public String getIdcategorieingredient() {
        return idcategorieingredient;
    }

    public void setIdcategorieingredient(String idcategorieingredient) {
        this.idcategorieingredient = idcategorieingredient;
    }

    public String getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(String idcategorie) {
        this.idcategorie = idcategorie;
    }

    public IngredientsLib() {
        setNomTable("AS_INGREDIENTS_LIB");
    }

    @Override
    public String[] getMotCles() {
        return new String[]{"id","libelle","unite"};
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","libelle","unite"};
        return motCles;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getRefpostLib() {
        return refpostLib;
    }

    public void setRefpostLib(String refpostLib) {
        this.refpostLib = refpostLib;
    }

    public String getRefqualificationLib() {
        return refqualificationLib;
    }

    public void setRefqualificationLib(String refqualificationLib) {
        this.refqualificationLib = refqualificationLib;
    }

    public String getIdFamilleLib() {
        return idFamilleLib;
    }

    public void setIdFamilleLib(String idFamilleLib) {
        this.idFamilleLib = idFamilleLib;
    }
}
