package faturefournisseur;
import bean.ClassMAPTable;
import java.sql.Date;

public class RapprochementFacturation extends ClassMAPTable{
    private String id;
    private String produit;
    private String numbl;
    private String iddetailsfacturefournisseur;
    private String unite;
    private String idbc_fille;
    private String remarque;
    private String idbc;
    private Date daty;
    private int etat;
    private String magasin;
    private String produitLib;
    private String unitelib;
    private String magasinlib;
    private String libelleexacte;

    private double pu;
    private double puar;
    private double quantite;
    private double qtefacturer;
    private double resteAfacturer;

    public RapprochementFacturation() {
        setNomTable("RAPPROCHEMENT_FFOURNISSEUR");
    }

    public double getResteAfacturer() {
        return resteAfacturer;
    }

    public void setResteAfacturer(double resteAfacturer) {
        this.resteAfacturer = resteAfacturer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getNumbl() {
        return numbl;
    }

    public void setNumbl(String numbl) {
        this.numbl = numbl;
    }

    public String getIddetailsfacturefournisseur() {
        return iddetailsfacturefournisseur;
    }

    public void setIddetailsfacturefournisseur(String iddetailsfacturefournisseur) {
        this.iddetailsfacturefournisseur = iddetailsfacturefournisseur;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getIdbc_fille() {
        return idbc_fille;
    }

    public void setIdbc_fille(String idbc_fille) {
        this.idbc_fille = idbc_fille;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getIdbc() {
        return idbc;
    }

    public void setIdbc(String idbc) {
        this.idbc = idbc;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getProduitLib() {
        return produitLib;
    }

    public void setProduitLib(String produitLib) {
        this.produitLib = produitLib;
    }

    

    public String getUnitelib() {
        return unitelib;
    }

    public void setUnitelib(String unitelib) {
        this.unitelib = unitelib;
    }

    public String getMagasinlib() {
        return magasinlib;
    }

    public void setMagasinlib(String magasinlib) {
        this.magasinlib = magasinlib;
    }

    public String getLibelleexacte() {
        return libelleexacte;
    }

    public void setLibelleexacte(String libelleexacte) {
        this.libelleexacte = libelleexacte;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getPuar() {
        return puar;
    }

    public void setPuar(double puar) {
        this.puar = puar;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getQtefacturer() {
        return qtefacturer;
    }

    public void setQtefacturer(double qtefacturer) {
        this.qtefacturer = qtefacturer;
    }

    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
    
    
    
    
}
