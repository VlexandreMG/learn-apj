<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageInsert"%>
<%@ page import="user.UserEJB" %>
<%@ page import="maintenance.tranche.Tranche" %>

<% try{ 
    UserEJB u = (user.UserEJB) session.getValue("u");
    String mapping = "maintenance.tranche.Tranche";
    String nomTable = "";
    String apres = "maintenance/tranche/tranche-fiche.jsp";

    Tranche o = new Tranche();
    o.setNomTable("");
    PageInsert pi = new PageInsert(o, request, u);
    pi.setLien((String) session.getValue("lien"));  
    pi.setTitre("Saisie d'une tranche ");


    pi.getFormu().getChamp("heuredebut").setLibelle("Heure de d&eacute;but");
    pi.getFormu().getChamp("heurefin").setLibelle("Heure de fin");
    pi.getFormu().getChamp("heuredebut").setType("time");
    pi.getFormu().getChamp("heurefin").setType("time");
    pi.getFormu().getChamp("val").setLibelle("Valeur");
    pi.getFormu().getChamp("desce").setLibelle("Description");

    String[] ordre = {"heuredebut","heurefin","val","desce"};
    pi.getFormu().setOrdre(ordre);
 
    String acte = request.getParameter("acte");
    if(acte != null && acte.equalsIgnoreCase("update")){
        pi.setTitre("Modification d'une tranche ");
    }

    pi.preparerDataFormu();
    pi.getFormu().makeHtmlInsertTabIndex();
%>

<div class="content-wrapper">
    <h1><%=pi.getTitre()%></h1>
    <form action="<%=pi.getLien()%>?but=apresTarif.jsp" method="post" name="<%=nomTable%>" id="<%=nomTable%>">
        <%
            out.println(pi.getFormu().getHtmlInsert());
            out.println(pi.getHtmlAddOnPopup());
        %>
        <input name="acte" type="hidden" id="nature" value="insert">
        <input name="bute" type="hidden" id="bute" value="<%=apres%>">
        <input name="classe" type="hidden" id="classe" value="<%=mapping%>">
        <input name="nomtable" type="hidden" id="nomtable" value="<%=nomTable%>">
    </form>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

