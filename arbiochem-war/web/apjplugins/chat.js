var $messages = $('.messages-content'),
    d, h, m;

let nomTable;
let dateDebutRep;
let dateFinRep;
let valiny
let galloisReponse;
let type;
let prompt;
let query;
let cols;
const baseUrl = window.location.origin;
let iaResp;
let jsonResp;
let datyFiltre;
let deb;
let fin;
let donneesSaisie;
let grouper;
let file;
let id;
let isError = false;
$(window).load(function() {
    $messages.mCustomScrollbar();
    firstMessage();
    loadHistory();
});

function loadHistory() {
    console.log("chatHistory:", window.chatHistory);
    if (window.chatHistory && window.chatHistory.length > 0) {
        window.chatHistory.forEach(msg => {

            if (msg.sender === "user") {
                $('<div class="message message-personal">' + msg.text + '</div>')
                    .appendTo($('.mCSB_container')).addClass('new');
            } else {




                //vaovao
                let obj;

                try {
                    obj = safeParse(msg.text);
                    const chartCallbacks = {
                        formatNumber: (value) => value.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 }),
                        tooltipLabel: (context) => {
                            let label = context.dataset.label || '';
                            if (label) label += ': ';
                            if (context.parsed.y !== null) {
                                label += new Intl.NumberFormat(undefined, { style: "decimal", minimumFractionDigits: 2, maximumFractionDigits: 2 }).format(context.parsed.y);
                            }
                            return label;
                        }
                    };

                    if (obj.stats?.options?.scales?.y?.ticks?.callback === "formatNumber") {
                        obj.stats.options.scales.y.ticks.callback = chartCallbacks.formatNumber;
                    }

                    if (obj.stats?.options?.plugins?.tooltip?.callbacks?.label === "tooltipLabel") {
                        obj.stats.options.plugins.tooltip.callbacks.label = chartCallbacks.tooltipLabel;
                    }

                    if (obj.iaResp != null) {
                        let rendered = marked.parse(obj.iaResp);
                        $('<div class="message new"></div>')
                            .html('<figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + rendered)
                            .appendTo($('.mCSB_container'))
                            .addClass('new');
                        Prism.highlightAllUnder($('.mCSB_container')[0]);
                    }

                    // 2. Render chart response
                    if (obj.stats != null) {
                        // Unique chart id
                        const chartId = "chart_" + Date.now();

                        // Add canvas
                        $('<div class="message new">' +
                            '<figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' +
                            '<canvas id="' + chartId + '" width="400" height="200"></canvas>' +
                            '</div>')
                            .appendTo($('.mCSB_container'))
                            .addClass('new');

                        // Initialize chart
                        const ctx = document.getElementById(chartId).getContext('2d');
                        new Chart(ctx, obj.stats);
                    }
                } catch (e) {
                    if (valiny.value != null) {
                        let obj = valiny.value;
                        // --- Traitement spécial code/texte ---
                        let rendered = marked.parse(valiny.value);
                        try{
                            obj = JSON.parse(valiny.value);
                        }
                        catch (e){
                            console.log("tsa metyyyyyy");
                            console.log(e.message);
                        }
                        //let vary = "java\npackage chatbot.templates;\n\nimport bean.ClassMAPTable;\nimport java.sql.Connection;\n\npublic class Client extends ClassMAPTable {\n    // les attributs de la table (meme nom)\n    String id;\n    String nom;\n    int age;\n\n    public Client() {\n        super.setNomTable(\"client\");\n    }\n\n    // retourne la valeur de l'id\n    @Override\n    public String getTuppleID() {\n        return id;\n    }\n\n    // retour le nom de variable de l'id\n    @Override\n    public String getAttributIDName() {\n        return \"id\";\n    }\n\n    @Override\n    public void construirePK(Connection c) throws Exception {\n        // premier argument c'est l'indice du PK, et deuxieme la sequence pour le prochain\n        this.preparePk(\"CL\", \"getSeqClient\");\n        this.setId(makePK(c));\n    }\n\n    // genere les getters et setters avec des checks logiques\n\n    public String getId() {\n        return id;\n    }\n\n    public void setId(String id) throws Exception {\n        if (id == null || id.trim().isEmpty()) {\n            throw new Exception(\"L'ID du client ne peut pas être vide.\");\n        }\n        this.id = id;\n    }\n\n    public String getNom() {\n        return nom;\n    }\n\n    public void setNom(String nom) throws Exception {\n        if (nom == null || nom.trim().isEmpty()) {\n            throw new Exception(\"Le nom du client ne peut pas être vide.\");\n        }\n        this.nom = nom;\n    }\n\n    public int getAge() {\n        return age;\n    }\n\n    public void setAge(int age) throws Exception {\n        if (age <= 0) {\n            throw new Exception(\"L'âge du client doit être un nombre positif.\");\n        }\n        this.age = age;\n    }\n}\n\njsp\n<%--\n    Document   : client-saisie.jsp\n    Created on :\n    Author     :\n--%>\n\n<%@page import=\"chatbot.templates.Client\"%>\n<%@page import=\"affichage.PageInsert\"%>\n<%@page import=\"user.UserEJB\"%>\n\n<%\n    //page de saisie simple suivant le framework APJ\n    try{\n\n        UserEJB u = (user.UserEJB) session.getValue(\"u\");\n        String  mapping = \"chatbot.templates.Client\",\n                nomtable = \"client\",\n                apres = \"client/client-fiche.jsp\", // redirige vers la fiche du client après insertion\n                titre = \"Nouveau Client\";\n\n        Client  client = new Client();\n        client.setNomTable(nomtable);\n        PageInsert pi = new PageInsert(client, request, u);\n        pi.setLien((String) session.getValue(\"lien\"));\n        pi.getFormu().getChamp(\"nom\").setLibelle(\"Nom du Client\");\n        pi.getFormu().getChamp(\"age\").setLibelle(\"Âge du Client\");\n        // Si 'age' devait être une page d'appel, on utiliserait setPageAppelComplete comme ceci :\n        // pi.getFormu().getChamp(\"age\").setPageAppelComplete(\"mg.cnaps.compta.ComptaAge\", \"age\", \"COMPTA_AGE\");\n        pi.preparerDataFormu();\n%>\n<div class=\"content-wrapper\">\n    <h1> <%=titre%></h1>\n    <form action=\"<%=pi.getLien()%>?but=apresTarif.jsp\" method=\"post\" name=\"<%=nomtable%>\" id=\"<%=nomtable%>\">\n        <%\n            pi.getFormu().makeHtmlInsertTabIndex();\n            out.println(pi.getFormu().getHtmlInsert());\n            out.println(pi.getHtmlAddOnPopup());\n        %>\n        <input name=\"acte\" type=\"hidden\" value=\"insert\">\n        <input name=\"bute\" type=\"hidden\" value=\"<%=apres%>\">\n        <input name=\"classe\" type=\"hidden\" value=\"<%=mapping%>\">\n        <input name=\"nomtable\" type=\"hidden\" value=\"<%=nomtable%>\">\n    </form>\n</div>\n<% } catch (Exception e) { e.printStackTrace(); %>\n<script>alert('<%=e.getMessage()%>'); history.back();</script>\n<% } %>\n";
                        const blocks = obj.split(/\n(?=java|jsp|sql)/); // séparer par "java", "jsp", "sql"
                        let html = "";

                        blocks.forEach(block => {
                            block = block.trim();
                            if (block.startsWith("java")) {
                                let code = block.replace(/^java\s*/i, "")
                                    .replace(/</g, "&lt;")
                                    .replace(/>/g, "&gt;");
                                html += `<h4>Java</h4><pre><code class="language-java">${code}</code></pre>`;
                            } else if (block.startsWith("jsp")) {
                                let code = block.replace(/^jsp\s*/i, "")
                                    .replace(/</g, "&lt;")
                                    .replace(/>/g, "&gt;");
                                html += `<h4>JSP</h4><pre><code class="language-markup">${code}</code></pre>`;
                            } else if (block.startsWith("sql")) {
                                let code = block.replace(/^sql\s*/i, "")
                                    .replace(/</g, "&lt;")
                                    .replace(/>/g, "&gt;");
                                html += `<h4>SQL</h4><pre><code class="language-sql">${code}</code></pre>`;
                            } else {
                                // Texte normal
                                let safe = block.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                                safe = marked.parse(safe);
                                html += `<p>${safe}</p>`;
                            }
                        });
                        if(html!=""){
                            $('<div class="message new"><figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + html + '</div>')
                                .appendTo($('.mCSB_container')).addClass('new');

                        }else{
                            $('<div class="message new"></div>')
                                .html('<figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + rendered)
                                .appendTo($('.mCSB_container'))
                                .addClass('new');
                            Prism.highlightAllUnder($('.mCSB_container')[0]);


                            // Lecture vocale uniquement pour texte normal
                            const utterance = new SpeechSynthesisUtterance(valiny.value);
                            utterance.lang = 'fr-FR';
                            utterance.pitch = 1;
                            utterance.rate = 1;
                            setTimeout(() => {
                                window.speechSynthesis.speak(utterance);
                            }, 500);
                        }
                    }
                }
                if(html!=""){
                    $('<div class="message new"><figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + html + '</div>')
                        .appendTo($('.mCSB_container')).addClass('new');

                }else{
                    $('<div class="message new"></div>')
                        .html('<figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + rendered)
                        .appendTo($('.mCSB_container'))
                        .addClass('new');
                    Prism.highlightAllUnder($('.mCSB_container')[0]);
                }

            }
        });
        updateScrollbar();
    }
}

function updateScrollbar() {
    $messages.mCustomScrollbar("update").mCustomScrollbar('scrollTo', 'bottom', {
        scrollInertia: 10,
        timeout: 0
    });
}

function setDate(){
    d = new Date()
    if (m != d.getMinutes()) {
        m = d.getMinutes();
        $('<div class="timestamp">' + d.getHours() + ':' + m + '</div>').appendTo($('.message:last'));
    }
}

async function transcriptionMessage(demande){
    const formData = new FormData();
    formData.append("message", demande);
    if (file) {
        formData.append("file", file);
    }
    formData.append("url",baseUrl);
    return $.ajax({
        url: baseUrl+"/socobis/query-generator",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(jsonResponse) {
            if(jsonResponse.value && jsonResponse.value !== "" && jsonResponse.value =="error")
            {
                isError = true;
                valiny = jsonResponse.url;
            }
            else if(jsonResponse.value && jsonResponse.value !== "" && jsonResponse.value =="ocr")
            {
                console.log("heooooo");
                window.location.href = baseUrl+jsonResponse.url;
                console.log("tao tsy lasa");
            }
            else{
                console.log("ty no valiny " + jsonResponse);
                console.log("Response: ", JSON.stringify(jsonResponse, null, 2));
                console.log(jsonResponse.type)
                nomTable = jsonResponse.nomTable;
                dateDebutRep = jsonResponse.date1;
                dateFinRep = jsonResponse.date2;
                type = jsonResponse.type;
                query = jsonResponse.requeteSql;
                jsonResp = jsonResponse.responseJson;
                iaResp = jsonResponse.iaResp;
                datyFiltre = jsonResponse.datyFiltre;
                deb = jsonResponse.deb;
                fin = jsonResponse.fin;
                grouper = jsonResponse.grouper;
                prompt = demande;
                donneesSaisie = jsonResponse.donnees;
                id = jsonResponse.id;
                console.log("ok mety "+prompt);
            }
        },
        error: function(xhr, status, error) {
            valiny = "Une erreur s'est produite, veuillez réessayer";
            console.error("An error occurred: " + error);
        },
    });
}

async function responseHandler(){
    return $.ajax({
        url: baseUrl+"/socobis/response-generator",
        type: "POST",
        dataType: 'json',
        data : {
            nomTable: nomTable,
            dateDebut: dateDebutRep,
            dateFin: dateFinRep,
            type: type,
            requeteSql: query,
            prompt: prompt,
            jsonResp : jsonResp,
            iaResp : iaResp,
            datyFiltre : datyFiltre,
            deb : deb,
            fin : fin,
            grouper :grouper,
            dataSaisie : JSON.stringify(donneesSaisie, null, 2),
            baseUrl : baseUrl,
            id:id,

        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(response) {

            if(response.value && response.value !== "" && response.value =="error")
            {
                isError = true;
                valiny = response.url;
            }
            else {
                console.log("sur? "+ response);
                valiny = response;
                console.log("huhuhu");
                console.log(valiny.value);
                if(valiny.value == "action"){
                    window.location.href = baseUrl+valiny.url;
                }
            }
        },
        error: function(xhr, status, error) {
            valiny = "Une erreur s'est produite, veuillez réessayer";
            console.error("An error occurred: " + error);
        },
    });
}

async function resendMessage(){
    return $.ajax({
        url: baseUrl+"/socobis/response-formattor",
        type: "GET",
        data: {message:valiny.value,prompty:prompt},
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(response) {
            console.log("bello " + response.response);
            galloisReponse = response.response;
        },
        error: function(xhr, status, error) {
            valiny = "Une erreur s'est produite, veuillez réesayer";
            console.error("An error occurred: " + error);
        },
    });
}

async function processMessage(msg) {
    $('<div class="message loadingChat new"><figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure><span></span></div>').appendTo($('.mCSB_container'));
    try {
        isError = false;
        $('.message-input').val(null);
        const transcr = await transcriptionMessage(msg);
        if(!isError && file==null){
            const respHandl = await responseHandler();
        }
        updateScrollbar();
        //const reponse = await resendMessage();
    } catch (e){
        console.log(e)
    }
}

async function insertMessage() {
    file = $('#fileInput')[0].files[0];
    msg = $('.message-input').val();
    if ($.trim(msg) == '') {
        return false;
    }
    $('<div class="message message-personal">' + msg + '</div>').appendTo($('.mCSB_container')).addClass('new');
    setDate();
    await processMessage(msg);
    realMessage();
}

$('.message-submit').click(function() {
    insertMessage();
    updateScrollbar();
});

$(window).on('keydown', function(e) {
    if (e.which == 13) {
        insertMessage();
        updateScrollbar();
        return false;
    }
})

function firstMessage(){
    $('.message.loading').remove();
    $('<div class="message new"><figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + "Bienvenue sur Async, je suis votre assistant virtuel, comment puis-je vous aider" + '</div>').appendTo($('.mCSB_container')).addClass('new');
    setDate();
    updateScrollbar();
}
function safeParse(raw) {
    if (!raw) return null;

    // If it's already an object → return it
    if (typeof raw === "object") {
        return raw;
    }

    if (typeof raw !== "string") {
        console.error("safeParse: unsupported type", typeof raw);
        return null;
    }

    let clean = raw.trim();

    // Remove all inline functions (replace with string)
    clean = clean.replace(/function\s*\([^)]*\)\s*{[^}]*}/g, '"__FUNC__"');

    // Fix trailing commas before } or ]
    clean = clean.replace(/,\s*}/g, "}").replace(/,\s*]/g, "]");

    // Remove stray );
    clean = clean.replace(/\);/g, "");

    try {
        return JSON.parse(clean);
    } catch (e) {
        console.error("safeParse failed:", e.message);
        console.log("Raw input:", raw);
        console.log("Cleaned input:", clean);
        return null;
    }
}

