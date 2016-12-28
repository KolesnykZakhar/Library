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
import java.util.List;

@WebServlet(name = "select_book_to_issue", urlPatterns = "/select_book_to_issue.cab")
public class SelectBookToIssueServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("authorName").trim().equals("") && req.getParameter("authorPatronymic").trim().equals("") &&
                req.getParameter("authorSurname").trim().equals("") && req.getParameter("bookName").trim().equals("") &&
                req.getParameter("booksCategory").trim().equals("")){
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/select_book_to_issue.jsp").forward(req, resp);
            return;
        }
        Library library = new Library();
        List<Book> foundedBooks = null;
        try {
            foundedBooks = library.searchBook(req.getParameter("authorName"), req.getParameter("authorPatronymic"), req.getParameter("authorSurname"),
                    req.getParameter("bookName"), req.getParameter("booksCategory"));
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.setAttribute("foundedBooks", foundedBooks);
        req.getRequestDispatcher("/WEB-INF/pages/choose_from_founded_book.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/select_book_to_issue.jsp").forward(req, resp);
    }
}
