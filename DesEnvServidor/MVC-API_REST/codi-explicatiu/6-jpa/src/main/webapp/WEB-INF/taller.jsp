<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- La fitxa no fa servir la sessió: com a tallers.jsp, no en cream cap per a qui només mira --%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title><c:out value="${taller.nom}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estils.css">
</head>
<body>
<p><a href="${pageContext.request.contextPath}/tallers">← Tornar a la llista</a></p>

<h1><c:out value="${taller.nom}"/></h1>

<%-- Una llista de definicions: cada <dt> és un nom, i el <dd> que el segueix, el seu valor --%>
<dl>
    <dt>Codi</dt>
    <dd><c:out value="${taller.id}"/></dd>

    <dt>Ponent</dt>
    <dd><c:out value="${taller.ponent}"/></dd>

    <dt>Dia</dt>
    <dd>${taller.diaText}</dd>

    <dt>Places</dt>
    <dd>${taller.places}</dd>

    <dt>Preu</dt>
    <dd>
        <c:choose>
            <c:when test="${taller.gratuit}">Gratuït</c:when>
            <%-- fmt:formatNumber sí que sap formatar un BigDecimal: 12,50 € --%>
            <c:otherwise>
                <fmt:setLocale value="ca_ES"/>
                <fmt:formatNumber value="${taller.preu}" type="currency" currencyCode="EUR"/>
            </c:otherwise>
        </c:choose>
    </dd>
</dl>
</body>
</html>
