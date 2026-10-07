<%@page import="affichage.Liste"%>
<%@page import="bean.TypeObjet"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="paie.avance.Avance" %>
<%@ page import="utils.ConstantePaie" %>
<%@ page import="utilitaire.Utilitaire" %>

<% 
    try{
    Avance lv = new Avance();
    lv.setNomTable("AVANCE_LIB2");

    String idType = "";

      
    String listeCrt[] = {"id", "idpersonnel","matricule","mois"};
    String listeInt[] = {};
    String libEntete[] = {"id","dateSaisie","matricule", "idpersonnel", "montant","moisLib","etatlib"};

    PageRecherche pr = new PageRecherche(lv, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("etat") != null && request.getParameter("etat").compareToIgnoreCase("") != 0) {
        pr.setAWhere(pr.getAWhere()+" and etat"+request.getParameter("etat"));
    }

    if(request.getParameter("idType") != null && request.getParameter("idType").compareToIgnoreCase("") != 0) {
        pr.setAWhere(pr.getAWhere()+" and idTypeAvance = '" + request.getParameter("idType") + "'");
        idType = request.getParameter("idType");
    }

    Liste[] liste = new Liste[1];
    liste[0] = new Liste("mois");
    liste[0].makeListeMois();
//    liste[0].setDefaut(Utilitaire.getMoisEnCours()+"");
    pr.getFormu().changerEnChamp(liste);


    pr.setTitre("Liste des avances");

//    pr.getFormu().getChamp("nbremboursement").setLibelle("nombre de remboursement");
//    pr.getFormu().getChamp("montant1").setLibelle("Montant min");
//    pr.getFormu().getChamp("montant2").setLibelle("Montant max");
    pr.getFormu().getChamp("idpersonnel").setLibelle("Personnel");
//    pr.getFormu().getChamp("etat").setVisible(false);
//    pr.getFormu().getChamp("interet").setLibelle("Int&eacute;r&ecirc;t (%)");


        pr.getFormu().getChamp("mois").setLibelle("Mois");
        pr.getFormu().getChamp("mois").setDefaut(Utilitaire.getMoisEnCours()+1+"");


        pr.setApres("paie/avance/avance-15-liste.jsp&idType=PRU0447");
        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);
       String[] lienTableau = {pr.getLien() + "?but=paie/avance/avance-fiche.jsp"};
       String colonneLien[] = {"id"};
       pr.getTableau().setLien(lienTableau);
       pr.getTableau().setColonneLien(colonneLien);

        String currentEtat = request.getParameter("etat");
        if(currentEtat == null || currentEtat.equals("")) {
            currentEtat = "!= 100";
        }

        boolean etatCree = "=1".equals(currentEtat);

        String libEnteteAffiche[] =  {"ID", "Date de saisie","Matricule","Personnel", "Montant(Ar)","Mois","&Eacute;tat"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);

        if (ConstantePaie.idAvanceExceptionnelle.equals(idType))
        {
            pr.getFormu().setAnotherButton(
                    "                <a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=paie/avance/avance-saisie.jsp&currentMenu=AMS007\">\n" +
                            "                    <i class=\"material-symbols-rounded\">add</i>Saisir une avance exceptionnelle " +
                            "                </a>"
            );
        } else if (ConstantePaie.idAvanceSurSalaire.equals(idType))
        {
            pr.getFormu().setAnotherButton(
                    "                <a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=paie/avance/avance-quinzaine-saisie.jsp&currentMenu=AMS007\">\n" +
                            "                    <i class=\"material-symbols-rounded\">add</i>Saisir un avance sur salaire" +
                            "                </a>"
            );
        }

        String[] etatVal = {"!= 100", "=1", "=11"};
        String[] etatAff = {"Tous", "Cr&eacute;&eacute;(s)", "Vis&eacute;(s)"};

        String formAction = pr.getLien() + "?but=" +
                (etatCree
                        ? "paie/fonction/apresMultipleAvance.jsp&acte=valider"
                        : pr.getApres()
                );
    %>
<script>
    function changerDesignation() {
        document.incident.submit();
    }
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
        
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name="incident" id="incident">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <div class="row col-md-12 nopadding">
                <div class="col-md-2 nopadding">
                    &Eacute;tat :
                    <select name="etat" class="champ form-control" id="etat" onchange="document.incident.submit();">
                        <%

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
            out.println(pr.getTableauRecap().getHtml());%>
        </br>
        <form action="<%=formAction%>"  method="post">
            <input type="hidden" name="lien" value="<%=pr.getLien()%>">
            <%
                if(etatCree){
                    pr.setNpp(pr.getTableau().getData().length);
                    pr.getTableau().setNameBoutton("Valider");
                    out.println(pr.getTableau().getHtmlWithCheckbox());
                } else {
                    out.println(pr.getTableau().getHtml());
                }
                out.println(pr.getBasPage());
            %>
        </form>
    </section>
</div>
<%
    }catch(Exception ex){
        ex.printStackTrace();
    }
%>