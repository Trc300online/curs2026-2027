<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Entrar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estils.css">
</head>
<body>
<p><a href="${pageContext.request.contextPath}/tallers">← Tornar a la llista</a></p>

<h1>Entrar</h1>

<c:if test="${not empty error}">
    <p style="color: #b42318"><c:out value="${error}"/></p>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <p>
        <label for="nom">Usuari</label>
        <input type="text" id="nom" name="nom" value="<c:out value='${nom}'/>">
    </p>
    <p>
        <label for="contrasenya">Contrasenya</label>
        <%-- La contrasenya no es torna mai a la pàgina --%>
        <input type="password" id="contrasenya" name="contrasenya">
    </p>
    <button>Entrar</button>
</form>

<p>Usuaris de prova: anna / anna123 i marc / marc123.</p>
</body>
</html>
