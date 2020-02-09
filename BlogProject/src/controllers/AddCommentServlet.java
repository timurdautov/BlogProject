package controllers;

import dao.Dbmanager;
import models.Comment;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCommentServlet", value="/addComment")
public class AddCommentServlet extends HttpServlet {

    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int user_id = Integer.parseInt(request.getParameter("comment_author"));
        int post_id = Integer.parseInt(request.getParameter("comment_post_id"));
        String content = request.getParameter("comment_new");
        Comment tempComment = new Comment(user_id, post_id, content);
        manager.addComment(tempComment);
        User us = (User)request.getSession().getAttribute("user_signed_in");
        request.getSession().setAttribute("user_posts", manager.getPosts(us.getId()));
        request.getRequestDispatcher("/home.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
