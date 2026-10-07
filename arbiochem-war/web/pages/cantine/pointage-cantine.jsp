<%--
    Document   : pointage-cantine
    Created on : 16 Dec 2025, 15:33
    Author     : NH
--%>

<%@page import="user.UserEJB"%>

<%
    try{
        String titre = "Import Pointage de cantine";
%>

<style>
    /* --- CSS THEME 100% BLEU - FORMULAIRE & DRAG/DROP --- */
    :root {
        --bg-color: #6a9ae2;
        --card-bg: #ffffff;
        --text-color: #4a5568;
        --accent-blue: #3b82f6;
        --light-blue-border: #bfdbfe;
        --hover-blue: #eff6ff;
    }

    /* Zone Drag & Drop */
    .drop-zone {
        border: 2px dashed var(--accent-blue);
        border-radius: 12px;
        background-color: var(--hover-blue);
        padding: 40px 20px;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    .drop-zone:hover, .drop-zone.dragover {
        background-color: #dbeafe;
        border-color: #2563eb;
        transform: translateY(-2px);
    }

    .drop-text {
        color: var(--text-color);
        font-size: 14px;
        font-weight: 600;
        text-align: center;
    }

    .browse-link {
        color: var(--accent-blue);
        text-decoration: underline;
    }

    /* Prévisualisation fichier */
    .file-item {
        display: flex;
        align-items: center;
        padding: 15px;
        border: 1px solid #16a34a;
        border-radius: 8px;
        background-color: #f0fdf4;
        margin-bottom: 20px;
        animation: fadeIn 0.3s ease-in;
    }

    .file-icon {
        color: #16a34a;
        margin-right: 15px;
        font-size: 24px;
    }

    .file-info {
        flex-grow: 1;
        text-align: left;
        overflow: hidden;
    }

    .file-name {
        font-size: 14px;
        color: #166534;
        font-weight: 600;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .file-action {
        color: #94a3b8;
        cursor: pointer;
        margin-left: 10px;
        transition: color 0.2s;
    }

    .file-action:hover { color: #ef4444; }

    #file-to-upload-cantine { display: none; }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(5px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>

<div class="content-wrapper">
    <section class="content-header">
        <h1><%=titre%></h1>
    </section>

    <section class="content">
        <form action="<%= (String)session.getValue("lien") + "/../../ImportPointageCantineServlet"%>"
              method="post"
              enctype="multipart/form-data">

            <!-- ETAT 1 : Zone de Drag & Drop -->
            <div id="drop-zone-cantine" class="drop-zone">
                <div class="drop-text">
                    Glissez le fichier ici <br> ou <span class="browse-link">Parcourir vos dossiers</span>
                </div>
            </div>

            <!-- ETAT 2 : Fichier sélectionné -->
            <div id="file-display-area-cantine" style="display:none;">
                <div class="file-item">
                    <div class="file-icon"><i class="fa fa-file-excel-o"></i></div>
                    <div class="file-info">
                        <div class="file-name" id="display-filename-cantine">pointage.xlsx</div>
                    </div>
                    <div class="file-action" id="close-file-cantine" title="Retirer ce fichier">
                        <i class="fa fa-times" style="font-size: 18px;"></i>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary w-100" style="justify-content: center;">
                    IMPORTER
                </button>
            </div>

            <!-- Input fichier caché -->
            <input type="file" id="file-to-upload-cantine" name="excelFile" accept=".xls,.xlsx" required />
        </form>
    </section>
</div>

<!-- SCRIPT DRAG & DROP -->
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const fileInput = document.getElementById('file-to-upload-cantine');
        const dropZone = document.getElementById('drop-zone-cantine');
        const fileDisplayArea = document.getElementById('file-display-area-cantine');
        const displayFilename = document.getElementById('display-filename-cantine');
        const closeFileBtn = document.getElementById('close-file-cantine');

        if (dropZone) {
            dropZone.addEventListener('click', function() { fileInput.click(); });

            dropZone.addEventListener('dragover', function(e) {
                e.preventDefault();
                dropZone.classList.add('dragover');
            });

            dropZone.addEventListener('dragleave', function() {
                dropZone.classList.remove('dragover');
            });

            dropZone.addEventListener('drop', function(e) {
                e.preventDefault();
                dropZone.classList.remove('dragover');
                if (e.dataTransfer.files.length) {
                    fileInput.files = e.dataTransfer.files;
                    handleFile(e.dataTransfer.files[0]);
                }
            });
        }

        if (fileInput) {
            fileInput.addEventListener('change', function() {
                if (fileInput.files.length) handleFile(fileInput.files[0]);
            });
        }

        if (closeFileBtn) {
            closeFileBtn.addEventListener('click', function() {
                fileDisplayArea.style.display = 'none';
                dropZone.style.display = 'block';
                fileInput.value = '';
            });
        }

        function handleFile(file) {
            if (!file.name.match(/\.(xls|xlsx)$/i)) {
                alert('Erreur : Veuillez sélectionner un fichier Excel valide (.xls ou .xlsx).');
                fileInput.value = '';
                return;
            }
            displayFilename.innerText = file.name;
            dropZone.style.display = 'none';
            fileDisplayArea.style.display = 'block';
        }
    });
</script>

<%
} catch (Exception e) {
    e.printStackTrace();
%>
<script language="JavaScript">
    alert('<%=e.getMessage()%>');
    history.back();
</script>
<% }%>
