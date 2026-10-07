<%-- 
    Document   : avance-saisie
    Created on : 20 Sep. 2022, 15:44:38
    Author     : Sambatra Rakotondrainibe
--%>
<%@page import="affichage.PageInsert"%> 
<%@page import="user.UserEJB"%>
<%@page import="utilitaire.Utilitaire"%>
<%@page import="affichage.Liste"%> 
<%@page import="bean.TypeObjet"%>
<%@ page import="paie.avance.Avance" %>
<%@ page import="paie.employe.ConstantePaie" %>
<%
    try{
    String lien = (String) session.getValue("lien");
    UserEJB u = (user.UserEJB) session.getValue("u");
    Avance av= new Avance();
    av.setNomTable("avance");
    PageInsert pi = new PageInsert(av, request, u);
    pi.setLien(lien);


    String apres = "paie/avance/avance-fiche.jsp";
//    Liste[] listes = new Liste[1];
//    TypeObjet tp = new TypeObjet();
//    tp.setNomTable("typeavance");
//    listes[0] = new Liste("idtypeavance", tp, "val", "id");
//    listes[0].setDefaut(ConstantePaie.idAvanceExceptionnelle);
//    pi.getFormu().changerEnChamp(listes);

        Liste[] listes = new Liste[1];
        TypeObjet tp = new TypeObjet();
        tp.setNomTable("CATEGORIE_PAIE");
        listes[0] = new Liste("idCategorie", tp, "val", "id");
        pi.getFormu().changerEnChamp(listes);

        String idCategorie = request.getParameter("idCategorie");
        pi.getFormu().getChamp("idCategorie").setLibelle("Cat&eacute;gorie");
        String aWhere = "";

        if (idCategorie != null && !idCategorie.isEmpty()) {
            aWhere = " AND idcategorie_paie = '" + idCategorie + "'";
        } else {
            idCategorie = ConstantePaie.CATEGORIE_CADRE;
            aWhere = " AND idcategorie_paie = '" + idCategorie + "'";
        }

        pi.getFormu().getChamp("idCategorie").setDefaut(idCategorie);


        pi.setTitre("  Saisie d’une avance sp&eacute;ciale");
        pi.getFormu().getChamp("daty").setVisible(false);
        pi.getFormu().getChamp("etat").setVisible(false);
        // pi.getFormu().getChamp("idpersonnel").setPageAppel("choix/logPersonnelChoix.jsp");
        //affichage.Champ.setPageAppelComplete(pi.getFormu().getChampMulitple("idpersonnel").getListeChamp(),"personnel.Personnel", "id", "personnel", "nom","id");
        pi.getFormu().getChamp("idpersonnel").setPageAppelCompleteAWhere("paie.log.LogPersonnel","id","LOG_PERSONNEL_V2", "", "", aWhere);

        pi.getFormu().getChamp("dateAvance").setDefaut(Utilitaire.dateDuJour());
        pi.getFormu().getChamp("dateSaisie").setDefaut(Utilitaire.dateDuJour());

        pi.getFormu().getChamp("idpersonnel").setLibelle("Personnel");
        pi.getFormu().getChamp("daty").setLibelle("Date");
        pi.getFormu().getChamp("dateAvance").setLibelle("Date de saisie de l'avance ");
        pi.getFormu().getChamp("dateDeblocage").setLibelle("Date de d&eacute;blocage ");
        pi.getFormu().getChamp("nbremboursement").setLibelle("Nombre de remboursement");
        pi.getFormu().getChamp("nbremboursement").setDefaut("1");
        pi.getFormu().getChamp("idtypeavance").setLibelle("Type");
        pi.getFormu().getChamp("idtypeavance").setDefaut(utils.ConstantePaie.idAvanceExceptionnelle);
        pi.getFormu().getChamp("idtypeavance").setVisible(false);
        pi.getFormu().getChamp("montant").setLibelle("Montant(Ar)");
        pi.getFormu().getChamp("interet").setLibelle("Int&eacute;r&ecirc;t (%)");
        pi.getFormu().getChamp("interet").setDefaut("1");
        pi.getFormu().getChamp("dateDebutRemboursement").setLibelle("Date de d&eacute;but de remboursement");


        pi.getFormu().getChamp("dateAvance").setDefaut(Utilitaire.dateDuJour());
        pi.getFormu().getChamp("dateAvance").setVisible(false);
        pi.getFormu().getChamp("mois").setVisible(false);
//        System.out.println(" date du jour " + Utilitaire.dateDuJour());
//
//        System.out.println(" moissss " + Utilitaire.getMois(Utilitaire.dateDuJour()));
        pi.getFormu().getChamp("mois").setDefaut(Utilitaire.getMois(Utilitaire.dateDuJour()));

        pi.getFormu().getChamp("remarque").setVisible(false);
        pi.getFormu().getChamp("dateSaisie").setVisible(false);
    String classe = "paie.avance.Avance";
    
    String[] ordre = {"idCategorie", "dateDeblocage", "dateDebutRemboursement","idtypeavance","idpersonnel","nbremboursement","daty","etat","remarque"};
    pi.getFormu().setOrdre(ordre);
    
    pi.preparerDataFormu();
    pi.getFormu().makeHtmlInsertTabIndex();
%>
<script>

</script>
<div class="content-wrapper">
    <h1> <%=pi.getTitre()%></h1>
    <form action="<%=pi.getLien()%>?but=apresTarif.jsp" method="post" name="<%=av.getNomTable()%>" id="<%=av.getNomTable()%>">
        <%
            out.println(pi.getFormu().getHtmlInsert());
        %>
        <input name="acte" type="hidden" id="nature" value="insert">
        <input name="daty" type="hidden" id="daty" value="<%=Utilitaire.dateDuJour()%>">
        <input name="bute" type="hidden" id="bute" value="<%=apres%>">
        <input name="classe" type="hidden" id="classe" value="<%=classe%>">
        <input name="nomtable" type="hidden" id="nomtable" value="<%=av.getNomTable()%>">


    </form>
</div>
<%
} catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();</script>

