<%@page import="paiement.*" %>
<%@page import="user.UserEJB" %>
<%@page import="utilitaire.*" %>
<%@ page import="maintenance.configuration.ReleveFab" %>
<%@ page import="faturefournisseur.DmdAchat" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<% try {
    UserEJB u = (UserEJB) session.getAttribute("u");
    String lien = (String) session.getAttribute("lien");
    String[] ids = request.getParameterValues("ids");
    String bute = request.getParameter("bute");
    DmdAchat dmdAchat = new DmdAchat();
    dmdAchat = dmdAchat.fusionners(ids, null);
    String redirection = lien+"?but="+bute+"&id="+dmdAchat.getId();
%>
<script language="JavaScript"> document.location.replace("<%=redirection%>");</script>
<%  } catch (Exception e) {
    e.printStackTrace(); %>
<script language="JavaScript">
    alert('<%=e.getMessage()%>');
    history.back();
</script>
<% } %>

