<%@ page import="models.BlogPost" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Comment" %>
<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: Юзер
  Date: 09.02.2020
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search by posts results</title>
</head>
<body>
<%
    ArrayList<BlogPost> results = (ArrayList<BlogPost>)request.getAttribute("search_post_results");
    User current = (User)request.getSession().getAttribute("user_signed_in");
%>

<table>
    <%
        for(BlogPost post : results){
    %>
    <tr><td><%=post.getTitle()%></td></tr>
    <tr><td><p><%=post.getText()%></p></td></tr>
    <tr><td><%=post.getDate()%></td></tr>
    <td><td><ul>
    <%
        ArrayList<Comment> tempComments = post.getComments();
        if(tempComments!=null){
        for(Comment com : tempComments){
    %><li>
    Author: <%=com.getUser_id()%>, <%=com.getContent()%>, date: <%=com.getPost_date()%>
</li><%
    }
    }
%>
    <li><form action="/addComment" method="post">New comment:
        <input type="text" name="comment_new" placeholder="Enter comment">
        <input type="hidden" value="<%=post.getId()%>" name="comment_post_id">
        <input type="hidden" value="<%=current.getId()%>" name="comment_author">
        <button>Add comment</button></form></li>
</ul></td></tr><%
    }
%>
</table>
<a href="/home.jsp">Back to main page</a>


</body>
</html>
