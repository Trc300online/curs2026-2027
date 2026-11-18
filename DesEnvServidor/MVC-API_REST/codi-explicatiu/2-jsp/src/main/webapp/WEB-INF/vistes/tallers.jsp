<%@ page contentType="text/html;charset=UTF-8" %>
<%-- ── PROVA 8 ─ Esborrau la línia següent i recarregau. No hi ha cap error: mirau el codi font de la pàgina.
     Sense el taglib, <c:forEach> és text normal i arriba tal qual al navegador --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="titol" value="Tallers"/>
<%@ include file="parts/capcalera.jspf" %>

<%-- El format de les xifres d'aquesta pàgina: 12,50 €
     ── PROVA 10 ─ Canviau ca_ES per en_US i mirau els preus --%>
<fmt:setLocale value="ca_ES"/>

<jsp:include page="parts/avis.jsp">
  <jsp:param name="text" value="Inscripció oberta fins divendres. Hi ha ${fn:length(tallers)} tallers."/>
</jsp:include>

<c:choose>
  <c:when test="${empty tallers}">
    <p>Encara no hi ha cap taller.</p>
  </c:when>
  <c:otherwise>
    <table>
      <tr><th>#</th><th>Taller</th><th>Ponent</th><th>Preu</th><th>Places</th></tr>
      <c:forEach var="t" items="${tallers}" varStatus="v">
        <tr>
          <td>${v.count}</td>
          <td>
            <c:url var="enllac" value="/taller">
              <c:param name="id" value="${t.id}"/>
            </c:url>
            <a href="${enllac}"><c:out value="${t.nom}"/></a>
          </td>
          <td><c:out value="${t.ponent}"/></td>
          <td>
            <c:choose>
              <c:when test="${t.preu == 0}">Gratuït</c:when>
              <c:otherwise><fmt:formatNumber value="${t.preu}" type="currency" currencyCode="EUR"/></c:otherwise>
            </c:choose>
          </td>
          <td>
            <c:if test="${t.ple}"><span class="ple">Ple</span></c:if>
            <c:if test="${not t.ple}"><span class="lliure">${t.placesLliures} lliures</span></c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<%@ include file="parts/peu.jspf" %>
