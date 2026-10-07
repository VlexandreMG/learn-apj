<%@page import="affichage.PageRecherche"%>
<%@ page import="historique.HistoInsert" %>
<%@ page import="utilitaire.Utilitaire" %>

<% try{
    HistoInsert t = new HistoInsert();
    t.setNomTable("v_histoinsert");
    String listeCrt[] = {};
    String listeInt[] = {};
    String libEntete[] = {"refobjet","remarque","datehistorique","heure","action","objet"};
    PageRecherche pr = new PageRecherche(t, request, listeCrt, listeInt, 4, libEntete, libEntete.length);
    pr.setTitre("Recherche Global");
    if ((request.getParameter("remarque") != null) && (request.getParameter("remarque").compareToIgnoreCase("") != 0)) {
        pr.setAWhere(" and upper(remarque) like upper('%"+request.getParameter("remarque")+"%')");
    }
    pr.setNpp(20);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    pr.setApres("recherche-global.jsp&remarque="+request.getParameter("remarque"));
    String[] colSomme = null;
    pr.creerObjetPage(libEntete, colSomme);
    String libEnteteAffiche[] = {"R&eacute;f&eacute;rence Objet","Libelle","Date","Heure","Action","Objet"};
    pr.getTableau().setLibelleAffiche(libEnteteAffiche);
%>

<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box cardradius box-solid" style="padding: 16px;border-radius: 16px;">
                    <div style="padding: 5px 0px 10px 0px;"></div>
                    <div class="box-body table-responsive no-padding">
                        <div id="selectnonee">
                            <table style="margin-bottom:0;" width="100%" border="0" align="center" cellpadding="3"
                                   cellspacing="3" class="table table-hover table-striped  ">
                                <thead>
                                <tr class="head">
                                    <th width="19%" align="center" valign="top" class="contenuetable"
                                        style="background-color:#2c3d91"><a
                                            href="module.jsp?but=historique/historique-liste.jsp&amp;numPag=1&amp;colonne=&amp;ordre=&amp;colAffiche1=refobjet&amp;colAffiche2=remarque&amp;colAffiche3=datehistorique&amp;colAffiche4=heure&amp;colAffiche5=action&amp;colAffiche6=objet&amp;triCol=yes&amp;newcol=refobjet"
                                            style="color:white;">R&eacute;f&eacute;rence Objet<i class="fa fa-sort"
                                                                                   style="color: white;font-size: 15px;margin-left: 5px;"></i></a></th>
                                    <th width="20%" align="center" valign="top" class="contenuetable"
                                        style="background-color:#2c3d91"><a
                                            href="module.jsp?but=historique/historique-liste.jsp&amp;numPag=1&amp;colonne=&amp;ordre=&amp;colAffiche1=refobjet&amp;colAffiche2=remarque&amp;colAffiche3=datehistorique&amp;colAffiche4=heure&amp;colAffiche5=action&amp;colAffiche6=objet&amp;triCol=yes&amp;newcol=remarque"
                                            style="color:white;">Libelle<i class="fa fa-sort"
                                                                           style="color: white;font-size: 15px;margin-left: 5px;"></i></a></th>
                                    <th width="19%" align="center" valign="top" class="contenuetable"
                                        style="background-color:#2c3d91"><a
                                            href="module.jsp?but=historique/historique-liste.jsp&amp;numPag=1&amp;colonne=&amp;ordre=&amp;colAffiche1=refobjet&amp;colAffiche2=remarque&amp;colAffiche3=datehistorique&amp;colAffiche4=heure&amp;colAffiche5=action&amp;colAffiche6=objet&amp;triCol=yes&amp;newcol=datehistorique"
                                            style="color:white;">Date<i class="fa fa-sort"
                                                                        style="color: white;font-size: 15px;margin-left: 5px;"></i></a></th>
                                    <th width="19%" align="center" valign="top" class="contenuetable"
                                        style="background-color:#2c3d91"><a
                                            href="module.jsp?but=historique/historique-liste.jsp&amp;numPag=1&amp;colonne=&amp;ordre=&amp;colAffiche1=refobjet&amp;colAffiche2=remarque&amp;colAffiche3=datehistorique&amp;colAffiche4=heure&amp;colAffiche5=action&amp;colAffiche6=objet&amp;triCol=yes&amp;newcol=heure"
                                            style="color:white;">Heure<i class="fa fa-sort"
                                                                         style="color: white;font-size: 15px;margin-left: 5px;"></i></a></th>
                                    <th width="20%" align="center" valign="top" class="contenuetable"
                                        style="background-color:#2c3d91"><a
                                            href="module.jsp?but=historique/historique-liste.jsp&amp;numPag=1&amp;colonne=&amp;ordre=&amp;colAffiche1=refobjet&amp;colAffiche2=remarque&amp;colAffiche3=datehistorique&amp;colAffiche4=heure&amp;colAffiche5=action&amp;colAffiche6=objet&amp;triCol=yes&amp;newcol=action"
                                            style="color:white;">Action<i class="fa fa-sort"
                                                                          style="color: white;font-size: 15px;margin-left: 5px;"></i></a></th>
                                </tr>
                                </thead>
        <%
            HistoInsert[] ob = (HistoInsert[]) pr.getTableau().getData();
            for (int i = 0; i < pr.getTableau().getData().length; i++) {
        %>
                                <tbody>
                                <tr style="height: 45px;" onmouseover="this.style.backgroundColor='#EAEAEA'"
                                    onmouseout="this.style.backgroundColor=''">
                                    <td class="contenuetable" width="16%" align="left"><a
                                            href="module.jsp?but=<%=ob[i].getFiche()%>&amp;id=<%=ob[i].getRefObjet()%>"><%=ob[i].getRefObjet()%>
                                    </a> </td>
                                    <td class="contenuetable" width="16%" align="left"><%=ob[i].getRemarque()%> </td>
                                    <td class="contenuetable" width="16%" align="left"><%=Utilitaire.formatterDaty(ob[i].getDateHistorique())%></td>
                                    <td class="contenuetable" width="16%" align="left"><%=ob[i].getHeure()%> </td>
                                    <td class="contenuetable" width="16%" align="left"><%=ob[i].getAction()%> </td>
                                    <td style="padding: 4px 15px;">
                                        <button class="more_info" id="ellipsis-0" style="border: none; background: none;">
                                            <i class="fa fa-ellipsis-v"></i>
                                        </button>
                                        <div id="more-action-0" class="mode_action" style="display: none;">
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
        <%}%>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    }catch(Exception e){

        e.printStackTrace();
    }
%>