# Imatge per desplegar com es fa de veres: es compilen els .war i es copien dins Tomcat.
# Sense recàrrega en calent (per desenvolupar, useu docker compose up).
#
#   docker compose --profile war up --build      (amb compose)
#   docker build -t base .            (a mà)

# ── 1. Compilar amb Maven cada mòdul war (cada carpeta amb un pom.xml de <packaging>war</packaging>) ──
FROM maven:3.9-eclipse-temurin-25 AS compilacio
WORKDIR /src
COPY . .
RUN for pom in */pom.xml; do \
        if grep -q '<packaging>war</packaging>' "$pom"; then mvn -q -f "$pom" package || exit 1; fi; \
    done

# ── 2. Tomcat amb els .war dins webapps ──
# jre: per executar no cal el JDK sencer
FROM tomcat:11.0-jre25-temurin
# Tomcat desplega tot sol cada .war que troba a webapps/, amb el seu nom: servlets.war → /servlets
COPY --from=compilacio /src/*/target/*.war /usr/local/tomcat/webapps/
