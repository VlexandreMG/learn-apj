<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageRecherche"%>
<%@ page import="faturefournisseur.FactureFournisseurCpl" %>

<% try{
    FactureFournisseurCpl o = new FactureFournisseurCpl();
    o.setNomTable("FACTUREFOURNISSEURCPL_TOUS");
    String[] listeCrt = {};
    String[] listeInt = {};
    String[] libEntete = {"id", "idBc","designation","idFournisseurLib","idDevise","daty","montantttc","montantpaye", "montantreste", "dateEcheancePaiement", "idServiceLib","etatlib"};
    PageRecherche pr = new PageRecherche(o, request, listeCrt, listeInt, 4, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getAttribute("u"));
    pr.setLien((String) session.getAttribute("lien"));

    String idFactPrincipale = request.getParameter("idFactPrincipale");
    if (idFactPrincipale != null && !idFactPrincipale.isEmpty()) {
        pr.setAWhere(" AND id = '"+idFactPrincipale+"'");
    }

    pr.creerObjetPage(libEntete, null);
    String[] libEnteteAffiche = {"ID", "ID Bon de commande","D&eacute;signation","Fournisseur","devise","Date","Montant TTC","Montant pay&eacute;","Montant Restant", "date d'&Eacute;ch&eacute;ance de Paiement", "D&eacute;partement","&Eacute;tat"};
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

