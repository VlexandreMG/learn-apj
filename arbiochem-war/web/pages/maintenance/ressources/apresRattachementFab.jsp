<%@page import="paiement.*" %>
<%@page import="user.UserEJB" %>
<%@page import="utilitaire.*" %>
<%@ page import="maintenance.configuration.ReleveFab" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<% try {
    UserEJB u = (UserEJB) session.getAttribute("u");
    String lien = (String) session.getAttribute("lien");
    String[] ids = request.getParameterValues("ids");
    String idCompteur = request.getParameter("idCompteur");
    String bute = "compteur/releve-fiche.jsp";
    if(idCompteur!= null && idCompteur.startsWith("CME")){
        bute = "compteur/releve-electricite-multiple-fiche.jsp";
    }
    ReleveFab releveFab = new ReleveFab();
    releveFab.setIdReleve(idCompteur);
    releveFab.rattacherFabrication(u.getUser().getTuppleID(),ids,null);
    String redirection = lien+"?but="+bute+"&id="+idCompteur;
%>
<script language="JavaScript"> document.location.replace("<%=redirection%>");</script>
<%  } catch (Exception e) {
    e.printStackTrace(); %>
    <script language="JavaScript">
        alert('<%=e.getMessage()%>');
        history.back();
    </script>
<% } %>

