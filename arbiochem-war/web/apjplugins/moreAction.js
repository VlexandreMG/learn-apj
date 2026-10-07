(function () {
    let floatingMenu = document.getElementById('floating-action-menu');
    if (!floatingMenu) {
        floatingMenu = document.createElement('div');
        floatingMenu.id = 'floating-action-menu';
        floatingMenu.className = 'mode_action';
        floatingMenu.style.cssText = `
            position: fixed;
            display: none;
            z-index: 9999;
            margin: 0;
        `;
        document.body.appendChild(floatingMenu);
    }

    let activeBtn = null;

    function getTableWrapper(btn) {
        return btn.closest('.table-responsive, .table-container, .box');
    }

    function positionMenu(btn) {
        const rect = btn.getBoundingClientRect();
        const menuH = floatingMenu.offsetHeight;
        const menuW = floatingMenu.offsetWidth;

        // Horizontal
        let left = rect.right - menuW;
        if (left < 8) left = rect.left;

        // Conteneur du tableau
        const tableWrapper = getTableWrapper(btn);
        const tableRect = tableWrapper
            ? tableWrapper.getBoundingClientRect()
            : { top: 0, bottom: window.innerHeight };

        const spaceBelow = window.innerHeight - rect.bottom;
        const spaceAbove = rect.top - tableRect.top;

        let top;
        if (spaceBelow >= menuH + 8) {
            top = rect.bottom + 4;
        } else if (spaceAbove >= menuH + 8) {
            top = rect.top - menuH - 4;
        } else {
            top = Math.max(tableRect.top + 4, rect.bottom + 4);
        }

        floatingMenu.style.top = top + 'px';
        floatingMenu.style.left = left + 'px';

        // Cacher si le menu sort des bornes du conteneur
        const menuTop = top;
        const menuBottom = top + menuH;

        if (menuBottom < tableRect.top || menuTop > tableRect.bottom) {
            floatingMenu.style.visibility = 'hidden';
        } else {
            floatingMenu.style.visibility = 'visible';
        }
    }

    function bindButtons() {
        document.querySelectorAll('.more_info').forEach(function (btn) {
            if (btn.dataset.menuBound) return;
            btn.dataset.menuBound = 'true';

            btn.addEventListener('click', function (e) {
                e.stopPropagation();

                // Toggle : refermer si on reclique sur le même bouton
                if (activeBtn === btn && floatingMenu.style.display === 'block') {
                    floatingMenu.style.display = 'none';
                    floatingMenu.style.visibility = 'hidden';
                    activeBtn = null;
                    return;
                }

                const siblingMenu = btn.nextElementSibling;
                if (!siblingMenu) return;

                floatingMenu.innerHTML = siblingMenu.innerHTML;

                // Étape 1 : afficher invisible pour mesurer les dimensions réelles
                floatingMenu.style.visibility = 'hidden';
                floatingMenu.style.display = 'block';

                // Étape 2 : positionner avec les bonnes dimensions
                positionMenu(btn);

                // Étape 3 : rendre visible (positionMenu gère visibility)
                activeBtn = btn;
            });
        });
    }

    // Fermer en cliquant ailleurs
    document.addEventListener('click', function () {
        floatingMenu.style.display = 'none';
        floatingMenu.style.visibility = 'hidden';
        activeBtn = null;
    });

    // Empêcher la fermeture quand on clique dans le menu
    floatingMenu.addEventListener('click', function (e) {
        e.stopPropagation();
    });

    let scrollRAF = null;

    window.addEventListener('scroll', function () {
        if (!activeBtn || floatingMenu.style.display !== 'block') return;

        if (scrollRAF) cancelAnimationFrame(scrollRAF);
        scrollRAF = requestAnimationFrame(function () {
            positionMenu(activeBtn);

            const tableWrapper = getTableWrapper(activeBtn);
            if (tableWrapper) {
                const tableRect = tableWrapper.getBoundingClientRect();
                const btnRect = activeBtn.getBoundingClientRect();
                if (btnRect.bottom < tableRect.top || btnRect.top > tableRect.bottom) {
                    floatingMenu.style.display = 'none';
                    floatingMenu.style.visibility = 'hidden';
                    activeBtn = null;
                }
            }
        });
    }, true);

    // Init au chargement
    document.addEventListener('DOMContentLoaded', bindButtons);

    // Re-bind après injection AJAX de nouvelles lignes
    window.bindMoreActions = bindButtons;

})();