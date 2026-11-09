<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="titol" value="Inscripció rebuda"/>
<%@ include file="parts/capcalera.jspf" %>

<p>Gràcies, <strong><c:out value="${nom}"/></strong>. T'escriurem a <c:out value="${correu}"/>.</p>
<p>Taller: <c:out value="${taller.nom}"/></p>
<p>Torns: ${fn:escapeXml(fn:join(tornsTriats.toArray(), ' i '))}</p>

<jsp:include page="parts/avis.jsp">
  <jsp:param name="text" value="Recordau: el taller de ${taller.ponent} comença a les 9.00."/>
</jsp:include>

<%@ include file="parts/peu.jspf" %>
