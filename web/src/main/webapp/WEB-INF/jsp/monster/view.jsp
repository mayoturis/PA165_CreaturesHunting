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

</jsp:attribute>
</my:pagetemplate>
