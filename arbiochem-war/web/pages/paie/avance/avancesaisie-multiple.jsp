<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="affichage.PageInsertMultiple" %>
<%@ page import="affichage.Champ" %>
<%@ page import="user.UserEJB" %>
<%@ page import="paie.avance.Avance" %>
<%@ page import="utilitaire.Utilitaire" %>
<%@ page import="affichage.Liste" %>
<%@ page import="bean.TypeObjet" %>
<%@ page import="bean.CGenUtil" %>
<%@ page import="utils.ConstantePaie" %>
<%@ page import="paie.avance.AvanceLib" %>

<% try{
  UserEJB u = (user.UserEJB) session.getValue("u");
  int taille = 10;

  AvanceLib fille = new AvanceLib();
  fille.setNomTable("AVANCELIB_VIDE");

  PageInsertMultiple pi = new PageInsertMultiple(fille, fille, request, taille, u);
  pi.setLien((String) session.getValue("lien"));
  pi.setTitre("Enregistrement des avances en lot");

  if (request.getParameter("onchanged") != null && request.getParameter("onchanged").equals("true")){
    String iddepartement = request.getParameter("iddepartement");
    if (iddepartement != null) {
      System.out.println("iddepartement : " + iddepartement);
      Avance[] filles = fille.genererAvance(iddepartement);
      System.out.println(filles);
      if (filles != null) {
        pi = new PageInsertMultiple(fille, fille, request, filles.length, u);
        pi.setLien((String) session.getValue("lien"));
        pi.setTitre("Enregistrement des avances en lot");
        taille = filles.length;
        pi.setDefautFille(filles);
      }
    }
  }

  pi.getFormu().getChamp("daty").setVisible(false);
  pi.getFormu().getChamp("etat").setVisible(false);
  pi.getFormu().getChamp("idpersonnel").setVisible(false);
  pi.getFormu().getChamp("dateAvance").setVisible(false);
  pi.getFormu().getChamp("daty").setVisible(false);
  pi.getFormu().getChamp("nbremboursement").setVisible(false);
  pi.getFormu().getChamp("idtypeavance").setVisible(false);
  pi.getFormu().getChamp("montant").setVisible(false);
  pi.getFormu().getChamp("interet").setVisible(false);
  pi.getFormu().getChamp("remarque").setVisible(false);

   String idAvance = request.getParameter("idAvance");
   String typeavance = null;

   if (ConstantePaie.idAvanceSurSalaire.equals(idAvance))
   {

     Liste[] listes = new Liste[1];
     TypeObjet tp = new TypeObjet();
     tp.setNomTable("typeavance");
     listes[0] = new Liste("idtypeavance", tp, "val", "id");
     listes[0].setDefaut(ConstantePaie.idAvanceSurSalaire);
     pi.getFormufle().changerEnChamp(listes);

     Champ.setPageAppelComplete(pi.getFormufle().getChampFille("idpersonnel"),"paie.log.LogPersonnel","id","LOG_PERSONNEL_V2" ,"nom;matricule" , "nomPersonnel;matricule");
     pi.getFormufle().getChamp("idpersonnel_0").setLibelle("Personnel");
     pi.getFormufle().getChamp("daty_0").setLibelle("Date");
     pi.getFormufle().getChamp("dateAvance_0").setLibelle("Date De l'avance");
     pi.getFormufle().getChampMulitple("nbremboursement").setVisible(false);
     pi.getFormufle().getChamp("idtypeavance_0").setLibelle("Type");
     pi.getFormufle().getChamp("montant_0").setLibelle("Montant(Ar)");
     pi.getFormufle().getChampMulitple("interet").setVisible(false);
     pi.getFormufle().getChamp("matricule_0").setLibelle("Matricule");
     pi.getFormufle().getChamp("nomPersonnel_0").setLibelle("Nom du Personnel");


     // Définir les valeurs par défaut pour toutes les lignes
     for(int i = 0; i < taille; i++){
       pi.getFormufle().getChamp("dateAvance_"+i).setDefaut(Utilitaire.dateDuJour());
       pi.getFormufle().getChamp("nbremboursement_"+i).setDefaut("1");
       pi.getFormufle().getChamp("interet_"+i).setDefaut("0");
       pi.getFormufle().getChamp("nomPersonnel_"+i).setAutre("readonly");
       pi.getFormufle().getChamp("matricule_"+i).setAutre("readonly");
       if(idAvance.compareToIgnoreCase(ConstantePaie.idAvanceSurSalaire)==0){
         typeavance = ConstantePaie.idAvanceSurSalaire;
         pi.getFormufle().getChamp("idtypeavance_"+i).setDefaut(ConstantePaie.idAvanceSurSalaire);
       }
     }

     pi.getFormufle().getChampMulitple("id").setVisible(false);
     pi.getFormufle().getChampMulitple("remarque").setVisible(false);
     pi.getFormufle().getChampMulitple("daty").setVisible(false);
     pi.getFormufle().getChampMulitple("etat").setVisible(false);

     pi.getFormufle().setColOrdre(new String[]{"idtypeavance","idpersonnel","matricule","nomPersonnel","montant","dateAvance"});

   } else if (ConstantePaie.idAvanceExceptionnelle.equals(idAvance)) {

     Liste[] listes = new Liste[1];
     TypeObjet tp = new TypeObjet();
     tp.setNomTable("typeavance");
     listes[0] = new Liste("idtypeavance", tp, "val", "id");
     listes[0].setDefaut(ConstantePaie.idAvanceExceptionnelle);
     pi.getFormufle().changerEnChamp(listes);

     Champ.setPageAppelComplete(pi.getFormufle().getChampFille("idpersonnel"),"paie.log.LogPersonnel","id","LOG_PERSONNEL_V2" ,"nom;matricule" , "nomPersonnel;matricule");
     pi.getFormufle().getChamp("idpersonnel_0").setLibelle("Personnel");
     pi.getFormufle().getChamp("daty_0").setLibelle("Date");
     pi.getFormufle().getChampMulitple("daty_0").setVisible(false);
     pi.getFormufle().getChamp("dateAvance_0").setLibelle("Date De l'avance");
     pi.getFormufle().getChamp("nbremboursement_0").setLibelle("Nombre de remboursement");
     pi.getFormufle().getChamp("idtypeavance_0").setLibelle("Type");
     pi.getFormufle().getChamp("montant_0").setLibelle("Montant(Ar)");
     pi.getFormufle().getChamp("interet_0").setLibelle("Int&eacute;r&ecirc;t");
     pi.getFormufle().getChamp("matricule_0").setLibelle("Matricule");
     pi.getFormufle().getChamp("nomPersonnel_0").setLibelle("Nom du Personnel");


     // Définir les valeurs par défaut pour toutes les lignes
     for(int i = 0; i < taille; i++){
       pi.getFormufle().getChamp("dateAvance_"+i).setDefaut(Utilitaire.dateDuJour());
       pi.getFormufle().getChamp("nbremboursement_"+i).setDefaut("1");
       pi.getFormufle().getChamp("interet_"+i).setDefaut("1");
       pi.getFormufle().getChamp("nomPersonnel_"+i).setAutre("readonly");
       pi.getFormufle().getChamp("matricule_"+i).setAutre("readonly");
       if(idAvance.compareToIgnoreCase(ConstantePaie.idAvanceExceptionnelle)==0){
         typeavance = ConstantePaie.idAvanceExceptionnelle;
         pi.getFormufle().getChamp("idtypeavance_"+i).setDefaut(ConstantePaie.idAvanceExceptionnelle);
       }
     }

     pi.getFormufle().getChampMulitple("id").setVisible(false);
     pi.getFormufle().getChampMulitple("remarque").setVisible(false);
     pi.getFormufle().getChampMulitple("daty").setVisible(false);
     pi.getFormufle().getChampMulitple("etat").setVisible(false);

     pi.getFormufle().setColOrdre(new String[]{"idtypeavance","idpersonnel","matricule","nomPersonnel","montant", "interet", "nbremboursement","dateAvance"});


   }


  TypeObjet typeObjet = new TypeObjet();
  typeObjet.setNomTable("DEPARTEMENT");
  TypeObjet[] deps = (TypeObjet[]) CGenUtil.rechercher(typeObjet, null, null, "");

  String selectedDepartement = request.getParameter("iddepartement");

  pi.preparerDataFormu();
  pi.getFormu().makeHtmlInsertTabIndex();
  pi.getFormufle().makeHtmlInsertTableauIndex();
%>
<script>
  function changerDesignation() {
    document.incident.submit();
  }
</script>
<div class="content-wrapper">
  <section class="content-header">
    <h1><%= pi.getTitre() %></h1>

  </section>
  <section class="content">
    <form action="<%=pi.getLien()%>?but=paie/avance/avancesaisie-multiple.jsp&idAvance=<%=idAvance%>" method="post" name="incident" id="incident">
      <input name="onchanged" type="hidden" id="onchanged" value="true">
      <%
        out.println(pi.getFormu().getHtmlEnsemble());
      %>
      <div class="row col-md-12 nopadding">
        <div class="col-md-2 nopadding">
          D&eacutepartement :
          <select name="iddepartement" class="champ form-control" id="iddepartement" onchange="changerDesignation()">
            <%
              for( int i = 0; i < deps.length; i++ ){
                String selected = "";
                if(selectedDepartement != null && selectedDepartement.equals(deps[i].getId())){
                  selected = "selected";
                }
              %>
                <option value="<%= deps[i].getId() %>" <%= selected %>> <%= deps[i].getVal() %> </option>
            <% } %>
          </select>
        </div>
      </div>
    </form>
  <form id="formId" class='container' action="<%=pi.getLien()%>?but=paie/avance/apresMultipleAvance.jsp" method="post" >
    <%
      out.println(pi.getFormufle().getHtmlTableauInsert());
    %>
    <input name="acte" type="hidden" id="nature" value="insertFilleSeul">
    <input name="bute" type="hidden" id="bute" value="paie/avance/avance-liste.jsp">
    <input name="classe" type="hidden" id="classe" value="paie.avance.Avance">
    <input name="classefille" type="hidden" id="classefille" value="paie.avance.Avance">
    <input name="nomtable" type="hidden" id="nomtable" value="avance">
    <input name="nombreLigne" type="hidden" id="nombreLigne" value="<%=taille%>">
    <input name="typeavance" type="hidden" id="nature" value="<%=typeavance%>">
  </form>
  </section>
</div>

<%
} catch (Exception e) {
  e.printStackTrace();
%>
<script language="JavaScript"> alert('<%=e.getMessage()%>');
history.back();</script>

<% }%>
