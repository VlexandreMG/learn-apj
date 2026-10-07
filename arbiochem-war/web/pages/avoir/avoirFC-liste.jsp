<%-- 
    Document   : avoirFC-liste
    Created on : 2 ao�t 2024, 15:48:25
    Author     : randr
--%>


<%@page import="avoir.AvoirFCLib"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="static java.time.DayOfWeek.MONDAY" %>
<%@ page import="static java.time.temporal.TemporalAdjusters.previousOrSame" %>
<%@ page import="static java.time.DayOfWeek.SUNDAY" %>
<%@ page import="static java.time.temporal.TemporalAdjusters.nextOrSame" %>
<%@ page import="java.sql.Date" %>
<%@ page import="affichage.Liste" %>
<%@ page import="bean.TypeObjet" %>

<% try{
    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(previousOrSame(MONDAY));
    LocalDate sunday = today.with(nextOrSame(SUNDAY));

    AvoirFCLib t = new AvoirFCLib();
    t.setNomTable("avoirfclib_cpl");
    String listeCrt[] = {"id", "clientlib", "idMagasinLib", "typeavoir", "idMotifLib" , "idCategorieLib", "daty","montantHT", "montantTVA", "montantTTC","numfact"};
    String listeInt[] = {"daty","montantHT", "montantTVA", "montantTTC"};
    String libEntete[] = {"id", "clientlib", "daty", "idMagasinLib", "numfact", "typeavoir", "idMotifLib" , "idCategorieLib", "montantHTAr", "montantTVAAr", "montantTTCAr"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setTitre("Liste des factures d'avoir");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("avoir/avoirFC-liste.jsp");
    pr.getFormu().getChamp("id").setLibelle("Id");

    pr.getFormu().getChamp("montantHT1").setLibelle("Montant HT Min");
    pr.getFormu().getChamp("montantHT2").setLibelle("Montant HT Max");
    pr.getFormu().getChamp("montantTVA1").setLibelle("Montant TVA Min");
    pr.getFormu().getChamp("montantTVA2").setLibelle("Montant TVA Max");
    pr.getFormu().getChamp("montantTTC1").setLibelle("Montant TTC Min");
    pr.getFormu().getChamp("montantTTC2").setLibelle("Montant TTC Max");

    pr.getFormu().getChamp("daty1").setLibelle("Date Min");
    pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.datetostring(Date.valueOf(monday)));
    pr.getFormu().getChamp("daty2").setLibelle("Date Max");
    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.datetostring(Date.valueOf(sunday)));
    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());
    Liste[] liste = new Liste[4];
    liste[0] = new Liste("idMagasinLib",new magasin.Magasin(),"val","val");
    TypeObjet motif = new TypeObjet();
    motif.setNomTable("motifavoirfc");
    liste[1] = new Liste("idMotifLib",motif,"val","val");
    TypeObjet cat = new TypeObjet();
    cat.setNomTable("categorieavoirfc");
    liste[2] = new Liste("idCategorieLib",cat,"val","val");
    TypeObjet typeav = new TypeObjet();
    typeav.setNomTable("typeavoir");
    liste[3] = new Liste("typeavoir",typeav,"val","val");
    pr.getFormu().changerEnChamp(liste);
    pr.getFormu().getChamp("idMagasinLib").setLibelle("Magasin");
    //pr.getFormu().getChamp("idVenteLib").setLibelle("Vente");
    pr.getFormu().getChamp("idCategorieLib").setLibelle("Cat&eacute;gorie");
    pr.getFormu().getChamp("idMotifLib").setLibelle("Motif");
    pr.getFormu().getChamp("typeavoir").setLibelle("Type");
    pr.getFormu().getChamp("clientlib").setLibelle("Client");

    String[] colSomme = { "montantHTAr", "montantTVAAr", "montantTTCAr" };
    String[] labelRecap = { "","Nombres","Somme des montants HT en Ar", "Somme des montants TVA en Ar", "Somme des montants TTC en Ar" };
    pr.creerObjetPage(libEntete, colSomme);
    pr.getTableauRecap().setLibeEntete(labelRecap);
    //Definition des lienTableau et des colonnes de lien
    String lienTableau[] = {pr.getLien() + "?but=avoir/avoirFC-fiche.jsp"};
    String colonneLien[] = {"id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    String libEnteteAffiche[] = {"ID", "Client", "Date", "Magasin", "Num&eacute;ro facture", "Type", "Motif" , "Cat&eacute;gorie", "montant HT Ar", "montant TVA Ar", "montant TTC Ar"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    pr.getFormu().setAnotherButton(
            "                <a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=avoir/avoirFC-saisie.jsp&currentMenu=MNDN0001109002\">\n" +
                    "                    <i class=\"material-symbols-rounded\">add</i> Saisir un avoir client" +
                    "                </a>"
    );
%>

<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
        </form>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        <br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    }catch(Exception e){

        e.printStackTrace();
    }
%>



