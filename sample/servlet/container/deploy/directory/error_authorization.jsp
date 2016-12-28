<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css"
          media="screen, projection">
</head>
<body>
<h1>Library</h1>
<h2>Signing in</h2>
<h3>Wrong login or password</h3>
<form action="${pageContext.request.contextPath}/index.jsp">
    <br>
    <button type="submit" class="button" id="toIndex">To authorization Page</button>
</form>
</body>
</html>
