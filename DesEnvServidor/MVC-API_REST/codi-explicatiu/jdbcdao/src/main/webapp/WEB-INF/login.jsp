
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ca">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h1>Iniciar sessió</h1>

<c:if test="${not empty error}">
    <p style="color:red">
        <c:out value="${error}"/>
    </p>
</c:if>

<form action="${pageContext.request.contextPath}/login"
      method="post">

    <label for="nom">Usuari:</label>
    <input type="text" id="nom" name="nom"
           value="<c:out value='${nom}'/>" required>

    <br><br>

    <label for="contrasenya">Contrasenya:</label>
    <input type="password" id="contrasenya"
           name="contrasenya" required>

    <br><br>

    <button type="submit">Entrar</button>

</form>

</body>
</html>
