<%-- 
    Created on : 26 mars 2024, 09:49:32
    Author     : Angela
--%>

<%@page import="caisse.MvtCaisse"%>
<%@page import="encaissement.Encaissement"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="caisse.MvtCaisseCpl" %>


<%
    try {
        MvtCaisseCpl t = new MvtCaisseCpl();
        t.setNomTable("MOUVEMENTCAISSECPL_V_Dispatch");
        String listeCrt[] = {};
        String listeInt[] = {};
        String libEntete[] = {"id","daty","designation","montantimpute","idCaisseLib"};
        PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        if (request.getParameter("id") != null) {
            pr.setAWhere(" and idorigine like '%" + request.getParameter("id") + "%'");
        }
        String[] colSomme = null;
        pr.setNpp(10);
        pr.creerObjetPage(libEntete, colSomme);
        String lienTableau[] = {pr.getLien() + "?but=caisse/mvt/mvtCaisse-fiche.jsp"};
        String colonneLien[] = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);

%>

<div class="box-body">
    <%  String libEnteteAffiche[] = {"id","date","d&eacute;signation","Montant","Caisse"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        pr.getTableau().getData();
        if (pr.getTableau().getHtml() != null) {
            out.println(pr.getTableau().getHtml());
        } else {
    %><center><h4>Aucune donne trouvee</h4></center><%
        }


        %>
</div>
<%    } catch (Exception e) {
        e.printStackTrace();
    }%>

