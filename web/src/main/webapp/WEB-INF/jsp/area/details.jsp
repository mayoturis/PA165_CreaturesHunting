<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${area.name} area details">
<jsp:attribute name="body">
    <div style="width:400px;">
        <div style="float: left; width: 130px">
            <my:a href="/area/list">Back to areas</my:a>
        </div>
        <br>
        <br>
        <c:if test="${authenticatedUser.isAdmin()}">
            <div style="width: 225px">
                <form method="post" action="${pageContext.request.contextPath}/area/delete/${area.id}">
                    <button type="submit" class="btn btn-primary">Delete this area</button>
                </form>
            </div>
        </c:if>
    </div>
    <br>
    <table class="table" style="width: 50%">
        <tbody>
            <tr>
                <td>Name</td>
                <td><c:out value="${area.name}"/></td>
            </tr>
            <tr>
                <td>Size</td>
                <td><c:out value="${area.size}"/></td>
            </tr>
            <tr>
                <td>Danger&nbsp;level</td>
                <td><c:out value="${area.dangerLevel}"/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><c:out value="${area.description}"/></td>
            </tr>
        </tbody>
    </table>
<br/>
    <h2>Spotted monsters in this area:</h2>

    <form method="post" action="${pageContext.request.contextPath}/area/addMonster/${area.id}" modelAttribute="monsterId">
        <label for="monsterId" cssClass="col-sm-2 control-label">Add monster to this area:</label>
            <select name="monsterId" id="monsterId" class="form-control" style="width: 200px; display: inline-block">
                <c:forEach items="${allMonsters}" var="monster">
                    <option value="${monster.id}"><c:out value="${monster.type}"/></option>
                </c:forEach>
            </select>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>

    <table class="table">
        <thead>
            <tr>
                <th>Id</th>
                <th>Type</th>
                <th>Height</th>
                <th>Weight</th>
                <th>Agility</th>
                <th>Strength</th>
                <th></th>
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
                    <td>
                        <c:if test="${authenticatedUser.isAdmin()}">
                            <form method="post" action="${pageContext.request.contextPath}/area/removeMonsterFromArea">
                                <input type="hidden" name="monsterId" value="${monster.id}">
                                <input type="hidden" name="areaId" value="${area.id}">
                                <button type="submit" class="btn btn-primary">Remove monster from this area</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
