<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Update User">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/user/update/${user.id}"
               modelAttribute="user" cssClass="form-horizontal">

        <%--<div class="form-group">--%>
        <%--<label for="email" class="col-sm-2 control-label">Email</label>--%>
        <%--<div class="col-sm-10">--%>
        <%--<input type="email" name="email" id="email" required="required" class="form-control "/>--%>
        <%--</div>--%>
        <%--</div>--%>

        <form:hidden path="email"/>
                <form:hidden path="password"/>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" value="${user.name}" name="name" pattern=".{3,}" title="Min 3 characters"
                               required="required" id="name" class="form-control "/>
                    </div>
                </div>


        <%--<div class="form-group">--%>
        <%--<label for="password" class="col-sm-2 control-label">Password</label>--%>
        <%--<div class="col-sm-10">--%>
        <%--<input type="password" pattern=".{6,}" title="Min 6 characters" name="password" required="required" id="password" class="form-control "/>--%>
        <%--</div>--%>
        <%--</div>--%>

        <div class="form-group">
            <label for="age" class="col-sm-2 control-label">Age</label>
            <div class="col-sm-10">
                <input type="number" value="${user.age}" name="age" id="age" required="required" class="form-control "/>
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


        <button class="btn btn-primary" type="submit">Update User</button>
        <a href="${pageContext.request.contextPath}/user/list" class="btn btn-primary " role="button">
            Back to list
        </a>

    </form:form>

</jsp:attribute>
</my:pagetemplate>