#!/bin/bash
# Prépare le dossier WAR du mini-projet : COPIE depuis le WAR d'origine uniquement
# les fichiers listés dans arbiochem-war-a-garder.txt (login + squelette de module.jsp).
# Rien n'est supprimé ni modifié dans le WAR d'origine.
#
# Usage :
#   bash preparer-arbiochem-war.sh <WAR d'origine> <WAR du mini-projet>
# Exemple :
#   bash preparer-arbiochem-war.sh ~/projet-base/arbiochem-war ~/mini_projet/arbiochem-war
#
# Mettez ce script et arbiochem-war-a-garder.txt dans le même dossier.
ORIG="$1"; DEST="$2"
LISTE="$(dirname "$0")/arbiochem-war-a-garder.txt"
if [ -z "$ORIG" ] || [ -z "$DEST" ]; then echo "Usage : bash $0 <WAR d'origine> <WAR du mini-projet>"; exit 1; fi
[ -d "$ORIG/web" ] || { echo "Introuvable : $ORIG/web (donnez le dossier arbiochem-war d'origine)"; exit 1; }
[ -f "$LISTE" ] || { echo "Introuvable : $LISTE"; exit 1; }

n=0; manque=0
while IFS= read -r f; do
  [ -z "$f" ] && continue
  if [ -f "$ORIG/$f" ]; then
    mkdir -p "$DEST/$(dirname "$f")"
    cp "$ORIG/$f" "$DEST/$f"
    n=$((n+1))
  else
    echo "absent dans l'original : $f"; manque=$((manque+1))
  fi
done < "$LISTE"

# Dossier des sources Java du WAR (vide : aucun servlet pour l'instant) pour que la compilation Ant ne se plaigne pas
mkdir -p "$DEST/src/java"

# Évite le conflit avec l'application de base (composant netty déjà enregistré)
JDS="$DEST/web/WEB-INF/jboss-deployment-structure.xml"
if [ ! -f "$JDS" ]; then
cat > "$JDS" <<'XML'
<?xml version="1.0" encoding="UTF-8"?>
<jboss-deployment-structure>
    <deployment>
        <exclude-subsystems>
            <subsystem name="pojo"/>
        </exclude-subsystems>
    </deployment>
</jboss-deployment-structure>
XML
  echo "créé : web/WEB-INF/jboss-deployment-structure.xml"
fi
echo "$n fichiers copiés vers $DEST ($manque absents)."
