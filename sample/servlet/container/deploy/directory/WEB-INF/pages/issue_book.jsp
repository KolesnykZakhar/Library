<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css" type="text/css"
          media="screen, projection">
</head>
<body>
<h2>Issue books to the client</h2>
<table>
    <tr>
        <td style="font-size: 25px; width: 100px;">Client</td>
        <td style="font-size: 25px"><%=getServletConfig().getServletContext().getAttribute("selectedClient")%>
        </td>
        <td>
            <form>
                <button onclick="selectClient('select_client_to_issue.cab')" type="button" class="logOutButton">Select
                    client
                </button>
            </form>
        </td>
    </tr>
    <tr>
        <td style="font-size: 25px">Book</td>
        <td style="font-size: 25px"><%=getServletConfig().getServletContext().getAttribute("selectedBook")%>
        </td>
        <td>
            <form>
                <button onclick="selectBook('select_book_to_issue.cab')" type="button" class="logOutButton">Select book
                </button>
            </form>
        </td>
    </tr>
    <tr>
        <td style="font-size: 25px"><%=LocalDateTime.now().toLocalDate()%>
        </td>
        <td>
            <select style="transform-style: preserve-3d; font-size: 25px; width: 750px; height: 50px;"
                    title="Choose the return date" required id="dateReturn">
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(1))%>"><%=LocalDateTime.now().plusDays(1).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(2))%>"><%=LocalDateTime.now().plusDays(2).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(3))%>"><%=LocalDateTime.now().plusDays(3).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(4))%>"><%=LocalDateTime.now().plusDays(4).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(5))%>"><%=LocalDateTime.now().plusDays(5).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(6))%>"><%=LocalDateTime.now().plusDays(6).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(7))%>"><%=LocalDateTime.now().plusDays(7).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(8))%>"><%=LocalDateTime.now().plusDays(8).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(9))%>"><%=LocalDateTime.now().plusDays(9).toLocalDate()%>
                </option>
                <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(10))%>"><%=LocalDateTime.now().plusDays(10).toLocalDate()%>
                </option>
            </select>
        </td>
        <td>
            <form>
                <button onclick="issue('issue_book.cab')" type="button" class="logOutButton">Issue book</button>
            </form>
        </td>
    </tr>
</table>
<br>

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
    function issue(url) {
        window.location.href = "#result";
        $.post(url, {
            dateReturn: $('#dateReturn').val(),
        }, function (responseText) {
            $('#result').html(responseText);
        });
    }
    function selectClient(url) {
        window.location.href = "#result";
        $.get(url, {}, function (responseText) {
            $('#result').html(responseText);
        });
    }
    function selectBook(url) {
        window.location.href = "#result";
        $.get(url, {}, function (responseText) {
            $('#result').html(responseText);
        });
    }
</script>
</html>
