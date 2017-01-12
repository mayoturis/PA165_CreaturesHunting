<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Monsters">
<jsp:attribute name="body">
    <c:if test="${authenticatedUser.isAdmin()}">
        <my:a href="/monster/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New Monster
        </my:a>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>Type</th>
            <th>Height</th>
            <th>Weight</th>
            <th>Agility</th>
            <th>Strength</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${monsters}" var="monster">
            <tr>
                <td><c:out value="${monster.type}"/></td>
                <td><c:out value="${monster.height}"/></td>
                <td><c:out value="${monster.weight}"/></td>
                <td><c:out value="${monster.agility}"/></td>
                <td><c:out value="${monster.strength}"/></td>
                <td>
                    <my:a href="/monster/view/${monster.id}" class="btn btn-primary">Detail</my:a>
                </td>
                <td>
                    <c:if test="${authenticatedUser.isAdmin()}">
                        <form method="post" action="${pageContext.request.contextPath}/monster/delete/${monster.id}">
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