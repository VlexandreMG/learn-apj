#!/bin/bash
# Supprime, dans arbiochem-ejb/src/java, tous les .java qui ne sont PAS dans arbiochem-ejb-a-garder.txt
# Usage (à lancer depuis la racine du projet) :
#   ./nettoyer-arbiochem-ejb.sh            -> simulation (n'efface rien, affiche ce qui serait supprimé)
#   ./nettoyer-arbiochem-ejb.sh --supprimer -> supprime vraiment
# Conseil : faites un commit Git (ou une copie du dossier) AVANT de supprimer.
SRC="arbiochem-ejb/src/java"
LISTE="arbiochem-ejb-a-garder.txt"
[ -d "$SRC" ] || { echo "Dossier $SRC introuvable : lancez le script depuis la racine du projet."; exit 1; }
[ -f "$LISTE" ] || { echo "Fichier $LISTE introuvable (mettez-le à côté du script)."; exit 1; }
cd "$SRC" || exit 1
n=0
while IFS= read -r f; do
  if ! grep -qxF "${f#./}" "../../../$LISTE"; then
    n=$((n+1))
    if [ "$1" = "--supprimer" ]; then rm "$f"; else echo "à supprimer : $f"; fi
  fi
done < <(find . -name '*.java' | sed 's|^\./||')
if [ "$1" = "--supprimer" ]; then
  find . -type d -empty -delete
  echo "$n fichiers supprimés."
else
  echo "$n fichiers seraient supprimés (simulation, rien n'a été effacé)."
fi
