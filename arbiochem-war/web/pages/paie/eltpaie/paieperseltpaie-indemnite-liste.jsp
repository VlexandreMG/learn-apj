<%@page import="paie.elementpaie.PaiePersonnelElementpaie"%>
<%@page import="affichage.Liste"%>
<%@page import="utilitaire.ConstanteEtat"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="affichage.Champ" %>

<%
    try{
        PaiePersonnelElementpaie dr = new PaiePersonnelElementpaie();
        String nomTable = "PAIE_PERS_ELTPAIE_LIB_IND";

        if (request.getParameter("table") != null && request.getParameter("table").compareToIgnoreCase("") != 0) {
            nomTable = request.getParameter("table");
        }


        dr.setNomTable(nomTable);

        String listeCrt[] = { "matricule","moisregularisation","gain","anneeregularisation"};
        String listeInt[] = { "gain","moisregularisation" ,"anneeregularisation"};
        String libEntete[] = { "id", "matricule", "idpersonnel", "moisregularisationLib", "anneeregularisation", "gain","etatlib" };
        PageRecherche pr = new PageRecherche(dr, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        pr.setApres("paie/eltpaie/paieperseltpaie-indemnite-liste.jsp");

        boolean etatCree = false;

        if(request.getParameter("etat") != null && request.getParameter("etat").compareToIgnoreCase("") != 0) {
            pr.setAWhere(pr.getAWhere()+" and etat"+String.valueOf(request.getParameter("etat")));
            if(request.getParameter("etat").equalsIgnoreCase("=1")) etatCree = true;
        }

        affichage.Champ[] liste = new Champ[2];
        Liste mois1 = new Liste("moisregularisation1");
        mois1.makeListeMois();
        liste[0] = mois1;
        Liste mois2 = new Liste("moisregularisation2");
        mois2.makeListeMois();
        liste[1] = mois2;
        pr.getFormu().changerEnChamp(liste);
        pr.getFormu().getChamp("moisregularisation1").setLibelle("Mois de r&eacute;gularisation (min)");
        pr.getFormu().getChamp("moisregularisation2").setLibelle("Mois de r&eacute;gularisation (max)");
        pr.getFormu().getChamp("gain1").setLibelle("Montant Min");
        pr.getFormu().getChamp("gain2").setLibelle("Montant Max");
        pr.getFormu().getChamp("anneeregularisation1").setLibelle("Ann&eacute;e de r&eacute;gularisation (min)");
        pr.getFormu().getChamp("anneeregularisation2").setLibelle("Ann&eacute;e de r&eacute;gularisation (max)");

        pr.getFormu().setAnotherButton(
            "                <a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=paie/eltpaie/paieperseltpaie-indemnite-saisie.jsp&currentMenu=MD00021110005\">\n" +
            "                    <i class=\"material-symbols-rounded\">add</i>Saisir un &eacute;l&eacute;ments de paie pour les indemnit&eacute;s fixes" +
            "                </a>"
        );
        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);

        String formAction = pr.getLien() + "?but=" +
                (etatCree
                        ? "paie/fonction/apresPaiePersonnelElement-indemnite.jsp&acte=valider"
                        : pr.getApres()
                );

        String[] etatVal = {"!= 100", "=1", "=11"};
        String[] etatAff = {"Tous", "Cr&eacute;&eacute;(s)", "Vis&eacute;(s)"};
%>

<div class="content-wrapper">
    <section class="content-header">
        <h1>Liste des &eacute;l&eacute;ments de paie sur les indemnit&eacute;s fixes</h1>
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=paie/eltpaie/paieperseltpaie-indemnite-liste.jsp" method="post" name="incident" id="incident">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <div class="row col-md-12 nopadding">
                <div class="col-md-2 nopadding">
                    &Eacute;tat :
                    <select name="etat" class="champ form-control" id="etat" onchange="document.incident.submit()">
                        <%
                            String currentEtat = request.getParameter("etat");
                            if (currentEtat == null || currentEtat.equals("")) {
                                currentEtat = "!= 100";
                            }
                            for( int i = 0; i < etatAff.length; i++ ){ %>
                        <% if(etatVal[i].equalsIgnoreCase(currentEtat)) {%>
                        <option value="<%= etatVal[i] %>" selected> <%= etatAff[i] %> </option>
                        <% } else { %>
                        <option value="<%= etatVal[i] %>"> <%= etatAff[i] %> </option>
                        <% } %>
                        <%    }
                        %>
                    </select>
                </div>
            </div>
        </form>

        <%
            String lienTableau[] = {pr.getLien() + "?but=paie/eltpaie/paieperseltpaie-indemnite-fiche.jsp"};
            String colonneLien[] = {"id"};
            pr.getTableau().setLien(lienTableau);
            pr.getTableau().setColonneLien(colonneLien);
            out.println(pr.getTableauRecap().getHtml());
        %>
        <br>
        <%
            String libEnteteAffiche[] = {"id", "Matricule", "Personnel", "Mois de r&eacute;gularisation", "Ann&eacute;e de r&eacute;gularisation", "Gain","&Eacute;tat"};
            pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        %>
        <form action="<%=formAction%>" method="post">
            <%
                if(etatCree){
                    pr.getTableau().setNameBoutton("Valider");
                    out.println(pr.getTableau().getHtmlWithCheckbox());
                }
                else{
                    out.println(pr.getTableau().getHtml());
                }
            %>
        </form>
        <%
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<% } catch(Exception e) { e.printStackTrace();}%>
