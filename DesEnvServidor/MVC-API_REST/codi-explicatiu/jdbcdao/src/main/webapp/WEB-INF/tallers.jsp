<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ca">
<head>
    <meta charset="UTF-8">
    <title>Tallers</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 850px; margin: 35px auto; padding: 0 18px; }
        label { display: block; margin-top: 12px; }
        input { padding: 8px; width: 100%; max-width: 420px; box-sizing: border-box; }
        button { margin-top: 18px; padding: 10px 18px; cursor: pointer; }
        .errors { background: #ffe9e9; border: 1px solid #cc5555; padding: 12px 30px; color: #8b1717; }
        li { margin: 7px 0; }
    </style>
</head>
<body>

<h1>Llista de tallers</h1>
<c:choose>
    <c:when test="${empty tallers}">
        <p>Encara no hi ha tallers.</p>
    </c:when>
    <c:otherwise>
        <ol>
            <c:forEach items="${tallers}" var="taller">
                <li>
                    <strong><c:out value="${taller.nom}"/></strong>
                    (codi: <c:out value="${taller.id}"/>)
                    — Ponent: <c:out value="${taller.ponent}"/>
                    — Dia: <c:out value="${taller.diaText}"/>
                    — Places: <c:out value="${taller.places}"/>
                    — Preu: <c:out value="${taller.preu}"/> €
                </li>
            </c:forEach>
        </ol>
    </c:otherwise>
</c:choose>

<h1>Creació d'un taller</h1>

<c:if test="${not empty errors}">
    <div class="errors">
        <strong>Hi ha errors en el formulari:</strong>
        <ul>
            <c:forEach items="${errors}" var="error">
                <li><c:out value="${error}"/></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/tallers" method="POST">
    <label for="id">Identificador:</label>
    <input type="text" id="id" name="id" required maxlength="20"
           pattern="[a-z0-9-]+" title="Lletres minúscules, xifres o guions">

    <label for="nom">Nom del taller:</label>
    <input type="text" id="nom" name="nom" required maxlength="100">

    <label for="ponent">Ponent:</label>
    <input type="text" id="ponent" name="ponent" required maxlength="100">

    <label for="dia">Dia del taller:</label>
    <input type="date" id="dia" name="dia" required>

    <label for="places">Nombre de places:</label>
    <input type="number" id="places" name="places" min="1" max="500" step="1" required>

    <label for="preu">Preu (€):</label>
    <input type="number" id="preu" name="preu" min="0" max="999.99"
           step="0.01" required>

    <button type="submit">Crear taller</button>
    <button type="reset">Esborrar</button>
</form>

</body>
</html>
