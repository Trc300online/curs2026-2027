
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ca">
<head>
    <meta charset="UTF-8">
    <title>Nou taller</title>
</head>
<body>

<h1>Creació d'un taller</h1>

<c:if test="${not empty errors}">
    <div style="color:red">
        <strong>Hi ha errors en el formulari:</strong>

        <ul>
            <c:forEach items="${errors}" var="error">
                <li><c:out value="${error}"/></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/gestio/nou-taller"
      method="post">

    <label for="id">Identificador:</label>
    <input type="text" id="id" name="id" required maxlength="20" pattern="[a-z0-9-]+">

    <br><br>

    <label for="nom">Nom del taller:</label>
    <input type="text" id="nom" name="nom" required maxlength="100">

    <br><br>

    <label for="ponent">Ponent:</label>
    <input type="text" id="ponent" name="ponent" required maxlength="100">

    <br><br>

    <label for="dia">Dia del taller:</label>
    <input type="date" id="dia" name="dia" required>

    <br><br>

    <label for="places">Nombre de places:</label>
    <input type="number" id="places" name="places" min="1" max="500" required>

    <br><br>

    <label for="preu">Preu (€):</label>
    <input type="number" id="preu" name="preu" min="0" max="999.99" step="0.01" required>

    <br><br>

    <button type="submit">Crear taller</button>

    <button type="reset">Esborrar</button>

</form>

<br>

<a href="${pageContext.request.contextPath}/tallers">Tornar a la llista</a>

</body>
</html>
