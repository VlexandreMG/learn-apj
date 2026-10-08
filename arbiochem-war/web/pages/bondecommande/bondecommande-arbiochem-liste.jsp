<%--
  ============================================================================
  bondecommande-arbiochem-liste.jsp : LISTE DES BONS DE COMMANDE FOURNISSEUR
  ============================================================================

  ENTREES (ce que la page reçoit)
    - Paramètres HTTP (envoyés par le formulaire de filtres) :
        id, daty1, daty2, designation, fournisseurlib, modepaiementlib,
        reference, refproforma  -> critères de recherche
        etat                    -> "", 1 (créée), 11 (visée), 0 (annulée)
        livraison               -> "", 0 (non traité), 1 (partiel), 2 (traité), 3 (surplus)
        numéro de page          -> pagination
    - Session : "u" (utilisateur connecté) et "lien" (URL de base : module.jsp)

  SORTIES (ce que la page affiche / où elle mène)
    - Un formulaire de filtres + un tableau de 50 bons par page + la pagination
    - Clic sur l'id            -> bondecommande/bondecommande-arbiochem-fiche.jsp
    - Bouton "Saisir un bon"   -> bondecommande/bondecommande-arbiochem-saisie.jsp
    - Clic droit "modifier"    -> bondecommande/bondecommande-modif.jsp
    - Ligne dépliable          -> bondecommande/inc/bondecommande-liste-detail.jsp
    - Les filtres re-soumettent la page elle-même (même fichier, nouveaux paramètres)

  SOURCE DES DONNEES : la vue Oracle As_BonDeCommande_MERETRAITE
    (le BC + la colonne "traite" calculée à partir des réceptions)
--%>
<%@page import="faturefournisseur.As_BonDeCommande"%>
<%@page import="affichage.PageRecherche"%>
<%@page import="bean.TypeObjet"%>
<%@page import="affichage.Liste"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="faturefournisseur.As_BonDeCommandeCpl" %>

