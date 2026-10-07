<%@page import="faturefournisseur.FactureFournisseurDetailsCpl" %>
<%@page import="utilitaire.Utilitaire" %>
<%@page import="affichage.PageRecherche" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<% try {
    FactureFournisseurDetailsCpl bc = new FactureFournisseurDetailsCpl();
    bc.setNomTable("FACTUREFOURNISSEURFILLECPL");

    String idProduitLib = request.getParameter("idproduitlib");
    if (idProduitLib == null) idProduitLib = request.getParameter("idProduitLib");

    String idPointLib = request.getParameter("idpointlib");
    if (idPointLib == null) idPointLib = request.getParameter("idPointLib");

    String idMagasinLib = request.getParameter("idmagasinlib");
    if (idMagasinLib == null) idMagasinLib = request.getParameter("idMagasinLib");

    String idCategorieLib = request.getParameter("idcategorielib");
    if (idCategorieLib == null) idCategorieLib = request.getParameter("idCategorieLib");

    String daty1 = request.getParameter("daty1");
    String daty2 = request.getParameter("daty2");

    String[] listeCrt = {"idProduitLib", "idPointLib", "idMagasinLib", "idCategorieLib", "daty"};
    String[] listeInt = {"daty"};
    String[] libEntete = {"id", "idFactureFournisseur", "libelleexacte", "qte", "pu", "montantttc", "idPointLib", "idMagasinLib", "idCategorieLib", "daty"};
    String[] libEnteteAffiche = {"ID", "ID Facture", "Produit", "Quantit&eacute;", "Prix Unitaire", "Montant TTC", "Point", "Magasin", "Cat&eacute;gorie", "Date"};

    PageRecherche pr = new PageRecherche(bc, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setTitre("D&eacute;tails des achats");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("facturefournisseur/achat-details-liste.jsp");

    String[] colSomme = {"qte", "montantttc"};

    pr.getFormu().getChamp("idProduitLib").setLibelle("Produit");
    if (idProduitLib != null && !idProduitLib.isEmpty()) {
        pr.getFormu().getChamp("idProduitLib").setDefaut(idProduitLib);
    }

    pr.getFormu().getChamp("idPointLib").setLibelle("Point");
    if (idPointLib != null && !idPointLib.isEmpty()) {
        pr.getFormu().getChamp("idPointLib").setDefaut(idPointLib);
    }

    pr.getFormu().getChamp("idMagasinLib").setLibelle("Magasin");
    if (idMagasinLib != null && !idMagasinLib.isEmpty()) {
        pr.getFormu().getChamp("idMagasinLib").setDefaut(idMagasinLib);
    }

    pr.getFormu().getChamp("idCategorieLib").setLibelle("Cat&eacute;gorie");
    if (idCategorieLib != null && !idCategorieLib.isEmpty()) {
        pr.getFormu().getChamp("idCategorieLib").setDefaut(idCategorieLib);
    }

    pr.getFormu().getChamp("daty1").setLibelle("Date Min");
    if (daty1 != null && !daty1.isEmpty()) {
        pr.getFormu().getChamp("daty1").setDefaut(daty1);
    } else {
        pr.getFormu().getChamp("daty1").setDefaut(Utilitaire.dateDuJour());
    }

    pr.getFormu().getChamp("daty2").setLibelle("Date Max");
    if (daty2 != null && !daty2.isEmpty()) {
        pr.getFormu().getChamp("daty2").setDefaut(daty2);
    } else {
        pr.getFormu().getChamp("daty2").setDefaut(Utilitaire.dateDuJour());
    }

    pr.creerObjetPage(libEntete, colSomme);

    String[] lienTableau = {pr.getLien() + "?but=facturefournisseur/facturefournisseur-fiche.jsp"};
    String[] colonneLien = {"idFactureFournisseur"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
%>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %>
        </h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name="achatDetails" id="achatDetails">
            <%
                String libelles[] = {" ", "Nombre", "Somme des Quantit&eacute;s", "Somme des Montants TTC"};
                pr.getTableauRecap().setLibeEntete(libelles);
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
        </form>
        <% out.println(pr.getTableauRecap().getHtml()); %>
        <br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
