<%--
  Created by IntelliJ IDEA.
  User: b.marchenko
  Date: 03.04.2021
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Title</title>
</head>
<body>

<form method="post">

<select name = "id-author">
    <c:forEach var="author" items="${listAuthor}">
        <option value="${author.getId()}">${author.getAuthor()}</option>
    </c:forEach>
</select>

<button type="submit" value="edit" name = "btn">Edit</button>
<button type="submit"  value="delete" name = "btn">Delete</button>
    <button type="submit" value="add" name = "btn">add</button>

</form>

<form method="post">

    <select name = "id-quotes">
        <c:forEach var="quotes" items="${listQuotes}">
            <option value="${quotes.getId()}">${quotes.getQuote()}</option>
        </c:forEach>
    </select>

    <button type="submit" value="edit" name = "btn">Edit</button>
    <button type="submit"  value="delete" name = "btn">Delete</button>
    <button type="submit" value="add" name = "btn">add</button>

</form>
</body>
</html>
