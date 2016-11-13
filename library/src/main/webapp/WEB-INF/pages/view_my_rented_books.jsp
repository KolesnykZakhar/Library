<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.mail.zahar.kolesnik.library.models.Library" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.10.2016
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:forward page="/cabinet"></jsp:forward>--%>
<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css"
          media="screen, projection">
</head>
<body>
<h2>List of books rented by myself</h2>
<table>
    <tr>
        <th>#</th>
        <th>Book's name</th>
        <th>Category</th>
        <th>Author's names</th>
        <th>Author's surname</th>
        <th>Author's patronymic</th>
        <th>Date to return</th>
    </tr>
    <c:forEach items="${requestScope.foundedBooks}" var="book" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <td>${book.bookName}</td>
            <td>${book.category}</td>
            <td>${book.authorName}</td>
            <td>${book.authorSurname}</td>
            <td>${book.authorPatronymic}</td>
            <td>${book.returnedDate}</td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</body>
</html>
