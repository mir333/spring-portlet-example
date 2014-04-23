<%@ page import="eu.ibacz.sample.portlet.bascispring.BasicSpringPortletConstants" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="ns"><portlet:namespace/></c:set>

<spring:message code="basicspring-question"/>
<br/>
<portlet:actionURL var="actionUrl" name="<%=BasicSpringPortletConstants.TEST_ACTION%>"/>
<form action="${actionUrl}" method="POST">
    <input type="text" name="${ns}<%=BasicSpringPortletConstants.NAME_PARAM%>">
    <input type="submit" value="<spring:message code="basicspring-submit"/>" />
</form>
