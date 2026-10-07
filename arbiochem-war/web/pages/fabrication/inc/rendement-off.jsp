
<%@page import="fabrication.*"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="produits.Ingredients" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="stock.MvtStockFilleLib" %>


<%
    try{
        MvtStockFilleLib t = new MvtStockFilleLib();
        t.setNomTable("V_MVTSTOCKFILLERENDEMENT");
        String listeCrt[] = {};
        String listeInt[] = {};
        String libEntete[] = {"idProduit","libelleexacte","pu", "entree","montant"};
        PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        if(request.getParameter("id") != null){
            pr.setApres("fabrication/ordre-fabrication-details-fiche.jsp&id="+request.getParameter("id"));
            pr.setAWhere(" and IDOFFILLE='"+request.getParameter("id")+"'");
        }

        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);

        MvtStockFilleLib[] listeFille=(MvtStockFilleLib[]) pr.getTableau().getData();
        System.out.println("listeFille.length : "+listeFille.length);

        pr.getTableau().transformerDataString();
        String lienTableau[] = {pr.getLien() + "?but=produits/as-ingredients-fiche.jsp"};
        String colonneLien[] = {"idProduit"};
        String attributLien[] = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);
        pr.getTableau().setAttLien(attributLien);

%>

<div class="box-body">
    <%
        String libEnteteAffiche[] =   {"Id produit","Nom produit","Prix Unitaire", "Quantit&eacute;","Montant"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPageOnglet());
    %>
    <%  }if(pr.getTableau().getHtml() == null)
    {
    %><center><h4>Aucune donne trouvee</h4></center><%
    }


%>
    <div class="w-100" style="display: flex; flex-direction: row-reverse;">
        <table style="width: 20%"class="table">
            <tr>
                <td><b>TOTAL MONTANT:</b></td>
                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"montant")) %> Ar</b></td>
            </tr>
        </table>
    </div>
</div>
<%=pr.getModalHtml("modalContent")%>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }%>

