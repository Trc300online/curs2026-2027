<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Inscripcions"/>
<%@ include file="parts/capcalera.jspf" %>

<c:choose>
  <c:when test="${empty inscripcions}">
    <p>Encara no hi ha cap inscripció.</p>
  </c:when>
  <c:otherwise>
    <table>
      <tr><th>#</th><th>Nom</th><th>Correu</th><th>Taller</th><th></th></tr>
      <c:forEach var="i" items="${inscripcions}">
        <tr>
          <td>${i.id}</td>
          <td><c:out value="${i.nom}"/></td>
          <td><c:out value="${i.correu}"/></td>
          <td><c:out value="${i.taller.nom}"/></td>
          <td>
            <%-- Un botó dins un formulari, perquè sigui un POST --%>
            <form action="<c:url value='/anulla'/>" method="post" style="margin:0">
              <input type="hidden" name="id" value="${i.id}">
              <button style="margin:0; padding:2px 10px; font-size:14px">Anul·lar</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<%@ include file="parts/peu.jspf" %>
