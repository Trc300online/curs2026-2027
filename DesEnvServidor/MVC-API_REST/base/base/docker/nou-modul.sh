#!/bin/sh
# Crea un mòdul web nou, llest per desplegar-se amb recàrrega en calent.
#
#   sh docker/nou-modul.sh <carpeta> <finalName>
#   sh docker/nou-modul.sh 2-jsp jsp            → http://localhost:8080/jsp/
#
# A Windows, sense sh, es pot executar dins Docker:
#   docker compose run --rm --entrypoint sh vigilant /apps/docker/nou-modul.sh 2-jsp jsp
#
# Crea el pom.xml, web.xml, index.html i un Servlet d'exemple, i afegeix el <module> al pom pare.
# Si docker compose up està engegat, el vigilant el desplega tot sol en uns segons.

set -e
ARREL=$(cd "$(dirname "$0")/.." && pwd)
MODUL=$1
CONTEXT=$2

if [ -z "$MODUL" ] || [ -z "$CONTEXT" ]; then
    echo "Ús: sh docker/nou-modul.sh <carpeta> <finalName>   (p. ex. 2-jsp jsp)"
    exit 1
fi
if [ -e "$ARREL/$MODUL" ]; then
    echo "Ja existeix la carpeta $MODUL"
    exit 1
fi
if grep -q "<finalName>$CONTEXT</finalName>" "$ARREL"/*/pom.xml 2>/dev/null; then
    echo "Un altre mòdul ja usa el finalName $CONTEXT"
    exit 1
fi

mkdir -p "$ARREL/$MODUL/src/main/java/inici" "$ARREL/$MODUL/src/main/webapp/WEB-INF"

cat > "$ARREL/$MODUL/pom.xml" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>dwes.patrons</groupId>
        <artifactId>codi-explicatiu</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>$MODUL</artifactId>
    <packaging>war</packaging>

    <dependencies>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
        </dependency>
    </dependencies>

    <build>
        <!-- target/$CONTEXT.war → http://localhost:8080/$CONTEXT/ -->
        <finalName>$CONTEXT</finalName>
    </build>
</project>
EOF

cat > "$ARREL/$MODUL/src/main/webapp/WEB-INF/web.xml" <<'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_6_1.xsd"
         version="6.1">

    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
    </welcome-file-list>

    <!-- Llegeix en UTF-8 els paràmetres dels formularis POST -->
    <request-character-encoding>UTF-8</request-character-encoding>
</web-app>
EOF

cat > "$ARREL/$MODUL/src/main/webapp/index.html" <<EOF
<!DOCTYPE html>
<html lang="ca">
<head>
  <meta charset="utf-8">
  <title>$MODUL</title>
</head>
<body>
  <h1>$MODUL</h1>
  <p><a href="hola">/hola</a></p>
</body>
</html>
EOF

cat > "$ARREL/$MODUL/src/main/java/inici/HolaServlet.java" <<EOF
package inici;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hola")
public class HolaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("Hola des de $MODUL!");
    }
}
EOF

# <module> al pom pare, just abans de </modules>
sed "s|^\([[:space:]]*\)</modules>|\1    <module>$MODUL</module>\\
\1</modules>|" "$ARREL/pom.xml" > "$ARREL/pom.xml.nou"
mv "$ARREL/pom.xml.nou" "$ARREL/pom.xml"

echo "Creat $MODUL → http://localhost:8080/$CONTEXT/"
echo "A IntelliJ: recarregau Maven (la icona de la M que surt al pom) i compilau (Cmd+F9 / Ctrl+F9)."
