<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="fabrication.*" %>
<%@ page import="java.util.List" %>

<%
    HeureSupFabricationCPL r = new HeureSupFabricationCPL();
    r.setNomTable("CHARGEPERSONNEL_CPL");

    String listeCrt[] = {};
    String listeInt[] = {};

    String libEntete[] = {"id","matricule", "th","valeurHS", "valeurMN", "valeurJF", "valeurHD","valeurIF","montantTotal"};

    PageRecherche pr = new PageRecherche(r, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));

    if(request.getParameter("id") != null){
        pr.setAWhere(" and idFabrication='"+request.getParameter("id")+"'");
    }

    String[] colSomme = null;
    pr.setNpp(500);
    pr.creerObjetPage(libEntete, colSomme);
    pr.getTableau().transformerDataString();

    String id = request.getParameter("id");

    String lienTableau[] = {pr.getLien() + "?but=fabrication/heureSup-fiche.jsp"};
    String colonneLien[] = {"id"};
    String[] attributLien = {"id"};
    String colonneModal[] = {"id"};

    pr.getTableau().setAttLien(attributLien);
    pr.getTableau().setLien(lienTableau);
    pr.getTableau().setColonneLien(colonneLien);
    pr.getTableau().setModalOnClick(true, colonneModal);

    String libEnteteAffiche[] = {"id","matricule","Taux horaire","Montant HS", "Montant MN", "Montant JF", "Montant HD","Montant IF","Montant Total"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);

    HeureSupFabricationCPL[] liste = (HeureSupFabricationCPL[]) pr.getTableau().getData();
%>

<div class="box-body">

    <div class="col-md-4" style="padding-top: 12px;">
        <form action="<%= (String)session.getValue("lien") + "/../../ImportChargePersonnel?idFab=" + id %>"
              method="post"
              enctype="multipart/form-data"
              style="text-align: center;">
            <div class="d-flex gap-2" style="align-items: end">
                <div class="form-input w-100">
                    <label class="input-label">Choisir un fichier Excel (.xlsx)</label><br>
                    <input type="file"
                           name="excelFile"
                           accept=".xlsx"
                           required
                           class="form-control"
                           style="height: 42px;" />
                </div>
                <button type="submit" class="btn btn-secondary" style="height: 42px;">
                    Importer charge personnel
                </button>
            </div>
        </form>
    </div>

    <%
        if(liste != null && liste.length > 0) {
    %>

    <div id="tableau-wrapper">
        <% out.println(pr.getTableau().getHtml()); %>
    </div>

    <div class="w-100" style="display: flex; flex-direction: row-reverse;">
        <table style="width: 20%" class="table">
            <tr>
                <td><b>Total montant: </b></td>
                <td>
                    <b>
                        <%= utilitaire.Utilitaire.formaterAr(
                                AdminGen.calculSommeDouble(liste, "montantTotal")
                        ) %>
                    </b>
                </td>
            </tr>
        </table>
    </div>

    <% } else { %>

    <center><h4>Aucune donn&eacute;e trouv&eacute;e</h4></center>

    <% } %>

    <div class="row">
        <div class="col-md-12">
            <%
                List<String> erreurs = (List<String>) session.getAttribute("erreurs");
                if (erreurs != null && !erreurs.isEmpty()) {
            %>
            <div class="alert alert-danger"
                 style="border-radius:8px;
                        padding:15px;
                        margin:15px 0;
                        width:100%;
                        box-shadow:0 2px 6px rgba(0,0,0,0.15);">

                <div style="display:flex; justify-content:space-between; align-items:center;">
                    <strong>Erreurs durant l'importation</strong>
                    <button type="button"
                            onclick="this.closest('.alert').style.display='none'"
                            style="background:none; border:none; font-size:18px; cursor:pointer;">
                        &times;
                    </button>
                </div>

                <hr style="margin:10px 0;">

                <div style="max-height:250px; overflow-y:auto;">
                    <ul style="padding-left:20px; margin:0;">
                        <% for (String e : erreurs) { %>
                        <li style="margin-bottom:6px; font-size:14px; line-height:1.4;">
                            <%= e %>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>
            <%
                    session.removeAttribute("erreurs");
                }
            %>
        </div>
    </div>

</div>

<%= pr.getModalHtml("modalContent") %>