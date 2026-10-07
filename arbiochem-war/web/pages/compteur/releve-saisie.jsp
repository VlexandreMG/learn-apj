<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.ZonedDateTime"%>
<%@page import="java.time.ZoneId"%>
<%@ page import="user.*"%>
<%@ page import="utilitaire.*"%>
<%@ page import="bean.*" %>
<%@ page import="affichage.*"%>
<%@ page import="maintenance.configuration.CompteurMaintenance" %>
<%@ page import="maintenance.utils.ConstanteMaintenance" %>
<%@ page import="magasin.Magasin" %>
<%@ page import="produits.Ingredients" %>
<% try{
    UserEJB u = (UserEJB) session.getAttribute("u");
    CompteurMaintenance compteur = new CompteurMaintenance();
    PageInsert pi = new PageInsert(compteur, request, u);
    pi.setLien((String) session.getAttribute("lien"));

    Ingredients ingredientsConsommable = new Ingredients();
    ingredientsConsommable.setNomTable("AS_INGREDIENTS_CONSOMMABLE");
    Liste[] liste = new affichage.Liste[3];
    liste[0] = new Liste("idLigne",   new TypeObjet("ligne"), "val", "id");
    liste[1] = new Liste("idCategorie", ingredientsConsommable,"libelle","id");
    liste[2] = new Liste("idMagasin", new Magasin(), "val", "id");

    pi.getFormu().changerEnChamp(liste);
    pi.getFormu().getChamp("daty").setLibelle("Date");
    pi.getFormu().getChamp("idCategorie").setLibelle("Cat&eacute;gorie");
    pi.getFormu().getChamp("idLigne").setLibelle("Ligne");
    pi.getFormu().getChamp("idMagasin").setLibelle("Magasin");
     ZoneId zoneMadagascar = ZoneId.of("Africa/Nairobi");
     ZonedDateTime now = ZonedDateTime.now(zoneMadagascar);
     DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
     String heureactuel = now.format(format);
    pi.getFormu().getChamp("heure").setDefaut(heureactuel);
    pi.getFormu().getChamp("heure").setType("time");
    pi.getFormu().getChamp("ecart").setVisible(false);
    pi.getFormu().getChamp("etat").setVisible(false);
    pi.getFormu().getChamp("ancien").setAutre("readonly");
    pi.getFormu().getChamp("ancien").setLibelle("Ancienne index");
    pi.getFormu().getChamp("valeur").setLibelle("Nouvelle index");
//    pi.getFormu().getChamp("idCategorie").setAutre("onchange=\"displayIdTranche(); loadDefaultValue();\"");
    pi.getFormu().getChamp("idLigne").setAutre("onchange=\"loadDefaultValue();\"");


    String[] formOrder={"daty","idCategorie","ancien"};
    pi.getFormu().setOrdre(formOrder);
    pi.preparerDataFormu();
%>
<div class="content-wrapper">
    <h1 class="box-title">Saisie d'un relev&eacute;</h1>
    <form action="<%=pi.getLien()%>?but=apresTarif.jsp" method="post" name="appro" id="appro" >
        <%
            pi.getFormu().makeHtmlInsertTabIndex();
            out.println(pi.getFormu().getHtmlInsert());
            out.println(pi.getHtmlAddOnPopup());
        %>
        <input name="acte" type="hidden" id="acte" value="insert">
        <input name="bute" type="hidden" id="bute" value="compteur/releve-fiche.jsp">
        <input name="classe" type="hidden" id="classe" value="maintenance.configuration.CompteurMaintenance">
    </form>
</div>

<style>
    .form-input[hidden] {
        display: none !important;
    }
</style>

<script>
    function displayIdTranche() {
        const idCategorie = document.getElementById("idCategorie");
        const idTranche = document.getElementById("idTranche");
        if (!idCategorie || !idTranche) return;
        let blocTranche = idTranche.closest(".form-input");
        if (!blocTranche) return;

        const isElectricite = idCategorie.value === "<%=ConstanteMaintenance.CATEGORIE_ELECTRICITE%>";
        blocTranche.hidden = !isElectricite;
    }

    async function loadDefaultValue() {
        const idLigne     = document.getElementById("idLigne").value.trim();
        const idCategorie = document.getElementById("idCategorie").value.trim();
        if (!idLigne || !idCategorie) {
            return;
        }
        const url = "<%=request.getContextPath()%>/CompteurMaintenanceServlet?idLigne=" + encodeURIComponent(idLigne) + "&idCategorie=" + encodeURIComponent(idCategorie);
        try {
            const response = await fetch(url);
            if (!response.ok) {
                const err = await response.json();
                alert("Erreur : " + err.error);
                return;
            }
            const result = await response.json();
            document.getElementById("ancien").value = result.valeur ?? 0;
        } catch (error) {
            console.error("Erreur inattendu :", error);
        }
    }

    window.addEventListener("load", function () {
        displayIdTranche();
        loadDefaultValue();
    });
</script>
<%} catch (Exception e) {
    e.printStackTrace(); %>
    <script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();</script>
<% }%>
