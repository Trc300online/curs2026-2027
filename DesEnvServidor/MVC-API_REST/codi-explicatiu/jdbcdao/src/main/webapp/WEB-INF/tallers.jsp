<%@ page import="cat.politecnicllevant.jdbcdao.model.Taller" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Llista de Tallers</h1>
<%List<Taller> tallersList = (List<Taller>) request.getAttribute("tallers");%>
<ol>
    <%for (Taller taller : tallersList) {%>

    <li><%= taller.getId() + ", " + taller.getNom() + ", " + taller.getPonent()%></li>

    <% } %>
</ol>

</body>
</html>
