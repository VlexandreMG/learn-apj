<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageRecherche"%>
<%@ page import="rapprochement.ReleverDetail" %>
<%@ page import="affichage.PageRechercheChoix" %>
<%@ page import="mg.cnaps.compta.ComptaSousEcriture" %>
<%@ page import="affichage.Liste" %>
<%@ page import="bean.TypeObjet" %>
<%@ page import="rapprochement.ReleverDetailCpl" %>
<%@ page import="bean.AdminGen" %>

<% try{
    String action = "rapprochement/regler-relever-debit.jsp";
    ReleverDetailCpl o = new ReleverDetailCpl();
    o.setNomTable("RELEVERDETAILNRMVT");
    String[] listeCrt = {"compte","datyvaleur","mouvement"};
    String[] listeInt = {"datyvaleur"};
    String[] libEntete = {"id","designation","datyvaleur","debit","credit"};
    PageRechercheChoix pr = new PageRechercheChoix(o, request, listeCrt, listeInt, 4, libEntete, libEntete.length, true);
    pr.setPreciserChoix(true);
    pr.setTitre("Liste des relev&eacute;s &agrave; r&eacute;gler");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("rapprochement/relever-a-regler.jsp");
    pr.getFormu().getChamp("datyvaleur1").setLibelle("Date min");
    pr.getFormu().getChamp("datyvaleur2").setLibelle("Date max");
    pr.getFormu().getChamp("compte").setLibelle("Banque");

    Liste[] liste=new Liste[2];
    TypeObjet typeContrat = new TypeObjet();
    typeContrat.setNomTable("BANQUE_COMPTA");
    liste[0]=new Liste("compte",typeContrat,"val","desce");
    liste[0].setLibelle("Banque");
    String[] mouvementVals = {"1","2"};
    String[] mouvementLibs = {"D&eacute;bit","Cr&eacute;dit"};
    liste[1]=new Liste("mouvement",mouvementLibs,mouvementVals);
    liste[1].setDefaut("1");
    pr.getFormu().changerEnChamp(liste);

    String actionForm = action;
    String principal = "RELEVER";
    String[] ids = (String[]) session.getAttribute("ids");
    //String[] ids = new String[]{"SECR339776"};
    ComptaSousEcriture [] res = null;
    if(ids!=null && ids.length>0) {
        res = ComptaSousEcriture.getSousEcriture(ids);
        if(res.length>0){
            pr.getFormu().getChamp("compte").setDefaut(res[0].getCompte());
            double credit = AdminGen.calculSommeDouble(res,"credit");
            double debit = AdminGen.calculSommeDouble(res,"debit");

            pr.setAWhere(" AND COMPTE='"+res[0].getCompte()+"' AND DEBIT<="+credit+" AND CREDIT<="+debit+" AND DATYVALEUR>= DATE '"+res[0].getDaty()+"'");
            pr.setPremier(false);
            session.removeAttribute("ids");
        }
    }

    String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);

    String[] libEnteteAffiche = {"Id","D&eacute;signation","Date","D&eacute;bit","Cr&eacute;dit"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);

    if(pr.getFormu().getChamp("mouvement").getValeur().compareTo("2")==0){
        actionForm = "rapprochement/regler-relever-credit.jsp";
    }

%>

<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <input name="premier" type="hidden"  value="false">
        </form>
        <%
            //out.println(pr.getTableauRecap().getHtml());
        %>
        <br>
        <% if (!pr.getPremier()) {%>
        <form action="<%= pr.getLien()+"?but="+actionForm%>" method="post">
            <input type="hidden" value="<%=pr.getLien()%>" name="lien">
            <% if (ids != null) {
                for (String id : ids) { %>
            <input type="hidden" name="ids2" value="<%= id %>">
            <%  } } %>
            <input type="hidden" value="<%=principal%>" name="principal">
            <input type="hidden" value="rapprochement/async-rib.jsp" name="bute">
            <%
                pr.getTableau().setNameBoutton("Rapprocher");
                out.println(pr.getTableau().getHtmlWithCheckbox());
                out.println(pr.getBasPage());
            %>
        </form>
        <% } %>
    </section>
</div>

<% } catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
history.back();
</script>
<% }%>

