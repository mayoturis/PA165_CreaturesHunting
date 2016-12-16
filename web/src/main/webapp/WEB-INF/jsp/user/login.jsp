<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:emptytemplate>
<jsp:attribute name="body">
	<div class="row">
        <div class="col-xs-3"></div>
        <div class="col-xs-6">
            <form:form method="post" action="${pageContext.request.contextPath}/user/doLogin"
                       modelAttribute="user" cssClass="form-horizontal">
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" name="email" id="email" required="required" class="form-control "/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" pattern=".{6,}" title="Min 6 characters" name="password" required="required" id="password" class="form-control "/>
                    </div>
                </div>
                <div class="col-xs-offset-2 col-xs-10">
                    <button class="btn btn-primary" type="submit">Login</button>
                </div>
            </form:form>
            <my:a href="/user/register">Register</my:a>
        </div>
        <div class="col-xs-3"></div>
    </div>
</jsp:attribute>
</my:emptytemplate>