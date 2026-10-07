
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8;" %>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="vente.*" %>
<%
    try{
        CommandeFIlleCpl t = new CommandeFIlleCpl();
        t.setNomTable("COMMANDEFILLE_CPL_LIB");
        String listeCrt[] = {};
        String listeInt[] = {};
        String libEntete[] = {"produit","produitlib","quantite","unitelib"};
        PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        if(request.getParameter("id") != null){
            pr.setAWhere(" and idc='"+request.getParameter("id")+"' order by produitlib ASC");
        }
        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);
        CommandeFIlleCpl[] listeFille=(CommandeFIlleCpl[]) pr.getTableau().getData();
        if(listeFille.length>0) {
            Commande o = new Commande();
            o.setFille(listeFille);
            //o.calculerRevient(null);
        }
        pr.getTableau().transformerDataString();
%>

<div class="box-body">
    <%
        String lienTableau[] = {pr.getLien() + "?but=produits/as-ingredients-fiche.jsp"};
        String colonneLien[] = {"produit"};
        String[] attributLien = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);
        pr.getTableau().setAttLien(attributLien);
        String libEnteteAffiche[] =   {"ID","Produit","Quantit&eacute;","Unit&eacute;"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
    %>
    <div class="w-100" style="display: flex; flex-direction: row-reverse;">
        <table style="width: 20%"class="table">
<%--            <tr>--%>
<%--                <td><b>Total Poids:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"poidstotal")) %> Kg </b></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><b>Total H.T:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"montanttotal")) %> Ar</b></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><b>Remise globale:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"montantdelaremise")) %> Ar</b></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><b>Transport:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"fraistransport")) %> Ar</b></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><b>Ristourne:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"montantristourne")) %> Ar</b></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><b>Total net H.T:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"montantremise")) %> Ar</b></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><b>Total taxe:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"montanttva")) %> Ar</b></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><b>Net &agrave; payer:</b></td>--%>
<%--                <td><b><%=utilitaire.Utilitaire.formaterAr(AdminGen.calculSommeDouble(listeFille,"montantttc")) %> Ar</b></td>--%>
<%--            </tr>--%>
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