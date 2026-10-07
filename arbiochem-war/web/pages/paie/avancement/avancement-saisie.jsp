<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="user.UserEJB" %>
<%@ page import="paie.avancement.PaieAvancement" %>
<%@ page import="affichage.PageInsert" %>
<%@ page import="affichage.Champ" %>
<%@ page import="affichage.Liste" %>
<%@ page import="bean.TypeObjet" %>
<%@ page import="utilitaire.Utilitaire" %>
<%@ page import="paie.employe.EmployeComplet" %>

<%
    try {
        String autreparsley = "data-parsley-range='[8, 40]' required";
        UserEJB u = (UserEJB) session.getValue("u");

        String mapping = "paie.avancement.PaieAvancement";
        String nomTable = "PAIE_AVANCEMENT";
        String apres = "paie/avancement/avancement-fiche.jsp";
        String titre = "Saisie d'un mouvement";
        String idPersonnel = request.getParameter("id");

        PaieAvancement paieAvancement = new PaieAvancement();
        paieAvancement.setNomTable(nomTable);

        PageInsert pi = new PageInsert(paieAvancement, request, u);
        pi.setLien((String) session.getValue("lien"));

        affichage.Champ[] liste = new Champ[7];

        // Type Avancement
        TypeObjet idTypeAvancement = new TypeObjet();
        idTypeAvancement.setNomTable("TYPE_AVANCEMENT");
        liste[0] = new Liste("idTypeAvancement", idTypeAvancement, "val", "id");

        // Direction
        TypeObjet directionObjet = new TypeObjet();
        directionObjet.setNomTable("log_direction");
        liste[1] = new Liste("direction", directionObjet, "val", "id");

        // Region
        TypeObjet regionObjet = new TypeObjet();
        regionObjet.setNomTable("region");
        liste[2] = new Liste("region", regionObjet, "val", "id");

        // Mode de paiement
        TypeObjet modePaiementObjet = new TypeObjet();
        modePaiementObjet.setNomTable("MODEPAIEMENT");
        liste[3] = new Liste("MODEPAIEMENT", modePaiementObjet, "val", "id");

        // Type Contrat
        TypeObjet typeContrat = new TypeObjet();
        typeContrat.setNomTable("type_contrat");
        liste[4] = new Liste("CONTRAT", typeContrat, "val", "id");

        // Type Personnel
        TypeObjet typePersonnel = new TypeObjet();
        typePersonnel.setNomTable("categorie_paie");
        liste[5] = new Liste("TYPEPERSONNEL", typePersonnel, "val", "id");

        // Type Personnel
        TypeObjet unite = new TypeObjet();
        unite.setNomTable("LOG_UNITE");
        liste[6] = new Liste("unite", unite, "val", "id");

        pi.getFormu().changerEnChamp(liste);

        // Personnel
        // pi.getFormu().getChamp("id_logpers").setPageAppelComplete("paie.log.LogPersonnel", "id", "LOG_PERSONNEL_INFOCOMPLET");
        pi.getFormu().getChamp("id_logpers").setPageAppelComplete("paie.log.LogPersonnel", "id", "LOG_PERSONNEL_INFOCOMPLET");
        pi.getFormu().getChamp("id_logpers").setLibelle("Personnel");

        // Service
        pi.getFormu().getChamp("service").setPageAppelComplete("paie.log.LogService", "id", "log_service");
        pi.getFormu().getChamp("service").setLibelle("Rattachement");

        // Fonction
        pi.getFormu().getChamp("idfonction").setPageAppelComplete("paie.edition.PaieFonction", "id", "paie_fonction");
        pi.getFormu().getChamp("idfonction").setLibelle("Fonction");

        // Other fields
        pi.getFormu().getChamp("MODEPAIEMENT").setLibelle("Mode de paiement");
        pi.getFormu().getChamp("TYPEPERSONNEL").setLibelle("Cadre professionnel");
        pi.getFormu().getChamp("dureecontrat").setLibelle("Dur&eacute;e de contrat (en jours)");
        pi.getFormu().getChamp("contrat").setLibelle("Contrat");
        pi.getFormu().getChamp("datedecision").setLibelle("Date de d&eacute;cision");
        pi.getFormu().getChamp("datedecision").setDefaut(Utilitaire.dateDuJour());
        pi.getFormu().getChamp("refdecision").setLibelle("R&eacute;f&eacute;rence de d&eacute;cision");
        pi.getFormu().getChamp("date_application").setLibelle("Date d'application");
        pi.getFormu().getChamp("date_application").setDefaut(Utilitaire.dateDuJour());
        pi.getFormu().getChamp("indicegrade").setLibelle("Indice");
        pi.getFormu().getChamp("remarque").setLibelle("Remarque");
        pi.getFormu().getChamp("code_banque").setLibelle("Code Banque");
        pi.getFormu().getChamp("region").setLibelle("R&eacute;gion d’affectation");
        pi.getFormu().getChamp("idTypeAvancement").setLibelle("Type Avancement");

        pi.getFormu().getChamp("motif").setVisible(false);
        pi.getFormu().getChamp("idcategorie").setVisible(false);
        pi.getFormu().getChamp("ctg").setVisible(false);
        pi.getFormu().getChamp("etat").setVisible(false);
        pi.getFormu().getChamp("echelon").setVisible(false);
        pi.getFormu().getChamp("indice_fonctionnel").setVisible(false);
        pi.getFormu().getChamp("indice_ct").setVisible(false);
        pi.getFormu().getChamp("classee").setVisible(false);
        pi.getFormu().getChamp("matricule_patron").setVisible(false);
        pi.getFormu().getChamp("statut").setVisible(false);
        pi.getFormu().getChamp("droit_hs").setVisible(false);
        pi.getFormu().getChamp("vehiculee").setVisible(false);


        if (request.getParameter("acte") == null)
        {
            if (idPersonnel != null && !idPersonnel.isEmpty()) {
                EmployeComplet emp = new EmployeComplet();
                emp.setId(idPersonnel);

                PaieAvancement avancement = emp.genererPaieAvancement();
                pi.getFormu().getChamp("id_logpers").setDefaut(avancement.getId_logpers());
                pi.getFormu().getChamp("service").setDefaut(avancement.getService());
                pi.getFormu().getChamp("idfonction").setDefaut(avancement.getIdfonction());
            }
        }


        pi.preparerDataFormu();


        String acte = "insert";
        if (request.getParameter("acte")!=null && request.getParameter("acte").equals("update")){
            acte = request.getParameter("acte");
            titre = "Modification d'une Avancement";
        }
%>

<div class="content-wrapper">
    <h1><%=titre%></h1>

    <form action="<%=pi.getLien()%>?but=paie/avancement/apresmouvement.jsp" method="post" name="<%=nomTable%>" id="<%=nomTable%>" data-parsley-validate>
        <%
            pi.getFormu().makeHtmlInsertTabIndex();
            out.println(pi.getFormu().getHtmlInsert());
        %>

        <input name="acte" type="hidden" id="nature" value=<%=acte%>>
        <input name="bute" type="hidden" id="bute" value="<%=apres%>">
        <input name="classe" type="hidden" id="classe" value="<%=mapping%>">
        <input name="nomtable" type="hidden" id="nomtable" value="<%=nomTable%>">
    </form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {

        const typeAvancement = document.getElementById("idTypeAvancement");

        if (!typeAvancement) return;


        const reglesAvancement = {

            // CHANGEMENT D'INDICE
            "TYPAV0001": [
                "direction",
                "region",
                "MODEPAIEMENT",
                "CONTRAT",
                "TYPEPERSONNEL",
                "service",
                "idfonction",
                "datedecision",
                "refdecision",
                "date_application",
                "code_banque",
                "remarque",
                "dureeContrat",
                "unite"
            ],

            // CHANGEMENT D'INDICE
            "TYPAV0004": [
                "direction",
                "region",
                "MODEPAIEMENT",
                "CONTRAT",
                "TYPEPERSONNEL",
                "service",
                "datedecision",
                "refdecision",
                "date_application",
                "code_banque",
                "remarque",
                "dureeContrat",
                "indicegrade",
                "unite"
            ],

            // CHANGEMENT DE REGION
            "TYPAV0005": [
                "idfonction",
                "region",
                "MODEPAIEMENT",
                "CONTRAT",
                "TYPEPERSONNEL",
                "service",
                "datedecision",
                "refdecision",
                "date_application",
                "code_banque",
                "remarque",
                "dureeContrat",
                "indicegrade",
                "unite"
            ],

            // CHANGEMENT D'AFFECTATION
            "TYPAV0006": [
                "idfonction",
                "direction",
                "MODEPAIEMENT",
                "CONTRAT",
                "TYPEPERSONNEL",
                "service",
                "datedecision",
                "refdecision",
                "date_application",
                "code_banque",
                "remarque",
                "dureeContrat",
                "indicegrade",
                "unite"
            ],

            // CHANGEMENNT DE MODE DE PAIEMENT
            "TYPAV0007": [
                "region",
                "idfonction",
                "direction",
                "CONTRAT",
                "TYPEPERSONNEL",
                "service",
                "datedecision",
                "refdecision",
                "date_application",
                "remarque",
                "dureeContrat",
                "indicegrade",
                "unite"
            ],

            // CHANGEMENNT DE COMPTE
            "TYPAV0008": [
                "region",
                "idfonction",
                "direction",
                "MODEPAIEMENT",
                "CONTRAT",
                "TYPEPERSONNEL",
                "service",
                "datedecision",
                "refdecision",
                "date_application",
                "remarque",
                "dureeContrat",
                "indicegrade",
                "unite"
            ],

            // CHANGEMENNT DE CONTRAT
            "TYPAV0009": [
                "region",
                "idfonction",
                "direction",
                "MODEPAIEMENT",
                "TYPEPERSONNEL",
                "service",
                "datedecision",
                "refdecision",
                "date_application",
                "code_banque",
                "remarque",
                "indicegrade",
                "unite"
            ],

            // CHANGEMENT D'UNITE
            "TYPAV0010": [
                "region",
                "idfonction",
                "direction",
                "MODEPAIEMENT",
                "CONTRAT",
                "TYPEPERSONNEL",
                "service",
                "datedecision",
                "refdecision",
                "date_application",
                "code_banque",
                "remarque",
                "dureeContrat",
                "indicegrade",
            ],

            // CHANGEMENT DE SERVICE/SECTION
            "TYPAV0011": [
                "direction",
                "region",
                "MODEPAIEMENT",
                "CONTRAT",
                "TYPEPERSONNEL",
                "idfonction",
                "datedecision",
                "refdecision",
                "date_application",
                "code_banque",
                "remarque",
                "dureeContrat",
                "indicegrade",
                "unite"
            ],



        };


        function appliquerRegleAvancement() {

            const value = typeAvancement.value;


            // Récupère les champs à cacher pour ce type
            const champsCacher = reglesAvancement[value] || [];


            // Tous les champs gérés par les règles
            Object.values(reglesAvancement)
                .flat()
                .forEach(function(idChamp) {

                    const champ = document.getElementById(idChamp);

                    if (!champ) return;

                    const div = champ.closest(".form-input");

                    if (!div) return;


                    if (champsCacher.includes(idChamp)) {
                        div.style.display = "none";
                    } else {
                        div.style.display = "block";
                    }

                });

        }


        appliquerRegleAvancement();

        typeAvancement.addEventListener("change", appliquerRegleAvancement);

    });
</script>
<%
} catch (Exception e) {
    e.printStackTrace();
%>

<script language="JavaScript">
    alert('<%=e.getMessage()%>');
    history.back();
</script>
<% } %>
