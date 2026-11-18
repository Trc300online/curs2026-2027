# Codi explicatiu: Jakarta EE

Aplicacions web curtes per executar i modificar en directe mentre s'expliquen les diapositives de Jakarta EE. **No són els exercicis**: els exemples (salutacions, inscripció a unes jornades, tallers, escurçador d'enllaços, comptador de visites) no coincideixen amb cap activitat.

És un projecte **Maven multimòdul**. El [`pom.xml`](pom.xml) d'aquesta carpeta és el pare, i cada subcarpeta és un mòdul que correspon a una passa del tema. A diferència de POO, cada mòdul és una **aplicació web** (`<packaging>war</packaging>`): no té `main`, sinó que es desplega a Tomcat.

| Mòdul | Diapositives | URL |
|-------|--------------|-----|
| [1-servlets](1-servlets/) | [slides-jakarta-1-servlets](../slides-jakarta-1-servlets.html) | http://localhost:8080/servlets/ |
| [2-jsp](2-jsp/) | [slides-jakarta-2-jsp](../slides-jakarta-2-jsp.html) | http://localhost:8080/jsp/ |
| [3-mvc](3-mvc/) | [slides-jakarta-3-mvc](../slides-jakarta-3-mvc.html) | http://localhost:8080/mvc/ |
| [4-sessions](4-sessions/) | [slides-jakarta-4-sessions](../slides-jakarta-4-sessions.html) | http://localhost:8080/sessions/ |
| [5-jdbc](5-jdbc/) | [slides-jakarta-5-jdbc](../slides-jakarta-5-jdbc.html) | http://localhost:8080/jdbc/ |
| [6-jpa](6-jpa/) | [slides-jakarta-6-jpa](../slides-jakarta-6-jpa.html) | http://localhost:8080/jpa/ |

Per crear-ne de nous, vegeu [Afegir un mòdul nou](#afegir-un-mòdul-nou).

## Versions

Totes triades perquè siguin actuals i coincideixin, i així no hi hagi sorpreses:

| Peça | Versió | Per què |
|------|--------|---------|
| Java | **25 (LTS)**: JDK Eclipse Temurin 25 | La LTS més recent. El `pom.xml` compila amb `release 25`, el mateix Java que du la imatge de Tomcat |
| Tomcat | **11.0** (imatge `tomcat:11.0-jdk25-temurin`) | Jakarta EE 11 · Servlet 6.1. L'etiqueta `11.0` rep tots els pegats de seguretat sense canviar de versió |
| API de Servlets | `jakarta.servlet-api` **6.1.0**, `provided` | La que implementa Tomcat 11 |
| JSTL | `jakarta.servlet.jsp.jstl-api` **3.0.2** i `org.glassfish.web:jakarta.servlet.jsp.jstl` **3.0.1** | Tomcat implementa JSP i EL, però no porta JSTL: va dins el `.war`. Són les versions estables més recents |
| MySQL | **8.4** (imatge `mysql:8.4`) | La LTS actual. S'engega amb la resta i és el mateix per a tot el curs |
| Controlador JDBC | `com.mysql:mysql-connector-j` **26.10.0** | El més recent. Va dins el `.war`, com JSTL |
| JPA | `org.hibernate.orm:hibernate-core` **7.4.14.Final** | La implementació de Jakarta Persistence 3.2. L'última estable: la 8 encara és *beta*. Va dins el `.war`, perquè Tomcat no du JPA |
| Maven | **3.9** (el que du IntelliJ, o la imatge `maven:3.9-eclipse-temurin-25`) | |
| Docker | Docker Desktop (o Docker Engine amb Compose v2) | |

> No useu Tomcat 9 ni exemples amb `javax.servlet.*`: des de Tomcat 10 els paquets són `jakarta.servlet.*`.
>
> **JDK o JRE?** Per desenvolupar, el **JDK** (compila i depura). La imatge per desplegar el `.war` usa el **JRE** (`tomcat:11.0-jre25-temurin`): per executar no cal el compilador.

## Què cal tenir a l'ordinador

- **Docker Desktop**, engegat. No cal instal·lar Tomcat.
- **IntelliJ IDEA Ultimate** amb el **JDK 25**. Si no el teniu: *File > Project Structure > SDK > Download JDK… > Version 25, Eclipse Temurin*.

## Com s'executa: Tomcat dins Docker amb recàrrega en calent

Des d'aquesta carpeta (o des d'IntelliJ: clic dret a `compose.yaml` › *Run*):

```bash
docker compose up
```

i obriu http://localhost:8080/servlets/. Per aturar-ho, Ctrl+C (o `docker compose down`).

> **No ho engegueu al mateix temps que `base`.** Els dos usen els mateixos ports: 8080 (Tomcat), 5005 (depurador) i 3307 (MySQL). Abans d'engegar-ne un, aturau l'altre amb `docker compose down` des de la seva carpeta.

