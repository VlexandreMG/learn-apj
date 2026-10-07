<%@page import="stock.MvtStock"%>
<%@page import="utilitaire.UtilDB"%>
<%@page import="user.UserEJB"%>

<%
try {
    String id = request.getParameter("id");
    String lien = (String) session.getValue("lien");
    UserEJB u = (UserEJB)session.getAttribute("u");
    if (id != null && !id.trim().isEmpty()) {
        MvtStock mvt = (MvtStock) new MvtStock().getById(id, "MVTSTOCK", null);
        if (mvt != null) {
            mvt.jeter(null,u.getUser().getTuppleID());
        }
    }
%>

<script language="JavaScript">
    document.location.replace("<%= lien %>?but=stock/mvtstock-fiche.jsp&id=<%= id %>");
</script>

<%
}catch (Exception e) {
  e.printStackTrace();
%>

<script language="JavaScript">
  alert('<%=e.getMessage()%>');
  history.back();
</script>
<%

  }
%>