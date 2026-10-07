
<%@page import="faturefournisseur.FactureFournisseurDetailsCpl"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8;" %>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>

<%
    try{
    FactureFournisseurDetailsCpl t = new FactureFournisseurDetailsCpl();

    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] = {"id","idPrevision","libelleexacte","qte","pu","tva","montantht", "montantttc", "puavecfraisaccessoire", "montantavecfraisaccessoire"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("id") != null){
        pr.setAWhere(" and idFactureFournisseur='"+request.getParameter("id")+"'");
    }
    String[] colSomme = null;
   
    String idDevise = (String) request.getAttribute("idDevise");
    if(idDevise==null) idDevise="Ar";
    pr.creerObjetPage(libEntete, colSomme);
    pr.getTableau().setLienFille("stock/historiqueprix-liste.jsp&idFFD=");
%>

<div class="box-body">
    <%
        String libEnteteAffiche[] =  {"R&eacute;f&eacute;rence","Pr&eacute;vision","Produit","Quantit&eacute;","Prix unitaire","TVA","montant HT", "montant TTC", "PU avec Frais accessoire (AR)", "Montant TTC avec frais accessoire (AR)"};

        String lienTableau[] = {pr.getLien() + "?but=prevision/prevision-fiche.jsp"};
        String colonneLien[] = {"idPrevision"};
        String attLien[] = {"id"};
        pr.getTableau().setLien(lienTableau);

        pr.getTableau().setColonneLien(colonneLien);
        pr.getTableau().setAttLien(attLien);

        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
    %>
    <div class="w-100" style="display: flex; flex-direction: row-reverse;">
        <table style="width: 20%"class="table">
            <tr>
                <td><b>Montant HT:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montantHT")) %> <%= idDevise %></b></td>
            </tr>
            <tr>
                <td><b>Montant TVA:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montantTva")) %> <%= idDevise %></b></td>
            </tr>
            <tr>
                <td><b>Montant TTC:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montantttc")) %> <%= idDevise %></b></td>
            </tr>
        </table>
    </div>
    <% }if(pr.getTableau().getHtml() == null){
               %><div style="text-align: center;"><h4>Aucune donnée trouvée</h4></div><%
         }
    %>
</div>
<%
} catch (Exception e) {
    e.printStackTrace();
}%>


