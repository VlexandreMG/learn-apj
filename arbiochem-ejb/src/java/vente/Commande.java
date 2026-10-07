package vente;

import bean.ClassMere;
import bean.CGenUtil;
import bean.LibelleAffichage;
import proforma.Proforma;
import proforma.ProformaDetailsLib;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;

public class Commande extends ClassMere {
    private String id;
    private String idClient;
    private Date daty;
    private Date dateBesoin;
    private int etat;
    private String remarque;
    private String modepaiement;
    private String reference;
    @LibelleAffichage("D&eacute;signation")
    private String designation;
    private String idDevise;
    private String idMagasin;
    private String idProforma;
    private int modeLivraison;
    private Date dateLivraison;
    private String lieuLivraison;
    private double fraisLivraison;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Date getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Date dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    @Override
    public int getEtat() {
        return etat;
    }

    @Override
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getModepaiement() {
        return modepaiement;
    }

    public void setModepaiement(String modepaiement) {
        this.modepaiement = modepaiement;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) throws Exception {
        if(this.getMode().equals("modif")) {
            if (reference==null||reference.equals(""))throw new Exception("La r\u00E9f\u00E9rence est obligatoire");
        }
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(String idProforma) {
        this.idProforma = idProforma;
    }

    public int getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison(int modeLivraison) {
        this.modeLivraison = modeLivraison;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getLieuLivraison() {
        return lieuLivraison;
    }

    public void setLieuLivraison(String lieuLivraison) {
        this.lieuLivraison = lieuLivraison;
    }

    public double getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(double fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public Commande() throws Exception {
        this.setNomTable("COMMANDE");
        this.setNomClasseFille("vente.CommandeFille");
        this.setLiaisonFille("idc");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CMD","GETseqCOMMANDE");
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

    public void checkProforma(Connection c) throws Exception{
        Proforma[] proformas = (Proforma[]) CGenUtil.rechercher(new Proforma(), null, null, c," and idCommande = '"+this.getId()+"'");
        if(proformas.length > 0){
            throw new Exception("La commande a d\u00e9j\u00e0 une proforma associ\u00e9e");
        }
    }

    public Proforma createProforma()throws Exception{
        Connection c = null;
        boolean canClose=false;
        try {
            if(c==null){
                if(c==null){
                    c=new UtilDB().GetConn();
                    canClose=true;
                }
            }
            Commande commande =(Commande) new Commande().getById(this.getId(),"COMMANDE",c);
            checkProforma(c);
            if(commande!=null) {
                Proforma proforma = new Proforma();
                proforma.setIdCommande(commande.getId());
                if (commande.getIdClient() != null) {
                    proforma.setIdClient(commande.getIdClient());
                }
                if (commande.getIdMagasin() != null) {
                    proforma.setIdMagasin(commande.getIdMagasin());
                }
                if (commande.getIdDevise() != null) {
                    proforma.setIdDevise(commande.getIdDevise());
                }
                proforma.setIdOrigine(commande.getId());
                proforma.setFraislivraison(commande.getFraisLivraison());
                proforma.setRemarque(commande.getRemarque());
                proforma.setDesignation(commande.getDesignation());
                proforma.setReference(commande.getReference());
                proforma.setLieuLivraison(commande.getLieuLivraison());
                return proforma;
            }else {
                throw new Exception("La commande n'\u00e9xiste pas");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(canClose){
                c.close();
            }

        }
    }
    public CommandeFIlleCpl[] getFilleCommandeLib()throws Exception{
        CommandeFIlleCpl filleD = new CommandeFIlleCpl();
        filleD.setIdc(this.getId());
        CommandeFIlleCpl[] val= (CommandeFIlleCpl[])CGenUtil.rechercher(filleD, null, null, "");
        return val;
    }
    public BonDeCommande createBonDeCommande()throws Exception{
        try {
            Commande commande = new Commande();
            commande.setId(this.getId());
            Commande[] resultats = (Commande[]) CGenUtil.rechercher(commande, null, null, "");
            if(resultats.length > 0) {
                commande = resultats[0];
                BonDeCommande bd = new BonDeCommande();
                if (commande.getIdClient() != null) {
                    bd.setIdClient(commande.getIdClient());
                }
                if (commande.getIdMagasin() != null) {
                    bd.setIdMagasin(commande.getIdMagasin());
                }
                if (commande.getIdDevise() != null) {
                    bd.setIdDevise(commande.getIdDevise());
                }
                bd.setIdProforma(commande.getId());
                bd.setDesignation(commande.getDesignation());
                bd.setLieuLivraison(commande.getLieuLivraison());
                bd.setRemarque(commande.getRemarque());
                bd.setReference(commande.getReference());
                bd.setModelivraison(commande.getModeLivraison());
                bd.setFraislivraison(commande.getFraisLivraison());
                return bd;
            }else {
                throw new Exception("La commande n'existe pas");
            }
        }catch(Exception e){
            throw e;
        }
    }

    public CommandeFille[] getFilleCommande()throws Exception{
        CommandeFille cmd = new CommandeFille();
        cmd.setIdc(this.getId());
        CommandeFille[] val= (CommandeFille[])CGenUtil.rechercher(cmd, null, null, " and idC = '"+this.getId()+"'");
        return val;
    }
}

