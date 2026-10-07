<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageInsert"%>
<%@ page import="user.UserEJB" %>
<%@ page import="societe.Societe" %>

<% try{ 
    UserEJB u = (user.UserEJB) session.getValue("u");
    String mapping = "societe.Societe";
    String nomTable = "MASOCIETE";
    String apres = "societe/societe-fiche.jsp";

    Societe o = new Societe();
    o.setNomTable("MASOCIETE");
    PageInsert pi = new PageInsert(o, request, u);
    pi.setLien((String) session.getValue("lien"));  
    pi.setTitre("Modification des donn&eacute;s de la soci&eacute;t&eacute;");


    pi.getFormu().getChamp("nom").setLibelle("Nom");
    pi.getFormu().getChamp("regime").setLibelle("R&eacute;gime");
    pi.getFormu().getChamp("adresse").setLibelle("Adresse");
    pi.getFormu().getChamp("telephone").setLibelle("T&eacute;l&eacute;phone");
    pi.getFormu().getChamp("fax").setLibelle("Fax");
    pi.getFormu().getChamp("eMail").setLibelle("E-mail");
    pi.getFormu().getChamp("gerant").setLibelle("G&eacute;rant");
    pi.getFormu().getChamp("nif").setLibelle("NIF");
    pi.getFormu().getChamp("numStat").setLibelle("Num&eacute;ro statistique");
    pi.getFormu().getChamp("rc").setLibelle("Registre du commerce");
    pi.getFormu().getChamp("capital").setLibelle("Capital");
    pi.getFormu().getChamp("banque1").setLibelle("Banque 1");
    pi.getFormu().getChamp("banque2").setLibelle("Banque 2");
    pi.getFormu().getChamp("logo").setLibelle("Logo");
    pi.getFormu().getChamp("tp").setLibelle("TP");
    pi.getFormu().getChamp("quittance").setLibelle("Quittance");
    pi.getFormu().getChamp("entite").setLibelle("Entit&eacute;");
    pi.getFormu().getChamp("idSpat").setVisible(false);
 
    String[] ordre = {"nom","regime","adresse","telephone","fax","eMail","gerant","nif","numStat","rc","capital","banque1","banque2","logo","tp","quittance","entite"};
    pi.getFormu().setOrdre(ordre);
 
    String acte = request.getParameter("acte");
    if(acte != null && acte.equalsIgnoreCase("update")){
        pi.setTitre("");
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

