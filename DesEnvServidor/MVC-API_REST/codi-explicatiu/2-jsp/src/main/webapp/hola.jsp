<%@ page contentType="text/html;charset=UTF-8" import="java.time.LocalTime" %>
<!DOCTYPE html>
<html lang="ca">
<head>
  <meta charset="utf-8">
  <title>Primera JSP</title>
  <link rel="stylesheet" href="estil.css">
</head>
<body>
  <h1>Hola, món!</h1>
  <p>Són les <strong><%= LocalTime.now().withNano(0) %></strong>.</p>

  <%-- Això és un comentari de JSP: no arriba mai al navegador (mirau el codi font amb Cmd+Opt+U).
       ── PROVA 1 ─ Llevau «< %--» i «--% >» de la línia següent i recarregau.
       La JSP es compila quan es demana: l'error surt al navegador, no a IntelliJ. --%>
  <%-- <p><%= LocalTime.now().getHora() %></p> --%>

  <p><a href="./">Tornar a l'inici</a></p>
</body>
</html>
