<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Add area">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/area/create"
               modelAttribute="newArea" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control" required="required"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${size_error?'has-error':''}">
                    <form:label path="size" cssClass="col-sm-2 control-label">Size</form:label>
                    <div class="col-sm-10">
                        <input type="number" min="0" name="size" id="size" class="form-control" required="required"/>
                        <form:errors path="size" cssClass="help-block"/>
                    </div>
                </div>
        <div class="form-group ${description_error?'has-error':''}">
                    <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
                    <div class="col-sm-10">
                        <form:textarea path="description" cssClass="form-control" required="required"/>
                        <form:errors path="description" cssClass="help-block"/>
                    </div>
                </div>
        <div class="form-group">
            <form:label path="dangerLevel" cssClass="col-sm-2 control-label">Danger Level</form:label>
            <div class="col-sm-10">
                <form:select path="dangerLevel" class="form-control" id="dangerLevel">
                    <form:options items="${dangerLevel}"/>
                </form:select>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create Area</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>