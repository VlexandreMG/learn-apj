<!-- jQuery 2.1.4 -->

<!--<script src="${pageContext.request.contextPath}/assets/js/socket.io/socket.io.js"></script>-->
<script src="${pageContext.request.contextPath}/assets/js/moment.min.js"></script>


<!-- jQuery UI 1.11.4 -->
<script src="${pageContext.request.contextPath}/dist/js/jquery-ui.min.js" type="text/javascript"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script type="text/javascript">
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.2 JS -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.tablesorter.min.js" type="text/javascript"></script>


<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>-->
<!-- Morris.js charts -->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js" type="text/javascript"></script>-->
<!-- Sparkline -->
<!--<script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>-->
<!-- jvectormap -->
<!--<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>-->
<!-- jQuery Knob Chart -->
<!--<script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js" type="text/javascript"></script>-->
<!-- daterangepicker -->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>-->
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- datepicker -->
<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/timepicker/bootstrap-timepicker.min.js" type="text/javascript"></script>
<!-- Bootstrap WYSIHTML5 -->
<!--<script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>-->
<!-- Slimscroll -->
<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.min.js" type="text/javascript"></script>
<!-- ChartJS 1.0.1 -->
<script src="${pageContext.request.contextPath}/assets/js/Chart.min.js" type="text/javascript"></script>
<%--<script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js" type="text/javascript"></script>--%>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/app.min.js" type="text/javascript"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!--<script src="${pageContext.request.contextPath}/dist/js/pages/dashboard.js" type="text/javascript"></script>-->
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!--<script src="${pageContext.request.contextPath}/dist/js/pages/dashboard2.js" type="text/javascript"></script>-->
<!-- AdminLTE for demo purposes -->
<!--<script src="${pageContext.request.contextPath}/dist/js/demo.js" type="text/javascript"></script>-->
<!-- Parsley -->
<script src="${pageContext.request.contextPath}/plugins/parsley/src/i18n/fr.js"></script>
<script src="${pageContext.request.contextPath}/plugins/parsley/dist/parsley.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-notify/bootstrap-notify.min.js"></script>
<!-- chatbot -->
<script src="${pageContext.request.contextPath}/apjplugins/chat.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/prism/prism.js"></script>
<script src="${pageContext.request.contextPath}/plugins/prism/prism-java.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/prism/prism-sql.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/prism/prism-markup.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/prism/marked.min.js"></script>

<!-- Calculcate min-height of content-wrapper -->
<script src="${pageContext.request.contextPath}/dist/js/content-wrapper.js"></script>

<script type="text/javascript">
    window.ParsleyValidator.setLocale('fr');
    $('.datepicker').datepicker({
        format: 'dd/mm/yyyy'
    });
    $('input[type="datetime-local"]').each(function () {
        let input = $(this);
        if (!input.val()) {
            input.val(moment().format('YYYY-MM-DDTHH:mm:ss'));
        }
    });
