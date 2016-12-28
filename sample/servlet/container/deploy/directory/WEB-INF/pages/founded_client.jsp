<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
