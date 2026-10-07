package maintenance.ressources;
import bean.ClassMAPTable;

import java.sql.Date;


public class OfNonRattache extends  ClassMAPTable{
    private String idOfFille,idProduit,idProduitLib,idLigne,ligne, lancepar;
    private double qte,qtePetri;
    private Date daty;

    public String getLancepar() {
        return lancepar;
    }

    public void setLancepar(String lancepar) {
        this.lancepar = lancepar;
    }

    public OfNonRattache(){
        super.setNomTable("OFNONRATTACHE");
    }
    @Override
    public String getTuppleID() {
        return idOfFille;
    }

    @Override
    public String getAttributIDName() {
        return "idOfFille";
    }

    public String getIdOfFille() {
        return idOfFille;
    }

    public void setIdOfFille(String idOfFille) {
        this.idOfFille = idOfFille;
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
}
