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
        Client's Name<br> <input type="text" id="clientName">
        <br>
    </label>
    <label>
        Client's Surname<br> <input type="text" id="clientSurname">
        <br>
    </label>
    <label>
        Client's Patronymic<br> <input type="text" id="clientPatronymic">
        <br>
    </label>
    <br>
    <button onclick="selectClient('select_client_to_issue.cab')" type="button" class="button">Search client</button>
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
    function selectClient(url) {
        window.location.href = "#result";
        $.post(url, {
            clientName: $('#clientName').val(),
            clientSurname: $('#clientSurname').val(),
            clientPatronymic: $('#clientPatronymic').val()
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }
</script>
</html>
