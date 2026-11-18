<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Vaja: error ${pageContext.errorData.statusCode}"/>
<%@ include file="parts/capcalera.jspf" %>

<c:choose>
  <c:when test="${pageContext.errorData.statusCode == 404}">
    <p>No hem trobat <code><c:out value="${pageContext.errorData.requestURI}"/></code>.</p>
  </c:when>
  <c:otherwise>
    <p>Alguna cosa ha fallat al servidor. Ja ho mirarem.</p>
    <%-- Només mentre aprenem: a un servidor real, els detalls van al registre i mai a l'usuari --%>
    <pre><c:out value="${pageContext.errorData.throwable}"/></pre>
  </c:otherwise>
</c:choose>
<%-- El missatge de sendError(404, "...") --%>
<p class="apagat"><c:out value="${requestScope['jakarta.servlet.error.message']}"/></p>

<%@ include file="parts/peu.jspf" %>
