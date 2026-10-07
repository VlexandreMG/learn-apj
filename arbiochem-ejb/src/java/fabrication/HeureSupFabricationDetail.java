package fabrication;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class HeureSupFabricationDetail extends ClassMAPTable {
    private String id;
    private String idFabrication;
    private String idPersonnel;
    private String type_hs;
    private double heures;
    private double taux;
    private double montant;
    private double cumul_avant;
    private String id_hsmois;
    private Date daty;

    public HeureSupFabricationDetail() {
        super.setNomTable("HS_FABRICATION_DETAIL_PAIE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("HSDET", "getseq_hsdetail");
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(String idFabrication) {
        this.idFabrication = idFabrication;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getType_hs() {
        return type_hs;
    }

    public void setType_hs(String type_hs) {
        this.type_hs = type_hs;
    }

    public double getHeures() {
        return heures;
    }

    public void setHeures(double heures) {
        this.heures = heures;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getCumul_avant() {
        return cumul_avant;
    }

    public void setCumul_avant(double cumul_avant) {
        this.cumul_avant = cumul_avant;
    }

    public String getId_hsmois() {
        return id_hsmois;
    }

    public void setId_hsmois(String id_hsmois) {
        this.id_hsmois = id_hsmois;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
}
