<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Inscripció rebuda"/>
<%@ include file="parts/capcalera.jspf" %>

<p>Gràcies, <strong><c:out value="${inscripcio.nom}"/></strong>. T'escriurem a <c:out value="${inscripcio.correu}"/>.</p>
<p>Taller: <c:out value="${inscripcio.taller.nom}"/>. El vostre número és el <strong>${inscripcio.id}</strong>.</p>
<p class="apagat">Premeu F5: aquesta pàgina només llegeix, i no es crea cap inscripció nova.</p>

<%@ include file="parts/peu.jspf" %>
