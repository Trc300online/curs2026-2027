<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Benvinguda"/>
<%@ include file="parts/capcalera.jspf" %>

<%-- ${salutacio} i ${nom} són els atributs que ha deixat BenvingudaServlet --%>
<p>${salutacio}, <strong><c:out value="${nom}"/></strong>!</p>

<%-- ── PROVA 4 ─ Canviau ${salutacio} per ${salutacions}. No hi ha cap error: surt buit. Compte amb les errades de nom! --%>

<%@ include file="parts/peu.jspf" %>
