package ru.mail.zahar.kolesnik.library.controllers;

import ru.mail.zahar.kolesnik.library.models.Library;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "add_book", urlPatterns = "/add_book.cab")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Library library = new Library();
        try {
            library.addBook(Integer.parseInt(req.getParameter("isbn")), req.getParameter("bookName"), req.getParameter("booksCategory"), req.getParameter("description"),
                    req.getParameter("authorName"), req.getParameter("authorPatronymic"), req.getParameter("authorSurname"));
        } catch (ClassNotFoundException | SQLException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/page_ok.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/add_book.jsp").forward(req, resp);
    }
}
