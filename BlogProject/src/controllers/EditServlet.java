package controllers;

import dao.Dbmanager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditServlet", value="/edit")
public class EditServlet extends HttpServlet {

    private Dbmanager manager;

    @Override
    public void init() throws ServletException {
        manager = new Dbmanager();
        manager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int edit_id = Integer.parseInt(request.getParameter("id_edit"));
        String title = request.getParameter("title_edit");
        String text = request.getParameter("text_edit");
        manager.editPost(edit_id, title, text);
        int user_id = Integer.parseInt(request.getParameter("id_user"));
        request.getSession().setAttribute("user_posts", manager.getPosts(user_id));
        response.sendRedirect("/home.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idPost"));
        request.setAttribute("post_edit", manager.getPostById(id));
        request.getRequestDispatcher("/edit.jsp").forward(request, response);

    }
}
