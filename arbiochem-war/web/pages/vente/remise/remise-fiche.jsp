<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="user.UserEJB" %>
<%@ page import="affichage.PageConsulte" %>
<%@ page import="remise.Remise" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<% try{ 
    UserEJB u = (user.UserEJB)session.getValue("u");
    String lien = (String) session.getValue("lien");

    Remise o = new Remise();
    o.setNomTable("REMISE");
    PageConsulte pc = new PageConsulte(o, request, u);
    pc.setTitre("");
    String id = pc.getBase().getTuppleID();
    pc.getChampByName("id").setLibelle("Id");
    pc.getChampByName("daty").setLibelle("Daty");
    pc.getChampByName("nom").setLibelle("Nom");
    pc.getChampByName("datedebut").setLibelle("Date de d&eacute;but");
    pc.getChampByName("datefin").setLibelle("Date de fin");
    pc.getChampByName("etat").setLibelle("Etat");

    String[] ordre = {"id","nom","datedebut","datefin","daty","etat"};
    pc.setOrdre(ordre);

    String pageActuel = "vente/remise/remise-fiche.jsp";
    String pageRetour = ".jsp";
    String pageModif = ".jsp&acte=update";
    String pageApresDelete = ".jsp";
    String classe = "remise.Remise";

    Map<String, String> map = new HashMap<>();
    map.put("inc/remise-detail", "");
    String tab = request.getParameter("tab");
    if (tab == null) {
        tab = "inc/remise-detail";
    }
    map.put(tab, "active");
    tab = tab + ".jsp";
%>

<div class="content-wrapper">

<h1 class="box-title"><a href=<%= lien + "?but=" + pageRetour%>> <i class="fa fa-angle-left"></i></a><%=pc.getTitre()%></h1>

<div class="row m-0">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <div class="box-fiche">
            <div class="box">
                <div class="box-body">
                    <%
                        out.println(pc.getHtml());
                    %>
                    <br/>
                    <div class="box-footer">
                        <a class="btn btn-primary pull-right" href="<%= lien + "?but=apresTarif.jsp&acte=valider&id=" + id + "&bute="+pageActuel+"&classe=remise.Remise&nomtable=REMISE"%>">Valider</a>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row m-0">
    <div class="col-md-12 nopadding">
        <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
                <!-- Exemple d'onglet -->
                <li class="<%=map.get("inc/remise-detail")%>"><a href="<%= lien %>?but=<%= pageActuel %>&id=<%= id %>&tab=inc/remise-detail">D&eacute;tails</a></li>
                <li class="<%=map.get("inc/vente-associer-details")%>"><a href="<%= lien %>?but=<%= pageActuel %>&id=<%= id %>&tab=inc/vente-associer-details">Ventes associ&eacute;es</a></li>

            </ul>
            <div class="tab-content">
                    <jsp:include page="<%= tab %>" >
                        <jsp:param name="id" value="<%= id %>" />
                    </jsp:include>
            </div>
        </div>
        
        </div>
    </div>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

