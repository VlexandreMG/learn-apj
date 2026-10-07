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
    CompteurMaintenance mere=new CompteurMaintenance();
    CompteurMaintenance compteur = new CompteurMaintenance();
    int nombreLigne = 10;
    PageInsertMultiple pi = new PageInsertMultiple(mere,compteur, request,nombreLigne, u);
    pi.setLien((String) session.getAttribute("lien"));

    Ingredients ingredientsConsommable = new Ingredients();
    ingredientsConsommable.setNomTable("AS_INGREDIENTS_CONSOMMABLE");
    Liste[] liste = new affichage.Liste[3];
    liste[0] = new Liste("idLigne",   new TypeObjet("ligne"), "val", "id");
    liste[1] = new Liste("idCategorie", ingredientsConsommable,"libelle","id");
    liste[2] = new Liste("idMagasin", new Magasin(), "val", "id");

    pi.getFormufle().changerEnChamp(liste);
    pi.getFormufle().getChamp("daty_0").setLibelle("Date");
    pi.getFormufle().getChamp("idCategorie_0").setLibelle("Cat&eacute;gorie");
    pi.getFormufle().getChamp("idLigne_0").setLibelle("Ligne");
    pi.getFormufle().getChamp("heure_0").setLibelle("Heure");
    pi.getFormufle().getChamp("idMagasin_0").setLibelle("Magasin");
    pi.getFormufle().getChamp("valeur_0").setLibelle("Nouvelle Index");
    affichage.Champ.setVisible(pi.getFormufle().getChampFille("id"),false);
    affichage.Champ.setVisible(pi.getFormufle().getChampFille("ecart"),false);
    affichage.Champ.setDefaut(pi.getFormufle().getChampFille("ecart"),"0");
    affichage.Champ.setVisible(pi.getFormufle().getChampFille("etat"),false);
    affichage.Champ.setVisible(pi.getFormufle().getChampFille("ancien"),false);
    affichage.Champ.setVisible(pi.getFormufle().getChampFille("remarque"),false);
    affichage.Champ.setDefaut(pi.getFormufle().getChampFille("remarque"),"init");
     ZoneId zoneMadagascar = ZoneId.of("Africa/Nairobi");
     ZonedDateTime now = ZonedDateTime.now(zoneMadagascar);
     DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
     String heureactuel = now.format(format);
    affichage.Champ.setDefaut(pi.getFormufle().getChampFille("heure"),heureactuel);

    pi.preparerDataFormu();
    pi.getFormufle().makeHtmlInsertTableauIndex();
%>
<div class="content-wrapper">
    <h1 class="box-title">Saisie d'un relev&eacute;</h1>
    <form action="<%=pi.getLien()%>?but=compteur/apresMultipleInitialisationReleve.jsp" method="post" name="appro" id="appro" >
        <%
            out.println(pi.getFormufle().getHtmlTableauInsert());
        %>
        <input name="acte" type="hidden" id="acte" value="insertFilleSeul">
        <input name="classe" type="hidden" id="classe" value="maintenance.configuration.CompteurMaintenance">
        <input name="nombreLigne" type="hidden" id="nombreLigne" value="<%=nombreLigne%>">
        <input name="bute" type="hidden" id="bute" value="compteur/releve-liste.jsp">
        <input name="nomtable" type="hidden" id="nomtable" value="CompteurMaintenance">
        <input name="classefille" type="hidden" id="classefille" value="maintenance.configuration.CompteurMaintenance">
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
