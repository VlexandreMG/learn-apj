/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paie.avancement;

import bean.CGenUtil;
import bean.ClassEtat;
import bean.ClassMAPTable;
import paie.employe.ConstantePaie;
import paie.employe.EmployeComplet;
import paie.employe.PaieInfoPersonnel;
import paie.log.LogPersonnel;
//import sun.rmi.runtime.Log;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

/**
 *
 * @author ACER
 */
public class PaieAvancement extends ClassEtat {
    private String id,id_logpers,direction,service,idfonction,idcategorie,ctg,remarque,refdecision,motif;
    private Date date_application, datedecision;
    private int echelon,indicegrade,indice_fonctionnel, indice_ct;
    private String classee,matricule_patron,statut,droit_hs, mode_paiement, code_banque;
    private int vehiculee;
    private String region;
    private String contrat;
    private String typePersonnel;
    private int dureeContrat;
    private String modePaiement;
    private String idTypeAvancement;
    private String unite;

    public PaieAvancement(){
        super.setNomTable("PAIE_AVANCEMENT");
    }

    public PaieAvancement(String id_logpers, String direction, String service, String idfonction, String idcategorie, String remarque, String refdecision, Date date_application, Date datedecision, int echelon, int indicegrade, int indice_fonctionnel, String classee, String matricule_patron, String statut, String droit_hs) {
        this.id_logpers = id_logpers;
        this.direction = direction;
        this.service = service;
        this.idfonction = idfonction;
        this.idcategorie = idcategorie;
        this.remarque = remarque;
        this.refdecision = refdecision;
        this.date_application = date_application;
        this.datedecision = datedecision;
        this.echelon = echelon;
        this.indicegrade = indicegrade;
        this.indice_fonctionnel = indice_fonctionnel;
        this.classee = classee;
        this.matricule_patron = matricule_patron;
        this.statut = statut;
        this.droit_hs = droit_hs;
        super.setNomTable("PAIE_AVANCEMENT");
    }
    
    
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RNAT", "getSeqPaie_avancement");
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

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getTypePersonnel() {
        return typePersonnel;
    }

    public void setTypePersonnel(String typePersonnel) {
        this.typePersonnel = typePersonnel;
    }

    public int getDureeContrat() {
        return dureeContrat;
    }

    public void setDureeContrat(int dureeContrat) {
        this.dureeContrat = dureeContrat;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getIndicegrade() {
        return indicegrade;
    }

    public String getCtg() {
        return ctg;
    }

    public void setCtg(String ctg) {
        this.ctg = ctg;
    }

    public void setIndicegrade(int indicegrade) {
        this.indicegrade = indicegrade;
    }

    public int getIndice_fonctionnel() {
        return indice_fonctionnel;
    }

    public void setIndice_fonctionnel(int indicefonctionnel) {
        this.indice_fonctionnel = indicefonctionnel;
    }

    public String getClassee() {
        return classee;
    }

    public void setClassee(String classee) {
        this.classee = classee;
    }

    public String getMatricule_patron() {
        return matricule_patron;
    }

    public void setMatricule_patron(String matricule_patron) {
        this.matricule_patron = matricule_patron;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDroit_hs() {
        return droit_hs;
    }

    public void setDroit_hs(String droit_hs) {
        this.droit_hs = droit_hs;
    }
    
    
    public void setId(String id) {
        this.id = id;
    }

    public void setId_logpers(String id_logpers) throws Exception {
        if ((getMode().compareToIgnoreCase("modif") == 0) && id_logpers.isEmpty()) {
	    throw new Exception("Champ personnel obligatoire");
        }
        this.id_logpers = id_logpers;
    }

    @Override
    public void setService(String service) {
        this.service = service;
    }

    public void setIdfonction(String idfonction) {
        this.idfonction = idfonction;
    }

    public void setIdcategorie(String idcategorie) throws Exception{
        try{
            if(getMode().compareTo("modif")!=0){
                this.idcategorie = idcategorie;
                return;
            }
            if(idcategorie==null || idcategorie.compareToIgnoreCase("")==0){
                throw new Exception("Categorie obligatoire");
            }
            this.idcategorie = idcategorie;
        }catch(Exception e){
        
        }
    }

//    @Override
//    public PaieAvancement validerObject(String u, Connection c) {
//        PaieInfoPersonnel paie = new PaieInfoPersonnel();
//        try {
//            paie.setNomTable("PAIE_INFO_PERSONNEL");
//            paie.setId(this.getId_logpers());
//
//            if (this.getIdfonction() != null) {
//                paie.setIdfonction(this.getIdfonction());
//            }
//
//            if (this.getService() != null) {
//                paie.setService(this.getService());
//            }
//
//            if (this.getCode_banque() != null) {
//                paie.setCode_agence_banque(this.getCode_banque());
//            }
//
//            if (this.getModePaiement() != null) {
//                paie.setMode_paiement(this.getModePaiement());
//            }
//
//            if (this.getTypePersonnel() != null) {
//                paie.setCategorie_qualificationlib(this.getTypePersonnel());
//            }
//
//            paie.updateToTableWithHisto(u, c);
//            super.validerObject(u, c);
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return this;
//    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setRefdecision(String refdecision) {
        this.refdecision = refdecision;
    }

    public void setDate_application(Date date_application) throws Exception {
        if ((getMode().compareToIgnoreCase("modif") == 0) && date_application == null) {
	    throw new Exception("Champ date application obligatoire");
        }
        this.date_application = date_application;
    }

    public void setDatedecision(Date datedecision) {
        this.datedecision = datedecision;
    }

    public void setEchelon(int echelon) throws Exception{
        if(getMode().compareTo("modif")!=0)
        {
            this.echelon = echelon;
            return;
        }
        //if(echelon<0) throw new Exception("Echelon invalide car <0");
        this.echelon = echelon;
    }

    @Override
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    @Override
    public String getDirection(){
        return this.direction;
    }

    public String getId() {
        return id;
    }

    public String getId_logpers() {
        return id_logpers;
    }

    @Override
    public String getService() {
        return service;
    }

    public String getIdfonction() {
        return idfonction;
    }

    public String getIdcategorie() {
        return idcategorie;
    }

    public String getRemarque() {
        return remarque;
    }

    public String getRefdecision() {
        return refdecision;
    }

    public Date getDate_application() {
        return date_application;
    }

    public Date getDatedecision() {
        return datedecision;
    }

    public int getEchelon() {
        return echelon;
    }

    public int getVehiculee() {
        return vehiculee;
    }

    public void setVehiculee(int vehiculee) {
        this.vehiculee = vehiculee;
    }
    
    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) throws Exception {
        if ((getMode().compareToIgnoreCase("modif") == 0) && motif.isEmpty()) {
	    throw new Exception("Champ motif obligatoire");
        }
        this.motif = motif;
    }

