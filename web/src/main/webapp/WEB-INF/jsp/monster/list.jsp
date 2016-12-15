<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Monsters">
<jsp:attribute name="body">

    <table class="table">
        <caption>Monsters</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>type</th>
            <th>height</th>
            <th>weight</th>
            <th>agility</th>
            <th>strength</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${monsters}" var="monster">
            <tr>
                <td>${monster.id}</td>
                <td><c:out value="${monster.type}"/></td>
                <td><c:out value="${monster.height}"/></td>
                <td><c:out value="${monster.weight}"/></td>
                <td><c:out value="${monster.agility}"/></td>
                <td><c:out value="${monster.strength}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>