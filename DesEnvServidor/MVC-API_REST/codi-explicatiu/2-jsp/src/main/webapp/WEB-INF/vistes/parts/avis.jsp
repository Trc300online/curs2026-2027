<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Una JSP completa, que s'executa a part: rep les dades amb jsp:param i les llegeix a ${param} --%>
<p class="avis"><c:out value="${param.text}"/></p>
