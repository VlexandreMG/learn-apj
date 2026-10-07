<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageInsert"%>
<%@ page import="user.UserEJB" %>
<%@ page import="produits.TarifIngredients" %>
<%@ page import="affichage.Liste" %>

<% try{ 
    UserEJB u = (user.UserEJB) session.getValue("u");
    String mapping = "produits.TarifIngredients";
    String nomTable = "TARIF_INGREDIENTS";
    String apres = "vente/tarifIngredients/tarifIngredients-fiche.jsp";

    TarifIngredients o = new TarifIngredients();
    o.setNomTable("TARIF_INGREDIENTS");
    PageInsert pi = new PageInsert(o, request, u);
    pi.setLien((String) session.getValue("lien"));  
    pi.setTitre("");
    Liste[] listeDeroulante=new Liste[2];
    listeDeroulante[0]=new Liste("unite",new bean.TypeObjet("AS_UNITE"),"val","id");
    listeDeroulante[1]=new Liste("idMagasin",new bean.TypeObjet("MAGASIN2"),"val","id");
    pi.getFormu().changerEnChamp(listeDeroulante);
    pi.getFormu().getChamp("idTypeClient").setLibelle("Type client");
    pi.getFormu().getChamp("idIngredient").setLibelle("Produit");
    pi.getFormu().getChamp("daty").setLibelle("Date");
    pi.getFormu().getChamp("unite").setLibelle("Unit&eacute;");
    pi.getFormu().getChamp("prixUnitaire").setLibelle("Prix unitaire");
    pi.getFormu().getChamp("idMagasin").setLibelle("Magasin");
    pi.getFormu().getChamp("idTypeClient").setPageAppelComplete("bean.TypeObjet","id","TYPECLIENT","","");
    pi.getFormu().getChamp("idIngredient").setPageAppelComplete("produits.Ingredients","id","as_ingredients","","");

    String[] ordre = {"idTypeClient","idIngredient","daty","unite","prixUnitaire","idMagasin"};
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

