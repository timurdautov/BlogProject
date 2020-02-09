<%@ page import="models.User" %>
<%@ page import="models.BlogPost" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Comment" %><%--
  Created by IntelliJ IDEA.
  User: Юзер
  Date: 09.02.2020
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        User u = (User)request.getAttribute("user_profile");
        ArrayList<BlogPost> uPosts = (ArrayList<BlogPost>)request.getAttribute("user_profile_posts");
        User current = (User)request.getSession().getAttribute("user_signed_in");

    %>
    <title><%=u.getFullName()%></title>

</head>
<body>
<h2><%=u.getFullName()%></h2>
<table>
<%
    for(BlogPost post : uPosts){
    %>
    <tr><td><%=post.getTitle()%></td></tr>
    <tr><td><p><%=post.getText()%></p></td></tr>
    <tr><td><%=post.getDate()%></td></tr>
    <td><td><ul>
    <%
        ArrayList<Comment> tempComments = post.getComments();
        for(Comment com : tempComments){
            %><li>
                    Author: <%=com.getUser_id()%>, <%=com.getContent()%>, date: <%=com.getPost_date()%>
            <%
            if(com.getUser_id() == current.getId()){
    %><form action="deleteComment" method="get">
    <input type="hidden" value="<%=com.getId()%>" name="comment_delete">
    <button>Delete comment</button>
</form>
    <%
        }
    %>
            </li><%
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
