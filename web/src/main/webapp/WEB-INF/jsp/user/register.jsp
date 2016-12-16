<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:emptytemplate>
<jsp:attribute name="body">
	<div class="row">
        <div class="col-xs-3"></div>
        <div class="col-xs-6">
            <form:form method="post" action="${pageContext.request.contextPath}/user/doRegister"
                       modelAttribute="user" cssClass="form-horizontal">
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" name="email" id="email" required="required" class="form-control "/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" pattern=".{3,}" title="Min 3 characters" required="required" id="name" class="form-control "/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" pattern=".{6,}" title="Min 6 characters" name="password" required="required" id="password" class="form-control "/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="age" class="col-sm-2 control-label">Age</label>
                    <div class="col-sm-10">
                        <input type="number" name="age" id="age" required="required" class="form-control "/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="isAdmin" class="col-sm-2 control-label">Is admin</label>
                    <div class="col-sm-10">
                        <input type="checkbox" name="admin" id="isAdmin" class=""/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="gender" class="col-sm-2 control-label">Gender</label>
                    <div class="col-sm-10">
                        <select name="gender" id="gender" class="form-control">
                            <option value="MALE">Male</option>
                            <option value="FEMALE">Female</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-offset-2 col-xs-10">
                    <button class="btn btn-primary" type="submit">Register</button>
                </div>
            </form:form>
        </div>
        <div class="col-xs-3"></div>
    </div>
</jsp:attribute>
</my:emptytemplate>