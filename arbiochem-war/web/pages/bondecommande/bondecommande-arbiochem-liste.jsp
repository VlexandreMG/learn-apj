<%@page import="affichage.PageRecherche"%>
<%@page import="faturefournisseur.As_BonDeCommande"%>
<%@ page import="faturefournisseur.As_BonDeCommandeCpl" %>

<% try {

    // 3. Champs de recherche, champ en intervalle (date), colonnes du tableau
    String listeCrt[] = {"id","daty","designation","fournisseurlib","modepaiementlib","reference","refproforma"};
    String listeInt[] = {"daty"};
    String libEntete[] = {"id","daty","reference","designation","modepaiementlib","fournisseurlib", "idServiceLib","refproforma","etatlib","traite"};

    As_BonDeCommandeCpl bdc_Cpl = new As_BonDeCommandeCpl(); 
    bdc_Cpl.setNomTable("As_BonDeCommande_MERETRAITE");

                                    // (modèle, request, critères, intervalles, nbRange, colonnes affichées, nbAff).
    PageRecherche pg = new PageRecherche(bdc_Cpl, request ,listeCrt, listeInt,3,libEntete,libEntete.length);

    pg.setTitre("Liste des bons de commande fournisseur");
    pg.setUtilisateur((user.UserEJB)session.getValue("u"));
    pg.setLien((String)session.getValue("lien"));
    pg.setApres("bondecommande/bondecommande-arbiochem-liste.jsp");

    // Je ne sais pas comment prendre les entêtes
    pg.creerObjetPage(new String[]{"id","daty","designation"}, null);

    out.println(pg.getTableau().getHtml());
    // Pour la pagination 
    out.println(pg.getBasPage());
} catch(Exception e) {
    e.printStackTrace();
}


%>
