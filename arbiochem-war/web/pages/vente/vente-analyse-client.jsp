<%-- 
    Document   : as-commande-analyse
    Created on : 30 d�c. 2016, 04:57:15
    Author     : Joe
--%>
<%@page import="utils.ConstanteAsync"%>
<%@page import="java.util.Calendar"%>
<%@page import="vente.VenteLib"%>
<%@page import="utilitaire.*"%>
<%@page import="affichage.*"%>
<%@page import="bean.TypeObjet"%>
<%@page import="java.util.LinkedHashMap"%>

<%
    try {
        VenteLib mvt = new VenteLib();
        mvt.setNomTable("VENTE_CPL");

        String[] listeCrt   = {"id", "daty", "idDevise", "idClient", "idClientLib", "idProvince", "provinceLib"};
        String[] listeInt   = {"daty"};
        String[] pourcentage = {};
        String[] colGr      = {"idClientLib"};
        String[] colGrCol   = {"iddevise"};
        String[] somDefaut  = {"montantttc", "montantpaye", "montantreste"};

        PageRechercheGroupe pr = new PageRechercheGroupe(
                mvt, request, listeCrt, listeInt, 3, colGr, somDefaut, pourcentage,
                colGr.length, somDefaut.length);

        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));

        LinkedHashMap<String, String[]> rubriquesAnalyse = new LinkedHashMap<>();
        rubriquesAnalyse.put("Client", new String[]{"idClientLib"});
        rubriquesAnalyse.put("Zone", new String[]{"provinceLib"});
        rubriquesAnalyse.put("Magasin", new String[]{"idMagasinLib"});
        rubriquesAnalyse.put("P\u00E9riode", new String[]{"daty", "mois", "annee"});
        rubriquesAnalyse.put("Finance", new String[]{"idDevise", "montantttc", "montantpaye", "montantreste", "montantTtcAr"});

        String[] colonnesSommeAutorisees = {
            "montantttc", "montantpaye", "montantreste", "montantTtcAr"
        };

        pr.setRubriquesAnalyse(rubriquesAnalyse);
        pr.setColonnesSommeAutorisees(colonnesSommeAutorisees);

        String daty1 = request.getParameter("daty1");
        String daty2 = request.getParameter("daty2");
        String apreswhere = "";

        if (daty1 == null || daty2 == null) {
            daty1 = utilitaire.Utilitaire.getDebutMoisString();
            daty2 = utilitaire.Utilitaire.dateDuJour();
            apreswhere = " and daty >= '" + daty1 + "' and daty <= '" + daty2 + "'";
        }

        String order = " order by idClientLib asc";
        String orderParam = request.getParameter("order");
        if (orderParam != null && !orderParam.isEmpty()) {
            order = " " + orderParam;
        }

        String grouperParam = request.getParameter("grouper");
        if (grouperParam != null && !grouperParam.isEmpty()) {
            pr.setColGroupeDefaut(new String[]{grouperParam});
        }

        pr.setOrdre(order);
        pr.setAWhere(apreswhere);

        String pageTitle = "Analyse des ventes par client";
        String titre= "Analyse des ventes par client";
        String desce= "Analyse des ventes par client";
        affichage.Champ[] liste;

        boolean isProvince = "provinceLib".equalsIgnoreCase(grouperParam);

        if (isProvince) {
            pageTitle = "Analyse des ventes par province";
            titre= "Analyse des ventes par province";
            desce= "Analyse des ventes par province";

            TypeObjet province = new TypeObjet();
            province.setNomTable("province");

            liste = new affichage.Champ[2];
            liste[0] = new Liste("iddevise", new caisse.Devise(), "val", "id");
            liste[1] = new Liste("idprovince", province, "val", "id");

            pr.getFormu().getChamp("idClient").setVisible(false);
            pr.getFormu().getChamp("idClientLib").setVisible(false);
            pr.getFormu().getChamp("provinceLib").setVisible(false);
        } else {
            client.Client clientActif = new client.Client();
            clientActif.setNomTable("CLIENT_ACTIF");

            liste = new affichage.Champ[1];
            liste[0] = new Liste("iddevise", new caisse.Devise(), "val", "id");

            pr.getFormu().getChamp("idClient")
                    .setPageAppelComplete("client.Client", "id", clientActif.getNomTable());

            pr.getFormu().getChamp("idProvince").setVisible(false);
            pr.getFormu().getChamp("provinceLib").setVisible(false);
            pr.getFormu().getChamp("idClientLib").setVisible(false);
        }

        pr.getFormu().getChamp("iddevise").setLibelle("ID Devise");
        pr.getFormu().changerEnChamp(liste);

        String apres = "vente/vente-analyse-client.jsp";
        if (grouperParam != null && !grouperParam.trim().isEmpty()) {
            apres += "&grouper=" + grouperParam;
        }

        pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.getDebutMoisString());
        pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());
        pr.getFormu().getChamp("daty1").setLibelle("Date Min");
        pr.getFormu().getChamp("daty2").setLibelle("Date max");
        pr.getFormu().getChamp("idClient").setLibelle("ID Client");
        pr.getFormu().getChamp("iddevise").setLibelle("ID Devise");
        pr.getFormu().getChamp("idProvince").setLibelle("ID Province");

        pr.setNpp(500);
        pr.setApres(apres);
        pr.creerObjetPageCroise(colGrCol, pr.getLien() + "?but=vente/vente-liste.jsp&daty1=" + daty1 + "&daty2=" + daty2);
%>
<script>
    function changerDesignation() {
        document.analyse.submit();
    }
    $(document).ready(function() {
        $('.box table tr').each(function () {
            $(this).find('td:last, th:last').hide();
        });
    });
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pageTitle %></h1>
    </section>
    <section class="content">
        <form action="<%= pr.getLien() %>?but=<%= apres %>"
              method="post"
              name="analyse"
              id="analyse">
            <%out.println(pr.getFormu().getHtmlEnsemble());%>
        </form>
        <ul>
            <li><strong>La premi&egrave;re ligne repr&eacute;sente la somme des factures.</strong> </li>
            <li><strong>La deuxi&egrave;me ligne repr&eacute;sente la somme des montants pay&eacute;s.</strong></li>
            <li><strong>La troisi&egrave;me ligne repr&eacute;sente la somme des montants impay&eacute;es.</strong></li>
        </ul>
        <%
            String[] lienTableau = {};
            pr.getTableau().setLien(lienTableau);
            pr.getTableau().setColonneLien(somDefaut);
        %>
        <br>
        <%
            out.println(pr.getHtmlWithEvaluation(ConstanteAsync.API_URL, ConstanteAsync.API_KEY, titre, desce));
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
%>
        <script language="JavaScript">
            alert('<%=e.getMessage() != null ? e.getMessage().replace("'", "\\'").replace("", "").replace("
", " ") : "Erreur durant la recherche groupée."%>');
            history.back();
        </script>
<%
    }
%>
