<%@page import="inventaire.InventaireFilleLib"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8;" %>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>

<%
    try{
    InventaireFilleLib t = new InventaireFilleLib();
    t.setNomTable("InventaireFilleLib");
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] = {"id","idproduitlib" ,"explication","quantiteTheorique","quantite","pu","ecart"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("id") != null){
        pr.setApres("inventaire/inventaire-fiche.jsp&id="+request.getParameter("id"));
        pr.setAWhere(" and idInventaire='"+request.getParameter("id")+"'");
    }
    pr.creerObjetPage(libEntete, null);
    String[] libEnteteAffiche =  {"Id", "Produit","Remarque","Quantit&eacute; th&eacute;orique","Quantit&eacute;", "Prix Unitaire","&Eacute;cart"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
%>

<div class="box-body">
    <form action="<%= pr.getLien() + "?but=apresMultiple.jsp"%>" method="post" name="validerForm" id="validerForm">
        <input name="acte" type="hidden" id="acte" value="validerMultiple">
        <% if(pr.getTableau().getHtmlWithCheckbox() != null){
            out.println(pr.getTableau().getHtmlWithCheckbox());
        }else { %>
        <div style="text-align: center;"><h4>Aucune donn&eacute;e trouv&eacute;e</h4></div>
        <% } %>
    </form>
    <%
        out.println(pr.getBasPage());
    %>
</div>
<%
} catch (Exception e) {
    e.printStackTrace();
}%>


