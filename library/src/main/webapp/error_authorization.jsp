<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.10.2016
  Time: 22:09
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
<h1>Library</h1>
<h2>Signing in</h2>
<h3>Wrong login or password</h3>
<form action="${pageContext.request.contextPath}/index.jsp">
    <br>
    <button type="submit" class="button" id="toIndex">To authorization Page</button>
</form>
</body>
</html>
