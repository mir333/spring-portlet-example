<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--@elvariable id="personPro" type="eu.ibacz.sample.portlet.bascispring.pto.PersonPto"--%>
<%@include file="../init.jspf"%>

<spring:message code="basicspring-greeting"/>&nbsp;<b><c:out value="${personPro.name}"/></b>!!!
<br/>
<spring:message code="basicspring-bithday-in" arguments="${daysToBirthday}"/>
<br/>
<a href="<portlet:renderURL />"><spring:message code="basicspring-back"/> </a>

