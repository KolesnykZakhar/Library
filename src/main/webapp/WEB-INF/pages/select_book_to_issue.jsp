<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css"
          media="screen, projection">
</head>
<body>
<h2>Fill in the search box</h2>
<form>
    <label>
        Author's Name<br> <input type="text" id="authorName">
        <br>
    </label>
    <label>
        Author's Patronymic<br> <input type="text" id="authorPatronymic">
        <br>
    </label>
    <label>
        Author's Surname<br> <input type="text" id="authorSurname">
        <br>
    </label>
    <label>
        Book's Name<br> <input type="text" id="bookName">
        <br>
    </label>
    <label>
        Books's Category<br> <input type="text" id="booksCategory">
        <br>
    </label>
    <br>
    <button onclick="searchBook('select_book_to_issue.cab')" type="button" class="button">Search book</button>
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
    function searchBook(url) {
        window.location.href = "#result";
        $.post(url, {
            authorName: $('#authorName').val(),
            authorPatronymic: $('#authorPatronymic').val(),
            authorSurname: $('#authorSurname').val(),
            bookName: $('#bookName').val(),
            booksCategory: $('#booksCategory').val()
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }
</script>
</html>