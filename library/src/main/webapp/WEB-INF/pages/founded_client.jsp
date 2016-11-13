<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.mail.zahar.kolesnik.library.models.Library" %><%--
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
<h2>Founded clients</h2>
<table>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Patronymic</th>
        <th>Phone</th>
    </tr>
    <c:forEach items="${requestScope.foundedClients}" var="client" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.patronymic}</td>
            <td>${client.tel}</td>
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
