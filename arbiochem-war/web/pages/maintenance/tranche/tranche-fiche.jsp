<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="user.UserEJB" %>
<%@ page import="affichage.PageConsulte" %>
<%@ page import="maintenance.tranche.Tranche" %>

<% try{ 
    UserEJB u = (user.UserEJB)session.getValue("u");
    String lien = (String) session.getValue("lien");

    Tranche o = new Tranche();
    o.setNomTable("TRANCHE");
    PageConsulte pc = new PageConsulte(o, request, u);
    pc.setTitre("Fiche d'une tranche");
    String id = pc.getBase().getTuppleID();

    pc.getChampByName("id").setLibelle("Id");
    pc.getChampByName("val").setLibelle("Valeur");
    pc.getChampByName("desce").setLibelle("Description");
    pc.getChampByName("heuredebut").setLibelle("Heure de d&eacute;but");
    pc.getChampByName("heurefin").setLibelle("Heure de fin");

    String[] ordre = {"id","val","desce","heuredebut","heurefin"};
    pc.setOrdre(ordre);

    String pageRetour = "maintenance/tranche/tranche-liste.jsp";
    String pageModif = "maintenance/tranche/tranche-saisie.jsp&acte=update";
    String pageApresDelete = "maintenance/tranche/tranche-liste.jsp";
    String classe = "maintenance.tranche.Tranche";

%>

<div class="content-wrapper">

<h1 class="box-title"><a href=<%= lien + "?but=" + pageRetour%>> <i class="fa fa-angle-left"></i></a><%=pc.getTitre()%></h1>

<div class="row m-0">
    <div class="col-md-3"></div>
    <div class="col-md-12 nopadding">
        <div class="box-fiche">
            <div class="box">
                <div class="box-body">
                    <%
                        out.println(pc.getHtml());
                    %>
                    <br/>
                    <div class="box-footer">
                        <a class="btn btn-warning pull-right"  href="<%= lien + "?but="+ pageModif +"&id=" + id %>" style="margin-right: 10px">Modifier</a>
                        <a  class="pull-right" href="<%= lien + "?but=apresTarif.jsp&id=" + id+"&acte=delete&bute="+pageApresDelete+"&classe="+classe %>"><button class="btn btn-danger">Supprimer</button></a>
                    </div>
                    <br/>
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

