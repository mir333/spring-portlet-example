<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../init.jspf"%>

<%@ page import="static eu.ibacz.sample.portlet.bascispring.BasicSpringPortletConstants.*" %>

<spring:message code="basicspring-question"/>
<br/>
<portlet:actionURL var="actionUrl" name="<%=TEST_ACTION%>"/>
<form action="${actionUrl}" method="POST">
    <input type="text" name="${ns}<%=NAME_PARAM%>">

    <input type="submit" value="<spring:message code="basicspring-submit"/>" />
</form>
