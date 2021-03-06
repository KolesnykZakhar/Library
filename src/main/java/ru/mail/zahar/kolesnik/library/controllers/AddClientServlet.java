package ru.mail.zahar.kolesnik.library.controllers;

import ru.mail.zahar.kolesnik.library.models.Library;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "add_client", urlPatterns = "/add_client.cab")
public class AddClientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("passwordClient").trim().equals(req.getParameter("confirmPasswordClient").trim())){
            resp.setContentType("text/html");
            req.getRequestDispatcher("WEB-INF/pages/error_confirm_password.jsp").forward(req, resp);
            return;
        }
        Library library = new Library();
        try {
            library.addClient(req.getParameter("namePerson"), req.getParameter("surname"), req.getParameter("patronymic"),
                    req.getParameter("tel"), req.getParameter("loginClient"), req.getParameter("passwordClient"));
        } catch (ClassNotFoundException | SQLException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.getRequestDispatcher("WEB-INF/pages/page_ok.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/add_client.jsp").forward(req, resp);
    }
}
