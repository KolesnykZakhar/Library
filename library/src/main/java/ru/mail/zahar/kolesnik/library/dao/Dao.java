package ru.mail.zahar.kolesnik.library.dao;


import ru.mail.zahar.kolesnik.library.models.entity.impl.Book;
import ru.mail.zahar.kolesnik.library.models.entity.impl.Client;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface Dao {

    void addBookToBase(int id, String bookName, String category, String description, String authorName, String authorPatronymic,
                       String authorSurname) throws SQLException, ClassNotFoundException;

    List<Client> getAllClientsFromDatabase() throws SQLException, ClassNotFoundException;

    List<Book> getIssuedBooksFromBase() throws SQLException, ClassNotFoundException;

    void addClientToBase(String name, String surname, String patronymic, String tel, String login,
                         String password) throws SQLException, ClassNotFoundException;


    List<Book> getDebtorsFromBase() throws SQLException, ClassNotFoundException;

    String[] getPersonFromBase(String login, String password) throws SQLException, ClassNotFoundException;



    List<Book> searchBookInBase(String authorName, String authorPatronymic, String authorSurname, String bookName,
                                String category) throws SQLException, ClassNotFoundException;

    List<Book> getAllBooksFromBase() throws SQLException, ClassNotFoundException;

    List<Client> searchClientInBase(String name, String patronymic, String surname) throws SQLException, ClassNotFoundException;


    Client getClientFromDatabaseByPhone(String clientTel) throws SQLException, ClassNotFoundException;

    void editClientInDatabaseByID(int id, String namePerson, String surname, String patronymic, String tel, String loginClient, String passwordClient) throws SQLException, ClassNotFoundException;


    void removeClientByIDFromDatabase(int id) throws SQLException, ClassNotFoundException;

    Book getBookByIdFromBase(int isbn) throws SQLException, ClassNotFoundException;

    boolean isNotIssuedByIsbnFormBase(String isbn) throws SQLException, ClassNotFoundException;

    void issueBookToClientFromDatabase(String isbn, String tel, Timestamp returnDate) throws SQLException, ClassNotFoundException;

    void returnBookInDatabase(String isbn, Timestamp dateReturn) throws SQLException, ClassNotFoundException;

    List<Client> searchClientWithBookInDatabase(String clientName, String clientName1, String clientPatronymic) throws SQLException, ClassNotFoundException;
}
