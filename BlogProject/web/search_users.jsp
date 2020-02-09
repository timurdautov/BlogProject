<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Юзер
  Date: 09.02.2020
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search by users results</title>
</head>
<body>
<%
    ArrayList<User> users = (ArrayList<User>)request.getAttribute("search_user_results");

%>
<ul><%
    for(User us : users) {
        %>
        <li><a href="/profile?id=<%=us.getId()%>"><%=us.getFullName()%></a></li>
    <%
    }
        %>
</ul>

<a href="/home.jsp">Back to main page</a>
</body>
</html>
