
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ca">
<head>
    <meta charset="UTF-8">
    <title>Llista de tallers</title>
</head>
<body>

<h1>Llista de tallers</h1>

<!-- Opcions segons si l'usuari ha iniciat sessió -->

<c:choose>
    <c:when test="${empty usuari}">
        <a href="${pageContext.request.contextPath}/login">
            Entrar
        </a>
    </c:when>
    <c:otherwise>
        <p>Hola, <strong><c:out value="${usuari}"/></strong></p>
        <a href="${pageContext.request.contextPath}/gestio/nou-taller">
            Nou taller
        </a>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Sortir</button>
        </form>
    </c:otherwise>
</c:choose>
<hr>

<!-- Llista de tallers -->

<c:choose>
    <c:when test="${empty tallers}">
        <p>Encara no hi ha tallers.</p>
    </c:when>
    <c:otherwise>
        <ol>
            <c:forEach items="${tallers}" var="taller">
                <li>
                    <strong>
                        <c:out value="${taller.nom}"/>
                    </strong>
                    (codi:
                    <c:out value="${taller.id}"/>)
                    — Ponent:
                    <c:out value="${taller.ponent}"/>
                    — Dia:
                    <c:out value="${taller.diaText}"/>
                    — Places:
                    <c:out value="${taller.places}"/>
                    — Preu:
                    <c:out value="${taller.preu}"/> €
                </li>
            </c:forEach>
        </ol>
    </c:otherwise>
</c:choose>

</body>
</html>
