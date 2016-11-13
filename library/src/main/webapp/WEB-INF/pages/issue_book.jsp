

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.mail.zahar.kolesnik.library.models.Library" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.sql.Timestamp" %><%--
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

    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/javaScript/datetimepicker-master/jquery.datetimepicker.css">--%>
    <%--<script src="${pageContext.request.contextPath}/resources/javaScript/datetimepicker-master/jquery.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/resources/javaScript/datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script>--%>
</head>
<body>
<h2>Issue books to the client</h2>

<table>
    <tr>
        <td style="font-size: 25px; width: 100px;">Client</td>
        <%--<td style="font-size: 25px"><%=request.getAttribute("selectedClient")%>--%>
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
        <%--<td style="font-size: 25px"><%=request.getAttribute("selectedBook")%>--%>
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
            <%--<label for="issueEnd">--%>
            <select <%--form="issueEnd"--%> style="transform-style: preserve-3d; font-size: 25px; width: 750px; height: 50px;"
            title="Choose the return date" required id="dateReturn">
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(1))%>"><%=LocalDateTime.now().plusDays(1).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(2))%>"><%=LocalDateTime.now().plusDays(2).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(3))%>"><%=LocalDateTime.now().plusDays(3).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(4))%>"><%=LocalDateTime.now().plusDays(4).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(5))%>"><%=LocalDateTime.now().plusDays(5).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(6))%>"><%=LocalDateTime.now().plusDays(6).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(7))%>"><%=LocalDateTime.now().plusDays(7).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(8))%>"><%=LocalDateTime.now().plusDays(8).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(9))%>"><%=LocalDateTime.now().plusDays(9).toLocalDate()%></option>
            <option value="<%=Timestamp.valueOf(LocalDateTime.now().plusDays(10))%>"><%=LocalDateTime.now().plusDays(10).toLocalDate()%></option>
            </select>
            <%--</label>--%>
            <%--&lt;%&ndash;<label>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form>&ndash;%&gt;--%>
                <%--<input title="Choose the return date" type="datetime" id="dateReturn"  class="date" &lt;%&ndash;style="transform-style: preserve-3d; font-size: 25px; width: 750px; height: 50px"&ndash;%&gt;>--%>
                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</label>&ndash;%&gt;--%>
                <%--<form>--%>
                    <%--<p>Время создания публикации</p>--%>
                    <%--<label>--%>
                    <%--<input type="datetime" name="created">--%>
                    <%--</label>--%>
                    <%--<p><input type="submit"></p>--%>
                <%--</form>--%>
                    <%--<input title="date" type="text" id="datetimepicker">--%>
        </td>
        <td>
            <form <%--id="issueEnd"--%>>
                <button onclick="issue('issue_book.cab')" type="button" class="logOutButton">Issue book</button>
                <%--<input type="submit" class="logOutButton" id="issueBook">Issue book--%>
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
//    jQuery('#datetimepicker').datetimepicker({
//        format:'d.m.Y H:i',
//        inline:true,
//        lang:'ru'
//    });
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
