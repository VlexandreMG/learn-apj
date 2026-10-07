<%--
    Document   : vente-liste
    Created on : 25 mars 2024, 09:57:03
    Author     : Angela
--%>

<%@page import="vente.VenteLib"%>
<%@page import="vente.Vente"%>
<%@page import="utilitaire.Utilitaire"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="static java.time.DayOfWeek.MONDAY" %>
<%@ page import="static java.time.temporal.TemporalAdjusters.previousOrSame" %>
<%@ page import="static java.time.DayOfWeek.SUNDAY" %>
<%@ page import="static java.time.temporal.TemporalAdjusters.nextOrSame" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="vente.Vente" %>
<%@ page import="affichage.Liste" %>
<%@ page import="bean.TypeObjet" %>

<% try{
    VenteLib bc = new VenteLib();
    String[] etatVal = {"","1","11", "0"};
    String[] etatAff = {"Tous","Cr&eacute;&eacute;e(s)", "Vis&eacute;e(s)", "Annul&eacute;e(s)"};

    String[] paiementVal = {"","1","0", "-1"};
    String[] paiementAff = {"Tous","Pay&eacute;e(s)", "Impay&eacute;e(s)", "Avec avoir"};

    String[] livraisonVal = {"","0","1"};
    String[] livraisonAff = {"Tous","Livr&eacute;e(s)", "Non Livr&eacute;e(s)"};
    bc.setNomTable("VENTE_CPL");

    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(previousOrSame(MONDAY));
    LocalDate sunday = today.with(nextOrSame(SUNDAY));





    if (request.getParameter("devise") != null && request.getParameter("devise").compareToIgnoreCase("") != 0) {
        bc.setNomTable(request.getParameter("devise"));
    } else {
        bc.setNomTable("VENTE_CPL");
    }
    String[] listeCrt = {"id", "designation","numerofacture","idClientLib","daty","datyprevu", "provincelib","montantttc","montantpaye","montantreste","datelivraison"};
    String[] listeInt = {"daty","datyprevu","montantttc","montantpaye","montantreste","datelivraison"};
    String[] libEntete = {"id", "datyprevu","designation","idClientLib","numerofacture","montantttc","montantpaye", "montantreste","datelivraison","provincelib", "etatlib"};
    String[] libEnteteAffiche = {"id", "Date", "D&eacute;signation","Client","Num&eacute;ro de la facture","Montant TTC","Montant Pay&eacute;","Montant restant","date de Livraison", "Province","&Eacute;tat","Mode de livraison"};
    PageRecherche pr = new PageRecherche(bc, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    if(request.getParameter("etat")!=null && request.getParameter("etat").compareToIgnoreCase("")!=0) {
        pr.setAWhere(" and etat=" + request.getParameter("etat"));
    }
    if(request.getParameter("paiement")!=null && request.getParameter("paiement").compareToIgnoreCase("")!=0) {
        if(request.getParameter("paiement").compareToIgnoreCase("0")==0){
            pr.setAWhere(pr.getAWhere()+" and montantreste>0");
        } else if(request.getParameter("paiement").compareToIgnoreCase("1")==0){
            pr.setAWhere(pr.getAWhere()+" and montantreste=0");
        }else if(request.getParameter("paiement").compareToIgnoreCase("-1")==0){
            pr.setAWhere(pr.getAWhere()+" and montantreste<0");
        }
    }
    if(request.getParameter("livraison")!=null && request.getParameter("livraison").compareToIgnoreCase("")!=0) {
        if(request.getParameter("livraison").compareToIgnoreCase("0")==0){
            pr.setAWhere(pr.getAWhere()+" and (reste=0 or id in (select val from livraisonFictif))");
        } else if(request.getParameter("livraison").compareToIgnoreCase("1")==0){
            pr.setAWhere(pr.getAWhere()+" and (reste>0  and id not in (select val from livraisonFictif))");
        }
    }
    pr.setAWhere(pr.getAWhere()+ " order by numerofacture desc");
    pr.setTitre("Liste des factures client");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("vente/vente-arbiochem-liste.jsp");
    //pr.setOrdre(" order by daty desc");
    String[] colSomme = { "montantttc", "montantpaye", "montantreste" };
    pr.getFormu().getChamp("id").setLibelle("ID");
    pr.getFormu().getChamp("idClientLib").setLibelle("Client");
    pr.getFormu().getChamp("idClientLib").setPageAppelComplete("client.Client", "nom", "CLIENT");
    pr.getFormu().getChamp("designation").setLibelle("D&eacute;signation");
    pr.getFormu().getChamp("numerofacture").setLibelle("Num&eacute;ro de la facture");
    pr.getFormu().getChamp("daty1").setLibelle("Date Min");
    pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("daty2").setLibelle("Date Max");
    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("montantttc1").setLibelle("Montant TTC min");
    pr.getFormu().getChamp("montantttc2").setLibelle("Montant TTC Max");
    pr.getFormu().getChamp("datelivraison1").setLibelle("Date de livraison min");
    pr.getFormu().getChamp("datelivraison2").setLibelle("Date de livraison max");
    pr.getFormu().getChamp("montantpaye1").setLibelle("Montant Pay&eacute; min");
    pr.getFormu().getChamp("montantpaye2").setLibelle("Montant Pay&eacute; Max");
    pr.getFormu().getChamp("montantreste1").setLibelle("Montant restant min");
    pr.getFormu().getChamp("montantreste2").setLibelle("Montant restant max");



//    pr.getFormu().getChamp("montantttc1").setLibelle("Montant TTC Min");
//    pr.getFormu().getChamp("montantttc2").setLibelle("Montant TTC Max");
//    pr.getFormu().getChamp("montantpaye1").setLibelle("Montant pay&eacute; Min");
//    pr.getFormu().getChamp("montantpaye2").setLibelle("Montant pay&eacute; Max");
//    pr.getFormu().getChamp("montantreste1").setLibelle("Montant Restant Min");
//    pr.getFormu().getChamp("montantreste2").setLibelle("Montant Restant Max");

    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());

    pr.getFormu().getChamp("datyprevu1").setLibelle("&Eacute;ch&eacute;ance Min");
    pr.getFormu().getChamp("datyprevu2").setLibelle("&Eacute;ch&eacute;ance Max");


    TypeObjet prov = new TypeObjet();
    prov.setNomTable("province");

    Liste[] liste = new Liste[1];
    liste[0] = new Liste("provincelib",prov,"val","val");
    liste[0].setLibelle("Province");

    //Liste listemode = new Liste("modelivraison");

    String [] affVal = new String[2];
    String [] aff = new String[2];
    aff = new String[]{"Tous", "LIVRAISON","RECUPERATION"};
    affVal = new String[]{"%", "1","2"};

    //listemode.ajouterValeur(affVal,aff);
    //liste[1] = listemode;

    //pr.getFormu().getChamp("provincelib").setLibelle("Province");
    pr.getFormu().changerEnChamp(liste);
    pr.creerObjetPage(libEntete, colSomme);

    Map<String,String> lienTab=new HashMap();
    lienTab.put("modifier",pr.getLien() + "?but=vente/vente-arbiochem-saisie.jsp&acte=update");
    lienTab.put("Valider",pr.getLien() + "?&classe=vente.Vente&but=apresTarif.jsp&bute=vente/vente-arbiochem-fiche.jsp&acte=valider"+pr.getFormu().getChamp("id").getValeur()+"");
    lienTab.put("Livrer",pr.getLien() + "?but=bondelivraison-client/apresLivraisonFacture.jsp&bute=vente/encaissement-modif.jsp" + pr.getFormu().getChamp("id").getValeur()+"");
    pr.getTableau().setLienClicDroite(lienTab);

    //Definition des lienTableau et des colonnes de lien
    String[] lienTableau = {pr.getLien() + "?but=vente/vente-arbiochem-fiche.jsp"};
    String[] colonneLien = {"id"};
    String[] enteteRecap = {"","Nombres","Somme des montants TTC","Somme des montants pay&eacute;s","Somme des montants restants"};
    pr.getTableauRecap().setLibeEntete(enteteRecap);
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    pr.getTableau().setLienFille("vente/inc/vente-details-arbiochem.jsp&id=");
     pr.getFormu().setAnotherButton(
            "                <a id='export-btn-details' class='btn btn-secondary  btn-bg-white btn-small' href='#' onclick='exportPDFAvecDetails()'>\n" +
            "                    <i class='material-symbols-rounded'>download</i> Exporter en pdf avec d&eacute;tails\n" +
            "                </a>\n" +
            "                <a id='export-btn-excel' class='btn btn-secondary  btn-small btn-bg-white' href='#' onclick='exportExcel()'>\n" +
            "                    <i class='material-symbols-rounded'>download</i> Exporter en excel\n" +
            "                </a>\n" +
            "                <a id='export-btn' class='btn btn-secondary  btn-small btn-bg-white' href='#' onclick='exportPDF()'>\n" +
            "                    <i class='material-symbols-rounded'>download</i> Exporter en pdf\n" +
            "                </a>\n" +
            "                <a id='export-btn-encaissement' class='btn btn-secondary  btn-small btn-bg-white' href='#' onclick='exportPDFEncaisser()'>\n" +
            "                    <i class='material-symbols-rounded'>download</i>Imprimer &agrave; Encaisser\n" +
            "                </a>\n" +
            "                <a class=\"btn btn-primary pull-right  btn-small\" href=\"module.jsp?but=vente/vente-arbiochem-saisie.jsp\">\n" +
            "                    <i class=\"material-symbols-rounded\">add</i> Saisir une facture client\n" +
            "                </a>"
        );

        %>
<script>
    function changerDesignation() {
        document.vente.submit();
    }
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name="vente" id="vente">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <div class="row col-md-12 nopadding" style="margin-top: 12px">
                <div class="col-md-2 nopadding">
                    <label class="input-label" for="etat">&Eacute;tat :</label>
                    <select name="etat" class="champ form-control" id="etat" onchange="changerDesignation()">
                        <%
                            for( int i = 0; i < etatAff.length; i++ ){ %>
                        <% if(request.getParameter("etat") !=null && request.getParameter("etat").compareToIgnoreCase(etatVal[i]) == 0) {%>
                        <option value="<%= etatVal[i] %>" selected> <%= etatAff[i] %> </option>
                        <% } else { %>
                        <option value="<%= etatVal[i] %>"> <%= etatAff[i] %> </option>
                        <% } %>
                        <%    }
                        %>
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="livraison">Livraison :</label>
                    <select name="livraison" class="champ form-control" id="livraison" onchange="changerDesignation()">
                        <%
                            for( int i = 0; i < livraisonAff.length; i++ ){ %>
                        <% if(request.getParameter("livraison") !=null && request.getParameter("livraison").compareToIgnoreCase(livraisonVal[i]) == 0) {%>
                        <option value="<%= livraisonVal[i] %>" selected> <%= livraisonAff[i] %> </option>
                        <% } else { %>
                        <option value="<%= livraisonVal[i] %>"> <%= livraisonAff[i] %> </option>
                        <% } %>
                        <%    }
                        %>
                    </select>
                </div>
                <div class="col-md-2 nopadding">
                    <label class="input-label" for="paiement">Paiement :</label>
                    <select name="paiement" class="champ form-control" id="paiement" onchange="changerDesignation()">
                        <%
                            for( int i = 0; i < paiementAff.length; i++ ){ %>
                        <% if(request.getParameter("paiement") !=null && request.getParameter("paiement").compareToIgnoreCase(paiementVal[i]) == 0) {%>
                        <option value="<%= paiementVal[i] %>" selected> <%= paiementAff[i] %> </option>
                        <% } else { %>
                        <option value="<%= paiementVal[i] %>"> <%= paiementAff[i] %> </option>
                        <% } %>
                        <%    }
                        %>
                    </select>
                </div>
            </div>

        </form>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        <br>

        <%
            out.println(pr.getTableau().getHtml());
        %>
        <div class="row justify-content-center d-none">
            <div class="col-md-12 d-flex justify-content-right">
                <div class="box box-primary">
                    <div class="box-body text-center">
                        <a id="export-btn" class="btn btn-warning pull-right" href="#">
                            Exporter en pdf <i class="fa fa-download"></i>
                        </a>
                        <a style="margin-right:10px" id="export-btn-excel" class="btn btn-success pull-right" href="#">
                            Exporter en excel <i class="fa fa-download"></i>
                        </a>
                        <a id="export-btn-details" class="btn btn-warning pull-left" href="#">
                            Exporter en pdf avec d&eacute;tails <i class="fa fa-download"></i>
                        </a>

                    </div>
                </div>
            </div>
        </div>
        <%
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<script>
    function exportPDF() {
        // Récupération des champs
        var daty1Value = document.getElementById("daty1").value;
        var daty2Value = document.getElementById("daty2").value;
        var idValue = document.getElementById("id").value;
        var designationValue = document.getElementById("designation").value;
        var idClientLibValue = document.getElementById("idClientLib").value;

        var exportUrl = "${pageContext.request.contextPath}/ExportPDF?action=vente_liste"
            + "&daty1=" + encodeURIComponent(daty1Value)
            + "&daty2=" + encodeURIComponent(daty2Value)
            + "&id=" + encodeURIComponent(idValue)
            + "&designation=" + encodeURIComponent(designationValue)
            + "&idClientLib=" + encodeURIComponent(idClientLibValue)


        var exportButton = document.getElementById("export-btn");
        exportButton.href = exportUrl;
    }
    function exportPDFEncaisser() {
        // Récupération des champs
        var daty1Value = document.getElementById("daty1").value;
        var daty2Value = document.getElementById("daty2").value;
        var idValue = document.getElementById("id").value;
        var designationValue = document.getElementById("designation").value;
        var idClientLibValue = document.getElementById("idClientLib").value;
        var provincelib = document.getElementById("provincelib").value;

        var exportUrl = "${pageContext.request.contextPath}/ExportPDF?action=vente_liste_encaisser"
            + "&daty1=" + encodeURIComponent(daty1Value)
            + "&daty2=" + encodeURIComponent(daty2Value)
            + "&id=" + encodeURIComponent(idValue)
            + "&designation=" + encodeURIComponent(designationValue)
            + "&idClientLib=" + encodeURIComponent(idClientLibValue)
            + "&provincelib=" + encodeURIComponent(provincelib)


        var exportButton = document.getElementById("export-btn-encaissement");
        exportButton.href = exportUrl;
    }
    function exportPDFAvecDetails() {
        // Récupération des champs
        var daty1Value = document.getElementById("daty1").value;
        var daty2Value = document.getElementById("daty2").value;
        var idValue = document.getElementById("id").value;
        var designationValue = document.getElementById("designation").value;
        var idClientLibValue = document.getElementById("idClientLib").value;


        var exportUrl = "${pageContext.request.contextPath}/ExportPDF?action=vente_liste_mere_fille"
            + "&daty1=" + encodeURIComponent(daty1Value)
            + "&daty2=" + encodeURIComponent(daty2Value)
            + "&id=" + encodeURIComponent(idValue)
            + "&designation=" + encodeURIComponent(designationValue)
            + "&idClientLib=" + encodeURIComponent(idClientLibValue)


        var exportButton = document.getElementById("export-btn-details");
        exportButton.href = exportUrl;
    }
    function exportExcel() {
        // Récupération des champs
        var daty1Value = document.getElementById("daty1").value;
        var daty2Value = document.getElementById("daty2").value;
        var idValue = document.getElementById("id").value;
        var designationValue = document.getElementById("designation").value;
        var idClientLibValue = document.getElementById("idClientLib").value;

        var exportUrl = "${pageContext.request.contextPath}/ExportExcel?action=vente_liste"
            + "&daty1=" + encodeURIComponent(daty1Value)
            + "&daty2=" + encodeURIComponent(daty2Value)
            + "&id=" + encodeURIComponent(idValue)
            + "&designation=" + encodeURIComponent(designationValue)
            + "&idClientLib=" + encodeURIComponent(idClientLibValue)


        var exportButton = document.getElementById("export-btn-excel");
        exportButton.href = exportUrl;
    }
    function exportReportingJournalier() {
        // Récupération des champs
        var daty1Value = document.getElementById("daty1").value;
        var daty2Value = document.getElementById("daty2").value;
        var idValue = document.getElementById("id").value;
        var designationValue = document.getElementById("designation").value;
        var idClientLibValue = document.getElementById("idClientLib").value;
        // var idModePaiementValue = document.getElementById("idmodepaiement").value;

        var exportUrl = "${pageContext.request.contextPath}/ExportPDF?action=reporting_journalier"
            + "&daty1=" + encodeURIComponent(daty1Value)
            + "&daty2=" + encodeURIComponent(daty2Value)
            + "&id=" + encodeURIComponent(idValue)
            + "&designation=" + encodeURIComponent(designationValue)
            + "&idClientLib=" + encodeURIComponent(idClientLibValue);
            // + "&idmodepaiement=" + encodeURIComponent(idModePaiementValue);


        var exportButton = document.getElementById("reporting-journalier");
        exportButton.href = exportUrl;
    }

    document.getElementById("export-btn").addEventListener("click", exportPDF);
    document.getElementById("export-btn-encaissement").addEventListener("click", exportPDFEncaisser());
    document.getElementById("export-btn-details").addEventListener("click", exportPDFAvecDetails);
    document.getElementById("export-btn-excel").addEventListener("click", exportExcel);
    document.getElementById("reporting-journalier").addEventListener("click", exportReportingJournalier);
</script>
<%
    }catch(Exception e){

        e.printStackTrace();
    }
%>




