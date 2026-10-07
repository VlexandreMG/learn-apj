package rapport;

import bean.AdminGen;
import bean.CGenUtil;
import fabrication.FabricationRecapJournalier;
import fabrication.Of;
import fabrication.OfFilleCpl;
import faturefournisseur.FactureFournisseurRecapJournalier;
import maintenance.planning.PlanningCpl;
import maintenance.planning.PlanningCr;
import paie.avance.Avance;
import prevision.AdminPrevision;
import prevision.Prevision;
import reporting.mail.MailContent;
import reporting.mail.MailRappel;
import reporting.mail.PageMail;
import reporting.utilitaire.ConstanteCR;
import stock.EtatStock;
import stock.EtatStockParEntreeStandard;
import utilitaire.Utilitaire;
import vente.VenteDetailsLib;
import vente.VenteRecapJournalier;
import caisse.EtatCaisse;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CRJournalier {

    private  String to = "fitiarazafinjoelina10@gmail.com"; //ravel@malagasy.com
    private  String[] cc = new String[]{"fitia.razafinjoelina@bici.mg","baovolarajaonarison@gmail.com"}; //du.socobis@blueline.mg

    private String getConditionPeriode(
            String colonneDate,
            Date dateDebut,
            Date dateFin
    ) {
        StringBuilder condition = new StringBuilder();

        if (dateDebut != null) {
            condition.append(" AND ")
                    .append(colonneDate)
                    .append(" >= DATE '")
                    .append(dateDebut)
                    .append("'");
        }

        if (dateFin != null) {
            condition.append(" AND ")
                    .append(colonneDate)
                    .append(" < DATE '")
                    .append(Utilitaire.ajoutJourDate(dateFin, 1))
                    .append("'");
        }

        return condition.toString();
    }

    private  void sendMail(String object, String message, String to, String[] cc) throws Exception {
        Mail mail = new Mail(ConstanteCR.getMailRapport()[0], ConstanteCR.getMailRapport()[1], object, message);
        mail.setTo(to);
        if (cc != null) {
            mail.setCc(cc);
        }

        mail.send();
//        System.out.printf("Le message est : " + message);
    }

    public void sendMailRappel() throws Exception {
        sendMail("Objtect Mail Rappele", contentMailRappel(), getTo(), getCc());
    }

    private  String contentMailRappel() throws  Exception {
        MailRappel mailRappel = new MailRappel();

        mailRappel.setLienLogo("https://images.unsplash.com/photo-1563013544-824ae1b704d3?w=300");
        mailRappel.setNom("Digital Business");
        mailRappel.setTitre("Rappel de votre facture");

        mailRappel.setParagraphe(new String[]{
                "Nous vous remercions de votre confiance. La facture n°[id-facture] reste en attente de règlement.",
                "Le montant dû est de [montant] et la date limite de paiement est fixée au [date].",
                "Pour toute assistance concernant cette facture, notre service client est à votre disposition."
        });

        mailRappel.setContacts(new String[]{
                "Digital Business",
                "+261 38 00 000 00",
                "facturation@digitalbusiness.com"
        });

        Map<String, Object> dataMail = new HashMap<>();
        dataMail.put("id-facture", "INV-2026-1042");
        dataMail.put("montant", "2 450 000 Ar");
        dataMail.put("date", "20/07/2026");

        mailRappel.setData(dataMail);

        return  mailRappel.genererMailRappel();
    }

    public void sendPageEmailHebdomadaire(
            Date dateDebut,
            Date dateFin
    ) throws Exception {

        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException(
                    "Les dates de début et de fin sont obligatoires"
            );
        }

        if (dateDebut.after(dateFin)) {
            throw new IllegalArgumentException(
                    "La date de début doit être antérieure à la date de fin"
            );
        }

        String objet =
                "SOCOBIS - Compte rendu hebdomadaire du "
                        + dateDebut
                        + " au "
                        + dateFin;

        StringBuilder message = new StringBuilder();

        message.append(getTexteHebdomadaire(
                dateDebut,
                dateFin
        ));

        message.append("<div class='section'>");
        message.append("<h2>Maintenance</h2>");
        message.append("<p>");
        message.append("Récapitulatif des opérations de maintenance de la semaine.");
        message.append("</p>");
        message.append(contentMaintenance(dateDebut));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Achats</h2>");
        message.append("<p>");
        message.append("Récapitulatif des approvisionnements de la semaine.");
        message.append("</p>");
        message.append(contentAchat(dateDebut, dateFin));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Ventes</h2>");
        message.append("<p>");
        message.append("Situation des ventes réalisées au cours de la semaine.");
        message.append("</p>");
        message.append(contentVente(dateDebut, dateFin));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Fabrication</h2>");
        message.append("<p>");
        message.append("Synthèse des opérations de fabrication de la semaine.");
        message.append("</p>");
        message.append(contentFabrication(dateDebut, dateFin));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Stock</h2>");
        message.append("<p>");
        message.append("Situation du stock à la fin de la période.");
        message.append("</p>");
        message.append(contentStock(dateDebut, dateFin));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Prevision</h2>");
        message.append("<p>Résultat prévisionnel des 3 mois prochains </p>");
        message.append(contentPrevision(dateFin));
        message.append("</div>");

        message.append("<div class='footer'>");
        message.append(
                "Ce message a été généré automatiquement "
                        + "par le système d'information."
        );
        message.append("</div>");

        message.append("</div>");
        message.append("</body>");
        message.append("</html>");

        sendMail(
                objet,
                message.toString(),
                getTo(),
                getCc()
        );
    }


    private String  contentAchat(Date datejour) throws  Exception {
        String[] libEntete = new String[]{"daty","nombre","montantttc", "montantpaye", "montantreste", "montantresteacejour"};
        String[] libEnteteAffiche = new String[] {"Date", "Nombre", "Montant TTC", "Montant Pay&eacute;", "Montant restant", "Montant restant &agrave; ce jour"};

        FactureFournisseurRecapJournalier ffrecap = new FactureFournisseurRecapJournalier();

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[1];
        MailContent mailContent = new MailContent(ffrecap, "Récapitulation des Achats", libEntete);
        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        mailContents[0] = mailContent;

        String datetest = "2026-06-30";

        return pageMail.genererPageMailAWhere(mailContents, null, "and daty = DATE '" + datejour + "'");
    }
    private String contentAchat(Date dateDebut, Date dateFin)
            throws Exception {

        String[] libEntete = {
                "nombre",
                "montantttc",
                "montantpaye",
                "montantreste",
                "montantresteacejour"
        };

        String[] libEnteteAffiche = {
                "Nombre",
                "Montant TTC",
                "Montant Pay&eacute;",
                "Montant restant",
                "Montant restant &agrave; ce jour"
        };

        FactureFournisseurRecapJournalier ffrecap =
                new FactureFournisseurRecapJournalier();

        FactureFournisseurRecapJournalier[] ff = (FactureFournisseurRecapJournalier[]) CGenUtil.rechercher(ffrecap,ffrecap.getRequeteHebdomadaire(dateDebut,dateFin));
        PageMail pageMail = new PageMail();

        MailContent[] mailContents = new MailContent[1];

        MailContent mailContent = new MailContent(
                ffrecap,
                "Récapitulation des achats",
                libEntete
        );

        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        mailContent.setDataDirect(ff);
        mailContents[0] = mailContent;

        String condition = getConditionPeriode(
                "daty",
                dateDebut,
                dateFin
        );

        return pageMail.genererPageMailAWhere(
                mailContents,
                null,
                condition
        );
    }

    private  String  contentFabrication(Date datejour) throws  Exception {
        //String datetest = "2026-07-13";

        String[] libEntete1 = new String[]{"nombre","qte", "qtefabrique", "qtereste"};
        String[] libEnteteAffiche1 = new String[] {"Nombre", "Somme des Quantités d'Ordre", "Somme des quantités fabriquées", "Somme des quantités restantes"};
        String[] libEntete2 = new String[]{"idIngredients", "libelleexacte", "idunite","qte","qteFabrique","qteReste","pv","montantentree","montantsortie","tauxRevient","puRevient","montantRevient"};
        String[] libEnteteAffiche2 = new String[]{"Composants","D&eacute;signation", "Unit&eacute;","Quantit&eacute; Ordre","Quantit&eacute; Fabriqu&eacute;e","Quantit&eacute; restante","Prix de vente","Valeur fabriqu&eacute;e","D&eacute;penses de Fabrication","Taux de revient ( en %)","Prix de revient","Montant Th&eacute;orique"};
        String[] libEntete3 = new String[]{"pourc"};
        String[] libEnteteAffiche3 = new String[] {"Taux de revient ( en %)"};

        double taux=0;
        int count=0;
        FabricationRecapJournalier frecap = new FabricationRecapJournalier();
        OfFilleCpl ofFilleCpl = new OfFilleCpl();
        ofFilleCpl.setNomTable("OfFilleLibStock");
        ofFilleCpl.calculerRevient(null);
        OfFilleCpl[] off = (OfFilleCpl[]) CGenUtil.rechercher(ofFilleCpl,null,null,null,"AND daty = DATE '" + datejour + "'");
        if(off.length>0){
            Of o = new Of();
            o.setFille(off);
            o.calculerRevient(null);
            for(OfFilleCpl f : (OfFilleCpl[]) o.getFille()){
                count++;
                taux+=f.getTauxRevient();
            }
        }
        OfFilleCpl[] tauxMoyen = new OfFilleCpl[1];
        tauxMoyen[0] = new OfFilleCpl();
        tauxMoyen[0].setNomTable("OfFilleLibStock");
        tauxMoyen[0].setPourc(taux/count);
        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[3];
        MailContent mailContent1 = new MailContent(frecap, "Récapitulation des Fabrications", libEntete1);
        MailContent mailContent2 = new MailContent(ofFilleCpl, "Détails des Fabrications",libEntete2);
        MailContent mailContent3 = new MailContent(ofFilleCpl, "Taux de revient moyen (en %)",libEntete3);
        mailContent2.setDataDirect(off);
        mailContent3.setDataDirect(tauxMoyen);
        mailContent1.setLibEnteteAffiche(libEnteteAffiche1);
        mailContent2.setLibEnteteAffiche(libEnteteAffiche2);
        mailContent3.setLibEnteteAffiche(libEnteteAffiche3);
        mailContents[0] = mailContent1;
        mailContents[1] = mailContent2;
        mailContents[2] = mailContent3;



        return pageMail.genererPageMailAWhere(mailContents, null, "and daty = DATE '" + datejour + "'");
    }
    private String contentFabrication(
            Date dateDebut,
            Date dateFin
    ) throws Exception {

        String[] libEntete1 = {
                "nombre",
                "qte",
                "qtefabrique",
                "qtereste"
        };

        String[] libEnteteAffiche1 = {
                "Nombre",
                "Somme des quantités d'ordre",
                "Somme des quantités fabriquées",
                "Somme des quantités restantes"
        };

        String[] libEntete2 = {
                "idIngredients",
                "libelleexacte",
                "idunite",
                "qte",
                "qteFabrique",
                "qteReste",
                "pv",
                "montantentree",
                "montantsortie",
                "tauxRevient",
                "puRevient",
                "montantRevient"
        };

        String[] libEnteteAffiche2 = {
                "Composants",
                "D&eacute;signation",
                "Unit&eacute;",
                "Quantit&eacute; ordre",
                "Quantit&eacute; fabriqu&eacute;e",
                "Quantit&eacute; restante",
                "Prix de vente",
                "Valeur fabriqu&eacute;e",
                "D&eacute;penses de fabrication",
                "Taux de revient (en %)",
                "Prix de revient",
                "Montant théorique"
        };

        String[] libEntete3 = {"pourc"};

        String[] libEnteteAffiche3 = {
                "Taux de revient moyen (en %)"
        };

        FabricationRecapJournalier recap =
                new FabricationRecapJournalier();

        OfFilleCpl filtre = new OfFilleCpl();
        filtre.setNomTable("OfFilleLibStock");

        String condition = getConditionPeriode(
                "daty",
                dateDebut,
                dateFin
        );

        OfFilleCpl[] lignes =
                (OfFilleCpl[]) CGenUtil.rechercher(
                        filtre,
                        null,
                        null,
                        null,
                        condition
                );

        double sommeTaux = 0.0;
        int nombreTaux = 0;

        if (lignes.length > 0) {
            Of of = new Of();
            of.setFille(lignes);
            of.calculerRevient(null);

            lignes = (OfFilleCpl[]) of.getFille();

            for (OfFilleCpl ligne : lignes) {
                sommeTaux += ligne.getTauxRevient();
                nombreTaux++;
            }
        }

        double moyenne = nombreTaux == 0
                ? 0.0
                : Utilitaire.arrondir(
                sommeTaux / nombreTaux,
                2
        );

        OfFilleCpl[] tauxMoyen = new OfFilleCpl[1];
        tauxMoyen[0] = new OfFilleCpl();
        tauxMoyen[0].setPourc(moyenne);

        PageMail pageMail = new PageMail();
        MailContent[] contents = new MailContent[3];

        MailContent recapContent = new MailContent(
                recap,
                "Récapitulation des fabrications",
                libEntete1
        );

        MailContent detailsContent = new MailContent(
                filtre,
                "Détails des fabrications",
                libEntete2
        );

        MailContent moyenneContent = new MailContent(
                filtre,
                "Taux de revient moyen",
                libEntete3
        );

        recapContent.setLibEnteteAffiche(
                libEnteteAffiche1
        );

        detailsContent.setLibEnteteAffiche(
                libEnteteAffiche2
        );

        moyenneContent.setLibEnteteAffiche(
                libEnteteAffiche3
        );

        detailsContent.setDataDirect(lignes);
        moyenneContent.setDataDirect(tauxMoyen);

        contents[0] = recapContent;
        contents[1] = detailsContent;
        contents[2] = moyenneContent;

        return pageMail.genererPageMailAWhere(
                contents,
                null,
                condition
        );
    }

    private  String contentVente(Date datejour) throws  Exception {

        String[] libEntete = new String[]{"daty","nombre","montantttc", "montantpaye", "montantreste", "montantresteacejour"};
        String[] libEnteteAffiche = new String[] {"Date","Nombre", "Montant TTC", "Montant Pay&eacute;", "Montant Restant", "Montant restant &agrave; ce jour"};
        String[] libEntete2 = new String[]{"daty","idProduitLib","puTotal", "puRevient"};
        String[] libEnteteAffiche2 = new String[] {"Date","Produit", "Montant", "Taux par rapport aux total des ventes (en %)"};

        VenteRecapJournalier ventesrecap = new VenteRecapJournalier();
        VenteDetailsLib venteDetailsLib = new VenteDetailsLib();
        venteDetailsLib.setNomTable("VENTE_DETAILS_CPL_2_VISEE");
        VenteDetailsLib[] venteDetailsLibs = (VenteDetailsLib[]) CGenUtil.rechercher(venteDetailsLib,venteDetailsLib.getRequetePourcentage(datejour,datejour));
        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[2];

        MailContent mailContent = new MailContent(ventesrecap, "Récapitulation des ventes", libEntete);
        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        MailContent mailContent2 = new MailContent(venteDetailsLib, "Analyse des ventes par produit", libEntete2);
        mailContent2.setDataDirect(venteDetailsLibs);
        mailContent2.setLibEnteteAffiche(libEnteteAffiche2);
        mailContents[0] = mailContent;
        mailContents[1] = mailContent2;



        return pageMail.genererPageMailAWhere(mailContents, null, "and daty = DATE '" + datejour + "'");
    }
    private String contentVente(Date dateDebut, Date dateFin)
            throws Exception {

        String[] libEntete = {
                "nombre",
                "montantttc",
                "montantpaye",
                "montantreste",
                "montantresteacejour"
        };

        String[] libEnteteAffiche = {
                "Nombre",
                "Montant TTC",
                "Montant Pay&eacute;",
                "Montant restant",
                "Montant restant &agrave; ce jour"
        };

        String[] libEntete2 = {
                "idProduitLib",
                "puTotal",
                "puRevient"
        };

        String[] libEnteteAffiche2 = {
                "Produit",
                "Montant",
                "Taux par rapport au total des ventes (en %)"
        };

        VenteRecapJournalier ventesRecap =
                new VenteRecapJournalier();

        VenteRecapJournalier[] venteRecapJournaliers = (VenteRecapJournalier[]) CGenUtil.rechercher(ventesRecap,ventesRecap.getRequeteHebdomadaire(dateDebut,dateFin));

        VenteDetailsLib venteDetailsLib =
                new VenteDetailsLib();

        venteDetailsLib.setNomTable(
                "VENTE_DETAILS_CPL_2_VISEE"
        );

        VenteDetailsLib[] details =
                (VenteDetailsLib[]) CGenUtil.rechercher(
                        venteDetailsLib,
                        venteDetailsLib.getRequetePourcentageGroupe(
                                dateDebut,
                                dateFin
                        )
                );

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[2];

        MailContent recapContent = new MailContent(
                ventesRecap,
                "Récapitulation des ventes",
                libEntete
        );

        recapContent.setLibEnteteAffiche(
                libEnteteAffiche
        );
        recapContent.setDataDirect(venteRecapJournaliers);

        MailContent detailsContent = new MailContent(
                venteDetailsLib,
                "Analyse des ventes par produit",
                libEntete2
        );

        detailsContent.setDataDirect(details);
        detailsContent.setLibEnteteAffiche(
                libEnteteAffiche2
        );

        mailContents[0] = recapContent;
        mailContents[1] = detailsContent;

        String condition = getConditionPeriode(
                "daty",
                dateDebut,
                dateFin
        );

        return pageMail.genererPageMailAWhere(
                mailContents,
                null,
                condition
        );
    }
    private String contentStock(Date dateDebut, Date dateFin)
            throws Exception {

        if (dateFin == null) {
            throw new IllegalArgumentException(
                    "La date de fin est obligatoire pour calculer le stock"
            );
        }

        String[] libEntete = {
                "idTypeProduitLib",
                "reste"
        };

        String[] libEnteteAffiche = {
                "Cat&eacute;gorie",
                "Reste en Ar"
        };

        EtatStock etatStock =
                new EtatStock();

        /*
         * Un état de stock représente une situation à une date donnée.
         * Pour un rapport hebdomadaire, on prend donc la date de fin.
         */
        EtatStock[] donneesStock =
                etatStock.getEtatDeStockParCategorie(dateFin);

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[1];

        MailContent mailContent = new MailContent(
                etatStock,
                "Situation du stock au " + dateFin,
                libEntete
        );

        mailContent.setLibEnteteAffiche(libEnteteAffiche);

        if (donneesStock != null) {
            mailContent.setDataDirect(donneesStock);
        } else {
            mailContent.setDataDirect(
                    new EtatStockParEntreeStandard[0]
            );
        }

        mailContents[0] = mailContent;

        /*
         * Les données sont déjà récupérées directement avec
         * getEtatDeStock(dateFin), donc aucun WHERE supplémentaire
         * n'est nécessaire.
         */
        return pageMail.genererPageMailAWhere(
                mailContents,
                null,
                ""
        );
    }

    private  String  contentStock(Date datejour) throws  Exception {
        String libEntete[] = {"idTypeProduitLib","reste"};
        String libEnteteAffiche[] = {"Cat&eacute;gorie","Reste en AR"};

        EtatStock t = new EtatStock();

        EtatStock[] etatStockParcateg = t.getEtatDeStockParCategorie(datejour);

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[1];

        MailContent mailContent = new MailContent(t, "Stock", libEntete);
        mailContent.setDataDirect(etatStockParcateg);
        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        mailContents[0] = mailContent;

        return pageMail.genererPageMailAWhere(mailContents, null, "and daty = DATE '" + datejour + "'");
    }
    private  String  contentEtatCaisse() throws  Exception {
        String libEntete[] = {"idCaisselib","idPointlib","idTypeCaisselib","dateDernierReport","montantDernierReport","credit","debit", "reste"};
        String libEnteteAffiche[] = {"Caisse","Point","Type de Caisse","Date du dernier report","Solde initial","Crédit","Débit", "Solde Final"};
    
        EtatCaisse t = new EtatCaisse();

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[1];

        MailContent mailContent = new MailContent(t, "caisse", libEntete);
        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        mailContents[0] = mailContent;

        return pageMail.genererPageMailAWhere(mailContents, null, "");
    }

    private  String  contentPaie(Date datejour) throws  Exception {
        String libEntete[] = {"id", "idpersonnel","matricule", "montant","interet", "nbremboursement","etatlib"};
        String libEnteteAffiche[] =  {"ID", "Personnel","Matricule", "Montant(Ar)","Int&eacute;r&ecirc;t (%)","Nombre de remboursement","&Eacute;tat"};

        Avance lv = new Avance();
        lv.setNomTable("AVANCE_LIB2");

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[1];

        MailContent mailContent = new MailContent(lv, "Avances", libEntete);
        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        mailContents[0] = mailContent;

        return pageMail.genererPageMailAWhere(mailContents, null, "and DATEAVANCE = DATE '" + datejour + "'");
    }

    private String contentMaintenance(Date dateJour) throws Exception{
        String dateDuJour = new SimpleDateFormat("dd/MM/yyyy").format(dateJour);
        String libEntete[] = {"nombreDemande", "nombreRealise","pourcentage"};
        String libEnteteAffiche[] =  {"Nombre de demandes", "Nombre de r&eacute;alisations","Pourcentage"};

        PlanningCr pl = new PlanningCr();
        pl.setNomTable("PLANNING_CR");
        PlanningCr[] data = pl.rapprochementCr(dateDuJour);

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[1];

        MailContent mailContent = new MailContent(pl, "Rapprochement de réalisation", libEntete);
        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        mailContent.setDataDirect(data);
        mailContents[0] = mailContent;
        return pageMail.genererPageMailAWhere(mailContents, null, "");
    }

    private String contentPrevision(Date dateJour) throws Exception{
        String grouper = "mois";

        String dateFormatee = new SimpleDateFormat("dd/MM/yyyy").format(dateJour);
        String moisDefaut = Utilitaire.getMois(dateFormatee);
        String anneeDefaut = Utilitaire.getAnnee(dateFormatee);

        String[] debutFinDefaut = Utilitaire.getBorneDatyMoisAnnee(moisDefaut, anneeDefaut);

        String defaultDatyDebut = debutFinDefaut[0];
        String defaultDatyFin = new SimpleDateFormat("dd/MM/yyyy").format(utilitaire.Utilitaire.addMonth(dateJour,3));

        String defaultDatyFiltre = dateFormatee;
        String[] libEntete = {"daty","soldeInitial", "debit", "credit", "soldeFinale"};
        String[] libEnteteAffiche = {"Date","Solde initial", "d&eacute;pense", "recette", "Solde final"};

        Prevision minimum = new Prevision();

        AdminPrevision ap = new AdminPrevision();
        ap.getPrevision(defaultDatyFiltre, defaultDatyDebut, defaultDatyFin,grouper);

        PageMail pageMail = new PageMail();
        MailContent[] mailContents = new MailContent[1];

        MailContent mailContent = new MailContent(minimum, "R&eacute;sultat pr&eacute;visionnel", libEntete);
        mailContent.setLibEnteteAffiche(libEnteteAffiche);
        mailContent.setDataDirect(ap.getListePrev());
        mailContents[0] = mailContent;
        return pageMail.genererPageMailAWhere(mailContents, null, "");
    }
    public String getTexteHebdomadaire(Date dateDebut, Date dateFin) {
        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException(
                    "La date de début et la date de fin sont obligatoires"
            );
        }

        if (dateDebut.after(dateFin)) {
            throw new IllegalArgumentException(
                    "La date de début ne peut pas être postérieure à la date de fin"
            );
        }

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");

        html.append("<style>");

        html.append("body{");
        html.append("font-family:Arial,Helvetica,sans-serif;");
        html.append("font-size:14px;");
        html.append("color:#333333;");
        html.append("background:#f5f5f5;");
        html.append("margin:0;");
        html.append("padding:20px;");
        html.append("}");

        html.append(".container{");
        html.append("max-width:1100px;");
        html.append("margin:auto;");
        html.append("background:#ffffff;");
        html.append("padding:25px;");
        html.append("border:1px solid #dddddd;");
        html.append("}");

        html.append("h1{");
        html.append("font-size:22px;");
        html.append("margin:0 0 10px;");
        html.append("color:#222222;");
        html.append("}");

        html.append("h2{");
        html.append("font-size:17px;");
        html.append("margin:30px 0 12px;");
        html.append("padding-bottom:6px;");
        html.append("border-bottom:2px solid #444444;");
        html.append("color:#222222;");
        html.append("}");

        html.append("p{");
        html.append("margin:8px 0;");
        html.append("line-height:1.6;");
        html.append("}");

        html.append(".intro{");
        html.append("margin-bottom:25px;");
        html.append("}");

        html.append(".periode{");
        html.append("background:#f3f3f3;");
        html.append("border-left:4px solid #444444;");
        html.append("padding:10px 15px;");
        html.append("margin:15px 0;");
        html.append("}");

        html.append(".section{");
        html.append("margin-top:20px;");
        html.append("}");

        html.append(".footer{");
        html.append("margin-top:35px;");
        html.append("font-size:12px;");
        html.append("color:#777777;");
        html.append("border-top:1px solid #dddddd;");
        html.append("padding-top:15px;");
        html.append("}");

        html.append("table{");
        html.append("width:100%;");
        html.append("border-collapse:collapse;");
        html.append("margin-top:10px;");
        html.append("}");

        html.append("th,td{");
        html.append("border:1px solid #dcdcdc;");
        html.append("padding:8px;");
        html.append("text-align:left;");
        html.append("}");

        html.append("th{");
        html.append("background:#eeeeee;");
        html.append("}");

        html.append("</style>");
        html.append("</head>");

        html.append("<body>");
        html.append("<div class='container'>");

        html.append("<h1>Compte rendu hebdomadaire</h1>");

        html.append("<div class='intro'>");

        html.append("<p>Bonjour,</p>");

        html.append("<p>");
        html.append("Veuillez trouver ci-dessous le compte rendu ");
        html.append("hebdomadaire des activités.");
        html.append("</p>");

        html.append("<div class='periode'>");
        html.append("<strong>Période :</strong> du ");
        html.append(dateDebut);
        html.append(" au ");
        html.append(dateFin);
        html.append("</div>");

        html.append("<p>");
        html.append("Ce rapport présente une synthèse des opérations ");
        html.append("d'achat, de vente, de fabrication, de stock, ");
        html.append("de caisse, de paie, de maintenance et de prévision ");
        html.append("enregistrées au cours de la semaine.");
        html.append("</p>");

        html.append("<div class='periode'>");
        html.append("<strong>Remarque :</strong><br/>");
        html.append("Ce compte rendu est généré automatiquement par <strong>Async</strong>.<br/>");
        html.append("La véracité des informations présentées dépend des données enregistrées par les utilisateurs dans le système.");
        html.append("</div>");

        html.append("</div>");

        return html.toString();
    }

    public String getTexte(Date datejour) {

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");

        html.append("<style>");
        html.append("body{");
        html.append("font-family:Arial,Helvetica,sans-serif;");
        html.append("font-size:14px;");
        html.append("color:#333333;");
        html.append("background:#f5f5f5;");
        html.append("margin:0;");
        html.append("padding:20px;");
        html.append("}");

        html.append(".container{");
        html.append("max-width:1100px;");
        html.append("margin:auto;");
        html.append("background:#ffffff;");
        html.append("padding:25px;");
        html.append("border:1px solid #dddddd;");
        html.append("}");

        html.append("h1{");
        html.append("font-size:22px;");
        html.append("margin:0 0 10px;");
        html.append("color:#222222;");
        html.append("}");

        html.append("h2{");
        html.append("font-size:17px;");
        html.append("margin:30px 0 12px;");
        html.append("padding-bottom:6px;");
        html.append("border-bottom:2px solid #444444;");
        html.append("color:#222222;");
        html.append("}");

        html.append("p{");
        html.append("margin:8px 0;");
        html.append("line-height:1.6;");
        html.append("}");

        html.append(".intro{");
        html.append("margin-bottom:25px;");
        html.append("}");

        html.append(".section{");
        html.append("margin-top:20px;");
        html.append("}");

        html.append(".footer{");
        html.append("margin-top:35px;");
        html.append("font-size:12px;");
        html.append("color:#777777;");
        html.append("border-top:1px solid #dddddd;");
        html.append("padding-top:15px;");
        html.append("}");

        html.append("table{");
        html.append("width:100%;");
        html.append("border-collapse:collapse;");
        html.append("margin-top:10px;");
        html.append("}");

        html.append("th,td{");
        html.append("border:1px solid #dcdcdc;");
        html.append("padding:8px;");
        html.append("text-align:left;");
        html.append("}");

        html.append("th{");
        html.append("background:#eeeeee;");
        html.append("}");
        html.append("</style>");

        html.append("</head>");
        html.append("<body>");
        html.append("<div class='container'>");

        html.append("<h1>Compte rendu journalier</h1>");

        html.append("<div class='intro'>");
        html.append("<p>Bonjour,</p>");
        html.append("<p>");
        html.append("Veuillez trouver ci-dessous le compte rendu des activités du <strong>");
        html.append(datejour);
        html.append("</strong>.");
        html.append("</p>");
        html.append("<p>");
        html.append("Ce rapport présente une synthèse des opérations d'achat, de vente et de fabrication enregistrées pour la journée.");
        html.append("</p>");
        html.append("</div>");

        html.append("<div class='periode'>");
        html.append("<strong>Remarque :</strong><br/>");
        html.append("Ce compte rendu est généré automatiquement par <strong>Async</strong>.<br/>");
        html.append("La véracité des informations présentées dépend des données enregistrées par les utilisateurs dans le système.");
        html.append("</div>");

        return html.toString();
    }

    public void sendPageEmail(Date datejour) throws Exception {

        String objet = "SOCOBIS - Compte rendu journalier - " + datejour;

        StringBuilder message = new StringBuilder();

        message.append(getTexte(datejour));
        message.append("<div class='section'>");
        message.append("<h2>Maintenance</h2>");
        message.append("<p>Rapprochement de réalisation d'une semaine.</p>");
        message.append(contentMaintenance(datejour));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Achats</h2>");
        message.append("<p>Récapitulatif des opérations d'approvisionnement enregistrées au cours de la journée.</p>");
        message.append(contentAchat(datejour));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Ventes</h2>");
        message.append("<p>Situation des ventes réalisées durant la journée.</p>");
        message.append(contentVente(datejour));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Fabrication</h2>");
        message.append("<p>État des opérations de production et des mouvements associés.</p>");
        message.append(contentFabrication(datejour));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Stock</h2>");
        message.append("<p>Situation du stock à la fin de la journée.</p>");
        message.append(contentStock(datejour));
        message.append("</div>");
        
        message.append("<div class='section'>");
        message.append("<h2>caisse</h2>");
        message.append("<p>Situation du caisse à la fin de la journée.</p>");
        message.append(contentEtatCaisse());
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Paie</h2>");
        message.append("<p>Situation des avances et des paies à la fin de la journée.</p>");
        message.append(contentPaie(datejour));
        message.append("</div>");

        message.append("<div class='section'>");
        message.append("<h2>Prevision</h2>");
        message.append("<p>Résultat prévisionnel des 3 mois prochains </p>");
        message.append(contentPrevision(datejour));
        message.append("</div>");

        message.append("<div class='footer'>");
        message.append("Ce message a été généré automatiquement par le système d'information.");
        message.append("</div>");

        message.append("</div>");
        message.append("</body>");
        message.append("</html>");

        sendMail(objet, message.toString(), getTo(), getCc());
    }

    public String[] getCc() {
        return cc;
    }

    public CRJournalier setCc(String[] cc) {
        this.cc = cc;
        return this;
    }

    public  String getTo() {
        return  this.to;
    }

    public  void setTo(String to) {
        this.to = to;
    }

}

