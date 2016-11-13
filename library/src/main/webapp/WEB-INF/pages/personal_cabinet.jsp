<!DOCTYPE html>
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
<% boolean isAdministrator = session.getAttribute("role").equals("Administrator");%>

<br>
<form class="logOutButton" action="${pageContext.request.contextPath}/logout.cab">
    <button type="submit" class="logOutButton" id="logout">Log out</button>
</form>
<h6><%=session.getAttribute("role")%>: <%=session.getAttribute("name")%>
</h6>
<br>
<h1>Personal Cabinet</h1>
<br>

<c:choose>
    <c:when test="<%= isLibrarian%>">
        <form>
            <button onclick="get('add_client.cab')" type="button" class="button" id="addClient">Add client</button>
        </form>
        <br>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="<%= isLibrarian || isAdministrator%>">
        <form>
            <button onclick="get('search_client.cab')" type="button" class="button" id="searchClient">Search client</button>
        </form>
        <br>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="<%= isLibrarian%>">
        <form>
            <button onclick="get('view_debtors.cab')" type="button" class="button" id="viewDebtors">View debtors</button>
        </form>
        <br>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="<%= isLibrarian || isAdministrator%>">
        <form>
            <button type="button" onclick="get('view_all_clients.cab')" class="button" id="viewAllClients">View all clients</button>
        </form>
        <br>
        <hr>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="<%= isAdministrator%>">
        <form>
            <button onclick="get('add_book.cab')" type="button" class="button" id="addBook">Add book</button>
        </form>
        <br>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="<%= isLibrarian%>">
        <form>
            <button onclick="get('issue_book.cab')" type="button" class="button" id="issueBook">Issue book</button>
        </form>
        <br>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="<%= isLibrarian%>">
        <form>
            <button onclick="get('return_book.cab')" type="button" class="button" id="returnBook">Return book</button>
        </form>
        <br>
    </c:when>
</c:choose>

<form>
    <button onclick="get('search_book.cab')" type="button" class="button" id="searchBooks">Search book</button>
</form>
<br>

<c:choose>
    <c:when test="<%= isLibrarian%>">
        <form>
            <button onclick="get('view_issued_books.cab')" type="button" class="button" id="viewIssuedBooks">View issued books</button>
        </form>
        <br>
    </c:when>
</c:choose>

<form>
    <button type="button" onclick="get('view_all_books.cab')" class="button" id="viewAllBooks">View all books</button>
</form>
<br>
<hr>

<form>
    <button onclick="get('view_my_rented_books.cab')" type="button" class="button" id="viewMyRentedBooks">View my rented books</button>
</form>

<br>
<br>
<hr id="hrMax">
<br>
<br>
<div id="result" ></div>
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