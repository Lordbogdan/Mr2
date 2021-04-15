<%--
  Created by IntelliJ IDEA.
  User: b.marchenko
  Date: 03.04.2021
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/BogdanAdd">
<textarea inp name = "text" >
    ${Bogdan}
</textarea>
    <input type="hidden" name="id" value="${Object_id}">

    <select name="authorId">
        <option value="${Author.getId()}">
            ${Author.getAuthor()}
        </option>
        <c:forEach items="${authors}" var="author">
            <option value="${author.getId()}">
                    ${author.getAuthor()}
            </option>
        </c:forEach>
    </select>

    <button type="submit" name = "btn" value="${btn}">Save</button>
</form>

</body>
</html>
