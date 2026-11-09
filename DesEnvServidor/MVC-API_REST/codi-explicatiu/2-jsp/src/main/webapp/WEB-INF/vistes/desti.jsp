<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titol" value="Destí"/>
<%@ include file="parts/capcalera.jspf" %>

<table>
  <tr><th>Atribut «missatge»</th><td>${empty missatge ? '(no hi és: aquesta és una altra petició)' : missatge}</td></tr>
  <tr><th>Mètode i URL de la petició</th><td>${pageContext.request.method} · ${requestScope['jakarta.servlet.forward.request_uri']}</td></tr>
</table>
<p>Mirau la barra d'adreces, i F12 › Xarxa: quantes peticions hi ha?</p>
<ul>
  <li><a href="<c:url value='/anar?com=forward'/>">Tornar a provar amb forward</a></li>
  <li><a href="<c:url value='/anar?com=redirect'/>">Tornar a provar amb redirect</a></li>
</ul>

<%@ include file="parts/peu.jspf" %>
