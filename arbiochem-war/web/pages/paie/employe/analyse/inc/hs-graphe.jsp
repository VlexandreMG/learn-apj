<%@ page import="java.util.Map" %>
<%@ page import="fabrication.HeureSupFabricationCPL" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Chargement de Chart.js depuis CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<%
    String dateDebutParam = request.getParameter("dateDebut");
    String dateFinParam = request.getParameter("dateFin");
    String iddep = request.getParameter("idDepartement");

    // Debug
    System.out.println("=== HS-GRAPHE.JSP DEBUG ===");
    System.out.println("dateDebutParam = [" + dateDebutParam + "]");
    System.out.println("dateFinParam = [" + dateFinParam + "]");
    System.out.println("iddep = [" + iddep + "]");

    // Conversion sécurisée des dates (optionnelles)
    java.sql.Date dateDebut = null;
    java.sql.Date dateFin = null;

    try {
        // Validation du format avant conversion (seulement si fourni)
        if(dateDebutParam != null && !dateDebutParam.trim().isEmpty() && dateDebutParam.matches("\\d{4}-\\d{2}-\\d{2}")) {
            dateDebut = java.sql.Date.valueOf(dateDebutParam);
        }

        if(dateFinParam != null && !dateFinParam.trim().isEmpty() && dateFinParam.matches("\\d{4}-\\d{2}-\\d{2}")) {
            dateFin = java.sql.Date.valueOf(dateFinParam);
        }
    } catch(Exception e) {
        // En cas d'erreur, on continue avec null (pas de filtre de dates)
        dateDebut = null;
        dateFin = null;
    }

    HeureSupFabricationCPL heureSup = null;
    Map<String, Double> dataMap = null;
    String chartTitle = "";
    String chartLabel = "";
    String errorMessage = null;

    try {
        heureSup = new HeureSupFabricationCPL();

        // Récupérer les données par type d'heures avec filtres optionnels
        dataMap = heureSup.getSommeHeuresSupParPeriode(dateDebut, dateFin, iddep, null);

        // Construction du titre dynamique
        chartTitle = "Répartition des heures supplémentaires";
        if(dateDebut != null && dateFin != null) {
            chartTitle += " (" + dateDebutParam + " au " + dateFinParam + ")";
        } else if(dateDebut != null) {
            chartTitle += " (à partir du " + dateDebutParam + ")";
        } else if(dateFin != null) {
            chartTitle += " (jusqu'au " + dateFinParam + ")";
        }
        chartLabel = "Nombre d'heures";

    } catch(java.sql.SQLException sqle) {
        sqle.printStackTrace();
        errorMessage = "Erreur de base de données: " + sqle.getMessage();
    } catch(Exception e) {
        e.printStackTrace();
        errorMessage = "Erreur lors de la récupération des données: " + e.getMessage();
    }

    // Préparer les données pour Chart.js avec les labels complets
    StringBuilder labelsJs = new StringBuilder("[");
    StringBuilder dataJs = new StringBuilder("[");
    boolean first = true;

    // Définir les labels complets pour chaque type
    java.util.Map<String, String> labelMap = new java.util.LinkedHashMap<>();
    labelMap.put("HS", "Heures Supplémentaires Normales");
    labelMap.put("JF", "Jours Fériés");
    labelMap.put("HD", "Heures Dimanche");
    labelMap.put("IF", "Indemnité de Fonction");

    if(dataMap != null && !dataMap.isEmpty()) {
        for(Map.Entry<String, Double> entry : dataMap.entrySet()) {
            if(!first) {
                labelsJs.append(",");
                dataJs.append(",");
            }
            String fullLabel = labelMap.get(entry.getKey());
            labelsJs.append("'").append(fullLabel != null ? fullLabel : entry.getKey()).append("'");
            dataJs.append(entry.getValue());
            first = false;
        }
    }
    labelsJs.append("]");
    dataJs.append("]");
%>

<!-- Section graphique (sans formulaire, utilise celui de la page parent) -->
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

<div class="row" style="margin-top: 20px;">
    <div class="col-md-12 mb-5">
        <div class="box">
            <div class="box-body" style="height:420px;">
                <%
                    if(dataMap == null || dataMap.isEmpty()) {
                %>
                <div class="alert alert-info" style="margin-top:150px; text-align:center;">
                    <h4><i class="icon fa fa-info"></i> Aucune donnée disponible</h4>
                    <p>Aucune heure supplémentaire n'a été trouvée pour la période sélectionnée.</p>
                </div>
                <%
                } else {
                %>
                <canvas id="chart-heures-sup" style="height:100%;"></canvas>
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
        double total = dataMap.values().stream().mapToDouble(Double::doubleValue).sum();
%>
<div class="row">
    <h3 class="h520pxSemibold m-0 col-md-12">Détails par type d'heures</h3>
    <div class="col-md-12">
        <div class="box">
            <div class="box-body">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Type d'heures</th>
                            <th>Code</th>
                            <th>Nombre d'heures</th>
                            <th>Pourcentage</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for(Map.Entry<String, Double> entry : dataMap.entrySet()) {
                                double pourcentage = total > 0 ? (entry.getValue() * 100.0) / total : 0;
                                String fullLabel = labelMap.get(entry.getKey());
                        %>
                        <tr>
                            <td><strong><%= fullLabel != null ? fullLabel : entry.getKey() %></strong></td>
                            <td><span class="label label-info"><%= entry.getKey() %></span></td>
                            <td><%= String.format("%.2f", entry.getValue()) %> h</td>
                            <td><%= String.format("%.2f", pourcentage) %> %</td>
                        </tr>
                        <%
                            }
                        %>
                        <tr class="bg-info">
                            <td colspan="2"><strong>TOTAL</strong></td>
                            <td><strong><%= String.format("%.2f", total) %> h</strong></td>
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

<script>
    (function(){
        console.log('=== DEBUG Chart.js - Heures Sup ===');
        console.log('typeof Chart:', typeof Chart);
        console.log('Labels:', <%= labelsJs.toString() %>);
        console.log('Data:', <%= dataJs.toString() %>);

        <% if(dataMap != null && !dataMap.isEmpty()) { %>
        var ctx = document.getElementById('chart-heures-sup');
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
                                'rgba(54, 162, 235, 0.6)',   // HS - Bleu
                                'rgba(255, 99, 132, 0.6)',   // JF - Rouge
                                'rgba(255, 206, 86, 0.6)',   // HD - Jaune
                                'rgba(75, 192, 192, 0.6)'    // IF - Vert
                            ],
                            borderColor: [
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 99, 132, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)'
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
                                    callback: function(value) {
                                        return value + ' h';
                                    }
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
                            },
                            tooltip: {
                                callbacks: {
                                    label: function(context) {
                                        return context.dataset.label + ': ' + context.parsed.y.toFixed(2) + ' h';
                                    }
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
