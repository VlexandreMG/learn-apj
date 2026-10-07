
<%@page import="faturefournisseur.Fournisseur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<%
    UserEJB u = (user.UserEJB)session.getValue("u");

%>
<%
    Fournisseur caisse = new Fournisseur();
    PageConsulte pc = new PageConsulte(caisse, request, u);
    pc.setTitre("Fiche fournisseur");
    pc.getBase();
    String id=pc.getBase().getTuppleID();
    pc.getChampByName("codePostal").setLibelle("code postal");
    pc.getChampByName("compte").setLibelle("Compte G&eacute;n&eacute;ral");
    pc.getChampByName("compteauxiliaire").setLibelle("Compte Auxiliaire");
    pc.getChampByName("echeance").setLibelle("Ech&eacute;ance  de paiement");
    pc.getChampByName("idTypeFournisseur").setLibelle("Type fournisseur");
    pc.getChampByName("estActif").setLibelle("Actif");
    String lien = (String) session.getValue("lien");
    String pageModif = "fournisseur/fournisseur-saisie.jsp";
    String classe = "faturefournisseur.Fournisseur";
    String pageActuel = "fournisseur/fournisseur-fiche.jsp";
        Map<String, String> map = new HashMap<String, String>();
    map.put("inc/fournisseur-bc", "");
    map.put("inc/fournisseur-facture", "");

    String tab = request.getParameter("tab");
    if (tab == null) {
        tab = "inc/fournisseur-bc";
    }
    map.put(tab, "active");
    tab = tab + ".jsp";
%>

<div class="content-wrapper">
    <h1 class="box-title"><a href=<%= lien + "?but=fournisseur/fournisseur-liste.jsp"%>> <i class="fa fa-angle-left"></i></a><%=pc.getTitre()%></h1>
    <div class="row m-0">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="box-fiche">
                <div class="box">
                    <div class="box-body">
                        <%
                            out.println(pc.getHtml());
                        %>
                        <div class="box-footer">
                            <a class="btn btn-danger pull-left" href="<%= lien + "?but=apresTarif.jsp&id=" + id+"&acte=delete&bute=fournisseur/fournisseur-fiche.jsp&classe="+classe %>">
                                Supprimer
                            </a>
                                 <a class="btn btn-secondary pull-right"  href="<%= lien + "?but="+ pageModif +"&id=" + id+"&acte=update"%>" style="margin-right: 10px">Modifier</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <div class="row m-0">
        <div class="col-md-12 nopadding">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <!-- a modifier -->
                    <li class="<%=map.get("inc/fournisseur-bc")%>"><a href="<%= lien %>?but=<%= pageActuel %>&id=<%= id %>&tab=inc/fournisseur-bc">Liste des Bon de Commandes</a></li>
                    <li class="<%=map.get("inc/fournisseur-facture")%>"><a href="<%= lien %>?but=<%= pageActuel %>&id=<%= id %>&tab=inc/fournisseur-facture">Liste des Factures</a></li>
<%--                    <li class="<%=map.get("inc/releve-fournisseur")%>"><a href="<%= lien %>?but=<%= pageActuel %>&id=<%= id %>&tab=inc/releve-fournisseur">Relev&eacute; fournisseur</a></li>--%>
<%--                    <li class="<%=map.get("inc/tarif-ingredients-liste")%>"><a href="<%= lien %>?but=<%= pageActuel %>&id=<%= id %>&tab=inc/tarif-ingredients-liste">Tarifs</a></li>--%>
                </ul>
                <div class="tab-content">
                    <jsp:include page="<%= tab %>" >
                        <jsp:param name="numbl" value="<%= id %>" />
                    </jsp:include>
                </div>
            </div>

        </div>
    </div>
</div>

