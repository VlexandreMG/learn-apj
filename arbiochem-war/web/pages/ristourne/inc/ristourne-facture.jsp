<%@page import="mg.cnaps.compta.*"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="ristourne.*" %>
<%@ page import="avoir.AvoirFCLib" %>

<%
  try{
    AvoirFCLib t = new AvoirFCLib();
    t.setNomTable("");
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] = {"id", "clientlib", "daty", "idMagasinLib", "idorigine", "typeavoir", "idMotifLib" , "idCategorieLib", "montantHTAr", "montantTVAAr", "montantTTCAr"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setAWhere(" AND idristourne='"+request.getParameter("id")+"'");
    pr.setLien((String) session.getValue("lien"));
    String[] colSomme = null;
    pr.setNpp(10);
    pr.creerObjetPage(libEntete, colSomme);

        String[] lienTableau = {pr.getLien() + "?but=avoir/avoirFC-fiche.jsp"};
        String colonneModal[] = {"id"};
        String[] colonneLien = {"id"};
        pr.getTableau().setLien(lienTableau);
//        pr.getTableau().setModalOnClick(true,colonneModal);
        pr.getTableau().setColonneLien(colonneLien);
%>

<div class="box-body">
  <%
    String libEnteteAffiche[] = {"ID", "Client", "Date", "Magasin", "Origine", "Type", "Motif" , "Cat&eacute;gorie", "montant HT Ar", "montant TVA Ar", "montant TTC Ar"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    if(pr.getTableau().getHtml() != null){
      out.println(pr.getTableau().getHtml());
    }else
    {
  %><center><h4>Aucune donne trouv&eacute;e</h4></center><%
  }

%>
</div>
<%=pr.getModalHtml("modalContent")%>
<%
  } catch (Exception e) {
    e.printStackTrace();
  }%>