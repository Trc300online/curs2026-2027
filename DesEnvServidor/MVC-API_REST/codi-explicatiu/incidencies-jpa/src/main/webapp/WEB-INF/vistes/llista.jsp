<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Incidències"/>
<%@ include file="parts/capcalera.jspf" %>

<table>
  <tr><th>#</th><th>Aula</th><th>Descripció</th><th>Prioritat</th><th>Estat</th></tr>

  <c:forEach var="i" items="${incidencies}">
    <tr>
      <td>${i.id}</td>
      <%-- L'aula i la descripció les escriu l'usuari: sempre amb c:out --%>
      <td><c:out value="${i.aula}"/></td>
      <td><c:out value="${i.descripcio}"/></td>
      <td>${i.prioritat}</td>
      <td>
        <c:choose>
          <c:when test="${i.resolta}"><span class="resolta">Resolta</span></c:when>
          <c:otherwise>
            <%-- Un botó dins un formulari, perquè sigui un POST --%>
            <form action="<c:url value='/incidencies/resol'/>" method="post">
              <input type="hidden" name="id" value="${i.id}">
              <span class="pendent">Pendent</span>
              <button>Resoldre</button>
            </form>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </c:forEach>

</table>

<%@ include file="parts/peu.jspf" %>
