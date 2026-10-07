<%@page import="faturefournisseur.As_BonDeCommande_Fille_CPL"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8;" %>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="filemanager.Util" %>

<% try {
    As_BonDeCommande_Fille_CPL t = new As_BonDeCommande_Fille_CPL();
    t.setNomTable("AS_BC_RESTE_A_FACTURER");
    String[] listeCrt = {"daty","idbc"};
    String[] listeInt = {"daty"};
    String[] libEntete =  {"id","idbc","daty","produitlib","fournisseurlib","unitelib","quantite","resteFacture"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getAttribute("u"));
    pr.setLien((String) session.getAttribute("lien"));
    pr.setApres("bondecommande/facturer-commande-multiple.jsp");
    pr.setTitre("Facturation BC Fille Multiple");
    pr.getFormu().getChamp("daty1").setLibelle("Date min");
    pr.getFormu().getChamp("daty2").setLibelle("Date max");
    pr.getFormu().getChamp("daty1").setDefaut(Utilitaire.dateDuJour());
    pr.getFormu().getChamp("daty2").setDefaut(Utilitaire.dateDuJour());
    pr.getFormu().getChamp("idbc").setLibelle("ID Bon de commande");

    pr.creerObjetPage(libEntete, null);
    String[] libEnteteAffiche =   {"id","ID Bon de commande","Date","produit","Fournisseur","unit&eacute;","Quantit&eacute;","Reste &agrave; facturer"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
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
            out.println(pr.getTableauRecap().getHtml());
        %>
        <br>
        <form action="<%= pr.getLien() + "?but=facturefournisseur/facturefournisseur-saisie.jsp"%>" method="post" >
            <input type="hidden" name="acte">
            <input type="hidden" name="idbc" value="vide">
            <% if(pr.getTableau().getHtmlWithCheckbox() != null){
                out.println(pr.getTableau().getHtmlWithCheckbox());
            }else { %>
            <div style="text-align: center;"><h4>Aucune donn&eacute;e trouv&eacute;e</h4></div>
            <% } %>
        </form>
        <%
            out.println(pr.getBasPage());
        %>
    </section>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

