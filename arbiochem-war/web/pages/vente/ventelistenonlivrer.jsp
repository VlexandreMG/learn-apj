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
    bc.setNomTable("VENTE_CPL");

    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(previousOrSame(MONDAY));
    LocalDate sunday = today.with(nextOrSame(SUNDAY));

    String[] etatVal = {"","1","11", "0"};
    String[] etatAff = {"Tous","Cr&eacute;&eacute;e(s)", "Vis&eacute;e(s)", "Annul&eacute;e(s)"};


    if (request.getParameter("devise") != null && request.getParameter("devise").compareToIgnoreCase("") != 0) {
        bc.setNomTable(request.getParameter("devise"));
    } else {
        bc.setNomTable("VENTE_CPL");
    }
    String[] listeCrt = {"id", "designation","numerofacture","idClientLib","daty","datyprevu", "provincelib","montantttc","montantpaye","montantreste"};
    String[] listeInt = {"daty","datyprevu","montantttc","montantpaye","montantreste"};
    String[] libEntete = {"id", "datyprevu","designation","idClientLib","numerofacture","montantttc","montantpaye", "montantreste", "provincelib", "etatlib"};
    String[] libEnteteAffiche = {"id", "Date", "D&eacute;signation","Client","Num&eacute;ro de la facture","Montant TTC","Montant Pay&eacute;","Montant restant", "Province","&Eacute;tat", "Mode de livraison"};
    PageRecherche pr = new PageRecherche(bc, request, listeCrt, listeInt, 3, libEntete, libEntete.length);

    String awhere = "";
    if(request.getParameter("etat")!=null && request.getParameter("etat").compareToIgnoreCase("")!=0) {
        awhere = " and etat=" + request.getParameter("etat") ;
    }

    pr.setAWhere(pr.getAWhere()+" and reste>0 and id not in (select val from livraisonFictif)" + awhere);

    pr.setTitre("Liste des factures client");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("vente/ventelistenonlivrer.jsp");
    //pr.setOrdre(" order by daty desc");
    String[] colSomme = { "montantttc", "montantpaye", "montantreste" };
    pr.getFormu().getChamp("id").setLibelle("ID");
    pr.getFormu().getChamp("idClientLib").setLibelle("Client");
    pr.getFormu().getChamp("idClientLib").setPageAppelComplete("client.Client", "nom", "CLIENT");
    pr.getFormu().getChamp("designation").setLibelle("D&eacute;signation");
    pr.getFormu().getChamp("numerofacture").setLibelle("Num&eacute;ro de la facture");
    //pr.getFormu().getChamp("referencefacture").setLibelle("R&eacute;f&eacute;rence de la facture");
    pr.getFormu().getChamp("daty1").setLibelle("Date Min");
    pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("daty2").setLibelle("Date Max");
    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("montantttc1").setLibelle("Montant TTC min");
    pr.getFormu().getChamp("montantttc2").setLibelle("Montant TTC Max");
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
    lienTab.put("modifier",pr.getLien() + "?but=vente/vente-modif.jsp");
    lienTab.put("Valider",pr.getLien() + "?&classe=vente.Vente&but=apresTarif.jsp&bute=vente/vente-fiche.jsp&acte=valider"+pr.getFormu().getChamp("id").getValeur()+"");
//    lienTab.put("Livrer",pr.getLien() + "?but=bondelivraison-client/apresLivraisonFacture.jsp&bute=vente/encaissement-modif.jsp" + pr.getFormu().getChamp("id").getValeur()+"");
    lienTab.put("Livrer",
            pr.getLien()
                    + "?but=bondelivraison-client/bondelivraison-client-saisie.jsp"
                    + pr.getFormu().getChamp("id").getValeur()
                    + "&classe=vente.Vente"
    );


    pr.getTableau().setLienClicDroite(lienTab);

    //Definition des lienTableau et des colonnes de lien
    String[] lienTableau = {pr.getLien() + "?but=vente/vente-fiche.jsp"};
    String[] colonneLien = {"id"};
    String[] enteteRecap = {"","Nombres","Somme des montants TTC","Somme des montants pay&eacute;s","Somme des montants restants"};
    pr.getTableauRecap().setLibeEntete(enteteRecap);
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    pr.getTableau().setLienFille("vente/inc/vente-details.jsp&id=");

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
            </div>
        </form>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        <br>
        <form action="<%= pr.getLien() + "?but=vente/apreslivre.jsp"%>" method="post" >
            <input type="hidden" name="acte" value="livrer">
            <input type="hidden" name="bute" value="vente/ventelistenonlivrer.jsp">
            <%
//                if (pr.getTableau().getHtmlWithCheckbox() != null) {
//                    out.println(pr.getTableau().getHtmlWithCheckbox());
//                } else {

                if (pr.getTableau().getHtml() != null) {
                    out.println(pr.getTableau().getHtml());
                } else {
            %>
            <h1>Aucune donn&eacute;e disponible</h1>
            <%
                }
            %>
        </form>
        <br>

        <%
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    }catch(Exception e){

        e.printStackTrace();
    }
%>




