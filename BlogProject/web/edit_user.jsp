<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: Юзер
  Date: 09.02.2020
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user profile</title>
</head>
<body>
<%
    User u = (User)request.getSession().getAttribute("user_signed_in");
%>
    <h2>Edit user profile:</h2>
    <form action="/editUser?command=1" method="post">
        Full name: <input type="text" name="name_edit" value="<%=u.getFullName()%>"><button>Edit</button></form><br>
    <form action="/editUser?command=2" method="post">
        Password: <input type="password" name="password_edit"><br>
    <button>Edit</button>
</form>
</body>
</html>
