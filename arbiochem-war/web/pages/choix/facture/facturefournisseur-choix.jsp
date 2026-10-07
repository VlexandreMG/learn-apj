

<%@page import="faturefournisseur.Fournisseur"%>
<%@page import="affichage.PageRechercheChoix"%>
<%@page import="affichage.Liste"%>
<%@ page import="faturefournisseur.FactureFournisseurCpl" %>


<%
    String champReturn = request.getParameter("champReturn");
    FactureFournisseurCpl choix = new FactureFournisseurCpl();
    String listeCrt[] = {"id", "designation","idFournisseurLib","daty"};
    String listeInt[] = {"daty"};
    String libEntete[] = {"id", "designation","idFournisseurLib","daty","montantttc","montantpaye", "montantreste","idDevise"};
    int range = 2;
    PageRechercheChoix pr = new PageRechercheChoix(choix, request, listeCrt, listeInt, range, libEntete, libEntete.length);
     pr.setAWhere(
        " AND etat >= 11 " +
        " AND NOT EXISTS ( " +
        "     SELECT 1 FROM LIAISONFACTUREFOURNISSEURS l " +
        "     WHERE l.id1 = FACTUREFOURNISSEURCPL.id " +
        " )"
    );
    
    pr.setTitre("FACTURE FOURNISSEUR");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("facturefournisseur-choix.jsp");
    pr.getFormu().getChamp("designation").setLibelle("D&eacute;signation");
    pr.getFormu().getChamp("idFournisseurLib").setLibelle("Fournisseur");
    pr.getFormu().getChamp("daty1").setLibelle("Date min");
    pr.getFormu().getChamp("daty2").setLibelle("Date max");
    pr.setChampReturn(champReturn);

    String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);
    String libEnteteAffiche[] = {"id", "D&eacute;signation","Fournisseur","Date","Montant TTC","Montant pay&eacute;","Montant restant","devise"};
    pr.getTableau().setLibeEntete(libEnteteAffiche);
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= pr.getTitre()%></title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page='./../../elements/css.jsp'/>
</head>
<body class="skin-blue sidebar-mini">
<div class="wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre()%></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getApres()%>?champReturn=<%=champReturn%>" method="post" name="fcdetailsliste" id="fcdetailsliste">
            <% out.println(pr.getFormu().getHtmlEnsemble());%>
        </form>
        <form action="./../apresChoix.jsp" method="post" name="frmchx" id="frmchx">
            <input type="hidden" name="champReturn" value="<%=pr.getChampReturn()%>">
            <%
         if(pr.getTableau().getHtmlWithRadioButton() != null){
            out.println(pr.getTableau().getHtmlWithRadioButton());
         }else
         {
               %><div style="text-align: center;"><h4>Aucune donn&eacute;e disponible</h4></div><%
         }
            %>
        </form>
        <% out.println(pr.getBasPage());%>
    </section>
</div>
<jsp:include page='./../../elements/js.jsp'/>
</body>
</html>