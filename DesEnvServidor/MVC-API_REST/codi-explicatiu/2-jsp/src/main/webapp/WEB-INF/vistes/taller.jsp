<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="titol" value="${taller.nom}"/>
<%@ include file="parts/capcalera.jspf" %>

<fmt:setLocale value="ca_ES"/>
<p>Ponent: <strong><c:out value="${taller.ponent}"/></strong></p>
<p>Ocupació: ${taller.inscrits} de ${taller.places}
   (<fmt:formatNumber value="${taller.inscrits / taller.places}" type="percent"/>)</p>
<p><a href="<c:url value='/tallers'/>">Tots els tallers</a></p>

<%@ include file="parts/peu.jspf" %>
