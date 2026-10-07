<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageRecherche"%>
<%@ page import="paie.avance.Avance" %>

<% try{ 
    Avance o = new Avance();
    o.setNomTable("avance_lib_visee");
    String[] listeCrt = {"id","idpersonnel","montant","matricule"};
    String[] listeInt = {"montant"};
    String[] libEntete = {"id","idpersonnel","montant","nbremboursement","idtypeavancelib","interet"};
    PageRecherche pr = new PageRecherche(o, request, listeCrt, listeInt, 4, libEntete, libEntete.length);
    pr.setTitre("Liste des avances impy&eacute;s");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("paie/avance/avance-liste-impayee.jsp");
    pr.getFormu().getChamp("id").setLibelle("Id");
    pr.getFormu().getChamp("idpersonnel").setLibelle("Nom Personnel");
    pr.getFormu().getChamp("montant1").setLibelle("Montant min");
    pr.getFormu().getChamp("montant2").setLibelle("Montant max");
    
    String[] colSomme = {"montant"};
    pr.creerObjetPage(libEntete, colSomme);
    String[] enteteRecap = {"", "Nombre", "Somme des montants"};
    pr.getTableauRecap().setLibeEntete(enteteRecap);
    String[] libEnteteAffiche = {"Id","Nom Personnel","Montant","Nombre de remboursements","Type d'avance","Int&eacute;r&ecirc;t"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);

    String lienTableau[] = {pr.getLien() + "?but=paie/avance/avance-fiche.jsp"};
    String colonneLien[] = {"id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
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
        </form>
        <%
            out.println(pr.getTableauRecap().getHtml());
        %>
        <br>
        <form action="<%=pr.getLien()%>?but=caisse/mvt/paiement-avance.jsp" method="post" name="paye" id="paye">

                <input type="hidden" name="acte">
        <%
            pr.getTableau().setNameBoutton("Payer");
            out.println(pr.getTableau().getHtmlWithCheckbox());
            out.println(pr.getBasPage());
        %>
        </form>
    </section>
</div>

<% } catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

