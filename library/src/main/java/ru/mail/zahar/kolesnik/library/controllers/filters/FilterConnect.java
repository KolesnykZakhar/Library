package ru.mail.zahar.kolesnik.library.controllers.filters;

import ru.mail.zahar.kolesnik.library.models.Library;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


//@WebFilter(servletNames = {"personalCabinet"})
@WebFilter(urlPatterns = {"*.cab"})
public class FilterConnect implements Filter {

    private Library library;

    public void init(FilterConfig config) throws ServletException {
        library = new Library();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        class RoleAndName {
            private boolean isNewSession() {
                return ((HttpServletRequest) req).getSession().getAttribute("role") == null
                        && ((HttpServletRequest) req).getSession().getAttribute("name") == null;
            }
        }
        String roleAndName[] = new String[2];
        if (req.getParameter("login") != null && req.getParameter("password") != null /*&& req.getParameter("confirmPassword") == null*/) {
            try {
                roleAndName = library.role(req.getParameter("login"), req.getParameter("password"));
                ((HttpServletRequest) req).getSession().setAttribute("role", roleAndName[0]);
                ((HttpServletRequest) req).getSession().setAttribute("name", roleAndName[1]);
                ((HttpServletRequest) req).getSession().setAttribute("login", req.getParameter("login"));
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("sql exception in filter");
                e.printStackTrace();
            }
            if (!roleAndName[0].equals("")) {
                resp.setContentType("text/html");
                req.getRequestDispatcher("/WEB-INF/pages/personal_cabinet.jsp").forward(req, resp);
                return;
            }
            resp.setContentType("text/html");
            req.getRequestDispatcher("/error_authorization.jsp").forward(req, resp);
            System.out.println("filter send to error");

        } else {
            RoleAndName roleAndNameClass = new RoleAndName();
            if (roleAndNameClass.isNewSession()) {
                resp.setContentType("text/html");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                return;
            }
            roleAndName[0] = (String) ((HttpServletRequest) req).getSession().getAttribute("role");
            roleAndName[1] = (String) ((HttpServletRequest) req).getSession().getAttribute("name");
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {
        library = null;
    }
}
