package fabrication;

import bean.CGenUtil;
import bean.ClassFille;
import bean.ClassMAPTable;
import bean.ClassEtat;
import paie.CategorieQualification;
import paie.edition.PeriodePaie;
import paie.elementpaie.PaiePersonnelElementpaie;
import paie.employe.ConstantePaie;
import paie.employe.PaieInfoPersonnel;
import paie.hs.HsMois;
import utilitaire.UtilDB;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;

import utils.ConstanteSocobis;

import personnel.Personnel;
import utilitaire.Utilitaire;

public class HeureSupFabrication extends ClassEtat {
    String id, idRessParFab, idPersonne, idFabrication, idClasseDefaut, idClasseEffective, matricule, remarque,poste,equipe ;
    double HS, MN, JF, HD, IF, tauxHoraire, tauxHoraireEffective, heurenormale, montant, totalHeureSemaine, heureDejaCumulees;
    int temporaire;
    Date dateFabrication;
    String idQualification;
    double montantJF, montantMN, montantHD, montantIF, montantHS, montantHeureSupp;
    Date daty;
    double hs30, hs50;
    int mois, annee;
    String of;
    Date dateImport;

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = Utilitaire.dateDuJourSql();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTemporaire() {
        return temporaire;
    }

    public void setTemporaire(int temporaire) {
        this.temporaire = temporaire;
    }

    public double getHeurenormale() {
        return heurenormale;
    }

