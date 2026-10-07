package compteur;

import bean.CGenUtil;import bean.ClassFille;
import bean.ClassMAPTable;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

public class CompteurElectricite extends ClassFille {
    private String id;
    private Date daty;
    private String idligne;
    private double valeur;
    private double ecart;
    private String idcategorie;
    private String idmagasin;
    private double ancien;
    private String remarque;
    private String heure;
    private String idmachine;
    private String idmere;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdligne() {
        return idligne;
    }

    public void setIdligne(String idligne) {
        this.idligne = idligne;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public double getEcart() {
        return ecart;
    }

    public void setEcart(double ecart) {
        this.ecart = ecart;
    }

    public String getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(String idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getIdmagasin() {
        return idmagasin;
    }

    public void setIdmagasin(String idmagasin) {
        this.idmagasin = idmagasin;
    }

    public double getAncien() {
        return ancien;
    }

    public void setAncien(double ancien) {
        this.ancien = ancien;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getIdmachine() {
        return idmachine;
    }

    public void setIdmachine(String idmachine) {
        this.idmachine = idmachine;
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }


    @Override
    public String getNomClasseMere() {
        return "compteur.CompteurElectriciteMere";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public CompteurElectricite() throws Exception {
        this.setNomTable("COMPTEURELECTRICITE");
        this.setNomClasseMere("compteur.CompteurElectriciteMere");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CMF","GETSEQ_CompteurElectricite");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        this.setEcart(Math.abs(this.getValeur() - this.getAncien()));
        CompteurElectricite filtre = new CompteurElectricite();
        filtre.setIdligne(this.idligne);
        filtre.setIdmachine(this.idmachine);
        filtre.setIdcategorie(this.idcategorie);
        String date = Utilitaire.datetostring(this.daty);
        String dateSQL = "TO_DATE('" + date + "', 'DD/MM/YYYY')";
        CompteurElectricite[] existe = (CompteurElectricite[]) CGenUtil.rechercher(filtre, null, null, c, " and DATY = " + dateSQL );
        System.out.println("Length: "+ existe.length + " daty " + this.daty + " machine " + this.idmachine + " categorie " + this.idcategorie + " ligne " + this.idligne);
        if(existe.length>0){
            throw new Exception("Ce compteur existe d\\u00E9j\\u00E0 pour cette ligne, ce categorie, cette machine et  cette date");
        }
        CompteurElectricite compteurElectricite = (CompteurElectricite) super.createObject(u, c);
        compteurElectricite.setId(this.getIdmere());
        return compteurElectricite;
    }

    @Override
    public int updateObject(String u, Connection c) throws Exception {
        this.setEcart(Math.abs(this.getValeur() - this.getAncien()));
        int valiny = super.updateObject(u, c);
        this.setTuppleId(this.idmere);
        return valiny;
    }

    @Override
    public void insertToTableWithHistoBatch(String refUser, Statement st) throws Exception {
        if(this.getRemarque().equalsIgnoreCase("init")){
            this.setEcart(0);
        }
        super.insertToTableWithHistoBatch(refUser, st);
    }
}

