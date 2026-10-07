<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageRecherche"%>
<%@ page import="mg.cnaps.compta.ComptaSousEcritureLib" %>

<% try{ 
    ComptaSousEcritureLib o = new ComptaSousEcritureLib();
    o.setNomTable("COMPTASOUSECRITURETVA");
    if(request.getParameter("tva")!=null && request.getParameter("tva").compareToIgnoreCase("")!=0) {
        o.setNomTable(request.getParameter("tva"));
    }
    String[] listeCrt = {"id","numero","declaration","journal","compte","libellePiece","debit","credit","daty"};
    String[] listeInt = {"daty"};
    String[] libEntete = {"id","numero","journal","compte","daty","libellePiece","debit","credit","declaration"};
    PageRecherche pr = new PageRecherche(o, request, listeCrt, listeInt, 4, libEntete, libEntete.length);
    pr.setTitre("Liste des &eacute;critures non declar&eacute;");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));

    pr.setAWhere(" AND daty < TRUNC(ADD_MONTHS(SYSDATE, -2), 'MM') ");

    pr.setApres("declaration/liste-tva-adeclarer.jsp");
    pr.getFormu().getChamp("id").setLibelle("Id");
    pr.getFormu().getChamp("numero").setLibelle("Intitul&eacute;");
    pr.getFormu().getChamp("declaration").setLibelle("Mois de d&eacute;claration");
    pr.getFormu().getChamp("journal").setLibelle("Journal");
    pr.getFormu().getChamp("compte").setLibelle("Compte");
    pr.getFormu().getChamp("libellePiece").setLibelle("Libell&eacute;");
    pr.getFormu().getChamp("debit").setLibelle("D&eacute;bit");
    pr.getFormu().getChamp("credit").setLibelle("Cr&eacute;dit");
    pr.getFormu().getChamp("daty1").setLibelle("Date min");
    pr.getFormu().getChamp("daty2").setLibelle("Date max");
    
    String[] colSomme = {"debit","credit"};
    pr.creerObjetPage(libEntete, colSomme);
    String[] enteteRecap = {"","Nombres","Somme des debit","Somme des crédits"};
    pr.getTableauRecap().setLibeEntete(enteteRecap);

    String[] libEnteteAffiche = {"Id","Intitul&eacute;","Journal","Compte","Date","Libell&eacute;","D&eacute;bit","Cr&eacute;dit","Mois de d&eacute;claration"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);

    String lienTableau[] = {pr.getLien() + "?but=compta/ecriture/sousecriture-fiche.jsp"};
    String colonneLien[] = {"id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);

    String [] val = new String[]{"","COMPTASOUSECRITURETVA_D","COMPTASOUSECRITURETVA_C"};
    String [] aff = new String[]{"Tous","D&eacute;ductible","Coll&eacute;ct&eacute;"};
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
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name="of" id="of">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <div class="row col-md-12">
                <div class="col-md-4">
                    TVA :
                    <select name="tva" class="champ form-control" id="tva" onchange="changerDesignation()">
                        <% for( int i = 0; i < aff.length; i++ ){ %>
                        <% if(request.getParameter("tva") !=null && request.getParameter("tva").compareToIgnoreCase(val[i]) == 0) {%>
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
            out.println(pr.getTableauRecap().getHtml());
        %>
        <br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

