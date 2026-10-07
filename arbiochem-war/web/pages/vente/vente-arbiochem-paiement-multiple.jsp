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

<% try{
    VenteLib bc = new VenteLib();
    String[] etatVal = {"","1","11", "0"};
    String[] etatAff = {"Tous","Cr&eacute;&eacute;e(s)", "Vis&eacute;e(s)", "Annul&eacute;e(s)"};

    String[] deviseVal = {"","AR","EUR","USD"};
    String[] deviseAff = {"Toutes","AR","EUR","USD"};

    bc.setNomTable("ventenonpaye");

    LocalDate today = LocalDate.now();
    LocalDate monday = today.withDayOfMonth(1);
    LocalDate sunday = today.withDayOfMonth(today.lengthOfMonth());

    String listeCrt[] = {"id", "designation","idClientLib","mois", "annee"};
    String listeInt[] = {};
    String libEntete[] = {"id", "designation","idClientLib","idDevise","daty","montantttc","montantpaye", "montantreste","margeBrute","montantRevient","etatlib"};
    String libEnteteAffiche[] = {"id", "D&eacute;signation","Client","devise","Date","Montant TTC","Montant Pay&eacute;","Montant Restant","marge Brute","montant de Revient","&Eacute;tat"};

    // Construire la clause WHERE dynamiquement
    String awhere = "";
    if(request.getParameter("etat") != null && !request.getParameter("etat").isEmpty()) {
        awhere += " and etat='" + request.getParameter("etat") + "'";
    }
    if(request.getParameter("devise") != null && !request.getParameter("devise").isEmpty()) {
        awhere += " and iddevise='" + request.getParameter("devise") + "'";
    }
    PageRecherche pr = new PageRecherche(bc, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    if(!awhere.isEmpty()) {
        pr.setAWhere(awhere);
    }
    pr.setTitre("Edition ristourne");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("vente/vente-arbiochem-paiement-multiple.jsp");
    String[] colSomme = { "montantttc", "montantpaye", "montantreste","margeBrute","montantRevient" };
    pr.getFormu().getChamp("id").setLibelle("ID");
    pr.getFormu().getChamp("designation").setLibelle("D&eacute;signation");
    pr.getFormu().getChamp("idClientLib").setLibelle("Client");
    pr.getFormu().getChamp("idClientLib").setPageAppelComplete("client.Client", "nom", "CLIENT");

    Liste [] liste = new Liste[1];
    String[] valMois = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] affMois = {"Janvier","F&eacute;vrier","Mars","Avril","Mai","Juin","Juillet","Ao&ucirc;t","Septembre","Octobre","Novembre","D&eacute;cembre"};
    liste[0] = new Liste("mois" ,affMois,valMois);
    int mois = Utilitaire.getMoisEnCours() + 1;
    liste[0].setDefaut(mois+"");

    pr.getFormu().getChamp("annee").setDefaut(Utilitaire.getAnneeEnCours());
    pr.getFormu().getChamp("annee").setLibelle("Ann&eacute;e");

    pr.getFormu().changerEnChamp(liste);

//    pr.getFormu().getChamp("daty1").setLibelle("Date Min");
//    pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.datetostring(Date.valueOf(monday)));
//    pr.getFormu().getChamp("daty2").setLibelle("Date Max");
//    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.datetostring(Date.valueOf(sunday)));
    pr.creerObjetPage(libEntete, colSomme);
    //pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());

    Map<String,String> lienTab=new HashMap();
//    lienTab.put("modifier",pr.getLien() + "?but=vente/vente-modif.jsp");
    lienTab.put("Valider",pr.getLien() + "?but=apresTarif.jsp&bute=vente/vente-arbiochem-fiche.jsp&acte=valider"+pr.getFormu().getChamp("id").getValeur()+"");
    lienTab.put("Livrer",pr.getLien() + "?but=bondelivraison-client/apresLivraisonFacture.jsp&bute=vente/encaissement-modif.jsp" + pr.getFormu().getChamp("id").getValeur()+"");
    pr.getTableau().setLienClicDroite(lienTab);

    //Definition des lienTableau et des colonnes de lien
    String lienTableau[] = {pr.getLien() + "?but=vente/vente-arbiochem-fiche.jsp"};
    String colonneLien[] = {"id"};
    String[] lib = {" ","Nombre", "Somme des Montants TTC", "Somme des Montants Pay&eacute;s", "Somme des Montant restants","Somme des marges brutes","Somme des montants de revient"};
    pr.getTableauRecap().setLibeEntete(lib);
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    pr.getTableau().setLienFille("vente/inc/vente-details.jsp&id=");
%>
<script>
    function changerDesignation() {
         document.getElementById("vente").submit();
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
            <div class="row col-md-12">
                <div class="row" style="margin-top: 12px">
                    <div class="col-md-2 nopadding">
                        <label class="input-label" for="devise">Devise :</label>
                        <select name="devise" class="champ form-control" id="devise" onchange="changerDesignation()" >
                            <%
                                for (int i = 0; i < deviseAff.length; i++) {
                            %>
                            <% if (request.getParameter("devise") != null
                                    && request.getParameter("devise").compareToIgnoreCase(deviseVal[i]) == 0) { %>
                            <option value="<%= deviseVal[i] %>" selected><%= deviseAff[i] %></option>
                            <% } else { %>
                            <option value="<%= deviseVal[i] %>"><%= deviseAff[i] %></option>
                            <% } %>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="input-label" for="etat">&Eacute;tat :</label>
                        <select name="etat" class="champ form-control" id="etat" onchange="changerDesignation()">
                            <%
                                for (int i = 0; i < etatAff.length; i++) {
                            %>
                            <% if (request.getParameter("etat") != null
                                    && request.getParameter("etat").compareToIgnoreCase(etatVal[i]) == 0) { %>

                            <option value="<%= etatVal[i] %>" selected>
                                <%= etatAff[i] %>
                            </option>

                            <% } else { %>

                            <option value="<%= etatVal[i] %>">
                                <%= etatAff[i] %>
                            </option>

                            <% } %>
                            <% } %>
                        </select>
                    </div>
                </div>
            </div>

        </form>
        <br>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        <br>
        <form action="<%=pr.getLien()%>?but=ristourne/ristourne-arbiochem-saisie.jsp" method="post" name="vente" id="vente">
        <input type="hidden" value="<%=pr.getLien()%>" name="lien">
        <%
        if(pr.getTableau().getHtmlWithCheckbox()!=null) {
            out.println(pr.getTableau().getHtmlWithCheckbox());
        }else{
            %><div style="text-align: center;"><h4>Aucun donn&eacute; disponible</h4></div><%
        }
            out.println(pr.getBasPage());
        %>
        </form>
    </section>
</div>
<%
    }catch(Exception e){

        e.printStackTrace();
    }
%>
