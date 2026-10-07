<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageRecherche"%>
<%@ page import="stock.EtatStock" %>

<% try{ 
    EtatStock o = new EtatStock();
    o.setNomTable("V_ETATSTOCK_INGINFSEUIL");
    String[] listeCrt = {};
    String[] listeInt = {};
    String[] libEntete = {"id","idProduitLib","idTypeProduitLib","daty","quantite","entree","sortie","reste","montantReste","pu","typeStock","seuil","seuilMin","seuilMax"};
    PageRecherche pr = new PageRecherche(o, request, listeCrt, listeInt, 4, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));

    if (request.getParameter("id") != null){
        pr.setAWhere(" and idMagasin='" + request.getParameter("id") + "'");
    }

    String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);

    String[] libEnteteAffiche = {"Id","Produit","Type de produit","Date","Quantit&eacute;","Entr&eacute;e","Sortie","Reste","Montant restant","Prix unitaire","Type de stock","Seuil","Seuil minimum","Seuil maximum"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
%>

<div class="box-body">
    <%  if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
        } else{ %>
            <center><h4>Aucune donn&eacute;e trouv&eacute;e</h4></center>
    <%  } %>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

