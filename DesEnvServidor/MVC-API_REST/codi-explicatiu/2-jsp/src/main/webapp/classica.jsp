<%@ page contentType="text/html;charset=UTF-8" %>
<%-- Totes les etiquetes «clàssiques» de JSP, per reconèixer-les quan les trobeu.
     Funcionen, però posen codi Java dins la vista: a partir del bloc 2 no les usarem. --%>
<%!
    // Declaració: es converteix en un CAMP del Servlet generat, compartit per tots els usuaris
    private int visites = 0;
%>
<%
    // Scriptlet: es copia dins el mètode que atén la petició, _jspService()
    visites++;
    String text = request.getParameter("n");                // request, un objecte implícit
    int n = (text == null) ? 3 : Integer.parseInt(text);

    // ── PROVA 2 ─ Descomentau la línia següent: ara és una variable local, i cada visita torna a començar.
    // int visites = 1;
%>
<!DOCTYPE html>
<html lang="ca">
<head>
  <meta charset="utf-8">
  <title>JSP clàssica</title>
  <link rel="stylesheet" href="estil.css">
</head>
<body>
  <h1>Els quadrats fins a <%= n %></h1>
  <table>
    <tr><th>n</th><th>n²</th></tr>
    <% for (int i = 1; i <= n; i++) { %>
      <tr><td><%= i %></td><td><%= i * i %></td></tr>
    <% } %>
  </table>
  <p>Visites a aquesta pàgina: <strong><%= visites %></strong> (obriu-la també des d'un altre navegador).</p>
  <p>Mètode: <%= request.getMethod() %> · Aplicació: <%= application.getContextPath() %></p>
  <p><a href="./">Tornar a l'inici</a></p>
</body>
</html>