<% }%>

<script>
    document.addEventListener("DOMContentLoaded", function () {

        const categorieSelect = document.querySelector("[name='idCategorie']");
        if (!categorieSelect) return;

        categorieSelect.addEventListener("change", function () {
            const idCategorie = this.value;

            console.log("Selected idCategorie:", idCategorie);

            window.location.href =
                "module.jsp?but=paie/avance/avance-saisie.jsp&idCategorie=" + encodeURIComponent(idCategorie);
        });

    });
</script>

<%--<script>--%>
<%--    document.addEventListener("DOMContentLoaded", function () {--%>

<%--        console.log("JS loaded");--%>

<%--        const categorieSelect = document.querySelector("[name='idCategorie']");--%>

<%--        if (!categorieSelect) {--%>
<%--            console.error("idCategorie field not found");--%>
<%--            return;--%>
<%--        }--%>

<%--        categorieSelect.addEventListener("change", function () {--%>
<%--            const idCategorie = this.value;--%>

<%--            console.log("Selected idCategorie:", idCategorie);--%>

<%--            const xhr = new XMLHttpRequest();--%>
<%--            xhr.open("POST", "yourServletOrJsp.jsp", true);--%>
<%--            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");--%>

<%--            xhr.onreadystatechange = function () {--%>
<%--                if (xhr.readyState === 4 && xhr.status === 200) {--%>
<%--                    console.log("Response:", xhr.responseText);--%>
<%--                }--%>
<%--            };--%>

<%--            xhr.send("idCategorie=" + encodeURIComponent(idCategorie));--%>
<%--        });--%>

<%--        // 🔥 THIS LINE FIXES YOUR ISSUE--%>
<%--        categorieSelect.dispatchEvent(new Event("change"));--%>

<%--        categorieSelect.addEventListener("change", function () {--%>
<%--            const idCategorie = this.value;--%>

<%--            window.location.href =--%>
<%--                "avance-saisie.jsp?idCategorie=" + encodeURIComponent(idCategorie);--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
