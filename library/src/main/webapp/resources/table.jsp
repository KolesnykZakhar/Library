<% boolean isLibrarian = session.getAttribute("role").equals("Librarian");%>

<table style="width: 100%;" class="editTable" cellpadding="1px">
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
                        <form action="${pageContext.request.contextPath}/view_all_clients" method="post">
                                <%--<button name="client" type="submit" class="editButton" id="editClient" value="${client.tel}">Edit--%>
                            <button name="clientTel" type="submit" class="editButton" id="editClient" value="${client.tel}">Edit
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