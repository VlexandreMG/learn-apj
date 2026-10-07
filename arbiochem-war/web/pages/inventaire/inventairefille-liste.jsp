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
        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);

        String[] lienTableau = { pr.getLien() + "?but=produits/as-ingredients-fiche.jsp" };
        String[] colonneLien = { "idproduitlib" };
        String[] attLien = { "id" };
        String[] valeurLien = { "idProduit" };
%>

<div class="box-body">
    <%
        String libEnteteAffiche[] =  {"R&eacute;f&eacute;rence", "Produit","Explication","Quantit&eacute; th&eacute;orique","Quantit&eacute;", "Prix Unitaire","&Eacute;cart"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);
        pr.getTableau().setAttLien(attLien);
        pr.getTableau().setValeurLien(valeurLien);
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPageOnglet());
        }else
        {
    %><div style="text-align: center;"><h4>Aucune donnée trouvée</h4></div><%
    }


%>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }%>


