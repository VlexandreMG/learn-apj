package maintenance.ressources;
import bean.ClassMAPTable;

import java.sql.Date;


public class OfRattache extends  ClassMAPTable{
    private String id,idCompteur,idFabrication,idProduit,nomProduit,idLigne,ligne;
    private double qte,qtePetri, pourcentage;
    private Date daty;
    private String idOffille, idReleve, idProduitLib;

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getIdReleve() {
        return idReleve;
    }

    public void setIdReleve(String idReleve) {
        this.idReleve = idReleve;
    }

    public String getIdOffille() {
        return idOffille;
    }

    public void setIdOffille(String idOffille) {
        this.idOffille = idOffille;
    }

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }

    public OfRattache(){
        super.setNomTable("OFRATTACHE");
    }
    @Override
    public String getTuppleID() {
        return idCompteur;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public String getIdCompteur() {
        return idCompteur;
    }

    public void setIdCompteur(String idCompteur) {
        this.idCompteur = idCompteur;
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

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getQtePetri() {
        return qtePetri;
    }

    public void setQtePetri(double qtePetri) {
        this.qtePetri = qtePetri;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
