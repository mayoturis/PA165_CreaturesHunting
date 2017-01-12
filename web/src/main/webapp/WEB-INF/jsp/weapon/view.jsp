<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Weapon Administration">
<jsp:attribute name="body">

    <c:if test="${authenticatedUser.isAdmin()}">
        <form method="post" action="${pageContext.request.contextPath}/weapon/delete/${weapon.id}">
            <button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>range</th>
            <th>ammunition</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${weapon.id}</td>
            <td><c:out value="${weapon.name}"/></td>
            <td><c:out value="${weapon.range}"/></td>
            <td><c:out value="${weapon.ammunition}"/></td>
        </tr>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
