<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New Monster">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/monster/create"
               modelAttribute="monsterCreate" cssClass="form-horizontal">

        <div class="form-group">
            <label for="type" class="col-sm-2 control-label">Type</label>
            <div class="col-sm-10">
                <input type="text" name="type" required="required" id="type" class="form-control "/>
            </div>
        </div>
        <div class="form-group">
            <label for="height" class="col-sm-2 control-label">Height</label>
            <div class="col-sm-10">
                <input type="number" min="0" name="height" id="height" class="form-control "/>
            </div>
        </div>
        <div class="form-group">
            <label for="weight" class="col-sm-2 control-label">Weight</label>
            <div class="col-sm-10">
                <input type="number" min="0" name="weight" id="weight" class="form-control "/>
            </div>
        </div>
        <div class="form-group">
            <label for="agility" class="col-sm-2 control-label">Agility</label>
            <div class="col-sm-10">
                <input type="number" min="1" max="10" required="required" name="agility" id="agility" class="form-control "/>
            </div>
        </div>
        <div class="form-group">
            <label for="strength" class="col-sm-2 control-label">Strength</label>
            <div class="col-sm-10">
                <input type="number" min="1" max="10" required="required"  name="strength" id="strength" class="form-control "/>
            </div>
        </div>

        <button class="btn btn-default" type="submit">Create Monster</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>