package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mkolbusz on 6/21/16.
 */
//@WebFilter(urlPatterns = {"/public/index.xhtml"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.isUserInRole("ADMIN")) {
            System.err.println("ADMIN REDIRECT");
            response.sendRedirect("../admin/index.xhtml");
        }else if(request.isUserInRole("USER")) {
            System.err.println("USER REDIRECT");
            response.sendRedirect("../user/index.xhtml");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
