<%-- 
    Document   : vente-details
    Created on : 22 mars 2024, 17:05:45
    Author     : Angela
--%>


<%@page import="vente.VenteDetailsLib"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>


<%
    try{
        VenteDetailsLib t = new VenteDetailsLib();
        t.setNomTable("VENTE_DETAILS_POIDS_ING");
        String listeCrt[] = {};
        String listeInt[] = {};
        String libEntete[] = {"id","idprevision", "idproduitlib","designation","unite2","qte", "poids","pu","remise","puRemiseLib","ristourne","tva","montantht","montantttc","reste"};
        PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        pr.setNpp(500);
        if(request.getParameter("id") != null){
            pr.setAWhere(" and idremise='"+request.getParameter("id")+"' order by idproduitlib desc");
        }
        String[] colSomme = null;

        pr.creerObjetPage(libEntete, colSomme);
        int nombreLigne = pr.getTableau().getData().length;
%>

<div class="box-body">
    <%
        String libEnteteAffiche[] =  {"id","Pr&eacute;vision","Produit","designation","Unit&eacute;","Quantit&eacute;", "Poids T.","PU H.T","Remise (En %)","PU Remis&eacute;","Ristourne (En %)","TVA(en %)","Net H.T","Montant TTC","Reste &agrave; Livrer"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);

        String lienTableau[] = {pr.getLien() + "?but=prevision/prevision-fiche.jsp",pr.getLien() };
        String colonneLien[] = {"idPrevision"};
        String attLien[] = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);
        pr.getTableau().setAttLien(attLien);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
    %>
    <%  }if(pr.getTableau().getHtml() == null)
    {
    %><center><h4>Aucune donne trouvee</h4></center><%
    }


%>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }%>

