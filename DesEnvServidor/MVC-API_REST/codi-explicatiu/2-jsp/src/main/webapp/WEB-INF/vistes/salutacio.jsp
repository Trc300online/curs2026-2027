<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Salutació"/>
<%@ include file="parts/capcalera.jspf" %>

<%-- c:out escapa: < passa a ser &lt; i el navegador ho mostra com a text.
     ── PROVA 9 ─ Canviau el c:out per ${nom} i obriu l'enllaç del <script> de la pàgina d'inici: ara s'executa. Això és XSS --%>
<p>Hola, <strong><c:out value="${nom}" default="desconegut"/></strong>!</p>

<%@ include file="parts/peu.jspf" %>
