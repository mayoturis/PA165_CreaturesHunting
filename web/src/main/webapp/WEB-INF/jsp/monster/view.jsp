<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Monster View">
<jsp:attribute name="body">
    <c:if test="${authenticatedUser.isAdmin()}">
        <form method="post" action="${pageContext.request.contextPath}/monster/delete/${monster.id}">
            <button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Type</th>
            <th>Height</th>
            <th>Weight</th>
            <th>Agility</th>
            <th>Strength</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${monster.id}</td>
            <td><c:out value="${monster.type}"/></td>
            <td><c:out value="${monster.height}"/></td>
            <td><c:out value="${monster.weight}"/></td>
            <td><c:out value="${monster.agility}"/></td>
            <td><c:out value="${monster.strength}"/></td>
        </tr>
        </tbody>
    </table>

    <h2>Efficient weapons which can be used against this monster</h2>
    <table class="table">
        <c:forEach items="${weapons}" var="weapon">
            <tr>
                <td><c:out value="${weapon.name}"/></td>
                <td><my:a href="/weapon/view/${weapon.id}" class="btn btn-primary">Detail</my:a></td>
                <td>
                    <c:if test="${authenticatedUser.isAdmin()}">
                        <form method="post" action="${pageContext.request.contextPath}/monster/removeWeaponFromMonster">
                            <button type="submit" class="btn btn-primary">Remove</button>
                            <input type="hidden" name="monsterId" value="${monster.id}">
                            <input type="hidden" name="weaponId" value="${weapon.id}">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form method="post" action="${pageContext.request.contextPath}/monster/addWeapon" modelAttribute="weaponId">
        <input type="hidden" name="monsterId" value="${monster.id}">
        <label for="weaponId" cssClass="col-sm-2 control-label">Add weapon:</label>
        <select name="weaponId" id="weaponId" class="form-control" style="width: 200px; display: inline-block">
            <c:forEach items="${allWeapons}" var="weapon">
                <option value="${weapon.id}"><c:out value="${weapon.name}"/></option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>


</jsp:attribute>
</my:pagetemplate>
