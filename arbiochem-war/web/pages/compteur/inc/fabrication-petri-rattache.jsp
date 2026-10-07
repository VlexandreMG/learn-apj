<%--
  Created by IntelliJ IDEA.
  User: maroussia
  Date: 29/07/2026
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@page import="mg.cnaps.compta.*"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="fabrication.FabricationFille" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>


<%
    try{
        FabricationFille o = new FabricationFille();
        o.setNomTable("fabricationrattache");
        String[] listeCrt = {};
        String[] listeInt = {};
        String[] libEntete = {"id", "idIngredients","libelle","qte","pourcentage"};
        PageRecherche pr = new PageRecherche(o, request, listeCrt, listeInt, 4, libEntete, libEntete.length);
        pr.setTitre("Liste des mouvements de stock non rattach&eacute;es");
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        if(request.getParameter("id") != null){
            pr.setAWhere(" and idreleve ='"+request.getParameter("id")+"'");
        }
        String[] colSomme = {"qte"};
        pr.creerObjetPage(libEntete, colSomme);

        String lienTableau[] = {pr.getLien() + "?but=fabrication/fabrication-fiche.jsp",pr.getLien() + "?but=produits/as-ingredients-fiche.jsp"};
        String colonneLien[] = {"id","idIngredients"};
        String valLien[] = {"idMere","idIngredients"};
        String varColonneLien[] = {"id","id"};
        pr.getTableau().setLien(lienTableau);
        pr.getTableau().setValeurLien(valLien);
        pr.getTableau().setAttLien(varColonneLien);
        pr.getTableau().setColonneLien(colonneLien);
       // lien + "?but=apresTarif.jsp&id=" + id &acte=valider&bute=pageActuel&classe=classe
        String pageActuel = "compteur/releve-fiche.jsp";
        if (request.getParameter("etat") != null && request.getParameter("type")!= null) {
            int etat = Integer.parseInt(request.getParameter("etat"));
            String type = request.getParameter("type");
            if (etat < 11) {
                FabricationFille[] fabricationFilles = (FabricationFille[]) pr.getTableau().getData();
                if(fabricationFilles.length > 0 ){
                    String idCompteur = fabricationFilles[0].getIdReleve();
                    Map<String,String> lienTab=new HashMap();
                    String lien = pageActuel;
                    if(type.equalsIgnoreCase("electricite")) {
                        lien = "compteur/releve-electricite-multiple-fiche.jsp";
                    }
                    lienTab.put("Supprimer",pr.getLien() + "?but=apresTarif.jsp&acte=delete&bute="+lien+"&classe=maintenance.configuration.ReleveFab&rajoutLienFormu=id-"+idCompteur);
                    pr.getTableau().setLienClicDroite(lienTab);
                }
            }
        }

        String[] libEnteteAffiche = {"ID","R&eacute;ference Produit", "Produit", "Quantite","Pourcentage"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);
%>

<div class="box-body">
    <section class="content">
        <form action="<%=pr.getLien()%>?but=apresMultiple.jsp" method="post" name="paye" id="paye">
            <%
                out.println(pr.getTableau().getHtml());
                out.println(pr.getBasPage());
            %>
        </form>
    </section>

</div>
<script>
    function changerDesignation() {
        document.paye.submit();
    }
</script>
<% } catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
history.back();
</script>
<% }%>
