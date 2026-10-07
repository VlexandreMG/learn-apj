


<%@page import="faturefournisseur.Fournisseur"%>
<%@page import="affichage.PageInsert"%> 
<%@page import="user.UserEJB"%> 
<%@page import="bean.TypeObjet"%> 
<%@page import="affichage.Liste"%>
<%@ page import="mg.cnaps.compta.ConstanteCompta" %>
<%@ page import="caisse.Devise" %>

<%
    try{
    String autreparsley = "data-parslsey-range='[8, 40]' required";
    UserEJB u = (user.UserEJB) session.getValue("u");
    String  mapping = "faturefournisseur.Fournisseur",
            nomtable = "FOURNISSEUR",
            apres = "fournisseur/fournisseur-fiche.jsp",
            titre = "Insertion fournisseur";
    
    Fournisseur  caisse = new Fournisseur();
    PageInsert pi = new PageInsert(caisse, request, u);
    pi.setLien((String) session.getValue("lien"));

    Liste[] liste = new Liste[2];
    Devise d = new Devise();
    liste[0] = new Liste("devise",d,"val","id");
    TypeObjet typeFournisseur = new TypeObjet();
    typeFournisseur.setNomTable("typefournisseur");
    liste[1] = new Liste("idTypeFournisseur",typeFournisseur,"val","id");

    pi.getFormu().changerEnChamp(liste);
    pi.getFormu().getChamp("nif").setLibelle("NIF");
    pi.getFormu().getChamp("stat").setLibelle("STAT");
    pi.getFormu().getChamp("devise").setDefaut("AR");
    pi.getFormu().getChamp("echeance").setLibelle("Ech&eacute;ance  de paiement");
    pi.getFormu().getChamp("codePostal").setLibelle("Code Postal");
    pi.getFormu().getChamp("compte").setLibelle("Compte G&eacute;n&eacute;ral");
    pi.getFormu().getChamp("compte").setDefaut(ConstanteCompta.compte_fournisseur);
    pi.getFormu().getChamp("compteauxiliaire").setVisible(false);
    pi.getFormu().getChamp("estActif").setVisible(false);
    //pi.getFormu().getChamp("compte").setPageAppelComplete("mg.cnaps.compta.ComptaCompte", "compte", "COMPTA_COMPTE");
    pi.getFormu().getChamp("idTypeFournisseur").setLibelle("Type Fournisseur");
    pi.getFormu().getChamp("mail").setLibelle("Mail");
    pi.getFormu().getChamp("banque").setLibelle("Banque");
    pi.preparerDataFormu();
            if(request.getParameter("acte")!=null){
            titre = "Modification fournisseur";
        }
%>
<div class="content-wrapper">
    <h1> <%=titre%></h1>
    
    <form action="<%=pi.getLien()%>?but=apresTarif.jsp" method="post" name="<%=nomtable%>" id="<%=nomtable%>">
    <%
        pi.getFormu().makeHtmlInsertTabIndex();
        out.println(pi.getFormu().getHtmlInsert());
       out.println(pi.getHtmlAddOnPopup());
    %>
    <input name="acte" type="hidden" id="nature" value="insert">
    <input name="bute" type="hidden" id="bute" value="<%=apres%>">
    <input name="classe" type="hidden" id="classe" value="<%=mapping%>">
    <input name="nomtable" type="hidden" id="nomtable" value="<%=nomtable%>">
    </form>
</div>

<%
} catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
    history.back();</script>

<% }%>