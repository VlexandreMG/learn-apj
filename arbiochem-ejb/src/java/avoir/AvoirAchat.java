package avoir;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class AvoirAchat extends ClassMere {
    String id;
    String designation;
    String idMagasin;
    Date daty;
    String remarque;
    String idFournisseur;
    String idFacture;

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("AVRAC", "GETSEQ_AVOIRACHAT");
        this.setId(makePK(c));
    }

    public AvoirAchat() throws  Exception{
        this.setNomTable("AVOIRACHAT");
        setLiaisonFille("idMere");
        setNomClasseFille("avoir.AvoirAchatFille");
    }

    @Override
    public String getNomClasseFille() {
        return "avoir.AvoirAchatFille";
    }

    @Override
    public String getLiaisonFille() {
        return "idMere";
    }

    public AvoirAchat(String nomtable){
        this.setNomTable(nomtable);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }
}
