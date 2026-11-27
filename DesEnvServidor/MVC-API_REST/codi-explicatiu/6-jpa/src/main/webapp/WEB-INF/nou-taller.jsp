<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Nou taller</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estils.css">
</head>
<body>
<p><a href="${pageContext.request.contextPath}/tallers">← Tornar a la llista</a></p>

<h1>Nou taller</h1>

<%-- Només hi ha errors si el Servlet ha fet forward després d'un POST incorrecte --%>
<c:if test="${not empty errors}">
    <ul style="color: #b42318">
        <c:forEach items="${errors}" var="error">
            <li><c:out value="${error}"/></li>
        </c:forEach>
    </ul>
</c:if>

<%-- El POST va a la mateixa URL, sota /gestio/: també passa pel filtre --%>
<form action="${pageContext.request.contextPath}/gestio/nou-taller" method="post" novalidate>
    <p>
        <label for="id">Codi</label>
        <input type="text" id="id" name="id" value="<c:out value='${nouTaller.id}'/>" placeholder="docker">
    </p>
    <p>
        <label for="nom">Nom</label>
        <input type="text" id="nom" name="nom" value="<c:out value='${nouTaller.nom}'/>">
    </p>
    <p>
        <label for="ponent">Ponent</label>
        <input type="text" id="ponent" name="ponent" value="<c:out value='${nouTaller.ponent}'/>">
    </p>
    <p>
        <%-- Un LocalDate escrit tal qual és 2026-11-24: just el format que vol un <input type="date"> --%>
        <label for="dia">Dia</label>
        <input type="date" id="dia" name="dia" value="${nouTaller.dia}">
    </p>
    <p>
        <label for="places">Places</label>
        <input type="number" id="places" name="places" min="1" max="500" step="1" value="${nouTaller.places}">
    </p>
    <p>
        <label for="preu">Preu (€)</label>
        <input type="text" id="preu" name="preu" inputmode="decimal" value="${nouTaller.preu}" placeholder="12,50">
    </p>
    <button>Crear</button>
</form>
</body>
</html>
