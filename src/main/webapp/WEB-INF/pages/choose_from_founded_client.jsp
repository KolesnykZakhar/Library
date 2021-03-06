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
<br>
<br>
<h2>List of all clients in library</h2>
<table class="editTable" cellpadding="1px">
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Patronymic</th>
        <th>Phone</th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.foundedClients}" var="client" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.patronymic}</td>
            <td>${client.tel}</td>
            <td>
                <form>
                    <button type="button" class="editButton"
                            onclick="postTel('selected_client.cab', '${client.tel}')">Select
                        client
                    </button>
                    <br>
                </form>
            </td>
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
<script src="webjars/jquery/3.1.1/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/javaScript/ajax_post_get.js">
</script>
</html>
