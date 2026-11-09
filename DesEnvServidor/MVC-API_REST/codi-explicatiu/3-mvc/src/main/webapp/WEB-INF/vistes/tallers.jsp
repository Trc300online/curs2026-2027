<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Tallers"/>
<%@ include file="parts/capcalera.jspf" %>

<table>
  <tr><th>Taller</th><th>Inscrits</th><th>Places</th></tr>
  <c:forEach var="t" items="${tallers}">
    <tr>
      <td><c:out value="${t.nom}"/></td>
      <td>${t.inscrits} de ${t.places}</td>
      <td>
        <c:choose>
          <c:when test="${t.ple}"><span class="ple">Ple</span></c:when>
          <c:otherwise><span class="lliure">Lliures: ${t.placesLliures}</span></c:otherwise>
        </c:choose>
      </td>
    </tr>
  </c:forEach>
</table>

<%@ include file="parts/peu.jspf" %>
