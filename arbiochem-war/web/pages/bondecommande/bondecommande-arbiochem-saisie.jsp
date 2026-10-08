<%--
  Created by IntelliJ IDEA.
  User: Tsinjoniaina
  Date: 10/08/2026
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%--
  ============================================================================
  bondecommande-arbiochem-saisie.jsp : SAISIE D'UN BON DE COMMANDE FOURNISSEUR
  ============================================================================

  ENTREES (ce que la page reçoit)
    - Paramètre HTTP "iddmdachat" (facultatif) : si présent, le formulaire est
      pré-rempli avec la demande d'achat correspondante (fournisseur, date,
      magasin, département, lignes de produits restant à commander)
    - Paramètre HTTP "acte" : si "update", le titre devient "Modification"
    - Session : "u" (utilisateur connecté) et "lien" (URL de base : module.jsp)

  SORTIES (ce que la page affiche / où elle mène)
    - Un formulaire d'en-tête (date, fournisseur, devise, magasin, ...)
    - Le total calculé en direct, puis un tableau de 10 lignes de produits
    - Au clic sur "Enregistrer" : POST vers module.jsp?but=apresMultiple.jsp
      avec les champs cachés suivants :
        acte=insert                                  (action à faire)
        classe=faturefournisseur.As_BonDeCommande    (objet mère)
        classefille=faturefournisseur.As_BonDeCommande_Fille  (objets lignes)
        nombreLigne=10, colonneMere=idbc             (lien mère / lignes)
        bute=bondecommande/bondecommande-arbiochem-fiche.jsp  (page affichée après)

  A SAVOIR
    - Les champs "Fournisseur" et "Produit" sont des autocomplétions : il faut
      CLIQUER sur une suggestion, sinon la vraie valeur (l'id) reste vide
    - Seules les lignes COCHEES sont enregistrées
    - En cas d'erreur : alerte avec le message, puis retour à la page précédente
--%>
<%@page import="user.*"%>
<%@page import="affichage.*"%>
<%@page import="utilitaire.*"%>
<%@page import="faturefournisseur.*"%>
<%@ page import="magasin.Magasin" %>
<%@ page import="mg.cnaps.compta.ConstanteCompta" %>
<%@ page import="prevision.Service" %>
<%@ page import="bean.TypeObjet" %>
<%
    try {
        // 1. Utilisateur, objet mère (BC), objet fille (ligne) et page de saisie multiple (mère + 10 lignes)
        UserEJB u = null;
        u = (UserEJB) session.getValue("u");
        As_BonDeCommande mere = new As_BonDeCommande();
        As_BonDeCommande_Fille fille = new As_BonDeCommande_Fille();
        fille.setNomTable("AS_BONDECOMMANDE_FILLE_CPL");
        int nombreLigne = 10;
        PageInsertMultiple pi = new PageInsertMultiple(mere, fille, request, nombreLigne, u);
        pi.setLien((String) session.getValue("lien"));

        // 2. Listes déroulantes de l'en-tête : devise (AR par défaut), mode de paiement,
        //    magasin actif, département actif
        Liste[] liste = new Liste[4];
        caisse.Devise dev = new caisse.Devise();
        //dev.setId("AR");
        liste[0] = new Liste("idDevise",dev,"val","id");
        liste[0].setDefaut("AR");
        ModePaiement mp = new ModePaiement();
        liste[1] = new Liste("modepaiement",mp,"val","id");
        Magasin magasin = new Magasin();
        magasin.setNomTable("MAGASIN2");
        liste[2] = new Liste("idMagasin",magasin,"val","id");
        liste[2].setApresW(" and actif = 1");
        liste[3] = new Liste("service",new TypeObjet("DEPARTEMENT"),"val","id");
        liste[3].setApresW(" and Actif = 1");
        pi.getFormu().changerEnChamp(liste);

        // 3. Libellés et comportement des champs de l'en-tête
        pi.getFormu().getChamp("service").setLibelle("D&eacute;partement");
        pi.getFormu().getChamp("modepaiement").setLibelle("Mode de paiement");
        pi.getFormu().getChamp("idMagasin").setLibelle("Magasin");
        pi.getFormu().getChamp("etat").setVisible(false);
        pi.getFormu().getChamp("reference").setLibelle("R&eacute;f&eacute;rence");
        pi.getFormu().getChamp("designation").setLibelle("D&eacute;signation");
        pi.getFormu().getChamp("remarque").setLibelle("Remarque");
        pi.getFormu().getChamp("daty").setLibelle("Date");
        pi.getFormu().getChamp("dateLimite").setLibelle("Date limite de livraison");
        // Fournisseur : autocomplétion (fournisseurs actifs seulement) + bouton "+" pour en créer un
        pi.getFormu().getChamp("fournisseur").setLibelle("Fournisseur");
        pi.getFormu().getChamp("fournisseur").setPageAppelCompleteAWhere("faturefournisseur.Fournisseur","id","Fournisseur","taxe","taxe", " and estActif = 1");
        pi.getFormu().getChamp("fournisseur").setPageAppelInsert("fournisseur/fournisseur-saisie.jsp","fournisseur;fournisseurlibelle","id;nom");
        pi.getFormu().getChamp("idDevise").setLibelle("Devise");
        pi.getFormu().getChamp("iddmdachat").setLibelle("Demande d'achat");
        pi.getFormu().getChamp("remise").setAutre("onchange='calculerMontantV2()'");
        pi.getFormu().getChamp("refproforma").setLibelle("R&eacute;f&eacute;rence proforma");
        // Demande d'achat : verrouillée si elle vient de l'URL, sinon autocomplétion
        if(request.getParameter("iddmdachat")!=null) {
            pi.getFormu().getChamp("iddmdachat").setDefaut(request.getParameter("iddmdachat"));
            pi.getFormu().getChamp("iddmdachat").setAutre("readonly");
        } else {
            pi.getFormu().getChamp("iddmdachat").setPageAppelComplete("faturefournisseur.DmdAchat","id","DMDACHAT","id","id");
        }
        // Changer de devise recharge l'autocomplétion des produits (fonction JS deviseModification)
        pi.getFormu().getChamp("idDevise").setAutre("onChange='deviseModification()'");

        // 4. Champs des lignes : produit en autocomplétion (vue AS_INGREDIENTS_LIB_ACHAT)
        //    qui remplit aussi pu, taux de change, compte et désignation ; bouton "+" pour créer un produit
        affichage.Champ.setPageAppelComplete(pi.getFormufle().getChampFille("produit"),"produits.IngredientsLib","id","AS_INGREDIENTS_LIB_ACHAT","pu;taux;compte_achat;compte_achat;libelle","pu;tauxDeChange;compte;comptelibelle;produitLib");
        affichage.Champ.setPageAppelInsert(pi.getFormufle().getChampFille("produit"),"produits/as-ingredients-saisie.jsp","id;libelle");

        // Libellés des colonnes et champs cachés (id, idbc, montant, unité, devise)
        pi.getFormufle().getChamp("produit_0").setLibelle("Produit");
        pi.getFormufle().getChamp("produitLib_0").setLibelle("D&eacute;signation");
        pi.getFormufle().getChamp("tva_0").setLibelle("TVA (%)");
        pi.getFormufle().getChamp("remise_0").setLibelle("Remise (%)");
        pi.getFormufle().getChamp("quantite_0").setLibelle("Quantit&eacute;");
        pi.getFormufle().getChamp("pu_0").setLibelle("Prix Unitaire HT");
        pi.getFormufle().getChamp("idDevise_0").setLibelle("Devise");
        pi.getFormufle().getChampMulitple("id").setVisible(false);
        pi.getFormufle().getChampMulitple("idbc").setVisible(false);
        pi.getFormufle().getChampMulitple("montant").setVisible(false);
        pi.getFormufle().getChampMulitple("unite").setVisible(false);
        //pi.getFormufle().getChampMulitple("tauxDeChange").setVisible(false);
        //pi.getFormufle().getChampMulitple("remise").setVisible(false);
        pi.getFormufle().getChampMulitple("idDevise").setVisible(false);
        pi.getFormufle().getChamp("tauxDeChange_0").setLibelle("Taux de change");
//        pi.getFormufle().getChampMulitple("tauxDeChange").setAutre("readonly");

        // 5. Toute modification de pu, quantité, TVA ou produit recalcule le total
        affichage.Champ.setAutre(pi.getFormufle().getChampFille("pu"),"onchange='calculerMontantV2()'");
        affichage.Champ.setAutre(pi.getFormufle().getChampFille("quantite"),"onchange='calculerMontantV2()'");
        affichage.Champ.setAutre(pi.getFormufle().getChampFille("tva"),"onchange='calculerMontantV2()'");
        affichage.Champ.setAutre(pi.getFormufle().getChampFille("produit"),"onchange='calculerMontantV2()'");

        // 6. Valeurs par défaut des 10 lignes : devise AR (non modifiable), quantité 0, TVA 0, taux 1
        pi.preparerDataFormu();
        for(int i=0;i<nombreLigne;i++){
            pi.getFormufle().getChamp("idDevise_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("quantite_"+i).setAutre("onChange='calculerMontantV2("+i+")'");
            pi.getFormufle().getChamp("remise_"+i).setAutre("onChange='calculerMontantV2("+i+")'");
            pi.getFormufle().getChamp("tva_"+i).setAutre("onChange='calculerMontantV2("+i+")'");
            pi.getFormufle().getChamp("quantite_"+i).setDefaut("0");
            pi.getFormufle().getChamp("tva_"+i).setDefaut("0");
            pi.getFormufle().getChamp("idDevise_"+i).setDefaut("AR");
            pi.getFormufle().getChamp("tauxDeChange_"+i).setDefaut("1");
//            pi.getFormufle().getChamp("tva_"+i).setDefaut("20");
        }

        // 7. Si on vient d'une demande d'achat : pré-remplir l'en-tête et les lignes
        //    (les quantités sont ajustées avec ce qui reste à commander)
        //pi.getFormufle().getChampMulitple("quantite").setVisible(false);
        String iddmdachat = request.getParameter("iddmdachat");
        if (iddmdachat!=null && !iddmdachat.isEmpty()) {
            DmdAchat dmdAchat = (DmdAchat) new DmdAchat().getById(iddmdachat,"DMDACHAT",null);
            if (dmdAchat!=null){
                pi.getFormu().getChamp("daty").setDefaut(Utilitaire.datetostring(dmdAchat.getDaty()));
                pi.getFormu().getChamp("fournisseur").setDefaut(dmdAchat.getFournisseur());
                pi.getFormu().getChamp("designation").setDefaut("bon de commande  de la demande d'achat "+iddmdachat);
                pi.getFormu().getChamp("idMagasin").setDefaut(dmdAchat.getIdMagasin());
                pi.getFormu().getChamp("service").setDefaut(dmdAchat.getService());

                if(dmdAchat.getDateLimite()!=null){
                    pi.getFormu().getChamp("dateLimite").setDefaut(Utilitaire.datetostring(dmdAchat.getDateLimite()));
                }
                As_BonDeCommande_Fille[] bcFille = dmdAchat.getBonDeCommandeDetails(null);
                As_BonDeCommande_Fille[] nouvellebcFille = DmdAchatSuiviCommande.updateResterCommandeQuantite(bcFille, iddmdachat);
                System.out.println("qte aff = " + bcFille[0].getQuantite());
                pi.setDefautFille(nouvellebcFille);
            }
        }

        // 8. Variables de navigation : elles partent dans les champs cachés du formulaire
        //Variables de navigation
        String classeMere = "faturefournisseur.As_BonDeCommande";
        String classeFille = "faturefournisseur.As_BonDeCommande_Fille";
        String butApresPost = "bondecommande/bondecommande-arbiochem-fiche.jsp";
        String colonneMere = "idbc";

        // 9. Ordre des colonnes du tableau, puis génération du HTML des deux formulaires
        pi.getFormufle().setColOrdre(new String[]{"produit", "produitLib", "quantite", "pu", "tva","remise", "tauxDeChange"});
        //Preparer les affichages
        pi.getFormu().makeHtmlInsertTabIndex();
        pi.getFormufle().makeHtmlInsertTableauIndex();

%>
<div class="content-wrapper">
    <%-- Titre : "Modification" si acte=update, sinon "Saisie" --%>
    <%if (request.getParameter("acte") != null && request.getParameter("acte").equalsIgnoreCase("update")) {%>
    <h1>Modification d'un bon de commande fournisseur</h1>
    <% }else{ %>
    <h1>Saisie d'un bon de commande fournisseur</h1>
    <% } %>
    <div class="box-body">
        <%-- Au clic sur Enregistrer : POST vers apresMultiple.jsp (le contrôleur) --%>
        <form class='container' action="<%=pi.getLien()%>?but=apresMultiple.jsp" method="post" >
            <%-- En-tête du bon de commande --%>
            <%

                out.println(pi.getFormu().getHtmlInsert());
            %>
            <%-- Total affiché en direct (mis à jour par calculerMontantV2) --%>
            <div class="col-md-12 nopadding" >
                <div class="col-md-12 cardradius">
                    <h3 class="fontinter" style="background: white;padding: 16px;margin-top: 10px;border-radius: 16px;" >Total  : <span id="montanttotal">0</span><span id="deviseLibelle"> Ar</span></h3>
                </div>
            </div>
            <%-- Tableau des 10 lignes de produits --%>
            <%
                out.println(pi.getFormufle().getHtmlTableauInsert());
            %>

            <%-- Champs cachés lus par apresMultiple.jsp --%>
            <input name="taxe" type="hidden" id="taxe" value="">
            <input name="acte" type="hidden" id="nature" value="insert">
            <input name="bute" type="hidden" id="bute" value="<%= butApresPost %>">
            <input name="classe" type="hidden" id="classe" value="<%= classeMere %>">
            <input name="classefille" type="hidden" id="classefille" value="<%= classeFille %>">
            <input name="nombreLigne" type="hidden" id="nombreLigne" value="<%= nombreLigne %>">
            <input name="colonneMere" type="hidden" id="colonneMere" value="<%= colonneMere %>">
            <input name="nomtable" type="hidden" id="nomtable" value="AS_BONDECOMMANDE_FILLE">
        </form>
    </div>
</div>
</div>
<%-- JAVASCRIPT 1 : au chargement, calcule le total et applique la TVA selon le fournisseur --%>
<script>
    $(document).ready(function() {
        var nombreLigne = <%=nombreLigne%>;
        for(var i=0;i<nombreLigne;i++){
            calculerMontant(i);
        }

        // Si le champ caché "taxe" (rempli par le choix du fournisseur) vaut 1.0,
        // la TVA de toutes les lignes prend la valeur de ConstanteCompta.constante_tva ; sinon 0
        const TAXE_VALUE = <%= ConstanteCompta.constante_tva %>;

        const taxeInput = document.getElementById('taxe');
        const tvaInputs = document.querySelectorAll('[id^="tva_"]');

        const observer = new MutationObserver(() => {
            const newValue = taxeInput.value;

            if (newValue === '1.0') {
                tvaInputs.forEach(input => {
                    input.value = TAXE_VALUE;
                });
            } else {
                tvaInputs.forEach(input => {
                    input.value = '0';
                });
            }
        });

        observer.observe(taxeInput, {
            attributes: true,
            attributeFilter: ['value']
        });
    });
</script>
<%-- JAVASCRIPT 2 : calcul du total et gestion du changement de devise --%>
<script>
    // Total simple : somme de quantité x prix unitaire (sans TVA ni remise)
    function calculerMontant(indice,source) {
        var val = 0;
        $('input[id^="quantite_"]').each(function() {
            var quantite =  parseFloat($("#"+$(this).attr('id').replace("quantite","pu")).val());
            var montant = parseFloat($(this).val());
            if(!isNaN(quantite) && !isNaN(montant)){
                var value =quantite * montant;
                val += value;
            }
        });
        $("#montanttotal").html(val.toFixed(2));
    }

    // Total TTC : pour chaque ligne HT - remise + TVA, puis remise globale de l'en-tête
    function calculerMontantV2(){
        var listChamp = document.querySelectorAll('input[id^="pu_"]');
        let montantTtc = 0;
        for (let i = 0; i < listChamp.length; i++) {
            let pu = parseFloat(document.getElementById("pu_"+i)?.value || 0);
            let qte = parseFloat(document.getElementById("quantite_"+i)?.value || 0);
            let tva = parseFloat(document.getElementById("tva_"+i)?.value || 0);
            let remise = parseFloat(document.getElementById("remise_"+i)?.value || 0);
            let montantremise = (pu*qte)*(remise/100);
            montantTtc += ((pu*qte)-montantremise)+(((pu*qte)-montantremise)*(tva/100));
        }

        // Remise globale au niveau de l'en-tête (mère), appliquée après le calcul des lignes
        let remiseMere = parseFloat(document.getElementById("remise")?.value || 0);
        let montantRemiseMere = montantTtc * (remiseMere / 100);
        montantTtc -= montantRemiseMere;

        document.getElementById("montanttotal").innerHTML = montantTtc.toLocaleString('en-US');
        // Optionnel : afficher le montant de la remise mère quelque part si besoin
        // document.getElementById("montantremisemere").innerHTML = montantRemiseMere.toLocaleString('en-US');
    }

    document.addEventListener("DOMContentLoaded", function() {
        calculerMontantV2();
    });

    // Quand la devise change : met la devise sur chaque ligne, vide produit / prix / taux,
    // et recharge l'autocomplétion des produits avec la table de la bonne devise (AR, USD ou EUR).
    // L'autocomplétion se déclenche avec Tab ou le bouton loupe ; si on retape dans le champ,
    // la vraie valeur (id du produit) est effacée : il faut re-sélectionner une suggestion.
    function deviseModification() {

        var nombreLigne = parseInt($("#nombreLigne").val());
        for(let iL=0;iL<nombreLigne;iL++){
            $(function(){
                var mapping = {
                    "AR": {
                        "table": "ST_INGREDIENTSAUTOACHAT_CPL",
                    },
                    "USD": {
                        "table": "ST_INGREDIENTSAUTOACHAT_USD"
                    },
                    "EUR": {
                        "table": "ST_INGREDIENTSAUTOACHAT_EUR"
                    }
                };
                $("#deviseLibelle").html($('#idDevise').val());
                var idDevise = $('#idDevise').val();
                $("#idDevise_"+iL).val(idDevise);
                let autocompleteTriggered = false;
                $("#produit_"+iL+"libelle").autocomplete('destroy');
                $("#tauxDeChange_"+iL).val('');
                $("#pu_"+iL).val('');
                $("#produit_"+iL+"libelle").autocomplete({
                    source: function(request, response) {
                        $("#produit_"+iL).val('');
                        if (autocompleteTriggered) {
                            fetchAutocomplete(request, response, "null", "id", "null", mapping[idDevise].table, "produits.IngredientsLib", "true","pu;taux;compte_achat;compte_achat;libelle");
                        }
                    },
                    select: function(event, ui) {
                        $("#produit_"+iL+"libelle").val(ui.item.label);
                        $("#produit_"+iL).val(ui.item.value);
                        $("#produit_"+iL).trigger('change');
                        $(this).autocomplete('disable');
                        var champsDependant = ['pu_'+iL,'tauxDeChange_'+iL,'compte_'+iL, 'comptelibelle_'+iL, 'produitLib_'+iL];
                        for(let i=0;i<champsDependant.length;i++){
                            $('#'+champsDependant[i]).val(ui.item.retour.split(';')[i]);
                        }
                        autocompleteTriggered = false;
                        return false;
                    }
                }).autocomplete('disable');
                $("#produit_"+iL+"libelle").off('keydown');
                $("#produit_"+iL+"libelle").keydown(function(event) {
                    if (event.key === 'Tab') {
                        event.preventDefault();
                        autocompleteTriggered = true;
                        $(this).autocomplete('enable').autocomplete('search', $(this).val());
                    }
                });
                $("#produit_"+iL+"libelle").off('input');
                $("#produit_"+iL+"libelle").on('input', function() {
                    $("#produit_"+iL).val('');
                    autocompleteTriggered = false;
                    $(this).autocomplete('disable');
                });
                $("#produit_"+iL+"searchBtn").off('click');
                $("#produit_"+iL+"searchBtn").click(function() {
                    autocompleteTriggered = true;
                    $("#produit_"+iL+"libelle").autocomplete('enable').autocomplete('search', $("#produit_"+iL+"libelle").val());
                });
            });
        }
    }
</script>
<%-- En cas d'erreur : message en alerte, puis retour à la page précédente --%>
<%
} catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript">
    alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>
