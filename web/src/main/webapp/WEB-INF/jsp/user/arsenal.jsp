<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Weapons">
    <jsp:attribute name="body">
        <my:a href="/user/addWeapon" class="btn btn-primary">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                Add Weapon
        </my:a>
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
        <c:forEach items="${weapons}" var="weapon">
            <tr>
                <td>${weapon.id}</td>
                <td><c:out value="${weapon.name}"/></td>
                <td><c:out value="${weapon.range}"/></td>
                <td><c:out value="${weapon.ammunition}"/></td>
                <td>
                    <my:a href="/weapon/view/${weapon.id}" class="btn btn-primary">View</my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
