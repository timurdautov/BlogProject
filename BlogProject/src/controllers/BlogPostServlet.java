package controllers;

import dao.Dbmanager;
import models.BlogPost;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "BlogPostServlet", value="/addPost")
public class BlogPostServlet extends HttpServlet {

    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User us = (User)request.getSession().getAttribute("user_signed_in");
        BlogPost post = new BlogPost(us.getId(), request.getParameter("post_title"), request.getParameter("post_content"));
        manager.addPost(post);
        request.getSession().setAttribute("user_posts", manager.getPosts(us.getId()));
        request.getRequestDispatcher("/home.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User us = (User)request.getSession().getAttribute("user_signed_in");

        request.setAttribute("user_posts", manager.getPosts(us.getId()));
        request.getRequestDispatcher("/home.jsp").forward(request, response);

    }
}
