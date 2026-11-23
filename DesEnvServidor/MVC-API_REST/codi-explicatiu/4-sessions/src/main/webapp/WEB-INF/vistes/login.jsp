<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ page session="false" %>--%>
<!DOCTYPE html>
<html lang="ca">
<head>
  <meta charset="utf-8">
  <title>Entrar</title>
  <link rel="stylesheet" href="<c:url value='/estil.css'/>">
</head>
<body>
  <h1>Entrar</h1>

  <c:if test="${not empty error}">
    <p class="errors"><c:out value="${error}"/></p>
  </c:if>

  <form action="<c:url value='/login'/>" method="post">
    <label for="nom">Usuari</label>
    <input type="text" id="nom" name="nom" value="<c:out value='${nom}'/>">

    <label for="contrasenya">Contrasenya</label>
    <%-- La contrasenya no es torna mai a la pàgina --%>
    <input type="password" id="contrasenya" name="contrasenya">

    <button>Entrar</button>
  </form>

  <p class="apagat">Usuaris de prova: <code>anna</code> / <code>anna123</code> i <code>marc</code> / <code>marc123</code>.</p>
  <p class="apagat">La pàgina privada és <a href="<c:url value='/privat/perfil'/>">/privat/perfil</a>: provau d'obrir-la sense entrar.</p>
</body>
</html>