**Tomcat no rep cap `.war`.** Serveix l'aplicació directament de les carpetes del projecte, muntades dins el contenidor:

| Canviau… | Què cal fer | Quan es veu |
|----------|-------------|-------------|
| Un HTML, CSS o una JSP | Res: desar | En recarregar el navegador (Tomcat comprova cada JSP com a màxim cada 4 segons) |
| Una classe Java, un Servlet nou o `@WebServlet` | Compilar: **Cmd+F9 / Ctrl+F9** a IntelliJ (*Build › Build Project*) | 2–3 segons després |
| El `web.xml` | Res: desar | 2–3 segons després |
| El `pom.xml` (una dependència) | Res: desar | Uns segons després: el vigilant la copia i recarrega |
| `compose.yaml` o `docker/` | `docker compose up` de nou | |

**Perquè no calgui ni Cmd+F9**, activau a IntelliJ *Settings › Build, Execution, Deployment › Compiler › Build project automatically*: compila tot sol quan desau.

**Cada mòdul `war` es desplega tot sol, amb el seu `<finalName>` com a URL** (`1-servlets` → `/servlets`). Vegeu més avall com funciona i com afegir-ne un.


**La consola de Tomcat** (els `System.out.println`, els errors) és la del `docker compose up`, o la pestanya *Services* d'IntelliJ. Si l'heu engegat amb `-d`: `docker compose logs -f tomcat`.

**Depurar:** Tomcat escolta el depurador al port 5005. A IntelliJ: *Run › Edit Configurations › + › Remote JVM Debug*, host `localhost`, port `5005`. Posau un punt d'aturada a un `doGet` i feis la petició.

### Sense IntelliJ

Si no compilau amb IntelliJ (per exemple, amb VS Code), engegau també el compilador. Compila dins Docker cada vegada que desau un `.java`:

```bash
docker compose --profile compilador up
```

No l'useu alhora que IntelliJ compila: tots dos escriurien a `target/classes`.

### Desplegar el .war, com en un servidor real

El [`Dockerfile`](Dockerfile) compila el `.war` amb Maven i el copia a `webapps/` d'una imatge de Tomcat amb JRE. Tomcat el desplega tot sol. Aquí no hi ha recàrrega: cada canvi vol dir tornar a construir la imatge.

```bash
docker compose --profile war up --build
```

Compila tots els mòduls del `pom.xml` pare. Les aplicacions queden a http://localhost:8081/servlets/, etc. (port 8081, per poder-les tenir engegades alhora que les de desenvolupament).

### Afegir un mòdul nou

Amb `docker compose up` engegat o no, des d'aquesta carpeta:

```bash
sh docker/nou-modul.sh 2-jsp jsp
```

