<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf"%>

<spring:message code="basicspring-greeting"/>&nbsp;<b><c:out value="${name}"/></b>!!!
<br/>
<a href="<portlet:renderURL />"><spring:message code="basicspring-back"/> </a>

