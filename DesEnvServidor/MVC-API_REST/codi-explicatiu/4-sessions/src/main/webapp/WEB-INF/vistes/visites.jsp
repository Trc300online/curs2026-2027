<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Visites"/>
<%@ include file="parts/capcalera.jspf" %>

<p>Heu visitat aquesta pàgina <strong>${sessionScope.visites}</strong> vegades.</p>
<table>
  <tr><th>L'id de la vostra sessió</th><td><code>${pageContext.session.id}</code></td></tr>
  <tr><th>És nova, en aquesta petició?</th><td>${pageContext.session.isNew() ? 'Sí' : 'No'}</td></tr>
  <tr><th>Caduca després de</th><td>${pageContext.session.maxInactiveInterval} segons sense peticions</td></tr>
</table>
<p class="apagat">Premeu F5, i obriu la mateixa pàgina en una finestra d'incògnit. Mirau la cookie <code>JSESSIONID</code> a F12 &gt; Aplicació.</p>

<%@ include file="parts/peu.jspf" %>
