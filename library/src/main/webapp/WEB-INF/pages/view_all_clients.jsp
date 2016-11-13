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
<% boolean isLibrarian = session.getAttribute("role").equals("Librarian");%>

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
        <c:choose>
            <c:when test="<%= isLibrarian%>">
                <th>
                </th>
            </c:when>
        </c:choose>
    </tr>
    <c:forEach items="${requestScope.clients}" var="client" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.patronymic}</td>
            <td>${client.tel}</td>
            <c:choose>
                <c:when test="<%= isLibrarian%>">
                    <td>
                        <form >
                            <button name="clientTel" type="button" class="editButton" id="editClient" onclick="postTel('view_all_clients.cab', '${client.tel}')">Edit
                                client
                            </button>
                            <br>
                        </form>
                    </td>
                </c:when>
            </c:choose>
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