<% try {
    // 1. Valeurs et libellés des deux listes déroulantes "Etat" et "Livraison"
    String[] etatVal = {"","1","11","0"};
    String[] etatAff = {"Tous","Cr&eacute;&eacute;e(s)", "Vis&eacute;e(s)","Annul&eacute;e(s)"};

    String[] etatValLiv = {"","0","1","2","3"};
    String[] etatAffLiv = {"Tous","Non Trait&eacute;","Trait&eacute; partiellement", "Trait&eacute;","Trait&eacute; avec surplus"};

    // 2. Objet modèle : on interroge la vue qui contient aussi le statut de livraison
    As_BonDeCommandeCpl f = new As_BonDeCommandeCpl();
    f.setNomTable("As_BonDeCommande_MERETRAITE ");

    // 3. Champs de recherche, champ en intervalle (date), colonnes du tableau
    String listeCrt[] = {"id","daty","designation","fournisseurlib","modepaiementlib","reference","refproforma"};
    String listeInt[] = {"daty"};
    String libEntete[] = {"id","daty","reference","designation","modepaiementlib","fournisseurlib", "idServiceLib","refproforma","etatlib","traite"};

    // 4. Page de recherche du framework (formulaire + tableau + pagination)
    PageRecherche pr = new PageRecherche(f, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setTitre("Liste des bons de commande fournisseur");

    // 5. Filtres "etat" et "livraison" (hors framework) -> condition SQL ajoutée à la recherche
    //    Attention : les valeurs sont collées telles quelles dans le SQL (pas de requête préparée)
    String awhere = "";
    if(request.getParameter("etat")!=null && request.getParameter("etat").compareToIgnoreCase("")!=0) {
        awhere += " and etat=" + request.getParameter("etat");
    }
    if(request.getParameter("livraison")!=null && request.getParameter("livraison").compareToIgnoreCase("")!=0) {
        awhere += " and idtraite= '"+request.getParameter("livraison")+"'";
    }
    pr.setAWhere(awhere);

    // 6. Utilisateur, URL de base et page rechargée par le formulaire
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("bondecommande/bondecommande-arbiochem-liste.jsp");

    // 7. Libellés des champs du formulaire
    pr.getFormu().getChamp("reference").setLibelle("R&eacute;f&eacute;rence"); 
    pr.getFormu().getChamp("designation").setLibelle("D&eacute;signation"); 
    pr.getFormu().getChamp("fournisseurlib").setLibelle("Fournisseur");
    pr.getFormu().getChamp("refproforma").setLibelle("R&eacute;f&eacute;rence proforma");

    // 8. "Mode de paiement" devient une liste déroulante (table MODEPAIEMENT)
    TypeObjet modePaiement= new TypeObjet();
    modePaiement.setNomTable("MODEPAIEMENT");
    Liste[] liste = new Liste[1];
    liste[0] = new Liste("modepaiementlib", modePaiement, "val", "val");
    pr.getFormu().changerEnChamp(liste);
    pr.getFormu().getChamp("modepaiementlib").setLibelle("Mode de paiement");
//    pr.getFormu().getChamp("reference").setLibelle("R&eacute;f&eacute;rence");

    // 9. Intervalle de dates : daty1 (min) et daty2 (max), pré-remplis avec la date du jour
    pr.getFormu().getChamp("daty1").setLibelle("Date min");
    pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("daty2").setLibelle("Date max");
    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());

    // 10. Pas de total, 50 lignes par page, puis exécution de la requête
    String[] colSomme = null;
    pr.setNpp(50);
    pr.creerObjetPage(libEntete, colSomme);

    // 11. Clic droit sur une ligne : "modifier"
    Map<String,String> lienTab=new HashMap(); 
        lienTab.put("modifier",pr.getLien() + "?but=bondecommande/bondecommande-modif.jsp"); 
    pr.getTableau().setLienClicDroite(lienTab);

    // 12. La colonne "id" devient un lien vers la fiche
    String lienTableau[] = {pr.getLien() + "?but=bondecommande/bondecommande-arbiochem-fiche.jsp"};
    String colonneLien[] = {"id"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);

    // 13. Titres des colonnes (même ordre que libEntete)
    String libEnteteAffiche[] = {"Id","Date","R&eacute;f&eacute;rence","D&eacute;signation","Mode de paiement","Fournisseur", "D&eacute;partement","R&eacute;f&eacute;rence proforma","&Eacute;tat","Livraison"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);

    // 14. Bouton "Saisir un bon de commande" dans le formulaire
    pr.getFormu().setAnotherButton("" +
            "<a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=bondecommande/bondecommande-arbiochem-saisie.jsp\">\n" +
            "                    <i class=\"material-symbols-rounded\">add</i> Saisir un bon de commande</a>"
    );

    // 15. Ligne dépliable : détail du bon (l'id est ajouté à la fin du lien)
    pr.getTableau().setLienFille("bondecommande/inc/bondecommande-liste-detail.jsp&id=");
%>
<%-- Envoie le formulaire de filtres quand on change "Etat" ou "Livraison" --%>
<script>
    function changerDesignation() {
        document.filtre.submit();
    }
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <%-- Formulaire de filtres : renvoie vers cette même page (POST) --%>
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post"  name="filtre">
            <%-- Champs de recherche générés par le framework + bouton "Saisir" --%>
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <%-- Filtres ajoutés à la main : "Etat" puis "Livraison" (lus en haut dans awhere) --%>
            <div class="row col-md-12" style="margin-top: 12px;">
                    <div class="col-md-2 nopadding" style="width: 271px" >
                        <label class="input-label" for="etat">&Eacute;tat :</label>
                        <select name="etat" class="champ form-control" id="etat" onchange="changerDesignation()">
                            <%
                                for( int i = 0; i < etatAff.length; i++ ){ %>
                            <% if(request.getParameter("etat") !=null && request.getParameter("etat").compareToIgnoreCase(etatVal[i]) == 0) {%>
                            <option value="<%= etatVal[i] %>" selected> <%= etatAff[i] %> </option>
                            <% } else { %>
                            <option value="<%= etatVal[i] %>"> <%= etatAff[i] %> </option>
                            <% } %>
                            <%    }
                            %>
                        </select>
                    </div>
                    <div class="col-md-2 nopadding">
                        <label class="input-label" for="livraison">Livraison  :</label>
                        <select name="livraison" class="champ form-control" id="livraison" onchange="changerDesignation()">
                            <%
                                for( int i = 0; i < etatAffLiv.length; i++ ){ %>
                            <% if(request.getParameter("livraison") !=null && request.getParameter("livraison").compareToIgnoreCase(etatValLiv[i]) == 0) {%>
                            <option value="<%= etatValLiv[i] %>" selected> <%= etatAffLiv[i] %> </option>
                            <% } else { %>
                            <option value="<%= etatValLiv[i] %>"> <%= etatAffLiv[i] %> </option>
                            <% } %>
                            <%    }
                            %>
                        </select>
                    </div>
        </div>
        </form>
        <%-- Récapitulatif (vide ici : aucune colonne à totaliser) --%>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        <br>
        <%-- Tableau des bons de commande + barre de pagination --%>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%-- En cas d'erreur : trace dans server.log seulement, rien n'est affiché à l'écran --%>
    <%
    }catch(Exception e){

        e.printStackTrace();
    }
%>
