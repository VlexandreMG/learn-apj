<%-- 
    Document   : as-produits-fiche
    Created on : 1 d�c. 2016, 10:40:08
    Author     : Joe
--%>

<%@page import="produits.*"%>
<%@ page import="user.*" %>
<%@ page import="bean.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="utilitaire.*" %>
<%@ page import="affichage.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%
    try{

    
    UserEJB u=(user.UserEJB) session.getValue("u");
    IngredientsLib a = new IngredientsLib();
    a.setNomTable("as_ingredients_lib");
    DecimalFormat df = new DecimalFormat("0.##################");
    PageConsulte pc = pc = new PageConsulte(a, request, u);//ou avec argument liste Libelle si besoin

    String isDispo=pc.getChampByName("etatlib").getValeur();
    //pc.getChampByName("id").setLibelle("ID");
    pc.getChampByName("unite").setLibelle("Unit&eacute;");
    pc.getChampByName("libelle").setLibelle("Libell&eacute;");
    pc.getChampByName("typestock").setLibelle("Type de stock");
    pc.getChampByName("composelib").setLibelle("Compos&eacute;");
    pc.getChampByName("IdDepartementLib").setLibelle("D&eacute;partement");
    pc.getChampByName("REFQUALIFICATIONLIB").setLibelle("Classification");
    pc.getChampByName("REFPOSTLIB").setLibelle("Poste");
    pc.getChampByName("pu").setLibelle("Prix Unitaire");
    pc.getChampByName("nbpiece").setLibelle("Contenu du carton");
    pc.getChampByName("quantiteparpack").setLibelle("Quantit&eacute; par pack");
    pc.getChampByName("bienOuServ").setLibelle("Bien ou Service");
    pc.getChampByName("categorieingredient").setLibelle("Cat&eacute;gorie d'ingr&eacute;dient");
    pc.getChampByName("etatlib").setLibelle("Disponibilit&eacute;");
    pc.getChampByName("pv").setLibelle("Prix de vente");
    pc.getChampByName("typeProduit").setVisible(false);
    pc.getChampByName("idligne").setVisible(false);
    pc.getChampByName("parfums").setVisible(false);
    pc.getChampByName("actif").setVisible(false);
    pc.getChampByName("Compte_sortie_stock").setLibelle("Compte de variation de stock");
    pc.getChampByName("Compte_stock").setLibelle("Compte de stock");
    pc.getChampByName("compte_ristourne").setLibelle("Compte de ristourne");
    pc.getChampByName("idFamilleLib").setVisible(false);
    pc.getChampByName("compte_vente").setLibelle("Compte de produits(vente)");
    pc.getChampByName("compte_achat").setLibelle("Compte de charges(achat)");
    pc.getChampByName("tva").setLibelle(" Taxe de Valeur Ajout&eacute; ( TVA )");
    pc.getChampByName("idFournisseur").setVisible(false);
    pc.getChampByName("idFournisseurLib").setLibelle("Fournisseur");
    pc.getChampByName("Libellevente").setLibelle("Libelle Vente");
    pc.getChampByName("bienOuServ").setVisible(false);
    pc.getChampByName("REFPOSTLIB").setVisible(false);
    pc.getChampByName("REFQUALIFICATIONLIB").setVisible(false);
    pc.getChampByName("photo").setVisible(false);
    pc.getChampByName("daty").setVisible(false);
    pc.getChampByName("id").setVisible(false);
    pc.getChampByName("calorie").setLibelle("Poids en Kg");
    pc.getChampByName("REFQUALIFICATION").setVisible(false);
    pc.getChampByName("REFPOST").setVisible(false);
    pc.getChampByName("idcategorieingredient").setVisible(false);
    pc.getChampByName("idcategorie").setVisible(false);
    pc.getChampByName("compose").setVisible(false);
    pc.getChampByName("typeProduitLib").setLibelle("Type produit");

    pc.setTitre("Consultation composant");
    Ingredients base = (Ingredients) (pc.getBase());
    RecetteLib[] liste = base.getRecette(null, null);
    Recette[] listeBase = base.decomposerBase(null);
    RecetteLib[] listerecette = base.getRecetteIngredient(null, null);
    double montantTotal = AdminGen.calculSommeDouble(listeBase, "qtetotal");
    if(base.getCompose()>0) pc.getChampByName("pu").setValeurDirect(Utilitaire.formaterAr (montantTotal));
    if(base.getCompose()==0) pc.getChampByName("pu").setValeurDirect(Utilitaire.formaterAr(base.getPu()));

    boolean isDG=false;
    if(u.getUser().isSuperUser()) isDG=true;

    try {
        // insertion recette
        Recette proc = new Recette();
        PageInsert pi = new PageInsert(proc, request, u);
        pi.setLien((String) session.getValue("lien"));
        pi.getFormu().getChamp("idProduits").setLibelle("Produits");
        pi.getFormu().getChamp("idProduits").setAutre("readonly");
        pi.getFormu().getChamp("idproduits").setDefaut(request.getParameter("id"));
        pi.getFormu().getChamp("unite").setLibelle("Unit&eacute;");
        pi.getFormu().getChamp("unite").setAutre("readonly");
        pi.getFormu().getChamp("idingredients").setLibelle("Ingr&eacute;dient");
        pi.getFormu().getChamp("idingredients").setPageAppelComplete("produits.IngredientsLib", "id", "as_ingredients_lib","unite", "unite");
        pi.getFormu().getChamp("quantite").setLibelle("Quantit&eacute;");

        pi.getFormu().getChamp("idProduits").setAutre("readonly");
        pi.getFormu().setNbColonne(2);

        pi.preparerDataFormu();
%>
<style>
        .col-md-12.cardradius {
        border: none;
        border-top: none !important;
        padding: 0;
        margin-bottom: 20px;
    }

</style>
<div class="content-wrapper onepage-container">
    <h1 class="main-title"><a href="<%=(String) session.getValue("lien")%>?but=produits/as-ingredients-liste.jsp"><i class="fa fa-angle-left"></i></a><%=pc.getTitre()%></h1>
    <div class="row m-0">
        <div class="col-md-12 mb-5" style="padding-bottom: 0;padding-top: 10px;">
            <div class="box-fiche">
                <div class="box">
                    <div class="box-body">
                        <%
                            out.println(pc.getHtml());
                        %>
                        <br/>
<%--                        nomColonneMere=IDPRODUITS--%>
                        <a class="btn btn-secondary pull-right"  href="<%=(String) session.getValue("lien") + "?but=apresTarif.jsp&acte=dupliquer&id=" + request.getParameter("id")%>&classe=produits.Ingredients&nomtable=AS_INGREDIENTS&nomClasseFille=produits.Recette&nomColonneMere=idproduits&bute=produits/as-ingredients-fiche.jsp" style="margin-right: 10px">Dupliquer</a>
                        <a class="btn btn-secondary pull-right"  href="<%=(String) session.getValue("lien") + "?but=produits/as-ingredients-saisie.jsp&id=" + request.getParameter("id")+"&acte=update"%>" style="margin-right: 10px">Modifier</a>
                        <a class="btn btn-secondary pull-right"  href="<%=(String) session.getValue("lien") + "?but=produits/tarifIngredients/tarifIngredients-saisie.jsp&idIng=" + request.getParameter("id")%>" style="margin-right: 10px">Ajouter Tarif</a>
                        <%  if(isDispo.equals("INDISPONIBLE")) {%> <a class="btn btn-primary pull-right"  href="<%=(String) session.getValue("lien") + "?but=apresTarif.jsp&classe=produits.Ingredients&acte=disponible&isdispo=true&&id=" + request.getParameter("id")%>" style="margin-right: 10px">Disponible</a><%}%>
                        <%  if(isDispo.equals("DISPONIBLE")) { %> <a class="btn btn-secondary pull-right"  href="<%=(String) session.getValue("lien") + "?but=apresTarif.jsp&classe=produits.Ingredients&acte=disponible&isdispo=false&id=" + request.getParameter("id")%>" style="margin-right: 10px">Indisponible</a><%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <h1 class="h520pxSemibold m-0" >Ajout Composant</h1>
    <div class="row m-0">
        <div class="col-md-12 mb-5" >
            <form id="maForme"  onsubmit='insertAjING(event)' >
                <div class="box-fiche">
                    <div class="box">
                        <div class="box-body">

                            <%--                            <table class="table table-bordered">--%>
                            <%--                                <thead>--%>
                            <%--                                    <tr>--%>
                            <%--                                        <th>Idproduit</th>--%>
                            <%--                                        <th colspan="2" >Ingredient</th>--%>
                            <%--                                        <th>Quantit&eacute;</th>--%>

                            <%--                                    </tr>--%>
                            <%--                                </thead>--%>
                            <%--                                <tbody>--%>
                            <%--                                    <tr>--%>
                            <%--                                        <td  align="center"><input name="idproduits" type="hidden" value="<%=request.getParameter("id")%>"><%=request.getParameter("id")%></td>--%>
                            <%--                                        <td  align="center">--%>
                            <%--                                            <input name="idingredientslibelle" type="textbox" class="form-control" id="idingredientslibelle" value="" tabindex="2" readonly ><input type="hidden" value="" name="idingredients" id="idingredients">--%>
                            <%--                                        </td>--%>
                            <%--                                        <td>--%>
                            <%--                                            <input name="choix" type="button" class="submit" onclick="pagePopUp('choix/listeIngredientChoix.jsp?champReturn=idingredients;idingredientslibelle&amp;apresLienPageAppel=')" value="...">--%>
                            <%--                                        </td>--%>
                            <%--                                        <td width="14%" align="center">--%>
                            <%--                                            <input name="quantite" type="textbox" class="form-control" id="quantite" value="0" onblur="calculer('quantite')" tabindex="3">--%>
                            <%--                                        </td>--%>

                            <%--                                    </tr>--%>
                            <%--                                </tbody>--%>
                            <%--                            </table>--%>
                            <%
                                pi.getFormu().makeHtmlInsertTabIndex();
                                out.println(pi.getFormu().getHtmlInsert());
//        out.println(pi.getHtmlAddOnPopup());
                            %>

                            <input name="acte" type="hidden" id="nature" value="insert">
                            <input name="bute" type="hidden" id="bute" value="produits/as-ingredients-fiche.jsp&id=<%=request.getParameter("id")%>">
                            <input name="classe" type="hidden" id="classe" value="produits.Recette">
                        </div>
                        <%--                        <div class="box-footer">--%>
                        <%--                            <% //if(u.getUser().getLoginuser().equalsIgnoreCase("narindra") || u.getUser().getLoginuser().equalsIgnoreCase("Baovola") ) { %>--%>
                        <%--                            <button type="submit" name="Submit2" class="btn btn-success pull-right" style="margin-right: 25px;">Ajouter ligne</button>--%>
                        <%--                            <%  //} %>--%>

                        <%--                        </div>--%>


                    </div>
                </div>
            </form>
        </div>
    </div>


    <h1 class="h520pxSemibold m-0">Composition</h1>
    <div class="row m-0">
        <div class="col-md-3"></div>
        <div class="col-md-6 lol nopadding mb-5" style="padding-bottom: 0;padding-top: 10px;" >
            <div class="box-fiche box-fiche-space">
                <div class="box">

                    <form  id="incident" onsubmit='modifEtatMultING(event)' enctype="multipart/form-data">
                        <!--<form action="<%=(String) session.getValue("lien")%>?but=modifierEtatMultiple.jsp" method="post" name="incident" id="incident"> -->
                        <div class="box-body table-responsive">
                            <input type="hidden" name="bute" value="produits/as-ingredients-fiche.jsp&id=<%=request.getParameter("id")%>"/>
                            <input type="hidden" name="acte" id="acte"/>




                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="contenuetable" align="center" valign="top" >
                                            <input onclick="CocheToutCheckbox(this, 'id')" type="checkbox">
                                        </th>
                                        <th class="contenuetable" >Ingr&eacute;dient</th>
                                        <th class="contenuetable">Quantit&eacute;</th>

                                    <th class="contenuetable">Unit&eacute;</th>
                                </tr>
                                </thead>

                                <tbody>
                                <%
                                    for (int i = 0; i < liste.length; i++) {
                                %>
                                <tr onmouseover="this.style.backgroundColor = '#EAEAEA'" onmouseout="this.style.backgroundColor = ''">
                                    <td align="center">
                                        <input type="checkbox" value="<%=liste[i].getId()%>_<%=i%>" name="ids" id="<%=liste[i].getId()%>_<%=i%>">
                                    </td>

                                    <td  align="center"><a href="<%=(String) session.getValue("lien")%>?but=produits/as-ingredients-fiche.jsp&id=<%=liste[i].getIdingredients()%>"><%=liste[i].getLibelleingredient()%></a></td>
                                    <td width="14%" align="center"><input type="text" id="quantite<%=i%>" name="quantite" value="<%=(new DecimalFormat("#.########")).format(liste[i].getQuantite())%>" onchange="synchro(this,<%=liste[i].getId()%>_<%=i%>.value)"></td>
                                    <td  align="right"><%=liste[i].getValunite()%></td>
                                </tr>
                                <%
                                    }
                                %>

                                </tbody>
                            </table>

                            <div class="box-footer">
                                <button type="submit" name="acte" value="modifier_recette" class="btn btn-secondary pull-right" style="margin-right: -8px;" onclick="document.getElementById('acte').value='modifier_recette'" >Modifier</button>
                                <button type="submit" name="acte" value="supprimer_recette" class="btn btn-danger pull-left" style="position: absolute;right: 0;margin-right: 110px;" onclick="document.getElementById('acte').value='supprimer_recette'" >Supprimer</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <h1 class="h520pxSemibold m-0">D&eacute;composition finale</h1>
    <div class="row m-0">
        <div class="col-md-3"></div>
        <div class="col-md-6 lol nopadding mb-5">
            <div class="box-fiche box-fiche-space">
                <div class="box">
                    <div class="box-body table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="contenuetable">Ingr&eacute;dient</th>
                                <th class="contenuetable">Quantit&eacute;</th>
                                <th class="contenuetable">Unit&eacute;</th>
                                <th class="contenuetable">Prix Unitaire</th>
                                <th class="contenuetable">Montant</th>
                            </tr>
                            </thead>

                            <tbody>
                            <%
                                for (int i = 0; i < listeBase.length; i++) {
                            %>
                            <tr onmouseover="this.style.backgroundColor = '#EAEAEA'" onmouseout="this.style.backgroundColor = ''">
                                <td  align="center"><%=listeBase[i].getLibIngredients()%></td>
                                <td  align="right"><%=df.format(listeBase[i].getQuantite())%></td>
                                <td  align="right"><%=listeBase[i].getUnite()%></td>
                                <td  align="right"><%=Utilitaire.formaterAr(listeBase[i].getQteav())%></td>
                                <td  align="right"><%=Utilitaire.formaterAr(listeBase[i].getQtetotal())%></td>

                            </tr>
                            <%
                                }
                            %>

                            </tbody>
                        </table>

                        <h3 class="asi-fiche-cout h617pxSemibold"> Co&ucirc;t de revient : <%=Utilitaire.formaterAr(montantTotal)%> Ar</h3>
                        <% if(base.getPv()>0){ %>
                            <h3 class="asi-fiche-cout h617pxSemibold m-0"><% if(base.getPv()>0){ %>   Marge brute : <%=Utilitaire.formaterAr(base.getPv()- montantTotal)%> Ar <% } %></h3>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h1 class="h520pxSemibold m-0" >Autres Composants concern&eacute;s</h1>
    <div class="row m-0">
        <div class="col-md-3"></div>
        <div class="col-md-6 lol nopadding mb-5">
            <div class="box-fiche box-fiche-space">
                <div class="box">
                    <div class="box-body table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="contenuetable">Produit</th>
                                <th class="contenuetable">Quantit&eacute;</th>
                                <th class="contenuetable">Unit&eacute;</th>
                            </tr>
                            </thead>

                            <tbody>
                            <%
                                for (int i = 0; i < listerecette.length; i++) {
                            %>
                            <tr onmouseover="this.style.backgroundColor = '#EAEAEA'" onmouseout="this.style.backgroundColor = ''">
                                <td  align="center"><a href="<%=(String) session.getValue("lien")%>?but=produits/as-ingredients-fiche.jsp&id=<%=listerecette[i].getIdproduits()%>"><%=listerecette[i].getLibelleproduit()%></a></td>
                                <td  align="right"><%=listerecette[i].getQuantite()%></td>
                                <td  align="right"><%=listerecette[i].getValunite()%></td>
                            </tr>
                            <%
                                }
                            %>

                            </tbody>
                        </table>

                    </div>

                </div>
            </div>
        </div>
    </div>
    <%if(base.getCompose()==0){
        HistoriquePrixIng[] histopu =base.getHistoriquePu(null,"pu","HISTORIQUEPUINGTRIE");
    %>
    <h1 >Historique prix achat</h1>
    <div class="row m-0">
        <div class="col-md-3"></div>
        <div class="col-md-6 lol nopadding mb-5">
            <div class="box-fiche">
                <div class="box box-asi-fiche">
                    <div class="box-body table-responsive">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="contenuetable">Date</th>
                                    <th class="contenuetable">Prix unitaire d&#39; Achat</th>

                                <th class="contenuetable">Remarque</th>
                            </tr>
                            </thead>

                            <tbody>
                            <%
                                if( histopu!=null&&histopu.length>0){
                                    for (int i = 0; i < histopu.length; i++) {
                            %>
                            <tr onmouseover="this.style.backgroundColor = '#EAEAEA'" onmouseout="this.style.backgroundColor = ''">

                                <td  align="right"><%=Utilitaire.formatterDaty(histopu[i].getDaty())%></td>
                                <td  align="right"><%=Utilitaire.formaterAr(histopu[i].getPu())%> Ar</td>

                                <td  align="right"><%=Utilitaire.remplacerNull(histopu[i].getRemarque())%></td>

                            </tr>
                            <%
                                    }}
                            %>

                            </tbody>
                        </table>

                    </div>

                </div>
            </div>
        </div>
    </div>
    <%}%>
    <%if(base.getCompose()>0){
        HistoriquePrixIng[] histopv =base.getHistoriquePu(null,"pv","HISTORIQUEPVINGTRIE");%>
    <h1 class="h520pxSemibold m-0" >Historique prix de vente</h1>
    <div class="row lolHisto m-0">
        <%--        <div class="col-md-3"></div>--%>
        <div class="col-md-6 lol nopadding mb-5 lolHisto-bas">
            <div class="box-fiche">
                <div class="box box-asi-fiche">
                    <div class="box-body table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="contenuetable">Date</th>
                                <th class="contenuetable">Prix unitaire</th>

                                <th class="contenuetable">Remarque</th>
                            </tr>
                            </thead>

                            <tbody>
                            <%
                                if( histopv!=null&&histopv.length>0){
                                    for (int i = 0; i < histopv.length; i++) {
                            %>
                            <tr onmouseover="this.style.backgroundColor = '#EAEAEA'" onmouseout="this.style.backgroundColor = ''">
                                <td  align="right"><%=Utilitaire.formatterDaty(histopv[i].getDaty())%></td>
                                <td  align="right"><%=Utilitaire.formaterAr(histopv[i].getPu())%> Ar</td>

                                <td  align="right"><%=Utilitaire.remplacerNull(histopv[i].getRemarque())%></td>
                            </tr>
                            <%
                                    }}
                            %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%}%>
    <%if(base.getCompose()>0){
        TarifIngredientsLib[] histopv =base.getHistoriqueTarif(null,"TARIF_INGREDIENTS_MAX_LIB");%>
    <h1 class="h520pxSemibold m-0" >Historique Tarif</h1>
    <div class="row lolHisto m-0">
        <%--        <div class="col-md-3"></div>--%>
        <div class="col-md-12 nopadding mb-5 lolHisto-bas">
            <div class="box-fiche">
                <div class="box box-asi-fiche">
                    <div class="box-body table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="contenuetable">Date</th>
                                <th class="contenuetable">Type client</th>
                                <th class="contenuetable">Prix unitaire</th>

                            </tr>
                            </thead>

                            <tbody>
                            <%
                                if( histopv!=null&&histopv.length>0){
                                    for (int i = 0; i < histopv.length; i++) {
                            %>
                            <tr onmouseover="this.style.backgroundColor = '#EAEAEA'" onmouseout="this.style.backgroundColor = ''">
                                <td  align="right"><%=Utilitaire.formatterDaty(histopv[i].getDaty())%></td>
                                <td  align="right"><%=Utilitaire.remplacerNull(histopv[i].getIdtypeclientlib())%></td>
                                <td  align="right"><%=Utilitaire.formaterAr(histopv[i].getPrixUnitaire())%> Ar</td>

                            </tr>
                            <%
                                    }}
                            %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%}%>
</div>
</div>
<%--<style>--%>
<%--    #maForme .col-md-6 {--%>
<%--        width: 100%--%>
<%--    }--%>
<%--    .lol--%>
<%--    {--%>
<%--        padding: 0 !important;--%>
<%--    }--%>
<%--    .lol #maForme .box-body .cardradius {--%>
<%--        padding: 0 !important;--%>
<%--        margin: 0 !important;--%>
<%--    }--%>
<%--    .lolHisto {--%>
<%--        display: flex;--%>
<%--        justify-content: center;--%>
<%--    }--%>
<%--    .lolHisto-bas {--%>
<%--        width: 71% !important;--%>
<%--    }--%>
<%--    .cardradius > div {--%>
<%--        margin-bottom: 0px  !important;--%>
<%--    }--%>
<%--</style>--%>
<%
    } catch (Exception e)
    {
        e.printStackTrace();
    }
%>
<script>
    const secondRow = document.querySelectorAll('.row')[3];
    const colMd3Divs = secondRow.querySelectorAll('div.col-md-3');
    colMd3Divs.forEach(div => {
        div.classList.remove('col-md-3');
        div.classList.add('col-md-1');
    });
    const colMd6Div = secondRow.querySelector('div.col-md-6');
    if (colMd6Div) {
        colMd6Div.classList.remove('col-md-6');
        colMd6Div.classList.add('col-md-12');
    }
</script>

<%
    } catch (Exception e)
    {
        e.printStackTrace();
    }
%>