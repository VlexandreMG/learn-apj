<%@page import="user.UserEJB"%>
<%@ page import="paie.elementpaie.PaiePersonnelElementpaie" %>
<%@ page import="paie.avance.Avance" %>

<%
    try {
        UserEJB u = (UserEJB) session.getAttribute("u");
        String lien = (String) session.getValue("lien");

        String[] ids = request.getParameterValues("ids");
        String acte = request.getParameter("acte");

        String redirectUrl = (lien != null ? lien : "");
        if (!redirectUrl.endsWith("?")) {
            redirectUrl += (redirectUrl.contains("?") ? "" : "?");
        }

        if("valider".equalsIgnoreCase(acte)) {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    PaiePersonnelElementpaie obj = new PaiePersonnelElementpaie();
                    obj.setId(id);

                    u.validerObject(obj);
                }
            }

            redirectUrl += "but=paie/fonction/paiepersonnelelementpaie-liste.jsp";
        }
%>

<script>
    document.location.replace("<%=redirectUrl%>");
</script>

<%
    } catch(Exception e) {
        e.printStackTrace();
    }
%>