<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title> Enter in Library</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css"
          media="screen, projection">
</head>
<body>
<h1>Library</h1>
<h2>Signing in</h2>
<form action="personal_cabinet.cab" method="post">
    <label>
        Login<br> <input required name="login" type="text" id="login"><br>
    </label>
    <label>
        Password<br> <input required name="password" type="password" id="password"><br>
    </label>
    <br>
    <button type="submit" class="button" id="signIn">Sign In</button>
</form>
</body>
</html>
