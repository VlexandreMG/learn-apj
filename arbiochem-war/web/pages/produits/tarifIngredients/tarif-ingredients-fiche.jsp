<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="user.UserEJB" %>
<%@ page import="affichage.PageConsulte" %>
<%@ page import="produits.TarifIngredientsLib" %>

<% try{ 
    UserEJB u = (user.UserEJB)session.getValue("u");
    String lien = (String) session.getValue("lien");

    TarifIngredientsLib o = new TarifIngredientsLib();
    o.setNomTable("TARIF_INGREDIENTS_LIB");
    PageConsulte pc = new PageConsulte(o, request, u);
    pc.setTitre("Fiche de tarif ingredient");
    String id = pc.getBase().getTuppleID();

    pc.getChampByName("idtypeclientlib").setLibelle("Type de client");
    pc.getChampByName("idingredientlib").setLibelle("Ingr&eacute;dient");
    pc.getChampByName("unitelib").setLibelle("Unite");
    pc.getChampByName("id").setLibelle("ID");
    pc.getChampByName("idTypeClient").setLibelle("ID Type Client");
    pc.getChampByName("idIngredient").setLibelle("ID Ingr&eacute;dient");
    pc.getChampByName("idIngredient").setLien(lien + "?but=produits/as-ingredients-fiche.jsp", "id=");
    pc.getChampByName("daty").setLibelle("Daty");
    pc.getChampByName("unite").setLibelle("Unite");
    pc.getChampByName("prixUnitaire").setLibelle("Prix Unitaire");

    String[] ordre = {"id","idtypeclientlib", "idTypeClient","idingredientlib", "idIngredient","unitelib","daty","prixUnitaire"};
    pc.setOrdre(ordre);

    String pageRetour = ".jsp";
    String pageModif = "produits/tarifIngredients/tarifIngredients-saisie.jsp&acte=update";
    String pageApresDelete = ".jsp";
    String classe = "produits.TarifIngredientsLib";

%>

<div class="content-wrapper">

<h1 class="box-title"><a href=<%= lien + "?but=" + pageRetour%>> <i class="fa fa-angle-left"></i></a><%=pc.getTitre()%></h1>

<div class="row m-0">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <div class="box-fiche">
            <div class="box">
                <div class="box-body">
                    <%
                        out.println(pc.getHtml());
                    %>
                    <br/>
                    <div class="box-footer">
                        <a class="btn btn-warning pull-right"  href="<%= lien + "?but="+ pageModif +"&id=" + id %>" style="margin-right: 10px">Modifier</a>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

