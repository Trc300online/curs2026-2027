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


<h1>Creació d'un taller</h1>

<form action="tallers" method="POST">

    <div class="form-group">
        <label for="id">Identificador:</label>
        <input type="text"
               id="id"
               name="id"
               required>
    </div>

    <div class="form-group">
        <label for="nom">Nom del taller:</label>
        <input type="text"
               id="nom"
               name="nom"
               required>
    </div>

    <div class="form-group">
        <label for="ponent">Ponent:</label>
        <input type="text"
               id="ponent"
               name="ponent"
               required>
    </div>

    <div class="form-group">
        <label for="dia">Dia del taller:</label>
        <input type="date"
               id="dia"
               name="dia"
               required>
    </div>

    <div class="form-group">
        <label for="places">Nombre de places:</label>
        <input type="number"
               id="places"
               name="places"
               min="1"
               step="1"
               required>
    </div>

    <div class="form-group">
        <label for="preu">Preu (€):</label>
        <input type="number"
               id="preu"
               name="preu"
               min="0"
               step="0.01"
               required>
    </div>

    <div class="buttons">
        <button type="submit" class="submit">
            Crear taller
        </button>

        <button type="reset" class="reset">
            Esborrar
        </button>
    </div>

</form>

</body>
</html>
