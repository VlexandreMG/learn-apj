<%--
  Created by IntelliJ IDEA.
  User: tokiniaina_judicael
  Date: 2025-04-01
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>

<%@page import="affichage.PageRecherche"%>
<%@ page import="fabrication.Of" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="utilitaire.Utilitaire" %>
<%@ page import="affichage.Liste" %>
<%@ page import="magasin.Magasin" %>
<% try{
    Of t = new Of();
    t.setNomTable("OFABLIB");
    String listeCrt[] = {"id", "lanceurLib", "cible", "remarque", "besoin", "daty", "libelle"};
    String listeInt[] = {"besoin","daty"};
    String libEntete[] = {"id","libelle", "daty", "besoin", "lanceurLib","cible", "remarque","etatLib"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setTitre("Liste des ordres de fabrication");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("fabrication/ordre-fabrication-liste.jsp");

    String [] val = new String[]{"%","1","11","0"};
    String [] aff = new String[]{"Tous","Cr&eacute;&eacute;(s)","Valid&eacute;(s)","Annul&eacute;(s)"};

    Liste [] listes = new Liste[1];
    Magasin cible = new Magasin();
    cible.setNomTable("MAGASINPOINT");
    listes[0]=new Liste("cible",cible,"val","val");

    pr.getFormu().changerEnChamp(listes);
    pr.getFormu().getChamp("daty1").setLibelle("Date min");
    pr.getFormu().getChamp("daty2").setLibelle("Date max");
    pr.getFormu().getChamp("besoin1").setLibelle("Date de Besoin min");
    pr.getFormu().getChamp("besoin2").setLibelle("Date de Besoin max");
    pr.getFormu().getChamp("lanceurLib").setLibelle("Lanc&eacute; par");
    pr.getFormu().getChamp("libelle").setLibelle("D&eacute;signation");
    String[] colSomme = null;
    pr.getFormu().getChamp("daty1").setDefaut(Utilitaire.dateDuJour());
    pr.getFormu().getChamp("daty2").setDefaut(Utilitaire.dateDuJour());

    if(request.getParameter("id")!=null && request.getParameter("id").compareToIgnoreCase("")!=0) {
        pr.getFormu().getChamp("id").setDefaut(request.getParameter("id"));
    }
    if(request.getParameter("etat")!=null && request.getParameter("etat").compareToIgnoreCase("")!=0) {
        if (request.getParameter("etat").equalsIgnoreCase("%")) pr.setAWhere(" ");
        else pr.setAWhere(" and etat=" + request.getParameter("etat")+" ");
    }

    pr.creerObjetPage(libEntete, colSomme);
    Map<String,String> lienTab=new HashMap();
    lienTab.put("Modifier",pr.getLien() + "?but=fabrication/ordre-fabrication-saisie.jsp&acte=update");
    pr.getTableau().setLienClicDroite(lienTab);

    //Definition des lienTableau et des colonnes de lien
    String lienTableau[] = {pr.getLien() + "?but=fabrication/ordre-fabrication-fiche.jsp"};
    String colonneLien[] = {"id"}; // Colonne contenant un lien, passé comme paramètre dans l'URL
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    //Remplacer le nom de l'attribut passé dans l'URL, par exemple passer 'idObjet' au lieu de 'id' du colonneLien
    String[] attributLien = {"id"};
    pr.getTableau().setAttLien(attributLien);

    //Definition des libelles à afficher
    String libEnteteAffiche[] = {"ID","D&eacute;signation", "Date","Date de besoin", "Lanc&eacute; par","Cible", "Remarque","&Eacute;tat"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    pr.getTableau().setLienFille("fabrication/inc/ordre-fabrication-details.jsp&id=");
    pr.getFormu().setAnotherButton("" +
    "<a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=fabrication/ordre-fabrication-saisie.jsp&currentMenu=MENUDYN00304003\">\n" +
    "                    <i class=\"material-symbols-rounded\">add</i>Saisie d'un ordre de fabrication</a>"
    );
%>
<script>
     function changerDesignation() {
        document.of.submit();
    }
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post"  name="of" id="of">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <div class="row col-md-12">
                <div class="col-md-4">
                    &Eacute;tat :
                    <select name="etat" class="champ form-control" id="etat" onchange="changerDesignation()">
                        <% for( int i = 0; i < aff.length; i++ ){ %>
                        <% if(request.getParameter("etat") !=null && request.getParameter("etat").compareToIgnoreCase(val[i]) == 0) {%>
                        <option value="<%= val[i] %>" selected> <%= aff[i] %> </option>
                        <% } else { %>
                        <option value="<%= val[i] %>"> <%= aff[i] %> </option>
                        <% } %>
                        <%    }
                        %>
                    </select>
                </div>
                <div class="col-md-4"></div>
            </div>
        </form>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        <br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    }catch(Exception e){

        e.printStackTrace();
    }
%>



