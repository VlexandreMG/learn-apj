/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paie.demande;

import bean.CGenUtil;
import bean.ClassEtat;
import bean.ClassMAPTable;
import bean.TypeObjet;
import paie.conge.Conge;
import paie.conge.MouvementAbsence;
import utilitaire.*;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import paie.conge.CongeDroit;
import paie.conge.CongeMoins;
import paie.demande.EmployeComplet;
import paie.edition.PeriodePaie;
import paie.elementpaie.PaiePersonnelElementpaie;
import paie.employe.PaieInfoPersonnel;
import utils.ConstantePaie;

/**
 *
 * @author Tsiky
 */
public class DemandeJustifications extends ClassEtat {

    String id, idpersonnel, matricule, idtypedemande, titre, nom, prenom, typeabsencelib;
    String horairenormal;
    String heuredepart;
    String heurearrive;
    String idtypeabsence, motif;
    String numero, desce, etatlib;
    Date daty, datedepart, datefin, dateretour;
    String observation, remarque, refuser;
    int rang, rangUser;
    double duree;
    EtatHierarchie etatHierarchie;
    DemandeJustificationVise situationDemandeSuivant;
    String validation, signature;
    double reste_conge;
    String personnel;
    String idremplacents;
    String remplacents;
    String idSup;
    String avenant;
    String annuler;
    double reste_permission;
    double dureefinal;
    String superieur;
    String motifRefu;
    int weekendDecompte;
    String weekendDecompteLib;

     public String getMotifRefu() {
        return motifRefu;
    }

    public void setMotifRefu(String motifRefu) {
        this.motifRefu = motifRefu;
    }

