
<%@page import="caisse.MvtCaisseCpl"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>


<%
    try {
        String id = request.getParameter("id");
        String idFactureFournisseur = request.getParameter("idFactureFournisseur");
        System.out.println("idFactureFournisseur==="+idFactureFournisseur);
        MvtCaisseCpl t = new MvtCaisseCpl();
        String listeCrt[] = {};
        String listeInt[] = {};
        String libEntete[] = {"id", "designation", "idCaisseLib", "debit","credit"};
        PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        String awhere = " AND (idOrigine = '" + id + "'";
        if(idFactureFournisseur != null && !idFactureFournisseur.isEmpty()){
            awhere += " OR idOrigine = '" + idFactureFournisseur + "'";
        }
        awhere += ")";
        pr.setAWhere(awhere);
        String[] colSomme = null;
        pr.setNpp(10);
        pr.creerObjetPage(libEntete, colSomme);
        String lienTableau[] = {pr.getLien() + "?but=caisse/mvt/mvtCaisse-fiche.jsp"};
        String colonneLien[] = {"id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);

%>

<div class="box-body">
    <%  String libEnteteAffiche[] = {"id", "d&eacute;signation", "Caisse", "D&eacute;dit", "Cr&eacute;dit"};
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

