<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Error ${pageContext.errorData.statusCode}"/>
<%@ include file="parts/capcalera.jspf" %>

<%-- Només mentre aprenem: a un servidor real, els detalls van al registre --%>
<pre><c:out value="${pageContext.errorData.throwable}"/></pre>

<%@ include file="parts/peu.jspf" %>
