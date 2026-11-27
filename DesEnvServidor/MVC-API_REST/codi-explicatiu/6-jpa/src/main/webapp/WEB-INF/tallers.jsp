<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- La vista no fa servir la sessió (l'usuari li arriba com a atribut de la request). Sense això, la JSP en crearia
     una per a cada visitant, i el getSession(false) de TallersServlet no serviria de res --%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Tallers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estils.css">
</head>
<body>

<%-- Un div i no un p: un <form> no pot anar dins un paràgraf, i el navegador tancaria el <p> abans del Sortir --%>
<div>
    <c:choose>
        <c:when test="${empty usuari}">
            <a href="${pageContext.request.contextPath}/login">Entrar</a>
        </c:when>
        <c:otherwise>
            Hola, <c:out value="${usuari}"/> ·
            <a href="${pageContext.request.contextPath}/gestio/nou-taller">Nou taller</a>
            <%-- Sortir canvia l'estat: un formulari amb POST, no un enllaç --%>
            <form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline">
                <button>Sortir</button>
            </form>
        </c:otherwise>
    </c:choose>
</div>

<%-- AMPLIACIÓ: només hi és la primera vegada que es mostra la llista després de crear un taller --%>
<c:if test="${not empty missatge}">
    <p style="color: #067647"><c:out value="${missatge}"/></p>
</c:if>

<h1>Llista de tallers</h1>
<ul>
    <c:forEach items="${tallers}" var="taller">
        <%-- c:url hi posa davant el context de l'aplicació, i c:param codifica el valor per a la URL --%>
        <c:url var="fitxa" value="/taller">
            <c:param name="id" value="${taller.id}"/>
        </c:url>
        <%-- c:out escapa < > & " ': el nom el decideix qui crea el taller --%>
        <li><a href="${fitxa}"><c:out value="${taller.nom}"/></a> · <c:out value="${taller.ponent}"/> · ${taller.diaText}</li>
    </c:forEach>
</ul>
</body>
</html>
