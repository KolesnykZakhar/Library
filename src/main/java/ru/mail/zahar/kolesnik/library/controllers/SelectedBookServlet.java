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

@WebServlet(name = "selected_book", urlPatterns = "/selected_book.cab")
public class SelectedBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Library library=new Library();
        try {
            Book book=library.getBookByIsbn(Integer.parseInt(req.getParameter("isbn")));
            getServletConfig().getServletContext().setAttribute("selectedBook", book.info());
            getServletConfig().getServletContext().setAttribute("isbn", book.isbn);
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }
}
