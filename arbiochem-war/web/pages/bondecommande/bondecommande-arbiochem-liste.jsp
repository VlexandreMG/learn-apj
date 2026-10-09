<%@page import="affichage.PageRecherche"%>
<%@page import="faturefournisseur.As_BonDeCommande"%>
<%@ page import="faturefournisseur.As_BonDeCommandeCpl" %>

<% try {
    As_BonDeCommandeCpl bdc_Cpl = new As_BonDeCommandeCpl(); 
    bdc_Cpl.setNomTable("As_BonDeCommande_MERETRAITE");

                                    // (modèle, request, critères, intervalles, nbRange, colonnes affichées, nbAff).
    PageRecherche pg = PageRecherche(bdc_Cpl, request ,new String[]{"id"}, new String[]{"daty"},3,new String[]{"id"},1)

    pg.setTitre("Liste des bons de commande fournisseur");
    pg.setUtilisateur((user.UserEJB)session.getValue("u"));
    pg.setLien((String)session.getValue("lien"));
    pg.setApres("bondecommande/bondecommande-arbiochem-liste.jsp");
    
    // Je ne sais pas comment prendre les entêtes
    pg.creerObjetPage(new String[]{"id"});

    out.println(pg.getTableau().getHtml());
    // Pour la pagination 
    out.println(pg.getBasPage());
} catch(Exception e) {
    e.printStackTrace();
}


%>
