<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageInsertMultiple"%>
<%@ page import="user.UserEJB" %>
<%@ page import="maintenance.travaux.Travaux" %>
<%@ page import="maintenance.travaux.MoTravaux" %>
<%@ page import="affichage.Champ" %>

<% try{ 
    UserEJB u = (user.UserEJB) session.getValue("u");
    String classeMere = "maintenance.travaux.Travaux";
    String classeFille = "maintenance.travaux.MoTravaux";
    String colonneMere = "idTravaux";
    String apres = "maintenance/travaux/Tableaux-saisie-multiple.jsp";

    Travaux mere = new Travaux();
    MoTravaux fille = new MoTravaux();
    int taille = 10;
    PageInsertMultiple pi = new PageInsertMultiple(mere, fille, request, taille, u);
    pi.setLien((String) session.getValue("lien"));  
    pi.setTitre("Saisie de Travaux");


    pi.getFormu().getChamp("remarque").setLibelle("Description");
    pi.getFormu().getChamp("libelle").setLibelle("D&eacute;signation");
    pi.getFormu().getChamp("daty").setLibelle("Date");
    pi.getFormu().getChamp("idOf").setVisible(false);
    pi.getFormu().getChamp("idOffille").setVisible(false);
    pi.getFormu().getChamp("fabricationPrec").setVisible(false);
    pi.getFormu().getChamp("fabricationSuiv").setVisible(false);
    pi.getFormu().getChamp("equipe").setVisible(false);
    pi.getFormu().getChamp("dureeEstimatif").setVisible(false);
    pi.getFormu().getChamp("lancePar").setVisible(false);
    pi.getFormu().getChamp("cible").setVisible(false);
    pi.getFormu().getChamp("idBc").setVisible(false);
    pi.getFormu().getChamp("besoin").setVisible(false);
    pi.getFormu().getChamp("etat").setVisible(false);


    pi.getFormufle().getChamp("idPersonnel_0").setLibelle("Personnel");
    pi.getFormufle().getChamp("dureeEstimatif_0").setLibelle("Duree &eacute;stimatif");
    pi.getFormufle().getChamp("tauxHoraire_0").setLibelle("Taux horaire");
    Champ.setVisible(pi.getFormufle().getChampMulitple("idTravaux").getListeChamp(),false);
    Champ.setPageAppelComplete(pi.getFormufle().getChampFille("idPersonnel"),"personnel.Personnel","id","PERSONNEL","","");

    String[] colOrdre = {"idPersonnel","dureeEstimatif","tauxHoraire"};
    pi.getFormufle().setColOrdre(colOrdre);

    String acte = request.getParameter("acte");
    if(acte != null && acte.equalsIgnoreCase("update")){
        pi.setTitre("Modification de travaux");
    }

    pi.preparerDataFormu();
    pi.getFormu().makeHtmlInsertTabIndex();
    pi.getFormufle().makeHtmlInsertTableauIndex();
%>

<div class="content-wrapper">
    <h1><%=pi.getTitre()%></h1>
    <form id="formId" class='container' action="<%=pi.getLien()%>?but=apresMultiple.jsp" method="post" >
        <%
            out.println(pi.getFormu().getHtmlInsert());
            out.println(pi.getFormufle().getHtmlTableauInsert());
        %>
        <input name="acte" type="hidden" id="nature" value="insert">
        <input name="bute" type="hidden" id="bute" value="<%=apres%>">
        <input name="classe" type="hidden" id="classe" value="<%=classeMere%>">
        <input name="classefille" type="hidden" id="classefille" value="<%=classeFille%>">
        <input name="nombreLigne" type="hidden" id="nombreLigne" value="<%=taille%>">
        <input name="colonneMere" type="hidden" id="colonneMere" value="<%=colonneMere%>">
    </form>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

