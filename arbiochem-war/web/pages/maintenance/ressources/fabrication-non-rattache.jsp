<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageRecherche"%>
<%@ page import="maintenance.ressources.RattachementFabrication" %>

<% try{
    RattachementFabrication o = new RattachementFabrication();
    o.setNomTable("FABNONRATTACHE");
    String[] listeCrt = {};
    String[] listeInt = {};
    String[] libEntete = {"idFabrication","daty","idProduit","idProduitLib","qte","qtePetri","Ligne"};
    PageRecherche pr = new PageRecherche(o, request, listeCrt, listeInt, 4, libEntete, libEntete.length);
    pr.setTitre("Liste des fabrications non rattach&eacute;es");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("maintenance/ressources/fabrication-non-rattache.jsp");
    String[] colSomme = null;
     if(request.getParameter("idligne") != null && request.getParameter("date") != null){
        pr.setAWhere(" and idligne='"+request.getParameter("idligne")+"' and daty<=TO_DATE('"+request.getParameter("date")+"','YYYY-MM-DD')");
    }
    pr.creerObjetPage(libEntete, colSomme);

    String lienTableau[] = {pr.getLien() + "?but=fabrication/fabrication-fiche.jsp",pr.getLien() + "?but=produits/as-ingredients-fiche.jsp"};
    String colonneLien[] = {"idFabrication","idProduit"};
    String valLien[] = {"idFabrication","idProduit"};
    String varColonneLien[] = {"id","id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setValeurLien(valLien);
    pr.getTableau().setAttLien(varColonneLien);
    pr.getTableau().setColonneLien(colonneLien);

    String[] libEnteteAffiche = {"ID Fabrication","Date","ID Produit","Produit","Quantit&eacute;","Quantit&eacute; Petri","Ligne"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);


%>

<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <br>
        <%
            out.println(pr.getTableauRecap().getHtml());
        %>
        <br>
        <form action="<%=pr.getLien()%>?but=maintenance/ressources/apresRattachementFab.jsp" method="post" name="paye" id="paye">
            <input type="hidden" value="<%=request.getParameter("idCompteur")%>" name="idCompteur" id="idCompteur">
        <%
            pr.getTableau().setNameBoutton("Rattacher");
            pr.getTableau().setNameActe("rattacher");
            out.println(pr.getTableau().getHtmlWithCheckbox());
            out.println(pr.getBasPage());
        %>
        </form>
    </section>
</div>
<script>
    function changerDesignation() {
        document.liste.submit();
    }
</script>
<% } catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
history.back();
</script>
<% }%>

