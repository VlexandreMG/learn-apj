<%-- 
    Document   : achat-analyse-fournisseur
    Created on : 5 ao�t 2024, 14:26:09
    Author     : soama
--%>

<%@page import="utils.ConstanteAsync"%>
<%@page import="faturefournisseur.FactureFournisseurCpl"%>
<%@page import="utilitaire.*"%>
<%@page import="affichage.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="caisse.Caisse"%>
<%
try{    
    FactureFournisseurCpl mvt = new FactureFournisseurCpl();
    String nomTable = "FACTUREFOURNISSEURCPL";
    mvt.setNomTable(nomTable);
    
    String listeCrt[] = {"id","daty","idDevise","idFournisseurLib"};
    String listeInt[] = {"daty"};
    String[] pourcentage = {};
    String[] colGr = {"idFournisseurLib"};
    String[] colGrCol = {"idDevise"};
    //String somDefaut[] = {"qte", "puTotal", "puRevient"};
    String somDefaut[] = {"montantttc"};
    
    PageRechercheGroupe pr = new PageRechercheGroupe(mvt, request, listeCrt, listeInt, 3, colGr, somDefaut, pourcentage, colGr.length , somDefaut.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    String apreswhere = ""; 
    Calendar calendar = Calendar.getInstance();
    int month = calendar.get(Calendar.MONTH) + 1; // January is 0
    int year = calendar.get(Calendar.YEAR);
    Liste[] liste = new Liste[1];
        caisse.Devise dev = new caisse.Devise();
        //dev.setId("AR");
        liste[0] = new Liste("idDevise",dev,"val","id");
        liste[0].setDefaut("AR");

        pr.getFormu().changerEnChamp(liste);


    String daty1, daty2;

    if(request.getParameter("daty1") == null || request.getParameter("daty2") == null){
        daty2 = utilitaire.Utilitaire.dateDuJour();
        daty1 = String.format("01/%02d/%04d", month, year);
    }else{
        daty1=request.getParameter("daty1");
        daty2=request.getParameter("daty2");
    }

    if(request.getParameter("daty1") == null || request.getParameter("daty2") == null){
        apreswhere = " and daty <= '"+ daty2 +"' and daty >= '"+ daty1 +"'";
    }
    pr.setAWhere(apreswhere);
    pr.getFormu().getChamp("daty1").setDefaut(daty1);
    pr.getFormu().getChamp("daty2").setDefaut(daty2);
    pr.getFormu().getChamp("daty1").setLibelle("Date Min");
    pr.getFormu().getChamp("daty2").setLibelle("Date max");
    pr.getFormu().getChamp("idFournisseurLib").setLibelle("Fournisseur");
    pr.getFormu().getChamp("idDevise").setLibelle("Devise");
    pr.setNpp(500);
    pr.setApres("achat/achat-analyse-fournisseur.jsp");

    pr.creerObjetPageCroise(colGrCol,pr.getLien()+"?but=facturefournisseur/facturefournisseur-liste.jsp&daty1="+daty1+"&daty2="+daty2);
    String titre = "Analyse par fournisseur";
    String desce = "Analyse par fournisseur";
%>
<script>
    function changerDesignation() {
        document.analyse.submit();
    }
    $(document).ready(function() {
        $('.box table tr').each(function() {
            $(this).find('td:last, th:last').hide();
        });
    });
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1>Analyse d&apos;achat par fournisseur</h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=achat/achat-analyse-fournisseur.jsp" method="post" name="analyse" id="analyse">
            <%out.println(pr.getFormu().getHtmlEnsemble());%>
        </form>
<!--        <ul>
            <li>La premiere ligne correspond au quantite</li>
            <li>La 2e ligne correspond au montant TTC total</li>
        </ul>-->
           <%
            String lienTableau[] = {};
            pr.getTableau().setLien(lienTableau);
            pr.getTableau().setColonneLien(somDefaut);%>
        <br>
        <%
            out.println(pr.getHtmlWithEvaluation(ConstanteAsync.API_URL, ConstanteAsync.API_KEY, titre, desce));
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