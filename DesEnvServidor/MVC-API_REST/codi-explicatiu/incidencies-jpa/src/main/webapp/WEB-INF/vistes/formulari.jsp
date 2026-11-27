<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Nova incidència"/>
<%@ include file="parts/capcalera.jspf" %>

<c:if test="${not empty errors}">
  <ul class="errors">
    <c:forEach var="error" items="${errors}">
      <li><c:out value="${error}"/></li>
    </c:forEach>
  </ul>
</c:if>

<form action="<c:url value='/incidencies/nova'/>" method="post" novalidate>
  <label for="aula">Aula</label>
  <input type="text" id="aula" name="aula" value="<c:out value='${incidencia.aula}'/>">

  <label for="descripcio">Descripció</label>
  <textarea id="descripcio" name="descripcio" rows="3"><c:out value="${incidencia.descripcio}"/></textarea>

  <label for="prioritat">Prioritat</label>
  <select id="prioritat" name="prioritat">
    <c:forEach var="p" items="${prioritats}">
      <option value="${p}" ${incidencia.prioritat == p ? 'selected' : ''}>${p}</option>
    </c:forEach>
  </select>

  <button>Crear la incidència</button>
</form>

<%@ include file="parts/peu.jspf" %>
