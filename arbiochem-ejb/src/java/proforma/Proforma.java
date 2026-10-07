package proforma;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ClassMere;
import vente.BonDeCommande;
import vente.NumeroFacture;

import java.sql.Connection;
import java.sql.Date;

public class Proforma extends ClassMere{
    private String id,designation,idMagasin,remarque,idOrigine,idClient,idReservation,echeance,reglement, idDevise;
    private Date daty,datyPrevu, dateprevres,dateLivraison;
    private int etat,estPrevu,modeLivraison;
    private double tva,remise,caution, fraislivraison;
    private String lieulocation;
    private String numeroProforma;
    private String idCommande,lieuLivraison;
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison(int modeLivraison) {
        this.modeLivraison = modeLivraison;
    }

    public String getLieuLivraison() {
        return lieuLivraison;
    }

    public void setLieuLivraison(String lieuLivraison) {
        this.lieuLivraison = lieuLivraison;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public String getNumeroProforma() {
        return numeroProforma;
    }

    public void setNumeroProforma(String numeroProforma) {
        this.numeroProforma = numeroProforma;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public double getCaution() {
        return caution;
    }
    public void setCaution(double caution) {
        this.caution = caution;
    }

    public String getLieulocation() {
        return lieulocation;
    }

    public void setLieulocation(String lieulocation) {
        this.lieulocation = lieulocation;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public Proforma getProforma(Connection c) throws Exception{
        Proforma proforma = (Proforma) this.getById(this.getId(), "PROFORMA", c);
        return proforma;
    }

    public Date getDateprevres() {
        return dateprevres;
    }

    public void setDateprevres(Date dateprevres) {
        this.dateprevres = dateprevres;
    }

    public Proforma()throws Exception{
        this.setNomTable("PROFORMA");
        this.setLiaisonFille("idProforma");
        this.setNomClasseFille("proforma.ProformaDetails");
    }
    @Override
    public String getTuppleID() {
        return this.id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PROF", "getSeqProforma");
        this.setId(makePK(c));
    }

    public double getFraislivraison() {
        return fraislivraison;
    }

    public void setFraislivraison(double fraislivraison) {
        this.fraislivraison = fraislivraison;
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

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(String idOrigine) {
        this.idOrigine = idOrigine;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getEcheance() {
        return echeance;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public String getReglement() {
        return reglement;
    }

    public void setReglement(String reglement) {
        this.reglement = reglement;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Date getDatyPrevu() {
        return datyPrevu;
    }

    public void setDatyPrevu(Date datyPrevu) {
        this.datyPrevu = datyPrevu;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEstPrevu() {
        return estPrevu;
    }

    public void setEstPrevu(int estPrevu) {
        this.estPrevu = estPrevu;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }
    @Override
    public String getNomClasseFille() {
        return ("proforma.ProformaDetails");
    }

    @Override
    public String getLiaisonFille() {
        return "idProforma"  ;
    }
    public ProformaDetails[] getFilleProforma()throws Exception{
        ProformaDetails profD = new ProformaDetails();
        profD.setIdProforma(this.getId());
        ProformaDetails[] val= (ProformaDetails[])CGenUtil.rechercher(profD, null, null, "");
        return val;
    }
    public ProformaDetailsLib[] getFilleProformaLib()throws Exception{
        ProformaDetailsLib profD = new ProformaDetailsLib();
        profD.setIdProforma(this.getId());
        ProformaDetailsLib[] val= (ProformaDetailsLib[])CGenUtil.rechercher(profD, null, null, "");
        return val;
    }
    public BonDeCommande createBonDeCommande()throws Exception{
        try {
            Proforma proforma = new Proforma();
            proforma.setId(this.getId());
            Proforma[] resultats = (Proforma[]) CGenUtil.rechercher(proforma, null, null, "");
            if(resultats.length > 0) {
                proforma = resultats[0];
                BonDeCommande bd = new BonDeCommande();
                if (proforma.getIdClient() != null) {
                    bd.setIdClient(proforma.getIdClient());
                }
                if (proforma.getIdMagasin() != null) {
                    bd.setIdMagasin(proforma.getIdMagasin());
                }
                if (proforma.getIdDevise() != null) {
                    bd.setIdDevise(proforma.getIdDevise());
                }
                bd.setIdProforma(proforma.getId());
                bd.setDesignation(proforma.getDesignation());
                bd.setLieuLivraison(proforma.getLieuLivraison());
                bd.setRemarque(proforma.getRemarque());
                bd.setModelivraison(proforma.getModeLivraison());
                bd.setFraislivraison(proforma.getFraislivraison());
                bd.setReference(proforma.getReference());
                return bd;
            }else {
                throw new Exception("La proforma n'existe pas");
            }
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        NumeroFacture numFactF = new NumeroFacture();
        numFactF.setNomTable("V_NUMEROPROFORMA_EN_COURS");
        NumeroFacture[] numFact = (NumeroFacture[]) CGenUtil.rechercher(numFactF, null, null, c, "");
        this.setNumeroProforma(numFact[0].getProchain_num_format());
        return super.createObject(u, c);

    }

    @java.lang.Override
    public java.lang.Object validerObject(java.lang.String u, java.sql.Connection c) throws Exception {
        if (this.getEtat() == 11){
            this.setEtat(12);
        }
        return super.validerObject(u, c);
    }
}