//    $(".timepicker").timepicker({
//        showInputs: false
//    });

    $(window).bind("load", function () {
//        getMessageDeploiement();
//        window.setInterval(getMessageDeploiement, 30000);
    });

    function getMessageDeploiement() {
        var text = 'ok';
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/MessageDeploiement',
            contentType: 'application/json',
            data: {'mes': text},
            success: function (ma) {
                if (ma != null) {
                    var data = JSON.parse(ma);
                    if (data.message != null) {
                        alert(data.message);
                    }
                    if (data.erreur != null) {
                        alert(data.erreur);
                    }
                }

            },
            error: function (e) {
                //alert("Erreur Ajax");
            }

        });
    }
    function ouvrirModal(event,contentUrl,modalCible)
    {
        event.preventDefault();
        document.getElementById("loader").style.display = "flex";
        fetch(contentUrl)
            .then(response => response.text())
            .then(html => {
                var contentElement = document.getElementById(modalCible);
                if (contentElement) {
                    contentElement.innerHTML = html;
                    $('#linkModal .content-wrapper').addClass('box');
                    $('#linkModal .content-wrapper').removeClass('content-wrapper');
                } else {
                    console.error("Élément avec ID '" + modalCible + "' non trouvé.");
                    return;
                }
                var modalElement = $('#linkModal');
                if (modalElement.length) {
                    modalElement.modal('show');
                    modalElement.css({
                        'opacity':'1',
                        'padding': '25rem 0px 0px 0px'
                    });
                } else {
                    console.error("Modal avec ID 'linkModal' non trouvé.");
                }
            })
            .catch(error => {
                console.error("Erreur de chargement:", error);
                var contentElement = document.getElementById(modalCible);
                if (contentElement) {
                    contentElement.innerHTML = "Erreur de chargement du contenu.";
                }
                var modalElement = $('#linkModal');
                if (modalElement.length) {
                    modalElement.modal('show');
                }
            })
            .finally(() => {
                // ✅ Cacher le loader après chargement ou erreur
                document.getElementById("loader").style.display = "none";
            });
    }
    function closeModal() {
        var modalElement = $('#linkModal');
        if (modalElement.length) {
            modalElement.modal('hide');
        } else {
            console.error("Modal avec ID 'linkModal' non trouvé.");
        }
    }
    function toggleRow(button,lienFille) {
        // Trouver la ligne suivante qui contient les détails à afficher/masquer
        var nextRow = button.parentNode.parentNode.nextElementSibling;
        // Vérifier si cette ligne a la classe "collapse"
        if (nextRow.classList.contains("collapse")) {
            // Si elle est masquée, on l'affiche
            nextRow.classList.remove("collapse");
            button.textContent = "-"; // Changer le bouton en "-" quand déplié
            var detailsElement = nextRow.querySelector('div');
            //detailsElement.innerHTML="Bonjourrrrrrrrrrrr "+detailsElement.id;
            var url='${pageContext.request.contextPath}/pages/moduleLeger.jsp?but='+button.id+detailsElement.id;
            console.log(url);
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Erreur de chargement de la page');}
                    return response.text(); // On récupère le texte HTML
                })
                .then(html => {
                    detailsElement.classList.add('details_collapse');
                    detailsElement.innerHTML = html;
                    // Trigger height recalculation for content-wrapper
                    document.dispatchEvent(new Event('dynamicContentLoaded'));
                });

        } else {
            // Si elle est affichée, on la cache
            nextRow.classList.add("collapse");
            button.textContent = "+"; // Changer le bouton en "+" quand replié
        }
    }
    function insertAj(event)
    {
        event.preventDefault();
        var formData = new FormData(event.target);
        var req="";
        document.getElementById("loader").style.display = "flex";
        formData.forEach((value, key) => {
            req=req+'&'+key+"="+ value;
        });
        $.ajax({
            type:'GET',
            url:'${pageContext.request.contextPath}/ApresTarif?'+req,
            contentType: 'application/json',
            data:null,
            success:function(ma){
                if(ma.status==='error'){
                    alert(ma.message);
                    document.getElementById("loader").style.display = "none";
                }
                else
                {
                    var myDiv = document.getElementById("butjsp");
                    console.log(myDiv);
                    fetch(ma.butApres)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Erreur de chargement de la page');}
                            return response.text(); // On récupère le texte HTML
                        })
                        .then(html => {
                            myDiv.innerHTML = html;
                            //reinitializeLibelleFields()
                            //initAutocompleteIngredients()
                            // Trigger height recalculation for content-wrapper
                            document.dispatchEvent(new Event('dynamicContentLoaded'));
                        })
                        .catch(error => {
                        console.error(error);
                        alert('Erreur lors du chargement des données.');
                        })
                        .finally(() => {
                            // ✅ Cacher le loader après chargement ou erreur
                            document.getElementById("loader").style.display = "none";
                        });

                }
            },
            error:function(ma){
                console.log(ma)
                alert(ma.message);
                document.getElementById("loader").style.display = "none";
            }
        })
    }
    function insertAjING(event)
    {
        event.preventDefault();
        var formData = new FormData(event.target);
        var req="";
        document.getElementById("loader").style.display = "flex";
        formData.forEach((value, key) => {
            req=req+'&'+key+"="+ value;
        });
        $.ajax({
            type:'GET',
            url:'${pageContext.request.contextPath}/ApresTarif?'+req,
            contentType: 'application/json',
            data:null,
            success:function(ma){
                if(ma.status==='error'){
                    alert(ma.message);
                    document.getElementById("loader").style.display = "none";
                }
                else
                {
                    var myDiv = document.getElementById("butjsp");
                    console.log(myDiv);
                    fetch(ma.butApres)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Erreur de chargement de la page');}
                            return response.text(); // On récupère le texte HTML
                        })
                        .then(html => {
                            myDiv.innerHTML = html;
                            reinitializeLibelleFields()
                            initAutocompleteIngredients()
                            // Trigger height recalculation for content-wrapper
                            document.dispatchEvent(new Event('dynamicContentLoaded'));
                        })
                        .catch(error => {
                        console.error(error);
                        alert('Erreur lors du chargement des données.');
                        })
                        .finally(() => {
                            // ✅ Cacher le loader après chargement ou erreur
                            document.getElementById("loader").style.display = "none";
                        });

                }
            },
            error:function(ma){
                console.log(ma)
                alert(ma.message);
                document.getElementById("loader").style.display = "none";
            }
        })
    }
    function cleanScriptWrappers(script) {
        // Remove first line (jQuery wrapper start)
        const withoutFirstLine = script.replace(
            /^jQuery\(document\)\.ready\(function\s*\(\)\s*\{\$\(function\(\)\s*\{\s*[\r\n]*/,
            ''
        );

        // Remove last line (jQuery wrapper end)
        const withoutWrappers = withoutFirstLine.replace(
            /[\s\r\n]*\}\);\}\);\s*$/,
            ''
        );

        return withoutWrappers;
    }

    function findInlineScriptByKeyword() {
        const scripts = document.getElementsByTagName('script');
        let targetScript;

        for (let i = 0; i < scripts.length; i++) {
            if (scripts[i].textContent.includes('.autocomplete(')) {
                targetScript = scripts[i].textContent;
                return cleanScriptWrappers(targetScript)
            }
        }
    }
    function initAutocompleteIngredients() {
        $("#idingredientslibelle").autocomplete({
            source: function(request, response) {
                $("#idingredients").val('');
                if (autocompleteTriggered) {
                    fetchAutocomplete(request, response, "null", "id", "null", "as_ingredients_lib", "produits.IngredientsLib", "true","unite");
                }
            },
            select: function(event, ui) {
                $("#idingredientslibelle").val(ui.item.label);
                $("#idingredients").val(ui.item.value);
                $("#idingredients").trigger('change');
                $(this).autocomplete('disable');
                var champsDependant = ['unite'];
                for(let i=0;i<champsDependant.length;i++){
                    $('#'+champsDependant[i]).val(ui.item.retour.split(';')[i]);
                }
                autocompleteTriggered = false;
                return false;
            }
        }).autocomplete('disable');
        $("#idingredientslibelle").off('keydown');
        $("#idingredientslibelle").keydown(function(event) {
            if (event.key === 'Tab') {
                event.preventDefault();
                autocompleteTriggered = true;
                $(this).autocomplete('enable').autocomplete('search', $(this).val());
            }
        });
        $("#idingredientslibelle").off('input');
        $("#idingredientslibelle").on('input', function() {
            $("#idingredients").val('');
            autocompleteTriggered = false;
            $(this).autocomplete('disable');
        });
        $("#idingredients"+"searchBtn").off('click');
        $("#idingredients"+"searchBtn").click(function() {
            autocompleteTriggered = true;
            $("#idingredientslibelle").autocomplete('enable').autocomplete('search', $("#idingredientslibelle").val());
        });
    }

    function reinitializeLibelleFields() {
        $('input[id*="libelle"]').each(function() {
            $(this).addClass('ui-autocomplete-input')
                .attr('autocomplete', 'off')
                .attr('tabindex', '1');
        });
        $("#ui-id-1").empty();

    }
    function modifEtatMult(event)
    {
        event.preventDefault();
        var formData = new FormData(event.target);
        var req="";
        document.getElementById("loader").style.display = "flex";
        $.ajax({
            type:'POST',
            url:'${pageContext.request.contextPath}/ApresMultiple',
            contentType: false,
            data:formData,
            processData:false,
            success:function(ma){
                if(ma.status==='error'){
                    alert(ma.message);
                    document.getElementById("loader").style.display = "none";
                }
                else
                {
                    var myDiv = document.getElementById("butjsp");
                    fetch(ma.butApres)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Erreur de chargement de la page');}
                            return response.text(); // On récupère le texte HTML
                        })
                        .then(html => {
                            myDiv.innerHTML = html;
                            // reinitializeLibelleFields()
                            // initAutocompleteIngredients()
                        })
                        .catch(error => {
                        console.error(error);
                        alert('Erreur lors du chargement des données.');
                        })
                        .finally(() => {
                            // ✅ Cacher le loader après chargement ou erreur
                            document.getElementById("loader").style.display = "none";
                        });

                }
            },
            error:function(ma){
                console.log(ma)
                alert(ma.message);
                document.getElementById("loader").style.display = "none";
            }
        })
    }

    function updateSection(event, formId, targetDivId = 'butfillejsp') {
        event.preventDefault();

        const input = event.target;
        const currentValue = input.value;

        if (input.dataset.lastValue === currentValue) {
            return;
        }
        input.dataset.lastValue = currentValue;

        const name = input.name;
        const lastValue = encodeURIComponent(currentValue);
        const form = document.getElementById(formId);
        const fd = new FormData(form);

        const params = new URLSearchParams(new FormData(form));
        params.append("onchanged", "true");

        document.getElementById("loader").style.display = "flex";

        $.ajax({
            url: window.location.pathname + window.location.search + "&onchanged=true",
            type: 'POST',
            data: params.toString(),
            contentType: 'application/x-www-form-urlencoded',
            success: function (response) {
                const doc = new DOMParser().parseFromString(response, 'text/html');
                const targetDiv = document.getElementById(targetDivId);

                if (targetDiv) {
                    targetDiv.innerHTML = '';
                    const newContent = doc.getElementById(targetDivId);
                    if (newContent) {
                        targetDiv.innerHTML = newContent.innerHTML;

                        newContent.querySelectorAll('script').forEach(script => {
                            const newScript = document.createElement('script');
                            newScript.text = script.textContent;
                            targetDiv.appendChild(newScript);
                        });
                    } else {
                        console.error(`Élément ${targetDivId} introuvable dans la réponse.`);
                    }
                } else {
                    console.error(`Élément cible ${targetDivId} introuvable.`);
                }

                document.getElementById("loader").style.display = "none";
            },
            error: function () {
                document.getElementById("loader").style.display = "none";
                console.error('Erreur lors de la mise à jour');
            }
        });
    }
    // updateFille
    function updateFille(event, formId) {
        event.preventDefault();

        const input = event.target;
        const currentValue = input.value;
        if (input.dataset.lastValue === currentValue) {
            return;
        }
        input.dataset.lastValue = currentValue;

        const name = input.name;
        const lastValue = input.dataset.lastValue;


        const url = `${baseUrl}?${currentParams.toString()}`;
        document.getElementById("loader").style.display = "flex";
        const fd = new FormData(document.getElementById(formId));
        $.ajax({
            url: window.location.pathname + window.location.search + "&onchanged=true" + "&" + name + "=" + lastValue,
            type: 'POST',
            data: fd,
            contentType: false,
            processData:false,
            success:function(response) {
                const doc = new DOMParser().parseFromString(response, 'text/html');
                const targetDiv = document.getElementById('butfillejsp');
                if (targetDiv) {
                    targetDiv.innerHTML = '';
                    const newContent = doc.getElementById('butfillejsp');
                    if (newContent) {
                        targetDiv.innerHTML = newContent.innerHTML;
                        newContent.querySelectorAll('script').forEach(script => {
                            const newScript = document.createElement('script');
                            newScript.text = script.textContent;
                            targetDiv.appendChild(newScript);
                        });
                    } else {
                        console.error('Élément butjsp introuvable dans la réponse.');
                    }
                } else {
                    console.error('Élément cible butjsp introuvable.');
                }
                document.getElementById("loader").style.display = "none";
            },
            error: function(){
                document.getElementById("loader").style.display = "none";
                console.error('Erreur lors de la mise à jour');
            }
        })
    }

    function modifEtatMultING(event)
    {
        event.preventDefault();
        var formData = new FormData(event.target);
        var req="";
        document.getElementById("loader").style.display = "flex";
        $.ajax({
            type:'POST',
            url:'${pageContext.request.contextPath}/ApresMultiple',
            contentType: false,
            data:formData,
            processData:false,
            success:function(ma){
                if(ma.status==='error'){
                    alert(ma.message);
                    document.getElementById("loader").style.display = "none";
                }
                else
                {
                    var myDiv = document.getElementById("butjsp");
                    fetch(ma.butApres)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Erreur de chargement de la page');}
                            return response.text(); // On récupère le texte HTML
                        })
                        .then(html => {
                            myDiv.innerHTML = html;
                            reinitializeLibelleFields()
                            initAutocompleteIngredients()
                        })
                        .catch(error => {
                            console.error(error);
                            alert('Erreur lors du chargement des données.');
                        })
                        .finally(() => {
                            // ✅ Cacher le loader après chargement ou erreur
                            document.getElementById("loader").style.display = "none";
                        });

                }
            },
            error:function(ma){
                console.log(ma)
                alert(ma.message);
                document.getElementById("loader").style.display = "none";
            }
        })
    }

    function pagePopUp(page, width, height) {
        w = 750;
        h = 600;
        t = "D&eacute;tails";

        if (width != null || width == "")
        {
            w = width;
        }
        if (height != null || height == "") {
            h = height;
        }
        window.open(page, t, "titulaireresizable=no,scrollbars=yes,location=no,width=" + w + ",height=" + h + ",top=0,left=0");
    }
    function searchKeyPress(e)
    {
        // look for window.event in case event isn't passed in
        e = e || window.event;
        if (e.keyCode == 13)
        {
            document.getElementById('btnListe').click();
            return false;
        }
        return true;
    }
    function back() {
        history.back();
    }
    function dependante(valeurFiltre,champDependant,nomTable,nomClasse,nomColoneFiltre,nomColvaleur,nomColAffiche)
    {
        document.getElementById(champDependant).length=0;
        var param = {'valeurFiltre':valeurFiltre,'nomTable':nomTable,'nomClasse':nomClasse,'nomColoneFiltre':nomColoneFiltre,'nomColvaleur':nomColvaleur,'nomColAffiche':nomColAffiche};
        var lesValeur=[new Option("-","",false,false)];  
        $.ajax({
            type:'GET',
            url:'${pageContext.request.contextPath}/Deroulante',
            contentType: 'application/json',
            data:param,
            success:function(ma){
                var data = JSON.parse(ma);   
                
                for(i in data.valeure)
                {
                    lesValeur.push(new Option(data.valeure[i].valeur, data.valeure[i].id, false, false));
                }
                addOptions(champDependant,lesValeur);
            },
            error:function(ma){
                console.log(ma);
            }
        });


    }
    function getChoix() {
        setTimeout("document.frmchx.submit()", 800);
    }
    $('#sigi').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": false,
        "autoWidth": false
    });
    $(function () {
        $(".select2").select2();
        $("#example1").DataTable();
        $('#example2').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });
    });
    function CocheToutCheckbox(ref, name) {
        var form = ref;

        while (form.parentNode && form.nodeName.toLowerCase() != 'form') {
            form = form.parentNode;
        }

        var elements = form.getElementsByTagName('input');

        for (var i = 0; i < elements.length; i++) {
            if (elements[i].type == 'checkbox' && elements[i].name == name) {
                elements[i].checked = ref.checked;
            }
        }
    }
    function showNotification(message, classe, url) {
        $.notify({
            message: message,
            url: url
        }, {
            type: classe
        });
    }
    function add_line() {
        var indexMultiple = document.getElementById('indexMultiple').value;
        var nbrLigne = document.getElementById('nbrLigne').value;
        var html = genererLigneFromIndex(indexMultiple);
        $('#ajout_multiple_ligne').append(html);
        document.getElementById('indexMultiple').value = parseInt(indexMultiple) + 1;
        document.getElementById('nbrLigne').value = parseInt(nbrLigne) + 1;
    }
    function removeLineByIndex(iLigne) {
        var nomId = "ligne-multiple-" + iLigne;

        var ligne = document.getElementById(nomId);
        ligne.parentNode.removeChild(ligne);
        var nbrLigne = document.getElementById('nbrLigne').value;
        //document.getElementById('nbrLigne').value = nbrLigne - 1;
    }

    function getHtmlTabeauLigne() {
        var htmlComplet = $('#tableauLigne').html();
        document.getElementById('htmlComplet').value = htmlComplet;
        $('#declarationFormulaire').submit();


    }

    function changeInput(input) {
//        alert(input.id);
//        document.getElementById(input.id).value = ;
        $('#' + input.id).attr('value', input.value);
    }
    <%--function dependante(valeurFiltre, champDependant, nomTable, nomClasse, nomColoneFiltre, nomColvaleur, nomColAffiche)--%>
    <%--{--%>
    <%--    document.getElementById(champDependant).length = 0;--%>
    <%--    var param = {'valeurFiltre': valeurFiltre, 'nomTable': nomTable, 'nomClasse': nomClasse, 'nomColoneFiltre': nomColoneFiltre, 'nomColvaleur': nomColvaleur, 'nomColAffiche': nomColAffiche};--%>
    <%--    var lesValeur=[new Option("-","",false,false)];  --%>
    <%--    $.ajax({--%>
    <%--        type: 'GET',--%>
    <%--        url: '${pageContext.request.contextPath}/Deroulante',--%>
    <%--        contentType: 'application/json',--%>
    <%--        data: param,--%>
    <%--        success: function (ma) {--%>
    <%--            var data = JSON.parse(ma);--%>

    <%--            for (i in data.valeure)--%>
    <%--            {--%>
    <%--                lesValeur.push(new Option(data.valeure[i].valeur, data.valeure[i].id, false, false));--%>
    <%--            }--%>
    <%--            addOptions(champDependant, lesValeur);--%>
    <%--        }--%>
    <%--    });--%>


    <%--}--%>
    /**
     * Duplique une seule ligne spécifique
     * @param {number} rowIndex - L'index de la ligne à dupliquer
     */
    function duplicateSingleRow(rowIndex) {
        // Récupérer la ligne à dupliquer
        const originalRow = document.getElementById('ligne-multiple-' + rowIndex);
        if (!originalRow) {
            console.error('Ligne non trouvée: ' + rowIndex);
            return;
        }

        // Récupérer l'index actuel pour la nouvelle ligne
        const tableBody = document.getElementById('ajout_multiple_ligne');
        const nbrLigneInput = document.getElementById('nbrLigne');
        const indexMultipleInput = document.getElementById('indexMultiple');
        const nombreLigneHidden = document.getElementById('nombreLigne');

        if (!tableBody || !nbrLigneInput || !indexMultipleInput || !nombreLigneHidden) {
            alert('Erreur: éléments nécessaires non trouvés');
            return;
        }

        let currentIndex = parseInt(indexMultipleInput.value) || 0;
        let currentNbrLigne = parseInt(nbrLigneInput.value) || 0;
        let currentNombreLigne = parseInt(nombreLigneHidden.value) || 0;

        // Créer une nouvelle ligne avec un nouvel index
        const newRowIndex = currentIndex;

        // Cloner la ligne originale profondément (avec tous les événements)
        const newRow = originalRow.cloneNode(true);
        newRow.id = 'ligne-multiple-' + newRowIndex;

        // Mettre à jour tous les IDs et noms dans la nouvelle ligne
        updateRowIdsAndNames(newRow, newRowIndex);

        // Mettre à jour la checkbox
        const newCheckbox = newRow.querySelector('input[name="ids"]');
        if (newCheckbox) {
            newCheckbox.value = newRowIndex;
            newCheckbox.id = 'checkbox' + newRowIndex;
            newCheckbox.checked = false;

            // Mettre à jour l'événement oninput pour synchro
            const inputs = newRow.querySelectorAll('input[oninput*="synchro"]');
            inputs.forEach(input => {
                const oldOninput = input.getAttribute('oninput');
                if (oldOninput) {
                    input.setAttribute('oninput', oldOninput.replace(/checkbox\d+\.id/, 'checkbox' + newRowIndex + '.id'));
                }
            });
        }

        // Mettre à jour le bouton de duplication
        const duplicateBtn = newRow.querySelector('.duplicate-row-btn');
        if (duplicateBtn) {
            duplicateBtn.setAttribute('onclick', 'duplicateSingleRow(' + newRowIndex + ')');
            duplicateBtn.setAttribute('title', 'Dupliquer cette ligne');
        }

        // Mettre à jour le lien de suppression
        const deleteLink = newRow.querySelector('a[onclick*="delete_line"]');
        if (deleteLink) {
            deleteLink.setAttribute('onclick', 'delete_line(' + newRowIndex + ')');
        }

        // Mettre à jour les boutons de recherche d'autocomplete
        const searchBtn = newRow.querySelector('button[id$="searchBtn"]');
        if (searchBtn) {
            const oldId = searchBtn.id;
            const newId = oldId.replace(/\d+searchBtn$/, newRowIndex + 'searchBtn');
            searchBtn.id = newId;

            // Réattacher l'événement click pour l'autocomplete
            $(searchBtn).off('click').on('click', function() {
                const libelleInputId = this.id.replace('searchBtn', 'libelle');
                const libelleInput = $('#' + libelleInputId);
                if (libelleInput.length && libelleInput.autocomplete('instance')) {
                    libelleInput.autocomplete('enable').autocomplete('search', libelleInput.val());
                }
            });
        }

        // Insérer la nouvelle ligne après la ligne originale
        originalRow.parentNode.insertBefore(newRow, originalRow.nextSibling);

        // Mettre à jour les index
        currentIndex++;
        currentNbrLigne++;
        currentNombreLigne++;

        indexMultipleInput.value = currentIndex;
        nbrLigneInput.value = currentNbrLigne;
        nombreLigneHidden.value = currentNombreLigne;

        // Réinitialiser l'autocomplete pour la nouvelle ligne
        setTimeout(() => {
            initializeAutocompleteForRow(newRowIndex);
        }, 50);
    }

    /**
     * Met à jour tous les IDs et noms dans une ligne
     */
    function updateRowIdsAndNames(row, newIndex) {
        // Mettre à jour tous les inputs
        const inputs = row.querySelectorAll('input, select, textarea');

        inputs.forEach(element => {
            const oldId = element.id;
            const oldName = element.name;

            if (oldId) {
                // Remplacer le numéro à la fin de l'ID
                const newId = oldId.replace(/\d+$/, '' + newIndex)
                    .replace(/\d+$/, newIndex)
                    .replace(/libelle$/, 'libelle')
                    .replace(/(\D+)\d+(.*)/, '$1' + newIndex + '$2');
                element.id = newId;
            }

            if (oldName) {
                // Remplacer le numéro à la fin du nom
                const newName = oldName.replace(/\d+$/, '' + newIndex);
                element.name = newName;
            }

            // Pour les champs avec auto
            if (oldName && oldName.endsWith('auto')) {
                const baseName = oldName.replace('auto', '');
                const baseMatch = baseName.match(/(.*)_\d+$/);
                if (baseMatch) {
                    element.name = baseMatch[1] + '_' + newIndex + 'auto';
                }
            }
        });
    }

    /**
     * Initialise l'autocomplete pour une ligne spécifique
     */
    function initializeAutocompleteForRow(rowIndex) {
        const libelleInput = $('#idProduit_' + rowIndex + 'libelle');
        const hiddenInput = $('#idProduit_' + rowIndex);
        const searchBtn = $('#idProduit_' + rowIndex + 'searchBtn');

        if (libelleInput.length && !libelleInput.data('ui-autocomplete')) {
            // Détruire l'autocomplete existant s'il y en a un
            if (libelleInput.autocomplete('instance')) {
                libelleInput.autocomplete('destroy');
            }

            // Initialiser l'autocomplete
            var autocompleteTriggered = false;

            libelleInput.autocomplete({
                source: function(request, response) {
                    hiddenInput.val('');
                    if (autocompleteTriggered) {
                        fetchAutocomplete(request, response, "null", "id", "null",
                            "ST_INGREDIENTSAUTOVENTE_CPL", "produits.IngredientsLib",
                            "true", "designation;designation", "null");
                    }
                },
                open: function() {
                    var wrapper = $(this).closest('.d-flex.input-with-addons');
                    var width = wrapper.length ? wrapper.outerWidth() : $(this).outerWidth();
                    $(this).autocomplete('widget').width(width);
                },
                select: function(event, ui) {
                    $(this).val(ui.item.label);
                    hiddenInput.val(ui.item.value);

                    // Mettre à jour les champs dépendants
                    var champsDependant = ['designation_' + rowIndex, 'designation_' + rowIndex + 'libelle'];
                    if (ui.item.retour) {
                        var valeurs = ui.item.retour.split(';');
                        for (let i = 0; i < champsDependant.length; i++) {
                            var champId = champsDependant[i];
                            if ($('#' + champId).length) {
                                $('#' + champId).val(valeurs[i]);
                            }
                        }
                    }

                    setTimeout(function(){
                        hiddenInput.trigger('change');
                    }, 100);
                    autocompleteTriggered = false;
                    return false;
                }
            }).autocomplete('disable');

            // Gérer les événements clavier
            libelleInput.off('keydown.autocomplete').on('keydown.autocomplete', function(event) {
                if (event.key === 'Tab') {
                    event.preventDefault();
                    var cursorPos = this.selectionStart;
                    autocompleteTriggered = true;
                    $(this).autocomplete('enable').autocomplete('search', $(this).val());
                    var el = this;
                    el.focus();
                    el.setSelectionRange(cursorPos, cursorPos);
                }
            });

            libelleInput.off('input.autocomplete').on('input.autocomplete', function() {
                hiddenInput.val('');
                autocompleteTriggered = false;
                $(this).autocomplete('disable');
            });

            // Bouton de recherche
            searchBtn.off('click').on('click', function() {
                autocompleteTriggered = true;
                libelleInput.autocomplete('enable').autocomplete('search', libelleInput.val());
            });
        }
    }
    function dependante(valeurFiltre, champDependant, nomTable, nomClasse, nomColoneFiltre, nomColvaleur, nomColAffiche)
    {
        document.getElementById(champDependant).length = 0;
        var valeurEnvoyee = valeurFiltre + "' AND " + nomColAffiche + " IS NOT NULL AND " + nomColoneFiltre + " = '" + valeurFiltre;
        var param = {'valeurFiltre': valeurEnvoyee, 'nomTable': nomTable, 'nomClasse': nomClasse, 'nomColoneFiltre': nomColoneFiltre, 'nomColvaleur': nomColvaleur, 'nomColAffiche': nomColAffiche};
        var lesValeur=[new Option("-","",false,false)];
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/Deroulante',
            contentType: 'application/json',
            data: param,
            success: function (ma) {
                if (!ma || !ma.trim()) { addOptions(champDependant, lesValeur); return; }
                var data = JSON.parse(ma);

                for (i in data.valeure)
                {
                    lesValeur.push(new Option(data.valeure[i].valeur, data.valeure[i].id, false, false));
                }
                addOptions(champDependant, lesValeur);
            }
        });


    }
    function dependanteMultiple(valeurFiltre, prefixeChamp, nomTable, nomClasse, nomColoneFiltre, nomColvaleur, nomColAffiche)
    {
        var selects = document.querySelectorAll("select[id^='" + prefixeChamp + "_']");
        if (selects.length === 0) {
            return;
        }
        if (!valeurFiltre) {
            for (var v = 0; v < selects.length; v++) {
                remplirOptions(selects[v], []);
            }
            return;
        }
        var valeurEnvoyee = valeurFiltre + "' AND " + nomColAffiche + " IS NOT NULL AND " + nomColoneFiltre + " = '" + valeurFiltre;
        var param = {'valeurFiltre': valeurEnvoyee, 'nomTable': nomTable, 'nomClasse': nomClasse, 'nomColoneFiltre': nomColoneFiltre, 'nomColvaleur': nomColvaleur, 'nomColAffiche': nomColAffiche};
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/Deroulante',
            contentType: 'application/json',
            data: param,
            success: function (ma) {
                if (!ma || !ma.trim()) {
                    for (var e = 0; e < selects.length; e++) {
                        remplirOptions(selects[e], []);
                    }
                    return;
                }
                var data = JSON.parse(ma);
                for (var s = 0; s < selects.length; s++) {
                    remplirOptions(selects[s], data.valeure);
                }
            },
            error: function (ma) {
                console.log(ma);
            }
        });
    }
    function dependante(valeurFiltre,champDependant,nomTable,nomClasse,nomColoneFiltre,nomColvaleur,nomColAffiche)
    {
        champDependant = resoudreChampDependantIndexe(champDependant);

        document.getElementById(champDependant).length=0;
        var param = {'valeurFiltre':valeurFiltre,'nomTable':nomTable,'nomClasse':nomClasse,'nomColoneFiltre':nomColoneFiltre,'nomColvaleur':nomColvaleur,'nomColAffiche':nomColAffiche};
        var lesValeur=[new Option("-","",false,false)];
        $.ajax({
            type:'GET',
            url:'${pageContext.request.contextPath}/Deroulante',
            contentType: 'application/json',
            data:param,
            success:function(ma){
                var data = JSON.parse(ma);

                for(i in data.valeure)
                {
                    lesValeur.push(new Option(data.valeure[i].valeur, data.valeure[i].id, false, false));
                }
                addOptions(champDependant,lesValeur);
            },
            error:function(ma){
                console.log(ma);
            }
        });
    }

    // Résout le vrai id du champ dépendant dans un contexte de tableau multi-lignes.
    // Le HTML généré côté serveur ne connaît pas l'index de ligne (ex: 'idparquet' au lieu
    // de 'idparquet_2'), donc on le déduit de l'élément qui vient de déclencher l'événement
    // (encore actif au moment du "change").
    function resoudreChampDependantIndexe(champDependant)
    {
        if (document.getElementById(champDependant)) {
            // Le champ existe tel quel (formulaire simple, hors tableau) : rien à faire.
            return champDependant;
        }

        var source = document.activeElement;
        if (source && source.id) {
            var m = source.id.match(/_(\d+)$/);
            if (m) {
                var champIndexe = champDependant + '_' + m[1];
                if (document.getElementById(champIndexe)) {
                    return champIndexe;
                }
            }
        }

        // Rien trouvé : on retourne la valeur d'origine (comportement inchangé, l'erreur
        // getElementById(...) === null remontera comme avant si le champ n'existe vraiment pas).
        return champDependant;
    }
    function addOptions(nomListe, lesopt)
    {
        var List = document.getElementById(nomListe);
        var elOption = lesopt;

        var i, n;
        n = elOption.length;

        for (i = 0; i < n; i++)
        {
            List.options.add(elOption[i]);
        }
    }

    function dependanteMultiple(valeurFiltre, prefixeChamp, nomTable, nomClasse, nomColoneFiltre, nomColvaleur, nomColAffiche)
    {
        var selects = document.querySelectorAll("select[id^='" + prefixeChamp + "_']");
        if (selects.length === 0) {
            return;
        }
        if (!valeurFiltre) {
            for (var v = 0; v < selects.length; v++) {
                remplirOptions(selects[v], []);
            }
            return;
        }
        var valeurEnvoyee = valeurFiltre + "' AND " + nomColAffiche + " IS NOT NULL AND " + nomColoneFiltre + " = '" + valeurFiltre;
        var param = {'valeurFiltre': valeurEnvoyee, 'nomTable': nomTable, 'nomClasse': nomClasse, 'nomColoneFiltre': nomColoneFiltre, 'nomColvaleur': nomColvaleur, 'nomColAffiche': nomColAffiche};
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/Deroulante',
            contentType: 'application/json',
            data: param,
            success: function (ma) {
                if (!ma || !ma.trim()) {
                    for (var e = 0; e < selects.length; e++) {
                        remplirOptions(selects[e], []);
                    }
                    return;
                }
                var data = JSON.parse(ma);
                for (var s = 0; s < selects.length; s++) {
                    remplirOptions(selects[s], data.valeure);
                }
            },
            error: function (ma) {
                console.log(ma);
            }
        });
    }

    function remplirOptions(select, valeurs)
    {
        var ancienne = select.value;
        select.length = 0;
        select.options.add(new Option("-", "", false, false));
        for (var i in valeurs) {
            select.options.add(new Option(valeurs[i].valeur, valeurs[i].id, false, false));
        }
        select.value = ancienne;
        if (window.jQuery && $(select).hasClass('select2')) {
            $(select).trigger('change.select2');
        }
    }
    function dependanteChamp(valeurFiltre, champDependant, nomTable, nomClasse, nomColoneFiltre, nomColvaleur, nomColAffiche, nomOrderby, sensOrderBy)
    {
        $('#' + champDependant + " option").remove();
        var param = {'valeurFiltre': valeurFiltre, 'nomTable': nomTable, 'nomClasse': nomClasse, 'nomColoneFiltre': nomColoneFiltre, 'nomColvaleur': nomColvaleur, 'nomColAffiche': nomColAffiche, 'nomOrderby': nomOrderby, 'sensOrderBy': sensOrderBy};
        var valeur = "";
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/Deroulante',
            contentType: 'application/json',
            data: param,
            success: function (ma) {
                var data = JSON.parse(ma);

                for (i in data.valeure)
                {
                    valeur += data.valeure[i].valeur;
                }
                console.log(valeur);
                addChamp(champDependant, valeur);
            }
        });


    }

    function addChamp(nomListe, valeur)
    {
        document.getElementById(nomListe).value = valeur;

    }
    function dependanteChampUneValeur(valeurFiltre, champDependant, nomTable, nomClasse, nomColoneFiltre, nomColvaleur, nomColAffiche, nomOrderby, sensOrderBy)
    {
        $('#' + champDependant + " option").remove();
        var param = {'valeurFiltre': valeurFiltre, 'nomTable': nomTable, 'nomClasse': nomClasse, 'nomColoneFiltre': nomColoneFiltre, 'nomColvaleur': nomColvaleur, 'nomColAffiche': nomColAffiche, 'nomOrderby': nomOrderby, 'sensOrderBy': sensOrderBy};
        var valeur = "";
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/Deroulante?estListe=false',
            contentType: 'application/json',
            data: param,
            success: function (ma) {
                var data = JSON.parse(ma);

                for (i in data.valeure)
                {
                    valeur += data.valeure[i].valeur;
                }
                addChamp(champDependant, valeur);
            }
        });


    }
