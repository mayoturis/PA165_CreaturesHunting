<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Areas">
<jsp:attribute name="body">
    <c:if test="${authenticatedUser.isAdmin()}">
        <my:a href="/area/add" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add area
        </my:a>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Size</th>
            <th>Danger&nbsp;Level</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${areas}" var="area">
            <tr>
                <td><c:out value="${area.name}"/></td>
                <td><c:out value="${area.size}"/></td>
                <td><c:out value="${area.dangerLevel}"/></td>
                <td>
                    <my:a href="/area/details/${area.id}" class="btn btn-primary">Show details</my:a>
                </td>
                <td>
                    <c:if test="${authenticatedUser.isAdmin()}">
                        <form method="post" action="${pageContext.request.contextPath}/area/delete/${area.id}">
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