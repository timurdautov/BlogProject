<%@ page import="models.BlogPost" %><%--
  Created by IntelliJ IDEA.
  User: Юзер
  Date: 09.02.2020
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit post</title>
</head>
<body>

<%
    BlogPost p = (BlogPost)request.getAttribute("post_edit");
%>
<form action="/edit" method="post">
    <input type="hidden" name="id_edit" value="<%=p.getId()%>">
    <input type="hidden" name="id_user" value="<%=p.getUserId()%>">
    <input type="text" name="title_edit" value="<%=p.getTitle()%>">
    <textarea name="text_edit"><%=p.getText()%></textarea>
    <button>Edit post</button>
</form>

</body>
</html>