</script>
<script src="${pageContext.request.contextPath}/assets/js/script.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/assets/js/controleTj.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>

<script src="${pageContext.request.contextPath}/assets/js/soundmanager2-jsmin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/messagews.js" type="text/javascript"></script>
<script type="text/javascript">
    if (typeof (Storage) !== "undefined") {
        // Code for localStorage/sessionStorage.
        var collapse = localStorage.getItem("menuCollapse");

    } else {
        // Sorry! No Web Storage support..
    }
    $(document).ready(function () {

        if (localStorage.getItem("menuCollapse") == "true") {
            $("body").addClass("sidebar-collapse");
        }

        $(".sidebar-toggle").click(function () {
            if (localStorage.getItem("menuCollapse") == "false" || localStorage.getItem("menuCollapse") == "") {
                localStorage.setItem("menuCollapse", "true");
            } else {
                localStorage.setItem("menuCollapse", "false");
            }
        });

        //TAB INDEX
        var tab = $("[tabindex]");
        for (var i = 0; i < tab.length; i++) {
            $(tab[i]).removeAttr("tabindex");
        }
        var nombre_form = $($("form")[1]).length;

        for (var f = 0; f < nombre_form; f++) {
            var id_index = 1;

            var new_elm = $($("form")[1])[f];

            for (var i = 0; i < new_elm.length; i++) {
                if ($(new_elm[i]).context.type === "hidden" || $(new_elm[i]).context.readOnly) {

                } else {
                    $(new_elm[i]).attr("tabindex", id_index);
                    id_index++;
                }

            }
        }

    });
		
		
		
		
		function fetchAutocomplete(request, response, affiche, valeur, colFiltre, nomTable, classe,useMocle,champRetour) {
		if (request.term.length >= 1) {
				$.ajax({
						url: "${pageContext.request.contextPath}/autocomplete",
						method: "GET",
						contentType: "application/x-www-form-urlencoded",
						dataType: "json",
						data: {
								libelle: request.term,
								affiche: affiche,
								valeur: valeur,
								colFiltre: colFiltre,
								nomTable: nomTable,
								classe: classe,
								useMotcle:useMocle,
                                champRetour: champRetour
						},
						success: function(data) {
								response($.map(data.valeure, function(item) {
										return {
												label: item.valeur,
												value: item.id,
                                                retour: item.retour
										};
								}));
						}
				});
		}
		}
    function fetchAutocomplete(request, response, affiche, valeur, colFiltre, nomTable, classe,useMocle,champRetour,ac_Where) {
        if (request.term.length >= 1) {
            $.ajax({
                url: "${pageContext.request.contextPath}/autocomplete",
                method: "GET",
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                data: {
                    libelle: request.term,
                    affiche: affiche,
                    valeur: valeur,
                    colFiltre: colFiltre,
                    nomTable: nomTable,
                    classe: classe,
                    useMotcle:useMocle,
                    champRetour: champRetour,
                    ac_Where:ac_Where
                },
                success: function(data) {
                    response($.map(data.valeure, function(item) {
                        return {
                            label: item.valeur,
                            value: item.id,
                            retour: item.retour
                        };
                    }));
                }
            });
        }
    }
    function fetchAutocomplete(request, response, affiche, valeur, colFiltre, nomTable, classe,useMocle,champRetour,ac_Where, isPropre) {
        if (request.term.length >= 1) {
            $.ajax({
                url: "${pageContext.request.contextPath}/autocomplete",
                method: "GET",
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                data: {
                    libelle: request.term,
                    affiche: affiche,
                    valeur: valeur,
                    colFiltre: colFiltre,
                    nomTable: nomTable,
                    classe: classe,
                    useMotcle:useMocle,
                    champRetour: champRetour,
                    ac_Where:ac_Where,
                    isPropre:isPropre
                },
                success: function(data) {
                    response($.map(data.valeure, function(item) {
                        return {
                            label: item.valeur,
                            value: item.id,
                            retour: item.retour
                        };
                    }));
                }
            });
        }
    }

    function synchro(champ,champCheck)
    {
        // var vraiNom=champ.name.substring(9);
        let checkboxes = document.querySelectorAll('input[name="ids"]');
        checkboxes.forEach((checkbox) => {
            if(checkbox.id===champCheck) {
                checkbox.checked = true;
            }
        });
    }

    <%--function uploadFormWS(event, containerId) {--%>
    <%--    event.preventDefault();--%>

    <%--    const container = document.getElementById(containerId);--%>
    <%--    const tempForm = document.createElement("form");--%>
    <%--    Array.from(container.children).forEach(child => {--%>
    <%--        tempForm.appendChild(child.cloneNode(true));--%>
    <%--    });--%>
    <%--    const fd = new FormData(tempForm);--%>

    <%--    document.getElementById("loader").style.display = "flex";--%>

    <%--    const url = `${pageContext.request.contextPath}/upload_excel`;--%>

    <%--    $.ajax({--%>
    <%--        url: url,--%>
    <%--        type: 'POST',--%>
    <%--        data: fd,--%>
    <%--        contentType: false,--%>
    <%--        processData: false,--%>
    <%--        success: function(response) {--%>
    <%--            const doc = new DOMParser().parseFromString(response, 'text/html');--%>
    <%--            const targetDiv = document.getElementById('butfillejsp');--%>

    <%--            if (!targetDiv) {--%>
    <%--                document.getElementById("loader").style.display = "none";--%>
    <%--                console.error('Élément cible butfillejsp introuvable.');--%>
    <%--                return;--%>
    <%--            }--%>

    <%--            targetDiv.innerHTML = '';--%>
    <%--            const newContent = doc.getElementById('butfillejsp');--%>

    <%--            if (!newContent) {--%>
    <%--                document.getElementById("loader").style.display = "none";--%>
    <%--                console.error('Élément butfillejsp introuvable dans la réponse.');--%>
    <%--                return;--%>
    <%--            }--%>

    <%--            // 1. Injecter d'abord le contenu normalement--%>
    <%--            targetDiv.innerHTML = newContent.innerHTML;--%>

    <%--            // 2. Cocher les cases nécessaires et compter celles cochées--%>
    <%--            let checkedCount = 0;--%>
    <%--            const rows = targetDiv.querySelectorAll('tr');--%>
    <%--            rows.forEach(row => {--%>
    <%--                const checkbox = row.querySelector('input[type="checkbox"]');--%>
    <%--                if (!checkbox) return;--%>

    <%--                const inputs = row.querySelectorAll('input[type="textbox"]');--%>
    <%--                let shouldCheck = false;--%>

    <%--                inputs.forEach(input => {--%>
    <%--                    const value = input.value.trim();--%>
    <%--                    if (value) {--%>
    <%--                        const numValue = parseFloat(value);--%>
    <%--                        if (!isNaN(numValue)) {--%>
    <%--                            if (numValue > 1) shouldCheck = true;--%>
    <%--                        } else {--%>
    <%--                            shouldCheck = true;--%>
    <%--                        }--%>
    <%--                    }--%>
    <%--                });--%>

    <%--                if (shouldCheck) {--%>
    <%--                    checkbox.checked = true;--%>
    <%--                    checkedCount++;--%>
    <%--                }--%>
    <%--            });--%>

    <%--            // ✅ Mettre à jour l'input hidden "nombreLigne"--%>
    <%--            const hiddenInput = document.getElementById('nombreLigne');--%>
    <%--            if (hiddenInput) {--%>
    <%--                hiddenInput.value = checkedCount;--%>
    <%--            } else {--%>
    <%--                console.warn('Input hidden avec id="nombreLigne" introuvable.');--%>
    <%--            }--%>

    <%--            // 3. Réexécuter les scripts seulement après--%>
    <%--            newContent.querySelectorAll('script').forEach(script => {--%>
    <%--                const newScript = document.createElement('script');--%>
    <%--                newScript.text = script.textContent;--%>
    <%--                targetDiv.appendChild(newScript);--%>
    <%--            });--%>

    <%--            document.getElementById("loader").style.display = "none";--%>
    <%--        },--%>
    <%--        error: function() {--%>
    <%--            document.getElementById("loader").style.display = "none";--%>
    <%--            console.error('Erreur lors de la mise à jour');--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>
<%--
    function uploadFormWS(event, containerId) {
        event.preventDefault();

        const container = document.getElementById(containerId);
        const fd = new FormData();

        container.querySelectorAll("input").forEach(input => {
            if (input.type === "file") {
                if (input.files.length > 0) {
                    fd.append(input.name, input.files[0]);
                }
            } else {
                fd.append(input.name, input.value);
            }
        });

        fd.append("action", "getDataFromCsv");

        document.getElementById("loader").style.display = "flex";

        const url = `${pageContext.request.contextPath}/LoadCSV`;

        $.ajax({
            url: url,
            type: 'POST',
            data: fd,
            contentType: false,
            processData: false,
            dataType: 'json',
            success: function(response) {

                document.getElementById("loader").style.display = "none";
                const data = response;
                const tbody = document.getElementById("ajout_multiple_ligne");

                let idMap = {};
                tbody.querySelectorAll('[id]').forEach(el => {
                    idMap[el.id.toLowerCase()] = el;
                });

                const nombreLigneInput = document.getElementById("nombreLigne");
                const nombreLigneActuel = parseInt(nombreLigneInput.value, 10);
                if (nombreLigneActuel < data.length) {
                    nombreLigneInput.value = data.length;
                }

                let addLine = false;
                const addBtn = document.getElementById("addLines");

                data.forEach((rowData, index) => {

                    if (addLine) {
                        if (addBtn) {
                            addBtn.click();
                            idMap = {};
                            tbody.querySelectorAll('[id]').forEach(el => {
                                idMap[el.id.toLowerCase()] = el;
                            });
                        }
                    } else {
                        if (index + 1 === nombreLigneActuel) {
                            addLine = true;
                        }
                    }

                    const tr = tbody.querySelectorAll("tr")[index];
                    if (!tr) return;

                    for (const [key, value] of Object.entries(rowData)) {
                        const inputId = key + '_' + index;
                        const field = idMap[inputId.toLowerCase()];

                        if (!field) return;

                        if (field instanceof HTMLSelectElement) {
                            const option = Array.from(field.options).find(opt =>
                                opt.value == value || opt.text.trim() == value
                            );

                            if (option) {
                                option.selected = true;
                                field.dispatchEvent(new Event('change'));
                            }

                        } else if (field instanceof HTMLInputElement || field instanceof HTMLTextAreaElement) {
                            field.value = value;
                        }

                        const inputLibelleId = inputId + 'libelle';
                        const inputLibelle = idMap[inputLibelleId.toLowerCase()];
                        if (inputLibelle) {
                            inputLibelle.value = value;
                        }
                    }

                    const checkbox = tr.querySelector('#checkbox' + index);
                    if (checkbox) {
                        checkbox.checked = true;
                    }
                });
            },
            error: function(xhr, status, error) {
                document.getElementById("loader").style.display = "none";
                console.error('Erreur lors de la récupération JSON :', error);
            }
        });
    }--%>

    function uploadFormWS(event, containerId) {
        event.preventDefault();

        const container = document.getElementById(containerId);
        const tempForm = document.createElement("form");
        Array.from(container.children).forEach(child => {
            tempForm.appendChild(child.cloneNode(true));
        });
        const fd = new FormData(tempForm);

        document.getElementById("loader").style.display = "flex";

        const url = `${pageContext.request.contextPath}/upload_excel`;

        $.ajax({
            url: url,
            type: 'POST',
            data: fd,
            contentType: false,
            processData: false,
            success: function(response) {
                const doc = new DOMParser().parseFromString(response, 'text/html');
                const targetDiv = document.getElementById('butfillejsp');

                if (!targetDiv) {
                    document.getElementById("loader").style.display = "none";
                    console.error('Élément cible butfillejsp introuvable.');
                    return;
                }

                targetDiv.innerHTML = '';
                const newContent = doc.getElementById('butfillejsp');

                if (!newContent) {
                    document.getElementById("loader").style.display = "none";
                    console.error('Élément butfillejsp introuvable dans la réponse.');
                    return;
                }

                // 1. Injecter d'abord le contenu normalement
                targetDiv.innerHTML = newContent.innerHTML;

                // 2. Cocher les cases nécessaires et compter celles cochées
                let checkedCount = 0;
                const rows = targetDiv.querySelectorAll('tr');
                rows.forEach(row => {
                    const checkbox = row.querySelector('input[type="checkbox"]');
                    if (!checkbox) return;

                    const inputs = row.querySelectorAll('input[type="textbox"]');
                    let shouldCheck = false;

                    inputs.forEach(input => {
                        const value = input.value.trim();
                        if (value) {
                            const numValue = parseFloat(value);
                            if (!isNaN(numValue)) {
                                if (numValue > 1) shouldCheck = true;
                            } else {
                                shouldCheck = true;
                            }
                        }
                    });

                    if (shouldCheck) {
                        checkbox.checked = true;
                        checkedCount++;
                    }
                });

                // ✅ Mettre à jour l'input hidden "nombreLigne"
                const hiddenInput = document.getElementById('nombreLigne');
                if (hiddenInput) {
                    hiddenInput.value = checkedCount;
                } else {
                    console.warn('Input hidden avec id="nombreLigne" introuvable.');
                }

                // 3. Réexécuter les scripts seulement après
                newContent.querySelectorAll('script').forEach(script => {
                    const newScript = document.createElement('script');
                    newScript.text = script.textContent;
                    targetDiv.appendChild(newScript);
                });

                document.getElementById("loader").style.display = "none";
            },
            error: function() {
                document.getElementById("loader").style.display = "none";
                console.error('Erreur lors de la mise à jour');
            }
        });
    }

    function dynamicAutocompleteDependantOne(
        filterSourceElement,
        filterDbColumnName,
        filterOperator,
        dependentFieldBaseName,
        ac_affiche_param,
        ac_valeur_param,
        ac_colFiltre_param,
        ac_nomTable_param,
        ac_classe_param,
        ac_useMotcle_param,
        ac_champRetour_param,
        dependentFieldsToMap_str
    ) {
        // var realChamp = filterSourceElement.replace("libelle", "");
        // let fieldName = "idproduit_0libelle";
        var valeurDefaut = filterSourceElement.value;
        var filtreID = "";

        if (valeurDefaut && valeurDefaut.includes(" - ")) {
            filtreID = valeurDefaut.split(" - ")[0].trim(); // Extrait l'ID
        } else if (valeurDefaut) {
            filtreID = valeurDefaut.trim();
        }

        var dynamicAWhere = "";
        if (filtreID !== "") {
            var escapedValue = filtreID.replace(/'/g, "''");
            if (filterOperator.toUpperCase() === 'LIKE') {
                dynamicAWhere = " AND UPPER(" + filterDbColumnName + ") LIKE UPPER('%" + escapedValue + "%') ";
            } else {
                dynamicAWhere = " AND UPPER(" + filterDbColumnName + ") " + filterOperator + " UPPER('" + escapedValue + "') ";
            }
        }
        console.log("AWhere:", dynamicAWhere);

        var dependentFieldsToMap_array = [];
        if (dependentFieldsToMap_str && typeof dependentFieldsToMap_str === 'string') {
            dependentFieldsToMap_array = dependentFieldsToMap_str.split(';');
        }

        let currentDisplayFieldId = dependentFieldBaseName + "libelle";
        let currentIdFieldId = dependentFieldBaseName;
        let currentSearchBtnId = dependentFieldBaseName + "searchBtn";

        if ($("#" + currentDisplayFieldId).data('ui-autocomplete')) {
            $("#" + currentDisplayFieldId).autocomplete('destroy');
        }

        $("#" + currentIdFieldId).val('');
        $("#" + currentDisplayFieldId).val('');

        dependentFieldsToMap_array.forEach(function(baseFieldName) {
            const regex = /^(\w+)(libelle)$/;
            if (regex.test(baseFieldName)) {
                $('#' + baseFieldName.replace(regex, '$1libelle')).val('');
            } else {
                $('#' + baseFieldName).val('');
            }
        });

        $("#" + currentDisplayFieldId).autocomplete({
            source: function(request, response) {
                $("#" + currentIdFieldId).val('');
                fetchAutocomplete(
                    request,
                    response,
                    ac_affiche_param,
                    ac_valeur_param,
                    ac_colFiltre_param,
                    ac_nomTable_param,
                    ac_classe_param,
                    ac_useMotcle_param,
                    ac_champRetour_param,
                    dynamicAWhere
                );
            },
            select: function(event, ui) {
                $("#" + currentDisplayFieldId).val(ui.item.label);
                $("#" + currentIdFieldId).val(ui.item.value);
                $("#" + currentIdFieldId).trigger('change');

                var returnedValues = ui.item.retour.split(';');
                dependentFieldsToMap_array.forEach(function(baseFieldName, k_idx) {
                    if (k_idx < returnedValues.length) {
                        const regex = /^(\w+)(libelle)$/;
                        if (regex.test(baseFieldName)) {
                            $('#' + baseFieldName.replace(regex, '$1libelle')).val(returnedValues[k_idx]);
                        } else {
                            $('#' + baseFieldName).val(returnedValues[k_idx]);
                        }
                    }
                });
                return false;
            },
            minLength: 1
        });

        $("#" + currentSearchBtnId).off('click').on('click', function() {
            $("#" + currentDisplayFieldId).autocomplete('search', $("#" + currentDisplayFieldId).val());
        });
    }


</script>
<script language="javascript">
    (function ($) {
        var title = ($('h1:first').text());
        if (title === '' || title == null)
            title = ($('h2:first').text());
        if (title === '' || title == null)
            title = 'ERP';
        document.title = title;
    }(jQuery));
</script>