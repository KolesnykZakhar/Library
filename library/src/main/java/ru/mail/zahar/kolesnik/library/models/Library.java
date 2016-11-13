package ru.mail.zahar.kolesnik.library.models;


import ru.mail.zahar.kolesnik.library.dao.impl.SqlDao;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Book;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Client;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Library {


    private SqlDao dao = new SqlDao();


    /***/
    public List<Book> getDebtors() throws SQLException, ClassNotFoundException {
        return dao.getDebtorsFromBase();
    }

    /***/
    public List<Book> getIssuedBooks() throws SQLException, ClassNotFoundException {
        return dao.getIssuedBooksFromBase();
    }

    /***/
    public void addBook(int id, String bookName, String category, String description, String authorName, String authorPatronymic,
                        String authorSurname) throws SQLException, ClassNotFoundException {
        dao.addBookToBase(id, bookName, category, description, authorName, authorPatronymic, authorSurname);
    }

    /***/
    public void addClient(String name, String surname, String patronymic, String tel, String login,
                          String password) throws SQLException, ClassNotFoundException {
        dao.addClientToBase(name, surname, patronymic, tel, login, password);

    }
    /***/
    public void changeTimeToRead(int period) {
        Book.timeToRead = period;
    }



    public void editClientByID(int id, String namePerson, String surname, String patronymic, String tel,
                           String loginClient, String passwordClient) throws SQLException, ClassNotFoundException {
        dao.editClientInDatabaseByID(id, namePerson, surname, patronymic, tel,
                loginClient, passwordClient);
    }

    public List<Client> searchClient(String name, String surname, String patronymic) throws SQLException, ClassNotFoundException {

        return dao.searchClientInBase(name, surname, patronymic);
    }
    /***/
    public List<Book> searchBook(String authorName, String authorPatronymic, String authorSurname, String bookName,
                                 String category) throws SQLException, ClassNotFoundException {
        return dao.searchBookInBase(authorName, authorPatronymic, authorSurname, bookName, category);
    }

    /***/
    public String[] role(String login, String password) throws SQLException, ClassNotFoundException {
        return dao.getPersonFromBase(login, password);
    }
    /***/
    public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
        return dao.getAllBooksFromBase();
    }
    /***/
    public List<Book> getIssuedBooksByClient(String login) throws SQLException, ClassNotFoundException {
        List<Book> issuedBooks = dao.getIssuedBooksFromBase();
        return issuedBooks.stream().filter(book -> book.issued.login.compareTo(login) == 0).collect(Collectors.toList());
    }

    public List<Client> getAllClients() throws SQLException, ClassNotFoundException {

        return dao.getAllClientsFromDatabase();
    }

    public Client getClientByPhone(String clientTel) throws SQLException, ClassNotFoundException {
        return dao.getClientFromDatabaseByPhone(clientTel);
    }

    public void removeClientByID(int idClient) throws SQLException, ClassNotFoundException {
        dao.removeClientByIDFromDatabase(idClient);
    }

    public Book getBookByIsbn(int isbn) throws SQLException, ClassNotFoundException {
        return dao.getBookByIdFromBase(isbn);
    }

    public boolean isNotIssuedByIsbn(String isbn) throws SQLException, ClassNotFoundException {
        return dao.isNotIssuedByIsbnFormBase(isbn);
    }

    public void issueBookToClient(String isbn, String tel, Timestamp returnDate) throws SQLException, ClassNotFoundException {
        dao.issueBookToClientFromDatabase(isbn, tel, returnDate);
    }

    public List<Book> getIssuedBooksByTel(String clientTel) throws SQLException, ClassNotFoundException {
        List<Book> issuedBooks = dao.getIssuedBooksFromBase();
        return issuedBooks.stream().filter(book -> book.issued.tel.compareTo(clientTel) == 0).collect(Collectors.toList());
    }

    public void returnBook(String idBook, Timestamp dateReturn) throws SQLException, ClassNotFoundException {
        dao.returnBookInDatabase(idBook, dateReturn);
    }

    public List<Client> searchClientWithBook(String clientName, String clientSurname, String clientPatronymic) throws SQLException, ClassNotFoundException {
        return dao.searchClientWithBookInDatabase(clientName, clientSurname, clientPatronymic);
    }
}


