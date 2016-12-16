<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

	<div class="jumbotron">
		<h1>Welcome to Creature Hunting!</h1>
		<p class="lead">Here you can find out various information about monsters, including areas where they were seen and weapons useful against them.</p>
		<p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/monster/list"
			  role="button">Go hunting!</a></p>
	</div>

</jsp:attribute>
</my:pagetemplate>