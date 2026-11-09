<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Entrar"/>
<%@ include file="parts/capcalera.jspf" %>

<c:if test="${not empty error}">
  <p class="errors"><c:out value="${error}"/></p>
</c:if>

<form action="<c:url value='/login'/>" method="post">
  <label for="nom">Usuari</label>
  <input type="text" id="nom" name="nom" value="<c:out value='${nom}'/>">

  <label for="contrasenya">Contrasenya</label>
  <%-- La contrasenya no es torna mai a la pàgina --%>
  <input type="password" id="contrasenya" name="contrasenya">

  <button>Entrar</button>
</form>

<p class="apagat">Usuaris de prova: <code>anna</code> / <code>anna123</code>, <code>marc</code> / <code>marc123</code>, <code>admin</code> / <code>admin123</code>.</p>

<%@ include file="parts/peu.jspf" %>
