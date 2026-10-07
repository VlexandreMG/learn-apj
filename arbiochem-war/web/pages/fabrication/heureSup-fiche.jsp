<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="fabrication.*" %>
<%@ page import="utilitaire.Constante" %>
<%@ page import="utils.ConstanteProcess" %>
<%@ page import="utils.ConstanteSocobis" %>
<%@ page import="constante.ConstanteEtat" %>

<%
    try{
        String lien = (String) session.getValue("lien");
        HeureSupFabricationCPL t = new HeureSupFabricationCPL();
        t.setNomTable("heureSupFabrication_cpl");
        PageConsulte pc = new PageConsulte(t, request, (user.UserEJB) session.getValue("u"));
        t = (HeureSupFabricationCPL) pc.getBase();
        String id=pc.getBase().getTuppleID( );

        pc.getChampByName("id").setLibelle("ID");
        pc.getChampByName("idRessParFab").setLibelle("Ressource Fabrication");
        pc.getChampByName("heureNormale").setLibelle("Heure Normale");
        pc.getChampByName("HS").setLibelle("Heure Suppl&eacute;mentaire");
        pc.getChampByName("MN").setLibelle("Majoration Nuit");
        pc.getChampByName("JF").setLibelle("Jour Feri&eacute;");
        pc.getChampByName("IF").setLibelle("Indemnit&eacute; de fonction");
        pc.getChampByName("HD").setLibelle("Heure Dimanche");
        pc.getChampByName("etatlib").setLibelle("Etat");
        pc.getChampByName("idPersonneLib").setLibelle("Personne");
        pc.getChampByName("poste").setVisible(false);
        pc.getChampByName("posteLib").setLibelle("Poste");
        pc.getChampByName("idFabrication").setLibelle("ID Fabrication");
        pc.getChampByName("idPersonne").setLibelle("ID Personne");
        pc.getChampByName("idFabrication").setLien(lien+"?but=fabrication/fabrication-fiche.jsp", "id=");
        pc.getChampByName("idPersonne").setLien(lien+"?but=personnel/personnel-fiche.jsp", "id=");
        pc.setTitre("Fiche Heure Suppl&eacute;mentaire");
        pc.getChampByName("tauxHoraireEffective").setVisible(false);
        pc.getChampByName("montantJF").setVisible(false);
        pc.getChampByName("montantHD").setVisible(false);
        pc.getChampByName("montantIF").setVisible(false);
        pc.getChampByName("montantMN").setVisible(false);
        pc.getChampByName("idQualification").setVisible(false);
        pc.getChampByName("dateFabrication").setLibelle("Date");
        pc.getChampByName("tauxHoraire").setLibelle("Taux horaire");

        String[] ordre = {"idFabrication","idRessParFab", "idPersonne", "matricule","idPersonneLib","date","poste","tauxHoraire","heureNormale","HS","MN","JF", "HD", "IF"};
        pc.setOrdre(ordre);
        String pageModif = "fabrication/heureSup-modif.jsp";
        String pageActuel = "fabrication/heureSup-fiche.jsp";
        String pageActuelListe = "fabrication/fabrication-liste.jsp";
        String classe = "fabrication.HeureSupFabrication";
        String nomTable = "heureSupFabrication";

%>
<div class="content-wrapper">
    <h1 class="box-title"><a href="#"><i class="fa fa-angle-left"></i></a><%=pc.getTitre()%></h1>
    <div class="row m-0">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="box-fiche">
                <div class="box">
                    <div class="box-body">
                        <%
                            out.println(pc.getHtml());
                        %>
                        <div class="box-footer">
                            <% if(t.getEtat()==1){ %>
                            <a class="btn btn-primary pull-right" href="<%= lien + "?but=apresTarif.jsp&acte=valider&id=" + id + "&bute="+pageActuel+"&classe=fabrication.HeureSupFabrication&nomtable=heureSupFabrication"%>" style="margin-right: 10px">Valider</a>
                            <% } %>
                            <a class="btn btn-secondary pull-right"  href="<%= lien + "?but="+ pageModif +"&id=" + id%>" style="margin-right: 10px">Modifier</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%=pc.getModalHtml("modalContent")%>
<%
    } catch (Exception e) {
        e.printStackTrace();
    } %>