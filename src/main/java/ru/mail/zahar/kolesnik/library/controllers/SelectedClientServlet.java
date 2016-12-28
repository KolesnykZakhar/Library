package ru.mail.zahar.kolesnik.library.controllers;

import ru.mail.zahar.kolesnik.library.models.Library;
import ru.mail.zahar.kolesnik.library.models.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "selected_client", urlPatterns = "/selected_client.cab")
public class SelectedClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Library library=new Library();
        try {
            Client client=library.getClientByPhone(req.getParameter("clientTel"));
            getServletConfig().getServletContext().setAttribute("selectedClient", client.info());
            getServletConfig().getServletContext().setAttribute("tel", client.tel);
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }
}
