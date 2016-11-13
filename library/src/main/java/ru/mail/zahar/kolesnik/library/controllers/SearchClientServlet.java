package ru.mail.zahar.kolesnik.library.controllers;

import ru.mail.zahar.kolesnik.library.models.Library;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "search_client", urlPatterns = "/search_client.cab")
public class SearchClientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("clientName").trim().equals("") && req.getParameter("clientSurname").trim().equals("") &&
                req.getParameter("clientPatronymic").trim().equals("")){
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/search_client.jsp").forward(req, resp);
            return;
        }
        Library library = new Library();
        List<Client> foundedClients = null;
        try {
            foundedClients = library.searchClient(req.getParameter("clientName"), req.getParameter("clientSurname"),
                    req.getParameter("clientPatronymic"));
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.setAttribute("foundedClients", foundedClients);
        req.getRequestDispatcher("/WEB-INF/pages/founded_client.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/search_client.jsp").forward(req, resp);
    }
}
