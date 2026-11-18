<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Inscripció"/>
<%@ include file="parts/capcalera.jspf" %>

<c:if test="${not empty errors}">
  <ul class="errors">
    <c:forEach var="error" items="${errors}">
      <li><c:out value="${error}"/></li>
    </c:forEach>
  </ul>
</c:if>

<form action="<c:url value='/inscripcio'/>" method="post" novalidate>
  <label for="nom">Nom</label>
  <input type="text" id="nom" name="nom" value="<c:out value='${nom}'/>">

  <label for="correu">Correu</label>
  <input type="email" id="correu" name="correu" value="<c:out value='${correu}'/>">

  <fieldset>
    <legend>Taller</legend>
    <c:forEach var="t" items="${tallers}">
      <p style="margin:4px 0">
        <input type="radio" id="t-${t.id}" name="taller" value="${t.id}"
               ${t.ple ? 'disabled' : (taller.id == t.id ? 'checked' : '')}>
        <label for="t-${t.id}"><c:out value="${t.nom}"/><c:if test="${t.ple}"> (ple)</c:if></label>
      </p>
    </c:forEach>
  </fieldset>

  <fieldset>
    <legend>Torns</legend>
    <c:forEach var="torn" items="${['matí', 'horabaixa']}">
      <input type="checkbox" id="${torn}" name="torns" value="${torn}"
             ${tornsTriats.contains(torn) ? 'checked' : ''}>
      <label for="${torn}">${torn}</label>
    </c:forEach>
  </fieldset>

  <button>Inscriure'm</button>
</form>

<%@ include file="parts/peu.jspf" %>
