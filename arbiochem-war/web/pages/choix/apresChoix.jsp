<%@page import="utilitaire.Utilitaire" %>
<html>
<%
    String champRet = (String) request.getParameter("champReturn");
    String choix = (String) request.getParameter("choix");
    String[] champs = Utilitaire.split(champRet, ";");
    String[] lstChoix = Utilitaire.split(choix, ";");
%>
<script language="JavaScript">
    <%for(int i =0;i<lstChoix.length;i++){
        if(champs[i]!=null || champs[i].compareTo("")!=0){
            // corriger compte_1libelle_1 → comptelibelle_1
            champs[i] = champs[i].replaceFirst("_\\d+(?=libelle_)", ""); %>
            window.opener.document.all.<%=champs[i]%>.value = "<%=lstChoix[i]%>";
    <%}}%>
    window.close();

</script>
</html> 
