# Imatge per desplegar com es fa de veres: es compilen els .war i es copien dins Tomcat.
# Sense recàrrega en calent (per desenvolupar, useu docker compose up).
#
#   docker compose --profile war up --build      (amb compose)
#   docker build -t codi-explicatiu .            (a mà)

# ── 1. Compilar tots els mòduls amb Maven ──
FROM maven:3.9-eclipse-temurin-25 AS compilacio
WORKDIR /src
COPY . .
RUN mvn -q package

# ── 2. Tomcat amb els .war dins webapps ──
# jre: per executar no cal el JDK sencer
FROM tomcat:11.0-jre25-temurin
# Tomcat desplega tot sol cada .war que troba a webapps/, amb el seu nom: servlets.war → /servlets
COPY --from=compilacio /src/*/target/*.war /usr/local/tomcat/webapps/
