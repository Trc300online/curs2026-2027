<%@ page import="java.util.List" %>
<%@ page import="cat.politecnicllevant.primer.Inscripcio" %><%--
  Created by IntelliJ IDEA.
  User: alumati
  Date: 9/11/26
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% List<Inscripcio> inscripcions = (List<Inscripcio>) request.getAttribute("inscripcions"); %>

<table>
    <tr>
        <th>NOM</th>
        <th>EDAT</th>
        <th>ESTUDIS</th>
    </tr>

<% for (Inscripcio inscripcio : inscripcions) {%>
<tr>
    <td> <%= inscripcio.getNom() %></td>
    <td> <%= inscripcio.getEdat() %></td>
    <td> <%= inscripcio.getEstudis() %></td>

</tr>
<% } %>

</table>

<a href="formulari.html">Tornar al formulari</a>

    </body>
</html>
