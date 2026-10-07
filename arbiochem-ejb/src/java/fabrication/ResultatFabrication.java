package fabrication;

import bean.ClassMAPTable;
import java.sql.Date;

public class ResultatFabrication extends ClassMAPTable {
    private String id;
    private String idOffille;
    private String idFabrication;
    private String idProduit;
    private String idProduitLib;
    private double qte;
    private double qteIngredients;
    private String idIngredients;
    private String idLigne;
    private Date daty;
    private double entreeDechet;

    public double getEntreeDechet() {
        return entreeDechet;
    }

    public void setEntreeDechet(double entreeDechet) {
        this.entreeDechet = entreeDechet;
    }

    public ResultatFabrication() {
        this.setNomTable("RESULTATFABRICATION");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOffille() {
        return idOffille;
    }

    public void setIdOffille(String idOffille) {
        this.idOffille = idOffille;
    }

    public String getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(String idFabrication) {
        this.idFabrication = idFabrication;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String getIdIngredients() {
        return idIngredients;
    }

    public void setIdIngredients(String idIngredients) {
        this.idIngredients = idIngredients;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getQteIngredients() {
        return qteIngredients;
    }

    public void setQteIngredients(double qteIngredients) {
        this.qteIngredients = qteIngredients;
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}