function realMessage() {
    if ($('.message-input').val() != '') {
        return false;
    }
    updateScrollbar();

    setTimeout(function() {
        $('.message.loadingChat').remove();

        let obj;

        try {
            obj = safeParse(valiny.value);
            const chartCallbacks = {
                formatNumber: (value) => value.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 }),
                tooltipLabel: (context) => {
                    let label = context.dataset.label || '';
                    if (label) label += ': ';
                    if (context.parsed.y !== null) {
                        label += new Intl.NumberFormat(undefined, { style: "decimal", minimumFractionDigits: 2, maximumFractionDigits: 2 }).format(context.parsed.y);
                    }
                    return label;
                }
            };

            if (obj.stats?.options?.scales?.y?.ticks?.callback === "formatNumber") {
                obj.stats.options.scales.y.ticks.callback = chartCallbacks.formatNumber;
            }

            if (obj.stats?.options?.plugins?.tooltip?.callbacks?.label === "tooltipLabel") {
                obj.stats.options.plugins.tooltip.callbacks.label = chartCallbacks.tooltipLabel;
            }

            if (obj.iaResp != null) {
                let rendered = marked.parse(obj.iaResp);
                $('<div class="message new"></div>')
                    .html('<figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + rendered)
                    .appendTo($('.mCSB_container'))
                    .addClass('new');
                Prism.highlightAllUnder($('.mCSB_container')[0]);
            }

            // 2. Render chart response
            if (obj.stats != null) {
                // Unique chart id
                const chartId = "chart_" + Date.now();

                // Add canvas
                $('<div class="message new">' +
                    '<figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' +
                    '<canvas id="' + chartId + '" width="400" height="200"></canvas>' +
                    '</div>')
                    .appendTo($('.mCSB_container'))
                    .addClass('new');

                // Initialize chart
                const ctx = document.getElementById(chartId).getContext('2d');
                new Chart(ctx, obj.stats);
            }
        } catch (e) {
            if (valiny.value != null) {
                let obj = valiny.value;
                // --- Traitement spécial code/texte ---
                let rendered = marked.parse(valiny.value);
                try{
                    obj = JSON.parse(valiny.value);
                }
                catch (e){
                    console.log("tsa metyyyyyy");
                    console.log(e.message);
                }
                //let vary = "java\npackage chatbot.templates;\n\nimport bean.ClassMAPTable;\nimport java.sql.Connection;\n\npublic class Client extends ClassMAPTable {\n    // les attributs de la table (meme nom)\n    String id;\n    String nom;\n    int age;\n\n    public Client() {\n        super.setNomTable(\"client\");\n    }\n\n    // retourne la valeur de l'id\n    @Override\n    public String getTuppleID() {\n        return id;\n    }\n\n    // retour le nom de variable de l'id\n    @Override\n    public String getAttributIDName() {\n        return \"id\";\n    }\n\n    @Override\n    public void construirePK(Connection c) throws Exception {\n        // premier argument c'est l'indice du PK, et deuxieme la sequence pour le prochain\n        this.preparePk(\"CL\", \"getSeqClient\");\n        this.setId(makePK(c));\n    }\n\n    // genere les getters et setters avec des checks logiques\n\n    public String getId() {\n        return id;\n    }\n\n    public void setId(String id) throws Exception {\n        if (id == null || id.trim().isEmpty()) {\n            throw new Exception(\"L'ID du client ne peut pas être vide.\");\n        }\n        this.id = id;\n    }\n\n    public String getNom() {\n        return nom;\n    }\n\n    public void setNom(String nom) throws Exception {\n        if (nom == null || nom.trim().isEmpty()) {\n            throw new Exception(\"Le nom du client ne peut pas être vide.\");\n        }\n        this.nom = nom;\n    }\n\n    public int getAge() {\n        return age;\n    }\n\n    public void setAge(int age) throws Exception {\n        if (age <= 0) {\n            throw new Exception(\"L'âge du client doit être un nombre positif.\");\n        }\n        this.age = age;\n    }\n}\n\njsp\n<%--\n    Document   : client-saisie.jsp\n    Created on :\n    Author     :\n--%>\n\n<%@page import=\"chatbot.templates.Client\"%>\n<%@page import=\"affichage.PageInsert\"%>\n<%@page import=\"user.UserEJB\"%>\n\n<%\n    //page de saisie simple suivant le framework APJ\n    try{\n\n        UserEJB u = (user.UserEJB) session.getValue(\"u\");\n        String  mapping = \"chatbot.templates.Client\",\n                nomtable = \"client\",\n                apres = \"client/client-fiche.jsp\", // redirige vers la fiche du client après insertion\n                titre = \"Nouveau Client\";\n\n        Client  client = new Client();\n        client.setNomTable(nomtable);\n        PageInsert pi = new PageInsert(client, request, u);\n        pi.setLien((String) session.getValue(\"lien\"));\n        pi.getFormu().getChamp(\"nom\").setLibelle(\"Nom du Client\");\n        pi.getFormu().getChamp(\"age\").setLibelle(\"Âge du Client\");\n        // Si 'age' devait être une page d'appel, on utiliserait setPageAppelComplete comme ceci :\n        // pi.getFormu().getChamp(\"age\").setPageAppelComplete(\"mg.cnaps.compta.ComptaAge\", \"age\", \"COMPTA_AGE\");\n        pi.preparerDataFormu();\n%>\n<div class=\"content-wrapper\">\n    <h1> <%=titre%></h1>\n    <form action=\"<%=pi.getLien()%>?but=apresTarif.jsp\" method=\"post\" name=\"<%=nomtable%>\" id=\"<%=nomtable%>\">\n        <%\n            pi.getFormu().makeHtmlInsertTabIndex();\n            out.println(pi.getFormu().getHtmlInsert());\n            out.println(pi.getHtmlAddOnPopup());\n        %>\n        <input name=\"acte\" type=\"hidden\" value=\"insert\">\n        <input name=\"bute\" type=\"hidden\" value=\"<%=apres%>\">\n        <input name=\"classe\" type=\"hidden\" value=\"<%=mapping%>\">\n        <input name=\"nomtable\" type=\"hidden\" value=\"<%=nomtable%>\">\n    </form>\n</div>\n<% } catch (Exception e) { e.printStackTrace(); %>\n<script>alert('<%=e.getMessage()%>'); history.back();</script>\n<% } %>\n";
                const blocks = obj.split(/\n(?=java|jsp|sql)/); // séparer par "java", "jsp", "sql"
                let html = "";

                blocks.forEach(block => {
                    block = block.trim();
                    if (block.startsWith("java")) {
                        let code = block.replace(/^java\s*/i, "")
                            .replace(/</g, "&lt;")
                            .replace(/>/g, "&gt;");
                        html += `<h4>Java</h4><pre><code class="language-java">${code}</code></pre>`;
                    } else if (block.startsWith("jsp")) {
                        let code = block.replace(/^jsp\s*/i, "")
                            .replace(/</g, "&lt;")
                            .replace(/>/g, "&gt;");
                        html += `<h4>JSP</h4><pre><code class="language-markup">${code}</code></pre>`;
                    } else if (block.startsWith("sql")) {
                        let code = block.replace(/^sql\s*/i, "")
                            .replace(/</g, "&lt;")
                            .replace(/>/g, "&gt;");
                        html += `<h4>SQL</h4><pre><code class="language-sql">${code}</code></pre>`;
                    } else {
                        // Texte normal
                        let safe = block.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                        safe = marked.parse(safe);
                        html += `<p>${safe}</p>`;
                    }
                });
                if(html!=""){
                    $('<div class="message new"><figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + html + '</div>')
                        .appendTo($('.mCSB_container')).addClass('new');

                }else{
                    $('<div class="message new"></div>')
                        .html('<figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + rendered)
                        .appendTo($('.mCSB_container'))
                        .addClass('new');
                    Prism.highlightAllUnder($('.mCSB_container')[0]);


                    // Lecture vocale uniquement pour texte normal
                    const utterance = new SpeechSynthesisUtterance(valiny.value);
                    utterance.lang = 'fr-FR';
                    utterance.pitch = 1;
                    utterance.rate = 1;
                    setTimeout(() => {
                        window.speechSynthesis.speak(utterance);
                    }, 500);
                }



                setDate();
                updateScrollbar();


                // Activer Prism.js si dispo
                if (typeof Prism !== "undefined") {
                    Prism.highlightAll();
                }
            }
        }


        if (valiny.url && valiny.url !== "") {
            valiny.url = baseUrl + valiny.url;
            $('<div class="message new"><figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' +
                '<a href="' + valiny.url + '">Voir les détails</a>' +
                '</div>').appendTo($('.mCSB_container')).addClass('new');
            setDate();
            updateScrollbar();
        } else if (valiny.value == null && valiny.url == null) {
            $('<div class="message new"><figure class="avatar"><img src="/socobis/assets/img/logo_A.png" /></figure>' + valiny + '</div>')
                .appendTo($('.mCSB_container')).addClass('new');
            setDate();
            updateScrollbar();
        }

        setDate();
        updateScrollbar();
    }, 1000 + (Math.random() * 20) * 100);
}

