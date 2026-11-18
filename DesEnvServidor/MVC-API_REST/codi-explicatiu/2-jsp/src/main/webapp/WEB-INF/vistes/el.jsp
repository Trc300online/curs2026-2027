<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Expression Language"/>
<%@ include file="parts/capcalera.jspf" %>

<%-- \${...} és l'expressió escrita tal qual, sense avaluar. Al seu costat, el resultat. --%>
<h2>Atributs i propietats</h2>
<table>
  <tr><th>Expressió</th><th>Resultat</th><th>Equival a</th></tr>
  <tr><td><code>\${curs}</code></td><td>${curs}</td><td>Atribut <code>curs</code></td></tr>
  <tr><td><code>\${buit}</code></td><td>${buit}</td><td>Si és <code>null</code>, res. Sense <code>NullPointerException</code></td></tr>
  <tr><td><code>\${taller.nom}</code></td><td>${taller.nom}</td><td><code>taller.getNom()</code></td></tr>
  <tr><td><code>\${taller.placesLliures}</code></td><td>${taller.placesLliures}</td><td><code>taller.getPlacesLliures()</code>: no hi ha cap camp, només el getter</td></tr>
  <tr><td><code>\${taller.ple}</code></td><td>${taller.ple}</td><td><code>taller.isPle()</code></td></tr>
  <tr><td><code>\${tallers[1].nom}</code></td><td>${tallers[1].nom}</td><td><code>tallers.get(1).getNom()</code></td></tr>
  <tr><td><code>\${tallers.size()}</code></td><td>${tallers.size()}</td><td>Una crida a un mètode</td></tr>
  <tr><td><code>\${sales.git}</code></td><td>${sales.git}</td><td><code>sales.get("git")</code></td></tr>
  <tr><td><code>\${sales['docker']}</code></td><td>${sales['docker']}</td><td><code>sales.get("docker")</code></td></tr>
</table>
<%-- ── PROVA 5 ─ Afegiu aquí ${taller.noExisteix}: 500, PropertyNotFoundException. Si l'objecte hi és, la propietat ha d'existir --%>

<h2>Operadors</h2>
<table>
  <tr><th>Expressió</th><th>Resultat</th></tr>
  <tr><td><code>\${2 + 3}</code></td><td>${2 + 3}</td></tr>
  <tr><td><code>\${10 / 4}</code></td><td>${10 / 4}</td></tr>
  <tr><td><code>\${10 % 3}</code></td><td>${10 % 3}</td></tr>
  <tr><td><code>\${edat >= 18}</code></td><td>${edat >= 18}</td></tr>
  <tr><td><code>\${edat ge 18 and curs eq 'DWES'}</code></td><td>${edat ge 18 and curs eq 'DWES'}</td></tr>
  <tr><td><code>\${edat >= 18 ? 'major' : 'menor'}</code></td><td>${edat >= 18 ? 'major' : 'menor'}</td></tr>
  <tr><td><code>\${empty buit}</code></td><td>${empty buit}</td></tr>
  <tr><td><code>\${not empty tallers}</code></td><td>${not empty tallers}</td></tr>
  <tr><td><code>\${curs += ' 26-27'}</code></td><td>${curs += ' 26-27'}</td></tr>
</table>

<h2>Àmbits: el mateix nom a tres llocs</h2>
<table>
  <tr><th>Expressió</th><th>Resultat</th></tr>
  <tr><td><code>\${qui}</code></td><td><strong>${qui}</strong></td></tr>
  <tr><td><code>\${requestScope.qui}</code></td><td>${requestScope.qui}</td></tr>
  <tr><td><code>\${sessionScope.qui}</code></td><td>${sessionScope.qui}</td></tr>
  <tr><td><code>\${applicationScope.qui}</code></td><td>${applicationScope.qui}</td></tr>
</table>

<h2>Objectes implícits</h2>
<table>
  <tr><th>Expressió</th><th>Resultat</th></tr>
  <tr><td><code>\${param.nom}</code></td><td><c:out value="${param.nom}"/></td></tr>
  <tr><td><code>\${paramValues.nom[1]}</code></td><td><c:out value="${paramValues.nom[1]}"/></td></tr>
  <tr><td><code>\${header['User-Agent']}</code></td><td><c:out value="${header['User-Agent']}"/></td></tr>
  <tr><td><code>\${pageContext.request.method}</code></td><td>${pageContext.request.method}</td></tr>
  <tr><td><code>\${pageContext.request.contextPath}</code></td><td>${pageContext.request.contextPath}</td></tr>
</table>

<%@ include file="parts/peu.jspf" %>
