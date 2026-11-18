#!/bin/sh
# El vigilant fa tres feines, cada segon:
#
#  1. DESPLEGAR: cerca els mòduls web del projecte (carpetes amb un pom.xml que diu <packaging>war</packaging>)
#     i escriu un fitxer de context per a cada un a /contexts, que per a Tomcat és conf/Catalina/localhost.
#     El nom del fitxer és la URL: el <finalName> del pom (servlets → /servlets) o, si no en té, el nom
#     de la carpeta. Tomcat desplega tot sol els fitxers nous i retira els que desapareixen (en uns 10 s).
#
#  2. RECARREGAR: si un mòdul té classes compilades noves, canviades o esborrades (o un web.xml nou),
#     demana al Manager de Tomcat que recarregui aquella aplicació. Així també es veuen els Servlets nous
#     i els canvis a @WebServlet, que necessiten tornar a arrencar l'aplicació.
#
#  3. DEPENDÈNCIES: si el pom.xml d'un mòdul canvia, copia a target/dependency les biblioteques que l'aplicació
#     necessita en execució (per exemple, JSTL). El context les munta a WEB-INF/lib, com dins un .war de veres.

CONTEXTS=/contexts
PLANTILLA=/apps/docker/tomcat/context.xml

# Mòduls desplegats ara mateix, com a "context=mòdul context=mòdul ..."
ACTIUS=""

context_de() {      # $1 = carpeta del mòdul
    nom=$(sed -n 's:.*<finalName>[[:space:]]*\([^<]*\)[[:space:]]*</finalName>.*:\1:p' "$1/pom.xml" | head -n 1)
    if [ -n "$nom" ]; then echo "$nom"; else basename "$1"; fi
}

ruta_de() {         # ROOT és l'arrel (/); la resta, /context
    if [ "$1" = ROOT ]; then echo "/"; else echo "/$1"; fi
}

compte_classes() {  # $1 = mòdul
    find "/apps/$1/target/classes" -type f 2>/dev/null | wc -l
}

sincronitza() {
    nous=""
    for pom in /apps/*/pom.xml; do
        [ -f "$pom" ] || continue
        grep -q '<packaging>war</packaging>' "$pom" || continue
        dir=${pom%/pom.xml}
        modul=${dir##*/}
        context=$(context_de "$dir")
        case " $nous " in
            *" $context="*) echo "ATENCIÓ: $modul té el mateix finalName ($context) que un altre mòdul. No es desplega."; continue ;;
        esac
        if [ ! -d "$dir/src/main/webapp" ]; then
            continue                                   # encara no és una aplicació web completa
        fi
        mkdir -p "$dir/target/classes" "$dir/target/dependency"   # Tomcat no arrenca l'aplicació si no existeixen
        sed -e "s|@MODUL@|$modul|g" "$PLANTILLA" > /tmp/context.xml
        if ! cmp -s /tmp/context.xml "$CONTEXTS/$context.xml"; then
            cp /tmp/context.xml "$CONTEXTS/$context.xml"
            touch "/tmp/marca-$context"
            compte_classes "$modul" > "/tmp/compte-$context"
            echo "Desplegant $modul a $(ruta_de "$context")"
        fi
        nous="$nous $context=$modul"
    done
    # Retira els contexts dels mòduls que ja no hi són (o que han canviat de finalName)
    for fitxer in "$CONTEXTS"/*.xml; do
        [ -f "$fitxer" ] || continue
        context=${fitxer##*/}
        context=${context%.xml}
        [ "$context" = manager ] && continue
        case " $nous " in
            *" $context="*) ;;
            *) rm -f "$fitxer"; echo "Retirant $(ruta_de "$context")" ;;
        esac
    done
    ACTIUS=$nous
}

dependencies_si_cal() {   # $1 = mòdul
    modul=$1
    # La suma del pom es guarda dins target/dependency: si no ha canviat, ni tan sols cal engegar Maven.
    # Tampoc si la carpeta ha desaparegut (mvn clean): llavors no hi ha suma i es torna a copiar
    ara=$(cat "/apps/$modul/pom.xml" "/apps/pom.xml" 2>/dev/null | cksum)
    if [ "$ara" != "$(cat "/apps/$modul/target/dependency/.pom" 2>/dev/null)" ]; then
        echo "Dependències de $modul: copiant les del pom.xml a target/dependency"
        rm -rf "/apps/$modul/target/dependency"
        # includeScope=runtime: el que aniria dins el .war. Les provided (Servlet) ja les posa Tomcat
        mvn -q -f "/apps/$modul/pom.xml" dependency:copy-dependencies -DincludeScope=runtime \
            || echo "ATENCIÓ: no s'han pogut copiar les dependències de $modul. Es tornarà a provar quan canviï el pom.xml."
        mkdir -p "/apps/$modul/target/dependency"     # Tomcat no arrenca l'aplicació si no existeix
        echo "$ara" > "/apps/$modul/target/dependency/.pom"
    fi
}

recarrega_si_cal() {   # $1 = context, $2 = mòdul
    context=$1
    modul=$2
    canvi=$(find "/apps/$modul/target/classes" "/apps/$modul/target/dependency" "/apps/$modul/src/main/webapp/WEB-INF/web.xml" \
                 -type f -newer "/tmp/marca-$context" 2>/dev/null | head -n 1)
    # Una classe esborrada no deixa cap fitxer nou: per això també comptam quantes n'hi ha
    compte=$(compte_classes "$modul")
    if [ -z "$canvi" ] && [ "$compte" != "$(cat "/tmp/compte-$context" 2>/dev/null)" ]; then
        canvi="$modul/target/classes (fitxers esborrats)"
    fi
    echo "$compte" > "/tmp/compte-$context"
    if [ -n "$canvi" ]; then
        sleep 1                                        # deixam acabar la compilació
        touch "/tmp/marca-$context"
        compte_classes "$modul" > "/tmp/compte-$context"
        echo "Canvi a ${canvi#/apps/}: recarregant $(ruta_de "$context")"
        resposta=$(curl -s -u "$USUARI:$CONTRASENYA" "$MANAGER/reload?path=$(ruta_de "$context")")
        case "$resposta" in
            # Si l'aplicació havia fallat en arrencar (un filtre sense compilar, un web.xml dolent), no es pot recarregar: s'engega
            *"not yet been started"*) resposta=$(curl -s -u "$USUARI:$CONTRASENYA" "$MANAGER/start?path=$(ruta_de "$context")") ;;
        esac
        echo "$resposta"
    fi
}

# En arrencar: començam de zero i deixam preparats tots els contexts abans que arrenqui Tomcat
rm -f "$CONTEXTS"/*.xml
cp /apps/docker/tomcat/manager.xml "$CONTEXTS/manager.xml"
sincronitza
# Les dependències, abans que arrenqui Tomcat: la primera vegada, les aplicacions ja les troben totes
for app in $ACTIUS; do
    dependencies_si_cal "${app#*=}"
    touch "/tmp/marca-${app%%=*}"
done
touch /tmp/preparat

while true; do
    sincronitza
    for app in $ACTIUS; do
        dependencies_si_cal "${app#*=}"
        recarrega_si_cal "${app%%=*}" "${app#*=}"
    done
    sleep 1
done
