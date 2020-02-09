<%@ page import="models.User" %>
<%@ page import="models.BlogPost" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Comment" %><%--
  Created by IntelliJ IDEA.
  User: Юзер
  Date: 03.02.2020
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile page</title>
</head>
<body>
    <%
        User current = (User)request.getSession().getAttribute("user_signed_in");
        out.println("Signed-in user: " + current.getFullName());
        ArrayList<BlogPost> posts = (ArrayList<BlogPost>)request.getSession().getAttribute("user_posts");
    %>
    <a href="/editUser">Edit profile</a>
    <form action="/signout" method="get">
<button>Sign out</button>
    </form>

    <hr>

<form action="/addPost" method="post">
    <table>
        <tr>
            <td><h2>New blog post:</h2></td>
        </tr>
        <tr>
            <td><label>Post title: </label></td>
            <td><input type="text" name="post_title"></td>
        </tr>
        <tr>
            <td>
                <textarea name="post_content">
                    Post text...
                </textarea>
            </td>
        </tr>
        <tr>
            <td>
                <button>Add post</button>
            </td>
        </tr>
    </table>
</form>
    <hr>
    <h2>Blog posts by this user:</h2>
<table>
    <%
        if(posts!=null){
      for(BlogPost bp : posts){
          %>
        <tr><td><%=bp.getTitle()%></td></tr>
        <tr><td><p><%=bp.getText()%></p></td></tr>
        <tr><td><%=bp.getDate()%></td></tr>
        <tr><td><a href="/edit?idPost=<%=bp.getId()%>">Edit</a></td></tr>
        <tr><td><form action="/deletePost" method="get">
            <input type="hidden" name="idPostDelete" value="<%=bp.getId()%>">
            <button>Delete</button></form></td></tr>
        <tr><td><ul>
    <%
              ArrayList<Comment> tempComments = bp.getComments();
          if(bp.getComments()!=null){
              for(Comment cm : tempComments){
                %><li>
                    Author: <%=cm.getUser_id()%>, <%=cm.getContent()%>, date: <%=cm.getPost_date()%>
                <%
                    if(cm.getUser_id() == current.getId()){
                        %><form action="deleteComment" method="get">
                            <input type="hidden" value="<%=cm.getId()%>" name="comment_delete">
                            <button>Delete comment</button>
                        </form>
                        <%
                    }
                %>
                </li>
                <%
              }
          }
          %>
        <li><form action="/addComment" method="post">New comment:
            <input type="text" name="comment_new" placeholder="Enter comment">
            <input type="hidden" value="<%=bp.getId()%>" name="comment_post_id">
            <input type="hidden" value="<%=current.getId()%>" name="comment_author">
            <button>Add comment</button></form></li>
        </ul>
        </td></tr>
        <%
      }
            }
    %>
</table>
<hr>
<h3>Other users: </h3>
<%
    ArrayList<User> users = (ArrayList<User>)request.getSession().getAttribute("users");
    for(User u : users){
        %>
        <p><a href="/profile?id=<%=u.getId()%>">
            <%=u.getFullName()%></a></p>
        <%
    }
%>
    <h3>Search posts:</h3>
    <form action="/searchPost" method="get">
        <input type="text" name="search_query" placeholder="Enter query">
        <button>Search</button>
    </form>
    <hr>
    <h3>Search users:</h3>
    <form action="/searchUser" method="get">
        <input type="text" name="search_query" placeholder="Enter query">
        <button>Search</button>
    </form>
</body>
</html>
