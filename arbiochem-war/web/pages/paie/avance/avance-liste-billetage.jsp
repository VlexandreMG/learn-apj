<%@page import="affichage.Liste"%>
<%@page import="bean.TypeObjet"%>
<%@page import="affichage.PageRecherche"%>
<%@ page import="paie.avance.Avance" %>
<%@ page import="utils.ConstantePaie" %>
<%@ page import="bean.ClassMAPTable" %>

<% 
    try{
    Avance lv = new Avance();
    lv.setNomTable("AVANCE_LIB2");

    String idType = "";

      
    String listeCrt[] = {"id", "idpersonnel"};
    String listeInt[] = {"montant"};
    String libEntete[] = {"id", "idpersonnel","montant","20000", "10000","5000", "2000","1000","500","200","100"};


    PageRecherche pr = new PageRecherche(lv, request, listeCrt, listeInt, 3, libEntete, libEntete.length);
    pr.setUtilisateur((user.UserEJB) session.getValue("u"));
    pr.setLien((String) session.getValue("lien"));
    if(request.getParameter("etat") != null && request.getParameter("etat").compareToIgnoreCase("") != 0) {
        pr.setAWhere(pr.getAWhere()+" and etat"+String.valueOf(request.getParameter("etat")));
    }

    if(request.getParameter("idType") != null && request.getParameter("idType").compareToIgnoreCase("") != 0) {
        pr.setAWhere(" and idTypeAvance = '" + request.getParameter("idType") + "'");
        idType = request.getParameter("idType");
    }

        Liste[] liste = new Liste[1];
        TypeObjet mp = new TypeObjet();
        liste[0] = new Liste("typeavance",mp,"val","id");
        pr.getFormu().changerEnChamp(liste);

    pr.setTitre("Liste des avances");

    pr.getFormu().getChamp("idpersonnel").setLibelle("Personnel");




        pr.setApres("paie/avance/avance-liste.jsp&idType=PRU0447");
        String[] colSomme = null;
        pr.creerObjetPage(libEntete, colSomme);

       String[] lienTableau = {pr.getLien() + "?but=paie/avance/avance-fiche.jsp"};
       String colonneLien[] = {"id"};
       pr.getTableau().setLien(lienTableau);
       pr.getTableau().setColonneLien(colonneLien);

        String libEnteteAffiche[] =  {"ID", "Personnel","Montant","20000", "10000","5000", "2000","1000","500","200","100"};
        pr.getTableau().setLibelleAffiche(libEnteteAffiche);

        if (ConstantePaie.idAvanceExceptionnelle.equals(idType))
        {
            pr.getFormu().setAnotherButton(
                    "                <a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=paie/avance/avance-saisie.jsp&currentMenu=AMS007\">\n" +
                            "                    <i class=\"material-symbols-rounded\">add</i>Saisir une avance exceptionnelle " +
                            "                </a>"
            );
        } else if (ConstantePaie.idAvanceSurSalaire.equals(idType))
        {
            pr.getFormu().setAnotherButton(
                    "                <a class=\"btn btn-primary pull-right btn-small\" href=\"module.jsp?but=paie/avance/avance-quinzaine-saisie.jsp&currentMenu=AMS007\">\n" +
                            "                    <i class=\"material-symbols-rounded\">add</i>Saisir un avance sur salaire" +
                            "                </a>"
            );
        }

    String[] etatVal = {"!= 100", "=1", "=11"};
    String[] etatAff = {"Tous", "Cr&eacute;&eacute;(s)", "Vis&eacute;(s)"};
        String lien = (String) session.getValue("lien");


%>
<script>
    function changerDesignation() {
        document.incident.submit();
    }
</script>
<script>
    $(document).ready(function() {
        // Exécuter au chargement
        setTimeout(repartirBillets, 500);

        // Relancer si le tableau change (pagination/filtre)
        $(document).on('dynamicContentLoaded', function() {
            repartirBillets();
        });
    });

    function repartirBillets() {
        const coupures = [20000, 10000, 5000, 2000, 1000, 500, 200, 100];

        // 1. MISE À JOUR VISUELLE (ÉCRAN)
        $('.table-box table tbody tr').each(function() {
            const cells = $(this).find('td');
            if (cells.length >= 11) {
                let montant = parseMontant($(cells[2]).text());
                if (!isNaN(montant)) {
                    let reste = montant;
                    coupures.forEach((val, i) => {
                        let nb = Math.floor(reste / val);
                        reste = reste % val;
                        $(cells[i + 3]).text(nb > 0 ? nb : '');
                    });
                }
            }
        });

        // 2. MISE À JOUR DU CHAMP CACHÉ 'TABLE' (POUR LE PDF)
        updateHiddenTable(coupures);

        // 3. MISE À JOUR DU CHAMP CACHÉ 'CSV' (POUR EXCEL)
        updateHiddenCSV(coupures);
    }

    // Fonction pour nettoyer les montants (enlève espaces et caractères non numériques)
    function parseMontant(txt) {
        if(!txt) return 0;
        return parseFloat(txt.replace(/[^\d]/g, ''));
    }

    function updateHiddenTable(coupures) {
        let tableInput = $('input[name="table"]');
        if (tableInput.length === 0) return;

        // Le framework utilise '*' au lieu de '"' dans le champ 'table'
        let rawHtml = tableInput.val().replace(/\*/g, '"');
        let $tempDiv = $('<div>').append(rawHtml);

        $tempDiv.find('tbody tr').each(function() {
            let cells = $(this).find('td');
            let montant = parseMontant($(cells[2]).text());

            if (!isNaN(montant)) {
                let reste = montant;
                coupures.forEach((val, i) => {
                    let nb = Math.floor(reste / val);
                    reste = reste % val;
                    $(cells[i + 3]).text(nb > 0 ? nb : '');
                });
            }
        });

        // On ré-injecte en remettant les '*' pour ne pas casser le moteur PDF
        tableInput.val($tempDiv.html().replace(/"/g, '*'));
    }

    function updateHiddenCSV(coupures) {
        let csvInput = $('input[name="csv"]');
        if (csvInput.length === 0) return;

        let lines = csvInput.val().split('\n');
        for (let i = 1; i < lines.length; i++) {
            let cols = lines[i].split(';');
            if (cols.length < 3) continue;

            let montant = parseMontant(cols[2]);
            if (!isNaN(montant)) {
                let reste = montant;
                coupures.forEach((val, j) => {
                    let nb = Math.floor(reste / val);
                    cols[3 + j] = (nb > 0 ? nb : "");
                    reste = reste % val;
                });
                lines[i] = cols.join(';');
            }
        }
        csvInput.val(lines.join('\n'));
    }
</script>
<div class="content-wrapper">
    <section class="content-header">
        <h1><%= pr.getTitre() %></h1>
        
    </section>
    <section class="content">
        <form action="<%=pr.getLien()%>?but=<%= pr.getApres() %>" method="post" name="incident" id="incident">
            <%
                out.println(pr.getFormu().getHtmlEnsemble());
            %>
            <div class="row col-md-12 nopadding">
                <div class="col-md-2 nopadding">
                    &Eacute;tat :
                    <select name="etat" class="champ form-control" id="etat" onchange="changerDesignation()">
                        <%
                            String currentEtat = request.getParameter("etat");
                            if (currentEtat == null || currentEtat.equals("")) {
                                currentEtat = "!= 100";
                            }
                            for( int i = 0; i < etatAff.length; i++ ){ %>
                        <% if(etatVal[i].equalsIgnoreCase(currentEtat)) {%>
                        <option value="<%= etatVal[i] %>" selected> <%= etatAff[i] %> </option>
                        <% } else { %>
                        <option value="<%= etatVal[i] %>"> <%= etatAff[i] %> </option>
                        <% } %>
                        <%    }
                        %>
                    </select>
                </div>
            </div>
        </form>
        <%
            out.println(pr.getTableauRecap().getHtml());%>
        </br>
        <%
            out.println(pr.getTableau().getHtml());
            out.println(pr.getBasPage());
        %>
    </section>
</div>
<%
    }catch(Exception ex){
        ex.printStackTrace();
    }
%>