package caisse;
import java.sql.Connection;

import bean.*;
import mg.cnaps.compta.ecriture.ComptaEcritureFille;
import paiement.LiaisonPaiement;
import utils.ConstanteAsync;

public class MouvementCaisseMere extends ClassMere{
    String id,designation,idmodepaiement,idtiers,idorigine,idtierslib,etatlib;
    double credit,debit;
    java.sql.Date daty;
    public MouvementCaisseMere() throws Exception {
        this.setNomTable("mouvementcaissemere");
        setLiaisonFille("idmvtcaissemere");
        setNomClasseFille("caisse.MouvementCaisseFille");
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MVTM", "GETSEQMOUVEMENTCAISSEMERE");
        this.setId(makePK(c));
    }
    @Override
    public String getTuppleID() {
        return id;
    }

    public  String getNomClasseFille()
    {
        return "caisse.MouvementCaisseFille";
    }
    public String getLiaisonFille() {
        return "idmvtcaissemere";
    }
    @Override
    public String getAttributIDName() {
        return "id";
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getIdmodepaiement() {
        return idmodepaiement;
    }
    public void setIdmodepaiement(String idmodepaiement) {
        this.idmodepaiement = idmodepaiement;
    }
    public String getIdtiers() {
        return idtiers;
    }
    public void setIdtiers(String idtiers) {
        this.idtiers = idtiers;
    }
    public String getIdorigine() {
        return idorigine;
    }
    public void setIdorigine(String idorigine) {
        this.idorigine = idorigine;
    }
    public String getIdtierslib() {
        return idtierslib;
    }
    public void setIdtierslib(String idtierslib) {
        this.idtierslib = idtierslib;
    }
    public String getEtatlib() {
        return etatlib;
    }
    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
    public double getCredit() {
        return credit;
    }
    public void setCredit(double credit) {
        this.credit = credit;
    }
    public double getDebit() {
        return debit;
    }
    public void setDebit(double debit) {
        this.debit = debit;
    }
    public java.sql.Date getDaty() {
        return daty;
    }
    public void setDaty(java.sql.Date daty) {
        this.daty = daty;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        MouvementCaisseFille [] filles = (MouvementCaisseFille[])this.getFille();
        for(MouvementCaisseFille f : filles){
            f.setIdTiers(this.getIdtiers());
            f.setDaty(this.getDaty());
            f.setIdOrigine(this.getIdorigine());
//            f.setIdCaisse(ConstanteAsync.CAISSE_DEFAUT);
            TypeObjet crd = new TypeObjet();
            crd.setNomTable("modepaiementmaj");
            TypeObjet[] ret = (TypeObjet[]) CGenUtil.rechercher(crd, null, null, c, " and id = '"+f.getIdModePaiement()+"'");
            if(ret.length > 0) f.setDesignation(ret[0].getVal());
        }
        MouvementCaisseMere m = (MouvementCaisseMere) super.createObject(u, c);
        this.createliaisonPayement(u, c);
        return m;
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        MouvementCaisseFille [] filles = (MouvementCaisseFille[])this.getFille("mouvementcaisse", c, "");
        for(MouvementCaisseFille f : filles){
            f.validerSimple(u, c);
        }
        return super.validerObject(u, c);
    }

    public void createliaisonPayement(String u, Connection c) throws Exception {
        ClassFille[] filles = this.getFille();
        double total_mvt_caisse = 0.0;

        for (int i = 0; i < filles.length; i++) {
            MouvementCaisseFille fille = (MouvementCaisseFille) filles[i];
            total_mvt_caisse += fille.getCredit();
        }
        double ratio = 1.0;

        if (total_mvt_caisse > this.getCredit()) {
            ratio = this.getCredit() / total_mvt_caisse;
        }
        
        //this.setFille((MouvementCaisseFille[]) filles);
        
        for (int i = 0; i < filles.length; i++) {
            MouvementCaisseFille fille = (MouvementCaisseFille) filles[i];
            LiaisonPaiement p = new LiaisonPaiement();
            p.setId1(fille.getId());
            p.setId2(this.getIdorigine());
            p.setMontant(fille.getCredit() * ratio);
            p.createObject(u, c);
            p.validerObject(u, c);
        }
    }
    
}
