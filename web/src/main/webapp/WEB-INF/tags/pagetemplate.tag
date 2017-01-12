<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><c:out value="${title}"/></title>
	<!-- bootstrap loaded from content delivery network -->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
	<jsp:invoke fragment="head"/>
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}"><f:message key="navigation.project"/></a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><f:message key="navigation.hunting"/></li>
				<li><my:a href="/monster/list"><f:message key="navigation.admin.monsters"/></my:a></li>
				<li><my:a href="/weapon/list"><f:message key="navigation.admin.weapons"/></my:a></li>
                <li><my:a href="/user/arsenal"><f:message key="navigation.admin.arsenal"/></my:a></li>

                <li><my:a href="/area/list"><f:message key="navigation.admin.areas"/></my:a></li>
				<c:if test="${authenticatedUser.isAdmin()}">
					<li><my:a href="/user/list"><f:message key="navigation.admin.users"/></my:a></li>
				</c:if>
				<li><my:a href="/user/logoff">Log off</my:a></li>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>

<div class="container" style="padding-bottom: 30px">

	<!-- page title -->
	<c:if test="${not empty title}">
		<div class="page-header">
			<h1><c:out value="${title}"/></h1>
		</div>
	</c:if>

	<!-- alerts -->
	<c:if test="${not empty alert_danger}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<c:out value="${alert_danger}"/></div>
	</c:if>
	<c:if test="${not empty alert_info}">
		<div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
	</c:if>
	<c:if test="${not empty alert_success}">
		<div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
	</c:if>
	<c:if test="${not empty alert_warning}">
		<div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
	</c:if>

	<!-- page body -->
	<jsp:invoke fragment="body"/>
</div>
<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
