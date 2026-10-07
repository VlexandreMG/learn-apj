<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageInsertMultiple"%>
<%@ page import="user.UserEJB" %>
<%@ page import="remise.Remise" %>
<%@ page import="remise.RemiseFille" %>
<%@ page import="bean.TypeObjet" %>
<%@ page import="affichage.Champ" %>
<%@ page import="annexe.Point" %>
<%@ page import="affichage.Liste" %>

<% try{ 
    UserEJB u = (user.UserEJB) session.getValue("u");
    String classeMere = "remise.Remise";
    String classeFille = "remise.RemiseFille";
    String nomTableFille = "REMISEFILLE";
    String colonneMere = "idremise";
    String apres = "vente/remise/remise-fiche.jsp";

    Remise mere = new Remise();
    mere.setNomTable("REMISE");
    RemiseFille fille = new RemiseFille();
    fille.setNomTable("REMISEFILLE");
    int taille = 10;
    PageInsertMultiple pi = new PageInsertMultiple(mere, fille, request, taille, u);
    pi.setLien((String) session.getValue("lien"));  
    pi.setTitre("");


    pi.getFormu().getChamp("daty").setLibelle("Date");
    pi.getFormu().getChamp("nom").setLibelle("Nom");
    pi.getFormu().getChamp("datedebut").setLibelle("Date de d&eacute;but");
    pi.getFormu().getChamp("datefin").setLibelle("Date de fin");
    pi.getFormu().getChamp("etat").setVisible(false);

    Liste[] listeFille = new Liste[3];
    TypeObjet listeFille0 = new TypeObjet();
    listeFille0.setNomTable("TYPECLIENT");
    listeFille[0] = new Liste("idcategorieclient",listeFille0,"val","id");
    TypeObjet listeFille1 = new TypeObjet();
    listeFille1.setNomTable("CATEGORIEINGREDIENT");
    listeFille[1] = new Liste("categorieproduit",listeFille1,"val","id");
    Point listeFille2 = new Point();
    listeFille2.setNomTable("POINT");
    listeFille[2] = new Liste("idpoint",listeFille2,"val","id");
    pi.getFormufle().changerEnChamp(listeFille);

    pi.getFormufle().getChamp("remise_0").setLibelle("Remise");
    pi.getFormufle().getChamp("idcategorieclient_0").setLibelle("Cat&eacute;gorie client");
    pi.getFormufle().getChamp("idproduit_0").setLibelle("Produit");
    pi.getFormufle().getChamp("categorieproduit_0").setLibelle("Cat&eacute;gorie produit");
    pi.getFormufle().getChamp("idpoint_0").setLibelle("Point");
    Champ.setVisible(pi.getFormufle().getChampMulitple("idremise").getListeChamp(),false);
    Champ.setPageAppelComplete(pi.getFormufle().getChampFille("idproduit"),"produits.Ingredients","id","AS_INGREDIENTS","","");

    String[] colOrdre = {"idproduit","categorieproduit","idcategorieclient","idpoint","remise"};
    pi.getFormufle().setColOrdre(colOrdre);

    String acte = request.getParameter("acte");
    if(acte != null && acte.equalsIgnoreCase("update")){
        pi.setTitre("");
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
        <input name="nomtable" type="hidden" id="nomtable" value=<%=nomTableFille%>>
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

