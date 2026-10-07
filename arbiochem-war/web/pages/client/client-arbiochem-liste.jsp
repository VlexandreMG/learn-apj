<%-- 
    Document   : client-liste
    Created on : 22 mars 2024, 14:50:31
    Author     : SAFIDY
--%>

<%@page import="client.Client"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="utilitaire.Utilitaire" %>

<% try{ 
    Client t = new Client();
    t.setNomTable("CLIENT_actif");
    String listeCrt[] = {"id","codeclient","nom","telephone","mail","adresse","remarque"};
    String listeInt[] = {};
    String libEntete[] = {"id","codeclient","nom","telephone","mail","adresse","remarque"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setTitre("Liste des Clients");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("client/client-arbiochem-liste.jsp");
    pr.getFormu().getChamp("id").setLibelle("Id");
    pr.getFormu().getChamp("nom").setLibelle("Nom");
    pr.getFormu().getChamp("telephone").setLibelle("T&eacute;l&eacute;phone");
    pr.getFormu().getChamp("mail").setLibelle("Adresse e-mail");
    pr.getFormu().getChamp("adresse").setLibelle("Adresse");
    pr.getFormu().getChamp("remarque").setLibelle("Remarque");
    pr.getFormu().getChamp("codeclient").setLibelle("Code Client");

    String[] colSomme = null;
    pr.setNpp(25);
    pr.creerObjetPage(libEntete, colSomme);
    //Definition des lienTableau et des colonnes de lien
    String lienTableau[] = {pr.getLien() + "?but=client/client-arbiochem-fiche.jsp"};
    String colonneLien[] = {"id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    String libEnteteAffiche[] = {"ID","Code Client", "Nom", "T&eacute;l&eacute;phone","Adresse e-mail","Adresse","Remarque"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    pr.getFormu().setAnotherButton("" +
    "<a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=client/client-arbiochem-saisie.jsp&currentMenu=MNDN0000508001\">\n" +
    "                    <i class=\"material-symbols-rounded\">add</i>Saisie d'un client</a>"
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




