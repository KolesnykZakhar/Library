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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "issue_book", urlPatterns = "/issue_book.cab")
public class IssueBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        getServletConfig().getServletContext().setAttribute("client", req.getSession().getAttribute("name"));
//        getServletConfig().getServletContext().setAttribute("id", 12);
        getServletConfig().getServletContext().setAttribute("selectedClient", "Choose the client");
        getServletConfig().getServletContext().setAttribute("selectedBook", "Choose the book");
        getServletConfig().getServletContext().setAttribute("selectedReturnDate", "Selected date to return");


        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = getServletConfig().getServletContext().getAttribute("isbn").toString();
        String tel = getServletConfig().getServletContext().getAttribute("tel").toString();
        if (isbn == null || isbn.equals("")|| tel == null || tel.equals("")) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_page.jsp").forward(req, resp);
            return;
        }
        Library library = new Library();
        boolean isNotIssued = false;
        try {
            isNotIssued = library.isNotIssuedByIsbn(getServletConfig().getServletContext().getAttribute("isbn").toString());
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }
        if (!isNotIssued) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_book_already_issued.jsp").forward(req, resp);
            return;
        }

//        System.out.println(getServletConfig().getServletContext().getAttribute("isbn"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("tel"));
//        System.out.println(req.getParameter("dateReturn"));
//        System.out.println(isNotIssued);
        try {

            library.issueBookToClient(isbn, tel, Timestamp.valueOf(req.getParameter("dateReturn")));
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/page_ok.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/pages/error_database.jsp").forward(req, resp);
            e.printStackTrace();
        }


//        System.out.println(getServletConfig().getServletContext().getAttribute("client"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("id"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("selectedClient"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("selectedBook"));
//        System.out.println(getServletConfig().getServletContext().getAttribute("selectedReturnDate"));
//
//
//
//        System.out.println(req.getParameter("dateReturn"));
        // TODO: 03.11.2016

//        resp.setContentType("text/html");
//        req.getRequestDispatcher("/WEB-INF/pages/issue_book.jsp").forward(req, resp);
    }
}
