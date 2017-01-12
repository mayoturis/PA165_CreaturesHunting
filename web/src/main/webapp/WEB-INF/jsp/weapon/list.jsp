<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Weapons">
    <jsp:attribute name="body">
    <c:if test="${authenticatedUser.isAdmin()}">
            <my:a href="/weapon/create" class="btn btn-primary">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                New Weapon
            </my:a>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Range</th>
            <th>Ammunition</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${weapons}" var="weapon">
            <tr>
                <td><c:out value="${weapon.name}"/></td>
                <td><c:out value="${weapon.range}"/></td>
                <td><c:out value="${weapon.ammunition}"/></td>
                <td>
                    <my:a href="/weapon/view/${weapon.id}" class="btn btn-primary">Detail</my:a>
                </td>
                <td>
                    <c:if test="${authenticatedUser.isAdmin()}">
                        <!--<my:a href="/weapon/update/${weapon.id}" class="btn btn-primary">Update</my:a>-->
                    </c:if>
                </td>
                <td>
                    <c:if test="${authenticatedUser.isAdmin()}">
                        <form method="post" action="${pageContext.request.contextPath}/weapon/delete/${weapon.id}">
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
