#!/usr/bin/env bash
# Nettoie le front arbiochem-war : ne garde que les .jsp / .java listés dans garder-front.txt
# Usage :
#   ./nettoyer-front.sh <dossier arbiochem-war> <garder-front.txt>            # simulation (rien n'est supprimé)
#   ./nettoyer-front.sh <dossier arbiochem-war> <garder-front.txt> --apply    # suppression réelle
# Seuls les fichiers *.jsp (sous web/) et *.java (sous src/java/) sont concernés.
# Les plugins, css, js, images, WEB-INF... ne sont jamais touchés.
set -euo pipefail
export LC_ALL=C

WAR="${1:?dossier arbiochem-war manquant}"
LISTE="${2:?fichier garder-front.txt manquant}"
APPLY="${3:-}"

[ -d "$WAR/web" ] && [ -d "$WAR/src/java" ] || { echo "ERREUR : $WAR ne ressemble pas à arbiochem-war (web/ ou src/java/ introuvable)"; exit 1; }
[ -f "$LISTE" ] || { echo "ERREUR : $LISTE introuvable"; exit 1; }

LISTE="$(cd "$(dirname "$LISTE")" && pwd)/$(basename "$LISTE")"
cd "$WAR"
tr -d '\r' < "$LISTE" | sed '/^[[:space:]]*$/d' | sort -u > /tmp/garder.$$.txt
{ find web -type f -name '*.jsp'; find src/java -type f -name '*.java'; } | sort > /tmp/existants.$$.txt
comm -23 /tmp/existants.$$.txt /tmp/garder.$$.txt > /tmp/a_supprimer.$$.txt
comm -13 /tmp/existants.$$.txt /tmp/garder.$$.txt > /tmp/manquants.$$.txt

echo "Fichiers .jsp/.java existants : $(wc -l < /tmp/existants.$$.txt)"
echo "À garder                      : $(comm -12 /tmp/existants.$$.txt /tmp/garder.$$.txt | wc -l)"
echo "À supprimer                   : $(wc -l < /tmp/a_supprimer.$$.txt)"
if [ -s /tmp/manquants.$$.txt ]; then
  echo "ATTENTION : listés dans la liste mais absents du dossier :"; cat /tmp/manquants.$$.txt
fi

if [ "$APPLY" != "--apply" ]; then
  cp /tmp/a_supprimer.$$.txt ./a_supprimer.txt
  echo "Simulation uniquement. Liste écrite dans $(pwd)/a_supprimer.txt"
  echo "Relancer avec --apply pour supprimer."
  rm -f /tmp/*.$$.txt; exit 0
fi

while IFS= read -r f; do rm -f -- "$f"; done < /tmp/a_supprimer.$$.txt
# supprime les dossiers devenus vides dans pages/ et src/java/
find web/pages src/java -type d -empty -delete 2>/dev/null || true
echo "Terminé : $(wc -l < /tmp/a_supprimer.$$.txt) fichiers supprimés."
rm -f /tmp/*.$$.txt
