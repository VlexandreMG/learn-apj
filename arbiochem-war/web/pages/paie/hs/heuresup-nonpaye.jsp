<%@page import="user.UserEJB"%>
<%@page import="bean.*"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="affichage.Liste" %>
<%@ page import="utilitaire.Utilitaire" %>
<%@ page import="fabrication.HeureSupFabricationCPL " %>

<%

    try{

        HeureSupFabricationCPL   hmc = new HeureSupFabricationCPL ();
        hmc.setNomTable("HS_NON_PAYE");
        String listeCrt[] = {"id", "matricule"};
        String listeInt[] = {};
        String libEntete[] = {"id", "matricule", "nompersonnel", "montanthd", "montantjf", "total"};
        PageRecherche pr = new PageRecherche(hmc, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setTitre("Liste des heures supplementaires non pay&eacute;s");
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        pr.setApres("paie/caisse/paiement-hs.jsp");

        pr.getFormu().getChamp("id").setLibelle("id");


        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);

//        String lienTableau[] = {pr.getLien() + "?but=paie/hs/heuresup-nonpaye.jsp"};
//        String colonneLien[] = {"id"};
//        pr.getTableau().setLien(lienTableau);
//        pr.getTableau().setColonneLien(colonneLien);

        String libEnteteAffiche[] = {"id", "matricule", "personnel", "montant HD", "montant JF", "total"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);

%>


%>


<div class="content-wrapper">

    <div class="row">
        <section class="content-header">
            <h1><%= pr.getTitre() %></h1>
        </section>
    </div>

    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <form action="<%= pr.getLien() %>?but=<%= pr.getApres() %>" method="post" name="recap" id="recap">
                    <%= pr.getFormu().getHtmlEnsemble() %>
                </form>

                <%= pr.getTableauRecap().getHtml() %>
                <br>
                <form action="<%= pr.getLien()%>?but=<%= pr.getApres() %>" method="post">
                <% pr.getTableau().setNameBoutton("Payer"); %>
                <%= pr.getTableau().getHtmlWithCheckbox() %>
                </form>
                <%= pr.getBasPage() %>

            </div>
        </div>
    </section>

</div>

<script>
    function changerDesignation() {
        document.recap.submit();
    }
</script>

<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>