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
        //pr.setNpp(500);
        if(request.getParameter("id") != null){
            pr.setAWhere(" and idVente='"+request.getParameter("id")+"' order by idproduitlib desc");
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
    <div class="w-100" style="display: flex; flex-direction: row-reverse;">
        <table style="width: 20%"class="table">
            <tr>
                <td><b>Total H.T:</b></td>
                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montant")) %> <%= ((VenteDetailsLib)pr.getListe()[0]).getIdDevise() %></b></td>
            </tr>
            <tr>
                <td><b>Remise globale:</b></td>
                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montantremise")) %> <%= ((VenteDetailsLib)pr.getListe()[0]).getIdDevise() %></b></td>
            </tr>
            <tr>
                <td><b>Participation transport:</b></td>
                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"frais")) %> <%= ((VenteDetailsLib)pr.getListe()[0]).getIdDevise() %></b></td>
            </tr>
            <tr>
                <td><b>Total Net H.T:</b></td>
                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montantht")) %> <%= ((VenteDetailsLib)pr.getListe()[0]).getIdDevise() %></b></td>
            </tr>
            <tr>
                <td><b>Total Taxe:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montanttva")) %> <%= ((VenteDetailsLib)pr.getListe()[0]).getIdDevise() %></b></td>
            </tr>
            <tr>
                <td><b>Net &agrave; payer:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(pr.getListe(),"montantttc")) %> <%= ((VenteDetailsLib)pr.getListe()[0]).getIdDevise() %></b></td>
            </tr>
        </table>
    </div>
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