    public int getIndice_ct() {
        return indice_ct;
    }

    public void setIndice_ct(int indice_ct) {
        this.indice_ct = indice_ct;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    public String getCode_banque() {
        return code_banque;
    }

    public void setCode_banque(String code_banque) {
        this.code_banque = code_banque;
    }

    public String getIdTypeAvancement() {
        return idTypeAvancement;
    }

    public void setIdTypeAvancement(String idTypeAvancement) {
        this.idTypeAvancement = idTypeAvancement;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementIndice(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(avancement.getIndicegrade());
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(empComp[0].getBanque_numero_compte());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }


    public PaieAvancement genererPaieAvancementTypeAvancementFonction(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(avancement.getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(empComp[0].getBanque_numero_compte());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementRegion(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(avancement.getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(empComp[0].getBanque_numero_compte());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementService(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(avancement.getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(empComp[0].getBanque_numero_compte());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementModePaiement(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(avancement.getModePaiement());
        createdAvancement.setCode_banque(avancement.getCode_banque());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementCompte(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(avancement.getCode_banque());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementContrat(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(empComp[0].getBanque_numero_compte());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(avancement.getContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat(avancement.getDureeContrat());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementAffectation(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(empComp[0].getBanque_numero_compte());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(avancement.getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(empComp[0].getUnite());

        return createdAvancement;
    }

    public PaieAvancement genererPaieAvancementTypeAvancementUnite(PaieAvancement avancement, Connection c) throws Exception
    {
        EmployeComplet ec = new EmployeComplet();
        ec.setId(avancement.getId_logpers());

        EmployeComplet[] empComp = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");

        if (empComp == null || empComp.length == 0)
        {
            throw new Exception("Personnel n'existe deja");
        }

        PaieAvancement createdAvancement = new PaieAvancement();
        createdAvancement.setId_logpers(empComp[0].getId());
        createdAvancement.setDirection(empComp[0].getDirection());
        createdAvancement.setService(empComp[0].getService());
        createdAvancement.setIdfonction(empComp[0].getIdfonction());
        createdAvancement.setIdcategorie(empComp[0].getIdcategorie());
        createdAvancement.setCtg(empComp[0].getCtg());
        createdAvancement.setRemarque(avancement.getRemarque());
        createdAvancement.setRefdecision(avancement.getRefdecision());
        createdAvancement.setMotif(avancement.getMotif());
        createdAvancement.setDate_application(avancement.getDate_application());
        createdAvancement.setDatedecision(avancement.getDatedecision());
//        createdAvancement.setEchelon(avancement.getEchelon());
        createdAvancement.setIndicegrade(Integer.valueOf(empComp[0].getIndicegrade()));
//        createdAvancement.setIndice_fonctionnel(...);
//        createdAvancement.setIndice_ct(...);
//        createdAvancement.setClassee(...);
        createdAvancement.setMatricule_patron(empComp[0].getMatricule_patron());
        createdAvancement.setStatut(empComp[0].getStatut());
        createdAvancement.setDroit_hs(empComp[0].getDroit_hs());
        createdAvancement.setCode_banque(empComp[0].getBanque_numero_compte());
//        createdAvancement.setVehiculee(...);
        createdAvancement.setRegion(empComp[0].getRegion());
        createdAvancement.setContrat(empComp[0].getTypeContrat());
        createdAvancement.setTypePersonnel(empComp[0].getIdcategorie_paie());
        createdAvancement.setDureeContrat((int) empComp[0].getDuree());
        createdAvancement.setModePaiement(empComp[0].getMode_paiement());
        createdAvancement.setIdTypeAvancement(avancement.getIdTypeAvancement());
        createdAvancement.setUnite(avancement.getUnite());

        return createdAvancement;
    }


    public PaieAvancement createPaieAvancement(String u, Connection c) throws Exception
    {

        boolean estOuvert = false;

        try{

            if (c == null)
            {
                c = new UtilDB().GetConn();
                estOuvert = true;
            }

            PaieAvancement avancement = null;

            if (ConstantePaie.TYPE_AVANCEMENT_INDICE.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementIndice(this, c);

            }
            else if (ConstantePaie.TYPE_AVANCEMENT_FONCTION.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementFonction(this, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_COMPTE.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementCompte(this, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_UNITE.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementUnite(this, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_CONTRAT.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementContrat(this, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_REGION.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementRegion(this, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_AFFECTATION.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementAffectation(this, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_MODE_PAIEMENT.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementModePaiement(this, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_SERVICE_SECTION.equals(this.getIdTypeAvancement()))
            {
                avancement = this.genererPaieAvancementTypeAvancementService(this, c);
            }


            if (avancement != null)
            {
                return (PaieAvancement) avancement.createObject(u, c);
            }
            else {
                throw new Exception("Erreur dans createPaieAvancement");
            }


        } catch (Exception e) {
            throw e;
        }
        finally {
            if (estOuvert)
            {
                c.close();
            }
        }
    }

    public void updatePersonnelModePaiement(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setMode_paiement(this.getModePaiement());
        pipForUpdate.setBanque_numero_compte(this.getCode_banque());

        pipForUpdate.updateToTableWithHisto(u, c);

    }

    public void updatePersonnelCompte(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setBanque_numero_compte(this.getCode_banque());

        pipForUpdate.updateToTableWithHisto(u, c);

    }

    public void updatePersonnelContrat(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setTypeContrat(this.getContrat());
        pipForUpdate.setDuree(this.getDureeContrat());

        pipForUpdate.updateToTableWithHisto(u, c);

    }

    public void updatePersonnelFonction(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setIdfonction(this.getIdfonction());

        pipForUpdate.updateToTableWithHisto(u, c);

    }

    public void updatePersonnelUnite(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setUnite(this.getUnite());

        pipForUpdate.updateToTableWithHisto(u, c);

    }

    public void updatePersonnelServiceRattachement(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setService(this.getService());

        pipForUpdate.updateToTableWithHisto(u, c);

    }

    public void updatePersonnelIndice(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setIndicegrade(this.getIndicegrade());

        pipForUpdate.updateToTableWithHisto(u, c);

    }

    public void updatePersonnelAffectation(String u, Connection c) throws Exception
    {
        PaieInfoPersonnel pip = new PaieInfoPersonnel();
        pip.setId(this.getId_logpers());

        PaieInfoPersonnel[] pips = (PaieInfoPersonnel[]) CGenUtil.rechercher(pip, null, null, c, "");

        if (pips == null || pips.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        PaieInfoPersonnel pipForUpdate = pips[0];
        pipForUpdate.setRegion(this.getRegion());

        pipForUpdate.updateToTableWithHisto(u, c);

    }


    public void updatePersonnelRegionDirection(String u, Connection c) throws Exception
    {
        LogPersonnel lp = new LogPersonnel();
        lp.setId(this.getId_logpers());

        LogPersonnel[] lps = (LogPersonnel[]) CGenUtil.rechercher(lp, null, null, c, "");

        if (lps == null || lps.length == 0)
        {
            throw new Exception("Personnel avec id: " + this.getId_logpers() + " n'existe pas");
        }

        LogPersonnel lpForUpdate = lps[0];
        lpForUpdate.setDirection(this.getDirection());

        lpForUpdate.updateToTableWithHisto(u, c);

    }


    public PaieAvancement validateAvanceUpdatePaieInfoPersonnel(String u, Connection c) throws Exception
    {
        boolean estOuvert = false;

        try{

            if (c == null){
                c = new UtilDB().GetConn();
                estOuvert = true;
            }

            if (ConstantePaie.TYPE_AVANCEMENT_INDICE.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelIndice(u, c);

            }
            else if (ConstantePaie.TYPE_AVANCEMENT_FONCTION.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelFonction(u, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_COMPTE.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelCompte(u, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_UNITE.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelUnite(u, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_CONTRAT.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelContrat(u, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_REGION.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelRegionDirection(u, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_AFFECTATION.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelAffectation(u, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_MODE_PAIEMENT.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelModePaiement(u, c);
            }
            else if (ConstantePaie.TYPE_AVANCEMENT_SERVICE_SECTION.equals(this.getIdTypeAvancement()))
            {
                this.updatePersonnelServiceRattachement(u, c);
            }

            return (PaieAvancement) this.validerObject(u, c);

        } catch (Exception e)
        {
            throw e;
        } finally {
            if (estOuvert)
            {
                c.close();
            }
        }


    }

}
