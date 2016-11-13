<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.mail.zahar.kolesnik.library.models.Library" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %><%--
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
<br>
<h2>List of all books in library</h2>
<form>
    <select style="align-self: center; transform-style: preserve-3d; font-size: 25px; width: 750px; height: 50px;"
            title="Choose the return date" required id="dateReturn">
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(1))%>"><%=LocalDateTime.now().plusDays(1).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(2))%>"><%=LocalDateTime.now().plusDays(2).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(3))%>"><%=LocalDateTime.now().plusDays(3).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(4))%>"><%=LocalDateTime.now().plusDays(4).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(5))%>"><%=LocalDateTime.now().plusDays(5).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(6))%>"><%=LocalDateTime.now().plusDays(6).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(7))%>"><%=LocalDateTime.now().plusDays(7).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(8))%>"><%=LocalDateTime.now().plusDays(8).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(9))%>"><%=LocalDateTime.now().plusDays(9).toLocalDate()%>
        </option>
        <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(10))%>"><%=LocalDateTime.now().plusDays(10).toLocalDate()%>
        </option>
    </select>
</form>
<table>
    <tr>
        <th>#</th>
        <th>Book's name</th>
        <th>Category</th>
        <th>Author's names</th>
        <th>Author's surname</th>
        <th>Author's patronymic</th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.foundedBooks}" var="book" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <td>${book.bookName}</td>
            <td>${book.category}</td>
            <td>${book.authorName}</td>
            <td>${book.authorSurname}</td>
            <td>${book.authorPatronymic}</td>
            <td>
                <form>
                    <button type="button" class="editButton"
                            onclick="postIsbn('select_book_by_client.cab', '${book.idBook}')">Select
                        book
                    </button>
                    <br>
                </form>
            </td>
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
<script src="webjars/jquery/3.1.1/jquery.js"></script>
<script>
    function postIsbn(url, idBook, dateReturn) {
        window.location.href = "#result'";
        $.post(url, {
            idBook: idBook,
            dateReturn: $('#dateReturn').val()
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }
</script>
</html>
