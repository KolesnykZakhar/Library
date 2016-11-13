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
import java.util.List;

@WebServlet(name = "view_all_clients", urlPatterns = "/view_all_clients.cab")
public class ViewAllClientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = null;
        Library library = new Library();
        try {
            clients = library.getAllClients();
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/pages/view_all_clients.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Library library = new Library();
        try {
            Client client = library.getClientByPhone(req.getParameter("clientTel"));
            req.setAttribute("nameClient", client.name);
            req.setAttribute("surnameClient", client.surname);
            req.setAttribute("patronymicClient", client.patronymic);
            req.setAttribute("telClient", client.tel);
            req.setAttribute("loginClient", client.login);
            req.setAttribute("passwordClient", client.password);
            req.setAttribute("idClient", client.id);
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.getRequestDispatcher("WEB-INF/pages/edit_client.jsp").forward(req, resp);
    }
}
