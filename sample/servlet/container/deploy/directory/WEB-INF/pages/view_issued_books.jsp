<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css"
          media="screen, projection">
</head>
<body>
<h2>List of issued books</h2>
<table>
    <tr>
        <th>#</th>
        <th>Book's name</th>
        <th>Category</th>
        <th>Author's name</th>
        <th>Author's surname</th>
        <th>Author's patronymic</th>
        <th>Debtor's name</th>
        <th>Debtor's surname</th>
        <th>Debtor's patronymic</th>
        <th>Debtor's phone</th>
    </tr>
    <c:forEach items="${requestScope.foundedBooks}" var="book" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <td>${book.bookName}</td>
            <td>${book.category}</td>
            <td>${book.authorName}</td>
            <td>${book.authorSurname}</td>
            <td>${book.authorPatronymic}</td>
            <td>${book.issued.name}</td>
            <td>${book.issued.surname}</td>
            <td>${book.issued.patronymic}</td>
            <td>${book.issued.tel}</td>
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
