#!/bin/sh
# Alternativa a compilar amb IntelliJ: compila tots els mòduls cada vegada que canvia un fitxer .java.
# El vigilant veu les classes noves i recarrega Tomcat, com si hagués compilat IntelliJ.

touch /tmp/marca
mvn -q compile && echo "Compilador: projecte compilat. Esperant canvis a src/main/java..."

while true; do
    canvi=$(find . -path '*/src/main/java/*' -name '*.java' -newer /tmp/marca 2>/dev/null | head -n 1)
    if [ -n "$canvi" ]; then
        touch /tmp/marca
        echo "Canvi a ${canvi#./}: compilant"
        mvn -q -o compile && echo "Compilador: fet"
    fi
    sleep 1
done
