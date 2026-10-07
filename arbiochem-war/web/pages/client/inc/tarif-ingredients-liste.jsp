<%--
  Created by IntelliJ IDEA.
  User: Tsinjoniaina
  Date: 19/09/2025
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="produits.TarifIngredientsLib" %>
<%@ page import="affichage.PageRecherche" %>
<%@ page import="client.Client" %>
<%
    try{
        TarifIngredientsLib t = new TarifIngredientsLib();
        String listeCrt[] = {};
        String listeInt[] = {};
        String libEntete[] = {"id", "idTypeClientLib", "idIngredientLib", "daty","prixUnitaire"};
        PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        if(request.getParameter("id") != null){
            Client client = (Client) new Client().getById(request.getParameter("id"), "CLIENT", null);
            pr.setAWhere(" and idTypeClient='"+client.getIdTypeClient()+"'");
        }
        String[] colSomme = null;

        pr.creerObjetPage(libEntete, colSomme);

        String lienTableau[] = {pr.getLien() + "?but=produits/tarifIngredients/tarif-ingredients-fiche.jsp"};
        String colonneLien[] = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);
%>

<div class="box-body">
    <%

        String libEnteteAffiche[] = {"id", "Type de client", "Ingr&eacute;dient","Date", "Prix Unitaire"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
        }else
        {
    %><center><h4>Aucune donne trouvé</h4></center><%
    }


%>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }%>
