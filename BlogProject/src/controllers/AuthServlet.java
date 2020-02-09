package controllers;

import dao.Dbmanager;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuthServlet", value="/auth")
public class AuthServlet extends HttpServlet {

    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email_signin");
        String password = request.getParameter("password_signin");
        User check = manager.auth(email, password);

        if(check!=null){
            request.getSession().setAttribute("user_signed_in", check);
            request.getSession().setAttribute("user_posts", manager.getPosts(check.getId()));
            request.getSession().setAttribute("users", manager.getUsers());
            request.getRequestDispatcher("/home.jsp").forward(request, response);

        }
        request.getRequestDispatcher("/index.jsp?message=3").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
