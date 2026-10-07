<%@page import="inventaire.InventaireFilleLib"%>
<%@page import="stock.MvtStockFilleLib"%>
<%@page import="stock.MvtStockFille"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8;" %>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>

<%
    try{
    InventaireFilleLib t = new InventaireFilleLib();
    t.setNomTable("v_inventaireFilleCpl");
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] = {"idInventaire","id","dateInv","idProduit","idproduitlib","quantiteTheorique","quantite","ecart","montantTheorique","montantReelle","ecartMontant"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("id") != null){
        pr.setAWhere(" and mvtsrc='"+request.getParameter("id")+"'");
    }
    String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);
        InventaireFilleLib[] liste=(InventaireFilleLib[])pr.getListe();
%>

<div class="box-body">
    <%
         String libEnteteAffiche[] = {"Id","ID Inventaire Fille","Date inventaire","ID Produit","Produit","Quantit&eacute; th&eacute;orique","quantit&eacute; Inventaire"," &Eacute;cart quantit&eacute;","valeur th&eacute;orique","valeur inventaire","&Eacute;cart valeur"};
         pr.getTableau().setLibelleAffiche(libEnteteAffiche);
         String lienTableau[] = {pr.getLien() + "?but=inventaire/inventaire-fiche.jsp" , pr.getLien() + "?but=produits/as-ingredients-fiche.jsp"};
            String colonneLien[] = {"idInventaire", "idProduit"};
            String varColonneLien[] = {"id", "id"};
            String valeurLien[] = {"idInventaire", "idProduit"};
            pr.getTableau().setLien(lienTableau);
            pr.getTableau().setAttLien(varColonneLien);
            pr.getTableau().setColonneLien(colonneLien);
            pr.getTableau().setValeurLien(valeurLien);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
         }else
         {
               %><div style="text-align: center;"><h4>Aucune donnée trouvée</h4></div><%
         }
        out.print("<div class=\"col-md-12\" style=\"padding: 16px 8px;text-align: end;align-content: end;\" ><b>Montant Total : "+Utilitaire.formaterAr(AdminGen.calculSommeDouble(liste,"montant"))  +" Ar</b></div>");
        
    %>
</div>
<%
} catch (Exception e) {
    e.printStackTrace();
}%>


