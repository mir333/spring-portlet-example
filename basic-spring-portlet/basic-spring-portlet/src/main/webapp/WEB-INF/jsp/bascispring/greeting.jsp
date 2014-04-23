<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--@elvariable id="personPro" type="eu.ibacz.sample.portlet.bascispring.pto.PersonPto"--%>
<%@include file="../init.jspf"%>

<spring:message code="basicspring-greeting"/>&nbsp;<b><c:out value="${personPro.name}"/></b>!!!
<br/>
<c:choose>
    <c:when test="${daysToBirthday eq 0}">
        <h1><spring:message code="basicspring-bithday" /></h1>
    </c:when>
    <c:otherwise>
        <spring:message code="basicspring-bithday-in" arguments="${daysToBirthday}"/>
    </c:otherwise>
</c:choose>
<br/>
<a href="<portlet:renderURL />"><spring:message code="basicspring-back"/> </a>

