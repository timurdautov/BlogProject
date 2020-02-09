package filters;

import dao.Dbmanager;
import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MainFilter extends HttpFilter {

    private Dbmanager manager;


    public void destroy() {
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("user_signed_in");

        if(user != null){
            User tempUser = manager.auth(user.getEmail(), user.getPassword());
            if(tempUser != null){
                chain.doFilter(request, response);

            }else{
                request.getSession().setAttribute("user_signed_in", null);
                request.getRequestDispatcher("/index.jsp?message=5").forward(request, response);
            }
        }
        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {

        manager = new Dbmanager();
        manager.connect();

    }

}