Crea la carpeta `2-jsp` (amb `pom.xml`, `web.xml`, `index.html` i un `HolaServlet` d'exemple) i l'afegeix als `<modules>` del `pom.xml` pare. A IntelliJ, recarregau Maven (la icona de la «M» que surt damunt el `pom.xml`) i compilau: en uns segons ja teniu http://localhost:8080/jsp/ amb recàrrega en calent. A Windows, si no teniu `sh`: `docker compose run --rm --entrypoint sh vigilant /apps/docker/nou-modul.sh 2-jsp jsp`.

També el podeu crear a mà, o amb l'assistent d'IntelliJ: basta que sigui una carpeta d'aquest directori amb un `pom.xml` amb `<packaging>war</packaging>` i una carpeta `src/main/webapp`.

| Vull… | Què faig | Què passa |
|-------|----------|-----------|
| Una URL concreta | `<finalName>` al `pom.xml` del mòdul | `jsp` → `/jsp`. `ROOT` → l'arrel `/`. Sense `finalName`, la URL és el nom de la carpeta |
| Canviar-la | Canviar el `<finalName>` | El vigilant retira la URL antiga i desplega la nova |
| Llevar un mòdul | Esborrar-ne la carpeta | Es retira de Tomcat |

Dos mòduls no poden tenir el mateix `finalName`: el vigilant avisa i només en desplega un.

Afegiu sempre el mòdul als `<modules>` del pom pare (l'script ja ho fa). Un projecte independent (amb el seu propi pom, sense pare) també es desplega, però el compilador de Docker i la imatge `war` només compilen els mòduls del pom pare.

### Com funciona

```
El vostre ordinador                         Docker
───────────────────                         ──────
1-servlets/src/main/webapp   ──volum──▶  tomcat: /servlets
1-servlets/target/classes    ──volum──▶          (WEB-INF/classes)
1-servlets/target/dependency ──volum──▶          (WEB-INF/lib)
2-jsp/…                      ──volum──▶          /jsp
                                           ▲
                                           │ escriu els contexts i demana recàrregues
                                         vigilant
```

1. **Tomcat no rep cap `.war`.** Tota la carpeta del projecte està muntada dins el contenidor, a `/apps`. Per a cada aplicació, Tomcat llegeix un **fitxer de context** a `conf/Catalina/localhost/`. El nom del fitxer és la URL (`servlets.xml` → `/servlets`), i el contingut diu on són els fitxers web (`src/main/webapp`), les classes (`target/classes`) i les biblioteques (`target/dependency`). Ho podeu veure a la plantilla [`docker/tomcat/context.xml`](docker/tomcat/context.xml).
2. **El vigilant escriu aquests fitxers** ([`docker/vigila.sh`](docker/vigila.sh)). Cada segon cerca els mòduls `war` i en llegeix el `finalName`. Si apareix un mòdul nou, escriu el seu fitxer de context; si en desapareix un, l'esborra. Tomcat ho veu tot sol i desplega o retira l'aplicació.
3. **El vigilant també recarrega.** Si un mòdul té classes compilades noves, canviades o esborrades, o un `web.xml` nou, crida `http://tomcat:8080/manager/text/reload?path=/servlets`. Tomcat torna a arrencar només aquella aplicació, en menys d'un segon. Com que la torna a arrencar sencera, també veu els Servlets nous i els canvis a `@WebServlet`.
4. **El vigilant també copia les dependències.** Quan el `pom.xml` d'un mòdul canvia, executa `mvn dependency:copy-dependencies` i deixa les biblioteques a `target/dependency`, que Tomcat veu com a `WEB-INF/lib`, igual que dins un `.war`. Per això la seva imatge és la de Maven. La primera vegada les baixa (cal Internet) i després queden al volum `maven-repo`.
5. **Els HTML, CSS i JSP no necessiten recàrrega**: Tomcat els llegeix de `src/main/webapp` a cada petició (amb la memòria cau desactivada).

> El Manager i l'usuari `vigilant` són **només per desenvolupar**: els ports només s'obren a `127.0.0.1`. No copieu aquesta configuració a un servidor real.

**Els errors, a la consola.** `docker compose logs -f tomcat` mostra també les excepcions de les aplicacions, amb la seva causa: de vegades, la pàgina del `500` només diu la línia de la JSP. Ho fa `docker/tomcat/logging.properties`.

## La base de dades: MySQL dins Docker

`docker compose up` també engega MySQL. Usuari `dwes`, contrasenya `dwes`. L'usuari té permís per crear bases de dades, perquè cada aplicació du el seu script a `sql/`, que l'esborra i la crea de zero. Es carrega des d'aquesta carpeta, tant per començar com per tornar a començar:

```bash
docker compose exec mysql sh -c "mysql -udwes -pdwes < /apps/5-jdbc/sql/jornades.sql"
```

| Des de | On és |
|---|---|
| L'aplicació, a Tomcat | `mysql:3306`: el nom del servei |
| L'ordinador, per exemple IntelliJ *Database* | `localhost:3307` |
| La consola | `docker compose exec mysql mysql -udwes -pdwes jornades` |

Les dades són al volum `dades-mysql`: `docker compose down` les conserva, i `docker compose down -v` les esborra.

## Les línies «PROVA»

El codi compila i funciona tal com està. Les línies marcades amb `// ── PROVA n ─` estan comentades: **descomentau-les d'una en una**, compilau (Cmd+F9) i esperau la recàrrega, mirau què passa i tornau-les a comentar. El comentari de cada PROVA ja diu què passarà.

Totes les PROVA s'han comprovat amb Tomcat 11.0.21; la recàrrega en calent, amb la imatge `tomcat:11.0-jdk25-temurin` (Tomcat 11.0.27, JDK 25.0.4).

---

## 1-servlets

La pàgina d'inici ([`index.html`](1-servlets/src/main/webapp/index.html)) té un enllaç per a cada cosa que es prova sense tocar codi, ordenats pels blocs de les diapositives.

| Bloc | Classe | URL | Què ensenya |
|:-:|---|---|---|
| 2 | `primer.HolaServlet` | `/hola` | `@WebServlet`, `doGet`, `setContentType`, `getWriter`. Contingut dinàmic: l'hora canvia a cada recàrrega |
| 2 | `primer.PeticioServlet` | `/peticio/*` | Tot el que porta un `HttpServletRequest`: mètode, context, ruta del Servlet, *path info*, paràmetres i capçaleres |
| 3 | `dades.SalutacioServlet` | `/salutacio?nom=…&idioma=…&vegades=…` | `getParameter`, el `null`, convertir a `int`, escapar l'HTML |
| 3 | `dades.InscripcioServlet` | `/inscripcio` (POST des d'[`inscripcio.html`](1-servlets/src/main/webapp/inscripcio.html)) | `doPost`, `getParameterValues`, validar al servidor, `setStatus(400)` |
| 4 | `resposta.HoraServlet` | `/hora?format=html\|text\|json` | Tipus MIME i `sendError(400, …)` |
| 4 | `resposta.EnllacServlet` | `/enllac/*` | `sendRedirect` (302) i `sendError(404, …)` |
| 5 | `ciclevida.ComptadorServlet` | `/comptador` | `init`, `destroy`, una sola instància i un fil per petició |
| — | `util.Html` | — | `escapa()`: canvia `<`, `>`, `&`, `"` i `'` per entitats |

### Sense tocar codi

| Què es fa | Què passa | Concepte |
|---|---|---|
| Botó *Enviar un POST a /hola* | `405 Method Not Allowed`: *HTTP method POST is not supported by this URL* | Sense `doPost`, `HttpServlet` respon 405 |
| `/peticio/cursos/7?nom=Aina&nom=Pau` | `getContextPath()` = `/servlets`, `getServletPath()` = `/peticio`, `getPathInfo()` = `/cursos/7`, `nom` = `[Aina, Pau]` | Anatomia de la URL |
| `/salutacio` | *Bon dia, desconegut!* | `getParameter` torna `null` si el paràmetre no hi és |
| `/salutacio?vegades=tres` | `500 Internal Server Error` amb `NumberFormatException` | Tot arriba com a `String`: el que no es valida esclata |
| `/salutacio?nom=<script>…</script>` | Es veu el text, no s'executa | `Html.escapa()` |
| Formulari amb *Enviar sense validar al navegador* | `400` amb la llista d'errors | La validació del navegador no basta: sempre es valida al servidor |
| `/inscripcio` escrit a la barra | `405` | La barra d'adreces sempre fa GET |
| `/hora?format=json` | `Content-Type: application/json` | El mateix Servlet pot tornar HTML, text o JSON |
| `/hora?format=xml` | `400` amb *Format desconegut* | `sendError` |
| `/enllac/tomcat` (amb F12 > Xarxa obert) | `302` amb `Location: https://tomcat.apache.org/…`, i després una segona petició | La redirecció la fa el navegador |
| `/enllac/noexisteix` · `/noexisteix` | `404` amb el nostre missatge · `404` de Tomcat | Error generat per nosaltres o pel contenidor |
| `/comptador` des de dos navegadors | El número és compartit; cada visita la pot atendre un fil diferent (`http-nio-8080-exec-N`) | Una instància, molts fils |

### PROVA

| PROVA | On | Què es fa | Què passa | Concepte |
|:-:|---|---|---|---|
| 1 | `HolaServlet` | `text/plain` en lloc de `text/html` | El navegador mostra les etiquetes tal qual | El tipus MIME decideix com s'interpreta la resposta |
| 2 | `HolaServlet` | `System.out.println(...)` | El missatge surt a la consola de Tomcat, no al navegador | El codi s'executa al servidor |
| 3 | `SalutacioServlet` | Escriure `nom` sense escapar | Amb `?nom=<i>Aina</i>` surt en cursiva; amb `<script>` s'executa | XSS |
| 4 | `InscripcioServlet` | Descomentar `doGet` | GET a `/inscripcio` → `302` cap a `inscripcio.html` | `sendRedirect` |
| 5 | `EnllacServlet` | Un segon `sendRedirect` | `500`: *IllegalStateException: Cannot call sendRedirect() after the response has been committed* | Després de redirigir, la resposta està tancada |
| 6 | `ComptadorServlet` | `loadOnStartup = 1` | `init()` surt a la consola en (re)carregar l'aplicació, abans de cap petició. Fixau-vos també en el `destroy()` de cada recàrrega | Cicle de vida |
| 7 | `web.xml` | Comentar `<request-character-encoding>` (es recarrega tot sol) | El formulari amb «Aina Ferrà» i «matí» mostra «Aina FerrÃ » i «matÃ­». `/salutacio?nom=Ferrà` (GET) continua bé | El cos d'un POST es llegeix en ISO-8859-1 si no es diu res; la URL, Tomcat ja la llegeix en UTF-8 |
| 8 | `web.xml` | Descomentar el bloc `<servlet>` | `/hello` també mostra *Hola, món!* | Mapeig tradicional amb `web.xml` |

---

## 2-jsp

La pàgina d'inici ([`index.html`](2-jsp/src/main/webapp/index.html)) té un enllaç per a cada demo, ordenats pels blocs de les diapositives. Les vistes són a [`WEB-INF/vistes`](2-jsp/src/main/webapp/WEB-INF/vistes), i `hola.jsp` i `classica.jsp` són públiques perquè s'obren directament.

| Bloc | Fitxer | URL | Què ensenya |
|:-:|---|---|---|
| 1 | `hola.jsp` | `/hola.jsp` | Directiva `page`, expressió, comentari. Es desa i es veu sense compilar |
| 1 | `classica.jsp` | `/classica.jsp?n=5` | Scriptlet, declaració i objectes implícits |
| 2 | `servletjsp.BenvingudaServlet` + `benvinguda.jsp` | `/benvinguda?nom=Aina` | `setAttribute`, `forward`, vista dins `WEB-INF` |
| 2 | `servletjsp.AnarServlet`, `DestiServlet` + `desti.jsp` | `/anar?com=forward\|redirect` | Forward i redirect, un al costat de l'altre |
| 3 | `expressions.ElServlet` + `el.jsp` | `/el?nom=Aina&nom=Pau` | Propietats, llistes, mapes, operadors, àmbits i objectes implícits |
| 4 | `etiquetes.TallersServlet` + `tallers.jsp` | `/tallers` | `c:forEach`, `c:if`, `c:choose`, `c:url`, `fmt`, `fn`, `jsp:include` |
| 4 | `etiquetes.SalutacioServlet` + `salutacio.jsp` | `/salutacio?nom=…` | `c:out` escapa, `${…}` no |
| 4 | `etiquetes.InscripcioServlet` + `inscripcio.jsp`, `confirmacio.jsp` | `/inscripcio` | La inscripció de la passa 1, sense `println`. Els errors tornen al formulari amb els valors escrits |
| 5 | `parts/capcalera.jspf`, `parts/peu.jspf`, `parts/avis.jsp` | totes les vistes | `include` i `jsp:include` |
| 5 | `web.xml` + `error.jsp`, `errors.FallaServlet` | `/noexisteix`, `/taller?id=x`, `/falla` | `jsp-config` i `error-page` |
| — | `model.Taller`, `model.Jornades` | — | Les dades, de moment fixes. El model de veres és a la passa 3 |

### Sense tocar codi

| Què es fa | Què passa | Concepte |
|---|---|---|
| Canviar un text a `hola.jsp`, desar i recarregar | Es veu sense compilar | Jasper torna a traduir la JSP si el fitxer canvia |
| `docker compose exec tomcat cat work/Catalina/localhost/jsp/org/apache/jsp/hola_jsp.java` | El Servlet que ha escrit Jasper, ple d'`out.write` | Una JSP és un Servlet |
| `/WEB-INF/vistes/benvinguda.jsp` | `404` | Dins `WEB-INF` només s'hi arriba amb forward |
| `/anar?com=forward` | Una petició, la URL no canvia, l'atribut hi és | Forward |
| `/anar?com=redirect` (amb F12 > Xarxa obert) | `302` i una segona petició, la URL canvia a `/desti`, l'atribut no hi és | Redirect |
| `/salutacio?nom=<script>…</script>` | Es veu el text, no s'executa | `c:out` |
| Enviar `/inscripcio` amb errors | `400` i el formulari amb els valors escrits | La vista mostra errors i dades |
| `/noexisteix` · `/taller?id=x` · `/falla` | La nostra pàgina d'error, amb `404`, `404` i `500` | `error-page` |

### PROVA

A les JSP, la PROVA és un comentari que diu què s'ha de canviar. Es desa i es recarrega, sense compilar. Els `.java` sí que s'han de compilar.

| PROVA | On | Què es fa | Què passa | Concepte |
|:-:|---|---|---|---|
| 1 | `hola.jsp` | Activar la línia amb `getHora()` | `500`, amb *The method getHora() is undefined for the type LocalTime* i la línia de la JSP | La JSP es compila quan es demana |
| 2 | `classica.jsp` | Descomentar `int visites = 1;` | El comptador es queda a 1 | Declaració (camp) i scriptlet (variable local) |
| 3 | `BenvingudaServlet` | Descomentar el segon `forward` | `500`: *Cannot forward after response has been committed* | `forward` no és un `return` |
| 4 | `benvinguda.jsp` | Escriure `${salutacions}` | Surt buit, sense cap error | Un atribut que no hi és no és un error |
| 5 | `el.jsp` | Afegir `${taller.noExisteix}` | `500`: *PropertyNotFoundException: Property [noExisteix] not found on type [model.Taller]* | Una propietat que no existeix sí que és un error |
| 6 | `ElServlet` | Comentar el `setAttribute` de `qui` a la petició | `${qui}` passa a ser el de la sessió | Ordre de cerca dels àmbits |
| 7 | `2-jsp/pom.xml` | Comentar les dues dependències de JSTL | `/tallers` dona `500`. En tornar-les a posar, es recupera tot sol | Tomcat no porta JSTL |
| 8 | `tallers.jsp` | Esborrar el `taglib` del prefix `c` | No hi ha cap error: `<c:set>` i `<c:forEach>` arriben tal qual al codi font | Sense `taglib`, és text |
| 9 | `salutacio.jsp` | Canviar `c:out` per `${nom}` | El `<script>` arriba sense escapar | EL no escapa: XSS |
| 10 | `tallers.jsp` | `fmt:setLocale` a `en_US` | `12,50 €` passa a ser `€12.50` | El *locale* decideix el format |
| 11 | `benvinguda.jsp` | Afegir `<%= 1 + 1 %>` | `500`: *Scripting elements … are disallowed here* | `scripting-invalid` |
| 12 | `web.xml` | Comentar els dos `<error-page>` | Tornen les pàgines de Tomcat | `error-page` |

`error.jsp` mostra l'excepció perquè així, mentre aprenem, es veu el motiu dels `500`. A un servidor real no s'ha de fer: els detalls van al registre.

---

## 3-mvc

| Bloc | Fitxer | URL | Què ensenya |
|:-:|---|---|---|
| 1 | `model.Taller`, `model.Inscripcio` | — | JavaBean: constructor buit, getters i setters, `Serializable` |
| 1 | `model.Jornades` | — | El model: dades i regles, sense res d'HTTP. Singleton i `synchronized` |
| 2 | `controlador.TallersServlet` + `tallers.jsp` | `/tallers` | El controlador més senzill |
| 2 | `controlador.InscripcioServlet` + `inscripcio.jsp` | `/inscripcio` | Paràmetres → objecte → model → vista |
| 3 | `controlador.ConfirmacioServlet` + `confirmacio.jsp` | `/confirmacio?id=1` | Post/Redirect/Get |
| 3 | `controlador.InscripcionsServlet`, `AnullaServlet` + `inscripcions.jsp` | `/inscripcions`, POST a `/anulla` | Una acció que canvia dades: POST, sense vista pròpia |

### Sense tocar codi

| Què es fa | Què passa | Concepte |
|---|---|---|
| Inscriure's i prémer F5 a la confirmació | No es crea cap inscripció nova | Post/Redirect/Get |
| Inscriure's amb F12 > Xarxa obert | `302` amb `Location: /mvc/confirmacio?id=1`, i després un GET | La darrera petició és un GET |
| Omplir el taller *Docker* (2 places) i tornar-hi | `400`: *ja és ple*. Es desactiva al formulari | La regla és al model |
| Anul·lar una inscripció | La plaça torna a estar lliure a `/tallers` | El model manté les dades coherents |
| `/anulla?id=1` escrit a la barra | `405` | Canviar dades només per POST |
| `/confirmacio?id=99` · `/confirmacio?id=abc` | `404`, sense cap `500` | No vos fieu del client |
| Compilar qualsevol classe i tornar a `/inscripcions` | Buida: la recàrrega ha esborrat les dades | La memòria no és persistent: passa 5 |

### PROVA

| PROVA | On | Què es fa | Què passa | Concepte |
|:-:|---|---|---|---|
| 1 | `InscripcioServlet` | Canviar el `sendRedirect` per un forward | Cada F5 torna a enviar el POST: inscripcions duplicades | Per què cal Post/Redirect/Get |
| 2 | `AnullaServlet` | Descomentar `doGet` | `/anulla?id=1` a la barra esborra la inscripció | Un GET mai no ha de canviar dades |
| 3 | `Taller` | Canviar `getPlacesLliures` per `getLliures` | Compila, però `/tallers` dona un `500` que assenyala la línia de la JSP. Al log: *PropertyNotFoundException* | La vista depèn dels getters, i el compilador no ho veu |

---

## 4-sessions

Usuaris de prova: `anna` / `anna123`, `marc` / `marc123`, `admin` / `admin123` (també surten a la pàgina d'entrada).

| Bloc | Fitxer | URL | Què ensenya |
|:-:|---|---|---|
| 1 | `controlador.VisitesServlet` + `visites.jsp` | `/visites` | `HttpSession`, la cookie `JSESSIONID`, una sessió per navegador |
| 2 | `controlador.LoginServlet` + `login.jsp` | `/login` | Entrar: `autentica`, `changeSessionId`, l'usuari a la sessió |
| 2 | `controlador.LogoutServlet` | POST a `/logout` | Sortir: `invalidate` |
| 2 | `controlador.InscriuServlet`, `parts/capcalera.jspf` | POST a `/meves/inscriu` | L'usuari surt de la sessió. Un missatge d'un sol ús després d'un redirect |
| 3 | `filtre.RegistreFilter` | Totes | Un filtre passa abans i després del Servlet |
| 3 | `filtre.AutenticacioFilter` | `/meves/*`, `/admin/*` | Qui sou? Redirigeix a `/login` |
| 3 | `filtre.AdminFilter` + `controlador.TotesServlet` | `/admin/inscripcions` | Teniu permís? `403` |
| 3 | `model.Jornades.anulla` | POST a `/meves/anulla` | De qui són les dades? Aquesta regla és del model |

### Sense tocar codi

| Què es fa | Què passa | Concepte |
|---|---|---|
| F5 a `/visites`, i obrir-la també en una finestra d'incògnit | Cada navegador du el seu compte | Una sessió per navegador |
| F12 > Xarxa a qualsevol pàgina | `Set-Cookie: JSESSIONID=...` a la primera resposta, `Cookie:` a les següents | Com es reconeix el navegador |
| Esborrar la cookie a F12 > Aplicació i recarregar | Sessió nova: tornau a estar fora | La cookie és només la clau |
| `/meves/inscripcions` sense entrar | `302` a `/login` | El filtre no deixa passar |
| Entrar amb una contrasenya dolenta | `401`, i el nom es manté | El mateix missatge per al nom i la contrasenya |
| `/admin/inscripcions` amb `anna` · amb `admin` | `403` · la llista | Autenticar no és autoritzar |
| Inscriure's i prémer F5 | El missatge surt una sola vegada | Missatge d'un sol ús |
| Sortir i prémer el botó d'enrere | Torna a `/login` | `Cache-Control: no-store` |
| Compilar qualsevol classe | Fora de la sessió, i sense inscripcions | Les sessions i el model viuen a la memòria |
| `docker compose logs -f tomcat` | Una línia per petició: mètode, URL, estat i temps | `RegistreFilter` |

### PROVA

| PROVA | On | Què es fa | Què passa | Concepte |
|:-:|---|---|---|---|
| 1 | `LogoutServlet` | Comentar `invalidate` | Després de *Sortir* seguiu dins | Redirigir no tanca la sessió |
| 2 | `LoginServlet` | Comentar `changeSessionId` | La cookie no canvia en entrar | Fixació de sessió |
| 3 | `web.xml` | Moure el `filter-mapping` d'autenticació darrere el d'admin | `/admin/inscripcions` sense entrar: `500`, *NullPointerException* | L'ordre dels filtres importa |
| 4 | `Jornades.anulla` | Comentar l'`if` del propietari | Canviant l'`id` del camp ocult, `anna` anul·la la inscripció d'en `marc` | El filtre no sap de qui són les dades |
| 5 | `RegistreFilter` | Comentar `chain.doFilter` | Totes les pàgines en blanc, amb un `200` | Sense `doFilter` la petició s'atura |

## 5-jdbc

**Abans de res, carregau l'script** (vegeu [La base de dades](#la-base-de-dades-mysql-dins-docker)). Els usuaris són els mateixos de `4-sessions`. Els Servlets, els filtres i les vistes també: només canvien `model.Jornades` i els paquets nous `dao` i `dao.jdbc`.

| Bloc | Fitxer | Què ensenya |
|:-:|---|---|
| 1 | `sql/jornades.sql` | Les taules, amb les seves claus i regles: `FOREIGN KEY`, `NOT NULL`, `UNIQUE` |
| 2 | `dao.jdbc.BaseDeDades` | D'on surten les connexions: `MysqlDataSource` |
| 2 | `dao.jdbc.TallerDaoJdbc` | Llegir: `PreparedStatement`, `ResultSet`, *try-with-resources* |
| 2 | `dao.jdbc.UsuariDaoJdbc` | Forats `?` davant la injecció SQL |
| 2 | `dao.jdbc.InscripcioDaoJdbc` | Transaccions: `commit`, `rollback`, `UPDATE ... WHERE inscrits < places` |
| 3 | `dao.UsuariDao`, `dao.TallerDao`, `dao.InscripcioDao` | El patró DAO: què es pot fer, sense dir com |
| 3 | `dao.DaoException` | L'error no delata que hi ha SQL |
| 3 | `model.Jornades` | Els mateixos mètodes públics. Les úniques línies que saben que hi ha JDBC són les que creen els DAO |

### Sense tocar codi

| Què es fa | Què passa | Concepte |
|---|---|---|
| Inscriure's i compilar qualsevol classe | Fora de la sessió, però la inscripció hi és | Les dades ja no viuen a la memòria |
| `SELECT * FROM inscripcio;` a la consola | Les inscripcions fetes des del navegador | Tot és a MySQL |
| `docker compose down` i `up` | Les dades hi continuen. Amb `down -v`, cal tornar a carregar l'script | El volum |
| Entrar amb l'usuari `admin' -- ` (acaba amb un espai) | `401` | Amb `?`, és només un valor |
| Inscriure's dues vegades al mateix taller, o a un de ple | El missatge d'error de sempre | Les regles continuen funcionant |

### PROVA

| PROVA | On | Què es fa | Què passa | Concepte |
|:-:|---|---|---|---|
| 1 | `BaseDeDades` | Canviar la contrasenya · `docker compose stop mysql` i després `start` | `500`, *Access denied* · `500`, *Communications link failure*, i es recupera tot sol | Cada petició obre la seva connexió |
| 2 | `UsuariDaoJdbc` | Canviar el `sql` amb `?` pel que enganxa text, i entrar amb `admin' -- ` | Sou l'administradora, sense contrasenya | La injecció SQL |
| 3 | `InscripcioDaoJdbc.inscriu` | Descomentar el `throw` · comentar també `setAutoCommit(false)` | `500`, i no ha canviat res · `500`, i una plaça ocupada sense inscripció | Tot o res. Deixau les dades bé amb l'script |
| 4 | `InscripcioDaoJdbc.anulla` | Llevar `AND usuari = ?` i el seu `setString` | `anna` esborra una inscripció d'en `marc` | El filtre no sap de qui són les dades |

## 6-jpa

**Abans de res, carregau l'script.** Són les mateixes taules de `5-jdbc`, i la mateixa base de dades:

```bash
docker compose exec mysql sh -c "mysql -udwes -pdwes < /apps/6-jpa/sql/jornades.sql"
```

Els Servlets, els filtres, les JSP i les interfícies `dao` no canvien. Mantingueu oberta una consola amb `docker compose logs -f tomcat`: `hibernate.show_sql` hi escriu cada SQL.

| Bloc | Fitxer | Què ensenya |
|:-:|---|---|
| 1 | `src/main/resources/META-INF/persistence.xml` | La unitat de persistència: classes, connexió, `show_sql`, `validate` |
| 1 | `dao.jpa.Persistencia` | Una sola `EntityManagerFactory`, creada quan cal i tancada quan s'atura l'aplicació |
| 2 | `model.Taller` | `@Entity`, `@Table`, `@Id`. Els mètodes calculats no són columnes |
| 2 | `model.Usuari` | `@Column`. La contrasenya no té getter |
| 2 | `model.Inscripcio` | `@GeneratedValue`, `@ManyToOne(fetch = LAZY)`, `@JoinColumn` |
| 3 | `dao.jpa.TallerDaoJpa` | `callInTransaction`, JPQL, `em.find` |
| 3 | `dao.jpa.UsuariDaoJpa` | Paràmetres amb nom. `SELECT new`, sense la contrasenya |
| 3 | `dao.jpa.InscripcioDaoJpa` | `JOIN FETCH`, `UPDATE` amb JPQL, `persist`, `getReference`, `remove`, objectes gestionats, bloqueig de la fila |

### Sense tocar codi

| Què es fa | Què passa | Concepte |
|---|---|---|
| Obrir `/tallers` amb el log obert | `select t1_0.id, ... from taller t1_0 order by t1_0.nom` | El SQL el genera Hibernate |
| Obrir `/jdbc/tallers` i `/jpa/tallers` | Les mateixes dades | Les mateixes taules, dues implementacions |
| Inscriure's | `update taller ...` i `insert into inscripcio ...` | Una transacció |
| Anul·lar | `select ... for update`, `update taller set inscrits=?,nom=?,places=? ...` i `delete ...` | Objectes gestionats: cap `UPDATE` al nostre codi, i escriu totes les columnes |
| Primera petició després de compilar | Tarda una mica més, i surt un avís de Hibernate sobre el *connection pool* | La fàbrica és lenta, i per això només n'hi ha una. A un servidor real, un pool: Spring Boot du HikariCP |
| Entrar amb l'usuari `admin' OR 'a'='a` | `401` | Amb `:nom`, és només un valor |

### PROVA

| PROVA | On | Què es fa | Què passa | Concepte |
|:-:|---|---|---|---|
| 1 | `Taller` | `@Column(name = "titol")` damunt `nom` | `500`, *missing column [titol]* | `validate` compara les entitats i les taules |
| 2 | `InscripcioDaoJpa` | Llevar els `JOIN FETCH` · a `Inscripcio`, llevar també els `fetch = LAZY` | `500` a la línia de la JSP, i *LazyInitializationException* al log · funciona, amb una consulta per a cada usuari i taller | `LAZY`, `JOIN FETCH` i el problema N + 1 |
| 3 | `InscripcioDaoJpa.inscriu` | Descomentar el `throw` | `500`, i no ha canviat res | `callInTransaction` fa el `rollback` |
| 4 | `UsuariDaoJpa` | Canviar el `jpql` amb `:nom` pel que enganxa text, i entrar amb `admin' OR 'a'='a` | Sou l'administradora, sense contrasenya | JPA no evita la injecció si enganxau text |
| 5 | `InscripcioDaoJpa.anulla` | Comentar `setInscrits` | Al log ja no hi ha l'`update`, i el taller queda amb un inscrit de més | Objectes gestionats |
