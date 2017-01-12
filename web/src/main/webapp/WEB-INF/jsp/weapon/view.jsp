<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${weapon.name} weapon detail">
<jsp:attribute name="body">

    <c:if test="${authenticatedUser.isAdmin()}">
        <form method="post" action="${pageContext.request.contextPath}/weapon/delete/${weapon.id}">
            <button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </c:if>
    <br>
    <table class="table" style="width: 50%">
        <tbody>
        <tr>
            <td>Name</td>
            <td><c:out value="${weapon.name}"/></td>
        </tr>
        <tr>
            <td>Range</td>
            <td><c:out value="${weapon.range}"/></td>
        </tr>
        <tr>
            <td>Ammunition</td>
            <td><c:out value="${weapon.ammunition}"/></td>
        </tr>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
