<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<spring:message code="basicspring-greeting"/>&nbsp;<b><c:out value="${name}"/></b>!!!
<br/>
<a href="<portlet:renderURL />"><spring:message code="basicspring-back"/> </a>