    public DemandeJustifications() {
        setNomTable("demande");
        setEtat(-1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeabsencelib() {
        return typeabsencelib;
    }

    public void setTypeabsencelib(String typeabsencelib) {
        this.typeabsencelib = typeabsencelib;
    }

    public String getIdremplacents() {
        return idremplacents;
    }

    public void setIdremplacents(String idremplacents) {
        this.idremplacents = idremplacents;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getRemplacents() {
        return remplacents;
    }

    public void setRemplacents(String remplacents) {
        this.remplacents = remplacents;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setSignatures(Map param) {
        String[] signatures = Utilitaire.split(this.getSignature(), ";");
        int i = 1;
        for (String sign : signatures) {
            if (sign.compareToIgnoreCase("-") == 0) {
                param.put("signature" + i, null);
                i++;
                continue;
            }
            param.put("signature" + i, ConstantePaie.imagePath + sign);
            i++;
        }
    }

    public double getReste_conge() {
        return reste_conge;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public void setReste_conge(double reste_conge) {
        this.reste_conge = reste_conge;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws Exception {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getIdtypedemande() {
        return idtypedemande;
    }

    public void setIdtypedemande(String idtypedemande) {
        this.idtypedemande = idtypedemande;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getHorairenormal() {
        return horairenormal;
    }

    public void setHorairenormal(String horairenormal) {
        this.horairenormal = horairenormal;
    }

    public String getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(String heuredepart) {
        this.heuredepart = heuredepart;
    }

    public String getHeurearrive() {
        return heurearrive;
    }

    public void setHeurearrive(String heurearrive) {
        this.heurearrive = heurearrive;
    }

    public String getIdtypeabsence() {
        return idtypeabsence;
    }

    public void setIdtypeabsence(String idtypeabsence) {
        this.idtypeabsence = idtypeabsence;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public String getEtatlib() {
//        if (this.getMode().compareTo("select") == 0) {
//            return chaineEtat(getEtat());
//        }
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
//        if (this.getMode().compareTo("select") == 0) {
//            this.etatlib = chaineEtat(getEtat());
//            return;
//        }
        this.etatlib = etatlib;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) throws Exception {
        this.datedepart = datedepart;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) throws Exception {
        this.datefin = datefin;
    }

    public Date getDateretour() {
        return dateretour;
    }

    public void setDateretour(Date dateretour) {
        this.dateretour = dateretour;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getSuperieur() {
        return superieur;
    }

    public void setSuperieur(String superieur) {
        this.superieur = superieur;
    }

    public EtatHierarchie getEtatHierarchie() {
        return etatHierarchie;
    }

    public void setEtatHierarchie(EtatHierarchie etatHierarchie) {
        this.etatHierarchie = etatHierarchie;
    }

    public DemandeJustificationVise getSituationDemandeSuivant() {
        return situationDemandeSuivant;
    }

    public void setSituationDemandeSuivant(DemandeJustificationVise situationDemandeSuivant) {
        this.situationDemandeSuivant = situationDemandeSuivant;
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
    public void controler(Connection c) throws Exception {
//        if(this.getIdtypeabsence().equals(ConstantePaie.id_typeabsenceconge)) {
//            MouvementAbsence mvt = new MouvementAbsence();
//            mvt.setIdPersonnel(this.getIdpersonnel());
//            double resteConge = mvt.getSoldeCongePers();
//            if(this.getDuree() > resteConge) {
//                throw new Exception("Solde conge insuffisant : " + resteConge);
//            }
//        }
//
//        if (this.getMotif().equals("")) {
//            if (!this.getIdtypeabsence().equals(ConstantePaie.id_typeabsenceconge)) {
//                throw new Exception("Veuillez entrer une motif");
//            }
//        }

//        if (this.getAvenant().compareToIgnoreCase("") == 0 || this.getAvenant() == null) {
//            if (this.getDuree() <= 0) {
//                throw new Exception("La duree ne peut pas &egrave;tre inferieur &agrave; 0");
//            }
//        }

//        TypeObjet recherche = new TypeObjet();
//        recherche.setNomTable("typeabsence");
//        TypeObjet[] listeabsence = (TypeObjet[]) CGenUtil.rechercher(recherche, null, null, c, " and id='" + this.getIdtypeabsence() + "'");
//        if (this.getDuree() > Double.valueOf(listeabsence[0].getVal())) {
//            throw new Exception("Le nombre de jour max pour le" + listeabsence[0].getDesce() + " est de :" + listeabsence[0].getVal());
//        }
//        if (this.getAvenant() != null && this.getAvenant().compareToIgnoreCase("") != 0) {
//            double duree = this.getDuree();
//            this.setDuree(duree);
//        }
    }

    public void construirePK(Connection c) throws Exception {
        super.setNomTable("demande");
        this.preparePk("DM", "get_seq_demande");
        this.setId(makePK(c));
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) throws Exception {
//        if (this.getMode().equals("modif") && (duree <= 0) ) {
//            throw new Exception("La duree de la demande est invalide");
//        }
        this.duree = duree;
    }

    public void Congee(String u, Connection c) throws Exception {
        this.enleverSoldeConge(u, c);
    }

    public void autorisation(String u, Connection c)throws Exception {
        this.enleverSoldeConge(u, c);
    }

    public void formation_syndicale(String u, Connection c) throws Exception {
            this.enleverSoldeFormationSyndicale(u, c);
    }

    public void ControlerPermission() throws Exception {
        if (this.getDuree() > this.getReste_permission()) {
            throw new Exception("Dur&eacute;e de permission invalide");
        }
    }

    public void permission(String u, Connection c) throws Exception {
        this.enleverSoldeConge(u, c);

    }

    public void maternite( String u, Connection c) throws Exception {
        this.insertelpMaternite(u,c);
    }


    public void insertelpMaternite(String refUser,Connection c) throws Exception {
        try {
            int dureecg = (int) this.getDuree();
            Date datedebut = this.getDaty();
            Date datefin = Date.valueOf(datedebut.toLocalDate().plusDays(dureecg-1));
            double montantSB =0.0;
            int jourStandard=30;
            String condition =
                    " AND datedebut <= DATE '" + this.getDaty() + "'" +
                            " AND datefin >= DATE '" + this.getDaty() + "'";

            PeriodePaie p = new PeriodePaie();

            PeriodePaie[] periodes = (PeriodePaie[]) CGenUtil.rechercher(p, null, null, c, condition);
            System.out.println("Lenfgth perdio "+periodes.length+ " Date debut periode "+periodes[0].getDatedebut());
            if(periodes.length>0){
                //periodes[0].setDatedebut(datedebut);
                //periodes[periodes.length-1].setDatefin(datefin);
                PaieInfoPersonnel crt = new PaieInfoPersonnel();
                crt.setNomTable("PAIE_INFO_PERSONNELDIRECT");
                crt.setId(this.getIdpersonnel());
                PaieInfoPersonnel[] liste=(PaieInfoPersonnel[]) CGenUtil.rechercher(crt, null, null, c, "");
                if (liste.length>0) montantSB = liste[0].getSalaire_Base();
                //System.out.println("this.getDaty()===>"+this.getDaty());
                System.out.println("liste[0].getDate_debut()===>"+periodes[0].getDatedebut()+ " Demande de conge "+this.getDaty());
                int nbJourNiasa=Utilitaire.diffJourDaty(this.getDaty(),periodes[0].getDatedebut());
                double proportionJourNiasa=(double)nbJourNiasa/jourStandard;
                System.out.println(("Jour niasa "+nbJourNiasa));
                int nombreMoisAhazoana=3;

                if(nbJourNiasa>=0)
                {
                    nombreMoisAhazoana = 4;
                    PaiePersonnelElementpaie temp = new PaiePersonnelElementpaie();
                    temp.setCode_rubrique(ConstantePaie.idSalaireBasePaie);
                    temp.setDate_debut(periodes[0].getDatedebut());
                    temp.setDate_fin(periodes[0].getDatefin());



                    double montantGainNeg = montantSB*(nbJourNiasa)/30;

                    temp.setGain(montantGainNeg);
                    temp.setRetenue(0);
                    temp.setRemarque("");
                    temp.setIdpersonnel(this.getIdpersonnel());
                    temp.setEtat(11);
                    temp.setId_objet(null);
                    temp.setIduser(refUser);
                    //temp.setMoisregularisation(Utilitaire.getMois(temp.getDate_debut().toString()));
                    //temp.setAnneeregularisation(String.valueOf(Utilitaire.getAnnee(temp.getDate_fin())));
                    temp.construirePK(c);
                    temp.insertToTableWithHisto(refUser, c);
                }
                double[]coefficient=new double[nombreMoisAhazoana];
                coefficient[0]=1.0-proportionJourNiasa;
                coefficient[1]=1.0;
                coefficient[2]=1.0;
                if(nbJourNiasa>=0)coefficient[3]=1-coefficient[0];
                for(int i=0;i<nombreMoisAhazoana;i++){
                    PaiePersonnelElementpaie temp = new PaiePersonnelElementpaie();
                    temp.setCode_rubrique(ConstantePaie.id_maternite);
                    temp.setDate_debut(Utilitaire.ajoutMoisDate(periodes[0].getDatedebut(),i));
                    temp.setDate_fin(Utilitaire.ajoutMoisDate(periodes[0].getDatefin(),i));



                    double montantGainMat = montantSB*coefficient[i]/2;

                    temp.setGain(montantGainMat);
                    temp.setRetenue(0);
                    temp.setRemarque("");
                    temp.setIdpersonnel(this.getIdpersonnel());
                    temp.setEtat(11);
                    temp.setId_objet(null);
                    temp.setIduser(refUser);
                    //temp.setMoisregularisation(Utilitaire.getMois(temp.getDate_debut().toString()));
                    //temp.setAnneeregularisation(String.valueOf(Utilitaire.getAnnee(temp.getDate_fin())));
                    temp.construirePK(c);
                    temp.insertToTableWithHisto(refUser, c);
                }

            }else{
                throw new Exception("Periode de paie inexistante pour ce date de conge de maternite");
            }

        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        EmployeComplet ec = new EmployeComplet();
        ec.setNomTable("EMPLOYE_COMPLET2");
        ec.setId(this.getIdpersonnel());
        EmployeComplet[] employe = (EmployeComplet[]) CGenUtil.rechercher(ec, null, null, c, "");
        if (employe.length == 0) {
            throw new Exception("Employ\\351 introuvable");
        }
        if (this.getIdtypeabsence().compareToIgnoreCase(paie.employe.ConstantePaie.conge) == 0) {
            this.Congee(u, c);
        } else if (this.getIdtypeabsence().compareToIgnoreCase(paie.employe.ConstantePaie.permission) == 0) {
            this.permission(u, c);
        }else if (this.getIdtypeabsence().compareToIgnoreCase(paie.employe.ConstantePaie.maternite) == 0) {
            this.maternite(u, c);
        }else if (this.getIdtypeabsence().compareToIgnoreCase(paie.employe.ConstantePaie.formation_syndicale) == 0) {
            this.formation_syndicale( u, c);
        }
        return super.validerObject(u, c);
    }


    public void congeCollectif(String u, Connection c) {

    }

    public void enleverSoldeConge(String u, Connection c) throws Exception {
        try {
            MouvementAbsence mvt = new MouvementAbsence();
            mvt.setIdSource(this.getIdtypeabsence());
            mvt.setIdPersonnel(this.getIdpersonnel());
            mvt.setDateDebut(this.getDatedepart());
            mvt.setMois(Utilitaire.getMois(this.getDatedepart()) - 1);
            mvt.setAnnee(Utilitaire.getAnnee(this.getDatedepart()));
            mvt.setMoins(this.getDuree());
            double soldeActuel = mvt.getSoldeCongePers();
//            if ( soldeActuel < this.duree){
//                throw new Exception("Demande de cong\\u00E9 ou de permission sup\\u00E9rieur \\u00E0 solde actuelle qui est de :" + soldeActuel + " jours");
//            }
            if (this.getRemarque() == null || this.getRemarque().isEmpty()) {
                mvt.setRemarque("Mouvement abscence du " + this.getDaty() + " de employe: " + this.getIdpersonnel());
            } else {
                mvt.setRemarque(this.getRemarque());
            }
            mvt.setMotif(this.getMotif());
            mvt.setIdRemplacant(this.getIdremplacents());
            mvt.setDateDemande(this.getDaty());
            mvt.setIdType(paie.employe.ConstantePaie.conge);
//            mvt.construirePK(c);
            System.out.println(" dans enlever solde conge ");
            mvt.createObject(u, c);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void enleverSoldeFormationSyndicale(String u, Connection c) throws Exception {
        try {
            MouvementAbsence mvt = new MouvementAbsence();
            mvt.setIdSource(this.getIdtypeabsence());
            mvt.setIdPersonnel(this.getIdpersonnel());
            mvt.setDateDebut(this.getDatedepart());
            mvt.setMois(Utilitaire.getMois(this.getDatedepart()) - 1);
            mvt.setAnnee(Utilitaire.getAnnee(this.getDatedepart()));
            mvt.setMoins(this.getDuree());
            double soldeActuel = mvt.getSoldeFormationSyndicale();
            if (soldeActuel < this.duree) {
                throw new Exception("Le solde de formation actuel du personnel est de "
                        + soldeActuel + " jours, insuffisant pour une demande de " + this.getDuree() + " jours.");
            }
            if (this.getRemarque() == null || this.getRemarque().isEmpty()) {
                mvt.setRemarque("Mouvement abscence du " + this.getDaty() + " de employe: " + this.getIdpersonnel());
            } else {
                mvt.setRemarque(this.getRemarque());
            }
            mvt.setMotif(this.getMotif());
            mvt.setIdRemplacant(this.getIdremplacents());
            mvt.setDateDemande(this.getDaty());
            mvt.setIdType(paie.employe.ConstantePaie.formation_syndicale);
//            mvt.construirePK(c);
            mvt.createObject(u, c);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    public void updateWithHistorique(HistoriqueDemande historique, int etatvisa, Connection c) throws Exception {
        try {
            historique.setEtatDemande(etatvisa);
            this.setEtat(etatvisa);
            historique.construirePK(c);
            this.updateToTableWithHisto(historique, c);
        } catch (Exception e) {
            throw e;
        }
    }

    public String chaineEtat(int value) {
        if (value == ConstanteEtatPaie.getEtatCreer()) {
            return "<b style=color:lightskyblue>CR&Eacute;&Eacute;(E)</b>";
        }
        if (value == ConstanteEtatPaie.getEtatValider()) {
            return "<b style=color:green>VIS&Eacute;(E)</b>";
        }
        if (value == ConstanteEtatPaie.getEtatAnnuler()) {
            return "<b style=color:orange>ANNUL&Eacute;(E)</b>";
        }
        if (value == ConstanteEtatPaie.getEtatValiderParDG()) {
            return "<b style=color:lightskyblue>VALID&Eacute;(E) PAR DG</b>";
        }
        if (value == ConstanteEtatPaie.getEtatRefuserParDG()) {
            return "<b style=color:red>REFUS&Eacute;(E) PAR DG</b>";
        }
        if (value == ConstanteEtatPaie.getEtatValiderParDE()) {
            return "<b style=color:lightskyblue>VALID&Eacute;(E) PAR DRCF</b>";
        }
        if (value == ConstanteEtatPaie.getEtatRefuserParDE()) {
            return "<b style=color:red>REFUS&Eacute;(E) PAR DRCF</b>";
        }
        if (value == ConstanteEtatPaie.getEtatValiderParCH()) {
            return "<b style=color:lightskyblue>VALID&Eacute;(E) PAR CH</b>";
        }
        if (value == ConstanteEtatPaie.getEtatRefuserParCH()) {
            return "<b style=color:red>REFUS&Eacute;(E) PAR CH</b>";
        }
        if (value == ConstanteEtatPaie.getEtatValiderParDemandeur()) {
            return "<b style=color:lightskyblue>VALID&Eacute;(E) PAR DEMANDEUR</b>";
        }
        if (value == ConstanteEtatPaie.getEtatRefuserParDemandeur()) {
            return "<b style=color:red>REFUS&Eacute;(E) PAR DEMANDEUR</b>";
        }
        if (value == ConstanteEtatPaie.getEtatValiderParRH()) {
            return "<b style=color:lightskyblue>VALID&Eacute;(E) PAR RH</b>";
        }
        if (value == ConstanteEtatPaie.getEtatRefuserParRH()) {
            return "<b style=color:red>REFUS&Eacute;(E) PAR RH</b>";
        }
        return null;
    }

    @Override
    public String toString() {
        return "DemandeJustification{" + "id=" + id + ", idpersonnel=" + idpersonnel + ", idtypedemande=" + idtypedemande + ", titre=" + titre + ", nom=" + nom + ", prenom=" + prenom + ", typeabsencelib=" + typeabsencelib + ", horairenormal=" + horairenormal + ", heuredepart=" + heuredepart + ", heurearrive=" + heurearrive + ", idtypeabsence=" + idtypeabsence + ", motif=" + motif + ", numero=" + numero + ", desce=" + desce + ", etatlib=" + etatlib + ", daty=" + daty + ", datedepart=" + datedepart + ", dateretour=" + dateretour + ", observation=" + observation + ", remarque=" + remarque + ", duree=" + duree + '}';
    }

    public String getRefuser() {
        return refuser;
    }

    public void setRefuser(String refuser) {
        this.refuser = refuser;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getRangUser() {
        return rangUser;
    }

    public void setRangUser(int rangUser) {
        this.rangUser = rangUser;
    }

    public boolean containsId(EmployeComplet[] emps, String idToCheck) {
        if (emps == null || idToCheck == null) {
            return false;
        }
        return Arrays.stream(emps)
                .anyMatch(emp -> idToCheck.equals(emp.getId()));
    }

    public boolean estCloturee(Connection c) throws Exception {
        boolean estCloturee = false;
        if (this.getEtat() == 11) {
            estCloturee = true;
        }
        return estCloturee;
    }

    public DemandeJustifications getRempWithId(String id, Connection c) throws Exception {
        try {
            DemandeJustifications ed = new DemandeJustifications();
            ed.setNomTable("demande_libcomplet");
            DemandeJustifications[] dem = (DemandeJustifications[]) CGenUtil.rechercher(ed, null, null, c, " and id='" + id + "'");
            if (dem.length == 0) {
                throw new Exception("Demande non trouver");
            }
            return dem[0];
        } catch (Exception e) {
            throw e;
        }
    }

    public String getIdSup() {
        return idSup;
    }

    public void setIdSup(String idSup) {
        this.idSup = idSup;
    }

    public String getAvenant() {
        return avenant;
    }

    public void setAvenant(String avenant) {
        this.avenant = avenant;
    }
    
    public String getAnnuler() {
        return annuler;
    }

    public void setAnnuler(String annuler) {
        this.annuler = annuler;
    }

    public double getReste_permission() {
        return reste_permission;
    }

    public void setReste_permission(double reste_permission) {
        this.reste_permission = reste_permission;
    }

    public double getDureefinal() {
        return dureefinal;
    }

    public void setDureefinal(double dureefinal) {
        this.dureefinal = dureefinal;
    }

    public String qweryList(Date datemin, Date datemax, String idP, String etat) {
        String requette = "";
        String et = " and etat = " + etat;
        if (etat == null || etat.compareToIgnoreCase("") == 0) {
            et = "";
        }
        requette = " select " +
                "  idpersonnel,  " +
                "  matricule, " +
                "  idfonction, " +
                "  fonctionlib, " +
                "  idtypedemande, " +
                "  typedemandelib, " +
                "  titre, " +
                "  horairenormal, " +
                "  idtypeabsence, " +
                "  typeabsencelib, " +
                "  nom, " +
                "  prenom, " +
                "  refuser, " +
                "  ranguser, " +
                "  idsup, " +
                "  annuler, " +
                "  SUM(duree)::numeric(32,0) as dureefinal, " +
                "  etat, " +
                "  iddirection, " +
                "  directionlib, " +
                "  etatlib " +
                " from " +
                "  demande_libcomplet  " +
                "  where daty >= TO_Date('" + datemin + "','YYYY/MM/DD') and daty <= TO_Date('" + datemax
                + "','YYYY/MM/DD') " +
                et +
                " and ( idpersonnel = '" + idP + "' or  idSup = '" + idP + "')" +
                " group by " +
                "  idpersonnel, " +
                "  matricule, " +
                "  idfonction, " +
                "  fonctionlib, " +
                "  idtypedemande, " +
                "  typedemandelib, " +
                "  iddirection, " +
                "  directionlib, " +
                "  titre, " +
                "  horairenormal, " +
                "  nom, " +
                "  prenom, " +
                "  idsup, " +
                "  etatlib, " +
                "  refuser, " +
                "  ranguser, " +
                "  idtypeabsence, " +
                "  typeabsencelib, " +
                "  annuler, " +
                "  etat ";
        return requette;
    }

    public DemandeJustifications[] getDemandeJustifications(Date datemin, Date datemax, String idP, String etat)throws Exception {
        DemandeJustifications dem = new DemandeJustifications();
        dem.setNomTable("demande_vide");
        String requString = this.qweryList(datemin, datemax, idP, etat);
        System.out.println(requString);
        DemandeJustifications[] demande = (DemandeJustifications[]) CGenUtil.rechercher(dem, requString);
        return demande;
    }

    public void controllerDateFinEtRetour() throws Exception{
         int val = Utilitaire.compareDaty(this.getDatedepart(), this.getDateretour());
        System.out.println(" val " + val);
//         if(val==1) {
//             throw new Exception("La date de d\\u00E9part doit \\u00EAtre inf\\u00E9rieure \\u00E0 la date de retour");
//         }

//        this.datefin = Utilitaire.ajoutJourDate(this.datedepart, (int)Math.ceil(this.duree));
//        while(!Utilitaire.estJourOuvrable(this.datefin)){
//            this.datefin = Utilitaire.ajoutJourDate(this.datefin,1);
//        }
//        this.dateretour = this.datefin;
    }

    public double differenceEnJours(Date dateDepart, Date dateRetour) throws Exception {
        LocalDate d1 = dateDepart.toLocalDate();
        LocalDate d2 = dateRetour.toLocalDate();

        long diff = ChronoUnit.DAYS.between(d1, d2);
        // Même date = demi-journée
        if (diff == 0) {
            return 0.5;
        }
        return (double) diff;
    }

    public double differenceEnJoursSansWeekend(Date dateDepart, Date dateRetour) throws Exception {
        LocalDate d1 = dateDepart.toLocalDate();
        LocalDate d2 = dateRetour.toLocalDate();

        if (d1.isAfter(d2)) {
            throw new Exception("La date de d\u00E9part doit \u00EAtre inf\u00E9rieure \u00E0 la date de retour");
        }
        // On ne compte pas le jour de retour
        LocalDate finCalcul = d2.minusDays(1);
        double jours = 0;
        LocalDate courant = d1;

        while (!courant.isAfter(finCalcul)) {
            DayOfWeek jourSemaine = courant.getDayOfWeek();
            if (jourSemaine != DayOfWeek.SATURDAY && jourSemaine != DayOfWeek.SUNDAY) {
                jours++;
            }
            courant = courant.plusDays(1);
        }

        // Cas meme jour (départ = retour)
        if (jours == 0 && d1.equals(d2)) {
            return 0.5;
        }
        return jours;
    }


    @Override
    public ClassMAPTable createObject(String user, Connection c) throws Exception {
        this.controllerDateFinEtRetour();
        double duree = this.getDuree();
//        if(this.getWeekendDecompte()==1) {
////           si weekend decompte dia makato
//            duree = this.differenceEnJours(this.getDatedepart(), this.getDateretour());
//        }else {
//            duree = this.differenceEnJoursSansWeekend(this.getDatedepart(), this.getDateretour());
//        }
        this.setDuree(duree);
        return super.createObject(user, c);
    }

    public ClassMAPTable refuser(String u) throws Exception {
//        this.controllerDateFinEtRetour();
//        return super.createObject(user, c);
        System.out.println(" id ++++ " + this.getId());
        DemandeJustifications res = DemandeJustifications.getDemandeById(this.getId());
        res.setMotifRefu(this.getMotifRefu());
        res.setEtat(-1);
        res.updateToTableWithHisto(u);
        return res;
    }


    @Override
    public int updateToTableWithHisto(String refUser, Connection c) throws Exception {
        this.controllerDateFinEtRetour();
        return super.updateToTableWithHisto(refUser, c);
    }
    public double getAvantResteconge() throws SQLException, Exception{
          MouvementAbsence mvt = new MouvementAbsence();
          mvt.setIdPersonnel(this.getIdpersonnel());
          double resteConge = mvt.getSoldeCongePers();
          return resteConge;
    }

    public double getApresResteconge() throws SQLException, Exception{
        double reste = this.getAvantResteconge() - this.getDuree();
        return reste;
    }

    public static DemandeJustifications getDemandeById(String id) throws Exception {
         Connection c = null;
        DemandeJustifications demande = null;
         try {
             if (c == null) {
                 c = new UtilDB().GetConn();
             }

             demande =(DemandeJustifications) new DemandeJustifications().getById(id, "DEMANDE", c);
             return demande;
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             if(c!=null) {
                 c.close();
             }
         }
           return demande;
    }
    public java.util.Map<String, Integer> getNombreAbsenceTypeJourMoisAnnee(String idtypeabsence, String j, String mois,String annee) throws Exception {
        Connection con = null;
        java.util.Map<String, Integer> result = new java.util.LinkedHashMap<>();
        try {
            con = (new UtilDB()).GetConn();
            StringBuilder query = new StringBuilder("SELECT TRUNC(daty) as daty, duree  FROM DEMANDE_LIBCOMPLET WHERE 1=1");

            if(idtypeabsence != null && !idtypeabsence.isEmpty()) {
                query.append(" AND IDTYPEABSENCE = '").append(idtypeabsence).append("'");
            }
            if(j != null) {
                query.append(" AND EXTRACT(DAY FROM daty) = "+j);
            }if(mois != null) {
                query.append(" AND EXTRACT(MONTH FROM daty) = "+mois);
            }if(mois != null) {
                query.append(" AND EXTRACT(YEAR FROM daty) = "+annee);
            }

            query.append(" GROUP BY TRUNC(daty),duree ORDER BY TRUNC(daty),duree ");

            java.sql.Statement stmt = con.createStatement();
            System.out.println("REAAAAA = "+query.toString());
            java.sql.ResultSet rs = stmt.executeQuery(query.toString());
            while(rs.next()) {
                String date = rs.getString("daty");
                int nombre = rs.getInt("duree");
                result.put(date, nombre);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(con != null) con.close();
        }
        return result;
    }

    public java.util.Map<String, Integer> getNombreAbsenceTypeDepartementPeriode(String idtypeabsence, String iddepartement, Date min, Date max) throws Exception {
        Connection con = null;
        java.util.Map<String, Integer> result = new java.util.LinkedHashMap<>();
        try {
            con = (new UtilDB()).GetConn();
            StringBuilder query = new StringBuilder(
                    "SELECT TRUNC(daty) as daty, SUM(duree) as total_duree " +
                            "FROM DEMANDE_LIBCOMPLET3 WHERE 1=1 "
            );
            if (idtypeabsence != null && !idtypeabsence.isEmpty()) {
                query.append(" AND IDTYPEABSENCE = ? ");
            }
            if (iddepartement != null && !iddepartement.isEmpty()) {
                query.append(" AND IDDEPARTEMENT = ? ");
            }
            if (min != null) {
                query.append(" AND daty >= ? ");
            }
            if (max != null) {
                query.append(" AND daty <= ? ");
            }
            query.append(" GROUP BY TRUNC(daty) ORDER BY TRUNC(daty)");
            PreparedStatement pstmt = con.prepareStatement(query.toString());
            int index = 1;
            if (idtypeabsence != null && !idtypeabsence.isEmpty()) {
                pstmt.setString(index++, idtypeabsence);
            }
            if (iddepartement != null && !iddepartement.isEmpty()) {
                pstmt.setString(index++, iddepartement);
            }
            if (min != null) {
                pstmt.setDate(index++, new java.sql.Date(min.getTime()));
            }
            if (max != null) {
                pstmt.setDate(index++, new java.sql.Date(max.getTime()));
            }
            System.out.println("REQUETE = " + query.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String date = rs.getString("daty");
                int total = rs.getInt("total_duree");
                result.put(date, total);
            }
            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (con != null) con.close();
        }
        return result;
    }

    public int getWeekendDecompte() {
        return weekendDecompte;
    }

    public void setWeekendDecompte(int weekendDecompte) {
        this.weekendDecompte = weekendDecompte;
    }

    public String getWeekendDecompteLib() {
        return weekendDecompteLib;
    }

    public void setWeekendDecompteLib(String weekendDecompteLib) {
        this.weekendDecompteLib = weekendDecompteLib;
    }
}