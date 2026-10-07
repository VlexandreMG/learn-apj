<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="user.UserEJB" %>
<%@ page import="affichage.PageConsulte" %>
<%@ page import="societe.Societe" %>

<% try{ 
    UserEJB u = (user.UserEJB)session.getValue("u");
    String lien = (String) session.getValue("lien");

    Societe o = new Societe();
    o.setNomTable("MASOCIETE");
    PageConsulte pc = new PageConsulte(o, request, u);
    pc.setTitre("Fiche des donn&eacute;s de la soci&eacute;t&eacute;");
    String id = pc.getBase().getTuppleID();

    pc.getChampByName("nom").setLibelle("Nom");
    pc.getChampByName("regime").setLibelle("R&eacute;gime");
    pc.getChampByName("adresse").setLibelle("Adresse");
    pc.getChampByName("telephone").setLibelle("T&eacute;l&eacute;phone");
    pc.getChampByName("fax").setLibelle("Fax");
    pc.getChampByName("eMail").setLibelle("Email");
    pc.getChampByName("gerant").setLibelle("G&eacute;rant");
    pc.getChampByName("nif").setLibelle("NIF");
    pc.getChampByName("numStat").setLibelle("Num&eacute;ro statistique");
    pc.getChampByName("rc").setLibelle("RC");
    pc.getChampByName("capital").setLibelle("Capital");
    pc.getChampByName("banque1").setLibelle("Banque 1");
    pc.getChampByName("banque2").setLibelle("Banque 2");
    pc.getChampByName("logo").setLibelle("Logo");
    pc.getChampByName("tp").setLibelle("TP");
    pc.getChampByName("quittance").setLibelle("Quittance");
    pc.getChampByName("entite").setLibelle("Entit&eacute;");
    pc.getChampByName("idSpat").setVisible(false);

    String[] ordre = {"nom","regime","adresse","telephone","fax","eMail","gerant","nif","numStat","rc","capital","banque1","banque2","logo","tp","quittance","entite"};
    pc.setOrdre(ordre);

    String pageRetour = ".jsp";
    String pageModif = "societe/societe-saisie.jsp&acte=update";
    String pageApresDelete = ".jsp";
    String classe = "societe.Societe";

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
                        <a class="btn btn-warning pull-right"  href="<%= lien + "?but="+ pageModif +"&id=" + id %>" style="margin-right: 10px">Modifier</a>
<%--                        <a  class="pull-right" href="<%= lien + "?but=apresTarif.jsp&id=" + id+"&acte=del
+"&classe="+classe %>"><button class="btn btn-danger">Supprimer</button></a>--%>
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

