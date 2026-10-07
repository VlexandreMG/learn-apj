<%--
    Document   : as-commande-analyse
    Created on : 30 d�c. 2016, 04:57:15
    Author     : Joe
--%>
<%@page import="vente.VenteDetailsLib"%>
<%@page import="utilitaire.*"%>
<%@page import="affichage.*"%>
<%@page import="java.util.Calendar"%>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="annexe.Point" %>
<%@ page import="bean.TypeObjet" %>
<%@ page import="magasin.Magasin" %>

<%
    try{
        VenteDetailsLib mvt = new VenteDetailsLib();
        String nomTable = "VENTE_CPL_ANALYSEF";
        mvt.setNomTable(nomTable);

        String listeCrt[] = {"daty","idMagasinLib","idCategorieLib"};
        String listeInt[] = {"daty"};
        String[] pourcentage = {};
        String[] colGr = {"idMagasinLib"};
        String[] colGrCol = {"idCategorieLib"};
        //String somDefaut[] = {"qte", "puTotal", "puRevient"};
        String somDefaut[] = {"qte", "puTotal"};

        PageRechercheGroupe pr = new PageRechercheGroupe(mvt, request, listeCrt, listeInt, 3, colGr, somDefaut, pourcentage, colGr.length , somDefaut.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        String apreswhere = "";
        String debutSem=Utilitaire.formatterDaty(Utilitaire.getDebutSemaine(Utilitaire.dateDuJourSql())) ;
        if(request.getParameter("daty1")==null&&request.getParameter("daty2")==null)
            apreswhere= "and daty >= TO_DATE('"+debutSem+"','DD/MM/YYYY') and daty <= TO_DATE('"+utilitaire.Utilitaire.dateDuJour()+"','DD/MM/YYYY')";
        Calendar calendar = Calendar.getInstance();

        String order = "";
        if(request.getParameter("order")!=null && request.getParameter("order").compareToIgnoreCase("")!=0){
            order+= (" "+ request.getParameter("order"));
        }

        String[] grouper = new String[1];
        String[] ligneGroupe = new String[1];
        String titre="Analyse des ventes";
        String aprs="";
        if(request.getParameter("grouper")!=null && request.getParameter("grouper").compareToIgnoreCase("")!=0){
            grouper[0]=request.getParameter("grouper");
            ligneGroupe[0]="daty";
            pr.setColGroupeDefaut(grouper);
            pr.setLigneGroupe(ligneGroupe);
            if(request.getParameter("grouper").equalsIgnoreCase("idCategorieLib")){
                titre="Analyse des ventes par cat&eacute;gorie";
                aprs="idCategorieLib";
            }
            else if(request.getParameter("grouper").equalsIgnoreCase("idMagasinLib")){
                titre="Analyse des ventes par Magasin";
                aprs="idMagasinLib";
            }
        }
        pr.setOrdre(order);
        pr.setAWhere(apreswhere);
        Liste[] liste = new Liste[2];
        Magasin point = new Magasin();
        liste[0] = new Liste("idMagasinLib",point,"val","val");
        TypeObjet categorie=new TypeObjet();
        categorie.setNomTable("CATEGORIEINGREDIENT");
        liste[1] = new Liste("idCategorieLib",categorie,"val","val");

        pr.getFormu().changerEnChamp(liste);
        pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.datetostring(Date.valueOf(LocalDate.now().minusDays(7))));
        pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());
        pr.getFormu().getChamp("daty1").setLibelle("Date Min");
        pr.getFormu().getChamp("daty2").setLibelle("Date max");
        pr.getFormu().getChamp("idMagasinLib").setLibelle("Magasin");
        pr.getFormu().getChamp("idCategorieLib").setLibelle("Cat&eacute;gorie");
        pr.setNpp(500);
        pr.setApres("vente/analyse/vente-analyse.jsp");
        System.out.println("apres = "+pr.getApres());
        pr.creerObjetPageCroise(colGrCol,pr.getLien()+"?but=");
%>
<script>
    function changerDesignation() {
        document.analyse.submit();
    }
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%=titre%></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=vente/analyse/vente-analyse.jsp" method="post" name="analyse" id="analyse">
            <%out.println(pr.getFormu().getHtmlEnsemble());%>
            <%
                if(request.getParameter("grouper")!=null && request.getParameter("grouper").compareToIgnoreCase("")!=0){
            %>
            <input type="hidden" name="grouper" value="<%=aprs%>"/>
            <% }
            %>
        </form>
        <ul>
            <li>La premi&egrave;re ligne correspond &agrave; la quantit&eacute;</li>
            <li>La 2&egrave;me ligne correspond au montant total</li>
        </ul>
        <%
            String lienTableau[] = {};
            pr.getTableau().setLien(lienTableau);
            pr.getTableau().setColonneLien(somDefaut);%>
        <br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
%>
        <script language="JavaScript">
            alert('<%=e.getMessage() != null ? e.getMessage().replace("'", "\\'").replace("", "").replace("
", " ") : "Erreur durant la recherche groupée."%>');
            history.back();
        </script>
<%
    }
%>
