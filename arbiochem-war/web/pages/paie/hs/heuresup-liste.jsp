
<%@page import="fabrication.*"%>
<%@page import="utilitaire.Utilitaire"%>
<%@page import="affichage.*"%>

<% try{
    HeureSupFabricationCPL bc = new HeureSupFabricationCPL();
    bc.setNomTable("heureSupFabrication_cpl");
    String lien = (String) session.getValue("lien");
    String listeCrt[] = {"matricule","idPersonneLib", "dateFabrication"};
    String listeInt[] = {"dateFabrication"};
    String libEntete[] = {"dateImport","id","idPersonne","matricule","idPersonneLib","dateFabrication","heurenormale","HS","MN","JF","HD","IF","etatlib"};
    String libEnteteAffiche[] =  {"Date d'import","ID","ID Personnel","Matricule","Personnel","Date","HN","Heure Suppl&eacute;mentaire","Majoration Nuit","Jour Feri&eacute;","Heure Dimanche","Indemnit&eacute; de fonction","&Eacute;tat"};
    PageRecherche pr = new PageRecherche(bc, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.getFormu().getChamp("idPersonneLib").setLibelle("Personnel");
    pr.getFormu().getChamp("dateFabrication1").setLibelle("Date min");
    pr.getFormu().getChamp("dateFabrication2").setLibelle("Date max");

    pr.setApres("fabrication/fabrication-fiche.jsp&id="+request.getParameter("id")+"&tab=inc/fabrication-hs");
    String[] colSomme = {"heurenormale","HS","MN","JF","HD","IF"};

    pr.setNpp(100);
    pr.creerObjetPage(libEntete, colSomme);

    String lienTableau[] = {pr.getLien() + "?but=personnel/personnel-fiche.jsp",pr.getLien() + "?but=fabrication/heureSup-fiche.jsp"};
    String colonneLien[] = {"idPersonne","id"};
    String[] attributLien = {"id","id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    pr.getTableau().setAttLien(attributLien);
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);

    String libRecap[] = {"","Nombre","Somme des HN", "Somme des HS", "Somme des MN", "Somme des JF", "Somme des HD", "Somme des IF"};
    pr.getTableauRecap().setLibeEntete(libRecap);
    pr.setApres("paie/hs/heuresup-liste.jsp");

%>

<% if(pr.getTableau().getHtml()!=null){ %>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name="hs" >
        <%
            out.println(pr.getFormu().getHtmlEnsemble());
        %>
    </form>
    <%
        out.println(pr.getTableauRecap().getHtml());
    %>
    <br>
    <section class="content">
<%--        <a class="btn btn-success pull-left"  href="<%= lien + "?but=fabrication/heureSup-modif-multiple.jsp&id=" + request.getParameter("id")%>" style="margin-right: 10px">Modifier</a>--%>
        <form action="<%= pr.getLien() + "?but=apresMultiple.jsp"%>" method="post" >
           <%
                out.println(pr.getTableau().getHtml());
        %>
        </form>
    </section>
</div>
<% }else{ %>
<h4>Aucune donn&eacute&eacute;e trouv&eacute&eacute;e</h4>
<% } %>
<%
    }catch(Exception e){

        e.printStackTrace();
    }
%>




