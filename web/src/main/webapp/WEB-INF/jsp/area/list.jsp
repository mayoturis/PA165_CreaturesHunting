<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Areas">
<jsp:attribute name="body">
    <my:a href="/area/add" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add area
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Size</th>
            <th>Danger Level</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${areas}" var="area">
            <tr>
                <td><c:out value="${area.name}"/></td>
                <td><c:out value="${area.size}"/></td>
                <td><c:out value="${area.dangerLevel}"/></td>
                <td><c:out value="${area.description}"/></td>
                <td>
                    <my:a href="/area/details/${area.id}" class="btn btn-default">Show details</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/area/delete/${area.id}">
                        <button type="submit" class="btn btn-default">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>