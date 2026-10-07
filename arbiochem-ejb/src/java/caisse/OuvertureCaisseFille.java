package caisse;

import bean.ClassFille;

public class OuvertureCaisseFille extends ClassFille {

    private String id;
    private String idOuverture;
    private String idCaisse;
    private double montant;
    private double montantTheorique;
    private String explication;
    private String caisseLib;

    public OuvertureCaisseFille() throws Exception
    {
        this.setNomTable("OUVERTURECAISSEFILLE");
        setLiaisonMere("idOuverture");
        setNomClasseMere("caisse.OuvertureCaisse");
    }

    public String getNomClasseMere()
    {
        return "caisse.OuvertureCaisse";
    }

    public String getLiaisonMere() {
        return "idOuverture";
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
    @Override
    public String getTuppleID() {
        return id;
    }
    @Override
    public void construirePK(java.sql.Connection c) throws Exception {
        this.preparePk("OVCF", "getseq_ouvcaissefille");
        this.setId(makePK(c));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(String idCaisse) {
        this.idCaisse = idCaisse;
    }

    public String getIdOuverture() {
        return idOuverture;
    }

    public void setIdOuverture(String idOuverture) {
        this.idOuverture = idOuverture;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getMontantTheorique() {
        return montantTheorique;
    }

    public void setMontantTheorique(double montantTheorique) {
        this.montantTheorique = montantTheorique;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public String getCaisseLib() {
        return caisseLib;
    }

    public void setCaisseLib(String caisseLib) {
        this.caisseLib = caisseLib;
    }
}
