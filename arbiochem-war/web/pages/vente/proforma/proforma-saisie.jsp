
<%--
  Created by IntelliJ IDEA.
  User: safidy
  Date: 05/08/2025
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>

<%@page import="utils.ConstanteSocobis"%>
<%@page import="magasin.Magasin"%>
<%@page import="vente.*"%>
<%@page import="user.*"%>
<%@page import="bean.*" %>
<%@page import="affichage.*"%>
<%@page import="utilitaire.*"%>
<%@ page import="client.Client" %>
<%@ page import="proforma.Proforma" %>
<%@ page import="proforma.ProformaDetails" %>
<%@ page import="proforma.ProformaDetailsLib" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="produits.TarifIngredients" %>
<%
    boolean carteExpiree = false;
    try{
        UserEJB u = null;
        u = (UserEJB) session.getValue("u");
        PageInsertMultiple pi=null;
        Proforma mere = new Proforma();
        ProformaDetailsLib fille = new ProformaDetailsLib();
        fille.setNomTable("PROFORMA_DETAILS_VIDE");

        int nombreLigne = 10;
        String idCommande = request.getParameter("idCommande");

        if (idCommande != null && !idCommande.trim().isEmpty()) {
            Commande commande = new Commande();
            commande.setId(idCommande);
            nombreLigne = commande.getFilleCommandeLib().length;
        }

        pi = new PageInsertMultiple(mere,fille,request, nombreLigne,u);
        Proforma prerempli = null;

        pi.setLien((String) session.getValue("lien"));
        pi.getFormu().getChamp("remarque").setLibelle("Remarque");
        pi.getFormu().getChamp("fraislivraison").setLibelle("Frais de Livraison(Par Kg)");
        pi.getFormu().getChamp("fraislivraison").setAutre("onchange=\"recalculerTousLesMontants()\"");
        pi.getFormu().getChamp("daty").setLibelle("Date");
        pi.getFormu().getChamp("etat").setVisible(false);
        pi.getFormu().getChamp("idClient").setLibelle("Client");
        pi.getFormu().getChamp("idClient").setPageAppelComplete("client.Client","id","Client");
        pi.getFormu().getChamp("idClient").setPageAppelInsert("client/client-saisie.jsp","idClient;idClientlibelle","id;nom");
        pi.getFormu().getChamp("idClient").setAutre("onchange=\"updateFille(event, 'formId')\"");

        pi.getFormu().getChamp("designation").setLibelle("D&eacute;signation");
        pi.getFormu().getChamp("idMagasin").setLibelle("Point");
        pi.getFormu().getChamp("idCommande").setAutre("readonly");
        pi.getFormu().getChamp("idCommande").setLibelle("ID Commande");

        //pi.getFormu().getChamp("idorigine").setLibelle("ID Origine");
        pi.getFormu().getChamp("idorigine").setVisible(false);
        pi.getFormu().getChamp("datyPrevu").setVisible(false);
        pi.getFormu().getChamp("remise").setVisible(false);
        pi.getFormu().getChamp("tva").setVisible(false);
        pi.getFormu().getChamp("echeance").setVisible(false);
        pi.getFormu().getChamp("reglement").setVisible(false);
        pi.getFormu().getChamp("estPrevu").setVisible(false);
        pi.getFormu().getChamp("numeroProforma").setVisible(false);
        pi.getFormu().getChamp("lieuLivraison").setLibelle("Lieu de livraison");
        pi.getFormu().getChamp("dateLivraison").setLibelle("Date de livraison");

//        if(request.getParameter("idDmd")!=null && !request.getParameter("idDmd").isEmpty()){
//            String idDmd = request.getParameter("idDmd");
//            DmdPrix demandePrix = new DmdPrix();
//            prerempli = (Proforma) demandePrix.genererProforma(idDmd, null);
//
//        }
//        ProformaDetails[] detFille = null;
//        if(request.getParameter("id")!=null && !request.getParameter("id").isEmpty()){
//            Proforma pro = new Proforma();
//            pro.setId(request.getParameter("id"));
//            prerempli = (Proforma) pro.getProforma(null);
//            if(prerempli!=null){
//                ProformaDetails [] temp = (ProformaDetails[]) prerempli.getFilleProforma();
//                detFille = new ProformaDetails[temp.length-1];
//                for (int i = 0; i < temp.length; i++) {
//                    if(temp[i].getIdProduit().compareToIgnoreCase(ConstanteLocation.id_produit_caution)!=0){
//                        detFille[i] = temp[i];
//                    }
//                }
//                prerempli.setIdOrigine(request.getParameter("id"));
//            }
//        }

        String idDevis = request.getParameter("idDevis");
       /*if(idDevis != null && !idDevis.isEmpty()){
            Devis devis = new Devis();
            devis.setId(idDevis);
            prerempli = (Proforma) devis.genererProforma(idDevis, null);
        }*/


        Liste[] liste = new Liste[3];
        Magasin m = new Magasin();
        m.setNomTable("MagasinVente");
        liste[0] = new Liste("idMagasin",m,"val","id");
        //liste[0].setDefaut(ConstanteSocobis.MAGASIN);
        liste[1] = new Liste("idDevise",new caisse.Devise(),"val","id");

        Liste listemode = new Liste("modeLivraison");
        String [] affVal = new String[2];
        String [] aff = new String[2];
        aff = new String[]{"LIVRAISON","RECUPERATION"};
        affVal = new String[]{"1","2"};
        listemode.ajouterValeur(affVal,aff);
        liste[2] = listemode;
        pi.getFormu().changerEnChamp(liste);
        pi.getFormu().getChamp("idMagasin").setAutre("onchange=\"updateFille(event, 'formId')\"");
        pi.getFormu().getChamp("idMagasin").setDefaut(ConstanteSocobis.MAGASIN);
        pi.getFormu().getChamp("idMagasin").setLibelle("Magasin");
        pi.getFormu().getChamp("reference").setLibelle("R&eacute;f&eacute;rence");
        pi.getFormu().getChamp("idReservation").setVisible(false);

        pi.getFormufle().getChamp("idProduit_0").setLibelle("Produit");
        pi.getFormufle().getChamp("pu_0").setLibelle("PU Brut");
        pi.getFormufle().getChamp("iddemandeprixfille_0").setLibelle("Demande de prix");
        pi.getFormufle().getChamp("compte_0").setLibelle("Compte");
        //pi.getFormufle().getChamp("idDevise_0").setLibelle("Devise");
        pi.getFormufle().getChamp("tva_0").setLibelle("TVA (En %)");
        //pi.getFormufle().getChamp("remise_0").setLibelle("Remise");
        pi.getFormufle().getChamp("designation_0").setLibelle("D&eacute;signation");
        pi.getFormufle().getChamp("qte_0").setLibelle("Quantit&eacute;");
        //pi.getFormufle().getChamp("unite_0").setVisible(false);
        pi.getFormufle().getChamp("unitelib_0").setLibelle("Unit&eacute;");
        pi.getFormufle().getChamp("remise_0").setLibelle("Remise (en %)");
        pi.getFormufle().getChamp("ristourne_0").setLibelle("Ristourne (en %)");
        pi.getFormufle().getChamp("punet_0").setLibelle("PU Net");
        pi.getFormufle().getChamp("montantht_0").setLibelle("Montant HT");
        pi.getFormufle().getChamp("montantttc_0").setLibelle("Montant TTC");
        pi.getFormufle().getChamp("calorie_0").setLibelle("Poids en Kg");
        pi.getFormufle().getChampMulitple("datedebut").setVisible(false);

        pi.getFormufle().getChampMulitple("idDevise").setVisible(false);
        pi.getFormu().getChamp("idDevise").setLibelle("Devise");
        pi.getFormu().getChamp("idDevise").setDefaut(ConstanteSocobis.Devise);

        //pi.getFormufle().getChampMulitple("remise").setVisible(false);
        //affichage.Champ.setPageAppelComplete(pi.getFormufle().getChampFille("idProduit"),"produits.IngredientVente","id","AS_INGREDIENT_VENTE_LIB","prixunitaire;compte_vente;libelle;idunite","pu;compte;designation;unite");
        affichage.Champ.setPageAppelCompleteAWhere(pi.getFormufle().getChampFille("idProduit"),"produits.IngredientVente","id","AS_INGREDIENT_VENTE_LIB","prixunitaire;compte_vente;libelle;idunite;idunitelib;tva;calorie","pu;compte;designation;unite;uniteLib;tva;calorie"," AND 1>2");
        double tva = 0.0;
        String acte=request.getParameter("acte");
        String idC = request.getParameter("id");




        for (int i = 0; i < pi.getNombreLigne(); i++) {
            pi.getFormufle().getChamp("qte_"+i).setAutre("onChange='calculerMontant("+i+")'");
            pi.getFormufle().getChamp("remise_"+i).setAutre("onChange='calculerMontant("+i+")'");
            pi.getFormufle().getChamp("ristourne_"+i).setAutre("onChange='calculerMontant("+i+")'");
            pi.getFormufle().getChamp("tva_"+i).setAutre("onChange='calculerMontant("+i+")'");
            pi.getFormufle().getChamp("id_"+i).setAutre("readonly");
            //affichage.Champ.setPageAppelComplete(pi.getFormufle().getChampFille("idproduit"),"annexe.ProduitLib","id","PRODUIT_LIB","id;puVente;desce","id;pu;designation");
            pi.getFormufle().getChamp("iddemandeprixfille_"+i).setAutre("readonly");
            //pi.getFormufle().getChamp("unite_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("unitelib_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("compte_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("punet_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("montantht_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("montantttc_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("calorie_"+i).setAutre("readonly");
//            pi.getFormufle().getChamp("pu_"+i).setAutre("readonly");
            pi.getFormufle().getChamp("tva_"+i).setDefaut(tva+"");
            //pi.getFormufle().getChamp("iddevise_"+i).setAutre("readonly");
            //pi.getFormufle().getChamp("tva_"+i).setAutre("readonly");
        }
//        idCommande = request.getParameter("idCommande");
        if (idCommande != null && !idCommande.trim().isEmpty()) {
            Commande commande = new Commande();
            commande.setId(idCommande);
            prerempli = commande.createProforma();
            CommandeFIlleCpl[] commandeFilles = commande.getFilleCommandeLib();
            if (commandeFilles != null && commandeFilles.length > 0) {
                ProformaDetailsLib[] lignes = new ProformaDetailsLib[commandeFilles.length];
                for (int i = 0; i < commandeFilles.length; i++) {
                    CommandeFIlleCpl detail = commandeFilles[i];
                    lignes[i] = detail.createProformaDetailsLib();
                }
                pi.setDefautFille(lignes);
            }
            pi.getFormu().setDefaut(prerempli);
            pi.getFormu().getChamp("idMagasin").setDefaut(prerempli.getIdMagasin());

        }

        if ( prerempli!=null ||  ((request.getParameter("onchanged") != null && request.getParameter("onchanged").equals("true"))||(acte!=null&&acte.compareToIgnoreCase("update")==0&&idC!=null&&idC.compareToIgnoreCase("")!=0))){
            String idmagasin = request.getParameter("idMagasin");
            if(request.getParameter("idMagasin") != null && !request.getParameter("idMagasin").isEmpty()){
                session.setAttribute("idMagasin", request.getParameter("idMagasin"));
            }
            if(idmagasin == null || idmagasin.isEmpty()){
                idmagasin = (String) session.getAttribute("idMagasin");
            }
            Client c = null;
            if(request.getParameter("idClientlibelle")!=null && request.getParameter("idClientlibelle").split(" - ").length>0){
                String idclient = request.getParameter("idClientlibelle").split(" - ")[0];
                c = (Client)new Client().getById(idclient,"client",null);
                Calendar cal = Calendar.getInstance();
                cal.setTime(c.getDatecarte());
                cal.add(Calendar.YEAR, 1);
                Date dateCartePlusUnAn = cal.getTime();
                Date dateAujourdhui = new Date();
                carteExpiree = dateAujourdhui.compareTo(dateCartePlusUnAn) >= 0;
                tva = c.getTaxe();
                session.setAttribute("idclient", idclient);
            }else{
                c = (Client)new Client().getById((String) session.getAttribute("idclient"),"client",null);
                tva = c.getTaxe();
            }
            String awhere = " AND IDTYPECLIENT='"+c.getIdTypeClient()+"'";
            if(idmagasin != null){
                TarifIngredients rechercheTarif = new TarifIngredients();
                rechercheTarif.setNomTable("TARIF_INGREDIENTS_MAX");
                rechercheTarif.setIdMagasin(idmagasin);
                TarifIngredients[] tarifs = (TarifIngredients[]) CGenUtil.rechercher(rechercheTarif, null, null, null, "");
                if (tarifs.length > 0){
                    //awhere += " AND TARIFMAGASIN='"+idmagasin+"'";
                }
                else {
                    //awhere += " AND TARIFMAGASIN IS NULL";
                }
            }
            else {
                //awhere += " AND TARIFMAGASIN IS NULL";
            }
            affichage.Champ.setPageAppelCompleteAWhere(pi.getFormufle().getChampFille("idProduit"),"produits.IngreventVenteTarif","id","AS_INGREDIENT_VENTE_LIB","prixunitaire;compte_vente;libelle;idunite;idunitelib;tva;calorie","pu;compte;designation;unite;uniteLib;tva;calorie", awhere);
        }else{
            session.removeAttribute("idMagasin");
            session.removeAttribute("idclient");
        }

        affichage.Champ.setVisible(pi.getFormufle().getChampFille("id"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("idDemandePrixFille"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("idProforma"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("idOrigine"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("puAchat"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("puVente"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("puRevient"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("tauxdechange"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("compte"),false);
        affichage.Champ.setVisible(pi.getFormufle().getChampFille("unite"),false);
        //affichage.Champ.setVisible(pi.getFormufle().getChampFille("tva"),false);
        //affichage.Champ.setVisible(pi.getFormufle().getChampFille("remise"),false);

        String[] colOrdre = {"idProduit","calorie","designation","unitelib", "qte", "pu","remise", "ristourne","punet","tva","montantht","montantttc","unite", "iddemandeprixfille", "compte","datedebut"};
        pi.getFormufle().setColOrdre(colOrdre);

        pi.preparerDataFormu();

        //Variables de navigation
        String classeMere = "proforma.Proforma";
        String classeFille = "proforma.ProformaDetails";
        String butApresPost = "vente/proforma/proforma-fiche.jsp";
        String colonneMere = "idProforma";

        String[] ordre = {"daty"};
        pi.getFormu().setOrdre(ordre);

        pi.getFormu().makeHtmlInsertTabIndex();
        pi.getFormufle().makeHtmlInsertTableauIndex();
        String titre = "Saisie d'un Proforma";
        if(request.getParameter("acte")!=null){
            titre = "Modification d'un Proforma";
        }
%>
<div class="content-wrapper">
    <h1><%= titre %></h1>
    <form id="formId"  class='container' action="<%=pi.getLien()%>?but=apresMultiple.jsp" method="post" >

        <%
            out.println(pi.getFormu().getHtmlInsert());
        %>
        <div class="col-md-12 cardradius">
            <h3 class="fontinter" style="background: white;padding: 16px;margin-top: 10px;border-radius: 16px;" >Total  : <span id="montanttotal">0</span><span id="deviseLibelle"> Ar</span></h3>
        </div>
        <div id="butfillejsp">
            <%
                out.println(pi.getFormufle().getHtmlTableauInsert());
            %>
        </div>
        <input name="acte" type="hidden" id="nature" value="insert">
        <input name="bute" type="hidden" id="bute" value="<%= butApresPost %>">
        <input name="classe" type="hidden" id="classe" value="<%= classeMere %>">
        <input name="classefille" type="hidden" id="classefille" value="<%= classeFille %>">
        <input name="nombreLigne" type="hidden" id="nombreLigne" value="<%= nombreLigne %>">
        <input name="colonneMere" type="hidden" id="colonneMere" value="<%= colonneMere %>">
    </form>

</div>
<script>
    $(document).ready(function () {
        var lignes = <%=pi.getNombreLigne()%>
        for (let i = 0; i < lignes; i++) {
            calculerMontant(i);
        }
    });

</script>
<script>
    function formatNumber(number) {
        return number.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }
    function calculerMontant(indice) {

        // Valeurs
        var pu = parseFloat(document.getElementById('pu_' + indice).value.replace(/\s/g, '')) || 0;
        var qte = parseFloat(document.getElementById('qte_' + indice).value.replace(/\s/g, '')) || 0;
        var remise = parseFloat(document.getElementById('remise_' + indice).value.replace(/\s/g, '')) || 0;
        var ristourne = parseFloat(document.getElementById('ristourne_' + indice).value.replace(/\s/g, '')) || 0;
        var tva = parseFloat(document.getElementById('tva_' + indice).value.replace(/\s/g, '')) || 0;
        var Poids = parseFloat(document.getElementById('calorie_' + indice).value.replace(/\s/g, '')) || 0;
        var fraislivraison = parseFloat(document.getElementById('fraislivraison').value.replace(/\s/g, '')) || 0;

        // 1️⃣ Montant HT brut
        var montantHT = pu * qte;

        var montantFraisLivraison = fraislivraison * Poids * qte;



        // 2️⃣ Remise
        var montantRemise = montantHT * remise / 100;
        var htApresRemise = montantHT - montantRemise;

        // 3️⃣ Ristourne (SUR montant remisé)
        var montantRistourne = htApresRemise * ristourne / 100;
        var htFinal = htApresRemise - montantRistourne - montantFraisLivraison ;

        // 4️⃣ TVA (si utilisée)
        var montantTVA = htFinal * tva / 100;
        var montantTTC = htFinal + montantTVA;

        // PU net informatif
        var puNet = htFinal / qte;

        // Mise à jour UI
        document.getElementById('punet_' + indice).value = formatNumber(puNet);
        document.getElementById('montantht_' + indice).value = formatNumber(htFinal);
        document.getElementById('montantttc_' + indice).value = formatNumber(montantTTC);

        // Total général
        var total = 0;
        $('input[id^="montantttc_"]').each(function () {
            var v = parseFloat($(this).val().replace(/\s/g, ''));
            if (!isNaN(v)) total += v;
        });

        $("#montanttotal").html(
            Intl.NumberFormat('fr-FR', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
            }).format(total)
        );
    }

    function recalculerTousLesMontants() {
        var nombreLigne = parseInt(document.getElementById('nombreLigne').value, 10);
        for (var i = 0; i < nombreLigne; i++) {
            var idProduit = document.getElementById('idProduit_' + i);
            if (idProduit && idProduit.value !== '') {
                calculerMontant(i);
            }
        }
    }

</script>
<%
} catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript">
    alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>

<script language="JavaScript">
document.querySelectorAll("input[id^='designation_']").forEach(function(input) {
    input.style.width = "350px";
    input.style.minWidth = "350px";
});
</script>


