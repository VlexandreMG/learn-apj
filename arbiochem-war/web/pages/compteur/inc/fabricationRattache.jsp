<%-- 
    Document   : ecriture-detail
    Created on : 30 juil. 2024, 15:15:57
    Author     : bruel
--%>

<%@page import="mg.cnaps.compta.*"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="maintenance.configuration.CompteurMaintenance" %>
<%@ page import="maintenance.ressources.OfRattache" %>

<%
    try{
    OfRattache t = new OfRattache();
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] =  {"id", "idOfFille","idProduitLib", "ligne","daty","qte", "qtePetri","pourcentage"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    CompteurMaintenance compteurMaintenance = new CompteurMaintenance();
    double consommationPetri = 0.0;
    if(request.getParameter("id") != null){
        pr.setAWhere(" and idreleve ='"+request.getParameter("id")+"'");
         compteurMaintenance.setId(request.getParameter("id"));
         consommationPetri = compteurMaintenance.calculConsommationParPetri(null);
    }
    String[] colSomme = {"qtePetri"};
    //pr.setNpp(10);
    pr.creerObjetPage(libEntete, colSomme);
    int nombreLigne = pr.getTableau().getData().length;
    String libEnteteAffiche[] =  {"ID", "ID Fabrication", "Libell&eacute; Produit", "Ligne", "Date", "Quantit&eacute;", "Quantit&eacute; Petri","Pourcentage"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);

    int etat = Integer.parseInt(request.getParameter("etat"));
    if(etat < 11) {
        pr.getTableau().setNameBoutton2("Supprimer");
        pr.getTableau().setNameActe2("deleteMultiple");
    }
    pr.getTableau().setValdierMultiple(false);

        String lienTableau[] = {pr.getLien() + "?but=fabrication/ordre-fabrication-details-fiche.jsp"};
        String colonneLien[] = {"idOfFille"};
        String valLien[] = {"idOfFille"};
        String varColonneLien[] = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setValeurLien(valLien);
        pr.getTableau().setAttLien(varColonneLien);
        pr.getTableau().setColonneLien(colonneLien);
%>
<div class="box-body">
    <% if(pr.getTableau().getHtmlWithCheckbox() != null){ %>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=apresMultiple.jsp" method="post">
            <input name="classe" type="hidden" id="classe" value="maintenance.configuration.ReleveFab">
            <input name="nomtable" type="hidden" id="nomtable" value="RELEVEFAB">
            <input name="acte" type="hidden" id="acte" value="deleteMultiple">
            <input type="hidden" name="bute" value="<%= pr.getLien() + "?but=compteur/releve-fiche.jsp&id="+request.getParameter("idmere")+"&tab=inc/fabricationRattache" %>">
        <%

            out.println(pr.getTableauRecap().getHtml());%>
        <br>    
            <%
                out.println(pr.getTableau().getHtmlWithCheckbox());
            %>
        </form>
         <br>  
        <div class="w-100" style="display: flex; flex-direction: row-reverse;">
            <table style="width: 30%"class="table">
                <tr>
                    <td><b>Consommation par petri :</b></td>
                    <td><b><%=consommationPetri %></b></td>
                </tr>
            
            </table>
        </div>
        <%
             out.println(pr.getBasPage());
        %>
         
    </section>
    <% }if(pr.getTableau().getHtmlWithCheckbox() == null)
             {
             %><center><h4>Aucune donn&eacute;e disponible</h4></center><%
             } %>

</div>
<script>
    function changerDesignation() {
        document.paye.submit();
    }
</script>
<% } catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
history.back();
</script>
<% }%>