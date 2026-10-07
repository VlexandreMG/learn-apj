<%@page import="stock.MvtStock"%>
<%@page import="user.UserEJB"%>

<%
    try {
        UserEJB u = (UserEJB) session.getAttribute("u");
        String lien = (String) request.getParameter("lien");
        String[] ids = request.getParameterValues("ids");
        MvtStock mvt = new MvtStock();
        mvt.jeter(null, u.getUser().getTuppleID(), ids);
%>

<script language="JavaScript">
    document.location.replace("<%=lien%>?but=stock/dechets-jeter-multiple.jsp");
</script>

<%
    } catch (Exception e) {
        e.printStackTrace();
%>
<script type="text/javascript">
    alert("<%=e.getMessage()%>");
    history.back();
</script>
<%
    }
%>