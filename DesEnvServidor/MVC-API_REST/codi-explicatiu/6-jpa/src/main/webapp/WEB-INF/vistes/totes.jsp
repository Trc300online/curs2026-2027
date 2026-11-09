<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Totes les inscripcions"/>
<%@ include file="parts/capcalera.jspf" %>

<table>
  <tr><th>#</th><th>Usuari</th><th>Taller</th></tr>
  <c:forEach var="i" items="${inscripcions}">
    <tr>
      <td>${i.id}</td>
      <td><c:out value="${i.usuari.nomComplet}"/></td>
      <td><c:out value="${i.taller.nom}"/></td>
    </tr>
  </c:forEach>
</table>

<%@ include file="parts/peu.jspf" %>
