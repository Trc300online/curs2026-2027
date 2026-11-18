<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Les meves inscripcions"/>
<%@ include file="parts/capcalera.jspf" %>

<c:choose>
  <c:when test="${empty inscripcions}">
    <p>Encara no teniu cap inscripció. Mirau els <a href="<c:url value='/tallers'/>">tallers</a>.</p>
  </c:when>
  <c:otherwise>
    <table>
      <tr><th>#</th><th>Taller</th><th></th></tr>
      <c:forEach var="i" items="${inscripcions}">
        <tr>
          <td>${i.id}</td>
          <td><c:out value="${i.taller.nom}"/></td>
          <td>
            <form action="<c:url value='/meves/anulla'/>" method="post">
              <input type="hidden" name="id" value="${i.id}">
              <button>Anul·lar</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<%@ include file="parts/peu.jspf" %>
