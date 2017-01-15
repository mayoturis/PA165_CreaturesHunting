<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New Weapon">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/weapon/create"
               modelAttribute="WeaponCreate" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control" required="required"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${range_error?'has-error':''}">
            <form:label path="range" cssClass="col-sm-2 control-label">Range</form:label>
            <div class="col-sm-10">
                <form:input path="range" cssClass="form-control" type="number" min="0" required="required"/>
                <form:errors path="range" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="ammunition" cssClass="col-sm-2 control-label">Ammunition</form:label>
            <div class="col-sm-10">
                <form:select path="ammunition" class="form-control" id="ammunition">
                    <form:options items="${ammunitions}"/>
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2 control-label"></div>
            <div class="col-sm-10">
                <button class="btn btn-primary" type="submit">Create Weapon</button>
            </div>
        </div>
    </form:form>

</jsp:attribute>
</my:pagetemplate>