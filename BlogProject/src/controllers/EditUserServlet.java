package controllers;

import dao.Dbmanager;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditUserServlet", value="/editUser")
public class EditUserServlet extends HttpServlet {


    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");
        String name = request.getParameter("name_edit");
        String password = request.getParameter("password_edit");

        User us = (User)request.getSession().getAttribute("user_signed_in");

        if(command.equals("1")){
            manager.editUserName(us.getId(), name);
            request.getSession().setAttribute("user_signed_in", manager.getUserById(us.getId()));
            request.getSession().setAttribute("users", manager.getUsers());
            response.sendRedirect("/home.jsp");
        }else if(command.equals("2")){
            manager.editPassword(us.getId(), password);
            request.getSession().setAttribute("user_signed_in", manager.getUserById(us.getId()));
            response.sendRedirect("/home.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/edit_user.jsp").forward(request, response);

    }
}
