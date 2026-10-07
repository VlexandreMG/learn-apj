
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8;" %>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="vente.BonDeCommandeFille" %>
<%
    try{
    BonDeCommandeFille t = new BonDeCommandeFille();
    t.setNomTable("BONDECOMMANDE_CLIENT_FILLE_CPL");
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] = {"produit","libelleProduit","quantite","qteOf","qteFab","qteLivre","resteOf","resteFab","resteLivre"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("id") != null){
        pr.setAWhere(" and idbc='"+request.getParameter("id")+"'");
    } else if (request.getParameter("produit")!=null) {
        pr.setAWhere(" and produit='"+request.getParameter("produit")+"' and QTEOFRESTANTE > 0");
    }
        String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);
%>

<div class="box-body">
    <%
        String lienTableau[] = {pr.getLien() + "?but=produits/as-ingredients-fiche.jsp"};
        String colonneLien[] = {"produit"};
        String[] attributLien = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);
        pr.getTableau().setAttLien(attributLien);
        String libEnteteAffiche[] =   {"ID","Produit","Quantit&eacute;","Quantit&eacute; OF","Quantit&eacute; Fabriqu&eacute;e","Quantit&eacute; Livr&eacute;e","Reste OF", "Reste à Fabriquer", "Reste à Livrer"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
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