<%@page import="affichage.PageRecherche"%>
<%@page import="faturefournisseur.As_BonDeCommande"%>
<%@ page import="faturefournisseur.As_BonDeCommandeCpl" %>

<% try {

    // 3. Champs de recherche, champ en intervalle (date), colonnes du tableau
    String listeCrt[] = {"id","daty","designation","fournisseurlib","modepaiementlib","reference","refproforma"};
    String listeInt[] = {"daty"};
    String libEntete[] = {"id","daty","reference","designation","modepaiementlib","fournisseurlib", "idServiceLib","refproforma","etatlib","traite"};

    As_BonDeCommandeCpl bdc_Cpl = new As_BonDeCommandeCpl(); 
    bdc_Cpl.setNomTable("As_BonDeCommande_MERETRAITE");

                                    // (modèle, request, critères, intervalles, nbRange, colonnes affichées, nbAff).
    PageRecherche pg = new PageRecherche(bdc_Cpl, request ,listeCrt, listeInt,3,libEntete,libEntete.length);

    pg.setTitre("Liste des bons de commande fournisseur");
    pg.setUtilisateur((user.UserEJB)session.getValue("u"));
    pg.setLien((String)session.getValue("lien"));
    pg.setApres("bondecommande/bondecommande-arbiochem-liste.jsp");

     // 7. Libellés des champs du formulaire
    pg.getFormu().getChamp("reference").setLibelle("R&eacute;f&eacute;rence"); 
    pg.getFormu().getChamp("designation").setLibelle("D&eacute;signation"); 
    pg.getFormu().getChamp("fournisseurlib").setLibelle("Fournisseur");
    pg.getFormu().getChamp("refpgoforma").setLibelle("R&eacute;f&eacute;rence proforma");

    // 8. "Mode de paiement" devient une liste déroulante (table MODEPAIEMENT)
    TypeObjet modePaiement= new TypeObjet();
    modePaiement.setNomTable("MODEPAIEMENT");
    Liste[] liste = new Liste[1];
    liste[0] = new Liste("modepaiementlib", modePaiement, "val", "val");
    pg.getFormu().changerEnChamp(liste);
    pg.getFormu().getChamp("modepaiementlib").setLibelle("Mode de paiement");
    pg.getFormu().getChamp("reference").setLibelle("R&eacute;f&eacute;rence");

    // 9. Intervalle de dates : daty1 (min) et daty2 (max), pgé-remplis avec la date du jour
    pg.getFormu().getChamp("daty1").setLibelle("Date min");
    pg.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pg.getFormu().getChamp("daty2").setLibelle("Date max");
    pg.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());

    //Fixe la pagination 
    pg.setNpp(50);

    // Je ne sais pas comment pgendre les entêtes
    pg.creerObjetPage(libEntete, null);

    out.println(pg.getFormu().getHtmlEnsemble());

    out.println(pg.getTableau().getHtml());
    // Pour la pagination 
    out.println(pg.getBasPage());   
    } catch(Exception e) {
        e.printStackTrace();
    }
%>
