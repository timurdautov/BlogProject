package controllers;

import dao.Dbmanager;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePostServlet", value="/deletePost")
public class DeletePostServlet extends HttpServlet {

    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idPostDelete"));
        manager.deletePost(id);
        User us = (User)request.getSession().getAttribute("user_signed_in");
        request.getSession().setAttribute("user_posts", manager.getPosts(us.getId()));
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