    public void setHeurenormale(double heurenormale) {
        this.heurenormale = heurenormale;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getIdRessParFab() {
        return idRessParFab;
    }

    public void setIdRessParFab(String idRessParFab) {
        this.idRessParFab = idRessParFab;
    }

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(String idFabrication) {
        this.idFabrication = idFabrication;
    }

    public String getIdClasseDefaut() {
        return idClasseDefaut;
    }

    public void setIdClasseDefaut(String idClasseDefaut) {
        this.idClasseDefaut = idClasseDefaut;
    }

    public String getIdClasseEffective() {
        return idClasseEffective;
    }

    public void setIdClasseEffective(String idClasseEffective) {
        this.idClasseEffective = idClasseEffective;
    }

    public double getHS() {
        return HS;
    }

    public void setHS(double HS) {
        this.HS = HS;
    }

    public double getMN() {
        return MN;
    }

    public void setMN(double MN) {
        this.MN = MN;
    }

    public double getJF() {
        return JF;
    }

    public void setJF(double JF) {
        this.JF = JF;
    }

    public double getHD() {
        return HD;
    }

    public void setHD(double HD) {
        this.HD = HD;
    }

    public double getIF() {
        return IF;
    }

    public void setIF(double IF) {
        this.IF = IF;
    }

    public double getTauxHoraire() throws Exception {
        PaieInfoPersonnel tmp = new PaieInfoPersonnel();
        tmp.setNomTable("PAIE_INFO_PERSONNEL_SB");
        tmp.setId(this.getIdPersonne());
        PaieInfoPersonnel[] details = (PaieInfoPersonnel[]) CGenUtil.rechercher(tmp, null, null, "");
//        System.out.println(" details " + details.length + details[0].getId() + " montant " + details[0].getMontant());
        double tauxHoraire = 0;
        if (details != null && details.length > 0) {
//            System.out.println("1 " + details[0].getSalaire_Base() + " 2 " + details[0].getSalaire_base() + " 3 " + details[0].getSalaire_de_base());
            tauxHoraire = details[0].getMontant() / 173.33;
            return tauxHoraire;
        }
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getTauxHoraireEffective() {
        return tauxHoraireEffective;
    }

    public void setTauxHoraireEffective(double tauxHoraireEffective) {
        this.tauxHoraireEffective = tauxHoraireEffective;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getAttributIDName() {
        return "id";
    }

    public String getTuppleID() {
        return id;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("HSFab", "getseqheureSupFabrication");
        this.setId(makePK(c));
    }

    public HeureSupFabrication() {
        this.setNomTable("heureSupFabrication");
    }

    public double getMontantHS() throws Exception {
        WeekFields weekFields = WeekFields.ISO;
        LocalDate localDate = this.getDateFabrication().toLocalDate();
        int year = localDate.get(weekFields.weekBasedYear());
        int week = localDate.get(weekFields.weekOfWeekBasedYear());
        String semaineAnnee = year + "-" + week;
        HeureSupSemaine hs = new HeureSupSemaine();
        hs.setSemaine(semaineAnnee);
        hs.setIdPersonne(this.getIdPersonne());
        hs.setAnnee(year);
        HeureSupSemaine[] res = (HeureSupSemaine[]) CGenUtil.rechercher(hs, null, null, "");
        double tauxHoraire = this.getTauxHoraire();
        double montantHS = 0;
        if(res != null && res.length > 0) {
            double hs30 = res[0].getHs30();
            double hs50 = res[0].getHs50();
            if(hs30 < 8) {
                double resteHs30 = 8 - hs30;
                if(resteHs30 >= this.getHS()) {
                    montantHS += this.HS * ConstanteSocobis.POURC_HS30NI * tauxHoraire;
                } else {
                    montantHS += resteHs30 * ConstanteSocobis.POURC_HS30NI * tauxHoraire;
                    double resteAjouter50 = this.getHS() - resteHs30;
                    montantHS += resteAjouter50 * ConstanteSocobis.POURC_HS50NI *  tauxHoraire;
                }
            } else {
                montantHS += this.getHS() * ConstanteSocobis.POURC_HS50NI * tauxHoraire;
            }
        } else {
            if(this.getHS() <= 8) {
                montantHS += this.getHS() * ConstanteSocobis.POURC_HS30NI * tauxHoraire;
            } else {
                double hs50Plus = this.getHS() - 8;
                montantHS += hs50Plus * ConstanteSocobis.POURC_HS50NI * tauxHoraire;
            }
        }
        this.setMontantHS(montantHS);
        return montantHS;
    }

    //  calcul hs hoe 30% non imposable, 50% NI
    public double calculMontantHs(double totalHeureSemaine) throws Exception {
        double hs30NI = Math.min(totalHeureSemaine, ConstanteSocobis.maxHS30NI) * this.getTauxHoraire() * ConstanteSocobis.POURC_HS30NI;

        double hs50 = Math.max(totalHeureSemaine - ConstanteSocobis.maxHS30NI, 0);
        double hs50NI = Math.min(hs50, ConstanteSocobis.plafondNI) * this.getTauxHoraire() * ConstanteSocobis.POURC_HS50NI; ;

        double montantTotal = hs30NI + hs50NI;
        return montantTotal;
    }

    public double calculMontantHsJour(double heuresHSJour, double heuresDejaCumulees) throws Exception {
        double montant = 0;
        double resteHS30 = Math.max(ConstanteSocobis.maxHS30NI - heuresDejaCumulees, 0);

        double hs30DuJour = Math.min(heuresHSJour, resteHS30);
        montant += hs30DuJour * this.getTauxHoraire() * ConstanteSocobis.POURC_HS30NI;

        double reste = heuresHSJour - hs30DuJour;

        if (reste > 0) {
            double deja50 = Math.max(heuresDejaCumulees - ConstanteSocobis.maxHS30NI, 0);
            double restePlafond50 = Math.max(ConstanteSocobis.plafondNI - deja50, 0);

            double hs50DuJour = Math.min(reste, restePlafond50);
            montant += hs50DuJour * this.getTauxHoraire() * ConstanteSocobis.POURC_HS50NI;
        }
        return montant;
    }


    public double getMontantMN() throws Exception {
        return this.MN * ConstanteSocobis.POURC_MN * this.getTauxHoraire();
    }

    public double getMontantJF() throws Exception {
        return this.JF * ConstanteSocobis.POURC_JF * this.getTauxHoraire();
    }

    public double getMontantHD() throws Exception {
        return this.HD * ConstanteSocobis.POURC_HD * this.getTauxHoraire();
    }

    public double getMontantIF() throws Exception {
        return this.IF * (this.getTauxHoraire() - this.tauxHoraireEffective);
    }

    public double getMontantHeureNormale() throws Exception { return this.heurenormale * this.getTauxHoraire(); }

    public double getMontantTotalHS() throws Exception {
        HeureSupFabrication sup = new HeureSupFabrication();
        sup.setNomTable("HEURESUPFABRICATION_CPL");
        sup.setId(this.getId());
        HeureSupFabrication[] res = (HeureSupFabrication[]) CGenUtil.rechercher(sup, null, null, "");
        double montant = 0;
        if(res != null && res.length > 0){
//            montant = res[0].getMontantHS();
//            if(temporaire == 1){
//                montant = res[0].getMontantHeureNormale();
//            }
            PaieInfoPersonnel pip = new PaieInfoPersonnel();
            pip.setId(res[0].getIdPersonne());
            double tauxhoraire = pip.getTauxHoraire();
            this.setTauxHoraire(tauxhoraire);

            PaieInfoPersonnel pers = (PaieInfoPersonnel) new PaieInfoPersonnel().getById(res[0].getIdPersonne(), "PAIE_INFO_PERSONNEL", null);

            CategorieQualification categ = new CategorieQualification();
            categ.setIdcategorie(pers.getIdcategorie());
            categ.setIdqualification(res[0].getIdQualification());
            categ.setRemarque("");
            CategorieQualification[] resCateg = (CategorieQualification[]) CGenUtil.rechercher(categ, null, null, "");
            double tauxHoraireEffective = 0;
            if(resCateg != null && resCateg.length > 0){
                if(tauxHoraireEffective < tauxhoraire) {
                    this.setTauxHoraireEffective(tauxhoraire);
                } else {
                    tauxHoraireEffective = resCateg[0].getMontant() / 173.33;
                    this.setTauxHoraireEffective(tauxHoraireEffective);
                }
            }
            return montant + res[0].getMontantMN() + res[0].getMontantJF() + res[0].getMontantHD() + res[0].getMontantIF();
        } else {
            throw new Exception("Erreur pendant la validation!");
        }
    }

    public void checkHS(Connection c)throws Exception{
        RessourceParFabrication hsp = new RessourceParFabrication();
        hsp.setNomTable("ressourceParFabrication");
        hsp.setId(this.getIdRessParFab());
        RessourceParFabrication[] hs = (RessourceParFabrication[]) CGenUtil.rechercher(hsp,null,null, "");

        HeureSupFabricationCPL crt = new HeureSupFabricationCPL();
        crt.setNomTable("heureSupFabrication_cpl_visee");
        crt.setIdFabrication(hs[0].getIdFabrication());
        crt.setIdPersonne(hs[0].getIdRessource());
        HeureSupFabricationCPL [] rep = (HeureSupFabricationCPL[]) CGenUtil.rechercher(crt,null,null, "");
        if(rep.length>0){
            for(int i=0;i<rep.length;i++){
                if(rep[i].getEtat() > 1){
                    throw new Exception("REF : "+this.getIdRessParFab()+", On ne peut pas faire des HS sur une meme fabrication!");
                }
            }
        }
    }

    @Override
    public void controler(Connection c) throws Exception {
        super.controler(c);
//        this.checkHS(c);
    }

    public String getPersonnelParHS(Connection c) throws Exception {
        HeureSupFabrication tmp = new HeureSupFabrication();
        tmp.setId(this.getId());
        tmp.setNomTable("HEURESUPFABRICATION_CPL");
        HeureSupFabrication[] hs = (HeureSupFabrication[]) CGenUtil.rechercher(tmp, null, null, c,"");
        if(hs != null && hs.length>0) {
            return hs[0].getIdPersonne();
        } else {
            throw new Exception(" Personnel introuvale pour la ligne " + this.getId());
        }
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception{
        this.controler(c);
//        this.setMontant(this.getMontantTotalHS());
        HeureSupFabricationCPL tmp = new HeureSupFabricationCPL();
        tmp.setId(this.getId());
        HeureSupFabricationCPL[] hs = (HeureSupFabricationCPL[]) CGenUtil.rechercher(tmp, null, null, c, "");
        double taux = hs[0].getTauxHoraire();
        double cumulSemaine = getCumulSemaine(c);
        double resteHS30 = Math.max(8 - cumulSemaine, 0);

        double hs30 = Math.min(this.HS, resteHS30);
        double hs50 = Math.max(this.HS - resteHS30, 0);
        double montant =
                (hs30 * 1.3 + hs50 * 1.5) * taux;
//        System.out.println(" hs ++++++ " +(hs30 * 1.3 + hs50 * 1.5) * taux );
        this.setMontantHeureSupp(montant);
        double montantHD = this.getMontantHD();
        double montantIF = this.getMontantIF();
        double montantJF = this.getMontantJF();
        double montantMN = this.getMontantMN();

        this.setMontantHD(montantHD);
        this.setMontantIF(montantIF);
        this.setMontantJF(montantJF);
        this.setMontantMN(montantMN);
        double total = montant + this.getMontantTotalHS();

        this.setTauxHoraire(this.getTauxHoraire());
        this.setMontant(total);
        this.updateToTableWithHisto(u, c);
//        this.insererElementsPaie(u, c);

        return super.validerObject(u, c);
    }

    public void insererElementsPaie(String u, Connection c) throws Exception {
        try {

            PeriodePaie periode = new PeriodePaie();
            periode.setAnnee(this.getDaty().toLocalDate().getYear());
            periode.setMois(String.valueOf(this.getDaty().toLocalDate().getMonthValue()));


            PeriodePaie[] periodes = (PeriodePaie[]) CGenUtil.rechercher(periode, null, null, c, "");

            if (periodes == null || periodes.length == 0) {
                throw new Exception("Période de paie introuvable");
            }

            PeriodePaie p = periodes[0];

            String idPersonnel = this.getPersonnelParHS(c);
            System.out.println(" dans insert element de paie +++++++++++++");

            if (this.getMontantHD() > 0) {
                System.out.println(" dans insert element de paie 11111 +++++++++++++");

                insertElt(u, c, idPersonnel, p, ConstantePaie.rub_hd, this.getMontantHD(), this.getHD());
            }

            if (this.getMontantIF() > 0) {
                System.out.println(" dans insert element de paie 22222 +++++++++++++");

                insertElt(u, c, idPersonnel, p, ConstantePaie.rub_if, this.getMontantIF(), this.getIF());
            }

            if (this.getMontantJF() > 0) {
                System.out.println(" dans insert element de paie 3333 +++++++++++++");

                insertElt(u, c, idPersonnel, p, ConstantePaie.rub_jf, this.getMontantJF(), this.getJF());
            }

            if (this.getMontantMN() > 0) {
                System.out.println(" dans insert element de paie 444444 +++++++++++++");

                insertElt(u, c, idPersonnel, p, ConstantePaie.rub_mn, this.getMontantMN(), this.getMN());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertElt(String u, Connection c, String idPersonnel, PeriodePaie p, String codeRubrique, double montant, double quantite) throws Exception {

        PaiePersonnelElementpaie elem = new PaiePersonnelElementpaie();

        elem.setIdpersonnel(idPersonnel);
        elem.setDate_debut(p.getDatedebut());
        elem.setDate_fin(p.getDatefin());
        elem.setMoisregularisation(p.getMois().toString());
        elem.setAnneeregularisation(String.valueOf(p.getAnnee()));
        elem.setId_objet(this.getIdFabrication());
        elem.setCode_rubrique(codeRubrique);
        elem.setGain(montant);
        elem.setQuantite(quantite);
        elem.setEtat(11);
        elem.setDatesaisie(Utilitaire.dateDuJourSql());
        elem.construirePK(c);
        elem.insertToTableWithHisto(u, c);
    }

    public FabricationCpl getFabrication(Connection c) throws Exception {
        String id = this.getIdFabrication();
        try {
            c = new UtilDB().GetConn();
            FabricationCpl crt=new FabricationCpl();
            crt.setNomTable("FABRICATIONCPL");
            crt.setId(id);
            FabricationCpl[] rep = (FabricationCpl[])CGenUtil.rechercher(crt,null,null,c,"");
            return rep[0];
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public Personnel getPersonne(Connection c) throws Exception {
        String id = this.getIdPersonne();
        try {
            c = new UtilDB().GetConn();
            Personnel crt=new Personnel();
            crt.setId(id);
            Personnel[] rep = (Personnel[])CGenUtil.rechercher(crt,null,null,c,"");
            return rep[0];
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }


    public double getCumulSemaine(Connection c) throws Exception {
        RessourceParFabrication ress = (RessourceParFabrication) new RessourceParFabrication().getById(this.getIdRessParFab(), "RESSOURCEPARFABRICATION", c);

        String semaine = this.getSemaineISO(this.getDaty());

        HsMois hs = new HsMois();
        hs.setNomTable("VW_HS_CUMUL_WEEK");
        String aWhere =  "AND IDPERSONNEL = '" + ress.getIdRessource() + "' " +
                "AND SEMAINE = '" + semaine + "'";

        HsMois[] hsTotalMois = (HsMois[]) CGenUtil.rechercher(hs, null, null, c, aWhere);

        if(hsTotalMois != null && hsTotalMois.length>0) {
            return hsTotalMois[0].getTotal_hs();
        }
        return 0;

    }

    public static String getSemaineISO(Date date) throws Exception {

        if (date == null) {
            throw new Exception("Date is null, impossible de calculer la semaine ISO");
        }

        LocalDate localDate = date.toLocalDate();

        WeekFields weekFields = WeekFields.ISO;

        int year = localDate.get(weekFields.weekBasedYear());
        int week = localDate.get(weekFields.weekOfWeekBasedYear());

        return year + "-" + String.format("%02d", week);
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTotalHeureSemaine() {
        return totalHeureSemaine;
    }

    public void setTotalHeureSemaine(double totalHeureSemaine) {
        this.totalHeureSemaine = totalHeureSemaine;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public double getHeureDejaCumulees() {
        return heureDejaCumulees;
    }

    public void setHeureDejaCumulees(double heureDejaCumulees) {
        this.heureDejaCumulees = heureDejaCumulees;
    }

    public String getIdQualification() {
        return idQualification;
    }

    public void setIdQualification(String idQualification) {
        this.idQualification = idQualification;
    }

    public void setMontantJF(double montantJF) {
        this.montantJF = montantJF;
    }

    public void setMontantMN(double montantMN) {
        this.montantMN = montantMN;
    }

    public void setMontantHD(double montantHD) {
        this.montantHD = montantHD;
    }

    public void setMontantIF(double montantIF) {
        this.montantIF = montantIF;
    }

    public void setMontantHS(double montantHS) {
        this.montantHS = montantHS;
    }

    public double getHs30() {
        return hs30;
    }

    public void setHs30(double hs30) {
        this.hs30 = hs30;
    }

    public double getHs50() {
        return hs50;
    }

    public void setHs50(double hs50) {
        this.hs50 = hs50;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getMontantHeureSupp() {
        return montantHeureSupp;
    }

    public void setMontantHeureSupp(double montantHeureSupp) {
        this.montantHeureSupp = montantHeureSupp;
    }
}