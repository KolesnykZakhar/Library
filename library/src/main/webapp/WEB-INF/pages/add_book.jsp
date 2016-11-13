<%@ page import="ru.mail.zahar.kolesnik.library.models.Library" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.10.2016
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css"
          media="screen, projection">
</head>
<body>
<h2>Fill in information about the book</h2>
<form>
    <label>
        <sup>*</sup>ISBN<br>
        <input required type="number" id="isbn" max="9">
        <br>
    </label>
    <label>
        <sup>*</sup>Book's Name<br>
        <input required type="text" id="bookName">
        <br>
    </label>
    <label>
        <sup>*</sup>Book's Category<br>
        <input required type="text" id="booksCategory">
        <br>
    </label>
    <label>
        <sup>*</sup>Author's name<br>
        <input required type="text" id="authorName">
        <br>
    </label>
    <label>
        <sup>*</sup>Author's Surname<br>
        <input required type="text" id="authorSurname">
        <br>
    </label>
    <label>
        Author's Patronymic<br>
        <input type="text" id="authorPatronymic">
        <br>
    </label>
    <label>
        Description<br>
        <textarea id="description" class="area" rows="8" cols="40"></textarea>
        <br>
    </label>
    <br>
    <button onclick="addBook('add_book.cab')" type="submit" class="button" >Add book</button>
</form>
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
    function addBook(url) {
        window.location.href = "#result";
        $.post(url, {
            isbn: $('#isbn').val(),
            bookName: $('#bookName').val(),
            booksCategory: $('#booksCategory').val(),
            authorName: $('#authorName').val(),
            authorSurname: $('#authorSurname').val(),
            authorPatronymic: $('#authorPatronymic').val(),
            description: $('#description').val()
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }
</script>
</html>
