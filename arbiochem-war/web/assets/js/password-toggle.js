document.addEventListener('DOMContentLoaded', function () {
    var passwordInput = document.getElementById('password-input');
    var toggleButton = document.getElementById('toggle-password');

    if (!passwordInput || !toggleButton) {
        return;
    }

    function setVisibility(isVisible) {
        passwordInput.type = isVisible ? 'text' : 'password';
        toggleButton.classList.toggle('is-visible', isVisible);
        toggleButton.setAttribute('aria-pressed', String(isVisible));
        toggleButton.setAttribute(
            'aria-label',
            isVisible ? 'Masquer le mot de passe' : 'Afficher le mot de passe'
        );
        toggleButton.title = isVisible ? 'Masquer le mot de passe' : 'Afficher le mot de passe';
    }

    toggleButton.addEventListener('click', function () {
        setVisibility(passwordInput.type === 'password');
        passwordInput.focus();
    });
});