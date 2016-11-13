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
<h2>Fill in information about the client</h2>
<form>
    <label>
        <sup>*</sup>Name<br>
        <input required type="text" id="namePerson">
        <br>
    </label>
    <label>
        <sup>*</sup>Surname<br>
        <input required type="text" id="surname">
        <br>
    </label>
    <label>
        Patronymic<br>
        <input type="text" id="patronymic">
        <br>
    </label>
    <label>
        <sup>*</sup>Phone<br>
        <input required type="text" id="tel" min="13" max="13">
        <br>
    </label>
    <label>
        <sup>*</sup>Login<br>
        <input required type="text" id="loginClient">
        <br>
    </label>
    <label>
        <sup>*</sup>Password<br>
        <input required type="text" id="passwordClient">
        <br>
    </label>
    <label>
        <sup>*</sup>Confirm password<br>
        <input required type="text" id="confirmPasswordClient">
        <br>
    </label>
    <br>
    <button onclick="addClient('add_client.cab')" type="button" class="button">Add client</button>
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
    function addClient(url) {
        window.location.href = "#result";
        $.post(url, {
            namePerson: $('#namePerson').val(),
            surname: $('#surname').val(),
            patronymic: $('#patronymic').val(),
            tel: $('#tel').val(),
            loginClient: $('#loginClient').val(),
            passwordClient: $('#passwordClient').val(),
            confirmPasswordClient: $('#confirmPasswordClient').val()
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }
</script>
</html>
