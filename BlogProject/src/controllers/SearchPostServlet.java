package controllers;

import dao.Dbmanager;
import models.BlogPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchPostServlet", value="/searchPost")
public class SearchPostServlet extends HttpServlet {
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
        ArrayList<BlogPost> posts = manager.getPostsAll();
        ArrayList<BlogPost> results = new ArrayList<BlogPost>();
        for(BlogPost bp : posts){
            if(bp.getTitle().contains(query) || bp.getText().contains(query)){
                results.add(bp);
            }
        }
        request.setAttribute("search_post_results", results);
        request.getRequestDispatcher("/search_posts.jsp").forward(request, response);

    }
}
