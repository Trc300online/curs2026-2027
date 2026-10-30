#!/bin/sh
# Crea un mòdul web nou, llest per desplegar-se amb recàrrega en calent.
#
#   sh docker/nou-modul.sh <carpeta> <finalName>
#   sh docker/nou-modul.sh 2-jsp jsp            → http://localhost:8080/jsp/
#
# A Windows, sense sh, es pot executar dins Docker:
#   docker compose run --rm --entrypoint sh vigilant /apps/docker/nou-modul.sh 2-jsp jsp
#
# Crea el pom.xml, web.xml, index.html i un Servlet d'exemple. Cada mòdul és independent:
# no hi ha pom pare, i no cal tocar res més.
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

    <groupId>dwes.patrons</groupId>
    <artifactId>$MODUL</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <properties>
        <!-- Java 25 (LTS): el mateix JDK a l'ordinador i a la imatge de Tomcat (tomcat:11.0-jdk25-temurin) -->
        <maven.compiler.release>25</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- Servlet 6.1 = Jakarta EE 11 = Tomcat 11 -->
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.1.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <!-- target/$CONTEXT.war → http://localhost:8080/$CONTEXT/ -->
        <finalName>$CONTEXT</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.4.0</version>
            </plugin>
        </plugins>
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

echo "Creat $MODUL → http://localhost:8080/$CONTEXT/"
echo "A IntelliJ: clic dret damunt $MODUL/pom.xml → Add as Maven Project, i compilau (Cmd+F9 / Ctrl+F9)."
