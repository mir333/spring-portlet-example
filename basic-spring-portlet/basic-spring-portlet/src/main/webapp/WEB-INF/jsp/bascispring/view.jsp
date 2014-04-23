<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--@elvariable id="personPro" type="eu.ibacz.sample.portlet.bascispring.pto.PersonPto"--%>
<%@include file="../init.jspf"%>

<%@ page import="static eu.ibacz.sample.portlet.bascispring.BasicSpringPortletConstants.*" %>

<spring:message code="basicspring-question"/>
<br/><br/>
<portlet:actionURL var="actionUrl" name="<%=TEST_ACTION%>"/>
<form:form action="${actionUrl}" method="POST" modelAttribute="<%=PERSON_PTO%>">
    <p>
        <form:label path="name" for="${ns}name"><spring:message code="basicspring-form-name"/></form:label>
        <form:input path="name" id="${ns}name"/>
    </p>
    <p>
        <form:label path="dateOfBirth" for="${ns}dateOfBirth"><spring:message code="basicspring-form-date-of-birth"/></form:label>
        <form:input path="dateOfBirth" id="${ns}dateOfBirth"/>
    </p>

    <input type="submit" value="<spring:message code="basicspring-submit"/>" />
</form:form>
