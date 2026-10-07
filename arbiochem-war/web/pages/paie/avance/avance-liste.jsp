<%@page import="affichage.Liste"%>
<%@page import="bean.TypeObjet"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="paie.avance.Avance" %>
<%@ page import="utils.ConstantePaie" %>

<% 
    try{
    Avance lv = new Avance();
    lv.setNomTable("AVANCE_LIB2");

    String idType = "";

      
    String listeCrt[] = {"id", "idpersonnel","matricule", "montant","interet","nbremboursement","etatlib"};
    String listeInt[] = {"montant","interet", "nbremboursement",};
    String libEntete[] = {"id","dateSaisie", "idpersonnel","matricule", "montant","interet", "nbremboursement","etatlib"};

    PageRecherche pr = new PageRecherche(lv, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setNpp(100);
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("etat") != null && request.getParameter("etat").compareToIgnoreCase("") != 0) {
        pr.setAWhere(pr.getAWhere()+" and etat"+String.valueOf(request.getParameter("etat")));
    }

    if(request.getParameter("idType") != null && request.getParameter("idType").compareToIgnoreCase("") != 0) {
        pr.setAWhere(" and idTypeAvance = '" + request.getParameter("idType") + "'");
        idType = request.getParameter("idType");
    }

        Liste[] liste = new Liste[1];
        TypeObjet mp = new TypeObjet();
        liste[0] = new Liste("typeavance",mp,"val","id");
        pr.getFormu().changerEnChamp(liste);

    pr.setTitre("Liste des avances");

    pr.getFormu().getChamp("nbremboursement1").setLibelle("nombre de remboursement min");
    pr.getFormu().getChamp("nbremboursement2").setLibelle("nombre de remboursement max");
    pr.getFormu().getChamp("montant1").setLibelle("Montant min");
    pr.getFormu().getChamp("montant2").setLibelle("Montant max");
    pr.getFormu().getChamp("idpersonnel").setLibelle("Personnel");
    pr.getFormu().getChamp("etatlib").setVisible(false);
    pr.getFormu().getChamp("interet1").setLibelle("Int&eacute;r&ecirc;t min (%)");
    pr.getFormu().getChamp("interet2").setLibelle("Int&eacute;r&ecirc;t max (%)");




        pr.setApres("paie/avance/avance-liste.jsp&idType="+idType);
        String[] colSomme = {"montant"};
        pr.creerObjetPage(libEntete, colSomme);
       String[] lienTableau = {pr.getLien() + "?but=paie/avance/avance-fiche.jsp"};
       String colonneLien[] = {"id"};
       pr.getTableau().setLien(lienTableau);
       pr.getTableau().setColonneLien(colonneLien);
        String enteteRecap[] = { "", "Nombre", "Montant total" };
        pr.getTableauRecap().setLibeEntete(enteteRecap);
        String libEnteteAffiche[] =  {"ID","Date de saisie", "Personnel","Matricule", "Montant(Ar)","Int&eacute;r&ecirc;t (%)","Nombre de remboursement","&Eacute;tat"};
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
        String lien = (String) session.getValue("lien");

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
                    <select name="etat" class="champ form-control" id="etat" onchange="changerDesignation()">
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
            out.println(pr.getTableauRecap().getHtml());%>
        </br>
        <a class="btn btn-secondary pull-right" href="<%= lien + "?but=paie/avance/avance-liste-billetage.jsp"%> " style="margin-right: 10px">Exporter avec billetage</a>
        <br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    }catch(Exception ex){
        ex.printStackTrace();
    }
%>