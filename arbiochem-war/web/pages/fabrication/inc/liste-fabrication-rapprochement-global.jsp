<%@page import="stock.*"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="produits.Ingredients" %>
<%@ page import="fabrication.OfFilleCpl" %>

<%
    try{
        MvtStockFilleTheorique t = new MvtStockFilleTheorique();
//        t.setNomTable("STOCKETDEPOFFILLETHECATGROUPE"); rapprochement sans charge personnel
        t.setNomTable("RAPPROCHEMENT_COMPLETOFFILLE");
        String listeCrt[] = {};
        String listeInt[] = {};
        String libEntete[] = {"idcategorieingredient","categorieingredient","montantsortie","pourcentage","montth","ecart"};
        PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        OfFilleCpl ofFilleCpl = null;
        if(request.getParameter("id") != null){
            pr.setAWhere(" and IDMERE='"+request.getParameter("id")+"'");
            pr.setApres("fabrication/ordre-fabrication-fiche.jsp&id="+request.getParameter("id")+"&tab=inc/ordre-fabrication-rapprochement-globale");
            ofFilleCpl = new OfFilleCpl();
            ofFilleCpl.setNomTable("OfFilleLibStock");
            ofFilleCpl = (OfFilleCpl) ofFilleCpl.getById(request.getParameter("id"),"OfFilleLibStock",null);
        }
        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);
        String lienTableau[] = {pr.getLien() + "?but=produits/as-ingredients-fiche.jsp"};
        String colonneLien[] = {"idproduit"};
        MvtStockFilleTheorique[] listeFille=(MvtStockFilleTheorique[]) pr.getTableau().getData();
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setColonneLien(colonneLien);

        double montant = AdminGen.calculSommeDouble(listeFille, "montantsortie");
        double montth = AdminGen.calculSommeDouble(listeFille, "montth");
        double ecart = AdminGen.calculSommeDouble(listeFille, "ecart");
        double puRevient = 0;
        double tauxRevient = 0;
        if(ofFilleCpl != null){
            puRevient = ofFilleCpl.getPuRevient(montant);
            tauxRevient = ofFilleCpl.getTauxRevientTotal(montant, null);
        }

        String styleMontant = montant > montth ? "color:red;" : "";
        MvtStockFilleTheorique.calculerPourcentage(listeFille);
        pr.getTableau().transformerDataString();
        //pr.getTableau().setLienFille(String.format("fabrication/inc/ordre-fabrication-rapprochement.jsp&id=%s&idCat=",request.getParameter("id")));
%>

<div class="box-body">
    <%
        String libEnteteAffiche[] =  {"ID","Ingredient","Montant","% pratique","Montant TH","Ecart"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
        if(pr.getTableau().getHtml() != null){
            out.println(pr.getTableau().getHtml());
    %>
    <div class="w-100" style="display: flex; flex-direction: row-reverse;">
        <table style="width: 80%" class="table">
            <tr>
                <td><b>Total Montant:</b></td>
                <td><b style="<%= styleMontant %>"><%= utilitaire.Utilitaire.formaterAr(montant) %></b></td>
                <td><b>Total Montant TH:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(montth) %> Ar</b></td>
                <td><b>Ecart Montant:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(ecart) %> Ar</b></td>
                <td><b>PU Revient:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(puRevient) %> Ar</b></td>
                <td><b>Taux Revient:</b></td>
                <td><b><%= utilitaire.Utilitaire.formaterAr(tauxRevient) %> %</b></td>
            </tr>
        </table>
    </div>
    <%  }if(pr.getTableau().getHtml() == null)
    {
    %><center><h4>Aucune donnee trouvee</h4></center><%
    }
%>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }%>

