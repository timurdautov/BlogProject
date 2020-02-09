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
import java.util.ArrayList;

@WebServlet(name = "SearchUserServlet", value="/searchUser")
public class SearchUserServlet extends HttpServlet {

    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query = request.getParameter("search_query");
        ArrayList<User> users = manager.getUsers();
        ArrayList<User> results = new ArrayList<User>();
        for(User us : users){
            if(us.getFullName().contains(query)){
                results.add(us);
            }
        }
        request.setAttribute("search_user_results", results);
        request.getRequestDispatcher("/search_users.jsp").forward(request, response);


    }
}
