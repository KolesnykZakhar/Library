package ru.mail.zahar.kolesnik.library.controllers;

import ru.mail.zahar.kolesnik.library.models.Library;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet(name = "remove_client", urlPatterns = "/remove_client.cab")
public class RemoveClientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Library library = new Library();
//        List<Book> issuedBooks = null;
        try {
            if (library.getIssuedBooks().stream().filter(book -> book.issued.id == Integer.parseInt(req.getParameter("idClient")))
                    .collect(Collectors.toList()).size()>0) {
                resp.setContentType("text/html");
                req.getRequestDispatcher("/WEB-INF/pages/error_client_are_debtors.jsp").forward(req, resp);
                return;
            }
            library.removeClientByID(Integer.parseInt(req.getParameter("idClient")));

        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_page.jsp").forward(req, resp);
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/page_ok.jsp").forward(req, resp);
    }
}
