<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cat.politecnicllevant.jdbcdao.model.Taller" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Llista de Tallers</h1>
<ol>
    <c:forEach items="${tallers}" var="taller">
        <li>${taller.id}, ${taller.nom}</li>
    </c:forEach>
</ol>

</body>
</html>
