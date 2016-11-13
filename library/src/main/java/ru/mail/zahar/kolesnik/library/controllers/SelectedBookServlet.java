package ru.mail.zahar.kolesnik.library.controllers;

import ru.mail.zahar.kolesnik.library.models.Library;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Book;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Client;

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
//        getServletConfig().getServletContext().setAttribute("client", req.getSession().getAttribute("name"));
//        getServletConfig().getServletContext().setAttribute("id", 12);
//        getServletConfig().getServletContext().setAttribute("selectedClient", "Choose the client");
//        getServletConfig().getServletContext().setAttribute("selectedBook", "Choose the book");
//        getServletConfig().getServletContext().setAttribute("selectedReturnDate", "Selected date to return");



        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(getServletConfig().getServletContext().getAttribute("client"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("id"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("selectedClient"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("selectedBook"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("selectedReturnDate"));
//        System.out.println(req.getParameter("isbn"));
        Library library=new Library();
        Book book=null;
        try {
            book=library.getBookByIsbn(Integer.parseInt(req.getParameter("isbn")));
            getServletConfig().getServletContext().setAttribute("selectedBook", book.info());
            getServletConfig().getServletContext().setAttribute("isbn", book.isbn);
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }

//        System.out.println(req.getParameter("isbn"));
//        System.out.println(book.isbn);
//        System.out.println(req.getParameter("dateReturn"));
//        System.out.println(req.getParameter("clientTel"));
//        System.out.println((Client) req.getAttribute("client").info());

        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }
}
