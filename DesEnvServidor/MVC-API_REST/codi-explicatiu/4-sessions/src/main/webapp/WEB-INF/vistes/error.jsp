<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Error ${pageContext.errorData.statusCode}"/>
<%@ include file="parts/capcalera.jspf" %>

<p><c:out value="${requestScope['jakarta.servlet.error.message']}"/></p>
<%-- Només mentre aprenem: a un servidor real, els detalls van al registre i mai a l'usuari --%>
<c:if test="${pageContext.errorData.statusCode == 500}">
  <pre><c:out value="${pageContext.errorData.throwable}"/></pre>
</c:if>

<%@ include file="parts/peu.jspf" %>
