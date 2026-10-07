package calculBesoin;

import bean.CGenUtil;
import bean.ClassFille;
import utilitaire.Utilitaire;
import utils.ConstanteSocobis;

import java.sql.Connection;

public class CalculBesoinFille extends ClassFille {
    private String id;
    private String idMere; // FK vers CalculBesoin
    private String idProduit;
    private double qte;

    // Getters / Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMere() {
        return idMere;
    }

    public void setIdMere(String idMere) {
        this.idMere = idMere;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) throws Exception {
        if (this.getMode().equals("modif")){
            if (!idProduit.equalsIgnoreCase(ConstanteSocobis.CATEGORIE_PRODUIT_FINI)){
                throw new Exception("Produit non produit fini!");
            }
        }
        this.idProduit = idProduit;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    // Méthode pour construire la PK
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CBF", "getseqCALCULBESOINFILLE");
        this.setId(makePK(c));
    }

    // Constructeur
    public CalculBesoinFille() {
        this.setNomTable("CalculBesoinFille");
        this.setLiaisonMere("calculBesoin.CalculBesoin"); // Classe mère
        this.setLiaisonMere("idMere"); // Attribut FK
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

    @Override
    public String getNomClasseMere() {
        return "calculBesoin.CalculBesoin";
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    // Récupération de l'objet mère
    public CalculBesoin getCalculBesoin(Connection c) throws Exception {
        CalculBesoin[] cbArray = (CalculBesoin[]) CGenUtil.rechercher(
                new CalculBesoin(), null, null, null, " AND id='" + this.getIdMere() + "'");
        if (cbArray != null && cbArray.length > 0) {
            return cbArray[0];
        }
        return null;
    }

    // Méthode statique pour récupérer plusieurs lignes par IDs
    public static CalculBesoinFille[] getByIds(String[] ids) throws Exception {
        return (CalculBesoinFille[]) CGenUtil.rechercher(
                new CalculBesoinFille(), null, null, null,
                " AND id in (" + Utilitaire.tabToString(ids, "'", ",") + ")");
    }
}