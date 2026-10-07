<%@page import="faturefournisseur.As_BonDeLivraison_Fille_Cpl"%>
<%@page import="affichage.PageRecherche"%>
<%
    try{
    As_BonDeLivraison_Fille_Cpl t = new As_BonDeLivraison_Fille_Cpl();
    t.setNomTable("RAPPROCHEMENT_BF_FOURNISSEUR");
    String listeCrt[] = {"id","numBl","produit","produitlib"};
    String listeInt[] = {};
    String libEntete[] = {"id","numBl","produit","produitlib","unitelib","quantite","qteEntree","resteAEntrer"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setTitre("Liste des bons de r&eacute;ception avec anomalies");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    String awhere = " and resteAEntrer > 0";
    pr.setAWhere(awhere);
    pr.setApres("bondelivraison/bondelivraison-liste-anomalies.jsp");

    pr.getFormu().getChamp("numBl").setLibelle("ID BL");
    pr.getFormu().getChamp("Produit").setLibelle("ID Produit");
    pr.getFormu().getChamp("produitlib").setLibelle("Produit");

    String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);
    //Definition des lienTableau et des colonnes de lien
    String lienTableau[] = {pr.getLien() + "?but=bondelivraison/bondelivraison-fiche.jsp"};
    String colonneLien[] = {"numBl"};
    String attLien[] = {"id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setAttLien(attLien);
    pr.getTableau().setColonneLien(colonneLien);

    String libEnteteAffiche[] = {"Id","ID BL","Id Produit", "Produit","Unit&eacute;","Quantit&eacute;","Quantit&eacute; Entr&eacute;e","Reste &agrave; entrer" };
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
%>
<script>
    function changerDesignation() {
        document.filtre.submit();
    }
</script>

<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name = "filtre">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
        </form>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        <br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
    <%
    }catch(Exception e){

        e.printStackTrace();
    }
%>
