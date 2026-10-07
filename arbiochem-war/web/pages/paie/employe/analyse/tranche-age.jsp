<%@ page import="java.util.Map" %>
<%@ page import="bean.CGenUtil" %>
<%@ page import="paie.log.LogPersonnel" %>
<%@ page import="bean.TypeObjet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
    String iddepartement = request.getParameter("iddepartement");
    String errorMessage = null;

    LogPersonnel logPersonnel = null;
    Map<String, Integer> dataMap = null;
    String chartTitle = "";
    String chartLabel = "";

    try {
        logPersonnel = new LogPersonnel();

        // Récupérer les données par tranche d'âge avec filtre optionnel de département
        dataMap = logPersonnel.getPersonnelParTrancheAge(iddepartement, null);

        // Construction du titre dynamique
        StringBuilder titleBuilder = new StringBuilder("Répartition du personnel par tranche d'âge");
        if(iddepartement != null && !iddepartement.isEmpty()) {
            // Récupérer le nom du département
            TypeObjet dept = new TypeObjet();
            dept.setNomTable("DEPARTEMENT");
            dept.setId(iddepartement);
            TypeObjet[] depts = (TypeObjet[]) CGenUtil.rechercher(dept, null, null, "");
            if(depts != null && depts.length > 0) {
                titleBuilder.append(" - Département: ").append(depts[0].getDesce());
            }
        }
        chartTitle = titleBuilder.toString();
        chartLabel = "Nombre d'employés";

    } catch(Exception e) {
        e.printStackTrace();
        errorMessage = "Erreur lors de la récupération des données: " + e.getMessage();
    }

    // Préparer les données pour Chart.js
    StringBuilder labelsJs = new StringBuilder("[");
    StringBuilder dataJs = new StringBuilder("[");
    boolean first = true;

    if(dataMap != null && !dataMap.isEmpty()) {
        for(Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            if(!first) {
                labelsJs.append(",");
                dataJs.append(",");
            }
            labelsJs.append("\"")
                .append(
                    entry.getKey().equals("HORS_TRANCHE")
                        ? "Hors tranche d'âge"
                        : entry.getKey() + " ans"
                ).append("\"");
            dataJs.append(entry.getValue());
            first = false;
        }
    }
    labelsJs.append("]");
    dataJs.append("]");
%>

<div class="content-wrapper">
    <section class="content-header">
        <h1>Analyse des tranches d'âge du personnel</h1>
    </section>
    <section class="content">
        <%
            if(errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <div class="alert alert-warning alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <h4><i class="icon fa fa-warning"></i> Attention!</h4>
            <%= errorMessage %>
        </div>
        <%
            }
        %>

        <form action="<%=session.getAttribute("lien")%>?but=paie/employe/analyse/tranche-age.jsp" name="forms" method="post" id="forms">
            <div class="row">
                <div class="col-md-3  mb-5">
                    <div class="form-input w-100">
                        <label class="input-label" for="iddepartement">Département :</label>
                        <select class="form-control w-100" name="iddepartement" id="iddepartement" onchange="sendForm()" >
                            <option value="">Tous (Sans département)</option>
                            <%
                                try {
                                    TypeObjet typedepObj = new TypeObjet();
                                    typedepObj.setNomTable("DEPARTEMENT");
                                    TypeObjet[] typedeps = (TypeObjet[]) CGenUtil.rechercher(typedepObj, null, null, "");
                                    if(typedeps != null) {
                                        for(TypeObjet m : typedeps) {
                            %>
                            <option value="<%= m.getId() %>" <%= (iddepartement != null && iddepartement.equals(m.getId())) ? "selected" : "" %>>
                                <%= m.getDesce() %>
                            </option>
                            <%
                                        }
                                    }
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                            %>
                        </select>
                    </div>
                </div>
            </div>
        </form>

        <div class="row">
            <div class="col-md-12  mb-5">
                <div class="box">
                    <div class="box-body box-body-chart">
                        <%
                            if(dataMap == null || dataMap.isEmpty()) {
                        %>
                        <div class="alert m-0">
                            <h4>Aucune donnée disponible</h4>
                            <p>Aucun personnel n'a été trouvé pour les critères sélectionnés.</p>
                        </div>
                        <%
                        } else {
                        %>
                        <canvas id="chart-tranche-age" class="canvas-fullheight"></canvas>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>

        <!-- Tableau récapitulatif -->
        <%
            if(dataMap != null && !dataMap.isEmpty()) {
                int total = dataMap.values().stream().mapToInt(Integer::intValue).sum();
        %>
        <h3 class="h520pxSemibold col-md-12 nopadding m-0">Détails par tranche d'âge</h3>
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th class="contenuetable">Tranche d'âge</th>
                                    <th class="contenuetable">Nombre d'employés</th>
                                    <th class="contenuetable">Pourcentage</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for(Map.Entry<String, Integer> entry : dataMap.entrySet()) {
                                        double pourcentage = (entry.getValue() * 100.0) / total;
                                %>
                                <tr>
                                    <td>
                                        <strong>
                                            <%= entry.getKey().equals("HORS_TRANCHE")
                                                    ? "Hors tranche d'âge"
                                                    : entry.getKey() + " ans"
                                            %>
                                        </strong>
                                    </td>
                                    <td><%= entry.getValue() %></td>
                                    <td><%= String.format("%.2f", pourcentage) %> %</td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr class="bg-info">
                                    <td><strong>TOTAL</strong></td>
                                    <td><strong><%= total %></strong></td>
                                    <td><strong>100.00 %</strong></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </section>
</div>
<script>
    function sendForm() {
        document.forms.submit();
    }
</script>

<script>
    (function(){
        console.log('=== DEBUG Chart.js - Tranche Age ===');
        console.log('typeof Chart:', typeof Chart);
        console.log('Labels:', <%= labelsJs.toString() %>);
        console.log('Data:', <%= dataJs.toString() %>);

        <% if(dataMap != null && !dataMap.isEmpty()) { %>
        var ctx = document.getElementById('chart-tranche-age');
        console.log('Canvas element:', ctx);

        if(ctx) {
            try {
                ctx = ctx.getContext('2d');
                console.log('Canvas context:', ctx);

                var chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: <%= labelsJs.toString() %>,
                        datasets: [{
                            label: "<%= chartLabel %>",
                            data: <%= dataJs.toString() %>,
                            backgroundColor: [
                                'rgba(75, 192, 192, 0.6)',
                                'rgba(54, 162, 235, 0.6)',
                                'rgba(153, 102, 255, 0.6)',
                                'rgba(255, 159, 64, 0.6)',
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(201, 203, 207, 0.6)'
                            ],
                            borderColor: [
                                'rgba(75, 192, 192, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)',
                                'rgba(255, 99, 132, 1)',
                                'rgba(201, 203, 207, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: {
                                    stepSize: 1
                                }
                            }
                        },
                        plugins: {
                            legend: {
                                display: true,
                                position: 'top'
                            },
                            title: {
                                display: true,
                                text: "<%= chartTitle %>",
                                font: {
                                    size: 16
                                }
                            }
                        }
                    }
                });
                console.log('Chart created successfully:', chart);
            } catch(error) {
                console.error('Erreur lors de la création du graphique:', error);
            }
        } else {
            console.error('Canvas element not found!');
        }
        <% } else { %>
        console.log('Pas de données à afficher');
        <% } %>
    })();
</script>
