<%@page import="affichage.PageConsulte"%>
<%@page import="paie.avance.Avance"%>
<%@page import="utilitaire.Utilitaire"%>
<%@page import="paie.avance.Remboursement"%>

<%
    try {
        String lien = (String) session.getAttribute("lien");
        String id = request.getParameter("id"),
                nb_Remboursement = request.getParameter("nbremboursement"),
                colonneMere = request.getParameter("idpersonnel"),
                classeFille = "paie.avance.Remboursement",
                redirection = "paie/avance/avance-fiche.jsp&id=" + id;

        Avance avance = new Avance();
        avance.setNomTable("AVANCE_LIB");

        PageConsulte pc = new PageConsulte(avance, request, (user.UserEJB) session.getValue("u"));
        pc.getChampByName("daty").setVisible(false);
        pc.getChampByName("idpersonnel").setLibelle("Personnel");
        pc.getChampByName("nbremboursement").setLibelle("Nombre de remboursements");
        pc.getChampByName("dateAvance").setLibelle("Date avance");
        pc.getChampByName("idTypeAvance").setLibelle("Type avance");
        pc.setTitre("Fiche Avance");

        Avance base = (Avance) pc.getBase();
        int nbremboursement = base.getNbremboursement();
        double interet = base.getInteret()/100;

        Remboursement fille = new Remboursement();
        String[] mois = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
        System.out.println(" date debut remboursement " + base.getDateDebutRemboursement() + " date avanceeee " + base.getDateAvance() + " id avance " + base.getId());
        int finMois = mois.length, j = 0, mois_av = Utilitaire.getMois(base.getDateDebutRemboursement()), annee = Utilitaire.getAnnee(base.getDateDebutRemboursement());

        double montant_int = (base.getMontant()*interet) + base.getMontant();
        int montant = (int) montant_int / nbremboursement;

        double mensuelMontantSanInteret = base.getMontant()/nbremboursement;
        double[] montantNbRemboursementSanInteret = new double[nbremboursement];
        double currentMontant = base.getMontant();

        for (int i = 0; i < nbremboursement; i++)
        {


            if (i == 0)
            {
                montantNbRemboursementSanInteret[i] = base.getMontant();
            }
            else {
                montantNbRemboursementSanInteret[i] = currentMontant - mensuelMontantSanInteret;
                currentMontant = currentMontant - mensuelMontantSanInteret;

            }

        }

        double[] montantNbRemboursementAvecInteretTBC = new double[nbremboursement];
        double currentMontantAvecInteret = base.getMontant();

        for (int i = 0; i < nbremboursement; i++)
        {
            montantNbRemboursementAvecInteretTBC[i] = currentMontantAvecInteret - mensuelMontantSanInteret;
            currentMontantAvecInteret = currentMontantAvecInteret - mensuelMontantSanInteret;
        }

        fille.setNomTable("Remboursement");
        fille.setIdavance(id);
        Remboursement[] lsP = fille.listPlanRemb("", null);

        int currentMonth = mois_av;
        int currentYear = annee;

        double totalInteret = 0.0;
        double totalMotantpayee = 0.0;


        if (lsP.length > 0) {
%>
<script>document.location.replace("<%=lien%>?but=paie/avance/remboursement-modif.jsp&id=<%=base.getId()%>");</script>
<%
        return;
    }
%>

<div class="content-wrapper">
    <h1 class="box-title">
        <a href="<%= lien + "?but=paie/avance/avance-fiche.jsp&id=" + id %>">
            <i class="fa fa-angle-left"></i>
        </a>Fiche avance
    </h1>
    <div class="row m-0">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="box-fiche">
                <div class="box">
                    <div class="box-body">
                        <%= pc.getHtml() %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row m-0">
        <div class="">
            <form action="<%=lien%>?but=apresTarifMultiple.jsp" method="post" name="remboursement" id="remboursement">
                <h3 class="box-title h520pxSemibold m-0 w-100">Plan de Remboursement</h3>
                <div class="col-md-12 cardradius nopadding" style="background: white;padding: 15px;margin-top: 20px;overflow: hidden">
                    <div class="table-container nopadding borderless">
                        <table class="table">
                            <thead>
                            <tr class="head">
                                <th  class='contenuetable'>Date</th>
                                <th  class='contenuetable'> Montant restant (avant) </th>
                                <th  class='contenuetable'> Mensualit&eacute; (sans int&eacute;r&ecirc;t) </th>
                                <th  class='contenuetable'>Int&eacute;r&ecirc;t</th>
                                <th  class='contenuetable'>Mensualit&eacute; (avec int&eacute;r&ecirc;t) </th>
                                <th  class='contenuetable'>Montant restant (apr&egrave;s) </th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (int i = 0; i < nbremboursement; i++) {
                                int displayMonth = (currentMonth % 12 == 0) ? 12 : (currentMonth % 12);
                                int displayYear = currentYear + (currentMonth - 1) / 12;
                                java.util.Calendar cal = java.util.Calendar.getInstance();

                                cal.set(java.util.Calendar.YEAR, displayYear);
                                cal.set(java.util.Calendar.MONTH, displayMonth - 1); // ⚠️ months are 0-based
                                cal.set(java.util.Calendar.DAY_OF_MONTH, cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));

                                java.sql.Date dateFinMois = new java.sql.Date(cal.getTimeInMillis());%>
                            <tr>
                                <input type="hidden" class="form-control" id="iddemande<%=i%>" name="iddemande<%=i%>" value="<%=id%>">
                                <input type="hidden" name="mois_<%=i%>" value="<%= displayMonth %>">
                                <input class="form-control" type="hidden" id="annee_<%=i%>" name="annee_<%=i%>" value="<%= displayYear %>">
                                <td>
                                    <input type="date" class="form-control" name="datyRemboursement_<%=i%>" value="<%= dateFinMois %>">
                                </td>
                                <td>
                                    <input type="number" class="form-control" name="restanteMontant" value="<%= montantNbRemboursementSanInteret[i]%>">
                                </td>
                                <td>
                                    <input type="number" class="form-control" name="mensuelMontantSanInteret" value="<%= mensuelMontantSanInteret %>">
                                </td>
                                <td>
                                    <input type="number" class="form-control" name="interet" value="<%= montantNbRemboursementSanInteret[i]*interet %>">
                                    <% totalInteret += montantNbRemboursementSanInteret[i]*interet; %>
                                </td>
                                <td>
                                    <input type="number" class="form-control" id="montant_<%=i%>" name="montant_<%=i%>" value="<%= mensuelMontantSanInteret + (montantNbRemboursementSanInteret[i]*interet) %>">
                                    <% totalMotantpayee += mensuelMontantSanInteret + (montantNbRemboursementSanInteret[i]*interet); %>
                                </td>
                                <td>
                                    <input class="form-control" type="number" id="restanteMontantAvecInteret" name="restanteMontantAvecInteret"
                                           value="<%= montantNbRemboursementAvecInteretTBC[i] - (montantNbRemboursementSanInteret[i]*interet) %>">
                                </td>
                            </tr>

                            <%
                                    currentMonth++;} %>
                            </tbody>
                        </table>
                    </div>
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr class="head">
                            <th align="end" >Total Interet</th>
                            <th align="end" >Total Montant Pay&eacute;e Avec Interet </th>
                        </tr>
                        </thead>
                        <tbody>
                        <td align="end" >
                            <%= totalInteret %>
                        </td>
                        <td align="end">
                            <%= totalMotantpayee %>
                        </td>
                        </tbody>
                    </table>
                    <div class="box-footer" style="padding: 8px">
                        <input type="submit" class="btn btn-primary pull-right" value="Enregistrer">
                    </div>
                </div>

                <input type="hidden" name="acte" value="savePlanRemboursement">
                <input type="hidden" name="bute" value="<%= redirection %>">
                <input type="hidden" name="idMere" value="<%= base.getId() %>">
                <input type="hidden" name="classefille" value="<%= classeFille %>">
                <input type="hidden" name="nombreLigne" value="<%= nbremboursement %>">
                <input type="hidden" name="colonneMere" value="<%= colonneMere %>">
            </form>
        </div>
    </div>
</div>

<%
} catch (Exception e) {
    e.printStackTrace();
%>
<script>alert('<%= e.getMessage() %>'); history.back();</script>
<% } %>
