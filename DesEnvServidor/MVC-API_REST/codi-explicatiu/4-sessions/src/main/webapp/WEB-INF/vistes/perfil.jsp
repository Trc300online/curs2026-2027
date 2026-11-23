<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ca">
<head>
  <meta charset="utf-8">
  <title>Zona privada</title>
  <link rel="stylesheet" href="<c:url value='/estil.css'/>">
</head>
<body>
  <h1>Hola, <c:out value="${usuari}"/>!</h1>

  <p>Aquesta pàgina només la veu qui ha entrat.</p>
  <p>L'id de la vostra sessió és <code>${id}</code>. Comparau-lo amb la cookie <code>JSESSIONID</code> a F12 &gt; Aplicació.</p>

  <%-- Sortir canvia l'estat: un formulari amb POST, no un enllaç --%>
  <form action="<c:url value='/logout'/>" method="post">
    <button>Sortir</button>
  </form>
</body>
</html>
