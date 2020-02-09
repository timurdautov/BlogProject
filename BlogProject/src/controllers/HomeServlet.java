package controllers;

import dao.Dbmanager;
import models.User;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value="/home")
public class HomeServlet extends HttpServlet {

    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email_signup");
        String password = request.getParameter("password_signup");
        String fullname = request.getParameter("full_name_signup");
        boolean registerAttempt = manager.register(email, password, fullname);

        if(registerAttempt){
            request.getRequestDispatcher("/index.jsp?message=1").forward(request, response);
        }else{
            request.getRequestDispatcher("/index.jsp?message=2").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
