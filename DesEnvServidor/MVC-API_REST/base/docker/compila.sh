#!/bin/sh
# Alternativa a compilar amb IntelliJ: compila un mòdul cada vegada que canvia un dels seus fitxers .java.
# El vigilant veu les classes noves i recarrega Tomcat, com si hagués compilat IntelliJ.
#
# No hi ha pom pare: és mòdul qualsevol carpeta amb un pom.xml. Els mòduls nous es compilen
# tot sols la primera vegada que es veuen, sense haver d'aturar res.

compila() {         # $1 = mòdul
    echo "Compilant $1..."
    mvn -q -f "$1/pom.xml" compile && echo "Compilador: $1 fet"
}

CONEGUTS=""
touch /tmp/marca

while true; do
    # Mòduls amb algun .java canviat
    canvis=$(find . -path '*/src/main/java/*' -name '*.java' -newer /tmp/marca 2>/dev/null)
    [ -n "$canvis" ] && touch /tmp/marca
    pendents=$(echo "$canvis" | sed -n 's:^\./\([^/]*\)/.*:\1:p' | sort -u)
    # Mòduls nous (també els que hi ha en arrencar): es compilen encara que no hagin canviat
    for pom in */pom.xml; do
        [ -f "$pom" ] || continue
        modul=${pom%/pom.xml}
        case " $CONEGUTS " in
            *" $modul "*) ;;
            *) CONEGUTS="$CONEGUTS $modul"; pendents="$pendents $modul" ;;
        esac
    done
    for modul in $(echo $pendents | tr ' ' '\n' | sort -u); do
        [ -f "$modul/pom.xml" ] && compila "$modul"
    done
    sleep 1
done
