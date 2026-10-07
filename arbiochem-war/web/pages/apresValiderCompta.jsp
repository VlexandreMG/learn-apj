
<%@page import="user.UserEJB"%>
<%@page import="utilitaire.UtilDB"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="mg.cnaps.compta.ComptaEcriture" %>
<%@ page import="utilitaire.ConstanteEtat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Connection connection = null;
    try{
        UserEJB u = (UserEJB)session.getAttribute("u");
        connection = new UtilDB().GetConn();
        String lien = (String)session.getAttribute("lien");
        String bute = request.getParameter("bute");
        String[] ids = request.getParameterValues("ids");

        for (String idFor : ids) {
            System.out.println("idFor = " + idFor);
            ComptaEcriture compta = (ComptaEcriture) new ComptaEcriture().getById(idFor, null, connection);
            if (compta.getEtat() != ConstanteEtat.getEtatValider()) {
                compta.validerObject(String.valueOf(u.getUser().getRefuser()), connection);
            }
        }
%>
<script language="JavaScript"> document.location.replace("<%=lien%>?but=<%=bute%>");</script>
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