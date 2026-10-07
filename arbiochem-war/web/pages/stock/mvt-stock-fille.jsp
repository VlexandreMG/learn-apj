<%@page import="stock.MvtStockFilleLib"%>
<%@page import="stock.MvtStockFille"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8;" %>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>

<%
    try{
    MvtStockFilleLib t = new MvtStockFilleLib();
    t.setNomTable("mvtstockfillelib");
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] = {"id","idProduit","idProduitlib","idMagasinLib","idMvtStock","entree","sortie","daty","pu", "montant","mvtsrc"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("id") != null){
        pr.setAWhere(" and mvtsrc='"+request.getParameter("id")+"'");
    }
    String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);
        MvtStockFilleLib[] liste=(MvtStockFilleLib[])pr.getListe();
%>

<div class="box-body">
    <%
        String libEnteteAffiche[] = {"ID","ID Produit","D&eacute;signation","Magasin","ID Mouvement de Stock","Entr&eacute;e","Sortie","Date","Prix unitaire", "Montant","Mouvement source"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
         String lienTableau[] = {pr.getLien() + "?but=stock/mvtstock-fiche.jsp", pr.getLien() + "?but=produits/as-ingredients-fiche.jsp",pr.getLien() + "?but=stock/mvtstockfille-fiche.jsp"};
        String colonneLien[] = {"idmvtstock","idProduit", "mvtsrc"};
        String attributLien[] = {"id","id","id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setAttLien(attributLien);
        pr.getTableau().setColonneLien(colonneLien);
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


