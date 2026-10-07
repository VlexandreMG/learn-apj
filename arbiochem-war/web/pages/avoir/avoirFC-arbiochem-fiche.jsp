<%-- 
    Document   : avoirFC-fiche
    Created on : 2 août 2024, 15:56:27
    Author     : randr
--%>


<%@page import="avoir.AvoirFCLib"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<%
		 try{
    UserEJB u = (user.UserEJB)session.getValue("u");
    String lien = (String) session.getValue("lien");
    AvoirFCLib avoir = new AvoirFCLib();
    avoir.setNomTable("avoirfclib_cpl");
    PageConsulte pc = new PageConsulte(avoir, request, u);
    pc.setTitre("Fiche de facture d'avoir");
    String pageActuel = "avoir/avoirFC-arbiochem-fiche.jsp";
    avoir =(AvoirFCLib) pc.getBase();
    String id=pc.getBase().getTuppleID();
    pc.getChampByName("id").setLibelle("Id");
    pc.getChampByName("idMagasinLib").setLibelle("Magasin");
    pc.getChampByName("idVente").setLibelle(" R&eacute;f&eacute;rence Vente");
    pc.getChampByName("idVente").setLien(lien+"?but=vente/vente-arbiochem-fiche.jsp", "id=");
    pc.getChampByName("idVenteLib").setVisible(false);
    pc.getChampByName("idCategorieLib").setLibelle("Cat&eacute;gorie");
    pc.getChampByName("idMotifLib").setLibelle("Motif");
    pc.getChampByName("compte").setLibelle("Compte de Tiers");
//    pc.getChampByName("idtypeavoirlib").setLibelle("Type");
     pc.getChampByName("idDevise").setLibelle("Devise");
    pc.getChampByName("montantHT").setLibelle("Montant HT");
    pc.getChampByName("montantTVA").setLibelle("Montant TVA"); 
    pc.getChampByName("montantTTC").setLibelle("Montant TTC"); 
    pc.getChampByName("montantHTAr").setLibelle("Montant HT en Ar");
    pc.getChampByName("montantTVAAr").setLibelle("Montant TVA  en Ar");
    pc.getChampByName("montantTTCAr").setLibelle("Montant TTC en Ar");
    pc.getChampByName("resteapayer").setLibelle("Reste &agrave; payer"); 
    pc.getChampByName("resteapayerar").setLibelle("Reste &agrave; payer en Ar");
             pc.getChampByName("numfact").setLibelle("Num&eacute;ro facture");
             pc.getChampByName("designation").setLibelle("D&eacute;signation");
             pc.getChampByName("typeavoir").setLibelle("Type");
             pc.getChampByName("clientlib").setLibelle("Client");
             pc.getChampByName("idorigine").setLibelle("Origine");
    pc.getChampByName("daty").setLibelle("Date");
    pc.getChampByName("etat").setLibelle("&Eacute;tat");

    pc.getChampByName("idMagasin").setVisible(false);
    //pc.getChampByName("idOrigine").setVisible(false);
    pc.getChampByName("idClient").setVisible(false);
    pc.getChampByName("idMotif").setVisible(false);
    pc.getChampByName("idCategorie").setVisible(false);
    pc.getChampByName("tauxdechange").setVisible(false);
    
    String pageModif = "avoir/avoirFC-arbiochem-saisie.jsp";
    String classe = "avoir.AvoirFC";
    
    Onglet onglet = new Onglet("page1");
    onglet.setDossier("inc");
    Map<String, String> map = new HashMap<String, String>();
    map.put("avoirfc-details", "");
    if(avoir.getEtat() >= ConstanteEtat.getEtatValider()) {
        map.put("ecriture-detail", "");
        map.put("encaissement-vise-liste", "");
    }
    String tab = request.getParameter("tab");
    if (tab == null) {
        tab = "avoirfc-details";
    }
    map.put(tab, "active");
    tab = "inc/" + tab + ".jsp";
%>

<div class="content-wrapper">
    <h1 class="box-title"><%=pc.getTitre()%></h1>
    <div class="row m-0">
        <div class="col-md-12 mb-5">
            <div class="box-fiche">
                <div class="box">
                    <div class="box-body">
                        <%
                            out.println(pc.getHtml());
                        %>
                        <div class="box-footer">
                            <% if(avoir.getEtat() ==1) { %>
                              <a  class="btn btn-danger pull-left"  href="<%= lien + "?but=apresTarif.jsp&id=" + id+"&acte=annuler&bute=avoir/avoirFC-fiche.jsp&classe="+classe %>" style="margin-right: 10px !important">Annuler</a>
                                <a class="btn btn-primary pull-right" href="<%= (String) session.getValue("lien") + "?but=apresTarif.jsp&acte=valider&id=" + request.getParameter("id") + "&bute=avoir/avoirFC-arbiochem-fiche.jsp&classe=" + classe %> " style="margin-right: 10px">Viser</a>
                                 <a class="btn btn-secondary pull-right"  href="<%= lien + "?but="+ pageModif +"&acte=update&id=" + id%>" style="margin-right: 10px">Modifier</a>
                            <% } %>
                           
                            <% if(avoir.getEtat()>= 11) { %>
                            <a class="btn btn-secondary pull-right" href="<%= (String) session.getValue("lien") + "?but=caisse/mvt/mvtCaisse-saisie-entree-avoir.jsp&idOrigine=" + avoir.getId()%> " style="margin-right: 10px">D&eacute;caisser</a>
                            <a class="btn btn-secondary pull-right" href="<%= (String) session.getValue("lien") + "?but=avoir/apres-generation-avoir.jsp&acte=sortieStock&bute=avoir/avoirFC-fiche.jsp&id=" + avoir.getId()%> " style="margin-right: 10px">Sortie de stock</a>
                            <% } %>
                            <% if(avoir.getEtat()== 11 && avoir.getResteapayer() == avoir.getMontantTTCAr()) { %>
                                <a class="btn btn-danger pull-left"  href="<%= (String) session.getValue("lien") + "?but=apresTarif.jsp&acte=annulerVisa&id=" + request.getParameter("id") + "&bute=avoir/avoirFC-fiche.jsp&classe=" + classe %>" >Annuler Visa</a>
                            <% } %>
                             <a class="btn btn-tertiary pull-right"  href="${pageContext.request.contextPath}/ExportPDF?action=fiche_vente_nouveau_avoir&id=<%=request.getParameter("id")%>" style="margin-right: 10px">Imprimer</a>
                               
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row m-0">
        <div class="col-md-12 ">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <!-- a modifier -->
                    <li class="<%=map.get("avoirfc-details")%>"><a href="<%= lien%>?but=<%= pageActuel%>&id=<%= id%>&tab=avoirfc-details">D&eacute;tails</a></li>
                    <% if(avoir.getEtat() > 0) { %>
                        <li class="<%=map.get("ecriture-detail")%>"><a href="<%= lien%>?but=<%= pageActuel%>&id=<%= id%>&tab=ecriture-detail">&Eacute;critures</a></li>
                        <li class="<%=map.get("encaissement-vise-liste")%>"><a href="<%= lien%>?but=<%= pageActuel%>&id=<%= id%>&tab=encaissement-vise-liste">Paiement</a></li>
                    <% } %>
                </ul>
                <div class="tab-content">       
                    <jsp:include page="<%= tab%>" >
                        <jsp:param name="idmere" value="<%= id%>" />
                    </jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<%
} catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
		history.back();</script>

<% }%>