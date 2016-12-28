package ru.mail.zahar.kolesnik.library.controllers;

import ru.mail.zahar.kolesnik.library.models.Library;
import ru.mail.zahar.kolesnik.library.models.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "select_book_by_client", urlPatterns = "/select_book_by_client.cab")
public class SelectBookByClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Library library = new Library();
        List<Book> foundedBooks = null;
        try {
            foundedBooks = library.getIssuedBooksByTel(req.getParameter("clientTel"));
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.setAttribute("foundedBooks", foundedBooks);
        req.getRequestDispatcher("/WEB-INF/pages/choose_book_from_client.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Library library = new Library();
        try {
            library.returnBook(req.getParameter("idBook"), Timestamp.valueOf(req.getParameter("dateReturn")));
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/page_ok.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}
