<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Weapons in your arsenal">
    <jsp:attribute name="body">
        <table class="table">
            <c:forEach items="${weapons}" var="weapon">
                <tr>
                    <td><c:out value="${weapon.name}"/></td>
                    <td>
                        <my:a href="/weapon/view/${weapon.id}" class="btn btn-primary">Detail</my:a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/user/removeFromArsenal/${weapon.id}">
                            <button type="submit" class="btn btn-primary">Remove from arsenal</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form method="post" action="${pageContext.request.contextPath}/user/addWeapon" modelAttribute="weaponId">
            <label for="weaponId" cssClass="col-sm-2 control-label">Add new weapon to arsenal:</label>
            <select name="weaponId" id="weaponId" class="form-control" style="width: 200px; display: inline-block">
                <c:forEach items="${allWeapons}" var="weapon">
                    <option value="${weapon.id}"><c:out value="${weapon.name}"/></option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </jsp:attribute>
</my:pagetemplate>
