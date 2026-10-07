<%@ page import="user.UserEJB" %>
<%@ page import="bean.ClassMAPTable" %>
<%@ page import="affichage.PageInsertMultiple" %>
<%@ page import="utilitaire.Utilitaire" %>
<html>
<% try {
    String lien = (String) session.getAttribute("lien");
    UserEJB u = (UserEJB) session.getAttribute("u");
    String[] tId = request.getParameterValues("ids");
    String classe = request.getParameter("classe");
    String acte = request.getParameter("acte");
    String classeFille = request.getParameter("classeFille");
    String bute = request.getParameter("bute");
    ClassMAPTable mere = null;
    ClassMAPTable fille = null;

    String nombreDeLigne = request.getParameter("nombreLigne");
    int nbLine = Utilitaire.stringToInt(nombreDeLigne);
    if (acte != null && acte.compareToIgnoreCase("insertFilleSeul") == 0) {
        mere =  (ClassMAPTable) (Class.forName(classe).newInstance());
        fille = (ClassMAPTable) (Class.forName(classeFille).newInstance());
        PageInsertMultiple p = new PageInsertMultiple(mere, fille, request, nbLine, tId);
        ClassMAPTable[] filles = p.getObjectFilleAvecValeur();
        u.createObjectMultiple(filles);
    }
%>
<script> document.location.replace("<%=lien%>?but=<%=bute%>");</script>
<% } catch (Exception ex) {
    ex.printStackTrace(); %>
<script>alert("<%=ex.getMessage()%>"); history.back();</script>
<% return; } %>
</html>

