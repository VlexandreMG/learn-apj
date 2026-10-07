<%-- 
    Document   : vente-liste
    Created on : 25 mars 2024, 09:57:03
    Author     : Angela
--%>

<%@page import="faturefournisseur.FactureFournisseurCpl"%>
<%@page import="utilitaire.Utilitaire"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="utils.ConstanteSocobis" %>

<% try{
    FactureFournisseurCpl bc = new FactureFournisseurCpl();
    String[] etatVal = {"","1","11", "0"};
    String[] etatAff = {"Tous","Cr&eacute;&eacute;e(s)", "Vis&eacute;e(s)", "Annul&eacute;e(s)"};
//    bc.setNomTable("FACTUREFOURNISSEURCPL_TOUS_FAE");
    bc.setNomTable("FACTUREFOURNISSEURCPL");
    if (request.getParameter("devise") != null && request.getParameter("devise").compareToIgnoreCase("") != 0) {
        bc.setNomTable(request.getParameter("devise"));
    } else {
        bc.setNomTable("FACTUREFOURNISSEURCPL");
    }
    String listeCrt[] = {"id", "designation","idFournisseurLib","idBc", "daty", "dateEcheancePaiement", "montantttc", "montantpaye", "montantreste"};
    String listeInt[] = {"daty", "dateEcheancePaiement", "montantttc", "montantpaye", "montantreste"};
    String libEntete[] = {"id", "idBc","designation","idFournisseurLib","idDevise","daty","typefacture","montantttc","montantpaye", "montantreste", "dateEcheancePaiement", "idServiceLib","etatlib"};
    String libEnteteAffiche[] = {"ID", "ID Bon de commande","D&eacute;signation","Fournisseur","devise","Date","Type de facture","Montant TTC","Montant pay&eacute;","Montant Restant", "date d'&Eacute;ch&eacute;ance de Paiement", "D&eacute;partement","&Eacute;tat"};
    PageRecherche pr = new PageRecherche(bc, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    if(request.getParameter("etat")!=null && request.getParameter("etat").compareToIgnoreCase("")!=0) {
        pr.setAWhere(" and etat=" + request.getParameter("etat"));
    }
    String paiement = "tous";
    if(request.getParameter("paiement")!=null && request.getParameter("paiement").compareToIgnoreCase("")!=0) {
        if(request.getParameter("paiement").compareToIgnoreCase("payer")==0){
            pr.setAWhere(pr.getAWhere()+" AND MONTANTRESTE=0 ");
            paiement = "payer";
        }else if(request.getParameter("paiement").compareToIgnoreCase("impayer")==0){
            pr.setAWhere(pr.getAWhere()+" AND MONTANTRESTE>0 ");
            paiement = "impayer";
        }
    }
        //pr.setAWhere(pr.getAWhere()+" AND idtypefacture = '" + ConstanteSocobis.TYPE_FACTURE + "' ");

    pr.setTitre("Liste des factures fournisseurs");
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("facturefournisseur/facturefournisseur-liste.jsp");
    String[] colSomme = { "montantttc", "montantpaye", "montantreste" };
    pr.getFormu().getChamp("id").setLibelle("ID");
    pr.getFormu().getChamp("idFournisseurLib").setLibelle("Fournisseur");
    pr.getFormu().getChamp("daty1").setLibelle("Date Min");
    pr.getFormu().getChamp("daty1").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("daty2").setLibelle("Date Max");
    pr.getFormu().getChamp("daty2").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("IdBc").setLibelle("ID Bon de commande");
    pr.getFormu().getChamp("montantttc1").setLibelle("Motant TTC Min");
    pr.getFormu().getChamp("montantttc2").setLibelle("Montant TTC Max");
    pr.getFormu().getChamp("montantreste1").setLibelle("Montant Restant Min");
    pr.getFormu().getChamp("montantreste2").setLibelle("Montant Restant Max");
    pr.getFormu().getChamp("montantpaye1").setLibelle("Montant Pay&eacute; Min");
    pr.getFormu().getChamp("montantpaye2").setLibelle("Montant Pay&eacute; Max");

    pr.getFormu().getChamp("dateEcheancePaiement1").setLibelle("Date d'&Eacute;ch&eacute;ance de paiement Min");
    pr.getFormu().getChamp("dateEcheancePaiement2").setLibelle("Date d'&Eacute;ch&eacute;ance de paiement Max");
//    pr.getFormu().getChamp("dateEcheancePaiement1").setDefaut(utilitaire.Utilitaire.dateDuJour());
//    pr.getFormu().getChamp("dateEcheancePaiement2").setDefaut(utilitaire.Utilitaire.dateDuJour());
    pr.getFormu().getChamp("designation").setLibelle("D&eacute;signation");

    pr.creerObjetPage(libEntete, colSomme);

    //Definition des lienTableau et des colonnes de lien

    Map<String,String> lienTab=new HashMap();
    lienTab.put("Modifier",pr.getLien() + "?but=facturefournisseur/facturefournisseur-modif.jsp");
    lienTab.put("Livrer",pr.getLien() + "?but=facturefournisseur/apresLivraisonFacture.jsp&id="+pr.getFormu().getChamp("id").getValeur() +"");
    pr.getTableau().setLienClicDroite(lienTab);

    String lienTableau[] = {pr.getLien() + "?but=facturefournisseur/facturefournisseur-fiche.jsp", pr.getLien() + "?but=bondecommande/bondecommande-fiche.jsp"};
    String colonneLien[] = {"id","idBc"};
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
    pr.getTableau().setLienFille("facturefournisseur/inc/facture-fournisseur-liste-detail.jsp&id=");
    pr.getFormu().setAnotherButton("" +
            "<a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=facturefournisseur/facturefournisseur-saisie.jsp\">\n" +
            "                    <i class=\"material-symbols-rounded\">add</i>Saisie d'une facture fournisseur</a>"
    );

    String currentDevise = request.getParameter("devise");
    if (currentDevise == null) currentDevise = "";
%>
<script>
    function changerDesignation() {
        document.vente.submit();
    }
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name="vente" id="vente">
            <%
                String libelles[]={" ","Nombre", "Somme des montants TTC", "Somme des Montants Pay&eacute;s", "Somme Montants Restants"};
                pr.getTableauRecap().setLibeEntete(libelles);
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <div class="row col-md-12 nopadding m-0" style="margin-top: 12px">
                <div class="col-md-2 nopadding">
                    <label class="input-label" for="devise">Devise</label>
                    <select name="devise" class="champ form-control" id="devise" onchange="changerDesignation()">

                        <%-- Option TOUS --%>
                        <option value="FACTUREFOURNISSEURCPL_TOUS_FAE"
                                <%= (currentDevise.equals("FACTUREFOURNISSEURCPL_TOUS_FAE") || currentDevise.equalsIgnoreCase("FACTUREFOURNISSEURCPL_TOUS_FAE")) ? "selected" : "" %>>
                            Tous
                        </option>

                        <%-- Option AR --%>
                        <option value="FACTUREFOURNISSEURCPL_MGA_FAE"
                                <%= (currentDevise.equals("FACTUREFOURNISSEURCPL_MGA_FAE") || currentDevise.equalsIgnoreCase("FACTUREFOURNISSEURCPL_MGA_FAE")) ? "selected" : "" %>>
                            AR
                        </option>

                        <%-- Option EUR --%>
                        <option value="FACTUREFOURNISSEURCPL_EUR_FAE"
                                <%= (currentDevise.equals("FACTUREFOURNISSEURCPL_EUR_FAE") || currentDevise.equalsIgnoreCase("FACTUREFOURNISSEURCPL_EUR_FAE")) ? "selected" : "" %>>
                            EUR
                        </option>

                        <%-- Option USD --%>
                        <option value="FACTUREFOURNISSEURCPL_USD_FAE"
                                <%= (currentDevise.equals("FACTUREFOURNISSEURCPL_USD_FAE") || currentDevise.equalsIgnoreCase("FACTUREFOURNISSEURCPL_USD_FAE")) ? "selected" : "" %>>
                            USD
                        </option>

                    </select>
                </div>
                <div class="col-md-2">
                    <label for="etat" class="input-label">&Eacute;tat</label>
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
                    <label class="input-label" for="paiement">Paiement</label>
                    <select name="paiement" class="champ form-control" id="paiement" onchange="changerDesignation()">
                        <%if(paiement.compareToIgnoreCase("tous")==0){%>
                        <option value="tous" selected> Tous </option>
                        <%}else{%>
                        <option value="tous"> Tous </option>
                        <%}%>

                        <%if(paiement.compareToIgnoreCase("payer")==0){%>
                        <option value="payer" selected> Pay&eacute;e(s) </option>
                        <%}else{%>
                        <option value="payer"> Pay&eacute;e(s) </option>
                        <%}%>

                        <%if(paiement.compareToIgnoreCase("impayer")==0){%>
                        <option value="impayer" selected> Impay&eacute;e(s) </option>
                        <%}else{%>
                        <option value="impayer"> Impay&eacute;e(s) </option>
                        <%}%>



                    </select>
                </div>
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




