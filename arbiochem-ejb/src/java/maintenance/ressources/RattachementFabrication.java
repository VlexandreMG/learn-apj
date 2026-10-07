package maintenance.ressources;

import bean.ClassMAPTable;

import java.sql.Date;

public class RattachementFabrication extends ClassMAPTable {
    private String idReleveFab,idcompteur, idFabrication,idProduit,idProduitLib,idLigne,ligne,nomProduit,idoffille;

    private Date daty;
    private double qte, qtePetri;
    
    @Override
    public String getTuppleID() {
        return idFabrication;
    }

    @Override
    public String getAttributIDName() {
        return "idFabrication";
    }
    public String getIdoffille() {
		return this.idoffille;
	}

	public void setIdoffille(String idoffille) {
		this.idoffille = idoffille;
	}
    public String getIdReleveFab() {
        return this.idReleveFab;
    }

    public void setIdReleveFab(String idReleveFab) {
        this.idReleveFab = idReleveFab;
    }
    public RattachementFabrication() {
        super.setNomTable("fabricationrattache");
    }

    public String getIdcompteur() {
        return idcompteur;
    }
    
        public String getNomProduit() {
            return this.nomProduit;
        }
    
        public void setNomProduit(String nomProduit) {
            this.nomProduit = nomProduit;
        }

    public void setIdcompteur(String idcompteur) {
        this.idcompteur = idcompteur;
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

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
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
}
