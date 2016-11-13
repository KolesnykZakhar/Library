<%@ page import="ru.mail.zahar.kolesnik.library.models.Library" %>
<%@ page import="ru.mail.zahar.kolesnik.library.models.entity.Client" %><%--
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
<br>
<h2>Fill in information about the client</h2>
<form>
    <label>
        <sup>*</sup>Name<br>
        <input required name="namePerson" type="text" id="namePerson" value="<%=request.getAttribute("nameClient")%>">
        <br>
    </label>
    <label>
        <sup>*</sup>Surname<br>
        <input required name="surname" type="text" id="surname" value="<%=request.getAttribute("surnameClient")%>">
        <br>
    </label>
    <label>
        Patronymic<br>
        <input name="patronymic" type="text" id="patronymic" value="<%=request.getAttribute("patronymicClient")%>">
        <br>
    </label>
    <label>
        <sup>*</sup>Phone<br>
        <input required name="tel" type="text" id="tel" min="13" max="13"
               value="<%=request.getAttribute("telClient")%>">
        <br>
    </label>
    <label>
        <sup>*</sup>Login<br>
        <input required name="loginClient" type="text" id="loginClient"
               value="<%=request.getAttribute("loginClient")%>">
        <br>
    </label>
    <label>
        <sup>*</sup>Password<br>
        <input required name="passwordClient" type="text" id="passwordClient"
               value="<%=request.getAttribute("passwordClient")%>">
        <br>
    </label>
    <label>
        <sup>*</sup>Confirm password<br>
        <input required name="confirmPasswordClient" type="text" id="confirmPasswordClient"
               value="<%=request.getAttribute("passwordClient")%>">
        <br>
    </label>
    <input type="hidden" name="idClient" id="idClient" value="<%=request.getAttribute("idClient")%>">
    <br>
    <button type="button" onclick="editClient('edit_client.cab')" class="button">Edit client</button>
</form>
<br>
<form>
    <button onclick="removeClient('remove_client.cab')" type="button" class="button">Remove client</button>
    <input type="hidden" name="idClient" value="<%=request.getAttribute("idClient")%>">
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
    function removeClient(url) {
        window.location.href = "#result";
        $.post(url, {
            idClient: $('#idClient').val()
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }

    function editClient(url) {
        window.location.href = "#result";
        $.post(url, {
            namePerson: $('#namePerson').val(),
            surname: $('#surname').val(),
            patronymic: $('#patronymic').val(),
            tel: $('#tel').val(),
            loginClient: $('#loginClient').val(),
            passwordClient: $('#passwordClient').val(),
            confirmPasswordClient: $('#confirmPasswordClient').val(),
            idClient: $('#idClient').val()
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }
</script>
</html>
