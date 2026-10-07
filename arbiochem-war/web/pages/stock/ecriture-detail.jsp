<%@page import="mg.cnaps.compta.*"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>


<%
    try{
    ComptaSousEcritureLib t = new ComptaSousEcritureLib();
    t.setNomTable("COMPTA_SOUSECRITURE_sanspg");
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] =  {"id","idMere", "numero","journal", "compte","compte_aux","daty","remarque", "debit", "credit"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("id") != null){
        pr.setAWhere(" and idobjet='"+request.getParameter("id")+"'");
    }
    String[] colSomme = null;
    pr.setNpp(10);
    pr.creerObjetPage(libEntete, colSomme);
    int nombreLigne = pr.getTableau().getData().length;
        String[] lienTableau = {pr.getLien() + "?but=compta/ecriture/ecriture-fiche.jsp"};
        String[] colonneLien = {"idMere"};
        String[] attLien = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);
        pr.getTableau().setAttLien(attLien);
%>

<div class="box-body">
    <%
         String libEnteteAffiche[] =  {"ID","Ecriture M&egrave;re", "Libell&eacute; de compte","Journal",  "Compte","Compte Auxiliaire","Date","Remarque",  "D&eacute;bit", "Cr&eacute;dit"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        ComptaSousEcritureLib[] liste=(ComptaSousEcritureLib[]) pr.getTableau().getData();
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
         }else
         {
               %><center><h4>Aucune donne trouvee</h4></center><%
         }

        
    %>
</div>
<%
} catch (Exception e) {
    e.printStackTrace();
}%>