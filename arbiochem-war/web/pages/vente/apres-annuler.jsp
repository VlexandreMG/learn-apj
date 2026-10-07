<%@page import="utils.ConstanteEtatStation"%>
<%@page import="bean.CGenUtil"%>
<%@page import="avoir.AvoirFC"%>
<%@page import="vente.Vente"%>
<%@page import="vente.Vente"%>
<%@page import="user.UserEJB"%>
<%@page import="utilitaire.UtilDB"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="avoir.AvoirFC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
Connection connection = null;
try{
        UserEJB u = (UserEJB)session.getAttribute("u");
        connection = new UtilDB().GetConn();
        String lien = (String)session.getAttribute("lien");
        String bute = request.getParameter("bute");
        String id = request.getParameter("id");
        Vente.annulerFacture(u.getUser().getTuppleID(), null,id);

%>
        <script language="JavaScript"> document.location.replace("<%=lien%>?but=<%=bute%>&id=<%=id%>");</script>
<%  
}catch (Exception e) {
        e.printStackTrace();
%>

    <script language="JavaScript"> alert("<%=new String(e.getMessage().getBytes(), "UTF-8")%>");
history.back();</script>
<%
        return;
} finally {
        if (connection != null) {
                try {
                        connection.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
}
%>