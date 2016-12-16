<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

	<h1>Welcome <c:out value="${user.getName()}"/> (<c:out value="${user.email}"/>)</h1>

	<c:if test="${empty user}">
		<div class="page-header">
			<h1>si prazdny</h1>
		</div>
	</c:if>
</jsp:attribute>
</my:pagetemplate>
